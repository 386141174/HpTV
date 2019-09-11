package com.hp.controller;

import com.hp.pojo.Live;
import com.hp.service.LiveService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/live")
public class LiveController {


    @Autowired
    private LiveService liveService;

    @PostMapping("create")
    public JsonResult createRoom(   @RequestParam("title") String title,
                                    @RequestParam("name") String name,
                                    @RequestParam("username") String username,
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam("crossfire") String crossfire){


        int count = liveService.createRoom(title,name,username,image,crossfire);
        return count == 1? new JsonResult(1,"success") : new JsonResult(2,"error");

    }

    /**
     * 显示直播的房间信息
     * @param username
     * @return
     */
    @GetMapping("selectRoom")
    public JsonResult selectRoom(@RequestParam("username") String username){

        Live live = liveService.selectRoom(username);

//        System.out.println();
        return new JsonResult(1,live);
    }


}