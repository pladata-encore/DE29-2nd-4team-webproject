package com.mini.emoti.model.dao;

import com.mini.emoti.model.entity.UserEntity;

public interface UserDao {

    // select
    public UserEntity findByNickname(String UserName) throws Exception;

    // select
    public UserEntity findByEmail(String email) throws Exception;

    // join
    public void joinUser(UserEntity entity) throws Exception;

    // update -> profile edit
    public void updateUser(UserEntity entity) throws Exception;

    // delete
    public void deleteUser(String email) throws Exception;

    // login 성공 확인
    public void updateIsLoginByEmail(UserEntity entity) throws Exception;

}
