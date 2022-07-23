package com.solutionpoint.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.solutionpoint.entity.Member;

public class PrincipalDetails implements UserDetails{

	private Member member;
	
	public PrincipalDetails(Member member){
        this.member = member;
    }
	
	public Member getMember() {
		return member;
	}
	
	@Override
    public String getPassword() {
        return member.getMemPasswd();
    }

    @Override
    public String getUsername() {
        return member.getMemId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        member.getRoleList().forEach(r -> {
        	authorities.add(()->{ return r;});
        });
        return authorities;
    }
}
