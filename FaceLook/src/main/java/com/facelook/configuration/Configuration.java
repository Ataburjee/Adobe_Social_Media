package com.facelook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors().and().csrf().disable()
		.authorizeHttpRequests()
		.anyRequest()
		.permitAll()
		.and()
		.httpBasic();
		
		return httpSecurity.build();
		
	}
	
	@Bean
	  public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	      @Override
	      public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/facelook/**")
	          .allowedOrigins("http://localhost:3000")
	          .allowedMethods("GET", "POST", "PUT", "DELETE")
	          .allowedHeaders("*")
	          .allowCredentials(true)
	          .maxAge(3600);
	      }
	    };
	  }
}