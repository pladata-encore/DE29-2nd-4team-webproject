package com.mini.emoti.controller;

import java.util.List;
import java.util.Map;

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

import com.mini.emoti.model.dto.EmotionDto;
import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.EmotionService;
import com.mini.emoti.service.PostService;
import com.mini.emoti.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private EmotionService emotionService;

    /* 
     * 유저만 접근 가능
     */


     
    /*  
     * USER REST API
     */

    // 유저 조회
    // localhost:8080/api/v1/user/{userName}

    //  User 객체를 그대로 반환 -> 클라이언트가 예상하는 HttpStatus를 설정해줄 수 없다
    // @GetMapping("/find/name/{userName}")
    // public UserDto findByUserName(@PathVariable("userName") String userName) throws Exception {
    //     return userService.findByUserName(userName);
    // }
    
    // 객체를 그대로 반환하면 HttpStatus를 설정해줄 수 없으므로 객체를 상황에 맞는 ResponseEntity로 감싸서 반환
    @GetMapping( "/find/name/{userName}")
    public ResponseEntity<UserDto> findByUserNamerWithResponseEntity(@PathVariable("userName") String userName) throws Exception {
        return ResponseEntity.ok(userService.findByUserName(userName));
    }


    @GetMapping("/find/user/email/{email}")
    public UserDto findByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findByEmail(email);
        // return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) throws Exception {

        userService.deleteUser(userName);
        return ResponseEntity.ok("삭제 성공");
    }

    // 프로필 수정
    // localhost:8080/api/v1/user/join
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto dto) throws Exception {

        userService.updateUser(dto);

        return ResponseEntity.ok("업데이트 성공");
    }


    /*
     * POST REST API
     */

    // 게시글 작성
    @PostMapping("/post/write")
    public List<Map<String, Object>> writePost(@Valid @ModelAttribute PostDto dto) throws Exception {

        List<Map<String, Object>> responseData;
        responseData = postService.writePost(dto);
        return responseData;

    }

    // 게시글 삭제
    @PostMapping("/post/delete")
    public ResponseEntity<String> deletePost(@RequestBody PostDto postDto) throws Exception {
        log.info("[deletePost1] : " + postDto.getPostId());

        postService.deletePost(postDto.getPostId());
        return ResponseEntity.ok().body("{\"message\": \"삭제 성공\"}");
    }

    // 게시글 수정
    @PostMapping("/post/update/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody PostDto dto,
            @PathVariable("postId") Long postId) throws Exception {

        postService.updatePost(dto, postId);

        return ResponseEntity.ok().body("{\"message\": \"수정 성공\"}");
    }

    // 게시글 조회
    @GetMapping("/post/view/{postid}")
    public PostDto findById(@PathVariable("postId") Long postId) throws Exception {
        PostDto dto;

        dto = postService.findById(postId);
        return dto;
    }

    /*
     * POST REST API
     */

    // localhost:8080/api/v1/emotion/insert
    @PostMapping("/emotion/insert")
    public String isertEmotion(@RequestBody EmotionDto dto) throws Exception {
        emotionService.isertEmotion(dto);
        return "저장 성공";
    }

    // 감정 조회
    // localhost:8080/api/v1/emotion/find/{emotionId}
    @GetMapping("/emotion/find/{emotionId}")
    public EmotionDto findByEmotionId(@PathVariable("emotionId") Long emotionId) throws Exception {
        EmotionDto dto;

        dto = emotionService.findByEmotionId(emotionId);
        return dto;

        // return dto.toString();
    }

    // 감정 삭제
    // localhost:8080/api/v1/emotion/delete/{emotionId}
    @DeleteMapping("/emotion/delete/{emotionId}")
    public String deleteEmotion(@PathVariable("emotionId") Long emotionId) throws Exception {
        emotionService.deleteEmotion(emotionId);
        return "삭제 성공";
    }

    // localhost:8080/api/v1/emotion/update
    @PostMapping("/emotion/update")
    public String updateEmotion(@RequestBody EmotionDto dto) throws Exception {

        emotionService.updateEmotion(dto);
        return "수정 성공";
    }

    // 모든 감정 데이터 조회
    @GetMapping("/emotion/all")
    public List<EmotionDto> findAllEmotion() throws Exception {

        return emotionService.findAllEmotion();

    }

    // 유저별 감정 데이터 조회
    @GetMapping("/emotion/{email}")
    public List<EmotionDto> findByEmailEmotion(@PathVariable("email") String email) throws Exception {

        return emotionService.findByEmailEmotion(email);
    }

    // 오늘 우리의 기분
    @GetMapping("/emotion/today")
    public List<Object[]> getTodayEmotions() throws Exception {

        // log.info("emotionService : " + emotionService.getTodayEmotions());
        return emotionService.getTodayEmotions();
    }

    // 주간 우리의 기분 (7일)
    @GetMapping("/emotion/last/weekly")
    public List<Object[]> getLastWeeklyEmotions() throws Exception {

        return emotionService.getLastWeeklyEmotions();
    }

    // 주별 우리의 기분 (7일)
    @GetMapping("/emotion/weekly")
    public List<Object[]> getWeeklyEmotions() throws Exception {

        return emotionService.getWeeklyEmotions();
    }

    // 그룹별 가장 많은 감정데이터 - 전체
    @GetMapping("/emotion/most/group")
    public List<Object[]> getMostEmotionType() throws Exception {

        return emotionService.getMostEmotionType();
    }

    // 개인별 가장 많은 감정데이터 - 개인
    @GetMapping("/emotion/most/member/{email}")
    public List<Object[]> getMostEmotionTypeByUser(@PathVariable("email") String email) throws Exception {

        return emotionService.getMostEmotionTypeByUser(email);

    }

}
