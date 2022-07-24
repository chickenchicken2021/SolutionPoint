package com.solutionpoint.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.solutionpoint.config.auth.PrincipalDetails;
import com.solutionpoint.entity.Member;
import com.solutionpoint.mapper.MemberMapper;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private MemberMapper memberMapper;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberMapper memberMapper) {
		super(authenticationManager);
		this.memberMapper = memberMapper;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(JwtProperties.HEADER_STRING);
		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		System.out.println("header : "+header);
		String token = request.getHeader(JwtProperties.HEADER_STRING)
				.replace(JwtProperties.TOKEN_PREFIX, "");

		String memId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
				.getClaim("memId").asString();
		System.out.println(memId);
		
		if(memId != null) {
			Member member = memberMapper.findByMemId(memId);
		
			PrincipalDetails principalDetails = new PrincipalDetails(member);
			Authentication authentication =
					new UsernamePasswordAuthenticationToken(
							principalDetails, 
							null, 
							principalDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
	}
}