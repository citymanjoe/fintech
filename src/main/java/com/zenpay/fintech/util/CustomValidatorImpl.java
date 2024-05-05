package com.zenpay.fintech.util;

import com.zenpay.fintech.model.dto.enumv.Type;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.zenpay.fintech.util.HelperUtils.*;

public class CustomValidatorImpl implements ConstraintValidator<CustomValidator, String> {
    protected Type type;
    public int maxSize;
    public int minSize;
    public String[] values;

    @Override
    public void initialize(CustomValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.type = constraintAnnotation.type();
        this.maxSize = constraintAnnotation.max();
        this.minSize = constraintAnnotation.min();
        this.values = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if (s == null)
            return true;
        return switch (this.type) {
            case SIZE -> validateForSize(s);
            case TEXT -> validateStringTextOnly(s);
            case NUMERIC -> isNumericOnly(s);
            case EMAIL -> isEmail(s);
            case CONTAINS -> validateContains(s);
            case EMAIL_OR_PHONE -> isEmailOrPhoneNumber(s);
            case PHONE -> isPhoneValidate(s);
        };
    }

    private boolean validateContains(String value) {
        for (String val : this.values) {
            if (!value.toLowerCase().contains(val.toLowerCase()))
                return false;
        }
        return true;
    }
    private boolean validateForSize(String value) {
        return (value.length() > this.minSize - 1) && (value.length() <= this.maxSize);
    }
    private boolean isPhoneValidate(String x) {
        if(x == null)
            return true;
        if(x.startsWith("+"))
            x = x.substring(1).replaceAll("\\s+", "").trim();
        return phoneNumPattern.matcher(x).find() && x.startsWith("234") && x.length() == 13;
    }
}
