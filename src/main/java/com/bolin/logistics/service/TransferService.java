package com.bolin.logistics.service;


import com.bolin.logistics.enums.LogisticsStatusEnum;
import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.GoodsInfoMapper;
import com.bolin.logistics.mapper.TransferInfoMapper;
import com.bolin.logistics.mapper.WarehouseMapper;
import com.bolin.logistics.model.*;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.OrderNumGenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransferService {
    @Autowired
    private TransferInfoMapper transferInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Transactional
    public CustomResponse addTransferInfo(String token, TransferInfo transferInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.OPERATOR.getType() || checkedUser.getTypeId() == UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse warehouse = warehouseMapper.selectByPrimaryKey(transferInfo.getDeliveryWarehouse());
            transferInfo.setGmtCreate(System.currentTimeMillis());
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setOperateUserId(warehouse.getDirectorUserId());
            transferInfo.setGoodsBillCode(OrderNumGenUtil.genTransferInfoNo());
            transferInfoMapper.insert(transferInfo);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deliver(String id , String token) {
        try {
            long  transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() || checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setSendGoodsDate(System.currentTimeMillis());
            transferInfo.setStatus(LogisticsStatusEnum.IN_DELEVERY.getType());

            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferBillId);
            transferInfoMapper.updateByExampleSelective(transferInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse driverAccept(String id , String token) {
        try {
            long  transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(token);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setStatus(LogisticsStatusEnum.IN_TRANSIT.getType());
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferBillId);
            transferInfoMapper.updateByExampleSelective(transferInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse driverArrive(String id , String token) {
        try {
            long  transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(token);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse receiveWarehouse = warehouseMapper.selectByPrimaryKey(info.getReceivingWarehouse());
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setStatus(LogisticsStatusEnum.IN_STORAGE.getType());
            transferInfo.setOperateUserId(receiveWarehouse.getDirectorUserId());
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferBillId);
            transferInfoMapper.updateByExampleSelective(transferInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse storage(String id , String token) {
        try {
            long  transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo transferInfo = new TransferInfo();
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setStatus(LogisticsStatusEnum.ARCHIVE.getType());
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferBillId);
            transferInfoMapper.updateByExampleSelective(transferInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteTransferInfo(String token, long transferInfoId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType()) {
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
