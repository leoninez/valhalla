package io.hybrid.valhalla.common.retire;

import io.hybrid.valhalla.common.schema.annotation.ElasticSearchRollingGranularity;

import java.util.Calendar;

public interface RetireListener {
    void onChange(ElasticSearchRollingGranularity granularity, Calendar newDay);
    void onRestore(Calendar day);
}
