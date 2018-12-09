package com.tang.valhalla.retire;


import com.tang.valhalla.schema.annotation.ElasticSearchRollingGranularity;

import java.util.Calendar;

public interface RetireListener {
    void onChange(ElasticSearchRollingGranularity granularity, Calendar newDay);
    void onRestore(Calendar day);
}
