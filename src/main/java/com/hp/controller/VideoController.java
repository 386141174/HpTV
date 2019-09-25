package com.hp.controller;


import com.hp.pojo.Video;
import com.hp.service.VideoService;
import com.hp.utils.Info;
import com.hp.utils.JsonResult;
import com.hp.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**上传视频
     * fileType：上传视频类型
     * */
    @PostMapping(value = "/uploadFile")
    public JsonResult uploadFile(@RequestParam("filetype") String fileType,
                                 @RequestParam("videoname") String videoname,
                                 @RequestParam("fileName") MultipartFile file) {


        //判断文件是否为空
        if (file.isEmpty()) {
            return new JsonResult(0,"上传内容不能为空");
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        //获取最后一个点的位置
        int index=fileName.lastIndexOf(".");
        //截取扩展名
        String extension=fileName.substring(index);
        //获取UUID
        String uuid=UUID.randomUUID().toString().replace("-","");
        //加个UUID，尽量避免文件名称重复
        String newName=uuid+extension;
        //新的文件名称
        System.out.println("新的文件名称-----"+newName);
        String path = "M:/file/" +newName;
        //文件绝对路径
        System.out.print("保存文件绝对路径"+path+"\n");
        //创建文件路径
        File dest = new File(path);

        try {
            //上传文件
            file.transferTo(dest); //保存文件
            String url="http://localhost:8080/img/"+newName;
//            Video video=new Video(url,newName,path,fileType);
            Video video=new Video(newName,url,path,fileType,videoname);
            int jieguo= videoService.inserVideo(video);
        } catch (IOException e) {
            return new JsonResult(0,"上传失败");
        }

        return new JsonResult(1,"上传成功");
    }

    //查询
    @PostMapping(value = "/selectVideo")
    public JsonResult selectVideo(Info info){
        System.out.println(info);
        PageObject<Video> pageObject= videoService.selectVideo(info);
        return new JsonResult(1,pageObject);
    }
}
