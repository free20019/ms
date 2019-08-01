package com.erxi.ms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.SbwxService;
/**
 * 信息服务
 * @author xianlehuang
 * @date : 2018年10月15日 上午9:41:00
 */
@Controller
@RequestMapping(value = "/sbwx")
public class SbwxAction {
	@Autowired
	private SbwxService sbwxService;

	private DownloadAct downloadAct = new DownloadAct();
	
	/**
	 * 安全监测
	 * @param request
	 * @return
	 */
	@RequestMapping("/safety")
	@ResponseBody
	public String safety(HttpServletRequest request) {
		String msg = sbwxService.getsafety();
		return msg;
	}
	/**
	 * 下拉所有公司
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qycomp")
	@ResponseBody
	public String qycomp() {
		String msg = sbwxService.qycomp();
		return msg;
	}
	
	/**
	 * 下拉栏
	 * @param request
	 * @param table
	 * @return
	 */
	@RequestMapping("/xll")
	@ResponseBody
	public String findxll(HttpServletRequest request,@RequestParam("table") String table) {
		String msg = sbwxService.findxll(table);
		return msg;
	}
	/**
	 * 车辆下拉栏
	 * @param request
	 * @param table
	 * @return
	 */
	@RequestMapping("/clxll")
	@ResponseBody
	public String findclxll(HttpServletRequest request,@RequestParam("table") String table) {
		String msg = sbwxService.findclxll(table);
		return msg;
	}
	/**
	 * 车辆下拉栏
	 * @param request
	 * @param table
	 * @return
	 */
	@RequestMapping("/clxll2")
	@ResponseBody
	public String findclxll2(HttpServletRequest request,@RequestParam("table") String table) {
		String msg = sbwxService.findclxll2(table);
		return msg;
	}
	/**
	 * 车辆监控
	 * @param request
	 * @return
	 */
	@RequestMapping("/vehicle")
	@ResponseBody
	public String vehicle(HttpServletRequest request) {
		String msg = sbwxService.vehicle();
		return msg;
	}
	/**
	 * 车辆定位
	 * @param request
	 * @param vehi_no
	 * @return
	 */
	@RequestMapping("/vhicmarker")
	@ResponseBody
	public String vhicmarker(HttpServletRequest request,String vehi_no) {
		String msg = sbwxService.vhicmarker(vehi_no);
		return msg;
	}
	/**
	 * 车辆定位
	 * @param request
	 * @param vehi_no
	 * @return
	 */
	@RequestMapping("/vhicmarker2")
	@ResponseBody
	public String vhicmarker2(HttpServletRequest request,String vehi_no) {
		String msg = sbwxService.vhicmarker2(vehi_no);
		return msg;
	}
	/**
	 * 分组监控
	 * @param request
	 * @param status
	 * @param vehstr
	 * @return
	 */
	@RequestMapping("/findfzjk")
	@ResponseBody
	public String findfzjk(HttpServletRequest request,String status,String vehstr) {
		String msg = sbwxService.findfzjk(status,vehstr);
		return msg;
	}
	/**
	 * 车辆组
	 * @param request
	 * @param id
	 * @param tree_name
	 * @param type
	 * @param vehstr
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public Result<List<Map<String, Object>>> tree(HttpServletRequest request,String id,String tree_name,String vehstr,String type) {
		return sbwxService.tree(id,tree_name,vehstr,type);
	}
//	/**
//	 * 修改车辆组
//	 * @param request
//	 * @param id
//	 * @param tree_name
//	 * @param vehstr
//	 * @return
//	 */
//	@RequestMapping("/editTree")
//	@ResponseBody
//	public Result<List<Map<String, Object>>> editTree(HttpServletRequest request,String id,String tree_name,String vehstr) {
//		return sbwxService.editTree(id,tree_name,vehstr);
//	}
	/**
	 * 删除车辆组
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/removeTree")
	@ResponseBody
	public Result<List<Map<String, Object>>> removeTree(HttpServletRequest request,String id) {
		return sbwxService.removeTree(id);
	}
	/**
	 * 查询所有车辆组
	 * @param request
	 * @return
	 */
	@RequestMapping("/findtree")
	@ResponseBody
	public String findtree(HttpServletRequest request) {
		String msg = sbwxService.findtree();
		return msg;
	}
	/**
	 * 监控车辆导出
	 * @param request
	 * @param list
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/vehicleexcel")
	@ResponseBody
	public String vehicleexcel(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		String a[] = {"业户","车牌号","车牌类型","SIM卡","车主","手机号码","故障类型","故障发生时间"};//导出列明
		String b[] = {"COMP_NAME","VEHI_NO","VT_NAME","SIM_NUM","OWN_NAME","OWN_TEL","gzlx","gzsj"};//导出map中的key
		String gzb = "故障车辆监控";//导出sheet名和导出的文件名
		String params=request.getParameter("params");
		List<Map<String, Object>> list=downloadAct.strlist(params) ;
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
//	/**
//	 * 故障车辆到期预警
//	 * @param request
//	 * @param stime
//	 * @param etime
//	 * @return
//	 */
//	@RequestMapping("/findydqbj")
//	@ResponseBody
//	public String findydqbj(HttpServletRequest request,
//			@RequestParam("stime") String stime,
//			@RequestParam("etime") String etime) {
//		String msg = sbwxService.findydqbj(stime,etime);
//		return msg;
//	}
	/**
	 * 故障车辆预警
	 * @param request
	 * @param day
	 * @param etime
	 * @param type
	 * @return
	 */
	@RequestMapping("/findclbj")
	@ResponseBody
	public String findclbj(HttpServletRequest request,
			@RequestParam("day") String day,
			@RequestParam("etime") String etime,
			@RequestParam("type") String type) {
		String msg = sbwxService.findclbj(day,etime,type);
		return msg;
	}
	/**
	 * 维修车辆预警（抓拍监控）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faultMonitor")
	@ResponseBody
	public String faultMonitor() {
		String msg = sbwxService.faultMonitor();
		return msg;
	}
	/**
	 * 查询维修车辆 
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findwxcl")
	@ResponseBody
	public String findwxcl(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("terminal") String terminal,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findwxcl(stime,etime,vehicle,company,terminal,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 维修车辆导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findwxcldc")
	@ResponseBody
	public String findwxcldc(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("terminal") String terminal) throws IOException{
		String a[] = {"业户","车牌号","当前故障类型","SIM卡","车主","手机号码","故障发生时间"};//导出列明
		String b[] = {"COMP_NAME","VEHICLE_NO","GZ","SIM_NUM","OWN_NAME","OWN_TEL","DB_TIME"};//导出map中的key
		String gzb = "维修车辆查询";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = sbwxService.findwxcldc(stime,etime,vehicle,company,terminal);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 维修进度查询
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findwxjd")
	@ResponseBody
	public String findwxjd(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findwxjd(stime,etime,vehicle,company,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 维修进度导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findwxjddc")
	@ResponseBody
	public String findwxjddc(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company) throws IOException{
		String a[] = {"业户","车牌号","SIM卡","车主","手机号码","上次故障原因","送修时间","完修时间","维修进度","维修情况"};//导出列明
		String b[] = {"COMP_NAME","VEHI_NO","SIM_NUM","OWN_NAME","OWN_TEL","RM_MALFUNCTION","RR_TIME","RR_TIME_END","JDXX","RC_CONTENT"};//导出map中的key
		String gzb = "维修进度查询";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = sbwxService.findwxjddc(stime,etime,vehicle,company);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 维修车辆统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findwxtj")
	@ResponseBody
	public String findwxtj(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("terminal") String terminal,
			@RequestParam("status") String status,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findwxtj(stime,etime,vehicle,company,terminal,status,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 维修车辆导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param status
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findwxtjdc")
	@ResponseBody
	public String findwxtjdc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("terminal") String terminal,
			@RequestParam("status") String status) throws IOException {
		String a[] = {"业户","车牌号","当前故障类型","故障发生时间","维修进度","SIM卡","车主","手机号码","送修时间","完修时间","上次维修类型","维修地点","维修人姓名","维修费用（元）","客户满意度","维修情况"};//导出列明
		String b[] = {"COMP_NAME","VEHICLE_NO","GZ","DB_TIME","JDXX","SIM_NUM","OWN_NAME","OWN_TEL","RR_TIME","RR_TIME_END","RT_TYPE","RA_ADDR","WXRY","RR_COST","TCSS","RC_CONTENT"};//导出map中的key
		String gzb = "维修车辆统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = sbwxService.findwxtjdc(stime,etime,vehicle,company,terminal,status);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	/**
	 * 维修工单
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findwxgd")
	@ResponseBody
	public String findwxgd(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findwxgd(stime,etime,vehicle,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 维修工单导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findwxgddc")
	@ResponseBody
	public String findwxgddc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle) throws IOException {
		String a[] = {"公司名称","车牌号码","区域","故障类型","故障时间","第一次催告时间","第二次催告时间","第三次催告时间","反馈时间","是否维修","维修时间","维修结果","备注"};//导出列明
		String b[] = {"COMP_NAME","VEHICLE_NO","AREA_NAME","FAULT_TYPE","TIME","ONCE","TWICE","THIRD","FGSJ","IS_REPAIR","WXSJ","REPAIR_RESULT","DESCRIPTION"};//导出map中的key
		String gzb = "维修工单";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = sbwxService.findwxgddc(stime,etime,vehicle);
		if(list.size()>0){			
			for(int i = 0; i< list.size();i++){
				list.get(i).put("IS_REPAIR", String.valueOf(list.get(i).get("IS_REPAIR")).equals("0")?"已维修":(String.valueOf(list.get(i).get("IS_REPAIR")).equals("1")?"未维修":""));
			}
		}
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	/**
	 * 视频移位巡检查询
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findspywxj")
	@ResponseBody
	public String findwxgd(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findspywxj(stime,etime,vehicle,company,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 视频移位巡检导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findspywxjdc")
	@ResponseBody
	public String findspywxjdc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company) throws IOException {
		String a[] = {"公司名称","车牌号码","1路（第一次巡检）","2路（第一次巡检）","3路（第一次巡检）","4路（第一次巡检）","1路（第二次巡检）","2路（第二次巡检）","3路（第二次巡检）","4路（第二次巡检）","巡查时间"};//导出列明
		String b[] = {"COMP_NAME","VEHI_NO","ONE_ROAD_ONE","ONE_ROAD_TWO","ONE_ROAD_THREE","ONE_ROAD_FOUR","TWO_ROAD_ONE","TWO_ROAD_TWO","TWO_ROAD_THREE","TWO_ROAD_FOUR","TIME",};//导出map中的key
		String gzb = "视频移位巡检";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = sbwxService.findspywxjdc(stime,etime,vehicle,company);
		if(list.size()>0){			
			for(int i = 0; i< list.size();i++){
				list.get(i).put("ONE_ROAD_ONE", String.valueOf(list.get(i).get("ONE_ROAD_ONE")).equals("0")?"正常":(String.valueOf(list.get(i).get("ONE_ROAD_ONE")).equals("1")?"偏移":(String.valueOf(list.get(i).get("ONE_ROAD_ONE")).equals("2")?"黑屏":"")));
				list.get(i).put("ONE_ROAD_TWO", String.valueOf(list.get(i).get("ONE_ROAD_TWO")).equals("0")?"正常":(String.valueOf(list.get(i).get("ONE_ROAD_TWO")).equals("1")?"偏移":(String.valueOf(list.get(i).get("ONE_ROAD_TWO")).equals("2")?"黑屏":"")));
				list.get(i).put("ONE_ROAD_THREE", String.valueOf(list.get(i).get("ONE_ROAD_THREE")).equals("0")?"正常":(String.valueOf(list.get(i).get("ONE_ROAD_THREE")).equals("1")?"偏移":(String.valueOf(list.get(i).get("ONE_ROAD_THREE")).equals("2")?"黑屏":"")));
				list.get(i).put("ONE_ROAD_FOUR", String.valueOf(list.get(i).get("ONE_ROAD_FOUR")).equals("0")?"正常":(String.valueOf(list.get(i).get("ONE_ROAD_FOUR")).equals("1")?"偏移":(String.valueOf(list.get(i).get("ONE_ROAD_FOUR")).equals("2")?"黑屏":"")));
				list.get(i).put("TWO_ROAD_ONE", String.valueOf(list.get(i).get("TWO_ROAD_ONE")).equals("0")?"正常":(String.valueOf(list.get(i).get("TWO_ROAD_ONE")).equals("1")?"偏移":(String.valueOf(list.get(i).get("TWO_ROAD_ONE")).equals("2")?"黑屏":"")));
				list.get(i).put("TWO_ROAD_TWO", String.valueOf(list.get(i).get("TWO_ROAD_TWO")).equals("0")?"正常":(String.valueOf(list.get(i).get("TWO_ROAD_TWO")).equals("1")?"偏移":(String.valueOf(list.get(i).get("TWO_ROAD_TWO")).equals("2")?"黑屏":"")));
				list.get(i).put("TWO_ROAD_THREE", String.valueOf(list.get(i).get("TWO_ROAD_THREE")).equals("0")?"正常":(String.valueOf(list.get(i).get("TWO_ROAD_THREE")).equals("1")?"偏移":(String.valueOf(list.get(i).get("TWO_ROAD_THREE")).equals("2")?"黑屏":"")));
				list.get(i).put("TWO_ROAD_FOUR", String.valueOf(list.get(i).get("TWO_ROAD_FOUR")).equals("0")?"正常":(String.valueOf(list.get(i).get("TWO_ROAD_FOUR")).equals("1")?"偏移":(String.valueOf(list.get(i).get("TWO_ROAD_FOUR")).equals("2")?"黑屏":"")));
				list.get(i).put("TIME", String.valueOf(list.get(i).get("TWO_TIME")).equals("null")?String.valueOf(list.get(i).get("ONE_TIME")):String.valueOf(list.get(i).get("TWO_TIME")));
			}
		}
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 登记信息接入
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param person
	 * @param type
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */

	@RequestMapping("/find")
	@ResponseBody
	public String findvehicle(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("block") String block,
			@RequestParam("company") String company,
			@RequestParam("person") String person,
			@RequestParam("type") String type,
			@RequestParam("terminal") String terminal,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findvehicle(stime,etime,vehicle,block,company,person,type,terminal,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 进度信息接入
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param person
	 * @param type
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findjd")
	@ResponseBody
	public String findjd(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("block") String block,
			@RequestParam("company") String company,
			@RequestParam("person") String person,
			@RequestParam("type") String type,
			@RequestParam("terminal") String terminal,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findjd(stime,etime,vehicle,block,company,person,type,terminal,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 维修车辆统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findtj")
	@ResponseBody
	public String findtj(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("block") String block,
			@RequestParam("company") String company,
			@RequestParam("terminal") String terminal,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = sbwxService.findtj(stime,etime,vehicle,block,company,terminal,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 出租乘运需求分析
	 * 打车热点分析
	 */
	@RequestMapping("/dcrdfx")
	@ResponseBody
	public String dardrx(HttpServletRequest request){
		String msg = sbwxService.dcrdfx();
		return msg;
	}
	@RequestMapping("/dcsjfb")
	@ResponseBody
	public String dcsjfb(HttpServletRequest request){
		String msg = sbwxService.dcsjfb();
		return msg;
	}
	/*
	 * 乘客流向分析
	 * */
	@RequestMapping("/cklxfx")
	@ResponseBody
	public String cklxfx(HttpServletRequest request,@RequestParam("time") String time,@RequestParam("table") String table){
		String msg = sbwxService.cklxfx(time,table);
		return msg;
	}
}
