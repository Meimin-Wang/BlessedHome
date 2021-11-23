package com.zhouzhili.zhilihomeproject.validator.constraint;

import com.zhouzhili.zhilihomeproject.validator.annotation.MimeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName MimeTypeConstraintValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/23 : 12:47
 * @Email blessedwmm@gmail.com
 */
@Slf4j
public class MimeTypeConstraintValidator implements ConstraintValidator<MimeType, String> {
    @Override
    public boolean isValid(String mimeType, ConstraintValidatorContext context) {
        try {
            MimeTypeUtils.parseMimeType(mimeType);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            return false;
        }
    }
}
