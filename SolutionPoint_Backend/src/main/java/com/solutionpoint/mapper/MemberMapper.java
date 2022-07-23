package com.solutionpoint.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.solutionpoint.entity.Member;

@Mapper
public interface MemberMapper {
	
	public Member findByMemId(String memId); // 회원가입
	
}
