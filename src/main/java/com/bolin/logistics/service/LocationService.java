package com.bolin.logistics.service;

import com.bolin.logistics.enums.UserEnum;
import com.bolin.logistics.exception.CustomizeErrorCodeImpl;
import com.bolin.logistics.exception.CustomizeException;
import com.bolin.logistics.mapper.LocationMapper;
import com.bolin.logistics.model.*;
import com.bolin.logistics.utils.CustomResponse;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public CustomResponse addLocation(HttpServletRequest request, Location location) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            location.setGmtCreate(System.currentTimeMillis());
            location.setGmtModified(System.currentTimeMillis());
            locationMapper.insert(location);
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
    public CustomResponse updateLocation(HttpServletRequest request, Location location) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            location.setGmtModified(System.currentTimeMillis());
            LocationExample example = new LocationExample();
            example.createCriteria()
                    .andIdEqualTo(location.getId());
            locationMapper.updateByExampleSelective(location, example);
            return CustomResponse.updateSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.updateFailed();
        }catch (Exception e) {
            return CustomResponse.updateFailed();
        }
    }

    @Transactional
    public CustomResponse deleteLocation(HttpServletRequest request, int locationId) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            LocationExample locationExample = new LocationExample();
            locationExample.createCriteria()
                    .andIdEqualTo(locationId);
            locationMapper.deleteByExample(locationExample);
            return CustomResponse.updateSuccess();
        } catch (CustomizeException e) {
            if (e.getCode() == CustomizeErrorCodeImpl.NO_LOGIN.getCode())
                return CustomResponse.loginFailed();
            else
                return CustomResponse.deleteFailed();
        }catch (Exception e) {
            return CustomResponse.deleteFailed();
        }
    }

    public CustomResponse list(int page, int size, HttpServletRequest request) {
        try {
            User checkedUser = userService.checkUser(request);
            if (checkedUser.getTypeId() != UserEnum.ADMIN.getType()) {
                throw new CustomizeException(CustomizeErrorCodeImpl.AUTHORIZE_FAIL);
            }
            LocationExample example = new LocationExample();
            example.createCriteria();
            example.setOrderByClause("gmt_modified");
            PageHelper.startPage(page, size);
            List<Location> locations = locationMapper.selectByExample(example);
            return CustomResponse.success(locations);
        } catch (Exception e) {
            return CustomResponse.queryFailed();
        }
    }


}
