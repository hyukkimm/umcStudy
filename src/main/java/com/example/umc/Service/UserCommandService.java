package com.example.umc.Service;

import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;

public interface UserCommandService {
    // 회원가입
    UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    );
}
