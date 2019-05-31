# Oauth2 with Spring Security 5 and JWT


## Provide Spring Security configuration by WebSecurityConfigurerAdapter

Class ***WebSecurityConfigurer*** must extends ***WebSecurityConfigurerAdapter***
Let's quote [The @EnableWebSecurity annotation and WebSecurityConfigurerAdapter work together to provide web based security](https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/)

So, by default all request with Spring Security must be authenticated by mechanism which redirect use to login page to be authenticated. Thanks to Spring Security we can customize this configuration by overwrite methods such as:
```
public void configureGlobal(AuthenticationManagerBuilder auth) 

or
public void configureGlobal(AuthenticationManagerBuilder auth)
```
Basicly what we want to achive here is to be able authenticate ourselfs by data stored in H2 (lightway implemenattion of database in Spring) and provide type of security in our application.

According to docs:
```
[A PasswordEncoder provides encoding and decoding of passwords presented in the UserDetails object that is returned from the configured UserDetailsService.](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#core-services-password-encoding)
```
Spring Security 5 introduced more secure mechanism to encoding and decoding stored password. 
```
@Bean
	public PasswordEncoder passwordEncoder() {
		if(this.passwordEncrypt == null) {
			this.passwordEncrypt = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
	    return passwordEncrypt;
	}
```

***UserDetailsService*** can obtain authentication information from a JDBC data source.
```
@Bean
	public UserDetailsService userDetailsService() {
		if (userDetailsService == null) {
			userDetailsService = new JdbcDaoImpl();
			((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
		}
		return userDetailsService;
	}
```

According to docs:
```
The standard JDBC implementation of the UserDetailsService (JdbcDaoImpl) requires tables to load the password, account status (enabled or disabled) and a list of authorities (roles) for the user.
```
[User schema](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#user-schema)
Tables are located at ```src/main/resources/schema.sql```.

```
create table users(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table authorities (
    username varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
```

[Spring Security 5 docs](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#spring-security-web)

## Configuration of 
