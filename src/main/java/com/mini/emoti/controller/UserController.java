package com.mini.emoti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mini.emoti.model.dto.PostDto;
import com.mini.emoti.service.PostService;
import com.mini.emoti.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("user/")
public class UserController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    /*
     * 로그인한 경우만
     */

    @GetMapping("mypage")
    public String mypage(Authentication authentication, Model model) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "member/mypage";
    }

    @GetMapping("graphs")
    public String graph(Model model) {
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // model.addAttribute("username", userDetails.getUsername());
        return "member/graphs";
    }

    @GetMapping("/index")
    public String viewPost(Model model, Authentication authentication) throws Exception {
        log.info("[PostController][viewPost] start");
        if (authentication == null) {
            return "redirect:/index";
        }
        List<PostDto> postDtos;
        try {
            postDtos = postService.viewAllPost();

            if (!postDtos.isEmpty()) {
                log.info("[PostController][viewPost] postDtos : " + postDtos);

                model.addAttribute("postDtos", postDtos);

            }

            model.addAttribute("postLength", postDtos != null ? postDtos.size() : 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            model.addAttribute("postDtos", null);
            model.addAttribute("postLength", null);

            e.printStackTrace();
        }
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            model.addAttribute("email", username);
            try {
                model.addAttribute("user_nickname",
                        userService.findByEmail(username).getNickname());
                log.info("[PostController][viewPost] nickname : " +
                        userService.findByEmail(username).getNickname());

            } catch (Exception e) {
                // TODO Auto-generatzed catch block
                e.printStackTrace();
            }

        }
        return "member/index";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("[UserController][logout] Start");

        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("userRole");
        return "redirect:/index";
    }


    // 인증 
    @GetMapping("/aouthPage")
    public String user(HttpServletRequest request, Model model) {
        log.info("[UserController][user] Start");

        HttpSession session = request.getSession();

        // 인증 확인!! -> 로그인 유무 확인 
        if(session.getAttribute("userId") == null) {
            return "redirect:/loginPage";
        }

        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        return "user";
    }

    // 인가 
    @GetMapping("/adminPage")
    public String admin(HttpServletRequest request, Model model) {
        log.info("[UserController][admin] Start");

        HttpSession session = request.getSession();

        // 인증 확인!! -> 로그인 유무 확인 
        if(session.getAttribute("userId") == null) {
            return "redirect:/loginPage";
        } 
        // 인가 확인!! -> 관리자 유무 확인 
        else if(!session.getAttribute("userRole").equals("admin")) {
            return "redirect:/aouthPage";
        }

        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        return "admin";
    }

    // // 게시글 가져오기
    // public @ResponseBody ResponseEntity<List<PostDto>> getAllPost() throws
    // Exception {
    // return ResponseEntity.ok(postService.viewAllPost());

    // }

    // @GetMapping("index")
    // public String viewPost(Model model, Authentication authentication) throws
    // Exception {
    // log.info("[PostController][viewPost] start");
    // if (authentication == null) {
    // return "redirect:/index";
    // }

    // ResponseEntity<List<PostDto>> responseEntity = getAllPost();
    // List<PostDto> postDtos = responseEntity.getBody();

    // if (!postDtos.isEmpty()) {
    // log.info("[PostController][viewPost] postDtos : " + postDtos);
    // model.addAttribute("postDtos", postDtos);

    // }

    // model.addAttribute("postLength", postDtos != null ? postDtos.size() : 0);

    // if (authentication != null) {
    // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    // String username = userDetails.getUsername();

    // model.addAttribute("email", username);
    // try {
    // model.addAttribute("user_nickname",
    // userService.findByEmail(username).getNickname());
    // log.info("[PostController][viewPost] nickname : " +
    // userService.findByEmail(username).getNickname());

    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

    // }
    // return "member/index";
    // }

}
