package com.hp.service.imp;

import com.hp.dao.ShiPinDao;
import com.hp.pojo.ShiPin;
import com.hp.service.ShiPinService;
import com.hp.utils.Info;
import com.hp.utils.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiPinServiceImpl implements ShiPinService {

    @Autowired
    private ShiPinDao shiPinDao;

    /**插入视频*/
    public int insertUrl(ShiPin shiPin) {
        int jieguo = shiPinDao.insertUrl(shiPin);
        return jieguo;
    }

    /**查询视频*/
    public PageObject<ShiPin> selectShipin(Info info) {
        PageObject<ShiPin> pageObject = new PageObject<ShiPin>();
        /**设置每页显示条数*/
        info.setSize(5);

        if (info.getPageCurrent()!=null){
            pageObject.setPageCurrent(info.getPageCurrent());
            /**页面有数据，重新计算开始行,每页显示5条数据*/
            info.setStartRow((info.getPageCurrent() - 1) * info.getSize());
        }

        /**总记录数*/
        pageObject.setTotal(shiPinDao.selectShipinCount(info));

        /**总页数*/
        if (pageObject.getTotal() % info.getSize()!=0){
            pageObject.setPageCount((pageObject.getTotal() / info.getSize())+1);
        }else {
            pageObject.setPageCount((pageObject.getTotal() / info.getSize()));
        }

        /**结果集*/
        pageObject.setItems(shiPinDao.selAllOrders(info));

        return pageObject;
    }
}
