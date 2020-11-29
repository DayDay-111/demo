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
    
    
    @Override
    public Personal findUserByName(String name) {
        return personalMapper.selectUserByName(name);
    }

    @Override
    public boolean signUp(Personal person){
        try {
            personalMapper.signUp(person);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public int maxId(){
        return personalMapper.maxId();
    }

    @Override
    public String logInPW(String username) {
        return personalMapper.logInPW(username);
    }




}
