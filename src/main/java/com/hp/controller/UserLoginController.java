package com.hp.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dao.LoginMapper;
import com.hp.pojo.PublishService;
import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;
import com.hp.service.LoginService;
import com.hp.utils.*;
import com.hp.vo.TokenBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/user")
public class UserLoginController {

    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginMapper loginMapper;

    @PostMapping(value = "/login")
    public JsonResult Login(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password) {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        String token = loginService.selectUserToken(userLogin);
       UserPower userPower = new UserPower();
       userPower.setToken(token);
       return new JsonResult(1,userPower);


    }


    @GetMapping("/info")
    public JsonResult Info(@RequestParam(value = "token",required = false) String token){
        TokenBean bean = new TokenBean();
        bean.setIntroduction("i am a "+token);
        String[] arr;
        UserPower userPower = new UserPower();
        userPower.setToken(token);
        String roles = loginService.selectUserPower(userPower);
        arr = new String[]{roles};
        bean.setRoles(arr);
        return  new JsonResult(1,bean);
    }


    @PostMapping("logout")
    public JsonResult logOut(){
        return new JsonResult(1,"ok");
    }



    @PostMapping("signIn")
    public JsonResult signIn(@RequestBody UserLogin userLogin){
        System.out.println(userLogin.toString());
        int count = loginService.selectbyUsername(userLogin.getUsername());
        if (count == 0) {
            int register = loginService.addUser(userLogin);
            return register == 1? new JsonResult(1,"success") : new JsonResult(0,"error");
        }else {
            return new JsonResult(0,"error");
        }

    }


    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam("pageNo") Integer pageNo,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("search") String search) {
        Page<UserLogin> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        Page<UserLogin> users = loginMapper.getUsers(page, search);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/attendant")
    public ResponseEntity<?> getAttendant(@RequestParam("pageNo") Integer pageNo,
                                     @RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("search") String search) {
        Page<UserLogin> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        Page<UserLogin> users = loginMapper.getAttendant(page, search);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserLogin userLogin){
        if (userLogin.getId() != null) {
            int update = loginMapper.updateById(userLogin);
            return new ResponseEntity<>(update,HttpStatus.OK);
        }
        String saltMD5 = loginService.getSaltMD5(userLogin.getPassword());
        userLogin.setPassword(saltMD5);
        int insert = loginMapper.insert(userLogin);
        return new ResponseEntity<>(insert,HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id){
        int deleteById = loginMapper.deleteById(id);
        return new ResponseEntity<>(deleteById,HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public ResponseEntity userInfo(@RequestParam("id") Integer id){
        UserLogin userLogin = loginMapper.selectById(id);
        return new ResponseEntity(userLogin,HttpStatus.OK);
    }

    @GetMapping("/allAttendant")
    public ResponseEntity<?> getAttendant() {
        QueryWrapper<UserLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token","attendant");
        queryWrapper.eq("state",1);
        List<UserLogin> userLogins = loginMapper.selectList(queryWrapper);
        return new ResponseEntity<>(userLogins, HttpStatus.OK);
    }
}



