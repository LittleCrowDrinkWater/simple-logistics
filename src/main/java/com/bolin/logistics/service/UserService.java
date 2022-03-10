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
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User checkUser(String token) {
        if (token == null || "".equals(token)) {
            throw new CustomizeException(CustomizeErrorCodeImpl.NO_LOGIN);
        }
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
        return users.get(0);
    }

    @Transactional
    public CustomResponse addUser(String token, User user) {
        try {
            User checkedUser = checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            userMapper.insert(user);
            return CustomResponse.addSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.addFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateUserByAdmin(String token, User user) {
        try {
            User checkedUser = checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            if (!StringUtils.isEmpty(user.getPassword()))
                user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            user.setGmtModified(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(user.getId());
            userMapper.updateByExampleSelective(user, userExample);
            return CustomResponse.updateSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse updateUserByUser(String token, User user) {
        try {
            User checkedUser = checkUser(token);
            long id = checkedUser.getId();
            if (!StringUtils.isEmpty(user.getPassword()))
                user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            user.setGmtModified(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(id);
            userMapper.updateByExampleSelective(user, userExample);
            return CustomResponse.updateSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteUser(String token, long userid) {
        try {
            User checkedUser = checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(userid);
            userMapper.deleteByExample(userExample);
            return CustomResponse.deleteSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.deleteFailed();
        } catch (Exception e) {
            return CustomResponse.deleteFailed();
        }
    }

    public CustomResponse selectUserById(String token, long userid) {
        try {
            User checkedUser = checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(userid);
            List<User> userList = userMapper.selectByExample(userExample);
            return CustomResponse.success(userList.get(0));
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.queryFailed();
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }

    public CustomResponse login(String telOrEmailOrName, String password, HttpServletResponse response) {
        try {
            password = MD5Utils.stringToMD5(password);
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
            if (users.size() == 0) {
                throw new CustomizeException(CustomizeErrorCodeImpl.USER_NOFOUND);
            }
            User user = users.get(0);
            String token = JwtUtil.generateJwtToken(user.getId(), user.getPassword());
            user.setGmtLastLogin(System.currentTimeMillis());
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(user.getId());
            userMapper.updateByExampleSelective(user, userExample);
            response.addCookie(new Cookie("token", token));
            return CustomResponse.loginSuccess(user);
        } catch (Exception e) {
            return CustomResponse.loginFailed();
        }
    }

    public CustomResponse logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("token");
            Cookie cookie = new Cookie("token", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return CustomResponse.success();
        } catch (Exception e) {
            return CustomResponse.fail();
        }
    }

    @Transactional
    public CustomResponse register(User user) {
        try {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.setPassword(MD5Utils.stringToMD5(user.getPassword()));
            user.setTypeId(UserEnum.CUSTOMER.getType());
            userMapper.insert(user);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

//    public CustomResponse list(String token , int page , int size) {
//        try {
//            User checkedUser = checkUser(token);
//            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
//                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
//            }
//            int offset = size * (page - 1);
//            UserExample example = new UserExample();
//            example.setOrderByClause("gmt_modified desc");
//            List<User> users = userMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
//            return CustomResponse.success(users);
//        } catch (Exception e) {
//            return CustomResponse.queryFailed();
//        }
//    }
}