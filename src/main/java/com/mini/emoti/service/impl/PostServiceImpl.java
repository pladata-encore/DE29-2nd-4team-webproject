package com.mini.emoti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.PostDao;
import com.mini.emoti.model.dao.UserDao;
import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.model.entity.PostEntity;
import com.mini.emoti.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void deletePost(Long postId) {
        postDao.deletePost(postId);
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<PostDto> getAllPost() {
        // TODO Auto-generated method stub
        List<PostEntity> postEntityList = postDao.getAllPost();
        List<PostDto> postDtoList  = new ArrayList<>();
        for(int i = 0; i < postEntityList.size(); i++){
            PostDto dto = new PostDto();
            dto.setPostId(postEntityList.get(i).getPostId());
            dto.setCreatedDate(postEntityList.get(i).getCreatedDate());
            dto.setContent(postEntityList.get(i).getContent());
            dto.setHateCnt(postEntityList.get(i).getHateCount());
            dto.setLikeCnt(postEntityList.get(i).getLikeCount());
            dto.setEmail(postEntityList.get(i).getUsers().getEmail());                                                                                                                   
            postDtoList.add(dto);
        }
        return postDtoList;
    
    }

    @Override
    public void updatePost(PostDto dto, Long postId) {
        // TODO Auto-generated method stub
        PostEntity postEntity = postDao.findById(postId);
        postEntity.setContent(dto.getContent());
        postEntity.setHateCount(dto.getHateCnt());
        postEntity.setLikeCount(dto.getLikeCnt());
        postEntity.setUsers(userDao.findByEmail(dto.getEmail()));

        postDao.updatePost(postEntity);
                
    }
    @Override
    public Long writePost(PostDto dto) {
        // TODO Auto-generated method stub
        if(dto != null){
        PostEntity entity = new PostEntity();
        entity.setContent(dto.getContent());
        entity.setHateCount(dto.getHateCnt());
        entity.setLikeCount(dto.getLikeCnt());
        entity.setUsers(userDao.findByEmail(dto.getEmail()));

        Long postId = postDao.writePost(entity);
        return postId;
        }else{
            // log.info("[PostServiceImpl][writePost] ERROR " + dto.toString());
            return 0L;
        }
       
    
    }

    @Override
    public PostDto findById(Long postId) {
        PostEntity postEntity = postDao.findById(postId);
        PostDto postDto = new PostDto();
        postDto.setContent(postEntity.getContent());
        postDto.setCreatedDate(postDto.getCreatedDate());
        postDto.setEmail(postEntity.getUsers().getEmail());
        // TODO Auto-generated method stub
        return postDto;
    }
    
}
