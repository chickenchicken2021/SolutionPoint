package com.solutionpoint.controller;

import com.solutionpoint.config.auth.PrincipalDetails;
import com.solutionpoint.entity.Member;
import com.solutionpoint.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RestApiController {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 모든 사람이 접근 가능
    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }

    // Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용
    // 불가능.
    // 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

    // 유저 혹은 매니저 혹은 어드민이 접근 가능
    @GetMapping("user")
    public String user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("principal : " + principal.getMember().getMemId());
        System.out.println("principal : " + principal.getMember().getMemName());
        System.out.println("principal : " + principal.getMember().getMemPasswd());

        return "<h1>user</h1>";
    }

    // 어드민이 접근 가능
    @GetMapping("admin/users")
    public List<Member> users() {
        return memberMapper.findAll();
    }

    @PostMapping(value = "join", produces = "application/json; charset=UTF-8")
    public String join(@RequestBody Member member) {
        System.out.println(member);
        member.setMemPasswd(bCryptPasswordEncoder.encode(member.getMemPasswd()));
        member.setMemRoles("ROLE_USER");
        memberMapper.save(member);
        return "회원가입완료";
    }

}
