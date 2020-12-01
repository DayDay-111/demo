package com.wht.controller;

import com.wht.dao.PostMapper;
import com.wht.entity.Post;
import com.wht.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @ApiOperation(value="create new post by userId",notes = "createtime,comment不用传，")
    //@ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/post/",method= RequestMethod.POST)
    public void find(@RequestBody Post post){
        postService.createPost(post);
        System.out.println(post.getComment());
        return ;
    }

    @ApiOperation(value="get all posts by userId")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/user/{userid}",method=RequestMethod.GET)
    public List<Post> find(@PathVariable("userid") Integer userid){
        System.out.println(userid);
        return postService.AllPostByUid(userid);
    }

    @ApiOperation(value="get all completed posts by userId")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/donepost/{userid}",method=RequestMethod.GET)
    public List<Post> findDonePost(@PathVariable("userid") Integer userid){
        System.out.println(userid);
        return postService.DonePostByUid(userid);
    }

    @ApiOperation(value="get post by postId")
    @ApiImplicitParam(name="postid",value="订单ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/post/{postid}",method=RequestMethod.GET)
    public Post PostBypid(@PathVariable("postid") Integer postid){
        System.out.println(postid);
        return postService.findPostByPid(postid);
    }

    @ApiOperation(value = "update a post by postid")
    @ApiImplicitParams({
            @ApiImplicitParam(name="postid",value="订单ID",required = true,dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name="post",value="用户实体",required = true,dataType = "Post")
    })
    @RequestMapping(value="/posts/post/{postid}",method=RequestMethod.PUT)
    public Post UpdatePostBypid( @PathVariable("postid") Integer postid, @RequestBody Post post){
        post.setPid(postid);
        postService.updatePostBypid(post);
        return post;
    }


}
