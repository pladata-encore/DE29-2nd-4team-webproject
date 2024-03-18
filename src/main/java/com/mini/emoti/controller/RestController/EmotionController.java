package com.mini.emoti.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.emoti.model.dto.EmotionDto;
import com.mini.emoti.service.EmotionService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/emotion")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    // localhost:8080/api/v1/emotion/insert
    @PostMapping("/insert")
    public String isertEmotion(@RequestBody EmotionDto dto) {
        emotionService.isertEmotion(dto);
        return "저장 성공";
    }

    // 감정 조회
    // localhost:8080/api/v1/emotion/find/{emotionId}
    @GetMapping("/find/{emotionId}")
    public EmotionDto findByEmotionId(@PathVariable("emotionId") Long emotionId) {
        EmotionDto dto = emotionService.findByEmotionId(emotionId);
        return dto;
        // return dto.toString();
    }

    // 감정 삭제
    // localhost:8080/api/v1/emotion/delete/{emotionId}
    @DeleteMapping("/delete/{emotionId}")
    public String deleteEmotion(@PathVariable("emotionId") Long emotionId) {
        emotionService.deleteEmotion(emotionId);
        return "삭제 성공";
    }

    // localhost:8080/api/v1/emotion/update
    @PostMapping("/update")
    public String updateEmotion(@RequestBody EmotionDto dto) {
        emotionService.updateEmotion(dto);
        return "수정 성공";
    }

    // 모든 감정 데이터 조회
    @GetMapping("/all")
    public List<EmotionDto> findAllEmotion() {
        return emotionService.findAllEmotion();

    }

    
    // 유저별 감정 데이터 조회
    @GetMapping("/user/{email}")
    public List<EmotionDto> findByEmailEmotion(@PathVariable("email") String email) {

        return emotionService.findByEmailEmotion(email);

    }

    // 오늘 우리의 기분
    @GetMapping("/today")
    public List<Object[]> getTodayEmotions() {
        log.info("emotionService : "+emotionService.getTodayEmotions());
        return emotionService.getTodayEmotions();
    }

    // 주간 우리의 기분 (7일)
    @GetMapping("/last/weekly")
    public List<Object[]> getLastWeeklyEmotions() {
        // TODO Auto-generated method stub
        return emotionService.getLastWeeklyEmotions();
    }

    // 주별 우리의 기분 (7일)
    @GetMapping("/weekly")
    public List<Object[]> getWeeklyEmotions() {
        // TODO Auto-generated method stub
        // List<Object[]> weeklyEmotions = emotionService.getWeeklyEmotions();

        // for (Object[] row : weeklyEmotions) {
        // String weekday = (String) row[0]; // weekday
        // String emotion_type = (String) row[1]; // count
        // Long count = (Long) row[2]; // count
        // }

        return emotionService.getWeeklyEmotions();
    }

    // 그룹별 가장 많은 감정데이터 - 전체
    @GetMapping("/most/group")
    public List<Object[]> getMostEmotionType() {
        // TODO Auto-generated method stub
        return emotionService.getMostEmotionType();
    }

    // 개인별 가장 많은 감정데이터 - 개인
    @GetMapping("/most/member/{email}")
    public List<Object[]> getMostEmotionTypeByUser(@PathVariable("email") String email) {
        // TODO Auto-generated method stub
        return emotionService.getMostEmotionTypeByUser(email);
    }

}
