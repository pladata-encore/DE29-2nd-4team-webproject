package com.mini.emoti.controller.ViewController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
    
        
    /*
     * 누구나 접근 가능
     */

     @GetMapping("/index")
     public String index(Authentication authentication, Model model) {
        if(authentication != null){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("username", userDetails.getUsername()); 
        }

        return "index";
     }

     @GetMapping("/loginPage")
     public String loginPage(@RequestParam(value = "errorMessage", required = false)String errorMessage, Model model){
        model.addAttribute("errorMessage", errorMessage);
        
        return "login/login";

     }

     @GetMapping("/joinPage")
     public String joinPage(Model model){

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
      public String user(Authentication authentication, Model model){

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "member/index";
      }

      @GetMapping("user/mypage")
      public String mypage(Authentication authentication, Model model){

         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         model.addAttribute("username", userDetails.getUsername());
         return "member/mypage";
       }

       @GetMapping("/graph")
       public String graph(){
          // UserDetails userDetails = (UserDetails) authentication.getPrincipal();
          // model.addAttribute("username", userDetails.getUsername());
          return "member/graphs";
        }




}
