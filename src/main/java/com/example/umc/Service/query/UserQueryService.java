package com.example.umc.Service.query;

import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;
import jakarta.validation.Valid;

public interface UserQueryService {
    UserResDTO.LoginDTO login(
            UserReqDTO.@Valid LoginDTO dto
    );
}
