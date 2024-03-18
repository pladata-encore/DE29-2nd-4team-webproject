package com.mini.emoti.service;

import java.util.List;

import com.mini.emoti.model.dto.PostDto;

import java.time.LocalDateTime;


public interface PostService {

    // 게시글 작성 
    public Long writePost(PostDto dto);
    
    // 게시글 삭제 
    public void deletePost(Long postId);

    // 게시글 수정 
    public void updatePost(PostDto dto, Long postId);

    // 전체 게시글 조회 
    public List<PostDto> getAllPost();

    // 게시글 조회
    public PostDto findById(Long postId);

    
    
}
