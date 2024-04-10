package com.mini.emoti.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.service.PostService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 작성
    @PostMapping("/write")
    public List<Map<String, Object>> writePost(@Valid @ModelAttribute PostDto dto) throws Exception {

        List<Map<String, Object>> responseData;
        try {
            responseData = postService.writePost(dto);
            return responseData;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        // log.info("writePost responseData : " + responseData);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody PostDto postDto) throws Exception {
        log.info("[deletePost1] : " + postDto.getPostId());

        postService.deletePost(postDto.getPostId());
        return ResponseEntity.ok().body("{\"message\": \"삭제 성공\"}");
    }

    // 게시글 수정
    @PostMapping("/update/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto dto,
            @PathVariable("postId") Long postId) throws Exception {

        postService.updatePost(dto, postId);

        return ResponseEntity.ok().body("{\"message\": \"수정 성공\"}");
    }

    // 게시글 조회G
    @GetMapping("/view/{postid}")
    public PostDto findById(@PathVariable("postId") Long postId) throws Exception {
        PostDto dto;

        dto = postService.findById(postId);
        return dto;
    }
}
