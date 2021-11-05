package com.blessed.blessedblog.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName SpringSecurityRoleNameValidator
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/19 : 4:19 下午
 * @Email blessedwmm@gmail.com
 */
public class SpringSecurityRoleNameValidator implements ConstraintValidator<SpringSecurityRoleName, String> {
   private boolean required = false;
   public void initialize(SpringSecurityRoleName constraint) {
      this.required = constraint.required();
   }

   public boolean isValid(String roleName, ConstraintValidatorContext context) {
      return roleName.startsWith("ROLE_");
   }
}
