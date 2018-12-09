package com.tang.valhalla.schema.annotation;


import com.tang.valhalla.util.CalendarUtil;

import java.util.Calendar;

public enum ElasticSearchRollingGranularity {
    DAY,
    MONTH,
    YEAR;

    public static Calendar toDayBefore(ElasticSearchRollingGranularity granularity, Calendar baseDay, int num) {
        switch (granularity) {
            case DAY:
                return CalendarUtil.toDayOfGap(baseDay, - num);

            case MONTH:
                return CalendarUtil.toMonthOfGap(baseDay, - num);

            case YEAR:
                return CalendarUtil.toYearOfGap(baseDay, - num);

        }

        // should not reach here
        throw new RuntimeException(String.format("Invalid or not handled granularity : %s", granularity));
    }

    public static String toKey(ElasticSearchRollingGranularity granularity, Calendar calendar) {
        switch (granularity) {
            case DAY:
                return CalendarUtil.toDayUnderKey(calendar);

            case MONTH:
                return CalendarUtil.toMonthUnderKey(calendar);

            case YEAR:
                return CalendarUtil.toYearUnderKey(calendar);

        }

        // should not reach here
        throw new RuntimeException(String.format("Invalid or not handled granularity : %s", granularity));
    }

    public static Calendar toDayBeforeToday(ElasticSearchRollingGranularity granularity, int num) {
        return toDayBefore(granularity, CalendarUtil.today(), num);
    }
}
