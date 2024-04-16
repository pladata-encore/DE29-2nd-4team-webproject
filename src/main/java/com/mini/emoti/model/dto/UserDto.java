package com.mini.emoti.model.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotBlank
    @Pattern(regexp = "^[가-힣a-z0-9_]+$")
    @Column(unique = true, length = 12)
    private String nickname;
    
    @NotBlank
    @Email
    private String email;

    @NotBlank
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "비밀번호는 적어도 하나의 영문자와 하나의 숫자를 포함해야 합니다.")
    private String password;
    
    @URL
    private String profileImage;

    @PositiveOrZero
    private int emotionCnt;
    
    @PositiveOrZero
    private int postCnt;

    // 일반사용자 / 관리자를 구분용
    private String role; 

}
