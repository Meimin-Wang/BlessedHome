package com.blessed.blessedblog.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = SpringSecurityRoleNameValidator.class)
public @interface SpringSecurityRoleName {
    String message() default "角色命名不符合Spring Security规范";
    boolean required() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
