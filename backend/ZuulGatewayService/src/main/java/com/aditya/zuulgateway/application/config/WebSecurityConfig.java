package com.aditya.zuulgateway.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.aditya.zuulgateway.application.service.UserService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer
{
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthRequestFilter authRequestFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
			.allowedHeaders("*");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception 
	{
		httpSecurity
			.cors().and().csrf().disable()
			.headers().frameOptions().deny()
			.and()
			.authorizeRequests()
				.antMatchers("/auth/login", "/auth/signup", "/auth/confirm", "/swagger-ui.html/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(authEntryPoint)
			.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity
			.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
