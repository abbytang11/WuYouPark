package com.ajie.wechat.controller;

import com.ajie.wechat.entity.CarOwner;
import com.ajie.wechat.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/carOwner")
public class CarOwnerController {
    @Autowired
    private CarOwnerService carOwnerService;

    @RequestMapping(value = "/login.htm",method = RequestMethod.POST)
    public String login(String userName, String password){
        carOwnerService.login(userName, password);
        return "index";
    }
    @RequestMapping(value = "/register.htm",method = RequestMethod.POST)
    public String register(CarOwner carOwner){
        carOwnerService.register(carOwner);
        return "index";
    }
    @RequestMapping(value = "/update.htm",method = RequestMethod.POST)
    public String update(CarOwner carOwner){

        carOwnerService.register(carOwner);
        return "index";
    }
}
