package com.solutionpoint.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Member {
	private int memSeq;
	private String memId;
	private String memPasswd;
	private String memName;
	private String memPhone;
	private String memEmail;
	private String memRoles;
	private String memRegDate;

	public List<String> getRoleList(){
		if(this.memRoles.length() > 0){
			return Arrays.asList(this.memRoles.split(","));
		}
		return new ArrayList<>();
	}
}
