package com.hp.controller;

import com.hp.pojo.Live;
import com.hp.service.LiveService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


}
