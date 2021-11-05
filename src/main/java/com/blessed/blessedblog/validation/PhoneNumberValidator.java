package com.blessed.blessedblog.validation;

import com.blessed.blessedblog.constants.RegularConstants;
import com.blessed.blessedblog.utils.RegexUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

/**
 * @ClassName PhoneNumberValidator
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 4:44 下午
 * @Email blessedwmm@gmail.com
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private boolean required = false;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return RegexUtils.regCheck(value, RegularConstants.PHONE_REGEXP);
    }
}
