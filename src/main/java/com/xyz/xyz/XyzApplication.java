package com.xyz.xyz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class XyzApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(XyzApplication.class, args);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/login")
				.permitAll()
				.antMatchers("/**")
				.hasAnyRole("BASICO")
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/wellcome")
				.failureUrl("/login?error=true")
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/login?logout=true")
				.invalidateHttpSession(true)
				.permitAll()
				.and()
				.csrf()
				.disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("diego").password("{noop}miclave").roles("BASICO");
	}

}
