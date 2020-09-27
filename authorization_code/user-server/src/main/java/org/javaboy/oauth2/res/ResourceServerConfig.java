package org.javaboy.oauth2.res;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    //配置了一个 RemoteTokenServices 的实例，这是因为资源服务器和授权服务器是分开的，
    // if资源服务器和授权服务器是放在一起的，就不需要配置 RemoteTokenServices 了。
    //需要配置一个受信任的client到授权服务器验证token令牌。
    @Bean
    RemoteTokenServices tokenServices() {
        //RemoteTokenServices 中我们配置了 access_token 的校验地址、client_id、client_secret 这三个信息，
        //当用户来资源服务器请求资源时，会携带上一个 access_token，通过这里的配置，就能够校验出 token 是否正确等。
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        services.setClientId("javaboy5");
        services.setClientSecret("123");
        return services;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //设置资源服务器id的标识
        resources.resourceId("res1_authorization_code").tokenServices(tokenServices());
    }

    //配置 资源的拦截规则
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //具有“admin”角色的 才能放行(故：javaboy2 不能获取到资源)
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated();
    }
}
