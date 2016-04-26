package com.ajie.wechat.service;

import com.ajie.wechat.dao.CarOwnerDao;
import com.ajie.wechat.entity.CarOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerService {
    @Autowired
    private CarOwnerDao carOwnerDao;

    public String login(String userName, String password){
        return carOwnerDao.findByUserNameAndPassword(userName, password).getUuid();
    }

    public void register(CarOwner carOwner){
        carOwnerDao.save(carOwner);
    }

    public void update(CarOwner carOwner){
        carOwnerDao.save(carOwner);
    }
}
