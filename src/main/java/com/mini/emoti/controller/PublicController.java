package com.mini.emoti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mini.emoti.model.dto.UserDto;
import com.mini.emoti.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PublicController {
    @Autowired
    private UserService userService;

    /*
     * 누구나 접근 가능
     */

    // Index Page
    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index";
    }

    // Login
    @GetMapping("/loginPage")
    public String loginPage(
            @RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);

        return "login/login";

    }

    // 회원가입
    // @PostMapping("/join")
    // public String join(@ModelAttribute @Valid UserDto dto, RedirectAttributes redirectAttributes) throws Exception {
    //     log.info("[UserController][join] : " + dto.toString());
    //     try {
    //         userService.joinUser(dto);
    //         // 회원가입이 성공했을 때 인덱스 페이지로 리다이렉션하고 성공 파라미터를 함께 전달
    //         redirectAttributes.addAttribute("success", true);
    //         return "redirect:/loginPage";
    //     } catch (Exception e) {

    //         redirectAttributes.addAttribute("error", true);
    //         log.info("[PublicController][joinPage] error : " + e.getMessage());

    //         return "redirect:/joinPage";
    //     }
    // }

    @PostMapping("/join")
    public String join(@ModelAttribute @Valid UserDto dto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) throws Exception {
        log.info("[PublicController][join] Start");

        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우
            log.info("[PublicController][join] 유효성 검사 오류: {}" + bindingResult.getAllErrors());
            redirectAttributes.addAttribute("error", true);
            throw new MethodArgumentNotValidException(null, bindingResult);
            // return "redirect:/joinPage";
        }

        userService.joinUser(dto);
        // 회원가입이 성공했을 때 인덱스 페이지로 리다이렉션하고 성공 파라미터를 함께 전달
        redirectAttributes.addAttribute("success", true);
        return "redirect:/loginPage";
    }

    @GetMapping("/joinPage")
    public String joinPage(@RequestParam(value = "error", required = false) String error, Model model) {
        log.info("[PublicController][joinPage] error : " + error);
        model.addAttribute("error", error);
        return "login/join";
    }

    // 팝업 닫음
    @GetMapping("/close")
    public String close() {
        return "close";
    }

}