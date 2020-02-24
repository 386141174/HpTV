package com.hp.controller;


import com.hp.pojo.Video;
import com.hp.service.VideoService;
import com.hp.utils.Info;
import com.hp.utils.JsonResult;
import com.hp.utils.PageObject;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    public JsonResult uploadFile(@RequestParam("fileType") String fileType,
                                 @RequestParam("upVideoName") String upVideoName,
                                 @RequestParam("fileName") MultipartFile file,
                                 @RequestParam("describe") String describe,
                                 @RequestParam("videoType") String videoType,
                                 @RequestParam("username") String username,
                                 @RequestParam("liveType") String liveType,
                                 @RequestParam("ifopen") boolean ifopen) throws Exception{

        int open = 0;
        if (!ifopen) {
            open = 1;
        }

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
        //String newName=uuid+extension;
        //新的视频文件名称
        String videoName=uuid+extension;
        System.out.println("新的文件名称-----"+videoName);
        //图片文件名称
        String picName=uuid+".jpg";
        System.out.println("图片文件名称-----"+videoName);
        //视频在本地存放路径
        String videopath = "D:/file/" +videoName;
        File  videoFile= new File(videopath);
        System.out.println(videopath);
        //图片在本地存放路径
        String picPath="D:/pic/"+picName;
        File picFile=new File(picPath);
        System.out.println(picPath);
        //保存视频
//        try {
            file.transferTo(videoFile);
            long start = System.currentTimeMillis();
            FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videopath);
            ff.start();
            int lenght = ff.getLengthInFrames();
            int i = 0;
            Frame f = null;
            while (i < lenght) {
                // 过滤前5帧，避免出现全黑的图片，依自己情况而定
                f = ff.grabFrame();
                if ((i > 5) && (f.image != null)) {
                    break;
                }
                i++;
            }
            opencv_core.IplImage img = f.image;
            int owidth = img.width();
            int oheight = img.height();
            // 对截取的帧进行等比例缩放
            int width = 800;
            int height = (int) (((double) width / owidth) * oheight);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            bi.getGraphics().drawImage(f.image.getBufferedImage().getScaledInstance(width, height, Image.SCALE_SMOOTH),
                    0, 0, null);
            ImageIO.write(bi, "jpg", picFile);
            ff.stop();
            System.out.println(System.currentTimeMillis() - start);

            String videoUrl="http://localhost:8080/file/"+videoName;
            String picUrl="http://localhost:8080/pic/"+picName;
            Video video=new Video(videoName,videoUrl,videopath,
                    picName,picUrl,picPath,fileType,upVideoName,username,liveType,open);
            video.setDescribe(describe);
            video.setVideoType(videoType);
            int jieguo= videoService.inserVideo(video);
//        } catch (Exception e) {
//            return new JsonResult(0,"上传失败");
//        }

        return new JsonResult(1,"上传成功");
    }

    //查询
    @PostMapping(value = "/selectVideo")
    public JsonResult selectVideo(Info info){
        System.out.println(info);
        PageObject<Video> pageObject= videoService.selectVideo(info);

        return new JsonResult(1,pageObject);
    }

    @GetMapping("query")
    public JsonResult queryVideo(@RequestParam("keyword") String keyword,
                                 @RequestParam("page") int page) {
        Info info = new Info();
        info.setStartRow(page);
        info.setVideoname(keyword);
        PageObject<Video> videoPageObject = videoService.queryVideo(info);
        return videoPageObject != null ? new JsonResult(1,videoPageObject) : new JsonResult(0,"error");
    }

}
