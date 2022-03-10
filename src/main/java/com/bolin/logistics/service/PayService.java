package com.bolin.logistics.service;

import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.PayMapper;
import com.bolin.logistics.model.Pay;
import com.bolin.logistics.model.PayExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.OrderNumGenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayService {
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addPay(String token, Pay pay) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            pay.setGmtCreate(System.currentTimeMillis());
            pay.setGmtModified(System.currentTimeMillis());
            pay.setPaymentNo(OrderNumGenUtil.genPayNo());
            payMapper.insert(pay);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updatePay(String token, Pay pay) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            pay.setGmtModified(System.currentTimeMillis());
            PayExample example = new PayExample();
            example.createCriteria()
                    .andIdEqualTo(pay.getId());
            payMapper.updateByExampleSelective(pay , example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deletePay(String token, long payId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            PayExample example = new PayExample();
            example.createCriteria()
                    .andIdEqualTo(payId);
            payMapper.deleteByExample(example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }
}
