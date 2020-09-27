package org.javaboy.oauth2.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 11.  Spring Security 的基本配置
 * 目的: 实际上就是配置用户。例如你想用微信登录第三方网站，在这个过程中，你得先登录微信，登录微信就要你的用户名/密码信息，
 * 那么我们在这里配置的，其实就是用户的用户名/密码/角色信息。
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //为了代码简洁，我就不把 Spring Security 用户存到数据库中去了，直接存在内存中。
    //javaboy2: 不具备 admin 角色，所以使用这个用户将无法获取到 admin 这个字符串，报错信息如下：403 : [{"error":"access_denied","error_description":"Access is denied"}]
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy1")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("admin")
                .and()
                .withUser("javaboy2")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin();
    }
}
