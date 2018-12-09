package com.tang.valhalla.schema;

import com.tang.valhalla.schema.annotation.ElasticSearchRollingGranularity;
import com.tang.valhalla.util.CalendarUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;

public class ElasticSearchSchema {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchSchema.class);

    public String index;
    public String type;
    public String[] id;
    public int shards = 13;
    public int replicas = 1;
    public ElasticSearchProperties properties;
    public Class clz;

    public boolean retire = false;
    public String view;
    public ElasticSearchRollingGranularity granularity = ElasticSearchRollingGranularity.DAY;
    public int limit = 1;

    public Map<String, String> insertParams;

    public ElasticSearchSchema(Class clz) {
        this.clz = clz;
    }


    // -- table creation related

    public JsonObject toIndexCreateJson() {
        JsonObject wrap = new JsonObject();
        wrap.add("settings", toIndexSettingJson());
        wrap.add("mappings", toTypeMappingJson());

        return wrap;
    }

    public String toIndexCreate() {
        return toIndexCreateJson().toString();
    }

    public JsonObject toIndexSettingJson() {
        JsonObject settings = new JsonObject();

        settings.addProperty("number_of_shards", this.shards);
        settings.addProperty("number_of_replicas", this.replicas);

        JsonObject myAnalyzer = new JsonObject();
        myAnalyzer.addProperty("type", "custom");
        myAnalyzer.addProperty("tokenizer", "icu_tokenizer");

        JsonObject analyzer = new JsonObject();
        analyzer.add("icu_analyzer", myAnalyzer);

        JsonObject analysis = new JsonObject();
        analysis.add("analyzer", analyzer);

        JsonObject index = new JsonObject();
        index.add("analysis", analysis);

        settings.add("index", index);

        return settings;
    }

    public String toIndexSetting() {
        return toIndexSettingJson().toString();
    }

    public JsonObject buildGlobalString2KeywordTemplate() {
        // build up global string => keyword template
        JsonObject mapping = new JsonObject();
        mapping.addProperty("type", "keyword");

        JsonObject template = new JsonObject();
        template.addProperty("match_mapping_type", "string");
        template.addProperty("match", "*");
        template.add("mapping", mapping);

        JsonObject strAsKey = new JsonObject();
        strAsKey.add("string_as_keyworkd", template);

        return strAsKey;
    }

    public JsonObject buildFieldTemplate(ElasticSearchField f) {
        JsonObject mapping = new JsonObject();

        if (!f.indexEnabled) {
            mapping.addProperty("index", false);
        }

        if (f.type != ElasticSearchFieldType.NONE) {
            mapping.addProperty("type", f.type.name().toLowerCase());
        }

        JsonObject template = new JsonObject();
        template.addProperty("match", f.key);
        template.add("mapping", mapping);

        JsonObject fieldName = new JsonObject();
        fieldName.add(f.key, template);

        return fieldName;
    }

    public JsonObject toTypeMappingJson() {
        if (!ElasticSearchSchema.isFullCreationMapping()) {

            JsonArray dynamicTemplates = new JsonArray();
            dynamicTemplates.add(buildGlobalString2KeywordTemplate());

            // build up no-index field mapping

            for (ElasticSearchField f : this.properties.fields) {
                dynamicTemplates.add(buildFieldTemplate(f));
            }

            JsonObject dynamicTemplateObj = new JsonObject();
            dynamicTemplateObj.add("dynamic_templates", dynamicTemplates);

            JsonObject mappingContent = new JsonObject();
            mappingContent.add(this.type, dynamicTemplateObj);

            return mappingContent;
        } else {
            JsonObject mappingContent = new JsonObject();
            JsonObject mappingPro = new JsonObject();

            mappingPro.add("properties", this.properties.toJson());
            mappingContent.add(this.type, mappingPro);

            return mappingContent;
        }
    }

    public String toTypeMapping() {
        return toTypeMappingJson().toString();
    }

    public String getId(JsonObject jobj) {
        // quick reference for length = 1 and length = 2
        if (id.length == 1) {
            JsonElement element = jobj.get(id[0]);
            return element.getAsString();
        } else if (id.length == 2) {
            JsonElement e1 = jobj.get(id[0]);
            JsonElement e2 = jobj.get(id[1]);

            return e1.getAsString() + "_" + e2.getAsString();
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(jobj.get(id[0]).getAsString());

            for (int i = 1; i < id.length; i++) {
                builder.append("_");
                if (jobj.get(id[i]) == null) {
                    log.debug("Unable find field {} in class {} of object {}",
                        id[i], clz.getName(), jobj.toString());
                } else {
                    builder.append(jobj.get(id[i]).getAsString());
                }
            }

            return builder.toString();
        }
    }


    // retire related

    public boolean dateShouldRetired(Calendar calendar) {
        return retire &&
            CalendarUtil.isDayBefore(calendar, ElasticSearchRollingGranularity.toDayBeforeToday(granularity, limit));
    }

    public boolean shouldRetired(String targetIndex) {
        String minDay = getWriteIndex(ElasticSearchRollingGranularity.toDayBeforeToday(granularity, limit));

        return targetIndex.compareTo(minDay) < 0;
    }

    public String lastDayIndexBeforeRetire() {
        return getWriteIndex(ElasticSearchRollingGranularity.toDayBeforeToday(granularity, limit));
    }

    public String toWithRetireIndex(Calendar calendar) {
        return  this.index + "_" + ElasticSearchRollingGranularity.toKey(granularity, calendar);
    }

    public String toWithRetireIndex() {
        return toWithRetireIndex(CalendarUtil.today());
    }

    public String toView() {
        return this.view;
    }


    // common index fetch logic

    public String getReadIndex() {
        if (retire) {
            return this.view;
        }

        return this.index;
    }

    public String getWriteIndex(Calendar calendar) {
        if (retire) {
            return this.toWithRetireIndex(calendar);
        }

        return this.index;
    }

    public String getWriteIndex() {
        if (retire) {
            return this.toWithRetireIndex();
        }

        return this.index;
    }


    // --- static managers

    public interface Manager {
        ElasticSearchSchema fromClass(Class clz, boolean parseField);

        ElasticSearchSchema getOrBuild(Class clz);

        ElasticSearchSchema fromShortClassName(String name);

        boolean isFullCreationMapping();

        void setFullCreationMapping(boolean fullCreationMapping);
    }

    public static ElasticSearchSchema fromClass(Class clz, boolean parseField) {
        return INSTANCE.fromClass(clz, parseField);
    }

    public static ElasticSearchSchema getOrBuild(Class clz) {
        return INSTANCE.getOrBuild(clz);
    }

    public static ElasticSearchSchema fromShortClassName(String name) {
        return INSTANCE.fromShortClassName(name);
    }

    public static boolean isFullCreationMapping() {
        return INSTANCE.isFullCreationMapping();
    }

    public static void setFullCreationMapping(boolean fullCreationMapping) {
        INSTANCE.setFullCreationMapping(fullCreationMapping);
    }

    private static Manager INSTANCE = new ElasticSearchSchemaManager(true);

    public static void setInstance(Manager manager) {
        INSTANCE = manager;
    }

    public static Manager instance() {
        return INSTANCE;
    }
}
