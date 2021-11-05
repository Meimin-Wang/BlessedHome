package com.blessed.blessedblog.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = {PhoneNumberValidator.class})
public @interface PhoneNumber {
    String message() default "手机号不合法";
    boolean required() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
