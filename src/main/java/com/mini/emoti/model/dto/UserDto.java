package com.mini.emoti.model.dto;

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
public class UserDto {
    private String nickname;
    private String email;
    private String password;
    private String profileImage;
    private int emotionCnt;
    private int postCnt;

}
