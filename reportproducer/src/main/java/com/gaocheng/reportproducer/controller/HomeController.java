package com.gaocheng.reportproducer.controller;

import com.gaocheng.reportproducer.domain.UserInfo;
import com.gaocheng.reportproducer.domain.WghStationDataHistroy;
import com.gaocheng.reportproducer.mapper.UserInfoMapper;
import com.gaocheng.reportproducer.service.UserInfoService;
import com.gaocheng.reportproducer.service.WghStationDataHistroyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class HomeController {
    @Autowired
    private UserInfoService userInfoMapper;
    @Autowired
    private WghStationDataHistroyService wghStationDataHistroyService;
    @RequestMapping("/")
    public Object index(){
        return  userInfoMapper.selectAll();
    }
    @RequestMapping("/user/{id}")
    public UserInfo selectByName(@PathVariable("id") String id){
        UserInfo userInfo=  userInfoMapper.selectByName(id);
        return  userInfo;
    }
    @RequestMapping("/insert")
    public boolean insertUser(){
        UserInfo userInfo=  userInfoMapper.selectAll().get(0);
        UserInfo userInfo1=new UserInfo();
        userInfo1.setOrganization_id(userInfo.getOrganization_id());

        userInfo1.setState(2);
        userInfo1.setRole_id(userInfo.getRole_id());
        userInfo1.setId(UUID.randomUUID().toString().toUpperCase());
        userInfo1.setUsername("admin"+UUID.randomUUID().toString().substring(30));
        userInfo1.setPwd(UUID.randomUUID().toString());
        userInfo1.setRealname("admin"+UUID.randomUUID().toString().substring(30));
        userInfo1.setRemark("remark");
        userInfo1.setPhone("");
        userInfo1.setSex(userInfo.getSex());
        userInfo1.setOperation_id(UUID.randomUUID().toString());
        java.util.Date  date=new java.util.Date();

        java.sql.Date  data1=new java.sql.Date(date.getTime());
        userInfo1.setOperationdate(data1);
        userInfoMapper.insertUser(userInfo1);
        return  true;
    }
    @RequestMapping(value = "/data")
    public List<WghStationDataHistroy> selectHisdataByTime(String starttime, String endtime){

            return wghStationDataHistroyService.selectByTime(starttime,endtime);
    }
}
