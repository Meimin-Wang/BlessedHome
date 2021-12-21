package com.zhouzhili.zhilihomeproject.controller;

import com.zhouzhili.zhilihomeproject.constants.AlibabaCloudOssConstants;
import com.zhouzhili.zhilihomeproject.dto.ResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import com.zhouzhili.zhilihomeproject.dto.VerificationCode;
import com.zhouzhili.zhilihomeproject.service.AlibabaCloudOssService;
import com.zhouzhili.zhilihomeproject.service.UserService;
import com.zhouzhili.zhilihomeproject.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @Author blessed
 * @Date 2021/11/13 : 19:04
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 注册模式
     */
    private static final String REGISTER_STATE = "register-state";

    /**
     * 修改密码模式
     */
    private static final String CHANGE_PASSWORD_STATE = "change-password-state";

    private final UserService userService;
    private final EmailServiceImpl emailService;
    private final AlibabaCloudOssService alibabaCloudOssService;

    public UserController(UserService userService, EmailServiceImpl emailService, AlibabaCloudOssService alibabaCloudOssService) {
        this.userService = userService;
        this.emailService = emailService;
        this.alibabaCloudOssService = alibabaCloudOssService;
    }

    /**
     * 获取验证码，应用于两种场景：
     *      模式1. 修改密码时候需要验证码验证
     *      模式2. 注册的时候需要邮箱验证
     * @param username 用户名
     * @param email 邮箱
     * @param state 模式
     * @return 响应 {@link ResponseData<VerificationCode>} JSON数据
     */
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

    /**
     * 上传用户头像
     * @param avatarFile 用户头像文件
     * @param username 用户名
     * @return 返回上传头像后在OSS中的URL
     * @throws IOException 上传文件的过程中抛出 {@link IOException}
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/avatar/upload/{username}")
    public ResponseData<URL> uploadAvatar(MultipartFile avatarFile, @PathVariable("username") String username) throws IOException {
        boolean existByUsername = userService.isExistByUsername(username);
        Assert.isTrue(existByUsername, () -> String.format("用户：{%s}不存在", username));
        String originalFilename = avatarFile.getOriginalFilename();
        log.info("{} 上传了文件 {}", username, originalFilename);
        URL url = alibabaCloudOssService.uploadFile(avatarFile.getInputStream(), AlibabaCloudOssConstants.AVATAR_OSS_DIRECTORY, originalFilename);
        ResponseData<URL> response = new ResponseData<>();
        response.setCode(201).setMessage("上传头像成功").setState(ResponseState.CREATED).setBody(url).setResponseDate(new Date());
        return response;
    }
}
