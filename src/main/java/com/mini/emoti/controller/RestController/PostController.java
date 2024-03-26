package com.mini.emoti.controller.RestController;

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
    public List<Map<String, Object>> writePost(@Valid @ModelAttribute PostDto dto) {

        List<Map<String, Object>> responseData = postService.writePost(dto);
        log.info("writePost responseData : " + responseData);
        return responseData;
    }

    // 게시글 삭제
    // @PostMapping("/delete/{postId}")
    // public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId)
    // {
    // log.info("[deletePost1] : " + postId);
    // postService.deletePost(postId);
    // log.info("[deletePost2] : " + postId);
    // return ResponseEntity.ok().body("{\"message\": \"삭제 성공\"}");
    // }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody PostDto postDto) {
        log.info("[deletePost1] : " + postDto.getPostId());
        postService.deletePost(postDto.getPostId());
        return ResponseEntity.ok().body("{\"message\": \"삭제 성공\"}");
    }

    // 게시글 수정
    @PostMapping("/update/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto dto,
            @PathVariable("postId") Long postId) {
        postService.updatePost(dto, postId);

        return ResponseEntity.ok().body("{\"message\": \"수정 성공\"}");
    }

    // 게시글 조회G
    @GetMapping("/view/{postid}")
    public PostDto findById(@PathVariable("postId") Long postId) {
        PostDto dto = postService.findById(postId);
        return dto;
    }

    // // 전체 게시글 조회
    // @GetMapping("/all")
    // public List<Map<String, Object>> getAllPost() {

    // List<Map<String, Object>> allPosts = postService.getAllPost();
    // log.info("[PostResrController][getAllPost] : " + allPosts);
    // return allPosts;
    // }

}
