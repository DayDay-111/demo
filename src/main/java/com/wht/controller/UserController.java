package com.wht.controller;

import com.wht.dao.PersonalMapper;
import com.wht.entity.Personal;
import com.wht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "hello word";
    }

    @PostMapping("/userInfoById")
    public Personal find(int i){
        return userService.findUserByid(i);
    }

}
