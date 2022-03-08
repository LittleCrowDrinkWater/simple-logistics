package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.UserMapper;
import com.bolin.logistics.model.User;
import com.bolin.logistics.model.UserExample;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.JwtUtil;
import com.bolin.logistics.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public CustomResponse addUser(String token, User user) {
        try {
            if (!JwtUtil.checkJwt(token)) {
                throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
            }
            long id = JwtUtil.getId(token);
            String password = JwtUtil.getPassword(token);
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(id)
                    .andPasswordEqualTo(password);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            if (users.get(0).getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            userMapper.insert(user);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateUserByAdmin(String token, User user) {
        try {
            if (!JwtUtil.checkJwt(token)) {
                throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
            }
            long id = JwtUtil.getId(token);
            String password = JwtUtil.getPassword(token);
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(id)
                    .andPasswordEqualTo(password);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            if (users.get(0).getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            user.setGmtModified(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(user.getId());
            userMapper.updateByExampleSelective(user, userExample);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse updateUserByUser(String token, User user) {
        try {
            if (!JwtUtil.checkJwt(token)) {
                throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
            }
            long id = JwtUtil.getId(token);
            String password = JwtUtil.getPassword(token);
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(id)
                    .andPasswordEqualTo(password);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            user.setGmtModified(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(id);
            userMapper.updateByExampleSelective(user, userExample);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteUser(String token, long userid) {
        try {
            if (!JwtUtil.checkJwt(token)) {
                throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
            }
            long id = JwtUtil.getId(token);
            String password = JwtUtil.getPassword(token);
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(id)
                    .andPasswordEqualTo(password);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            if (users.get(0).getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(userid);
            userMapper.deleteByExample(userExample);
            return CustomResponse.deleteSuccess();
        } catch (Exception e) {
            return CustomResponse.deleteFailed();
        }
    }

    public CustomResponse selectUserById(String token, long userid) {
        try {
            if (!JwtUtil.checkJwt(token)) {
                throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
            }
            long id = JwtUtil.getId(token);
            String password = JwtUtil.getPassword(token);
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(id)
                    .andPasswordEqualTo(password);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            if (users.get(0).getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(userid);
            List<User> userList = userMapper.selectByExample(userExample);
            return CustomResponse.success(userList.get(0));
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }

    public CustomResponse login(String telOrEmailOrName, String password, HttpServletResponse response) {
        try {
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            UserExample.Criteria criteria1 = example.createCriteria();
            UserExample.Criteria criteria2 = example.createCriteria();
            criteria.andTelEqualTo(telOrEmailOrName)
                    .andPasswordEqualTo(password);
            criteria1.andEmailEqualTo(telOrEmailOrName)
                    .andPasswordEqualTo(password);
            criteria2.andNameEqualTo(telOrEmailOrName)
                    .andPasswordEqualTo(password);
            example.or(criteria);
            example.or(criteria1);
            example.or(criteria2);
            List<User> users = userMapper.selectByExample(example);
            if (users.size() == 0){
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            User user = users.get(0);

            String token = JwtUtil.generateJwtToken(user.getId(), user.getPassword());
            response.addCookie(new Cookie("token", token));
            return CustomResponse.success();
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }

    public CustomResponse logout(HttpServletResponse response) {
        try {
            response.addCookie(new Cookie("token", ""));
            return CustomResponse.success();
        } catch (Exception e) {
            return CustomResponse.fail();
        }
    }

}