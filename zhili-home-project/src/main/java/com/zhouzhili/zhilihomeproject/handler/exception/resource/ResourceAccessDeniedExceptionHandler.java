package com.zhouzhili.zhilihomeproject.handler.exception.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhouzhili.zhilihomeproject.dto.ErrorResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @ClassName ResourceAccessDeniedExceptionHandler
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/20 : 22:03
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Component
public class ResourceAccessDeniedExceptionHandler implements AccessDeniedHandler, InitializingBean {

    private final ObjectMapper objectMapper;

    @Value("${jwt.key}")
    private String key;

    public ResourceAccessDeniedExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        String jwtToken = authorization.split(" ")[1];
        Jwt jwt = Jwts.parserBuilder().setSigningKey(key.getBytes()).build().parse(jwtToken);
        ErrorResponseData errorResponseData = new ErrorResponseData()
                .setResponseDate(new Date())
                .setErrorCode(HttpStatus.SC_FORBIDDEN)
                .setErrorMessage("您的权限不够，禁止访问！")
                .setResponseState(ResponseState.AUTHENTICATION_FAILED)
                .setUri(request.getRequestURI())
                .setAdditionalInformation(jwt.getBody())
                ;
        response.setContentType(MimeTypeUtils.APPLICATION_JSON.toString());
        response.setLocale(Locale.CHINA);
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter;
        try {
            printWriter = response.getWriter();
            printWriter.write(objectMapper.writeValueAsString(errorResponseData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(objectMapper, "objectMapper is null!");
    }
}
