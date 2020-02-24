package com.hp.controller;


import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;
import com.hp.service.LoginService;
import com.hp.utils.*;
import com.hp.vo.TokenBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/user")
public class UserLoginController {

    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private LoginService loginService;



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


}



