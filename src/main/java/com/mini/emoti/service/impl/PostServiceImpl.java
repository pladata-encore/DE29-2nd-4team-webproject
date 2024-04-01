package com.mini.emoti.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void deletePost(Long postId) {
        try {
            postDao.deletePost(postId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(PostDto dto, Long postId) {
        // TODO Auto-generated method stub
        PostEntity postEntity;
        try {
            postEntity = postDao.findById(postId);
            postEntity.setContent(dto.getContent());

            postDao.updatePost(postEntity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public List<Map<String, Object>> writePost(PostDto dto) {
        // TODO Auto-generated method stub
        if (dto != null) {
            PostEntity entity = new PostEntity();
            entity.setContent(dto.getContent());
            entity.setHateCount(dto.getHateCnt());
            entity.setLikeCount(dto.getLikeCnt());
            try {
                entity.setUsers(userDao.findByEmail(dto.getEmail()));
                Long postId = postDao.writePost(entity);

                List<Map<String, Object>> responseData = new ArrayList<>();

                Map<String, Object> data = new HashMap<>();
                String nickname = userDao.findByEmail(dto.getEmail()).getNickname();
                data.put("nickname", nickname);

                data.put("content", dto.getContent());
                data.put("postId", postId);
                // data.put("email", dto.getEmail());

                responseData.add(data);
                log.info("[PostServiceImpl][writePost] : " + responseData);
                return responseData;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

       }
       
       return null;

    }

    @Override
    public PostDto findById(Long postId) {
        PostEntity postEntity;
        try {
            postEntity = postDao.findById(postId);
            PostDto postDto = new PostDto();
            postDto.setContent(postEntity.getContent());
            postDto.setCreatedDate(postDto.getCreatedDate());
            postDto.setEmail(postEntity.getUsers().getEmail());
            postDto.setNickname(postEntity.getUsers().getNickname());
            return postDto;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
        // TODO Auto-generated method stub
    }

    @Override
    public List<PostDto> viewAllPost() {
        // TODO Auto-generated method stub
        List<PostEntity> postEntityList;
        try {
            postEntityList = postDao.getAllPost();
            List<PostDto> postDtoList = new ArrayList<>();
            for (int i = 0; i < postEntityList.size(); i++) {
                PostDto dto = new PostDto();
                dto.setPostId(postEntityList.get(i).getPostId());
                dto.setCreatedDate(postEntityList.get(i).getCreatedDate());
                dto.setContent(postEntityList.get(i).getContent());
                dto.setHateCnt(postEntityList.get(i).getHateCount());
                dto.setLikeCnt(postEntityList.get(i).getLikeCount());
                dto.setEmail(postEntityList.get(i).getUsers().getEmail());
                dto.setNickname(postEntityList.get(i).getUsers().getNickname());
    
                postDtoList.add(dto);
                return postDtoList;
    
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    

    }

}
