package com.hp.controller;


import com.hp.pojo.UserLogin;
import com.hp.pojo.UserPower;
import com.hp.result.Result;
import com.hp.result.ResultFactory;
import com.hp.service.LoginService;
import com.hp.utils.*;
import com.hp.vo.TokenBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserLoginController {

    Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @Autowired
    private LoginService loginService;



    @PostMapping(value = "/login")
    public JsonResult Login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
//        /*logger.info("用户名username为：" + username + " 密码password：" + password);*/
//        UserLogin userLogin = loginService.Login(username);
//        logger.info("从数据库查出来的用户user：" + userLogin);
//        JSONObject obj = new JSONObject();
//        if (userLogin != null) {
//            if (MD5Utils.MD5(password).equals(userLogin.getPassword())) {
//                /*session.setAttribute("user",userLogin);*/
////                String token = SetRedisData(username, password);
//                obj.put("status", "用户登录成功");
////                obj.put("token", token);
//                return new JsonResult(1, "登录成功", userLogin);
//            } else {
//                String message = "账号或密码错误！";
//                obj.put("status", "用户登录失败");
//                return new JsonResult(0, message);
//            }
//        } else {
//            String message = "账号或密码不能为空！";
//            obj.put("status", "用户登录失败");;
//
//            return new JsonResult(0, message);
//        }
        UserPower userPower = new UserPower();
        userPower.setToken("admin-token");
        return new JsonResult(1,userPower);

    }

    //@AuthToken
    @GetMapping("/info")
    public JsonResult Info(@RequestParam("token") String token){

        UserPower userPower = new UserPower();
        userPower.setPower(token);
//        List<UserPower> userPowers = loginService.selectUserPower(userPower);
        TokenBean bean = new TokenBean();
        bean.setIntroduction("i am a student");
        bean.setName("student");
        String[] arr = {"admin"};
        bean.setRoles(arr);
        return  new JsonResult(1,bean);
    }


    @PostMapping("logout")
    public JsonResult logOut(){
        return new JsonResult(1,"ok");
    }




    @PostMapping("/register")
    public JsonResult addUser(@RequestBody UserLogin userLogin) {
        if ((userLogin.getUsername() == null) || userLogin.getUsername().equals("") || (userLogin.getPassword() == null) || userLogin.getPassword().equals("")) {

            String message = "用户或密码不能为空！";
            return new JsonResult(0, message);

        } else if (loginService.selectbyUsername(userLogin.getUsername()) != 0) {
            String message = "用户名已经被注册，请换个用户名！";
            return new JsonResult(0, message);

        } else {
            String remark = userLogin.getPassword();
            /* String password=EdsUtil.encryptBasedDes(userLogin.getPassword());*/
            String password = MD5Utils.MD5(userLogin.getPassword());
            System.out.println("password1:" + password);
            userLogin.setPassword(password);
            boolean flag = loginService.addUser(userLogin);
            loginService.updateRemarkByUsername(remark, userLogin.getUsername());
            if (flag) {
                System.out.println("添加成功");
                return new JsonResult(1, flag);
            } else {
                String message = "添加失败";
                System.out.println("添加失败");
                return new JsonResult(0, message);
            }
        }
    }


}



