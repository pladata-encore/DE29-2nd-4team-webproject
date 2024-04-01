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
    public void deleteEmotion(Long emotionId) {
        try {
            EmotionEntity entity = emotionDao.findByEmotionId(emotionId);
            emotionDao.deleteEmotion(entity.getEmotionId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // select
    @Override
    public EmotionDto findByEmotionId(Long emotionId) {
        EmotionEntity entity;
        try {
            entity = emotionDao.findByEmotionId(emotionId);
            EmotionDto dto = new EmotionDto();
            dto.setEmotionId(entity.getEmotionId());
            dto.setEmotionType(entity.getEmotionType());
            dto.setEmail(entity.getUsers().getEmail());
            return dto;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    // insert
    @Override
    public void isertEmotion(EmotionDto dto) {
        EmotionEntity entity = new EmotionEntity();
        entity.setEmotionId(dto.getEmotionId());
        try {
            entity.setUsers(userDao.findByEmail(dto.getEmail()));
            entity.setEmotionType(dto.getEmotionType());
            emotionDao.isertEmotion(entity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // update
    @Override
    public void updateEmotion(EmotionDto dto) {
        EmotionEntity entity;
        try {
            entity = emotionDao.findByEmotionId(dto.getEmotionId());
            entity.setEmotionId(dto.getEmotionId());
            entity.setUsers(userDao.findByEmail(dto.getEmail()));
            entity.setEmotionType(dto.getEmotionType());
            emotionDao.updateEmotion(entity);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public List<EmotionDto> findAllEmotion() {
        // TODO Auto-generated method stub'
        List<EmotionEntity> emotionEntities;
        try {
            emotionEntities = emotionDao.findAllEmotion();
            List<EmotionDto> emotionDtos = new ArrayList<>();

            for (EmotionEntity entity : emotionEntities) {
                EmotionDto dto = new EmotionDto();
                dto.setEmotionType(entity.getEmotionType());
                // 다른 필드들을 entity로부터 가져와 dto에 설정
                emotionDtos.add(dto);
            }
        return emotionDtos;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
      

    }

    @Override
    public List<EmotionDto> findByEmailEmotion(String email) {
        // TODO Auto-generated method stub
        List<EmotionEntity> emotionEntities;
        try {
            emotionEntities = emotionDao.findByEmailEmotion(email);
            List<EmotionDto> emotionDtos = new ArrayList<>();

            for (EmotionEntity entity : emotionEntities) {
                EmotionDto dto = new EmotionDto();
                dto.setEmotionType(entity.getEmotionType());
                // 다른 필드들을 entity로부터 가져와 dto에 설정
                emotionDtos.add(dto);
            }
            return emotionDtos;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
       
    }

    @Override
    public List<Object[]> getLastWeeklyEmotions() {
        // TODO Auto-generated method stub
        try {
            return emotionDao.getLastWeeklyEmotions();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Object[]> getMostEmotionType() {
        // TODO Auto-generated method stub
        try {
            return emotionDao.getMostEmotionType();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Object[]> getMostEmotionTypeByUser(String email) {
        // TODO Auto-generated method stub
        try {
            return emotionDao.getMostEmotionTypeByUser(email);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Object[]> getTodayEmotions() {
        // TODO Auto-generated method stub
        try {
            return emotionDao.getTodayEmotions();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Object[]> getWeeklyEmotions() {
        // TODO Auto-generated method stub
        List<Object[]> weeklyEmotions;
        try {
            weeklyEmotions = emotionDao.getWeeklyEmotions();
            for (Object[] row : weeklyEmotions) {
                String weekday = (String) row[0]; // weekday
                String emotion_type = (String) row[1]; // count
                Long count = (Long) row[2]; // count
            }
        return emotionDao.getWeeklyEmotions();

    
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;       
    }

}
