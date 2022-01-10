package com.readingisgood.bookshop.domain.order;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {
    PENDING(1),
    COMPLETED(2),
    CANCELLED(3);

    private static Map<Integer, OrderStatus> map = new HashMap<>();

    static {
        for (OrderStatus status : OrderStatus.values()) {
            map.put(status.value, status);
        }
    }

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public static OrderStatus valueOf(int status) {
        return map.get(status);
    }

    public int getValue() {
        return value;
    }
}
