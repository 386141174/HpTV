package com.hp.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.hp.dao.DictionaryDao;
import com.hp.dao.LoginMapper;
import com.hp.dao.PublishServiceDao;
import com.hp.dao.UserEvaluateDao;
import com.hp.pojo.Dictionary;
import com.hp.pojo.PublishService;
import com.hp.pojo.PublishServiceVo;
import com.hp.pojo.UserEvaluate;
import com.hp.pojo.UserLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/28 20:17
 **/
@RestController
@RequestMapping("/dataReview")
public class DataReviewController {

    @Autowired
    private PublishServiceDao publishServiceDao;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private DictionaryDao dictionaryDao;

    @Autowired
    private UserEvaluateDao userEvaluateDao;


    @GetMapping("overall")
    public ResponseEntity<?> getOverall() {
        Map<String,Object> result = new HashMap<>();
        List<UserLogin> userLogins = loginMapper.selectList(new QueryWrapper<>());
        long user = userLogins.stream()
                .filter(item -> StringUtils.equals(item.getToken(), "user"))
                .count();
        result.put("userCount",user);
        List<PublishService> publishServices = publishServiceDao.selectList(new QueryWrapper<>());
        int size = publishServices.size();
        result.put("message",size);
        ArrayList<PublishService> collect = publishServices.stream()
                .filter(item -> item.getServiceValue() != null)
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(PublishService::getServiceValue))), ArrayList::new));
        int serviceCount = collect.size();
        result.put("service",serviceCount);
        long attendant = userLogins.stream()
                .filter(item -> StringUtils.equals(item.getToken(), "attendant"))
                .count();
        result.put("attendant",attendant);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/publishService")
    public ResponseEntity<?> publishService(@RequestParam(value = "userName",required = false) String userName,@RequestParam(value = "serviceName",required = false) String serviceName) {
        List<PublishServiceVo> publishServiceVos = publishServiceDao.thisYearService(userName,serviceName, DateUtil.format(new Date(), "yyyy") + "-01-01 00:00:00", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        List<String> date = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        DateTime startPassDate = DateUtil.parse(DateUtil.format(new Date(), "yyyy") + "-01","yyyy-MM");
        String endDate = DateUtil.format(new Date(), "yyyy-MM");
        while (startPassDate.isBeforeOrEquals(DateUtil.parse(endDate,"yyyy-MM"))) {
            String formatStartPassDate = sdf.format(startPassDate);
            date.add(formatStartPassDate);
            int count = 0;
            for (int i =0;i < publishServiceVos.size();i++) {
                PublishServiceVo publishServiceVo = publishServiceVos.get(i);
                Date time = publishServiceVo.getTime();
                if(sdf.format(time).equals(formatStartPassDate)){
                    count++;
                }
            }
            data.add(count);
            startPassDate = DateUtil.offset(startPassDate, DateField.MONTH, 1);
        }
        Map map = new HashMap<String,Object>(){{
            put("date",date);
            put("data",data);
        }};
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("proportion")
    public ResponseEntity<?> getProportion(@RequestParam("userName") String userName) {
        List<PublishServiceVo> publishServiceVos = publishServiceDao.thisYearService(userName,"", DateUtil.format(new Date(), "yyyy") + "-01-01 00:00:00", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("serial_number","service_items");
        List<Dictionary> dictionaries = dictionaryDao.selectList(queryWrapper);
//        List<String> name = new LinkedList<>();
//        List<Long> data = new ArrayList<>();
        List<Map<String,Object>> data = new ArrayList<>();
        for (int i = 0;i < dictionaries.size();i++) {
            Map<String,Object> map = new HashMap<>();
            Dictionary dictionary = dictionaries.get(i);
            long count = publishServiceVos.stream()
                    .filter(item -> dictionary.getValue().equals(item.getServiceValue()))
                    .count();
            map.put("value",count);
            map.put("name",dictionary.getSerialName());
            data.add(map);
        }

        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @GetMapping("orderDetails")
    public ResponseEntity<?> orderDetails(@RequestParam("userName") String userName) {
        List<PublishServiceVo> publishServiceVos = publishServiceDao.thisYearService(userName,"", DateUtil.format(new Date(), "yyyy") + "-01-01 00:00:00", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        DateTime startPassDate = DateUtil.parse(DateUtil.format(new Date(), "yyyy") + "-01","yyyy-MM");
        String endDate = DateUtil.format(new Date(), "yyyy-MM");
        List<String> date = new ArrayList<>();
        List<Long> accept = new ArrayList<>();
        List<Long> back = new ArrayList<>();
        List<Long> reject = new ArrayList<>();
        while (startPassDate.isBeforeOrEquals(DateUtil.parse(endDate,"yyyy-MM"))) {
            String formatStartPassDate = sdf.format(startPassDate);
            date.add(formatStartPassDate);
            long acceptCount = publishServiceVos.stream()
                    .filter(item -> sdf.format(item.getTime()).equals(formatStartPassDate))
                    .filter(item -> item.getState().equals(5) || item.getState().equals(4))
                    .count();
            accept.add(acceptCount);
            long acceptBack = publishServiceVos.stream()
                    .filter(item -> sdf.format(item.getTime()).equals(formatStartPassDate))
                    .filter(item -> item.getState().equals(3))
                    .count();
            back.add(acceptBack);
            long acceptReject = publishServiceVos.stream()
                    .filter(item -> sdf.format(item.getTime()).equals(formatStartPassDate))
                    .filter(item -> item.getState().equals(6))
                    .count();
            reject.add(acceptReject);
            startPassDate = DateUtil.offset(startPassDate, DateField.MONTH, 1);
        }

        Map<String,Object> resultMap = new HashMap<String,Object>(){{
            put("name",date);
            put("accept",accept);
            put("back",back);
            put("reject",reject);
        }};
        return new ResponseEntity<>(resultMap,HttpStatus.OK);

    }


    @GetMapping("comment")
    public ResponseEntity<?> getComment(){
        List<UserEvaluate> userEvaluates = userEvaluateDao.selectList(new QueryWrapper<>());
        List<UserEvaluate> collect = userEvaluates.stream()
                .filter(item -> StringUtils.isNotBlank(item.getImage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(collect,HttpStatus.OK);
    }

}
