# Implementation of OAuth server

In this section I would like to show configuration of two main classes:
 - WebSecurityConfigurer,
 - AuthorizationServerConfiguration. 

## Provide Spring Security configuration by WebSecurityConfigurerAdapter

Firstly, we need to configure class ***WebSecurityConfigurer*** which provide security for OAuth. We need to remember about important annotations to achive that.

Let's quote [The @EnableWebSecurity annotation and WebSecurityConfigurerAdapter work together to provide web based security](https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/). Which means that annotation **@EnableWebSecurity** disable the default security configuration and we can setup our custom security configuration.

This class  must extends ***WebSecurityConfigurerAdapter*** to provide abstract methods such as:

```java
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
```
In custom implemenatation for this methods we setup service for manage user authentication. 

So, by default all request with Spring Security must be authenticated by mechanism which redirect use to login page to be authenticated. In implementation above we pointed at in-memory database which store user data to authentication. Thanks to Spring Security we can customize this configuration by overwrite methods.

```java
 public void configure(HttpSecurity http) throws Exception
 ```

As I mention above about Spring Security, method **configureGlobal** is used to get user credential (username, password, role) thanks to ***UserDetailsService***  interface . This interface contains one method ***loadUserByUsername***  locate user based on username. 

Class ***AuthenticationManagerBuilder*** provides implementation for **AuthenticationManager** that's why we add  annotation for ***authenticationManagerBean()*** and it's used to perform authentication.

Spring security providea a lots of ways for authentication, one of them is jdbc mechanism implemented in bean:
```java
@Bean
public UserDetailsService userDetailsService()
```
with a ***DataSource*** ineterface which [provides a standard method of working with database connections](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html).

According to docs:
```
The standard JDBC implementation of the UserDetailsService (JdbcDaoImpl) requires tables to load the password, account status (enabled or disabled) and a list of authorities (roles) for the user.
```
[User schema](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#user-schema)

Spring 5 introduced encoding mechanism by ***PasswordEncoder*** to don't store password as a plaint text.

According to docs:
[A PasswordEncoder provides encoding and decoding of passwords presented in the UserDetails object that is returned from the configured UserDetailsService.](https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#core-services-password-encoding)

Thanks to ```createDelegatingPasswordEncoder``` method we can choose type for encoding. In this project we use Bcrypt one.

## Configuration of AuthorizationServerConfiguration class
This part configure all needed methods,  annotations to be able to generate authorization token.

In this case we extend class by ***AuthorizationServerConfigurerAdapter*** and add annotation ***@EnableAuthorizationServer*** with says that it's autorization server configuration class.

First, we configure method which is used to define client:
```java
public void configure(final ClientDetailsServiceConfigurer clients) throws Exception 
```
Based on schemas that were puted in resurces I choose ***jdbc*** type of client and it will be refer to an exitsting store.

The ```public TokenStore tokenStore() ``` method is used to create access token with ***JwtTokenStore*** mechanism. In the implemenations is used ***accessTokenConverter*** to encode and decode by key pair. In this case we will be use symethric key to sign JWT token.

Another one method ```public DefaultTokenServices tokenServices(TokenStore tokenStore, ClientDetailsService clientDetailsService)``` is used to enable or disable some aspects of token mechanism for example we can  put:
 - setSupportRefreshToken, what's says taht we want support a refresh token
 - accessTokenValiditySeconds, or set how long access token is valid. 

One of the important overrides method 
```java
public void configure(final AuthorizationServerEndpointsConfigurer endpoints)
```
thats used before needed things that we setup. So, in this point of configuration we [defines the authorization and token endpoints and the token services](https://projects.spring.io/spring-security-oauth/docs/oauth2.html)

The last method for configuration
```java
public void configure(final AuthorizationServerSecurityConfigurer oauthServer)
``` 
[defines the security constraints on the token endpoint](https://projects.spring.io/spring-security-oauth/docs/oauth2.html). Basicly Spring Security Oauth exposes endpoints that are not exposes by default:
 - /oauth/check_token,
 - /oauth/token_key,
So, we configure it in that way to be have access to it.

Sources:
 - Spring 5.0 Projects, by Nilang Patel,
 - https://projects.spring.io/spring-security-oauth/docs/oauth2.html,
 - http://blog.marcosbarbero.com/centralized-authorization-jwt-spring-boot2/,
