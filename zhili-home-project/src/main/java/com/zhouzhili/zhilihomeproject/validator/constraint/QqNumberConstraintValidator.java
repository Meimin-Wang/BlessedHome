package com.zhouzhili.zhilihomeproject.validator.constraint;

import com.zhouzhili.zhilihomeproject.validator.annotation.QQNumber;
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
    public void initialize(QQNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String qqNumber, ConstraintValidatorContext context) {
        if (StringUtils.hasLength(qqNumber)) {
            return false;
        }
        return qqNumber.matches(QQ_NUMBER_REGEX);
    }
}
