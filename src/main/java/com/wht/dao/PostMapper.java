package com.wht.dao;

import com.wht.entity.Post;

import java.util.List;

public interface PostMapper {
    void insertPost(Post post);

    List<Post> selectPostByUid(int uid);

    List<Post> selectDonePostByUid(int uid);

    Post selectPostByPid(int pid);

    void updatePost(Post post);

    Post selectDoingByUid(int uid);
}
