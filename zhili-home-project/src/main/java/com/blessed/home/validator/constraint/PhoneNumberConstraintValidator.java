package com.blessed.home.validator.constraint;

import com.blessed.home.validator.annotation.PhoneNumber;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName PhoneNumberConstraintValidator
 * @Description 手机号校验
 * @Author blessed
 * @Date 2021/11/9 : 21:58
 * @Email blessedwmm@gmail.com
 */
public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * 手机号合法校验
     * @param phoneNumber 手机号
     * @param context 校验上下文
     * @return 手机号不能为空，且必须为11位
     */
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return StringUtils.hasText(phoneNumber) && phoneNumber.length() == 11;
    }
}
