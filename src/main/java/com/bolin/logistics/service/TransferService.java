package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.GoodsInfoMapper;
import com.bolin.logistics.mapper.TransferInfoMapper;
import com.bolin.logistics.model.GoodsInfo;
import com.bolin.logistics.model.TransferInfo;
import com.bolin.logistics.model.TransferInfoExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {
    @Autowired
    private TransferInfoMapper transferInfoMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addTransferInfo(String token, TransferInfo transferInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            transferInfo.setGmtCreate(System.currentTimeMillis());
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfoMapper.insert(transferInfo);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateTransferInfo(String token, TransferInfo transferInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            transferInfo.setGmtModified(System.currentTimeMillis());
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferInfo.getId());
            transferInfoMapper.updateByExampleSelective(transferInfo , example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deleteTransferInfo(String token, long transferInfoId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() || checkedUser.getTypeId() == UserEnum.FINANCE_STAFF.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferInfoId);
            transferInfoMapper.deleteByExample(example);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }
}
