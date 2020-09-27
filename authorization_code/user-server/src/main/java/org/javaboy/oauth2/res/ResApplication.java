package org.javaboy.oauth2.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//资源服务器 搭建
//大家网上看到的例子，资源服务器大多都是和授权服务器放在一起的，如果项目比较小的话，这样做是没问题的，但是如果是一个大项目，这种做法就不合适了。
//资源服务器就是用来存放用户的资源，例如你在微信上的图像、openid 等信息，用户从授权服务器上拿到 access_token 之后，接下来就可以通过 access_token 来资源服务器请求数据。
@SpringBootApplication
public class ResApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResApplication.class, args);
    }

}
