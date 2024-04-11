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
    @Pattern(regexp = "^[가-힣a-z0-9_]+$", message = "닉네임은 한글, 영어 소문자, 숫자, 밑줄(_)만 포함할 수 있습니다.")
    @Column(unique = true, length = 10)
    private String nickname;
    
    @NotBlank
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
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
