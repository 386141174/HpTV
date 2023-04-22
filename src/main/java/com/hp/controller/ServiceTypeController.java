package com.hp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dao.ServiceTypeDao;
import com.hp.pojo.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/25 18:02
 **/
@RestController
@RequestMapping("/serviceType")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeDao serviceTypeDao;

    @GetMapping("/getServiceType")
    public ResponseEntity<?> getServiceType(@RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("serviceName") String serviceName){
        Page<ServiceType> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        Page<ServiceType> serviceTypePage = serviceTypeDao.pageSearch(page, serviceName);
        return new ResponseEntity<>(serviceTypePage, HttpStatus.OK);

    }

    @PostMapping("saveServiceType")
    public ResponseEntity<?> saveServiceType(@RequestBody ServiceType serviceType){
        if (serviceType.getId() != null){
            int update = serviceTypeDao.updateById(serviceType);
            return new ResponseEntity<>(update,HttpStatus.OK);
        }
        int insert = serviceTypeDao.insert(serviceType);
        return new ResponseEntity<>(insert,HttpStatus.OK);

    }

    @GetMapping("/serviceTypeInfo")
    public ResponseEntity<?> serviceTypeInfo(@RequestParam("id") Integer id){
        ServiceType serviceType = serviceTypeDao.selectById(id);
        return new ResponseEntity<>(serviceType,HttpStatus.OK);
    }

    @GetMapping("/deleteServiceType")
    public ResponseEntity<?> deleteServiceType(@RequestParam("id") Integer id){
        int delete = serviceTypeDao.deleteById(id);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }

}
