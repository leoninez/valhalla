package com.ebay.valhalla.retire;


import com.ebay.valhalla.schema.annotation.ElasticSearchRollingGranularity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RetireHandler {
    List<RetireListener> listeners = new ArrayList<>();

    public void appendListener(RetireListener retireListener) {
        this.listeners.add(retireListener);
    }

    public void onChangeToNextDay(Calendar current, Calendar next) {
        // next should be current + 1

        int currentMonth = current.get(Calendar.MONTH);
        int nextMonth = next.get(Calendar.MONTH);

        int currentYear = current.get(Calendar.YEAR);
        int nextYear = next.get(Calendar.YEAR);

        for (RetireListener retireListener: listeners) {
            retireListener.onChange(ElasticSearchRollingGranularity.DAY, next);
        }

        if (currentMonth != nextMonth) {
            for (RetireListener retireListener: listeners) {
                retireListener.onChange(ElasticSearchRollingGranularity.MONTH, next);
            }
        }

        if (currentYear != nextYear) {
            for (RetireListener retireListener: listeners) {
                retireListener.onChange(ElasticSearchRollingGranularity.YEAR, next);
            }
        }
    }

    public void onRestore(Calendar targetDay) {
        for(RetireListener retireHandler: listeners) {
            retireHandler.onRestore(targetDay);
        }
    }
}
