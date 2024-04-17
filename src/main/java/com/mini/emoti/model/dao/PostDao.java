package com.mini.emoti.model.dao;

import java.util.List;

import com.mini.emoti.model.entity.PostEntity;

public interface PostDao {

    // 게시글 작성
    public Long writePost(PostEntity entity) throws Exception;

    // 게시글 삭제
    public void deletePost(Long postId) throws Exception;

    // 게시글 수정
    public void updatePost(PostEntity entity) throws Exception;

    // 전체 게시글 조회
    public List<PostEntity> getAllPost() throws Exception;

    // 특정 게시글 조회
    public PostEntity findById(Long postId) throws Exception;

}