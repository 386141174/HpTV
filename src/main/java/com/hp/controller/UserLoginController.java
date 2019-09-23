package com.hp.controller;

import com.hp.pojo.UserLogin;
import com.hp.result.Result;
import com.hp.result.ResultFactory;
import com.hp.service.LoginService;
import com.hp.utils.EdsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping(value = "/login")
    public Result Login( @RequestParam(value="username" )String username, @RequestParam(value = "password") String password, HttpSession session) {


        UserLogin userLogin = loginService.Login(username);
        if(userLogin!=null){
            if(EdsUtil.decryptBasedDes(userLogin.getPassword()).equals(password)){
                session.setAttribute("user",userLogin);
                return ResultFactory.buildSuccessResult(userLogin);
            }else {
                String message="账号或密码错误！";
                return ResultFactory.buildFailResult(message);
            }
        }
        else {
            String message="账号或密码不能为空！";
           return ResultFactory.buildFailResult(message);
        }

    }


     @PostMapping("/register")
    public Result addUser(@RequestParam(value="username")String username,@RequestParam(value = "password")String password ){
        if( (username==null)||username.equals("")||(password==null)||password.equals("")){

                String message="用户或密码不能为空！";
                return ResultFactory.buildFailResult(message);

        } else if(loginService.selectbyUsername(username)!=0){
            String message="用户名已经被注册，请换个用户名！";
            return ResultFactory.buildFailResult(message);

        }
        else  {
            String password1=EdsUtil.encryptBasedDes(password);
            System.out.println("password1:"+password1);
            boolean flag = loginService.addUser(username, password1);
            loginService.updateRemarkByUsername(password,username);
            if (flag) {
                System.out.println("添加成功");
                return ResultFactory.buildSuccessResult(flag);
            } else {
                String message = "添加失败";
                System.out.println("添加失败");
                return ResultFactory.buildFailResult(message);
            }
        }
    }

}
