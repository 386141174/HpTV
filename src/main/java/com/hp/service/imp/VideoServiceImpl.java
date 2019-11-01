package com.hp.service.imp;

import com.hp.dao.VideoDao;
import com.hp.pojo.Video;
import com.hp.service.VideoService;
import com.hp.utils.Info;
import com.hp.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
