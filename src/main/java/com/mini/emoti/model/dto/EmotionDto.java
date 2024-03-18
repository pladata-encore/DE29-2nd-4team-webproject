package com.mini.emoti.model.dto;

import com.mini.emoti.config.constant.EmotionTypes;
import com.mini.emoti.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmotionDto {
    private Long emotionId;
    private String email;
    private String emotionType; 
    
}

