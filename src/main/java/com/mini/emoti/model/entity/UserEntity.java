package com.mini.emoti.model.entity;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.mini.emoti.config.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "UserEntity")
@Table (name = "users")
public class UserEntity extends BaseEntity{

       // email
    @Id
    @NotBlank
    @Email(message = "올바른 이메일 형식이 아닙니다.") 
    // @Column(unique = true)
    private String email; // ID

    // nickname
    @NotBlank
    @Pattern(regexp = "^[가-힣a-z0-9_]+$", message = "닉네임은 한글, 영어 소문자, 숫자, 밑줄(_)만 포함할 수 있습니다.")
    @Column(unique = true, length = 10)
    private String nickname;

    // pw
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password; // PW

    // 개수 -> SQL문으로 정리 

    // 감정 개수 
    @PositiveOrZero
    @Column(name = "emotion_cnt", columnDefinition = "int default 0")
    private int emotionCnt;
    // 댓글 개수
    @PositiveOrZero
    @Column(name = "comment_cnt", columnDefinition = "int default 0")
    private int CommentCnt;
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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PostEntity> posts;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmotionEntity> emotions;
    
    @Override
    public String toString() {
    return "UserEntity{id=" + email + ", name=" + nickname + "}"; // 예시로 필요한 정보만 반환하도록 수정
}


}
