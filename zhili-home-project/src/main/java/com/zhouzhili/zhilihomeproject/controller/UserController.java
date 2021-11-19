package com.zhouzhili.zhilihomeproject.controller;

import com.zhouzhili.zhilihomeproject.dto.ResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import com.zhouzhili.zhilihomeproject.dto.VerificationCode;
import com.zhouzhili.zhilihomeproject.service.EmailService;
import com.zhouzhili.zhilihomeproject.service.UserService;
import com.zhouzhili.zhilihomeproject.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/13 : 19:04
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private static final String REGISTER_STATE = "register-state";
    private static final String CHANGE_PASSWORD_STATE = "change-password-state";

    private final UserService userService;
    private final EmailServiceImpl emailService;

    public UserController(UserService userService, EmailServiceImpl emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/valid-code")
    public ResponseData<VerificationCode> getValidationCode(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") @Email(message = "邮箱格式不合法") String email,
            @RequestParam(name = "state") String state
            ) {
        // 断言用户名和邮箱是否为空，需要处理异常
        Assert.hasLength(username, "用户名不能为空");
        Assert.hasLength(email, "邮箱不能为空");
        List<String> verifiedCodeStates = List.of(REGISTER_STATE, CHANGE_PASSWORD_STATE);
        Assert.isTrue(CollectionUtils.contains(verifiedCodeStates.iterator(), state),
                String.format("%s 不在候选状态：[%s, %s]", state, REGISTER_STATE, CHANGE_PASSWORD_STATE));
        ResponseData<VerificationCode> verificationCodeResponseData = new ResponseData<>();
        if (REGISTER_STATE.equals(state)) {
            if (userService.isExistByUsername(username) || userService.isExistByEmail(email)) {
                throw new IllegalArgumentException(String.format("用户名 %s 或邮箱 %s 已经存在", username, email));
            } else {
                VerificationCode verificationCode = userService.getVerificationCode(username);
                emailService.sendVerificationCode(username, email, verificationCode);
                verificationCodeResponseData.setCode(200)
                .setState(ResponseState.SUCCESS)
                .setBody(verificationCode)
                .setMessage("获取验证码成功！")
                .setResponseDate(new Date());
                return verificationCodeResponseData;
            }
        } else {

            return verificationCodeResponseData;
        }
    }
}
