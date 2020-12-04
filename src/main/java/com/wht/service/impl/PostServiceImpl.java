package com.wht.service.impl;

import com.wht.dao.PostMapper;
import com.wht.entity.Post;
import com.wht.entity.ReturnPost;
import com.wht.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public void createPost(Post post) {
        Date now = new Date();
        String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
        //java.sql.Timestamp time= Timestamp.valueOf(nowTime);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR,1);
        Date due = calendar.getTime();
        String dutTime =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(due);
        post.setCreateTime(nowTime);
        post.setDueTime(dutTime);
        postMapper.insertPost(post);
    }

    @Override
    public List<Post> AllPostByUid(int uid) {
        return postMapper.selectPostByUid(uid);
    }

    @Override
    public List<Post> DonePostByUid(int uid) {
        return postMapper.selectDonePostByUid(uid);
    }

    @Override
    public Post findPostByPid(int pid) {
        return postMapper.selectPostByPid(pid);
    }

    @Override
    public void updatePostBypid(Post post) {
        postMapper.updatePost(post);
    }

    @Override
    public Post NewPostByUid(int uid) {
        return postMapper.selectDoingByUid(uid);
    }

    @Override
    public List<ReturnPost> AllReturnPostByPoster(int uid) {
        return postMapper.selectAllPostByUid(uid);
    }

    @Override
    public List<Post> AllNotTakenPosts() {
        return postMapper.selectAllNotTakenPosts();
    }

    @Override
    public List<Post> AllCompletedAndDoingPosts(int uid) {
        return postMapper.selectAllCompletedAndDoingPosts(uid);
    }
}
