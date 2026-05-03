package com.palle.securityconfig;


	import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

	@Configuration
	@EnableWebSecurity //this says go with this config not the spring default config
	public class Security {
		
		@Autowired
		private UserDetailsService userDetailsService; // since UserDetailsService is an interface spring does not know how to implement this 
		
		@Bean
		public SecurityFilterChain securityChain(HttpSecurity security) {
			security.csrf(customizer->customizer.disable());
			security.authorizeHttpRequests(request->request.anyRequest().authenticated());
			security.formLogin(Customizer.withDefaults());
			security.httpBasic(Customizer.withDefaults());
			return security.build();
		}
		
		
		@Bean
		public AuthenticationProvider authenticationProvider() { //we are using our own authentication provider instead of the default authentication provider
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);//gives authentication to the database
			provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
			return provider;
		}
		
	}