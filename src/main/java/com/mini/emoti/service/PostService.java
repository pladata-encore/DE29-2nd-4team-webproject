package com.mini.emoti.service;

import java.util.List;
import java.util.Map;

import com.mini.emoti.model.dto.PostDto;

public interface PostService {

    // 게시글 작성
    // public Long writePost(PostDto dto);
    public List<Map<String, Object>> writePost(PostDto dto) throws Exception;

    // 게시글 삭제
    public void deletePost(Long postId) throws Exception;

    // 게시글 수정
    public void updatePost(PostDto dto, Long postId) throws Exception;

    // 게시글 조회
    public PostDto findById(Long postId) throws Exception;

    // 전체 게시글 조회
    public List<PostDto> viewAllPost() throws Exception;

}
