package com.tistory.irerin07.miniwebshop.controller;

import com.tistory.irerin07.miniwebshop.domain.User;
import com.tistory.irerin07.miniwebshop.dto.JoinForm;
import com.tistory.irerin07.miniwebshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "fail",
        required = false,
                defaultValue = "false") String errorFlag){

            return "users/login"; // view name 을 리턴한다.
    }

    @GetMapping("/check")
    public String loginCheck(Principal principal){
        if(principal == null){
            System.out.println("로그인 실패");
        } else {
            System.out.println("로그인 성공");
        }
        return "users/login";
    }
    @GetMapping("/join")
    public String joinform(){
        return "users/joinform";
    }

    // Form데이터를 DTO로 파라미터를 받아들일 경우엔 @ModelAttribute JoinForm joinForm
    // DTO에 Validation관련 어노테이션을 사용했을 경우에는 @Valid를 사용한다.
    //
    @PostMapping("/join")
    public String join(@Valid JoinForm joinForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.toString());
        }
        if(!joinForm.getPassword1().equals(joinForm.getPassword2()))
            throw new IllegalArgumentException("암호와 암호확인이 틀려요.");

        User user = new User();
        user.setEmail(joinForm.getEmail());
        user.setName(joinForm.getName());
        user.setNickName(joinForm.getNickName());
        user.setAddress((joinForm.getAddress()));
        user.setContactNumber(joinForm.getContactNumber());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(passwordEncoder.encode(joinForm.getPassword1()));

        User result = userService.join(user);

        return "redirect:/users/login";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "users/welcome";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id")Long id){
        userService.deleteAccount(id);
        return "users/welcome";
    }
}