package com.mini.emoti.controller.RestController;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
import java.lang.Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.service.PostService;
import com.mini.emoti.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/post")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    // 게시글 작성 
    @PostMapping("/user/write")
    public List<Map<String, Object>> writePost(@Valid @ModelAttribute PostDto dto) {
        List<Map<String, Object>> responseData = new ArrayList<>();
        
        Long postId = postService.writePost(dto);

        Map<String, Object> data = new HashMap<>();
        try {
            String nickname = userService.findByEmail(dto.getEmail()).getNickname();
            data.put("nickname", nickname);
        } catch (Exception e) {
            data.put("nickname", null);
            e.printStackTrace();
        }
        data.put("content", dto.getContent());
        data.put("postId", postId);
        data.put("email", dto.getEmail());

        responseData.add(data);

        log.info("[PostRestController][responseData] : "+ responseData);
        
        return responseData;
    }

    
    // 게시글 삭제 
    @DeleteMapping("/user/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId){
        log.info("[deletePost] : "+postId);
        postService.deletePost(postId);
        return ResponseEntity.ok("삭제 성공");
    }

    // 게시글 수정 
    @PostMapping("/user/update/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto dto,
                                            @PathVariable("postId") Long postId ){
        postService.updatePost(dto, postId);
        
        return ResponseEntity.ok().body("수정 성공");
    }
    
    // 게시글 조회 
    @GetMapping("/user/view/{postid}")
    public PostDto findById(@PathVariable("postId") Long postId){
        PostDto dto = postService.findById(postId);
        return dto;
    }

    // 전체 게시글 조회 
    @GetMapping("/user/all")
    public List<Map<String,Object>> getAllPost(){
        
        List<PostDto> allPosts = postService.getAllPost();
        log.info("[PostResrController][getAllPost] : "+allPosts);

        List<Map<String, Object>> posts = new ArrayList<>();

        for (PostDto post : allPosts) {
            String email = post.getEmail(); // 작성자의 이메일 가져오기
            String nickname;
            Long postId = post.getPostId();
            try {
                nickname = userService.findByEmail(email).getNickname();
            } catch (Exception e) {
                nickname = null;
                e.printStackTrace();
            }
            String content = post.getContent(); // 내용 가져오기
            
            // 댓글 데이터를 Map 형태로 생성하여 리스트에 추가
            Map<String, Object> postData = new HashMap<>();
            postData.put("author", nickname); // 닉네임으로 대체
            postData.put("content", content);
            postData.put("postId", postId);
            postData.put("email", email);


            posts.add(postData);

            log.info("[PostResrController][getAllPost] : "+postData);
        
        }
    

        return posts;
    }

}

    

