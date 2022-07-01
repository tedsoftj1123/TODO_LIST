package com.example.dmstodo.service;


import com.example.dmstodo.controller.dto.MemberSignUpDto;
import com.example.dmstodo.controller.dto.MemberSignInDto;
import com.example.dmstodo.domain.Member;
import com.example.dmstodo.domain.MemberRepository;
import com.example.dmstodo.domain.Role;
import com.example.dmstodo.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public String signup(MemberSignInDto req) {
        System.out.println("ASdf");
        if(memberRepository.existsByUserId(req.getUserId())){
            throw new RuntimeException("이미 가입된 유저 입니다.");
        }
        memberRepository.save(Member.builder()
                .userName(req.getUserName())
                .userAge(req.getUserAge())
                .userId(req.getUserId())
                .userPw(passwordEncoder.encode(req.getUserPw()))
                .userRole(Role.USER)
                .build());
        return req.getUserName() + "님의 회원가입이 완료되었습니다.";
    }
    public String login(@Valid MemberSignUpDto req) {
        Member member = memberRepository.findByUserId(req.getUserId())
                .orElseThrow(()-> new IllegalStateException("user not exists"));
        if(!passwordEncoder.matches(req.getUserPw(), member.getUserPw())) {
            throw new RuntimeException("wrong password");
        }
        return jwtTokenProvider.createToken(member.getUserId(), List.of(member.getUserRole().getValue()));
    }

    public MemberSignInDto findMember(String uid){
        Member member = memberRepository.findByUserId(uid).orElseThrow(() -> new RuntimeException("cant find user"));
        return MemberSignInDto.builder()
                .userId(member.getUserId())
                .userAge(member.getUserAge())
                .role(member.getUserRole())
                .userName(member.getUsername()).build();
    }
}
