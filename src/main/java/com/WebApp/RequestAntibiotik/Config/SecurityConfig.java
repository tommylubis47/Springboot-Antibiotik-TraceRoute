
package com.WebApp.RequestAntibiotik.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
			http.csrf().disable()
			.authorizeHttpRequests()
                        .requestMatchers("/plugins/**","/dist/**").permitAll()
                        .requestMatchers("/user/registration/**","/user/processRegistration/**").permitAll()       
			//.requestMatchers("/").permitAll().anyRequest().authenticated()
                        .requestMatchers("/user/profile").authenticated()
                        .requestMatchers("/").authenticated()
			.and()
			.formLogin().loginProcessingUrl("/processLogin").loginPage("/userLogin")
			.defaultSuccessUrl("/user/profile",true).permitAll()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/userLogout"))
			.logoutSuccessUrl("/pageLogout")
			.invalidateHttpSession(true)
			.clearAuthentication(true).permitAll();
		
		return http.build();
		//loginPage("/userLogin")
		//loginProcessingUrl("/userLogin")
	}
    
}
