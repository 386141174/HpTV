package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.hp.dao.DictionaryDao;
import com.hp.pojo.Dictionary;
import com.hp.pojo.DictionaryDto;
import org.springframework.beans.BeanUtils;
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
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/26 11:54
 **/
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryDao dictionaryDao;

    @PostMapping("saveDic")
    public ResponseEntity<?> saveDic(@RequestBody Dictionary dictionary) {
        if (dictionary.getId() != null) {
            int insert = dictionaryDao.updateById(dictionary);
            return new ResponseEntity<>(insert, HttpStatus.OK);
        }
        int insert = dictionaryDao.insert(dictionary);
        return new ResponseEntity<>(insert,HttpStatus.OK);
    }

    @GetMapping("getDic")
    public ResponseEntity<?> getDic() {
        List<DictionaryDto> allDic = dictionaryDao.getAllDic();
        List<DictionaryDto> collect = allDic.stream()
                .filter(item -> item.getParentId() == null)
                .collect(Collectors.toList());
        for (DictionaryDto dic:collect) {
            List<DictionaryDto> collect1 = allDic.stream()
                    .filter(item -> dic.getId().equals(item.getParentId()) )
                    .collect(Collectors.toList());
            dic.setChildren(collect1);
        }
        return new ResponseEntity<>(collect,HttpStatus.OK);
    }

    @GetMapping("dicInfo")
    public ResponseEntity<?> getDicInfo(@RequestParam("id") Integer id) {
        Dictionary dictionary = dictionaryDao.selectById(id);
        return new ResponseEntity<>(dictionary,HttpStatus.OK);
    }


    @GetMapping("delete")
    public ResponseEntity<?> deleteDicInfo(@RequestParam("id") Integer id) {
        int delete = dictionaryDao.deleteById(id);
        return new ResponseEntity<>(delete,HttpStatus.OK);
    }

    @GetMapping("getType")
    public ResponseEntity<?> getDicInfo(@RequestParam("serialNumber") String serialNumber) {

        List<Dictionary> dicByType = dictionaryDao.getDicByType(serialNumber);
        return new ResponseEntity<>(dicByType,HttpStatus.OK);
    }

}
