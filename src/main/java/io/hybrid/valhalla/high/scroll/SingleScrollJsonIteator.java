package io.hybrid.valhalla.high.scroll;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.hybrid.valhalla.high.util.HighResponseHelper;
import org.elasticsearch.search.SearchHits;

import java.util.Iterator;

public class SingleScrollJsonIteator implements Iterator<JsonElement>  {
    private SearchHits hits;
    private boolean consumed = false;

    public SingleScrollJsonIteator(SearchHits hits) {
        this.hits = hits;
    }

    private SingleScrollJsonIteator() {
        this.consumed = true;
    }

    @Override
    public boolean hasNext() {
        return !consumed;
    }
    @Override
    public JsonElement next() {
        JsonArray jsonArray = HighResponseHelper.jsonArrayFromSearchHits(hits);
        consumed = true;
        return jsonArray;
    }

    public final static SingleScrollJsonIteator EMPTY = new SingleScrollJsonIteator();
}
