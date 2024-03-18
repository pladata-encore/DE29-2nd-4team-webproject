package com.mini.emoti.config.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmotionTypes {
    // 행복 놀람 보통 슬픔 분노

    HAPPY(1, "HAPPY"),
    SURPRISE(2, "SURPRISE"),
    NORMAL(3,"NORMAL"),
    SAD(4,"SAD"),
    ANGRY(5,"ANGRY");

    private int emotionType;
    private String emotionMsg;
    
}
