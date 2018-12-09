package com.tang.valhalla.schema;


import com.tang.valhalla.schema.annotation.ElasticSearchIndex;
import com.tang.valhalla.schema.annotation.ElasticSearchMeta;
import com.tang.valhalla.util.StorageUtil;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class ElasticSearchSchemaManager implements ElasticSearchSchema.Manager {
    private static final Logger log = LoggerFactory.getLogger(ElasticSearchSchemaManager.class);

    protected final Map<Class, ElasticSearchSchema> maps = new HashMap<>();
    protected final Map<String, ElasticSearchSchema> shortNameMaps = new HashMap<>();

    private boolean isFullCreationMapping = true;

    public ElasticSearchSchemaManager(boolean isFullCreationMapping) {
        this.isFullCreationMapping = isFullCreationMapping;
    }

    public boolean isFullCreationMapping() {
        return isFullCreationMapping;
    }

    public void setFullCreationMapping(boolean isFullCreationMapping) {
        this.isFullCreationMapping = isFullCreationMapping;
    }

    @Override
    public ElasticSearchSchema fromClass(Class clz, boolean parseField) {
        ElasticSearchMeta ei = (ElasticSearchMeta) clz.getAnnotation(ElasticSearchMeta.class);

        ElasticSearchSchema schema = new ElasticSearchSchema(clz);

        if (ei == null) {
            log.warn("{} contains no @ElasticSearchMeta, use class name as index name, 'data' as type", clz.getName());

            schema.index = clz.getCanonicalName();
            schema.type = "data";

            return schema;
        } else {
            schema.index = ei.index();
            schema.type = ei.type();
            schema.id = ei.id();

            schema.shards = ei.shards();
            schema.replicas = ei.replicas();

            if (ei.retire()) {
                schema.retire = true;
                schema.granularity = ei.granularity();
                schema.limit = ei.limit();

                schema.view = ei.view().equals("") ? schema.index : ei.view();
            }
        }

        if (parseField) {
            ElasticSearchProperties properties = new ElasticSearchProperties();
            buildProperties(clz, properties, ei);
            schema.properties = properties;
            schema.insertParams = new HashMap<>();
        }

        return schema;
    }

    protected void buildProperties(Class clz, ElasticSearchProperties properties, ElasticSearchMeta ei) {
        // scan fields
        Field[] fields = clz.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);

            // if transient is appended, ignore the field
            if (Modifier.isTransient(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
                continue;
            }

            // fetch target name
            // - if SerializedName is defined with name provided, use provided name
            // - else use field name
            SerializedName serializedName = f.getDeclaredAnnotation(SerializedName.class);
            String target;
            if (serializedName != null && !serializedName.value().equals("")) {
                target = serializedName.value();
            } else {
                target = f.getName();
            }

            ElasticSearchIndex index = f.getDeclaredAnnotation(ElasticSearchIndex.class);
            boolean needIndex = false;
            ElasticSearchFieldType type = null;
            Class fclzType = f.getType();

            if (index == null) {
                needIndex = ei.filedIndex();
                if (isFullCreationMapping) {
                    type = getFieldType(f);
                } else {
                    type = ElasticSearchFieldType.NONE;
                }
            } else {
                needIndex = index.enabled();

                type = index.type();
                if (type == ElasticSearchFieldType.AUTO) {
                    if (needIndex) {
                        type = getFieldType(f);
                    } else {
                        type = ElasticSearchFieldType.NONE;
                    }
                }
            }

            if (type == ElasticSearchFieldType.OBJECT) {
                ElasticSearchProperties subProperties = new ElasticSearchProperties();
                buildProperties(fclzType, subProperties, ei);
                properties.fields.add(
                    new ElasticSearchComplexField(target, ElasticSearchFieldType.OBJECT, needIndex, subProperties));
            } else if (type == ElasticSearchFieldType.NESTED) {
                ElasticSearchProperties subProperties = new ElasticSearchProperties();
                buildProperties(fclzType, subProperties,ei);
                properties.fields.add(
                    new ElasticSearchComplexField(target, ElasticSearchFieldType.NESTED, needIndex, subProperties));
            } else {
                properties.fields.add(new ElasticSearchField(target, type, needIndex));
            }
        }

        Class superClz = clz.getSuperclass();
        if (superClz == null
            || superClz.equals(Object.class)
            || superClz.isInterface()
            || List.class.isAssignableFrom(superClz)
            || Map.class.isAssignableFrom(superClz)) {
            return;
        }

        // check up parent
        buildProperties(clz.getSuperclass(), properties, ei);
    }

    private ElasticSearchFieldType getFieldType(Field f) {
        Class clz = f.getType();

        if (clz.isArray()) {
            clz = clz.getComponentType();
        }

        if (List.class.isAssignableFrom(clz)) {
            clz = (Class) StorageUtil.getFieldGenericClz(f)[0];
        }

        if (clz.equals(Byte.TYPE)) {
            return ElasticSearchFieldType.BYTE;
        } else if (clz.equals(Short.TYPE)) {
            return ElasticSearchFieldType.SHORT;
        } else if (clz.equals(Integer.TYPE)) {
            return ElasticSearchFieldType.INTEGER;
        } else if (clz.equals(Long.TYPE)) {
            return ElasticSearchFieldType.LONG;
        } else if (clz.equals(Float.TYPE)) {
            return ElasticSearchFieldType.FLOAT;
        } else if (clz.equals(Double.TYPE)) {
            return ElasticSearchFieldType.DOUBLE;
        } else if (clz.equals(String.class) || clz.isEnum()) {
            // @NOTE @TODO if you want to txt, you need explicitly define it
            // make all string field can be search by es: use text
            return ElasticSearchFieldType.TEXT;
        } else if (clz.equals(Boolean.TYPE)) {
            return ElasticSearchFieldType.BOOLEAN;
        } else if (clz.equals(Date.class)) {
            return ElasticSearchFieldType.DATE;
        } else if (Map.class.isAssignableFrom(clz)) {
            return ElasticSearchFieldType.NESTED;
        } else if (!clz.isPrimitive()) {
            return ElasticSearchFieldType.OBJECT;
        }

        return ElasticSearchFieldType.NONE;
    }

    @Override
    public ElasticSearchSchema getOrBuild(Class clz) {
        if (maps.containsKey(clz)) {
            return maps.get(clz);
        } else {
            ElasticSearchSchema schema = fromClass(clz, true);
            maps.put(clz, schema);
            shortNameMaps.put(clz.getSimpleName(), schema);

            return schema;
        }
    }

    @Override
    public ElasticSearchSchema fromShortClassName(String name) {
        if (shortNameMaps.containsKey(name)) {
            return shortNameMaps.get(name);
        }

        return null;
    }
}
