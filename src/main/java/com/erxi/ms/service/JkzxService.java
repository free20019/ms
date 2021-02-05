package com.erxi.ms.service;

import com.erxi.ms.access.UserContext;
import com.erxi.ms.config.DS;
import com.erxi.ms.dao.CommonDao;
import com.erxi.ms.dao.JkzxDao;
import com.erxi.ms.domain.User;
import com.erxi.ms.redis.BasePrefix;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.ZlKey;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.util.AMQPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: xianlehuang
 * @Description:监控中心
 * @date: 2020/8/26 - 10:05
 */
@Service
@Slf4j
@Component
public class JkzxService {

    @Autowired
    private JkzxDao jkzxDao;

    private static CommonDao commonDao;

    @Autowired
    public void setCommonDao(CommonDao commonDao) {
        JkzxService.commonDao = commonDao;
    }

    @Autowired
    RedisService redisService;

    @Autowired
    TlaqService tlaqService;

    @Autowired
    AMQPUtil amqpUtil;
    /**
     * 标记redis取数据
     */
    public static boolean flag =true;

    private Map<String,Object> getParameterToMap(HttpServletRequest request,String... parameters){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        for(String parameter : parameters){
            map.put(parameter,request.getParameter(parameter)==null?"":request.getParameter(parameter));
        }
        return map;
    }


    public static HashMap<String, Object> findConditionBytype(String type, HashMap<String, Object> map) {
        if(map==null){
            map = new HashMap<String, Object>();
        }
        try {
            User user = commonDao.getByUsername(UserContext.getUser().getUsername());
            if(!user.getMenu().isEmpty()&&user.getMenu().indexOf("ddyzt_seeOne")>-1){
                map.put("power_company",user.getPower().toString().split(",",-1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @DS("datasource1")
    public String getMapCluster(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"lev","veh_type");
        map.put("veh_type",map.get("veh_type").toString().split(","));
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getMapCluster(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    @Transactional
    public void getAllVehicleLocation() {
        if(flag){
            //出租定位（巡游车）
            redisService.set(ZlKey.monitorXyc, "", FastJsonUtil.toJSONString(jkzxDao.getMonitorXyc()));
            //网约定位
            redisService.set(ZlKey.monitorWyc, "", FastJsonUtil.toJSONString(jkzxDao.getMonitorWyc()));
            //两客两货定位
            redisService.set(ZlKey.monitorLklh, "", FastJsonUtil.toJSONString(jkzxDao.getMonitorLklh()));
            //执法车定位
            redisService.set(ZlKey.monitorZfc, "", FastJsonUtil.toJSONString( jkzxDao.getMonitorZfc(new HashMap<String, Object>())));
            //执法终端定位
            redisService.set(ZlKey.monitorZfzd, "", FastJsonUtil.toJSONString(jkzxDao.getMonitorZfzd()));
            //对讲机定位
            redisService.set(ZlKey.monitorDjj, "", FastJsonUtil.toJSONString(jkzxDao.getMonitorDjj(new HashMap<String, Object>())));

            //出租车辆（巡游车）
            redisService.set(ZlKey.vehicleXyc, "", FastJsonUtil.toJSONString(jkzxDao.getVehicleXyc()));
            //网约车辆
            redisService.set(ZlKey.vehicleWyc, "", FastJsonUtil.toJSONString(jkzxDao.getVehicleWyc()));
            //两客两货车辆
            redisService.set(ZlKey.vehicleLklh, "", FastJsonUtil.toJSONString(jkzxDao.getVehicleLklh()));
            //执法车车辆
            redisService.set(ZlKey.vehicleZfc, "", FastJsonUtil.toJSONString( jkzxDao.getVehicleZfc(new HashMap<String, Object>())));
            //执法终端车辆
            redisService.set(ZlKey.vehicleZfzd, "", FastJsonUtil.toJSONString(jkzxDao.getVehicleZfzd()));
            //对讲机车辆
            redisService.set(ZlKey.vehicleDjj, "", FastJsonUtil.toJSONString(jkzxDao.getVehicleDjj(new HashMap<String, Object>())));
        }
    }

    @DS("datasource1")
    @Transactional
    public String getVehicleLocation(String type) {
        Map<String, BasePrefix> map = new HashMap<String, BasePrefix>();
        List list = null;
        map.put("0",ZlKey.monitorXyc);
        map.put("1",ZlKey.monitorWyc);
        map.put("2",ZlKey.monitorLklh);
        map.put("3",ZlKey.monitorZfc);
        map.put("4",ZlKey.monitorZfzd);
        map.put("5",ZlKey.monitorDjj);
        if(!redisService.exist(map.get(type),"")||Integer.parseInt(type)>=3){
            //出租（巡游车）
            if(type.equals("0")){
                try {
                    list = jkzxDao.getMonitorXyc();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorXyc, "", FastJsonUtil.toJSONString(list));
            }
            //网约
            else  if(type.equals("1")){
                try {
                    list = jkzxDao.getMonitorWyc();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorWyc, "", FastJsonUtil.toJSONString(list));
            }
            //两客两货
            else  if(type.equals("2")){
                try {
                    list = jkzxDao.getMonitorLklh();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorLklh, "", FastJsonUtil.toJSONString(list));
            }
            //执法车
            else  if(type.equals("3")){
                try {
                    HashMap<String, Object> mapZfc = findConditionBytype(type, null);
                    list = jkzxDao.getMonitorZfc(mapZfc);
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorZfc, "", FastJsonUtil.toJSONString(list));
            }
            //执法终端
            else  if(type.equals("4")){
                try {
                    list = jkzxDao.getMonitorZfzd();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorZfzd, "", FastJsonUtil.toJSONString(list));
            }
            //对讲机
            else  if(type.equals("5")){
                try {
                    HashMap<String, Object> mapDjj = findConditionBytype(type, null);
                    list = jkzxDao.getMonitorDjj(mapDjj);
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.monitorDjj, "", FastJsonUtil.toJSONString(list));
            }
            if(list==null){
                list = new ArrayList<LinkedHashMap<String, Object>>();
            }
            return FastJsonUtil.toJSONString(list);
        }else {
            //出租（巡游车）
            if(type.equals("0")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorXyc, "", String.class)));
            }
            //网约
            else  if(type.equals("1")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorWyc, "", String.class)));
            }
            //两客两货
            else  if(type.equals("2")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorLklh, "", String.class)));
            }
            //执法车
            else  if(type.equals("3")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorZfc, "", String.class)));
            }
            //执法终端
            else  if(type.equals("4")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorZfzd, "", String.class)));
            }
            //对讲机
            else  if(type.equals("5")){
                return FastJsonUtil.toJSONString(FastJsonUtil.getListMap(redisService.get(ZlKey.monitorDjj, "", String.class)));
            }
            return FastJsonUtil.toJSONString(list);
        }
    }

    @DS("datasource1")
    public String getBrigadeLocation(HttpServletRequest request) {
        List<Map<String, Object>> list = new ArrayList();
        try {
            list = jkzxDao.getBrigadeLocation();
        }catch (Exception e){
            e.printStackTrace();
        }
        return FastJsonUtil.toJSONString(list);
    }

    public String getVehicleByCoordinate(HttpServletRequest request) {
        //list中经纬度字段
        String px = request.getParameter("px")==null?"":request.getParameter("px");
        String py = request.getParameter("py")==null?"":request.getParameter("py");
        //屏幕显示的经纬度
        float maxlat = request.getParameter("maxlat")==null?0: Float.parseFloat(request.getParameter("maxlat"));
        float maxlng = request.getParameter("maxlng")==null?0: Float.parseFloat(request.getParameter("maxlng"));
        float minlat = request.getParameter("minlat")==null?0: Float.parseFloat(request.getParameter("minlat"));
        float minlng = request.getParameter("minlng")==null?0: Float.parseFloat(request.getParameter("minlng"));
        //车辆类型
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        List returnList = null;
        //巡游车车辆单独
        if(type.equals("0")){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("maxlat",maxlat);
            map.put("maxlng",maxlng);
            map.put("minlat",minlat);
            map.put("minlng",minlng);
            returnList = jkzxDao.findAreaVehicleXyc(map);
        }
        //网约车车辆单独
        else
        if(type.equals("1")){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("maxlat",maxlat);
            map.put("maxlng",maxlng);
            map.put("minlat",minlat);
            map.put("minlng",minlng);
            returnList = jkzxDao.findAreaVehicleWyc(map);
        }
        //两客两货车辆单独
        else if(type.equals("2")){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("maxlat",maxlat);
            map.put("maxlng",maxlng);
            map.put("minlat",minlat);
            map.put("minlng",minlng);
            returnList = jkzxDao.findAreaVehicleLklh(map);
        }
        else{
            returnList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> list = FastJsonUtil.getListMap(getVehicleLocation(type));
            float valuepx,valuepy;
            if(px==""||py==""||list==null){
                return FastJsonUtil.toJSONString(returnList);
            }else{
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).get(px)!=null&&list.get(i).get(py)!=null){
                        valuepx = Float.parseFloat(list.get(i).get(px).toString());
                        valuepy = Float.parseFloat(list.get(i).get(py).toString());
                        if (valuepx >= minlng && valuepx <=maxlng && valuepy >= minlat && valuepy <= maxlat){
                            returnList.add(list.get(i));
                        }
                    }
                }
            }
        }
        return FastJsonUtil.toJSONString(returnList);
    }

    @DS("datasource1")
    public String getVehicleOnline(HttpServletRequest request) {
        String bytime = request.getParameter("bytime")==null?"":request.getParameter("bytime");
        String[] split = bytime.split(",");
        Map<String, Object> map = new HashMap<String, Object>();
//        //出租（巡游车）
//        List<Map<String, Object>> monitorXyc = FastJsonUtil.getListMap(getVehicleLocation("0"));
//        map.put("xyc",findOnlineCountByTime(monitorXyc,"stime",compareValueMax,compareValueMin));
//        //网约
//        List<Map<String, Object>> monitorWyc = FastJsonUtil.getListMap(getVehicleLocation("1"));
//        map.put("wyc",findOnlineCountByTime(monitorWyc,"positiontime",compareValueMax,compareValueMin));
//        //两客两货
//        List<Map<String, Object>> monitorLklh = FastJsonUtil.getListMap(getVehicleLocation("2"));
//        map.put("lklh",findOnlineCountByTime(monitorLklh,"stime",compareValueMax,compareValueMin));
//        //执法车
//        List<Map<String, Object>> monitorZfc = FastJsonUtil.getListMap(getVehicleLocation("3"));
//        map.put("zfc",findOnlineCountByTime(monitorZfc,"speed_time",compareValueMax,compareValueMin));
//        //执法终端
//        List<Map<String, Object>> monitorZfzd = FastJsonUtil.getListMap(getVehicleLocation("4"));
//        map.put("zfzd",findOnlineCountByTime(monitorZfzd,"speed_time",compareValueMax,compareValueMin));
//        //对讲机
//        List<Map<String, Object>> monitorDjj = FastJsonUtil.getListMap(getVehicleLocation("5"));
//        map.put("djj",findOnlineCountByTime(monitorDjj,"speed_time",compareValueMax,compareValueMin));
        List<Map<String, Object>> list = jkzxDao.getVehicleOnline();
        map.put("lklh",0);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                //出租（巡游车）
                if("0".equals(list.get(i).get("type"))){
                    map.put("xyc",list.get(i).get("count"));
                }
                //网约
                else if("1".equals(list.get(i).get("type"))){
                    map.put("wyc",list.get(i).get("count"));
                }
                //两客两货
                else if("2".equals(list.get(i).get("type"))||"3".equals(list.get(i).get("type"))||/*"4".equals(list.get(i).get("type"))||*/"5".equals(list.get(i).get("type"))){
                    map.put("lklh",Integer.parseInt(map.get("lklh").toString())+(list.get(i).get("count")==null?0:Integer.parseInt(list.get(i).get("count").toString())));
                }

            }
        }
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("3","zfc");
        map2.put("4","zfzd");
        map2.put("5","djj");
        for (int i = 3; i < split.length ; i++) {
            if(!"".equals(split[i])){
                long compareValueMax = System.currentTimeMillis();
                long compareValueMin = System.currentTimeMillis()-Long.parseLong(split[i]);
                List<Map<String, Object>> monitor = FastJsonUtil.getListMap(getVehicleLocation(i+""));
                map.put(map2.get(i+"").toString(),findOnlineCountByTime(monitor,"speed_time",compareValueMax,compareValueMin));
            }else{
                List<Map<String, Object>> monitor = FastJsonUtil.getListMap(getVehicleLocation(i+""));
                map.put(map2.get(i+"").toString(),monitor.size());
            }
        }
//        if(!"".equals(bytime)){
//            long compareValueMax = System.currentTimeMillis();
//            long compareValueMin = System.currentTimeMillis()-Long.parseLong(bytime);
//            //执法车
//            List<Map<String, Object>> monitorZfc = FastJsonUtil.getListMap(getVehicleLocation("3"));
//            map.put("zfc",findOnlineCountByTime(monitorZfc,"speed_time",compareValueMax,compareValueMin));
//            //执法终端
//            List<Map<String, Object>> monitorZfzd = FastJsonUtil.getListMap(getVehicleLocation("4"));
//            map.put("zfzd",findOnlineCountByTime(monitorZfzd,"speed_time",compareValueMax,compareValueMin));
//            //对讲机
//            List<Map<String, Object>> monitorDjj = FastJsonUtil.getListMap(getVehicleLocation("5"));
//            map.put("djj",findOnlineCountByTime(monitorDjj,"speed_time",compareValueMax,compareValueMin));
//        }else{
//            //执法车
//            List<Map<String, Object>> monitorZfc = FastJsonUtil.getListMap(getVehicleLocation("3"));
//            map.put("zfc",monitorZfc.size());
//            //执法终端
//            List<Map<String, Object>> monitorZfzd = FastJsonUtil.getListMap(getVehicleLocation("4"));
//            map.put("zfzd",monitorZfzd.size());
//            //对讲机
//            List<Map<String, Object>> monitorDjj = FastJsonUtil.getListMap(getVehicleLocation("5"));
//            map.put("djj",monitorDjj.size());
//        }
        return FastJsonUtil.toJSONString(map);
    }

    private Integer findOnlineCountByTime(List<Map<String, Object>> list,String field, long max, long min) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int count = 0;
        if(list==null||list.size()==0){
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).get(field)==null){
                continue;
            }
            long value = 0;
            try {
                value = sdf.parse(list.get(i).get(field).toString()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(min<=value&&value<=max){
                count ++;
            }
        }
        return count;
    }

    @DS("datasource1")
    public String getMonitorVehicle(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"vehicle_type", "vehicle_no");
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getMonitorVehicle(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public synchronized String addMonitorVehicle(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"vehicle_type", "vehicle_no");
        int count = 0;
        List<LinkedHashMap<String, Object>> monitorVehicle = jkzxDao.getMonitorVehicle(map);
        if(monitorVehicle.size()>0){
            return FastJsonUtil.toJSONString(-1);
        }
        try {
            count = jkzxDao.addMonitorVehicle(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(count);
        }
        return FastJsonUtil.toJSONString(count);
    }

    @DS("datasource1")
    public String deleteMonitorVehicle(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"vehicle_type", "vehicle_no");
        int count = 0;
        if(map.get("vehicle_type")==""){
            return FastJsonUtil.toJSONString(count);
        }
        map.put("vehicle_type",map.get("vehicle_type").toString().split(","));
        try {
            count = jkzxDao.deleteMonitorVehicle(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(count);
        }
        return FastJsonUtil.toJSONString(count);
    }

    @DS("datasource1")
    public String getVehicle(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        String is_screen = request.getParameter("is_screen")==null?"":request.getParameter("is_screen");
        String screen_value = request.getParameter("screen_value")==null?"":request.getParameter("screen_value");
        Map<String, BasePrefix> map = new HashMap<String, BasePrefix>();
        map.put("0",ZlKey.vehicleXyc);
        map.put("1",ZlKey.vehicleWyc);
        map.put("2",ZlKey.vehicleLklh);
        map.put("3",ZlKey.vehicleZfc);
        map.put("4",ZlKey.vehicleZfzd);
        map.put("5",ZlKey.vehicleDjj);
        List<Map<String, Object>> list = null;
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        if(!redisService.exist(map.get(type),"")||Integer.parseInt(type)>=3){
            //出租（巡游车）
            if(type.equals("0")){
                try {
                    list = jkzxDao.getVehicleXyc();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleXyc, "", FastJsonUtil.toJSONString(list));
            }
            //网约
            else  if(type.equals("1")){
                try {
                    list = jkzxDao.getVehicleWyc();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleWyc, "", FastJsonUtil.toJSONString(list));
            }
            //两客两货
            else  if(type.equals("2")){
                try {
                    list = jkzxDao.getVehicleLklh();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleLklh, "", FastJsonUtil.toJSONString(list));
            }
            //执法车
            else  if(type.equals("3")){
                try {
                    HashMap<String, Object> mapZfc = findConditionBytype(type, null);
                    list = jkzxDao.getVehicleZfc(mapZfc);
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleZfc, "", FastJsonUtil.toJSONString(list));
            }
            //执法终端
            else  if(type.equals("4")){
                try {
                    list = jkzxDao.getVehicleZfzd();
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleZfzd, "", FastJsonUtil.toJSONString(list));
            }
            //对讲机
            else  if(type.equals("5")){
                try {
                    HashMap<String, Object> mapDjj = findConditionBytype(type, null);
                    list = jkzxDao.getVehicleDjj(mapDjj);
                }catch (Exception e){
                    e.printStackTrace();
                }
                redisService.set(ZlKey.vehicleDjj, "", FastJsonUtil.toJSONString(list));
            }
        }
        list = FastJsonUtil.getListMap(redisService.get(map.get(type), "", String.class));

        if(list!=null && list.size()>=0){
            if("true".equals(is_screen)){
                for (int i = 0; i < list.size(); i++) {
                    if(String.valueOf(list.get(i).get("vehicle")).indexOf(screen_value) > -1){
                        returnList.add(list.get(i));
                    }
                }
            }else{
                returnList.addAll(list);
            }
        }
        return FastJsonUtil.toJSONString(returnList);
    }

    @DS("datasource1")
    public String getLocationByVehicle(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        String vehicle = request.getParameter("vehicle")==null?"":request.getParameter("vehicle");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("0","vehi_no");
        map.put("1","vehicleno");
        map.put("2","vehi_num");
        map.put("3","vehicle_num");
        map.put("4","vehicle_num");
        map.put("5","vehicle_num");
        List returnList = null;
        //网约车车辆单独
        if(type.equals("1")){
            HashMap<String, Object> map2 = new HashMap<String, Object>();
            map2.put("vehicle",vehicle);
            returnList = jkzxDao.findAreaVehicleWyc(map2);
        }else{
            returnList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> list = FastJsonUtil.getListMap(getVehicleLocation(type));
            if(list==null){
                return FastJsonUtil.toJSONString(returnList);
            }else{
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).get(map.get(type))!=null&&String.valueOf(list.get(i).get(map.get(type))).equals(vehicle)){
                        returnList.add(list.get(i));
                        continue;
                    }
                }
            }
        }
        return FastJsonUtil.toJSONString(returnList);
    }

    @DS("datasource1")
    public String getTrajectoryByVehicle(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        String vehicle = request.getParameter("vehicle")==null?"":request.getParameter("vehicle");
        String stime = request.getParameter("stime")==null?"":request.getParameter("stime");
        String etime = request.getParameter("etime")==null?"":request.getParameter("etime");
        List<Map<String, Object>> list = new ArrayList();
        HashMap<String, Object> map = new HashMap<String, Object>();
        String date = stime.replaceAll("-", "").substring(2,6);
        map.put("vehicle",vehicle);
        map.put("stime",stime);
        map.put("etime",etime);
        //出租（巡游车）
        try {
            if(type.equals("0")){
//                list = jkzxDao.getTrajectoryXyc(map);
                list = tlaqService.findGjww(stime, etime, vehicle);
            }
            //网约
            else  if(type.equals("1")){
                map.put("table","tb_positionvehicle_"+date);
                list = jkzxDao.getTrajectoryWyc(map);
            }
            //两客两货
            else  if(type.equals("2")){
                map.put("table","tb_non_gps_"+date);
                list = jkzxDao.getTrajectoryLklh(map);
            }
            //执法车
            else  if(type.equals("3")){
                map.put("table","tb_zf_gps_"+date);
                map = findConditionBytype(type, map);
                list = jkzxDao.getTrajectoryZfc(map);
            }
            //执法终端
            else  if(type.equals("4")){
                map.put("table","tb_zf_gps_"+date);
                list = jkzxDao.getTrajectoryZfzd(map);
            }
            //对讲机
            else  if(type.equals("5")){
                map.put("table","tb_zf_gps_"+date);
                map = findConditionBytype(type, map);
                list = jkzxDao.getTrajectoryDjj(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public String getMonitorDetails(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        List<Map<String, Object>> list = new ArrayList();
        //执法车
        if("3".equals(type)){
            try {
                HashMap<String, Object> mapZfc = findConditionBytype(type, null);
                list = jkzxDao.getMonitorDetailsZfc(mapZfc);
                list = changeListColumn(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //对讲机
        else  if("5".equals(type)){
            try {
                HashMap<String, Object> mapDjj = findConditionBytype(type, null);
                list = jkzxDao.getMonitorDetailsDjj(mapDjj);
                list = changeListColumn(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public String getMonitorDetailsMenu(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        List<Map<String, Object>> list = new ArrayList();
        //执法车
        if("3".equals(type)){
            try {
                HashMap<String, Object> mapZfc = findConditionBytype(type, null);
                list = jkzxDao.getMonitorDetailsMenuZfc(mapZfc);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //对讲机
        else  if("5".equals(type)){
            try {
                HashMap<String, Object> mapDjj = findConditionBytype(type, null);
                list = jkzxDao.getMonitorDetailsMenuDjj(mapDjj);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return FastJsonUtil.toJSONString(list);
    }


    @DS("datasource1")
    public String getMonitorDetailsDetails(HttpServletRequest request) {
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        String name = request.getParameter("name")==null?"":request.getParameter("name");
        List<Map<String, Object>> list = new ArrayList();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        //执法车
        if("3".equals(type)){
            try {
                map = findConditionBytype(type, map);
                list = jkzxDao.getMonitorDetailsDetailsZfc(map);
                list = changeListColumn(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //对讲机
        else  if("5".equals(type)){
            try {
                map = findConditionBytype(type, map);
                list = jkzxDao.getMonitorDetailsDetailsDjj(map);
                list = changeListColumn(list);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public String getAllStation(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request);
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getAllStation(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    public String getStationLocation(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"station_name");
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getStationLocation(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public String getStationCamera(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"station_id", "floor");
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getStationCamera(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    private List<Map<String, Object>> changeListColumn(List<Map<String, Object>> list) {
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                list.get(i).put("SPEED_TIME_OLD",list.get(i).get("SPEED_TIME"));
                list.get(i).put("SPEED_TIME",list.get(i).get("updateTime"));
            }
        }
        return list;
    }

    @DS("datasource1")
    public String getVideoRoom(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"room_name");
        map.put("user_id",UserContext.getUser().getId());
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getVideoRoom(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    @Transactional(rollbackFor = Exception.class)
    public synchronized String addVideoRoom(HttpServletRequest request) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //被邀请的用户需要包含自己
        Map<String, Object> map = getParameterToMap(request,"room_name", "wait_time", "invite_users", "invite_user_ids");
        map.put("create_userid", UserContext.getUser().getId());
        map.put("create_username",UserContext.getUser().getUsername());
        map.put("room_id","");
        //创建房间地址逻辑
        map.put("room_url", "https://im2.twkeji.cn:8443/demos/test.html");

        int count = 0;
        //判断有无权限
        User user = commonDao.getByUsername(UserContext.getUser().getUsername());
        if(user.getMenu().isEmpty()||user.getMenu().indexOf("sphy_add")==-1){
            count = -1;
            log.info(UserContext.getUser().getMenu());
            log.info("该用户无权限");
            return FastJsonUtil.toJSONString(count);
        }
        try {
            count = jkzxDao.addVideoRoom(map);
        }catch (Exception e){
            count = 0;
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtil.toJSONString(count);
        }
        //被邀请的用户入库以及消息提醒
        Map<String, Object> invite_map = new HashMap<String, Object>();
        //被邀请的用户入库id集合
        LinkedList<Object>  inviteId_list = new LinkedList<Object>();
        invite_map.put("room_id",map.get("room_id"));
        invite_map.put("id","");
        String[] users = String.valueOf(map.get("invite_users")).split(",", -1);
        String[] user_ids = String.valueOf(map.get("invite_user_ids")).split(",", -1);
        try {
            //被邀请的用户入库
            for (int i = 0; i < user_ids.length; i++) {
                invite_map.put("username",users[i]);
                invite_map.put("user_id",user_ids[i]);
                jkzxDao.addVideoInvite(invite_map);
                inviteId_list.add(invite_map.get("id"));
            }
            //被邀请的用户消息提醒
            amqpUtil.createExchange("exchange");
            HashMap<String, Object> sendMap = new HashMap<String, Object>();
            sendMap.put("is_popup", "true");
//            sendMap.put("room_url", "https://im2.twkeji.cn:8443/demos/test.html");
            sendMap.put("room_id", map.get("room_id"));
            sendMap.put("wait_time", map.get("wait_time"));
            sendMap.put("time",sdf.format(new Date()));
            for (int i = 0; i < user_ids.length; i++) {
                amqpUtil.createQueue("video."+user_ids[i],"exchange","video."+user_ids[i]);
                amqpUtil.sendMessage("exchange","video."+user_ids[i], sendMap, Long.parseLong(map.get("wait_time").toString())*1000+"");
                log.info("消息提示：用户id="+user_ids[i]+",用户="+users[i]+",记录id="+inviteId_list.get(i));
            }
        }catch (Exception e){
            count = 0;
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtil.toJSONString(count);
        }
        return FastJsonUtil.toJSONString(count);
    }

    @DS("datasource1")
    public String deleteVideoRoom(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"room_id");
        int count = 0;
        try {
            count = jkzxDao.deleteVideoRoom(map);
            //被邀请的用户消息取消提醒
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(count);
        }
        return FastJsonUtil.toJSONString(count);
    }

    @DS("datasource1")
    public String getUser(HttpServletRequest request) {
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            list = jkzxDao.getUser();
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    @Transactional(rollbackFor = Exception.class)
    public String clickVideoRoom(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"room_id");
        HashMap<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("count",0);
        returnMap.put("url","");
        try {
            //判断该房间是否被删除
            if(jkzxDao.getVideoRoomByIdOne(map)==0){
                return FastJsonUtil.toJSONString(returnMap);
            } else{
                //判断该房间视频有无结束,结束删除
                if(jkzxDao.getVideoRoomByIdTwo(map)==0){
                    jkzxDao.deleteVideoRoom(map);
                    return FastJsonUtil.toJSONString(returnMap);
                }else{
                    //查询该用户点击的房间
                    map.put("user_id",UserContext.getUser().getId());
                    List<LinkedHashMap<String, Object>> list = jkzxDao.getVideoRoom(map);
                    if(list.size() == 1){
                        //有无权限进入
                        if("1".equals(list.get(0).get("is_in"))){
                            //更新用户进入记录
                            jkzxDao.updateVideoRecord(map);
                            returnMap.put("count",1);
                            returnMap.put("url",list.get(0).get("room_url"));
                            return FastJsonUtil.toJSONString(returnMap);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return FastJsonUtil.toJSONString(returnMap);
        }
        return FastJsonUtil.toJSONString(returnMap);
    }

    public Object getUserId(HttpServletRequest request) {
        return UserContext.getUser().getId();
    }

    @DS("datasource1")
    public String getZfcOnlineTimeStatistics(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"structure_name","vehi_no","stime","etime");
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            map = findConditionBytype("3", (HashMap<String, Object>) map);
            map.put("structure_name",map.get("structure_name").toString().isEmpty()?null:map.get("structure_name").toString().split(","));
            map.put("vehi_no",map.get("vehi_no").toString().isEmpty()?null:map.get("vehi_no").toString().split(","));
            list = jkzxDao.getZfcOnlineTimeStatistics(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        if(list.size()>0){
            for (LinkedHashMap<String, Object> hashMap : list) {
                hashMap.put("time",hashMap.get("online_time"));
                hashMap.put("lc",hashMap.get("mileage"));
                hashMap.put("online_time",hashMap.get("online_time")==null?"":String.format("%02d小时%02d分钟%02d秒",Long.parseLong(hashMap.get("online_time").toString())/1000/60/60, Long.parseLong(hashMap.get("online_time").toString())/1000/60%60, Long.parseLong(hashMap.get("online_time").toString())/1000%60));
                hashMap.put("mileage",hashMap.get("mileage")==null?"0公里":String.format("%01d.%03d公里",new Double(Double.parseDouble(hashMap.get("mileage").toString())).longValue()/1000, new Double(Double.parseDouble(hashMap.get("mileage").toString())).longValue()%1000));
            }
        }
        return FastJsonUtil.toJSONString(list);
    }


    @DS("datasource1")
    public String getZfcOnlineTime(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"structure_name","vehi_no","stime","etime");
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            map = findConditionBytype("3", (HashMap<String, Object>) map);
            map.put("structure_name",map.get("structure_name").toString().isEmpty()?null:map.get("structure_name").toString().split(","));
            map.put("vehi_no",map.get("vehi_no").toString().isEmpty()?null:map.get("vehi_no").toString().split(","));
            list = jkzxDao.getZfcOnlineTime(map);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        if(list.size()>0){
//            for (LinkedHashMap<String, Object> hashMap : list) {
//                hashMap.put("Date",hashMap.get("Date")==null?"":hashMap.get("Date").toString().substring(0,10));
//                hashMap.put("time1",hashMap.get("OnlineTime1"));
//                hashMap.put("time2",hashMap.get("OnlineTime2"));
//                hashMap.put("OnlineTime1",hashMap.get("OnlineTime1")==null?"":String.format("%02d小时%02d分钟",Integer.parseInt(hashMap.get("OnlineTime1").toString())/60, Integer.parseInt(hashMap.get("OnlineTime1").toString())%60));
//                hashMap.put("OnlineTime2",hashMap.get("OnlineTime2")==null?"":String.format("%02d小时%02d分钟",Integer.parseInt(hashMap.get("OnlineTime2").toString())/60, Integer.parseInt(hashMap.get("OnlineTime2").toString())%60));
//            }
            for (LinkedHashMap<String, Object> hashMap : list) {
                hashMap.put("stat_date",hashMap.get("stat_date")==null?"":hashMap.get("stat_date").toString().substring(0,10));
                hashMap.put("time",hashMap.get("online_time"));
                hashMap.put("lc",hashMap.get("mileage"));
                hashMap.put("online_time",hashMap.get("online_time")==null?"":String.format("%02d小时%02d分钟%02d秒",Long.parseLong(hashMap.get("online_time").toString())/1000/60/60, Long.parseLong(hashMap.get("online_time").toString())/1000/60%60, Long.parseLong(hashMap.get("online_time").toString())/1000%60));
                hashMap.put("mileage",hashMap.get("mileage")==null?"0公里":String.format("%01d.%03d公里",new Double(Double.parseDouble(hashMap.get("mileage").toString())).longValue()/1000, new Double(Double.parseDouble(hashMap.get("mileage").toString())).longValue()%1000));
            }
        }
        return FastJsonUtil.toJSONString(list);
    }

    public String getAllZfc(HttpServletRequest request) {
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            HashMap<String, Object> mapZfc = findConditionBytype("3", null);
            list = jkzxDao.getAllZfc(mapZfc);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

    public String getStructure(HttpServletRequest request) {
        List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        try {
            HashMap<String, Object> mapZfc = findConditionBytype("3", null);
            list = jkzxDao.getStructure(mapZfc);
        }catch (Exception e){
            e.printStackTrace();
            return FastJsonUtil.toJSONString(list);
        }
        return FastJsonUtil.toJSONString(list);
    }

}
