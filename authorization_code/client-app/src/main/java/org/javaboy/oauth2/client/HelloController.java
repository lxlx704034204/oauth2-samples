package org.javaboy.oauth2.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 *
 */
@Controller
public class HelloController {
    @Autowired
    RestTemplate restTemplate;

    // http://localhost:8082/index.html
    @GetMapping("/index.html")
    public String hello(String code, Model model) {
        if (code != null) {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("code", code);
            map.add("client_id", "javaboy5");
            map.add("client_secret", "123");
            map.add("redirect_uri", "http://localhost:8082/index.html");
            map.add("grant_type", "authorization_code");

            /** //如果 code 不为 null，也就是如果是通过授权服务器重定向到这个地址来的，那么我们做如下两个操作：
             *
             * 1.根据拿到的 code，去请求 http://localhost:8080/oauth/token 地址去获取 Token，返回的数据结构如下：
             *      {
             *          "access_token": "e7f223c4-7543-43c0-b5a6-5011743b5af4",
             *          "token_type": "bearer",
             *          "refresh_token": "aafc167b-a112-456e-bbd8-58cb56d915dd",
             *          "expires_in": 7199,
             *          "scope": "all"
             *      }
             *      access_token 就是我们请求数据所需要的令牌，refresh_token 则是我们刷新 token 所需要的令牌，expires_in 表示 token 有效期还剩多久。
             * 2. 接下来，根据我们拿到的 access_token，去请求资源服务器，注意 access_token 通过请求头传递，最后将资源服务器返回的数据放到 model 中。
             *
             * 这里我只是举一个简单的例子，目的是和大家把这个流程走通，正常来说，access_token 我们可能需要一个定时任务去维护，不用每次请求页面都去获取，定期去获取最新的 access_token 即可。
             */

            Map<String,String> resp = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
            //通过[授权服务器]+[资源服务器] 获取到access_token令牌
            String access_token = resp.get("access_token");
            System.out.println(access_token);

            //拿到令牌后，通过令牌获取[资源服务器 8081]的接口的返回值
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + access_token);
            HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> entity = restTemplate.exchange("http://localhost:8081/admin/hello", HttpMethod.GET, httpEntity, String.class);
            model.addAttribute("msg", entity.getBody());
        }
        return "index";
    }
}
