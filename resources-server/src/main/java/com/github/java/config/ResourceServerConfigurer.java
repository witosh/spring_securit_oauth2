package com.github.java.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfigurer extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource.jwt.key-value}")
	String signKey;

	private final String ROOT_PATTERN = "/**";
	
	private TokenStore tokenStore;

	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore());
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, ROOT_PATTERN).access("hasRole('ROLE_USER')")
                .antMatchers(HttpMethod.POST, ROOT_PATTERN).access("hasRole('ROLE_USER')");
    }
	
	@Bean
	public TokenStore tokenStore() {
		tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
		return tokenStore;
	}

	@Bean
	public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setSigningKey(signKey);
		return jwtAccessTokenConverter;
	}

}
