package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.dao.MytableDao;
import com.hp.pojo.Mytable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/23 14:22
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MytableDao mytableDao;

    @GetMapping("")
    public ResponseEntity menu(){
        QueryWrapper<Mytable> wrapper = new QueryWrapper();
        List<Mytable> mytables = mytableDao.selectList(wrapper);
        return new ResponseEntity(mytables, HttpStatus.OK);
    }
}
