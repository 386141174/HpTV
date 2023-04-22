package com.hp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dao.ServiceContentDao;
import com.hp.pojo.ServiceContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 13:14
 **/
@RestController
@RequestMapping("/serviceContent")
public class ServiceContentController {

    @Autowired
    private ServiceContentDao serviceContentDao;

    @PostMapping("saveContent")
    private ResponseEntity<?> saveContent(@RequestBody ServiceContent serviceContent) {
        if (serviceContent.getId() == null) {
            int insert = serviceContentDao.insert(serviceContent);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        }
        int update = serviceContentDao.updateById(serviceContent);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @GetMapping("getServiceContent")
    public ResponseEntity<?> getServiceContent(@RequestParam("pageNo") Integer pageNo,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("search") String search) {
        Page<ServiceContent> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        Page<ServiceContent> serviceContents = serviceContentDao.pageSearch(page, search);
        return new ResponseEntity<>(serviceContents,HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteServiceContent(@RequestParam("id") Integer id) {
        int delete = serviceContentDao.deleteById(id);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }

    @GetMapping("/serviceByArea")
    public ResponseEntity<?> serviceByArea(@RequestParam("area") Integer id) {
        List<ServiceContent> serviceContents = serviceContentDao.serviceByArea(id);
        return new ResponseEntity<>(serviceContents,HttpStatus.OK);
    }

}
