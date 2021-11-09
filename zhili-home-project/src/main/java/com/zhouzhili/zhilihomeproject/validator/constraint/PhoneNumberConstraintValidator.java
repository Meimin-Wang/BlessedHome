package com.zhouzhili.zhilihomeproject.validator.constraint;

import com.zhouzhili.zhilihomeproject.utils.SmsUtils;
import com.zhouzhili.zhilihomeproject.validator.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName PhoneNumberConstraintValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:58
 * @Email blessedwmm@gmail.com
 */
public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return SmsUtils.isPhoneNumber(phoneNumber);
    }
}
