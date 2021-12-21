package com.zhouzhili.zhilihomeproject.handler.exception.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhouzhili.zhilihomeproject.dto.ErrorResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName AuthenticationExceptionHandler
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/21 : 14:10
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Component("authenticationExceptionHandler")
public class AuthenticationExceptionHandler implements AuthenticationEntryPoint, InitializingBean {

    private final ObjectMapper objectMapper;

    public AuthenticationExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String uri = request.getRequestURI();
        String message = "该请求需要认证，情先认证，再访问！";
        ResponseState responseState = ResponseState.AUTHENTICATION_FAILED;
        Integer errorCode = HttpStatus.SC_UNAUTHORIZED;
        ErrorResponseData errorResponseData = new ErrorResponseData()
                .setErrorCode(errorCode)
                .setResponseState(responseState)
                .setErrorMessage(message)
                .setUri(uri)
                .setResponseDate(new Date());
        String responseData = objectMapper.writeValueAsString(errorResponseData);
        response.setCharacterEncoding("UTF-8");

        response.setContentType(MimeTypeUtils.APPLICATION_JSON.toString());
        PrintWriter printWriter;
        response.setLocale(Locale.CHINA);
        try {
            printWriter = response.getWriter();
            printWriter.write(responseData);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (IOException ioException) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ioException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(objectMapper, "objectMapper is null!");
    }
}
