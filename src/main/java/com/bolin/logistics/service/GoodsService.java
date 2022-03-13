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
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
    private CarMapper carMapper;
    @Autowired
    private TransferInfoMapper transferInfoMapper;

    @Transactional
    public CustomResponse addGoodsInfo(HttpServletRequest request, GoodsInfo goodsInfo) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            Warehouse warehouse = warehouseMapper.selectByPrimaryKey(goodsInfo.getDeliveryWarehouse());
            goodsInfo.setGmtCreate(System.currentTimeMillis());
            goodsInfo.setGmtModified(System.currentTimeMillis());
            goodsInfo.setOperateUserId(warehouse.getDirectorUserId());
            goodsInfo.setGoodsBillCode(OrderNumGenUtil.genGoodsInfoNo());
            goodsInfo.setStatus(LogisticsStatusEnum.WAIT_OPERATION.getType());
            goodsInfoMapper.insert(goodsInfo);
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
    public CustomResponse deliver(String id, HttpServletRequest request) {
        try {
            long goodsBillId = Long.parseLong(id);
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId()) && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
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
            long goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            Car car = carMapper.selectByPrimaryKey(info.getCarId());
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            if (!checkedUser.getId().equals(car.getUserId()) && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
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
            User checkedUser = userService.checkUser(request);
            if (!(checkedUser.getTypeId() == UserEnum.DRIVER.getType()) && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            Car car = carMapper.selectByPrimaryKey(info.getCarId());
            if (!checkedUser.getId().equals(car.getUserId()) && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
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

            car.setWarehouseId(receiveWarehouse.getId());
            CarExample carExample = new CarExample();
            carExample.createCriteria()
                    .andIdEqualTo(car.getId());
            carMapper.updateByExampleSelective(car, carExample);
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
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.OPERATOR.getType() && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long goodsBillId = Long.parseLong(id);
            GoodsInfo info = goodsInfoMapper.selectByPrimaryKey(goodsBillId);
            if (!checkedUser.getId().equals(info.getOperateUserId()) && checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
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
    public CustomResponse receive(String id, HttpServletRequest request) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            long goodsBillId = Long.parseLong(id);
            TransferInfoExample transferInfoExample = new TransferInfoExample();
            transferInfoExample.createCriteria()
                    .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                    .andGoodsInfoIdEqualTo(goodsBillId);
            List<TransferInfo> transferInfos = transferInfoMapper.selectByExample(transferInfoExample);
            if (transferInfos.size() > 0) {
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
    public CustomResponse deleteGoodsInfo(HttpServletRequest request, long goodsInfoId) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() && checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            GoodsInfoExample example = new GoodsInfoExample();
            example.createCriteria()
                    .andIdEqualTo(goodsInfoId);
            goodsInfoMapper.deleteByExample(example);
            return CustomResponse.addSuccess();
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
                GoodsInfoExample example = new GoodsInfoExample();
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page, size);
                List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(example);
                return CustomResponse.success(goodsInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.OPERATOR.getType()) {
                GoodsInfoExample example = new GoodsInfoExample();
                example.createCriteria()
                        .andOperateUserIdEqualTo(checkedUser.getId())
                        .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                        .andStatusNotEqualTo(LogisticsStatusEnum.IN_TRANSIT.getType());
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page, size);
                List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(example);
                return CustomResponse.success(goodsInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.DRIVER.getType()) {
                CarExample carExample = new CarExample();
                carExample.createCriteria()
                        .andUserIdEqualTo(checkedUser.getId());
                List<Car> cars = carMapper.selectByExample(carExample);

                GoodsInfoExample example = new GoodsInfoExample();
                example.createCriteria()
                        .andCarIdEqualTo(cars.get(0).getId())
                        .andStatusNotEqualTo(LogisticsStatusEnum.ARCHIVE.getType())
                        .andStatusNotEqualTo(LogisticsStatusEnum.IN_WAREHOUSE.getType());
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page, size);
                List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(example);
                return CustomResponse.success(goodsInfos);
            }
            if (checkedUser.getTypeId() == UserEnum.CUSTOMER.getType()) {
                GoodsInfoExample example = new GoodsInfoExample();
                GoodsInfoExample.Criteria criteria1 = example.createCriteria();
                GoodsInfoExample.Criteria criteria2 = example.createCriteria();
                criteria1.andSendGoodsUserIdEqualTo(checkedUser.getId());
                criteria2.andReceiveUserIdEqualTo(checkedUser.getId());
                example.or(criteria1);
                example.or(criteria2);
                example.setOrderByClause("gmt_modified");
                PageHelper.startPage(page, size);
                List<GoodsInfo> goodsInfos = goodsInfoMapper.selectByExample(example);
                return CustomResponse.success(goodsInfos);
            }
            return CustomResponse.queryFailed();
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }
}
