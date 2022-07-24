package com.solutionpoint.entity;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String memId;
	private String memPasswd;
}
