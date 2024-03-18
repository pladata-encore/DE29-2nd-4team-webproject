package com.mini.emoti.controller.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
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
import org.thymeleaf.TemplateEngine;

import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

@Autowired
private UserService userService;


// @PostMapping("/join")
// public ResponseEntity<String> join(@RequestBody UserDto dto) {
// try {
//     // UserService를 사용하여 유저 가입 처리
//     userService.joinUser(dto);
//     return ResponseEntity.ok().body("회원가입 성공");
// } catch (Exception e) {
//     e.printStackTrace();
//     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
// }
// }

// 회원가입 
@PostMapping("/join")
public RedirectView join(@ModelAttribute UserDto dto) {
    try {
        userService.joinUser(dto);
        // 회원가입이 성공했을 때 인덱스 페이지로 리다이렉션하고 성공 파라미터를 함께 전달
        return new RedirectView("/loginPage?success=true");
    } catch (Exception e) {
        e.printStackTrace();
        // 에러 처리
        // 에러가 발생하면 어떤 페이지로 리다이렉션할지에 따라서 이 부분을 수정할 수 있습니다.
        return new RedirectView("/index");
    }
}



    // 유저 조회
    // localhost:8080/api/v1/user/{userName}
    @GetMapping("/user/{userName}")
    public UserDto findByUserName(@PathVariable("userName") String userName) throws Exception {
        return userService.findByUserName(userName);
        // return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    @GetMapping("/user/{email}")
    public UserDto findByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findByEmail(email);
        // return ResponseEntity.ok(userService.findByUserName(userName).toString());
    }

    // 삭제
    // localhost:8080/api/v1/user/{userName}
    @DeleteMapping("/user/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName) throws Exception {

        userService.deleteUser(userName);
        return ResponseEntity.ok("삭제 성공");
    }

    // 프로필 수정 
    // localhost:8080/api/v1/user/join
    @PostMapping("/user/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserDto dto) throws Exception {
        
        if(dto.getNickname() == null){
        }

        userService.updateUser(dto);

        return ResponseEntity.ok("업데이트 성공");
    }
    
}
