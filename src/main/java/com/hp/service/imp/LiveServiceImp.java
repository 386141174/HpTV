package com.hp.service.imp;


import cn.hutool.core.util.RandomUtil;
import com.hp.dao.LiveDao;
import com.hp.pojo.Live;
import com.hp.service.LiveService;


import com.hp.utils.JsonResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class LiveServiceImp implements LiveService {

    @Autowired
    private LiveDao liveDao;

    private final String resourcesPath = "/img/";


    /**
     * 申请直播间
     * @param title
     * @param name
     * @param username
     * @param image
     * @param crossfire
     * @return
     */
    @Override
    public int createRoom(String title, String name, String username, MultipartFile image, String crossfire) {
        String img = saveImage(image);
        Live live = new Live();
        live.setCrossfire(crossfire);
        live.setName(name);
        live.setTitle(title);
        live.setUsername(username);
        live.setImage(img);
        Random random = new Random();
        int roomNumber = random.nextInt(9999-1000+1)+1000;
        System.out.println(roomNumber);
        live.setRoomNumber(roomNumber);
        String s = RandomUtil.randomString(4);
        live.setLiveType(s);
        int count = liveDao.createRoom(live);
        return count;
    }


    public String saveImage(MultipartFile file) {
        if (file.isEmpty()){
            throw new NullPointerException("文件为空");
        }

        String fileAllname = file.getOriginalFilename();
        String filename = fileAllname.substring(0,fileAllname.indexOf("."));
        String fileExtname = fileAllname.substring(fileAllname.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        String filepath = "D:/file/" + uuid + filename + fileExtname;
        String fileDataSource = "http://localhost:8080"+resourcesPath + uuid + filename + fileExtname;

        try{
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filepath));
        }catch (IOException e){
            e.printStackTrace();
        }

        return fileDataSource;
    }


    /**
     * 查询直播间信息
     * @param username 根据用户名查找
     * @return
     */
    @Override
    public Live selectRoom(String username) {
        return liveDao.selectRoom(username);
    }


    /**
     * 更新直播间信息
     * @param title 标题
     * @param name 老师名称
     * @param image 封面
     * @param crossfire 串流码
     * @return
     */
    @Override
    public int updateRoom(String title, String name, String username, MultipartFile image, String crossfire) {
        String img =null;
        if (image != null){
           img = saveImage(image);
        }

        Live live = new Live();
        live.setImage(img);
        live.setTitle(title);
        live.setName(name);
        live.setCrossfire(crossfire);
        live.setUsername(username);
        return liveDao.updateRoom(live);
    }

    /**
     * 查询所有直播间信息
     * @return
     */
    @Override
    public List<Live> selectListRoom() {
        return liveDao.selectListRoom();
    }


    /**
     * 获取当前直播房间信息
     * @param roomNumber
     * @return
     */
    @Override
    public Live getRtmp(int roomNumber) {
        return liveDao.getRtmp(roomNumber);
    }

    @Override
    public List<Live> selectLiveType(String username) {
        return liveDao.selectLiveType(username);
    }

    @Override
    public int queryBuyLive(String liveType, String userName) {

        return liveDao.queryBuyLive(liveType,userName);
    }

    @Override
    public List<Live> queryStudentCourse(String username) {
        return liveDao.queryStudentCourse(username);
    }
}
