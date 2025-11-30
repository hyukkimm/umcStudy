package com.example.umc.Service;

import com.example.umc.Entity.User;
import com.example.umc.Repository.UserRepository;
import com.example.umc.converter.UserConverter;
import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    // 회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        User user = UserConverter.toUser(dto);
        // DB 적용
        userRepository.save(user);
        return UserConverter.toJoinDTO(user);
    }
}
