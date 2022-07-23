package com.solutionpoint.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Member {

	private int  MemSeq; 	
	private String MemId;	
	private String MemPasswd;	
	private String MemName;
	private String MemPhone;	
	private String MemEmail;
	private String MemRole;
	private String MemRegDate;
	
	public List<String> getRoleList(){
	    if(this.MemRole.length() > 0){
	        return Arrays.asList(this.MemRole.split(","));
	    }
	    return new ArrayList<>();
	}
}
