package com.mini.emoti.service;

import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.model.entity.UserEntity;

public interface UserService {

    // 가입 insert
    public void joinUser(UserDto dto) throws Exception;

    // 조회 select
    public UserDto findByUserName(String userName) throws Exception;
    
    public UserDto findByEmail(String email) throws Exception;

    // 프로필 수정 update
    public void updateUser(UserDto dto) throws Exception;

    // 회원 탈퇴 delete
    public void deleteUser(String userName) throws Exception;

    // 로그인 성공시 >> 로그인 유무 저장
    public void updateIsLoginByEmail(String email, Boolean isLogin) throws Exception;



    
    
}
