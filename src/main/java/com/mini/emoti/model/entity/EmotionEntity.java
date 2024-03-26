package com.mini.emoti.model.entity;

import com.mini.emoti.config.BaseEntity;
import com.mini.emoti.config.constant.EmotionTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "emotionEntity")
@Table(name = "emotion")
public class EmotionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionId;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "email") // FK
    private UserEntity users;

    @NotBlank
    private String emotionType;

    public EmotionTypes checkEmotionType() {

        return EmotionTypes.valueOf(this.emotionType);
    }

}