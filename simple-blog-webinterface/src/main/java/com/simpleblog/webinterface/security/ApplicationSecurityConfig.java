package com.simpleblog.webinterface.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.simpleblog.webinterface.auth.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final MyUserDetailsService userService;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, MyUserDetailsService userService) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable() //disable only for non-browser service
				.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/login*", "/logout*", "/user/registration*").permitAll() //Whitelist
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.defaultSuccessUrl("index", true)
				.and()
				.rememberMe()
					.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)) //default to 2 weeks
					.rememberMeParameter("remember-me")
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) //BUT TO BE SECURED, POST SHOULD BE USED
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID", "remember-me")
					.logoutSuccessUrl("/login");
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	} 
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}

}
