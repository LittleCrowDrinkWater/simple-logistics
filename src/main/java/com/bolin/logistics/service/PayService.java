package com.bolin.logistics.service;

import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.PayMapper;
import com.bolin.logistics.model.Pay;
import com.bolin.logistics.model.PayExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.model.UserExample;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.OrderNumGenUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PayService {
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addPay(HttpServletRequest request, Pay pay) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() && checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            pay.setGmtCreate(System.currentTimeMillis());
            pay.setGmtModified(System.currentTimeMillis());
            pay.setPaymentNo(OrderNumGenUtil.genPayNo());
            payMapper.insert(pay);
            return CustomResponse.addSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.addFailed();
        }catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updatePay( HttpServletRequest request, Pay pay) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() && checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            pay.setGmtModified(System.currentTimeMillis());
            PayExample example = new PayExample();
            example.createCriteria()
                    .andIdEqualTo(pay.getId());
            payMapper.updateByExampleSelective(pay , example);
            return CustomResponse.addSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        }catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deletePay(HttpServletRequest request, long payId) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() && checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            PayExample example = new PayExample();
            example.createCriteria()
                    .andIdEqualTo(payId);
            payMapper.deleteByExample(example);
            return CustomResponse.addSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.deleteFailed();
        }catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.deleteFailed();
        }
    }

    public CustomResponse list(int page, int size, HttpServletRequest request) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            PayExample example = new PayExample();
            example.createCriteria();
            example.setOrderByClause("gmt_modified");
            PageHelper.startPage(page, size);
            List<Pay> pays = payMapper.selectByExample(example);
            return CustomResponse.success(pays);
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }
}
