package com.solutionpoint.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solutionpoint.entity.Member;
import com.solutionpoint.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		System.out.println("PrincipalDetailsService : 진입");
		Member member = memberMapper.findByMemId(username);
				
				return new PrincipalDetails(member);
	}

}
