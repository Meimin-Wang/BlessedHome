package com.zhouzhili.zhilihomeproject.validator.constraint;

import com.zhouzhili.zhilihomeproject.validator.annotation.GrantType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @ClassName GrantTypeConstraintValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/11 : 19:01
 * @Email blessedwmm@gmail.com
 */
public class GrantTypeConstraintValidator implements ConstraintValidator<GrantType, String> {

    private static final List<String> GRANT_TYPES = List.of("authorization_code", "password", "refresh_token", "implicit");

    @Override
    public boolean isValid(String grantTypeName, ConstraintValidatorContext context) {
        for (String gtn : GRANT_TYPES) {
            if (grantTypeName.equals(gtn)) {
                return true;
            }
        }
        return false;
    }
}
