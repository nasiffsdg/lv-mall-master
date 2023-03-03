package com.lv.mall.constant;

/**
 * @author 17324
 */

public enum StatusEnum {

    SPU_NEW(0), SPU_UP(1), SPU_DOWN(2);

    private int code;
    StatusEnum(int i) {
        code = i;
    }
}
