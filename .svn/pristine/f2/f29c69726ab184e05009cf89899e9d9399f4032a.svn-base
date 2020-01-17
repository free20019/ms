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
import com.erxi.ms.service.OneToTwoService;

/**
 * 合并一期功能
 * 营运态势
 * @author erxi
 *
 */
@Controller
@RequestMapping(value = "/otta")
public class OneToTwoAction {
	
	@Autowired
	private OneToTwoService  oneToTwoService;
	
	private DownloadAct downloadAct = new DownloadAct();
	
	/**
	 * 上线率
	 * @return
	 */
	@RequestMapping("/sxl")
	@ResponseBody
	public Result<List<Map<String, Object>>> sxl() {
		Result<List<Map<String, Object>>> msg = oneToTwoService.sxl();
		return msg;
	}
	
	/**
	 * 在线营运
	 * @return
	 */
	@RequestMapping(value = "/zxyy")
	@ResponseBody
	public Result<List<Map<String, Object>>> zzyy() {
		Result<List<Map<String, Object>>> msg = oneToTwoService.zxyy();
		return msg;
	}
	
	/**
	 * 一小时未营运
	 * @return
	 */
	@RequestMapping(value = "/yxswyy")
	@ResponseBody
	public Result<List<Map<String, Object>>> yxswyy() {
		Result<List<Map<String, Object>>> msg = oneToTwoService.yxswyy();
		return msg;
	}
	
	/**
	 * 重点监控区域车辆数
	 * @return
	 */
	@RequestMapping(value = "/findzdqu")
	@ResponseBody
	public Result<List<Map<String, Object>>> findzdqu() {
		Result<List<Map<String, Object>>> msg = oneToTwoService.findzdqu();
		return msg;
	}
	
	/**
	 * 疑似停运
	 * @return
	 */
	@RequestMapping(value = "/ysty")
	@ResponseBody
	public Result<List<Map<String, Object>>> ysty() {
		Result<List<Map<String, Object>>> msg = oneToTwoService.ysty();
		return msg;
	}
	
	/**
	 * 杭州出租保有量统计
	 */
	@RequestMapping("/findbyl")
	@ResponseBody
	public Result<List<Map<String, Object>>> findbyl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		Result<List<Map<String, Object>>> msg = oneToTwoService.findbyl(postData);
		return msg;
	}
	
	/**
	 * 杭州出租保有量导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("findbyldc")
	@ResponseBody
	public String findbyldc(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","当前车辆","新增车辆"};//导出列明
		String b[] = {"VEHI_DATE","C1","C"};//导出map中的key
		String gzb = "杭州出租保有量导出";//导出sheet名和导出的文件名
		Result<List<Map<String, Object>>> msg = oneToTwoService.findbyl(postData);
		List<Map<String, Object>> list = msg.getData();//导出的数据
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("C", Integer.valueOf(list.get(i).get("C1").toString())+Integer.valueOf(list.get(i).get("C").toString()));
		}
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	/**
	 * 重点监控区域车辆分析
	 * @return
	 */
	@RequestMapping(value = "/zdjkqy")
	@ResponseBody
	public String zdjkqy(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = oneToTwoService.zdjkqy(postData);
		return msg;
	}
	
	/**
	 * 重点监控区域车辆分析   导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("zdjkqyexcle")
	@ResponseBody
	public String zdjkqyexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00","4:30","5:00","5:30","6:00","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23","y24","y25","y26","y27","y28","y29","y30","y31","y32","y33","y34","y35","y36","y37","y38","y39","y40","y41","y42","y43","y44","y45","y46","y47"};//导出map中的key
		String gzb = "重点区域车辆数量分析";//导出sheet名和导出的文件名
		String msg = oneToTwoService.zdjkqy(postData);
		List<Map<String, Object>>list = DownloadAct.str2list3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	
	/**
	 * 重点监控区域车辆明细查询
	 * @return
	 */
	@RequestMapping(value = "/clmxinfo")
	@ResponseBody
	public String clmxinfo(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = oneToTwoService.clmxinfo(postData);
		return msg;
	}
	
	/**
	 * 重点监控区域车辆明细  导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("clmxexcle")
	@ResponseBody
	public String clmxexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"0:00","0:30","1:00","1:30","2:00","2:30","3:00","3:30","4:00","4:30","5:00","5:30","6:00","6:30","7:00","7:30","8:00","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30"};//导出列明
		String b[] = {"y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23","y24","y25","y26","y27","y28","y29","y30","y31","y32","y33","y34","y35","y36","y37","y38","y39","y40","y41","y42","y43","y44","y45","y46","y47"};//导出map中的key
		String gzb = "重点监控区域车辆分析";//导出sheet名和导出的文件名
		String msg = oneToTwoService.clmxinfo(postData);
		List<Map<String, Object>>list = DownloadAct.strlist2(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	
	
	/**
	 * .车辆实载率
	 * @return
	 */
	@RequestMapping(value = "/szl")
	@ResponseBody
	public String szl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = oneToTwoService.szl(postData);
		return msg;
	}
	
	/**
	 * 车辆实载率 导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("szlexcle")
	@ResponseBody
	public String szlexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "实载率分析";//导出sheet名和导出的文件名
		String msg = oneToTwoService.szl(postData);
		List<Map<String, Object>>list = downloadAct.str2list3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	
	/**
	 * .重点区域上线率分析
	 * @return
	 */
	@RequestMapping(value = "/zdqusxlfx")
	@ResponseBody
	public String zdqusxlfx(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = oneToTwoService.zdqusxlfx(postData);
		return msg;
	}
	
	/**
	 * 重点区域上线率分析 导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("sxlexcle")
	@ResponseBody
	public String sxlexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "重点区域上线率分析 ";//导出sheet名和导出的文件名
		String msg = oneToTwoService.zdqusxlfx(postData);
		List<Map<String, Object>>list = downloadAct.str2list3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	
	/**
	 * .重车率
	 * @return
	 */
	@RequestMapping(value = "/zcl")
	@ResponseBody
	public String zcl(HttpServletRequest request,
			@RequestParam("postData") String postData) {
		String msg = oneToTwoService.zcl(postData);
		return msg;
	}
	
	/**
	 * 重车率 导出
	 * @param request
	 * @param response
	 * @param postData
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("zclexcle")
	@ResponseBody
	public String zclexcle(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("postData") String postData) throws IOException{
		String a[] = {"时间","0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};//导出列明
		String b[] = {"message","y0","y1","y2","y3","y4","y5","y6","y7","y8","y9","y10","y11","y12","y13","y14","y15","y16","y17","y18","y19","y20","y21","y22","y23"};//导出map中的key
		String gzb = "重车率分析";//导出sheet名和导出的文件名
		String msg = oneToTwoService.zcl(postData);
		List<Map<String, Object>>list = downloadAct.str2list3(msg);//导出的数据
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
}
