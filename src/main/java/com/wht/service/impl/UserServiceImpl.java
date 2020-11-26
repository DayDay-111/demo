package com.wht.service.impl;

import com.wht.dao.PersonalMapper;
import com.wht.entity.Personal;
import com.wht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PersonalMapper personalMapper;
    @Override
    public Personal findUserByid(int uid) {
        return personalMapper.selectUserByid(uid);
    }


}
