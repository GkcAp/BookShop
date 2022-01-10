package com.readingisgood.bookshop.domain.order;

import java.util.HashMap;
import java.util.Map;

public enum MonthOrder {
    January(1),
    February(2),
    March(3),
    April(4),
    May(5),
    June(6),
    July(7),
    August(8),
    September(9),
    October(10),
    November(11),
    December(12);


    private static Map<Integer, MonthOrder> map = new HashMap<>();

    static {
        for (MonthOrder status : MonthOrder.values()) {
            map.put(status.value, status);
        }
    }

    private int value;

    MonthOrder(int value) {
        this.value = value;
    }

    public static MonthOrder valueOf(int status) {
        return map.get(status);
    }

    public int getValue() {
        return value;
    }
}
