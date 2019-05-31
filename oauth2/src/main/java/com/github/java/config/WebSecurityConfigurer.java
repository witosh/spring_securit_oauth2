package com.github.java.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private final DataSource dataSource;

	@Autowired
	PasswordEncoder passwordEncrypt;

	@Autowired
	UserDetailsService userDetailsService;

	public WebSecurityConfigurer(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncrypt);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		if (this.passwordEncrypt == null) {
			this.passwordEncrypt = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
		return passwordEncrypt;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		if (userDetailsService == null) {
			userDetailsService = new JdbcDaoImpl();
			((JdbcDaoImpl) userDetailsService).setDataSource(dataSource);
		}
		return userDetailsService;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
