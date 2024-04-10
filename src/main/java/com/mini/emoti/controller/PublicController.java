package com.mini.emoti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api/v1")
public class PublicController {
    @Autowired
    private UserService userService;

    // 회원가입
    @PostMapping("/join")
    public String join(@ModelAttribute UserDto dto,  RedirectAttributes redirectAttributes) throws Exception {
        log.info("[UserController][join] : " + dto.toString());
        try {
            userService.joinUser(dto);
            // 회원가입이 성공했을 때 인덱스 페이지로 리다이렉션하고 성공 파라미터를 함께 전달
            redirectAttributes.addAttribute("success", true);
            return "redirect:/loginPage";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            // 에러 처리
            return "redirect:/index";
        }
    }

}