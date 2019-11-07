package com.hp.controller;

import com.hp.pojo.Live;
import com.hp.service.LiveService;
import com.hp.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/live")
public class LiveController {


    @Autowired
    private LiveService liveService;


    @PostMapping("create")
    public JsonResult createRoom(@RequestParam("title") String title,
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
        return new JsonResult(1,live);
    }

    /**
     * 更新直播间信息
     * @param title 标题
     * @param name 老师名称
     * @param image 封面
     * @param crossfire 串流码
     * @return
     */
    @PostMapping("updateRoom")
    public JsonResult updateRoom(@RequestParam("title") String title,
                                 @RequestParam("username") String username,
                                 @RequestParam("name") String name,
                                 @RequestParam(value = "image",required = false) MultipartFile image,
                                 @RequestParam("crossfire") String crossfire){

        int count = liveService.updateRoom(title,name,username,image,crossfire);
        return count == 1? new JsonResult(1,"success") : new JsonResult(0,"error");
    }


    /**
     * 查询所有直播间信息
     * @return
     */
    @GetMapping("selectListRoom")
    public JsonResult selectListRoom(){
        List<Live> lives = liveService.selectListRoom();
        return new JsonResult(1,lives);
    }

    @GetMapping("getRtmp")
    public JsonResult getRtmp(@RequestParam("roomNumber") int roomNumber){
        return new JsonResult(1,liveService.getRtmp(roomNumber));
    }


}
