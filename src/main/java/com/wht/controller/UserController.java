package com.wht.controller;

import com.wht.dao.PersonalMapper;
import com.wht.entity.Personal;
import com.wht.service.UserService;
import io.swagger.annotations.*;
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

    @GetMapping("/test1/{id}")
    public Integer test1(@ApiParam("测试") @PathVariable(value="id") Integer uid){

        System.out.println(uid);
        return uid;
    }

    @ApiOperation(value="操作值域",notes="操作note")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户ID",required = true,dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name="personal",value="用户实体",required = true,dataType = "Personal")
    })
    @RequestMapping(value="/test/test1/{id}",method =RequestMethod.POST)
    public Integer test2( @PathVariable("id") Integer uid,@RequestBody Personal personal){

        System.out.println(uid);
        return uid;
    }

    @ApiOperation(value="get user by userId")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/users/user/{userid}",method=RequestMethod.GET)
    public Personal find(@PathVariable("userid") Integer userid){
        System.out.println(userid);
        return userService.findUserByid(userid);
    }



}
