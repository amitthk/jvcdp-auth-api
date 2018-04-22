package com.jvcdp.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	Log log = LogFactory.getLog(WebSecurityConfig.class);

	static String[] allowedCorsOrigins;
	static String[] allowedHttpMethods;
    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/auth/login",
            "/webjars/**",
            "/swagger-ui.html",
            "/favicon.ico",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security"
    };
	
	@Value("${allowed.cors.origins}")
	public void setAllowedCorsOrigins(String[] origins){
		allowedCorsOrigins=origins;
	}
	
	@Value("${allowed.http.methods}")
	public void setAllowedHttpMethods(String[] methods){
		allowedHttpMethods=methods;
	}
	
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()
        .antMatchers(AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
        .antMatchers(HttpMethod.PUT, "/auth/register").permitAll()
        .anyRequest().authenticated()
        .and().cors().and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.authenticationProvider(customAuthenticationProvider);  
  }
  

  
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      final CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Collections.unmodifiableList(Arrays.asList(allowedCorsOrigins)));
      configuration.setAllowedMethods(Collections.unmodifiableList(Arrays.asList(allowedHttpMethods)));
      // setAllowCredentials(true) is important, otherwise:
      // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
      configuration.setAllowCredentials(true);
      // setAllowedHeaders is important! Without it, OPTIONS preflight request
      // will fail with 403 Invalid CORS request
      configuration.setAllowedHeaders(Collections.unmodifiableList(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
      configuration.setExposedHeaders(Collections.unmodifiableList(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }

}