package com.zenpay.fintech.util;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableCustomValidator {
    CustomValidator[] value();
}
