package org.javaboy.oauth2.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * s1.提供了一个 TokenStore 的实例，这个是指你生成的 Token 要往哪里存储，我们可以存在 Redis 中，也可以存在内存中，
 * 也可以结合 JWT 等等，这里，我们就先把它存在内存中，所以提供一个 InMemoryTokenStore 的实例即可。
 */
@Configuration
public class AccessTokenConfig {
    @Bean
    TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
