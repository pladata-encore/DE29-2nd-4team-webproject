package com.mini.emoti.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto  {
    private Long postId;
    private String email;
    private String content;
    private int likeCnt;
    private int hateCnt; 
    private LocalDateTime createdDate;
}
