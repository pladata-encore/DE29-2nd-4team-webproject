package com.mini.emoti.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mini.emoti.config.error.UserjoinException;
import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/join")
    public RedirectView join(@ModelAttribute UserDto dto) throws UserjoinException, Exception {
    log.info("[UserController][join] : " + dto.toString());

    userService.joinUser(dto);
    return new RedirectView("/loginPage?success=true");
    }

    // 유저 조회
    // localhost:8080/api/v1/user/{userName}
    @GetMapping("/find/name/{userName}")
    public UserDto findByUserName(@PathVariable("userName") String userName) throws Exception {
        return userService.findByUserName(userName);
        // return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    @GetMapping("/find/user/email/{email}")
    public UserDto findByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findByEmail(email);
        // return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) throws Exception {

        userService.deleteUser(userName);
        return ResponseEntity.ok("삭제 성공");
    }

    // 프로필 수정
    // localhost:8080/api/v1/user/join
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto dto) throws Exception {

        if (dto.getNickname() == null) {
        }

        userService.updateUser(dto);

        return ResponseEntity.ok("업데이트 성공");
    }

}
