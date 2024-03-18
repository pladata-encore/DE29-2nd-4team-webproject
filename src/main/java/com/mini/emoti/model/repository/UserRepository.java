package com.mini.emoti.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mini.emoti.model.entity.UserEntity;




public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    public UserEntity findByNickname(String UserName);

    public UserEntity findByEmail(String email);

    // @Query(value = "select * from user where name = :name", nativeQuery = true)
    // public UserDto getUserDtoByName(@Param(value = "name") String name);

}
