package com.example.dmstodo.controller;



import com.example.dmstodo.controller.dto.MemberSignUpDto;
import com.example.dmstodo.controller.dto.MemberSignInDto;
import com.example.dmstodo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody MemberSignInDto req){
        return memberService.signup(req);
    }
    @PostMapping("/signin")
    public String singIn(@Valid @RequestBody MemberSignUpDto req){
        return memberService.login(req);
    }
    @GetMapping("/my")
    public MemberSignInDto myPage(Authentication authentication){
        if(authentication == null){
            throw new RuntimeException("cant find user");
        }
        return memberService.findMember(authentication.getName());
    }
}
