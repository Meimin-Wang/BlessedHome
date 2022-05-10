package com.blessed.home.controller;

import com.aliyun.oss.common.utils.StringUtils;
import com.blessed.home.constants.AlibabaCloudOssConstants;
import com.blessed.home.dto.ResponseData;
import com.blessed.home.dto.ResponseState;
import com.blessed.home.dto.VerificationCode;
import com.blessed.home.entity.security.User;
import com.blessed.home.exception.UserNotFoundException;
import com.blessed.home.service.AlibabaCloudOssService;
import com.blessed.home.service.UserService;
import com.blessed.home.service.impl.EmailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
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
@Api("用户相关操作API")
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
    @ApiOperation("用户头像上传")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名，路径变量", required = true, dataTypeClass = String.class, dataType = "String"),
            @ApiImplicitParam(name = "avatarFile", value = "用户头像文件", required = true, dataTypeClass = MultipartFile.class, dataType = "MultipartFile")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "用户头像更新成功"),
            @ApiResponse(code = 401, message = "用户未登录"),
            @ApiResponse(code = 403, message = "用户权限不够"),
            @ApiResponse(code = 404, message = "用户不存在")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorityValidator.isAuthenticationEqualsSpecificUsername(authentication, #username)")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/avatar/upload/{username}")
    public ResponseData<URL> uploadAvatar(MultipartFile avatarFile, @PathVariable("username") String username) throws IOException, UserNotFoundException {
        Assert.hasText(username, () -> "上传用户头像，用户名不能为空！");
        Assert.notNull(avatarFile, () -> "头像文件不能为null！");
        boolean existByUsername = userService.isExistByUsername(username);
        if (!existByUsername) {
            throw new UserNotFoundException(username);
        }
        String originalFilename = avatarFile.getOriginalFilename();
        log.info("{} 上传了文件 {}", username, originalFilename);
        URL url = alibabaCloudOssService.uploadFile(avatarFile.getInputStream(), StringUtils.join("/", AlibabaCloudOssConstants.AVATAR_OSS_DIRECTORY, originalFilename));
        ResponseData<URL> response = new ResponseData<>();
        response.setCode(201).setMessage("上传头像成功").setState(ResponseState.CREATED).setBody(url).setResponseDate(new Date());
        return response;
    }

    /**
     * 更新用户头像
     * @param avatarFile 用户头像文件
     * @param username 用户名
     * @return 返回修改后的用户信息
     * @throws UserNotFoundException 当用户名不存在的时候抛出该异常
     * @throws IOException 当发生网络I/O异常的时候抛出该异常
     */
    @ApiOperation("用户更新头像操作")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名，路径变量", required = true, dataTypeClass = String.class, dataType = "String"),
            @ApiImplicitParam(name = "avatarFile", value = "用户头像文件", required = true, dataTypeClass = MultipartFile.class, dataType = "MultipartFile")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "用户头像更新成功"),
            @ApiResponse(code = 401, message = "用户未登录"),
            @ApiResponse(code = 403, message = "用户权限不够"),
            @ApiResponse(code = 404, message = "用户不存在")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorityValidator.isAuthenticationEqualsSpecificUsername(authentication, #username)")
    @ResponseStatus(code = HttpStatus.OK, reason = "用户修改头像成功")
    @PutMapping("/avatar/update/{username}")
    public ResponseData<User> updateUserAvatar(MultipartFile avatarFile, @PathVariable("username") String username) throws UserNotFoundException, IOException {
        Assert.hasText(username, () -> "用户名不能为空！");
        URL url = alibabaCloudOssService.uploadFile(avatarFile.getInputStream(), StringUtils.join("/", AlibabaCloudOssConstants.AVATAR_OSS_DIRECTORY, avatarFile.getOriginalFilename()));
        User user = userService.updateAvatarUrl(username, url.toString());
        ResponseData<User> responseData = new ResponseData<>();
        responseData.setState(ResponseState.SUCCESS)
                .setCode(200)
                .setResponseDate(new Date())
                .setBody(user)
                .setMessage(String.format("%s 更新头像成功", username));
        return responseData;
    }

    @DeleteMapping("/deleteUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "删除成功")
    public void deleteBatchUsers(@RequestBody List<Long> ids) {
        log.info("DeleteBatchUsers: {}", ids);
        userService.deleteBatchUsers(ids);
    }
}
