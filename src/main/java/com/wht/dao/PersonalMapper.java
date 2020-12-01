package com.wht.dao;

import com.wht.entity.Personal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface PersonalMapper {

    Personal selectUserByid(int uid);
    
    Personal selectUserByName(String name);

    boolean signUp(Personal person);

    int maxId();

    String logInPW(String username);

    void updateInfo(Personal personal);
    
}
