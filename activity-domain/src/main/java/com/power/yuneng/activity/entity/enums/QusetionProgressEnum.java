package com.power.yuneng.activity.entity.enums;

/**
 * Created by Administrator on 2017/7/28.
 */
public enum  QusetionProgressEnum {
    START(0),
    END_ASYNC(99),
    END(100),
    ;
    private final int value;

    QusetionProgressEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
