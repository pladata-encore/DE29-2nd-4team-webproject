package com.mini.emoti.model.dao;

import com.mini.emoti.model.entity.UserEntity;

public interface UserDao {

    //select
    public UserEntity findByNickname(String UserName) ;

    //select
    public UserEntity findByEmail(String email) ;

    //join 
    public void joinUser(UserEntity entity);


    //update -> profile edit
    public void updateUser(UserEntity entity);

    //delete 
    public void deleteUser(String email);
    
    // login 성공 확인 
    public void updateIsLoginByEmail(UserEntity entity);


}
