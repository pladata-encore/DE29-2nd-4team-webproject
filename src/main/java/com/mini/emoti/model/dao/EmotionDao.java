package com.mini.emoti.model.dao;

import java.util.List;

import com.mini.emoti.model.entity.EmotionEntity;

public interface EmotionDao {
    // select
    public EmotionEntity findByEmotionId(Long emotionId);

    // insert
    public void isertEmotion(EmotionEntity entity);

    // update
    public void updateEmotion(EmotionEntity entity);

    // delete
    public void deleteEmotion(Long emotionId);

    // 유저 감정 데이터  
    public List<EmotionEntity> findByEmailEmotion(String email);

    // 전체 감정 데이터  
    public List<EmotionEntity> findAllEmotion();

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
