package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCode;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.UserMapper;
import com.bolin.logistics.model.User;
import com.bolin.logistics.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void addUser(String token , User user){
        UserExample example = new UserExample();
        example.createCriteria()
                .andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(example);
        if (users.get(0).getTypeId() != UserEnum.ADMIN.getType()){
            throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
        }
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(System.currentTimeMillis());
        userMapper.insert(user);
    }
}
