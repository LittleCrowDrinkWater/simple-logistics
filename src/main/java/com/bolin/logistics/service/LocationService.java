package com.bolin.logistics.service;

import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.LocationMapper;
import com.bolin.logistics.model.Location;
import com.bolin.logistics.model.LocationExample;
import com.bolin.logistics.model.User;
import com.bolin.logistics.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addLocation(String token, Location location) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            location.setGmtCreate(System.currentTimeMillis());
            location.setGmtModified(System.currentTimeMillis());
            locationMapper.insert(location);
            return CustomResponse.addSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.addFailed();
        }
    }

    @Transactional
    public CustomResponse updateLocation(String token, Location location) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            location.setGmtModified(System.currentTimeMillis());
            LocationExample example = new LocationExample();
            example.createCriteria()
                    .andIdEqualTo(location.getId());
            locationMapper.updateByExampleSelective(location, example);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteLocation(String token, int locationId) {
        try {
            User checkedUser = userService.checkUser(token);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            LocationExample locationExample = new LocationExample();
            locationExample.createCriteria()
                    .andIdEqualTo(locationId);
            locationMapper.deleteByExample(locationExample);
            return CustomResponse.updateSuccess();
        } catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }


}
