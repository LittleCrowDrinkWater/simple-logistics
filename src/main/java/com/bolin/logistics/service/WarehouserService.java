package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.WarehouseMapper;
import com.bolin.logistics.model.*;
import com.bolin.logistics.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarehouserService {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addWarehouse(String token, Warehouse warehouse) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            warehouseMapper.insert(warehouse);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateLocation(String token, Warehouse warehouse) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            WarehouseExample example = new WarehouseExample();
            example.createCriteria()
                    .andIdEqualTo(warehouse.getId());
            warehouseMapper.updateByExampleSelective(warehouse, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteLocation(String token, int warehouseId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            WarehouseExample example = new WarehouseExample();
            example.createCriteria()
                    .andIdEqualTo(warehouseId);
            warehouseMapper.deleteByExample(example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }
}
