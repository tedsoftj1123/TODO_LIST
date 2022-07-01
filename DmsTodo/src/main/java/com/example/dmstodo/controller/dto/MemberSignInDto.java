package com.example.dmstodo.controller.dto;



import com.example.dmstodo.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class MemberSignInDto {
    @NotBlank
    private String userId;
    @NotNull
    private Long userAge;
    @NotBlank
    private String userName;
    @NotBlank
    private String userPw;

    private Role role;
}
