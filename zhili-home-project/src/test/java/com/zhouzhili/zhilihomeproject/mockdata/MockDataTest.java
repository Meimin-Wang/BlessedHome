package com.zhouzhili.zhilihomeproject.mockdata;

import com.github.javafaker.Address;
import com.github.javafaker.Avatar;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * @ClassName MockDataTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 20:23
 * @Email blessedwmm@gmail.com
 */
public class MockDataTest {

    @Test
    public void testMockData() {
        System.out.println(JMockData.mock(byte.class));
        System.out.println(JMockData.mock(int.class));
        System.out.println(JMockData.mock(long.class));
        System.out.println(JMockData.mock(double.class));
        System.out.println(JMockData.mock(float.class));
        System.out.println(JMockData.mock(String.class));
        System.out.println(JMockData.mock(BigDecimal.class));

        // 基础数据类型的数组
        System.out.println(JMockData.mock(byte[].class));
        System.out.println(JMockData.mock(int[].class));
        System.out.println(JMockData.mock(long[].class));
        System.out.println(JMockData.mock(double[].class));
        System.out.println(JMockData.mock(float[].class));
        System.out.println(JMockData.mock(String[].class));
        System.out.println(JMockData.mock(BigDecimal[].class));
    }

    @Test
    public void testMockBean() {
        Faker faker = new Faker(Locale.ENGLISH);
        Avatar avatar = faker.avatar();
        System.out.println(avatar.image());
    }
}
