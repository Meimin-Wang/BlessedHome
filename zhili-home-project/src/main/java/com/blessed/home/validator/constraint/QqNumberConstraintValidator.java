package com.blessed.home.validator.constraint;

import com.blessed.home.validator.annotation.QQNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName QqNumberConstraintValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 22:13
 * @Email blessedwmm@gmail.com
 */
public class QqNumberConstraintValidator implements ConstraintValidator<QQNumber, String> {
    private static final String QQ_NUMBER_REGEX = "[1-9][0-9]{4,14}";

    @Override
    public boolean isValid(String qqNumber, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(qqNumber)) {
            return false;
        }
        return qqNumber.matches(QQ_NUMBER_REGEX);
    }
}
