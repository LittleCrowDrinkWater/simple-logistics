package com.bolin.logistics.service;


import com.bolin.logistics.enums.LogisticsStatusEnum;
import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.CarMapper;
import com.bolin.logistics.mapper.TransferInfoMapper;
import com.bolin.logistics.mapper.WarehouseMapper;
import com.bolin.logistics.model.*;
import com.bolin.logistics.utils.CustomResponse;
import com.bolin.logistics.utils.OrderNumGenUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TransferService {
    @Autowired
    private TransferInfoMapper transferInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private CarMapper carMapper;

    @Transactional
    public CustomResponse addTransferInfo(HttpServletRequest request, TransferInfo transferInfo) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.OPERATOR.getType() && checkedUser.getTypeId() == UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse warehouse = warehouseMapper.selectByPrimaryKey(transferInfo.getDeliveryWarehouse());
            transferInfo.setGmtCreate(System.currentTimeMillis());
            transferInfo.setGmtModified(System.currentTimeMillis());
            transferInfo.setOperateUserId(warehouse.getDirectorUserId());
            transferInfo.setGoodsBillCode(OrderNumGenUtil.genTransferInfoNo());
            transferInfoMapper.insert(transferInfo);
            return CustomResponse.addSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse deliver(String id, HttpServletRequest request) {
        try {
            long transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())) {
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
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse driverAccept(String id, HttpServletRequest request) {
        try {
            long transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(request);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())) {
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
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse driverArrive(String id, HttpServletRequest request) {
        try {
            long transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(request);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType())) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())) {
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
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse storage(String id, HttpServletRequest request) {
        try {
            long transferBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfo info = transferInfoMapper.selectByPrimaryKey(transferBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId())) {
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
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteTransferInfo(HttpServletRequest request, long transferInfoId) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            TransferInfoExample example = new TransferInfoExample();
            example.createCriteria()
                    .andIdEqualTo(transferInfoId);
            transferInfoMapper.deleteByExample(example);
            return CustomResponse.deleteFailed();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.deleteFailed();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.deleteFailed();
        }
    }

    public CustomResponse list(int page, int size, HttpServletRequest request) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.ADMIN.getType()) {
                TransferInfoExample example = new TransferInfoExample();
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page , size);
                List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(example);
                return CustomResponse.success(transferInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.OPERATOR.getType()) {
                TransferInfoExample example = new TransferInfoExample();
                example.createCriteria()
                        .andOperateUserIdEqualTo(checkedUser.getId())
                        .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                        .andStatusNotEqualTo(LogisticsStatusEnum.IN_TRANSIT.getType());
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page , size);
                List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(example);
                return CustomResponse.success(transferInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType()) {
                CarExample carExample = new CarExample();
                carExample.createCriteria()
                        .andUserIdEqualTo(checkedUser.getId());
                List<Car> cars = carMapper.selectByExample(carExample);

                TransferInfoExample example = new TransferInfoExample();
                example.createCriteria()
                        .andCarIdEqualTo(cars.get(0).getId())
                        .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                        .andStatusNotEqualTo(LogisticsStatusEnum.IN_WAREHOUSE.getType());
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page , size);
                List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(example);
                return CustomResponse.success(transferInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                TransferInfoExample example = new TransferInfoExample();
                TransferInfoExample.Criteria criteria1 = example.createCriteria();
                TransferInfoExample.Criteria criteria2 = example.createCriteria();
                criteria1.andSendGoodsUserIdEqualTo(checkedUser.getId());
                criteria2.andReceiveUserIdEqualTo(checkedUser.getId());
                example.or(criteria1);
                example.or(criteria2);
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page , size);
                List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(example);
                return CustomResponse.success(transferInfos);
            }
            return CustomResponse.queryFailed();
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }
}
