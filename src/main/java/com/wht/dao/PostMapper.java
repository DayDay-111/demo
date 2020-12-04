package com.wht.dao;

import com.wht.entity.Post;
import com.wht.entity.ReturnPost;

import java.util.List;

public interface PostMapper {
    void insertPost(Post post);

    List<Post> selectPostByUid(int uid);

    List<Post> selectDonePostByUid(int uid);

    Post selectPostByPid(int pid);

    void updatePost(Post post);

    Post selectDoingByUid(int uid);

    List<ReturnPost> selectAllPostByUid(int uid);

    List<Post> selectAllNotTakenPosts();

    List<Post> selectAllCompletedAndDoingPosts(int uid);
}
