package com.wht.service;

import com.wht.entity.Post;

import java.util.List;

public interface PostService {
    void createPost(Post post);

    List<Post> AllPostByUid(int uid);

    List<Post> DonePostByUid(int uid);

    Post findPostByPid(int pid);

    void updatePostBypid(Post post);

    Post NewPostByUid(int uid);
}
