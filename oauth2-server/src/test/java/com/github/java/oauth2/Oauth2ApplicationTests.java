package com.github.java.oauth2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2ApplicationTests {

	@Test
	public void contextLoads() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("client secret: "+encoder.encode("github_secret"));
//		System.out.print(""+encoder.encode("test"));
		System.out.println("user password: "+encoder.encode("test"));
		
	}

}
