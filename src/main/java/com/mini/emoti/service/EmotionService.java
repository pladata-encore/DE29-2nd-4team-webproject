package com.mini.emoti.service;

import java.util.List;

import com.mini.emoti.model.dto.EmotionDto;

public interface EmotionService {
    // select
    public EmotionDto findByEmotionId(Long emotionId);

    // insert
    public void isertEmotion(EmotionDto dto);

    // update
    public void updateEmotion(EmotionDto dto);

    // delete
    public void deleteEmotion(Long emotionId);

    // 유저 감정 데이터
    public List<EmotionDto> findByEmailEmotion(String email);

    // 전체 감정 데이터
    public List<EmotionDto> findAllEmotion();

    // 오늘 우리의 기분
    public List<Object[]> getTodayEmotions();

    // 주간 우리의 기분 (7일)
    public List<Object[]> getLastWeeklyEmotions();

    // 주별 우리의 기분
    public List<Object[]> getWeeklyEmotions();

    // 그룹별 가장 많은 감정데이터 - 전체
    public List<Object[]> getMostEmotionType();

    // 개인별 가장 많은 감정데이터 - 개인
    public List<Object[]> getMostEmotionTypeByUser(String email);

}
