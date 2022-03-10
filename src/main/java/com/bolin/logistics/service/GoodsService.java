package com.bolin.logistics.service;


import com.bolin.logistics.enums.LogisticsStatusEnum;
import com.bolin.logistics.enums.TransferStatusEnum;
import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.CarMapper;
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
public class GoodsService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private PayService payService;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private TransferInfoMapper transferInfoMapper;

    @Transactional
    public CustomResponse addGoodsInfo(String token, GoodsInfo goodsInfo) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() || checkedUser.getTypeId() == UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse warehouse = warehouseMapper.selectByPrimaryKey(goodsInfo.getDeliveryWarehouse());
            goodsInfo.setGmtCreate(System.currentTimeMillis());
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setOperateUserId(warehouse.getDirectorUserId());
            goodsInfo.setGoodsBillCode(OrderNumGenUtil.genGoodsInfoNo());
            goodsInfo.setStatus(LogisticsStatusEnum.WAIT_OPERATION.getType());
            goodsInfo.setStatus(TransferStatusEnum.WAIT_OPERATION.getType());
            goodsInfoMapper.insert(goodsInfo);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deliver(String id , String token) {
        try {
            long  goodsBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setSendGoodsDate(System.currentTimeMillis());
            goodsInfo.setStatus(LogisticsStatusEnum.IN_DELEVERY.getType());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsBillId);
            goodsInfoMapper.updateByExampleSelective(goodsInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }
    @Transactional
    public CustomResponse driverAccept(String id ,String token) {
        try {
            long  goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            Car car = carMapper.selectByPrimaryKey(info.getCarId());
            User checkedUser = userService.checkUser(token);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            if (checkedUser.getId().equals(car.getUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setStatus(LogisticsStatusEnum.IN_TRANSIT.getType());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsBillId);
            goodsInfoMapper.updateByExampleSelective(goodsInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse driverArrive(String id ,String token) {
        try {
            User checkedUser = userService.checkUser(token);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long  goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            Car car = carMapper.selectByPrimaryKey(info.getCarId());
            if (checkedUser.getId().equals(car.getUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse receiveWarehouse = warehouseMapper.selectByPrimaryKey(info.getReceivingWarehouse());
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setStatus(LogisticsStatusEnum.IN_STORAGE.getType());
            goodsInfo.setOperateUserId(receiveWarehouse.getDirectorUserId());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsBillId);
            goodsInfoMapper.updateByExampleSelective(goodsInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse storage(String id ,String token) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long  goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            Car car = carMapper.selectByPrimaryKey(info.getCarId());
            if (checkedUser.getId().equals(car.getUserId())){
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setStatus(LogisticsStatusEnum.IN_WAREHOUSE.getType());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsBillId);
            goodsInfoMapper.updateByExampleSelective(goodsInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse receive(String id ,String token) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long  goodsBillId = Long.parseLong(id);
            TransferInfoExample transferInfoExample = new TransferInfoExample();
            transferInfoExample.createCriteria()
                    .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                    .andGoodsInfoIdEqualTo(goodsBillId);
            List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(transferInfoExample);
            if (transferInfos.size() > 0){
                throw new CustomizeException(CustomizeErrorCodeImpl.EXIST_TRANSFER_ORDER);
            }
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setStatus(LogisticsStatusEnum.ARCHIVE.getType());
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsBillId);
            goodsInfoMapper.updateByExampleSelective(goodsInfo, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteGoodsInfo(String token, long goodsInfoId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType()) {
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
