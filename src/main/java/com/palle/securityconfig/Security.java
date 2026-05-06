package com.palle.securityconfig;


	import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.palle.filter.JwtFilter;

	@Configuration
	@EnableWebSecurity //this says go with this config not the spring default config
	public class Security {
		
		@Autowired
		private UserDetailsService userDetailsService;
		
		@Autowired
		private JwtFilter jwtFilter;
		
		@Bean
		public SecurityFilterChain securityChain(HttpSecurity security) {
			security.csrf(customizer->customizer.disable());
			security.authorizeHttpRequests(request->request
					.requestMatchers("/user/register","/user/login")
					.permitAll()
					.anyRequest().authenticated());
			security.formLogin(Customizer.withDefaults());
			security.httpBasic(Customizer.withDefaults());
			security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
			return security.build();
		}
		
		
		@Bean
		public AuthenticationProvider authenticationProvider() { //we are using our own authentication provider instead of the default authentication provider
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);//gives authentication to the database
			provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
			return provider;
		}
		
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) { //authenication manager will talk to authentication provider 
			return configuration.getAuthenticationManager();
		}
		
	}