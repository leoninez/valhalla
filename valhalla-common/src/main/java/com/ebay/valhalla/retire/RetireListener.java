package com.ebay.valhalla.retire;


import com.ebay.valhalla.schema.annotation.ElasticSearchRollingGranularity;

import java.util.Calendar;

public interface RetireListener {
    void onChange(ElasticSearchRollingGranularity granularity, Calendar newDay);
    void onRestore(Calendar day);
}
