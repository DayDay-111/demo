package com.wht.controller;

import com.wht.dao.PostMapper;
import com.wht.entity.Personal;
import com.wht.entity.Post;
import com.wht.service.PostService;
import com.wht.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Post DoingPost(@PathVariable("userid") Integer userid){
        System.out.println(userid);
        return postService.NewPostByUid(userid);
    }
    public class ReturnPost{
        private int pid;
        private int poster;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getPoster() {
            return poster;
        }

        public void setPoster(int poster) {
            this.poster = poster;
        }

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getPostPhone() {
            return postPhone;
        }

        public void setPostPhone(String postPhone) {
            this.postPhone = postPhone;
        }

        public int getDeliver() {
            return deliver;
        }

        public void setDeliver(int deliver) {
            this.deliver = deliver;
        }

        public String getDeliverName() {
            return deliverName;
        }

        public void setDeliverName(String deliverName) {
            this.deliverName = deliverName;
        }

        public String getDeliverPhone() {
            return deliverPhone;
        }

        public void setDeliverPhone(String deliverPhone) {
            this.deliverPhone = deliverPhone;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDueTime() {
            return dueTime;
        }

        public void setDueTime(String dueTime) {
            this.dueTime = dueTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        private String postName;
        private String postPhone;
        private int deliver;
        private String deliverName;
        private String deliverPhone;
        private String topic;
        private String description;
        private String location;
        private int price;
        private String createTime;
        private String dueTime;
        private int status;
        private String comment;
    }
}
