package io.hybrid.valhalla.high.scroll;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.hybrid.valhalla.high.util.HighResponseHelper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;

public class ScrollJsonIterator implements Iterator<JsonElement> {

    private static final Logger log = LoggerFactory.getLogger(ScrollJsonIterator.class);

    private String scrollId;
    private long total;
    private long currentCount;
    private RestHighLevelClient client;
    private SearchRequest oriSearchRequest;

    SearchHits currentHits;

    public ScrollJsonIterator(String scrollId, long total, SearchHits currentHits,
                              RestHighLevelClient client, SearchRequest oriSearchRequest) {
        this.scrollId = scrollId;
        this.total = total;
        this.client = client;
        this.oriSearchRequest = oriSearchRequest;
        this.currentHits = currentHits;

        this.currentCount = this.currentHits.totalHits;
    }
    @Override
    public boolean hasNext() {
        return this.currentHits != null;
    }

    @Override
    public JsonElement next() {
        JsonArray result = HighResponseHelper.jsonArrayFromSearchHits(currentHits);
        fetchNext();
        return result;
    }

    // @NOTE will modify currentHits
    public void fetchNext() {

        if (currentCount >= total) {
            this.currentHits = null;
        }

        SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
        scrollRequest.scroll(oriSearchRequest.scroll());

        SearchResponse searchScrollResponse = null;
        try {
            searchScrollResponse = client.searchScroll(scrollRequest);
        } catch (IOException e) {
            log.warn("Unable to scroll for id = {}", scrollId, e);
            this.currentCount = this.total + 1;
            this.currentHits = null;
        }

        this.scrollId = searchScrollResponse.getScrollId();
        this.currentHits = searchScrollResponse.getHits();

        if (this.currentHits.totalHits == 0) {
            this.currentHits = null;
            this.currentCount = this.total + 1;
        } else {
            this.currentCount += this.currentHits.getHits().length;
        }
    }
}
