package com.ajie.wechat.dao;

import com.ajie.wechat.entity.CarOwner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnerDao extends CrudRepository<CarOwner,Integer>{
    CarOwner findByUserNameAndPassword(String userName, String password);
}
