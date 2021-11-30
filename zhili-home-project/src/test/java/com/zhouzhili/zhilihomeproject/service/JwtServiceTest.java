package com.zhouzhili.zhilihomeproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName JwtServiceTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/30 : 00:28
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
public class JwtServiceTest {

    @Autowired JwtService jwtService;

    @Test
    public void testGetUserIdFromJwtToken() {
        jwtService.getUserIdFromJwtToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJzdXBlcnZpc29yIiwic2NvcGUiOlsiYWxsIl0sImV4cCI6Mzc4NTY4NjM3MywidXNlcklkIjozLCJjcmVhdGUgdGltZSI6eyJtb250aCI6Ik5PVkVNQkVSIiwiZGF5T2ZXZWVrIjoiVFVFU0RBWSIsImRheU9mWWVhciI6MzM0LCJuYW5vIjo2NTQ3MjMwMDAsInllYXIiOjIwMjEsIm1vbnRoVmFsdWUiOjExLCJkYXlPZk1vbnRoIjozMCwiaG91ciI6MCwibWludXRlIjoxOCwic2Vjb25kIjo0NiwiY2hyb25vbG9neSI6eyJjYWxlbmRhclR5cGUiOiJpc284NjAxIiwiaWQiOiJJU08ifX0sImF1dGhvcml0aWVzIjpbIlJPTEVfU1VQRVJWSVNPUiJdLCJqdGkiOiJ4TklOUVY1RU1VZDVNY2lNSkJfTG1yemFaaDgiLCJjbGllbnRfaWQiOiJNZWltaW5XYW5nIn0.cWtsHAxRM9SWjuVAlQFDWbFzlw_esuDf54_q0jZYscY");
    }
}
