package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.CarMapper;
import com.bolin.logistics.model.*;
import com.bolin.logistics.utils.CustomResponse;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addCar(HttpServletRequest request, Car car) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            car.setGmtCreate(System.currentTimeMillis());
            car.setGmtModified(System.currentTimeMillis());
            carMapper.insert(car);
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
    public CustomResponse updateCar(HttpServletRequest request, Car car) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            car.setGmtModified(System.currentTimeMillis());
            CarExample example = new CarExample();
            example.createCriteria()
                    .andIdEqualTo(car.getId());
            carMapper.updateByExampleSelective(car , example);
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
    public CustomResponse deleteCar(HttpServletRequest request, long carId) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType() || checkedUser.getTypeId() != UserEnum.OPERATOR.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            CarExample example = new CarExample();
            example.createCriteria()
                    .andIdEqualTo(carId);
            carMapper.deleteByExample(example);
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
            CarExample example = new CarExample();
            example.createCriteria();
            example.setOrderByClause("gmt_modified");
            PageHelper.startPage(page, size);
            List<Car> cars = carMapper.selectByExample(example);
            return CustomResponse.success(cars);
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }

    public CustomResponse list(int warehouseId , int page, int size, HttpServletRequest request) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            CarExample example = new CarExample();
            example.createCriteria()
                    .andWarehouseIdEqualTo(warehouseId);
            example.setOrderByClause("gmt_modified");
            PageHelper.startPage(page, size);
            List<Car> cars = carMapper.selectByExample(example);
            return CustomResponse.success(cars);
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }
}
