package com.solutionpoint.config.jwt;

public interface JwtProperties {

	String SECRET = "김선웅"; 
	int EXPIRATION_TIME = 864000000; 
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
