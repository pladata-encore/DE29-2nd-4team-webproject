package com.mini.emoti.config.auth;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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
public class AuthUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    // email 이 아이디므로 email을 기준으로 load
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        log.info("[AuthUserService] : "+email);
        UserEntity entity = userRepository.findByEmail(email);
        // UserEntity entity2 = userRepository.findById(email).get();
        UserDto dto = new UserDto();
        
        log.info("[AuthUserService][entity] "+entity);
        
        dto.setCommentCnt(entity.getCommentCnt());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setEmotionCnt(entity.getEmotionCnt());
        dto.setNickname(entity.getNickname());
        dto.setPostCnt(entity.getPostCnt());
        dto.setProfileImage(entity.getProfileImage());

        // username의 데이터가 database에 존재함(가입함)!!
        if(entity != null) {
            return new AuthUserDto(dto);
        }

        return null;
        
    }

    
}
