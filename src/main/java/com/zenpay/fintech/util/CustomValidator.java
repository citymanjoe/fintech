package com.zenpay.fintech.util;

import com.zenpay.fintech.model.dto.enumv.Type;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidatorImpl.class)
@Repeatable(RepeatableCustomValidator.class)
public @interface CustomValidator {
    String message() default "";
    int min() default 1;
    int max() default Integer.MAX_VALUE;
    String format() default "dd-MM-yyyy";
    String[] values() default {};
    Type type();
    boolean optional() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
