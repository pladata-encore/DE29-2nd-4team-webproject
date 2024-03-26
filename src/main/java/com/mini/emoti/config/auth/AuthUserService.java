package com.mini.emoti.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.model.entity.UserEntity;
import com.mini.emoti.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    // email 이 아이디므로 email을 기준으로 load
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        log.info("[AuthUserService] : " + email);

        UserEntity entity = userRepository.findById(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        UserDto dto = new UserDto();

        log.info("[AuthUserService][entity] " + entity);

        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setEmotionCnt(entity.getEmotionCnt());
        dto.setNickname(entity.getNickname());
        dto.setPostCnt(entity.getPostCnt());
        dto.setProfileImage(entity.getProfileImage());

        // username의 데이터가 database에 존재함
        return new AuthUserDto(dto);
    }
}
