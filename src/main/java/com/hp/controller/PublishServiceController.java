package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dao.PublishServiceDao;
import com.hp.pojo.PublishService;
import com.hp.pojo.PublishServiceVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/23 16:43
 **/
@RestController
@RequestMapping("/publishService")
public class PublishServiceController {

    private static final Logger log = LoggerFactory.getLogger(PublishServiceController.class);

    @Autowired
    private PublishServiceDao publishServiceDao;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("userName") String userName,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("area") Integer area,
                                  @RequestParam("floorUnit") String floorUnit,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime,
                                  @RequestParam("servicePersonnelId") String servicePersonnelId,
//                                  @RequestParam("file")MultipartFile file,
                                  @RequestParam("serviceContent") String serviceContent,
                                  @RequestParam("serviceValue") Integer serviceValue,
                                  @RequestParam("userId") String userId){

        PublishService publishService = PublishService.builder()
                .userName(userName)
                .userId(userId)
                .area(area)
                .serviceValue(serviceValue)
                .phone(phone)
                .state(1)
                .serviceContent(serviceContent)
                .floorUnit(floorUnit)
                .startTime(startTime)
                .endTime(endTime)
                .servicePersonnelId(servicePersonnelId)
                .build();
        String uuid = UUID.randomUUID().toString().replace("-", "");
//        if (file != null) {
//            String upload = upload(file);
//            if (StringUtils.isBlank(upload)){
//                return new ResponseEntity("图片上传失败", HttpStatus.BAD_REQUEST);
//            }
//            publishService.setImage(upload);
//        }
        publishService.setServiceNumber(uuid);
        int insert = publishServiceDao.insert(publishService);
        return new ResponseEntity<>(insert,HttpStatus.OK);

    }





    @GetMapping("serviceQuery")
    public ResponseEntity<?> serviceQuery(@RequestParam("pageNo") Integer pageNo,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("search") String search,
                                          @RequestParam(value = "userName",required = false) String userName,
                                          @RequestParam(value = "serviceName",required = false) String serviceName){
        Page<PublishService> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        if (StringUtils.equals(userName,"admin")){
            userName = null;
        }
        Page<PublishServiceVo> page1 = publishServiceDao.pageSearch(page, search,userName,serviceName);
        return new ResponseEntity<>(page1,HttpStatus.OK);
    }


    @GetMapping("fillOrder")
    public ResponseEntity<?> fillOrder(@RequestParam("state") Integer state,
                                       @RequestParam("id") Integer id){
        PublishService publishService = publishServiceDao.selectById(id);
        publishService.setState(state);
        int update = publishServiceDao.updateById(publishService);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }



}
