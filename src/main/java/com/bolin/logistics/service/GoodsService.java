package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.GoodsInfoMapper;
import com.bolin.logistics.mapper.PayMapper;
import com.bolin.logistics.model.Car;
import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.model.GoodsInfoExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addGoodsInfo(String token, GoodsInfo goodsInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            goodsInfo.setGmtCreate(System.currentTimeMillis());
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfoMapper.insert(goodsInfo);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateGoodsInfo(String token, GoodsInfo goodsInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            goodsInfo.setGmtModified(System.currentTimeMillis());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsInfo.getId());
            goodsInfoMapper.updateByExampleSelective(goodsInfo , example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deleteGoodsInfo(String token, long goodsInfoId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsInfoId);
            goodsInfoMapper.deleteByExample(example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }
}
