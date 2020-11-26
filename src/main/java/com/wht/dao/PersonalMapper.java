package com.wht.dao;

import com.wht.entity.Personal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface PersonalMapper {

    Personal selectUserByid(int uid);
}
