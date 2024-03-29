package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.service.ZpsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * 抓拍数据
 * @author xianlehuang
 * @date : 2018年12月17日 上午9:41:00
 */
@Controller
@RequestMapping(value = "/zpsj")
public class ZpsjAction {
	@Autowired
	private ZpsjService zpsjservice;

	private DownloadAct downloadAct = new DownloadAct();
	
	/**
	 * 下拉栏
	 * @param request
	 * @param table
	 * @return
	 */
	@RequestMapping("/xll")
	@ResponseBody
	public String findxll(HttpServletRequest request,@RequestParam("field") String field,@RequestParam("table") String table) {
		String msg = zpsjservice.findxll(field,table);
		return msg;
	}
	/**
	 * 抓拍数据查询
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findzpsjcx")
	@ResponseBody
	public String findzpsjcx(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("address") String address,
							 @RequestParam("company") String company,
							 @RequestParam("type") String type,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findzpsjcx(stime,etime,vehicle,address,company,type,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 抓拍数据导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findzpsjcxdc")
	@ResponseBody
	public String findzpsjcxdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("address") String address,
							   @RequestParam("company") String company,
							   @RequestParam("type") String type,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"业户","车牌号","场站","时间"};//导出列明
		String b[] = {"COMPANY_NAME","VEHICLE_NO","ADDRESS","DBTIME"};//导出map中的key
		String gzb ="";
		if(type.equals("null")||type.equals("1")){
			gzb = "抓拍数据";//导出sheet名和导出的文件名
		}else{
//			gzb = "抓拍数据";//导出sheet名和导出的文件名
			gzb = "抓拍数据("+type.replace(":", "：")+")";//导出sheet名和导出的文件名
		}
		List<Map<String, Object>> list =zpsjservice.findzpsjcxdc(stime,etime,vehicle,address,company,type,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 场站流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findczlltj")
	@ResponseBody
	public String findczlltj(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("address") String address,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findczlltj(stime,etime,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 场站流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findczlltjdc")
	@ResponseBody
	public String findczlltjdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("address") String address,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"场站","数据量"};//导出列明
		String b[] = {"ADDRESS","SJL"};//导出map中的key
		String gzb = "场站流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findczlltjdc(stime,etime,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 日流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findrlltj")
	@ResponseBody
	public String findrlltj(HttpServletRequest request,
							@RequestParam("stime") String stime,
							@RequestParam("etime") String etime,
							@RequestParam("address") String address,
							@RequestParam("check") String check,
							@RequestParam("pageIndex") Integer pageIndex,
							@RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findrlltj(stime,etime,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 日流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findrlltjdc")
	@ResponseBody
	public String findrlltjdc(HttpServletRequest request,HttpServletResponse response,
							  @RequestParam("stime") String stime,
							  @RequestParam("etime") String etime,
							  @RequestParam("address") String address,
							  @RequestParam("check") String check) throws IOException {
		String a[] = {"时间","数量"};//导出列明
		String b[] = {"SJ","SJL"};//导出map中的key
		String gzb = "日流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findrlltjdc(stime,etime,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 分段流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findfdlltj")
	@ResponseBody
	public String findfdlltj(HttpServletRequest request,
							 @RequestParam("time") String time,
							 @RequestParam("address") String address,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findfdlltj(time,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 分段流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findfdlltjdc")
	@ResponseBody
	public String findfdlltjdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("time") String time,
							   @RequestParam("address") String address,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"场站","0-2","2-4","4-6","6-8","8-10","10-12","12-14","14-16","16-18","18-20","20-22","22-24"};//导出列明
		String b[] = {"ADDRESS","c0","c1","c2","c3","c4","c5","c6","c7","c8","c9","c10","c11"};//导出map中的key
		String gzb = "分段流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findfdlltjdc(time,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}

	/**
	 * 卡口抓拍数据查询
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findkkzpsjcx")
	@ResponseBody
	public String findkkzpsjcx(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("address") String address,
							 @RequestParam("company") String company,
							 @RequestParam("type") String type,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findkkzpsjcx(stime,etime,vehicle,address,company,type,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 卡口抓拍数据导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findkkzpsjcxdc")
	@ResponseBody
	public String findkkzpsjcxdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("address") String address,
							   @RequestParam("company") String company,
							   @RequestParam("type") String type,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"业户","车牌号","场站","时间"};//导出列明
		String b[] = {"COMPANY_NAME","VEHICLE_NO","ADDRESS","DBTIME"};//导出map中的key
		String gzb ="";
		if(type.equals("null")||type.equals("1")){
			gzb = "卡口抓拍数据";//导出sheet名和导出的文件名
		}else{
//			gzb = "抓拍数据";//导出sheet名和导出的文件名
			gzb = "卡口抓拍数据("+type.replace(":", "：")+")";//导出sheet名和导出的文件名
		}
		List<Map<String, Object>> list =zpsjservice.findkkzpsjcxdc(stime,etime,vehicle,address,company,type,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 卡口场站流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findkkczlltj")
	@ResponseBody
	public String findkkczlltj(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("address") String address,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findkkczlltj(stime,etime,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 卡口场站流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findkkczlltjdc")
	@ResponseBody
	public String findkkczlltjdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("address") String address,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"场站","数据量"};//导出列明
		String b[] = {"ADDRESS","SJL"};//导出map中的key
		String gzb = "卡口场站流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findkkczlltjdc(stime,etime,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 卡口日流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findkkrlltj")
	@ResponseBody
	public String findkkrlltj(HttpServletRequest request,
							@RequestParam("stime") String stime,
							@RequestParam("etime") String etime,
							@RequestParam("address") String address,
							@RequestParam("check") String check,
							@RequestParam("pageIndex") Integer pageIndex,
							@RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findkkrlltj(stime,etime,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 卡口日流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findkkrlltjdc")
	@ResponseBody
	public String findkkrlltjdc(HttpServletRequest request,HttpServletResponse response,
							  @RequestParam("stime") String stime,
							  @RequestParam("etime") String etime,
							  @RequestParam("address") String address,
							  @RequestParam("check") String check) throws IOException {
		String a[] = {"时间","数量"};//导出列明
		String b[] = {"SJ","SJL"};//导出map中的key
		String gzb = "卡口日流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findkkrlltjdc(stime,etime,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
	/**
	 * 卡口分段流量统计
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findkkfdlltj")
	@ResponseBody
	public String findkkfdlltj(HttpServletRequest request,
							 @RequestParam("time") String time,
							 @RequestParam("address") String address,
							 @RequestParam("check") String check,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = zpsjservice.findkkfdlltj(time,address,check,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 卡口分段流量统计导出
	 * @param request
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findkkfdlltjdc")
	@ResponseBody
	public String findkkfdlltjdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("time") String time,
							   @RequestParam("address") String address,
							   @RequestParam("check") String check) throws IOException {
		String a[] = {"场站","0-2","2-4","4-6","6-8","8-10","10-12","12-14","14-16","16-18","18-20","20-22","22-24"};//导出列明
		String b[] = {"ADDRESS","c0","c1","c2","c3","c4","c5","c6","c7","c8","c9","c10","c11"};//导出map中的key
		String gzb = "卡口分段流量统计";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = zpsjservice.findkkfdlltjdc(time,address,check);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
}
