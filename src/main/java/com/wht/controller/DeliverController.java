package com.wht.controller;

import com.wht.dao.PostMapper;
import com.wht.entity.Personal;
import com.wht.entity.Post;
import com.wht.entity.ReturnPost;
import com.wht.service.PostService;
import com.wht.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class DeliverController {
    @Autowired
    private PostService postService;

    @ApiOperation(value="Get all not taken posts")
    @RequestMapping(value="/deliver/getAllNotTakenPosts/",method= RequestMethod.GET)
    public List<Post> getAllNotTakenPosts(){
        return postService.AllNotTakenPosts();
    }

    @ApiOperation(value="get all completed and doing posts of the deliver, status = 1 or 2 means doing, status = 3 means done")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/deliver/getAllCompletedAndDoingPosts/{userid}",method=RequestMethod.GET)
    public List<Post> getAllCompletedAndDoingPosts(@PathVariable("userid") Integer userid){
        return postService.AllCompletedAndDoingPosts(userid);
    }

}
