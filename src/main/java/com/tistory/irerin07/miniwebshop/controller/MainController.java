package com.tistory.irerin07.miniwebshop.controller;

import com.tistory.irerin07.miniwebshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserService userService;

    public String main(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "searchKind", required = false) String searchKind,
            @RequestParam(name = "searchStr", required = false) String searchStr,
            Model model){

//        Page<Post> pagePost = postService.getPosts(page, searchKind, searchStr);
//        model.addAttribute("pagePost", pagePost);
        return "index";
    }
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/main")
//    public String main(Model model){
//        List<User> list = userService.getUserAll();
//        model.addAttribute("list", list);
//        return "index";
//    }
//
}