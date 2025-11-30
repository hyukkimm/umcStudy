package com.example.umc.Controller;

import com.example.umc.Entity.User;
import com.example.umc.Service.UserCommandService;
import com.example.umc.Service.query.UserQueryService;
import com.example.umc.common.apiPayload.ApiResponse;
import com.example.umc.common.exception.code.UserSuccessCode;
import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(
            @RequestBody UserReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(dto));
    }
    // 로그인
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginDTO> login(
            @RequestBody @Valid UserReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userQueryService.login(dto));
    }
}