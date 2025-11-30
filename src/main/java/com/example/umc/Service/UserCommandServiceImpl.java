package com.example.umc.Service;

import com.example.umc.Entity.User;
import com.example.umc.Repository.UserRepository;
import com.example.umc.common.auth.enums.Role;
import com.example.umc.converter.UserConverter;
import com.example.umc.dto.UserReqDTO;
import com.example.umc.dto.UserResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public UserResDTO.JoinDTO signup(
            UserReqDTO.JoinDTO dto
    ){
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        User user = UserConverter.toUser(dto, salt, Role.ROLE_USER);
        // DB 적용
        userRepository.save(user);
        return UserConverter.toJoinDTO(user);
    }
}
