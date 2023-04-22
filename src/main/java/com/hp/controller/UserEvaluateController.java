package com.hp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.hp.dao.PublishServiceDao;
import com.hp.dao.UserEvaluateDao;
import com.hp.pojo.PublishService;
import com.hp.pojo.UserEvaluate;
import com.hp.vo.TokenBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/27 23:54
 **/
@RestController
@RequestMapping("/userEvaluate")
public class UserEvaluateController {

    Logger logger = LoggerFactory.getLogger(UserEvaluateController.class);

    @Autowired
    private UserEvaluateDao userEvaluateDao;

    @Autowired
    private PublishServiceDao publishServiceDao;

    @PostMapping("saveEvaluate")
    public ResponseEntity<?> saveEvaluate(@RequestParam("id") Integer id,
                                          @RequestParam("serviceNumber") String serviceNumber,
                                          @RequestParam("evaluate") String evaluate,
                                          @RequestParam(value = "file",required = false) MultipartFile file,
                                          @RequestParam("userName") String userName) {
        UserEvaluate build = UserEvaluate.builder()
                .evaluate(evaluate)
                .serviceNumber(serviceNumber)
                .userName(userName)
                .publishServiceId(id)
                .build();
        PublishService publishService = publishServiceDao.selectById(id);
        publishService.setState(4);
        publishServiceDao.updateById(publishService);
        if (file != null) {
            String upload = upload(file);
            if (StringUtils.isBlank(upload)) {
                return new ResponseEntity("图片上传失败", HttpStatus.BAD_REQUEST);
            }
            build.setImage(upload);
        }
        int insert = userEvaluateDao.insert(build);
        return new ResponseEntity<>(insert,HttpStatus.OK);
    }

    private String upload(MultipartFile file){
        //图片校验（图片是否为空,图片大小，上传的是不是图片、图片类型（例如只能上传png）等等）
        if (file.isEmpty()) {
            return "";
        }
        //可以自己加一点校验 例如上传的是不是图片或者上传的文件是不是png格式等等 这里省略
        //获取原来的文件名和后缀
        String originalFilename = file.getOriginalFilename();
//        String ext = "." + FilenameUtils.getExtension(orgFileName); --需要导依赖
        int lastIndexOf = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(lastIndexOf);
        //生成一个新的文件名（以防有重复的名字存在导致被覆盖）
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String newName = uuid + ext;
//        //拼接图片上传的路径 url+图片名
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());

//        String pre = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "/src/main/resources/static/image/";
        String path = pre + newName;
//        String path = pre + newName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            logger.error("文件上传错误",e);
            return "";
        }
        return "/imctemp-rainy/" + newName;
    }

    @PostMapping("queryEvaluate")
    public ResponseEntity<?> queryEvaluate(@RequestParam("id") Integer id) {
        QueryWrapper<UserEvaluate> queryWrapper = new QueryWrapper();
        queryWrapper.eq("publish_service_id",id);
        queryWrapper.orderByAsc("time");
        List<UserEvaluate> userEvaluates = userEvaluateDao.selectList(queryWrapper);
        return new ResponseEntity<>(userEvaluates,HttpStatus.OK);
    }

}
