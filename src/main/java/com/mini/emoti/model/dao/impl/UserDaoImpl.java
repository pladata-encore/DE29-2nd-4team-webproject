package com.mini.emoti.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.UserDao;
import com.mini.emoti.model.entity.UserEntity;
import com.mini.emoti.model.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {


    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserWithEmail(String email) {
        // 사용자 이메일로 조회
        UserEntity user = userRepository.findById(email).orElse(null);
        
        // 사용자가 존재하고 posts 필드를 사용할 경우 초기화
        if (user != null) {
            user.getPosts().size(); // 이렇게 호출하여 지연 로딩된 컬렉션을 초기화
            user.getEmotions().size(); // 이렇게 호출하여 지연 로딩된 컬렉션을 초기화

        }
        
        return user;
    }

    @Override
    public void deleteUser(String email) {
        // TODO Auto-generated method stub
        userRepository.deleteById(email);
    }
    
    @Override
    public UserEntity findByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }



    @Override
    public UserEntity findByNickname(String UserName) {
        // TODO Auto-generated method stub
        
        return userRepository.findByNickname(UserName);
    }

    // security 적용 
    @Override
    public void joinUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
        
    }

    @Override
    public void updateUser(UserEntity entity) {
        // TODO Auto-generated method stub
        userRepository.save(entity);
        
    }
 
    // 로그인 성공시 >> 로그인 유무 저장
    @Override
    public void updateIsLoginByEmail(UserEntity entity) {
        // TODO Auto-generated method stub
        if(entity != null){
        userRepository.save(entity);
        }
    }    
}
