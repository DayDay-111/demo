package com.wht.service;

import com.wht.entity.Personal;
import org.springframework.stereotype.Service;

public interface UserService {
    Personal findUserByid(int uid);
    
    Personal findUserByName(String name);

    boolean signUp(Personal person);

    int maxId();

    String logInPW(String username);


}
