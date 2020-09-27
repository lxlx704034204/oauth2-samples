package org.javaboy.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//第三方应用 搭建
//当前第三方应用就是一个普通的 Spring Boot工程(Thymeleaf 依赖 + Web 依赖)
//注意，第三方应用并非必须，下面所写的代码也可以用 POSTMAN 去测试，这个小伙伴们可以自行尝试。
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
