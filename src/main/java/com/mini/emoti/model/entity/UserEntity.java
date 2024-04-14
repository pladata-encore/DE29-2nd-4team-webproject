package com.mini.emoti.model.entity;

import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Value;

import com.mini.emoti.config.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "users")
public class UserEntity extends BaseEntity {

    // email
    @Id
    @NotBlank
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email; // ID

    // nickname
    @NotBlank
    @Pattern(regexp = "^[가-힣a-z0-9_]+$", message = "닉네임은 한글, 영어 소문자, 숫자, 밑줄(_)만 포함할 수 있습니다.")
    @Column(unique = true, length = 10)
    private String nickname;

    // pw
    // @Size(min = 6, max = 10, message = "비밀번호는 최소 6자에서 최대 10자여야 합니다.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]*$", message = "비밀번호는 적어도 하나의 영문자와 하나의 숫자를 포함해야 합니다.")
    private String password; // PW

    // 감정 개수
    @PositiveOrZero
    @Column(name = "emotion_cnt", columnDefinition = "int default 0")
    private int emotionCnt;

    // 게시글 개수
    @PositiveOrZero
    @Column(name = "post_cnt", columnDefinition = "int default 0")
    private int PostCnt;

    // 프로필 이미지
    @URL
    private String profileImage;

    // 로그인 유무
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;

    // 일반사용자 / 관리자를 구분용
    private String role; 

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<EmotionEntity> emotions;

    @Override
    public String toString() {
        return "UserEntity{id=" + email + ", name=" + nickname + "}"; // 예시로 필요한 정보만 반환하도록 수정
    }

}
