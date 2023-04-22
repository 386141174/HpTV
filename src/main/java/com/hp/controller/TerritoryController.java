package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dao.TerritoryDao;
import com.hp.pojo.Territory;
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
 * @Date 2023/2/25 13:12
 **/
@RestController
@RequestMapping("/territory")
public class TerritoryController {

    @Autowired
    private TerritoryDao territoryDao;

    @GetMapping("/getTerritory")
    private ResponseEntity<?> getTerritory(@RequestParam("pageNo") Integer pageNo,
                                           @RequestParam("pageSize") Integer pageSize,
                                           @RequestParam("search") String search){
        Page<Territory> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        Page<Territory> page1 = territoryDao.pageSearch(page, search);
        return new ResponseEntity<>(page1, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTerritory(@RequestBody Territory territory){
        int count = territoryDao.selectAreaNameCount(territory.getAreaName());
        if (count != 0){
            return new ResponseEntity<>("当前区域已存在！",HttpStatus.OK);
        }
        if (territory.getId() != null) {
            int update = territoryDao.updateById(territory);
            return new ResponseEntity<>(update,HttpStatus.OK);
        }
        int insert = territoryDao.insert(territory);
        return new ResponseEntity<>(insert,HttpStatus.OK);
    }

    @GetMapping("/delete")
    private ResponseEntity<?> getDelete(@RequestParam("id") Integer id){
        int delete = territoryDao.deleteById(id);
        return new ResponseEntity<>(delete, HttpStatus.OK);
    }

    @GetMapping("/territoryInfo")
    private ResponseEntity<?> getTerritoryInfo(@RequestParam("id") Integer id){
        Territory territory = territoryDao.selectById(id);
        return new ResponseEntity<>(territory, HttpStatus.OK);
    }

    @GetMapping("allTerritory")
    private ResponseEntity<?> allTerritory(){
        List<Territory> territories = territoryDao.selectList(new QueryWrapper<>());
        return new ResponseEntity<>(territories,HttpStatus.OK);
    }

}
