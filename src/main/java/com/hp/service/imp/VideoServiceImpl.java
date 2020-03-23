package com.hp.service.imp;

import com.hp.dao.VideoDao;
import com.hp.pojo.Video;
import com.hp.service.VideoService;
import com.hp.utils.Info;
import com.hp.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    /**插入视频*/
    public int inserVideo(Video video) {
        int jieguo = videoDao.inserVideo(video);
        return jieguo;
    }

    /**查询视频*/
    public PageObject<Video> selectVideo(Info info) {
        PageObject<Video> pageObject = new PageObject<Video>();
        /**设置每页显示条数*/
        info.setSize(9);

        if (info.getPageCurrent()!=null){
            pageObject.setPageCurrent(info.getPageCurrent());
            /**页面有数据，重新计算开始行,每页显示5条数据*/
            info.setStartRow((info.getPageCurrent() - 1) * info.getSize());
        }

        /**总记录数*/
        pageObject.setTotal(videoDao.selectVideoCount(info));

        /**总页数*/
        if (pageObject.getTotal() % info.getSize()!=0){
            pageObject.setPageCount((pageObject.getTotal() / info.getSize())+1);
        }else {
            pageObject.setPageCount((pageObject.getTotal() / info.getSize()));
        }

        /**结果集*/
        pageObject.setItems(videoDao.selAllOrders(info));

        return pageObject;
    }


    @Override
    public PageObject<Video> queryVideo(Info info) {

        int startPage = info.getStartRow();
        int count = videoDao.selectCount(info.getVideoname());
        int size = 12;
        int pageSize = count/size;
        if (count % size != 0) {
            pageSize++;
        }
        System.out.println(pageSize);
        info.setStartRow((startPage - 1) * size);
        info.setSize(size);
        List<Video> videos = videoDao.queryVideo(info);
        PageObject<Video> pageObject = new PageObject<>();
        pageObject.setItems(videos);
        pageObject.setPageCount(pageSize);
        return pageObject;
    }


    @Override
    public List<Video> selectVideoType(String videoType) {
        return videoDao.selectVideoType(videoType);
    }

    @Override
    public List<Video> queryTeacherVideo(String teachername) {
        return videoDao.queryTeacherVideo(teachername);
    }

    @Override
    public int deleteVideo(int videoId) {

        return videoDao.deleteVideo(videoId);
    }

    @Override
    public List<Video> replay(Video video) {

        return videoDao.replayCourse(video);
    }
}
