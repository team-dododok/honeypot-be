package com.dodok.honeypot.global.utils;

import java.util.Objects;

public class UpdateValueUtils {
    public static <T> T updateValue(T currentValue, T newValue) {
        if (Objects.isNull(newValue)) {
            return currentValue;
        } else {
            return newValue;
        }
    }
}
