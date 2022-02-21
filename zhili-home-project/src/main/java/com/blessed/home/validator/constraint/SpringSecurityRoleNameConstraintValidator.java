package com.blessed.home.validator.constraint;

import com.blessed.home.validator.annotation.SpringSecurityRoleName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName SpringSecurityRoleNameConstraintValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 15:49
 * @Email blessedwmm@gmail.com
 */
public class SpringSecurityRoleNameConstraintValidator implements ConstraintValidator<SpringSecurityRoleName, String> {

    private static final String ROLE_NAME_PREFIX = "ROLE_";

    @Override
    public void initialize(SpringSecurityRoleName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String roleName, ConstraintValidatorContext context) {
        return roleName.startsWith(ROLE_NAME_PREFIX);
    }
}
