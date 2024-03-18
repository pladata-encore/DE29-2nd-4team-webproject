package com.mini.emoti.model.dao;

import java.util.List;

import com.mini.emoti.model.entity.PostEntity;

import java.time.LocalDateTime;


public interface PostDao {

    // 게시글 작성 
    public Long writePost(PostEntity entity);
    
    // 게시글 삭제 
    public void deletePost(Long postId);

    // 게시글 수정 
    public void updatePost(PostEntity entity);

    // 전체 게시글 조회 
    public List<PostEntity> getAllPost();
    
    //특정 
    public PostEntity findById(Long postId);

    
}