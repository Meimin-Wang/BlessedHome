package com.blessed.blessedblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
public class BlessedBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlessedBlogApplication.class, args);
    }

}
