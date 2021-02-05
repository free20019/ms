package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.service.JkzxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:监控中心
 * @date: 2020/8/26 - 10:02
 */
@Controller
@RequestMapping(value = "/jkzx")
public class JkzxAction {

    @Autowired
    private JkzxService jkzxService;

    private DownloadAct downloadAct = new DownloadAct();

    //点聚合
    @RequestMapping("/getMapCluster")
    @ResponseBody
    public String getMapCluster(HttpServletRequest request){
        return jkzxService.getMapCluster(request);
    }

    //根据车辆类型查询车辆定位信息
    @RequestMapping("/getVehicleLocation")
    @ResponseBody
    public String getVehicleLocation(HttpServletRequest request){
        String type = request.getParameter("type")==null?"":request.getParameter("type");
        return jkzxService.getVehicleLocation(type);
    }

    //执法大队点位
    @RequestMapping("/getBrigadeLocation")
    @ResponseBody
    public String getBrigadeLocation(HttpServletRequest request){
        return jkzxService.getBrigadeLocation(request);
    }

    //根据坐标范围筛选车辆
    @RequestMapping("/getVehicleByCoordinate")
    @ResponseBody
    public String getVehicleByCoordinate(HttpServletRequest request){
        return jkzxService.getVehicleByCoordinate(request);
    }

    //车辆在线信息
    @RequestMapping("/getVehicleOnline")
    @ResponseBody
    public String getVehicleOnline(HttpServletRequest request){
        return jkzxService.getVehicleOnline(request);
    }

    //多车监控车辆查询
    @RequestMapping("/getMonitorVehicle")
    @ResponseBody
    public String getMonitorVehicle(HttpServletRequest request){
        return jkzxService.getMonitorVehicle(request);
    }

    //多车监控车辆添加
    @RequestMapping("/addMonitorVehicle")
    @ResponseBody
    public String addMonitorVehicle(HttpServletRequest request){
        return jkzxService.addMonitorVehicle(request);
    }

    //多车监控车辆删除
    @RequestMapping("/deleteMonitorVehicle")
    @ResponseBody
    public String deleteMonitorVehicle(HttpServletRequest request){
        return jkzxService.deleteMonitorVehicle(request);
    }

    //车辆查询
    @RequestMapping("/getVehicle")
    @ResponseBody
    public String getVehicle(HttpServletRequest request){
        return jkzxService.getVehicle(request);
    }

    //车辆定位查询
    @RequestMapping("/getLocationByVehicle")
    @ResponseBody
    public String getLocationByVehicle(HttpServletRequest request){
        return jkzxService.getLocationByVehicle(request);
    }

    //车辆轨迹查询
    @RequestMapping("/getTrajectoryByVehicle")
    @ResponseBody
    public String getTrajectoryByVehicle(HttpServletRequest request){
        return jkzxService.getTrajectoryByVehicle(request);
    }

    //监控详情查询页面
    @RequestMapping("/getMonitorDetails")
    @ResponseBody
    public String getMonitorDetails(HttpServletRequest request){
        return jkzxService.getMonitorDetails(request);
    }

    //监控详情查询页面
    @RequestMapping("/getMonitorDetailsMenu")
    @ResponseBody
    public String getMonitorDetailsMenu(HttpServletRequest request){
        return jkzxService.getMonitorDetailsMenu(request);
    }

    //监控详情查询页面
    @RequestMapping("/getMonitorDetailsDetails")
    @ResponseBody
    public String getMonitorDetailsDetails(HttpServletRequest request){
        return jkzxService.getMonitorDetailsDetails(request);
    }

    //所有场站查询
    @RequestMapping("/getAllStation")
    @ResponseBody
    public String getAllStation(HttpServletRequest request){
        return jkzxService.getAllStation(request);
    }

    //场站定位查询
    @RequestMapping("/getStationLocation")
    @ResponseBody
    public String getStationLocation(HttpServletRequest request){
        return jkzxService.getStationLocation(request);
    }

    //场站摄像头查询
    @RequestMapping("/getStationCamera")
    @ResponseBody
    public String getStationCamera(HttpServletRequest request){
        return jkzxService.getStationCamera(request);
    }

    //视频房间查询
    @RequestMapping("/getVideoRoom")
    @ResponseBody
    public String getVideoRoom(HttpServletRequest request){
        return jkzxService.getVideoRoom(request);
    }

    //视频房间添加
    @RequestMapping("/addVideoRoom")
    @ResponseBody
    public String addVideoRoom(HttpServletRequest request){
        return jkzxService.addVideoRoom(request);
    }

    //视频房间删除
    @RequestMapping("/deleteVideoRoom")
    @ResponseBody
    public String deleteVideoRoom(HttpServletRequest request){
        return jkzxService.deleteVideoRoom(request);
    }

    //用户下拉框
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request){
        return jkzxService.getUser(request);
    }

    //用户点入视频房间
    @RequestMapping("/clickVideoRoom")
    @ResponseBody
    public String clickVideoRoom(HttpServletRequest request){
        return jkzxService.clickVideoRoom(request);
    }

    //获取用户id
    @RequestMapping("/getUserId")
    @ResponseBody
    public Object getUserId(HttpServletRequest request){
        return jkzxService.getUserId(request);
    }

    //在线统计
    @RequestMapping("/getZfcOnlineTimeStatistics")
    @ResponseBody
    public String getZfcOnlineTimeStatistics(HttpServletRequest request){
        return jkzxService.getZfcOnlineTimeStatistics(request);
    }

    //在线统计导出
    @RequestMapping("/getZfcOnlineTimeStatisticsExcel")
    @ResponseBody
    public String getZfcOnlineTimeStatisticsExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String a[] = {"单位","车牌号","上线时长","里程"};//导出列明
        String b[] = {"structure_name","vehi_no","online_time","mileage"};//导出map中的key
        String gzb = "在线统计";//导出sheet名和导出的文件名
        List<Map<String, Object>> list = DownloadAct.strlist(jkzxService.getZfcOnlineTimeStatistics(request));
        downloadAct.download(request,response,a,b,gzb,list);
        return null;
    }

    //在线查询
    @RequestMapping("/getZfcOnlineTime")
    @ResponseBody
    public String getZfcOnlineTime(HttpServletRequest request){
        return jkzxService.getZfcOnlineTime(request);
    }

    //在线查询导出
    @RequestMapping("/getZfcOnlineTimeExcel")
    @ResponseBody
    public String getZfcOnlineTimeExcel(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        String a[] = {"日期","单位","车牌号","全天上线时长","工时在线时长"};//导出列明
//        String b[] = {"Date","structure_name","vehi_no","OnlineTime1","OnlineTime2"};//导出map中的key
//        String gzb = "执法车在线时长";//导出sheet名和导出的文件名
//        List<Map<String, Object>> list = DownloadAct.strlist(jkzxService.getZfcOnlineTime(request));
//        long time1 = 0, time2 = 0;
//        if(list.size()>0){
//            for (Map<String, Object> map : list) {
//                time1 = time1 + (map.get("time1")==null?0:Long.parseLong(map.get("time1").toString()));
//                time2 = time2 + (map.get("time2")==null?0:Long.parseLong(map.get("time2").toString()));
//            }
//            Map<String, Object> lastMap =  new LinkedHashMap<String, Object>();
//            lastMap.put("Date","在线统计");
//            lastMap.put("OnlineTime1",String.format("%02d小时%02d分钟",time1/60, time1%60));
//            lastMap.put("OnlineTime2",String.format("%02d小时%02d分钟",time2/60, time2%60));
//            list.add(lastMap);
//        }
//        downloadAct.download(request,response,a,b,gzb,list);
//        return null;
        String a[] = {"日期","单位","车牌号","上线时长","里程"};//导出列明
        String b[] = {"stat_date","structure_name","vehi_no","online_time","mileage"};//导出map中的key
        String gzb = "在线查询";//导出sheet名和导出的文件名
        List<Map<String, Object>> list = DownloadAct.strlist(jkzxService.getZfcOnlineTime(request));
        downloadAct.download(request,response,a,b,gzb,list);
        return null;
    }

    //执法车下拉框
    @RequestMapping("/getAllZfc")
    @ResponseBody
    public String getAllZfc(HttpServletRequest request){
        return jkzxService.getAllZfc(request);
    }

    //单位下拉框
    @RequestMapping("/getStructure")
    @ResponseBody
    public String getStructure(HttpServletRequest request){
        return jkzxService.getStructure(request);
    }


}
