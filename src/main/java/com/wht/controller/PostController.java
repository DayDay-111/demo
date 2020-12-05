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
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @ApiOperation(value="create new post by userId",notes = "createtime,comment不用传，")
    //@ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/post/",method= RequestMethod.POST)
    public List<ReturnPost> find(@RequestBody Post post){
        postService.createPost(post);
        System.out.println(post.getComment());
        postService.NewPostByUid(post.getPoster());
        List<ReturnPost> list =new ArrayList();
        ReturnPost returnPost = new ReturnPost();
        BeanUtils.copyProperties(post,returnPost);
        Personal postPer = userService.findUserByid(post.getPoster());
        returnPost.setPostName(postPer.getUsername());
        returnPost.setPostPhone(postPer.getPhone());
        list.add(returnPost);
        return list;
    }

    @ApiOperation(value="get all posts by userId")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/user/{userid}",method=RequestMethod.GET)
    public List<ReturnPost> find(@PathVariable("userid") Integer userid){

        return postService.AllReturnPostByPoster(userid);
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
    public ReturnPost PostBypid(@PathVariable("postid") Integer postid){
        System.out.println(postid);
        Post post =postService.findPostByPid(postid);
        ReturnPost returnPost=new ReturnPost();
        BeanUtils.copyProperties(post,returnPost);
        System.out.println(post.getPoster());
        Personal postPer = userService.findUserByid(post.getPoster());
        Personal deliverPer = userService.findUserByid(post.getDeliver());
        returnPost.setPostName(postPer.getUsername());
        returnPost.setPostPhone(postPer.getPhone());
        returnPost.setDeliverName(deliverPer.getUsername());
        returnPost.setDeliverPhone(deliverPer.getPhone());
        return returnPost;
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
        return postService.findPostByPid(postid);
    }

    @ApiOperation(value="获取正在进行的订单", notes = "有发起的或正在配送的，返回一个对象，否则空")
    @ApiImplicitParam(name="userid",value="用户ID",required = true,dataType = "Integer",paramType = "path")
    @RequestMapping(value="/posts/Doingpost/{userid}",method=RequestMethod.GET)
    public List<ReturnPost> DoingPost(@PathVariable("userid") Integer userid){
        System.out.println(userid);
        Post post = postService.NewPostByUid(userid);
        ReturnPost returnPost=new ReturnPost();
        List<ReturnPost> list = new ArrayList();
        if(post!=null){
            BeanUtils.copyProperties(post,returnPost);
            Personal postPer = userService.findUserByid(post.getPoster());
            Personal deliverPer = userService.findUserByid(post.getDeliver());
            returnPost.setPostName(postPer.getUsername());
            returnPost.setPostPhone(postPer.getPhone());
            returnPost.setDeliverName(deliverPer.getUsername());
            returnPost.setDeliverPhone(deliverPer.getPhone());
            list.add(returnPost);
            return list;
        }else{
            return list;
        }

    }

}
