package com.zhouzhili.zhilihomeproject.repository;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @ClassName UserRepositoryTest
 * @Description TODO
 * @Author blessed
 * @Date 2022/2/1 : 16:59
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            System.out.println(u);
        }
    }

    public static void main(String[] args) {
        System.out.println(new User());
    }
}
