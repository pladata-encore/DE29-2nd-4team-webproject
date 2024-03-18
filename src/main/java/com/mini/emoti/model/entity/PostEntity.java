package com.mini.emoti.model.entity;

import java.util.List;

import com.mini.emoti.config.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "postEntity")
@Table(name = "post")
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    
    @ManyToOne
    @JoinColumn(name = "email")
    private UserEntity users; // FK 

    @Column(length = 500, nullable = false)
    private String content;

    @PositiveOrZero
    @Column(name = "like_count", columnDefinition = "int default 0")
    private int likeCount;

    @PositiveOrZero
    @Column(name = "hate_count",  columnDefinition = "int default 0")
    private int hateCount;
}