package com.palle.filter;

import java.io.IOException;

import org.springframework.context.ApplicationContext;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.palle.service.JwtService;
import com.palle.service.StudentUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	/*
	 * ApplicationContext is the main container of Spring.
	 * It stores and manages all Spring objects called Beans.
	 */
	
	@Autowired
	ApplicationContext context;
	
	
	@Autowired
	public JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//the token starts with "Bearer " and then token values
		String authHeader=request.getHeader("Authorization");
		String token=null;
		String username=null;
		
		if(authHeader!=null&&authHeader.startsWith("Bearer ")) {
			 token=authHeader.substring(7); //token starts from 7th index
			 username=jwtService.extractUsername(token);
		}
		
		if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=context.getBean(StudentUserDetailsService.class).loadUserByUsername(username);
			if(jwtService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				
				/*
				 * adds request info like:
					IP address
					session info
					request details
					from:
					HttpServletRequest
				 */
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		filterChain.doFilter(request, response);
	}

}
