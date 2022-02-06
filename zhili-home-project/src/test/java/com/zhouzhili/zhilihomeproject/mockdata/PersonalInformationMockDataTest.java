package com.zhouzhili.zhilihomeproject.mockdata;

import com.github.javafaker.Faker;
import com.zhouzhili.zhilihomeproject.entity.profile.Address;
import com.zhouzhili.zhilihomeproject.entity.profile.City;
import com.zhouzhili.zhilihomeproject.entity.profile.Contact;
import com.zhouzhili.zhilihomeproject.entity.profile.Country;
import com.zhouzhili.zhilihomeproject.entity.profile.Education;
import com.zhouzhili.zhilihomeproject.entity.profile.JobInformation;
import com.zhouzhili.zhilihomeproject.entity.profile.PersonalInformation;
import com.zhouzhili.zhilihomeproject.entity.profile.Province;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.profile.AddressRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.CityRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.ContactRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.CountryRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.EducationRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.JobInformationRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.PersonalInformationRepository;
import com.zhouzhili.zhilihomeproject.repository.profile.ProvinceRepository;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @ClassName PersonalInformationMockDataTest
 * @Description 模拟用户个人信息测试
 * @Author blessed
 * @Date 2022/1/30 : 21:08
 * @Email blessedwmm@gmail.com
 */
@DisplayName("用户个人信息数据数据生成和准备测试")
@Slf4j
@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonalInformationMockDataTest {

    @Autowired private UserRepository userRepository;
    @Autowired private PersonalInformationRepository personalInformationRepository;

    @Autowired private ContactRepository contactRepository;
    @Autowired private EducationRepository educationRepository;
    @Autowired private JobInformationRepository jobInformationRepository;

    @Autowired private AddressRepository addressRepository;
    @Autowired private CountryRepository countryRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private CityRepository cityRepository;

    @Test
    @DisplayName("1. 生成虚拟个人信息数据")
    @Order(1)
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testAddPersonalInformationForEveryUser() {
        // 获取所有用户
        List<User> allUsers = userRepository.findAll();
        int userCount = allUsers.size();

        // 为每一个用户添加个人信息数据
        List<PersonalInformation> peopleInformation = new ArrayList<>();
        Faker faker = new Faker();
        for (User user : allUsers) {
            log.info("{}->{}", user.getId(), user.getUsername());

            PersonalInformation personalInformation = new PersonalInformation();
            // contact: 联系方式
            Contact contact = new Contact();
            contact.setWeChatName(user.getUsername().length() > 20 ? user.getUsername().substring(20) : user.getUsername());
            contact.setPhoneNumber(faker.phoneNumber().subscriberNumber(11));
            contact.setQqNumber(faker.regexify("[1-9][0-9]{4,14}").trim());
            long nowTime = new Date().getTime();
            contact.setCreateDate(new Timestamp(nowTime));
            contact.setUpdateDate(new Timestamp(nowTime));
            Contact savedContact = contactRepository.saveAndFlush(contact);
            personalInformation.setContact(savedContact);

            // education: 教育经历
            Education education = new Education();
            education.setCreateDate(new Timestamp(nowTime));
            education.setUpdateDate(new Timestamp(nowTime));
            education.setMajor(faker.job().field());
            education.setBio(faker.book().genre());
            education.setGraduationSchool(faker.bothify("####??????3####?"));
            Education savedEducation = educationRepository.saveAndFlush(education);
            personalInformation.setEducation(savedEducation);

            // jobInformation: 工作情况
            JobInformation jobInformation = new JobInformation();
            jobInformation.setCreateDate(new Timestamp(nowTime));
            jobInformation.setUpdateDate(new Timestamp(nowTime));
            jobInformation.setCompany(faker.job().title());
            jobInformation.setOccupation(faker.job().position());
            JobInformation savedJobInformation = jobInformationRepository.saveAndFlush(jobInformation);
            personalInformation.setJobInformation(savedJobInformation);

            // address: 联系地址
            Address address = new Address();
            address.setCreateDate(new Timestamp(nowTime));
            address.setUpdateDate(new Timestamp(nowTime));
            address.setAddressDetail(faker.address().fullAddress());
            List<Country> allCountries = countryRepository.findAll();
            Random random = new Random();
            Country randomCountry = allCountries.get(random.nextInt(allCountries.size()));
            log.info("random country: {}", randomCountry);
            address.setCountry(randomCountry);
            List<Province> allProvinces = new ArrayList<>(randomCountry.getProvinces());
            Province randomProvince = allProvinces.get(random.nextInt(allProvinces.size()));
            log.info("random province: {}", randomProvince);
            address.setProvince(randomProvince);
            List<City> allCities = new ArrayList<>(randomProvince.getCities());
            if (allCities.size() > 0) {
                City randomCity = allCities.get(random.nextInt(allCities.size()));
                log.info("random city: {}", randomCity);
                address.setCity(randomCity);
            }
            Address savedAddress = addressRepository.saveAndFlush(address);
            personalInformation.setAddress(savedAddress);

            Calendar calendar = Calendar.getInstance();
            calendar.set(1996, 3, 29);
            personalInformation.setBirthday(new Date(calendar.getTimeInMillis()));
            personalInformation.setCreateDate(new Timestamp(nowTime));
            personalInformation.setUpdateDate(new Timestamp(nowTime));
            // user: 用户
            personalInformation.setUser(user);
            // 添加到集合中
            peopleInformation.add(personalInformation);
        }
        personalInformationRepository.saveAll(peopleInformation);
    }
}
