package com.solutionpoint.mapper;

import com.solutionpoint.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
	public void save(Member member); // 회원가입
	public Member findByMemId(String MemId); // 회원 정보
	public List<Member> findAll(); // 전체 회원
}
