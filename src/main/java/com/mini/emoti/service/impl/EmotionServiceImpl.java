package com.mini.emoti.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.emoti.model.dao.EmotionDao;
import com.mini.emoti.model.dao.UserDao;
import com.mini.emoti.model.dto.EmotionDto;
import com.mini.emoti.model.entity.EmotionEntity;
import com.mini.emoti.service.EmotionService;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Autowired
    private EmotionDao emotionDao;

    @Autowired
    private UserDao userDao;

    // delete
    @Override
    public void deleteEmotion(Long emotionId) throws Exception {

        EmotionEntity entity = emotionDao.findByEmotionId(emotionId);
        emotionDao.deleteEmotion(entity.getEmotionId());

    }

    // select
    @Override
    public EmotionDto findByEmotionId(Long emotionId) throws Exception {
        EmotionEntity entity;

        entity = emotionDao.findByEmotionId(emotionId);
        EmotionDto dto = new EmotionDto();
        dto.setEmotionId(entity.getEmotionId());
        dto.setEmotionType(entity.getEmotionType());
        dto.setEmail(entity.getUsers().getEmail());
        return dto;

    }

    // insert
    @Override
    public void isertEmotion(EmotionDto dto) throws Exception {
        EmotionEntity entity = new EmotionEntity();
        entity.setEmotionId(dto.getEmotionId());

        entity.setUsers(userDao.findByEmail(dto.getEmail()));
        entity.setEmotionType(dto.getEmotionType());
        emotionDao.isertEmotion(entity);

    }

    // update
    @Override
    public void updateEmotion(EmotionDto dto) throws Exception {
        EmotionEntity entity;

        entity = emotionDao.findByEmotionId(dto.getEmotionId());
        entity.setEmotionId(dto.getEmotionId());
        entity.setUsers(userDao.findByEmail(dto.getEmail()));
        entity.setEmotionType(dto.getEmotionType());
        emotionDao.updateEmotion(entity);

    }

    @Override
    public List<EmotionDto> findAllEmotion() throws Exception {
        // TODO Auto-generated method stub'
        List<EmotionEntity> emotionEntities;

        emotionEntities = emotionDao.findAllEmotion();
        List<EmotionDto> emotionDtos = new ArrayList<>();

        for (EmotionEntity entity : emotionEntities) {
            EmotionDto dto = new EmotionDto();
            dto.setEmotionType(entity.getEmotionType());
            // 다른 필드들을 entity로부터 가져와 dto에 설정
            emotionDtos.add(dto);
        }
        return emotionDtos;

    }

    @Override
    public List<EmotionDto> findByEmailEmotion(String email) throws Exception {
        // TODO Auto-generated method stub
        List<EmotionEntity> emotionEntities;

        emotionEntities = emotionDao.findByEmailEmotion(email);
        List<EmotionDto> emotionDtos = new ArrayList<>();

        for (EmotionEntity entity : emotionEntities) {
            EmotionDto dto = new EmotionDto();
            dto.setEmotionType(entity.getEmotionType());
            // 다른 필드들을 entity로부터 가져와 dto에 설정
            emotionDtos.add(dto);
        }
        return emotionDtos;

    }

    @Override
    public List<Object[]> getLastWeeklyEmotions() throws Exception {
        // TODO Auto-generated method stub

        return emotionDao.getLastWeeklyEmotions();
    }

    @Override
    public List<Object[]> getMostEmotionType() throws Exception {
        // TODO Auto-generated method stub

        return emotionDao.getMostEmotionType();
    }

    @Override
    public List<Object[]> getMostEmotionTypeByUser(String email) throws Exception {
        // TODO Auto-generated method stub

        return emotionDao.getMostEmotionTypeByUser(email);

    }

    @Override
    public List<Object[]> getTodayEmotions() throws Exception {
        // TODO Auto-generated method stub

        return emotionDao.getTodayEmotions();
    }

    @Override
    public List<Object[]> getWeeklyEmotions() throws Exception {
        // TODO Auto-generated method stub
        List<Object[]> weeklyEmotions;

        weeklyEmotions = emotionDao.getWeeklyEmotions();
        for (Object[] row : weeklyEmotions) {
            String weekday = (String) row[0]; // weekday
            String emotion_type = (String) row[1]; // count
            Long count = (Long) row[2]; // count
        }
        return emotionDao.getWeeklyEmotions();

    }

}
