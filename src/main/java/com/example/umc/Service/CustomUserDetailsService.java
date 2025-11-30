package com.example.umc.Service;

import com.example.umc.Entity.User;
import com.example.umc.Repository.UserRepository;
import com.example.umc.common.auth.CustomUserDetails;
import com.example.umc.common.exception.UserException;
import com.example.umc.common.exception.code.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}
