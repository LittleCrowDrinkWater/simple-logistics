package com.bolin.logistics.service;


import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.CarMapper;
import com.bolin.logistics.model.Car;
import com.bolin.logistics.model.CarExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addCar(String token, Car car) {
        try {
            User checkedUser = userService.checkUser(token);
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
    public CustomResponse updateCar(String token, Car car) {
        try {
            User checkedUser = userService.checkUser(token);
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
    public CustomResponse deleteCar(String token, long carId) {
        try {
            User checkedUser = userService.checkUser(token);
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

}
