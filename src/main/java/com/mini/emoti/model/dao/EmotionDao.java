package com.mini.emoti.model.dao;

import java.util.List;

import com.mini.emoti.model.entity.EmotionEntity;

public interface EmotionDao {
    // select
    public EmotionEntity findByEmotionId(Long emotionId) throws Exception;

    // insertG
    public void isertEmotion(EmotionEntity entity) throws Exception;

    // update
    public void updateEmotion(EmotionEntity entity) throws Exception;

    // delete
    public void deleteEmotion(Long emotionId) throws Exception;

    // 유저 감정 데이터
    public List<EmotionEntity> findByEmailEmotion(String email) throws Exception;

    // 전체 감정 데이터
    public List<EmotionEntity> findAllEmotion() throws Exception;

    // 오늘 우리의 기분
    public List<Object[]> getTodayEmotions() throws Exception;

    // 주간 우리의 기분 (7일)
    public List<Object[]> getLastWeeklyEmotions() throws Exception;

    // 주별 우리의 기분
    public List<Object[]> getWeeklyEmotions() throws Exception;

    // 그룹별 가장 많은 감정데이터 - 전체
    public List<Object[]> getMostEmotionType() throws Exception;

    // 개인별 가장 많은 감정데이터 - 개인
    public List<Object[]> getMostEmotionTypeByUser(String email) throws Exception; 

}
