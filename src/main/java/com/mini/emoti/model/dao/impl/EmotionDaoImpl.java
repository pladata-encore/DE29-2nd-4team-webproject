package com.mini.emoti.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.EmotionDao;
import com.mini.emoti.model.entity.EmotionEntity;
import com.mini.emoti.model.repository.EmotionRepository;


@Service
public class EmotionDaoImpl implements EmotionDao{

    @Autowired
    private EmotionRepository emotionRepository;

    
    // delete
    @Override
    public void deleteEmotion(Long emotionId) {
        emotionRepository.deleteById(emotionId);
    }

    // select
    @Override
    public EmotionEntity findByEmotionId(Long emotionId) {
        return emotionRepository.findById(emotionId).get();
    }

    // insert
    @Override
    public void isertEmotion(EmotionEntity entity) {
        emotionRepository.save(entity);
    }

    // update
    @Override
    public void updateEmotion(EmotionEntity entity) {
        emotionRepository.save(entity);
    }

    @Override
    public List<EmotionEntity> findAllEmotion() {
        // TODO Auto-generated method stub
        return emotionRepository.findAll();
    }


    @Override
    public List<Object[]> getLastWeeklyEmotions() {
        // TODO Auto-generated method stub

        return emotionRepository.getLastWeeklyEmotions();
        
    }

    @Override
    public List<Object[]> getMostEmotionType() {
        // TODO Auto-generated method stub
        return emotionRepository.getMostEmotionType();
    }

    @Override
    public List<Object[]> getMostEmotionTypeByUser(String email) {
        // TODO Auto-generated method stub
        return emotionRepository.getMostEmotionTypeByUser(email);
    }

    @Override
    public List<Object[]> getTodayEmotions() {
        // TODO Auto-generated method stub
        return emotionRepository.getTodayEmotions();
    }

    @Override
    public List<Object[]> getWeeklyEmotions() {
        // TODO Auto-generated method stub
        return emotionRepository.getWeeklyEmotions();
    }

    @Override
    public List<EmotionEntity> findByEmailEmotion(String email) {
        // TODO Auto-generated method stub
        return emotionRepository.findByEmailEmotion(email);

}

}