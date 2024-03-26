package com.mini.emoti.controller.ViewController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.service.PostService;
import com.mini.emoti.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ViewController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    /*
     * 누구나 접근 가능
     */

    @GetMapping("/index")
    public String index(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);

        return "login/login";

    }

    @GetMapping("/joinPage")
    public String joinPage(Model model) {

        return "login/join";
    }

    @GetMapping("/close")
    public String close() {
        return "close";
    }

    /*
     * 로그인한 경우만
     */

    @GetMapping("user/index")
    public String user(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());

        return "member/index";
    }

    @GetMapping("user/mypage")
    public String mypage(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "member/mypage";
    }

    @GetMapping("/graph")
    public String graph() {
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // model.addAttribute("username", userDetails.getUsername());
        return "member/graphs";
    }

    @GetMapping("user/view/all")
    public String viewPost(Model model, Authentication authentication) {
        log.info("[PostController][viewPost] start");
        if (authentication == null) {
            return "redirect:/index";
        }
        List<PostDto> postDtos = postService.viewAllPost();

        if (!postDtos.isEmpty()) {
            log.info("[PostController][viewPost] postDtos : " + postDtos);

            model.addAttribute("postDtos", postDtos);

        }

        model.addAttribute("postLength", postDtos != null ? postDtos.size() : 0);

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            model.addAttribute("email", username);
            try {
                model.addAttribute("user_nickname", userService.findByEmail(username).getNickname());
                log.info("[PostController][viewPost] nickname : " + userService.findByEmail(username).getNickname());

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return "member/index";
    }

}
