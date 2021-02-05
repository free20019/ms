package com.erxi.ms.controller;

import com.erxi.ms.annotation.SystemControllerLog;
import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.service.YqcxService;
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
 * 疫情查询
 * @author xianlehuang
 * @date : 2020年02月12 下午2:41:00
 */
@Controller
@RequestMapping(value = "/yqcx")
public class YqcxAction {
	@Autowired
	private YqcxService yqcxService;

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
		String msg = yqcxService.findxll(field,table);
		return msg;
	}

	/**
	 * 乘坐信息查询
	 */
	@RequestMapping("/findczxxcx")
	@ResponseBody
	public String findczxxcx(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("phone") String phone,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = yqcxService.findczxxcx(stime,etime,vehicle,company,phone,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 乘坐信息查询导出
	 */
	@RequestMapping("/findczxxcxdc")
	@ResponseBody
	public String findczxxcxdc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("company") String company,
			@RequestParam("phone") String phone) throws IOException {
		String a[] = {"车牌号","所属公司","司机姓名","司机电话","登记时间","乘客手机号","乘客姓名"};//导出列明
		String b[] = {"CPHM","COMPANY_NAME","SJXM","SJDH","DJSJ","CKDH","CKXM"};//导出map中的key
		String gzb = "乘坐信息查询";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = yqcxService.findczxxcxdc(stime,etime,vehicle,company,phone);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}

	/**
	 * 乘坐信息查询(敏感)
	 */
	@SystemControllerLog(description = "乘坐信息查询(敏感)")
	@RequestMapping("/findczxxcxmg")
	@ResponseBody
	public String findczxxcxmg(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("company") String company,
							 @RequestParam("phone") String phone,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = yqcxService.findczxxcxmg(stime,etime,vehicle,company,phone,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 乘坐信息查询(敏感)导出
	 */
	@SystemControllerLog(description = "乘坐信息查询(敏感)导出")
	@RequestMapping("/findczxxcxmgdc")
	@ResponseBody
	public String findczxxcxmgdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("company") String company,
							   @RequestParam("phone") String phone) throws IOException {
		String a[] = {"车牌号","所属公司","司机姓名","司机电话","登记时间","乘客手机号","乘客姓名"};//导出列明
		String b[] = {"CPHM","COMPANY_NAME","SJXM","SJDH","DJSJ","CKDH","CKXM"};//导出map中的key
		String gzb = "乘坐信息查询(敏感)";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = yqcxService.findczxxcxmgdc(stime,etime,vehicle,company,phone);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}

	/**
	 * 登记数据分析
	 */
	@RequestMapping("/finddjsjfx")
	@ResponseBody
	public String finddjsjfx(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("company") String company,
							 @RequestParam("value") String value,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = yqcxService.finddjsjfx(stime,etime,vehicle,company,value,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 登记数据分析导出
	 */
	@RequestMapping("/finddjsjfxdc")
	@ResponseBody
	public String finddjsjfxdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("company") String company,
							   @RequestParam("value") String value) throws IOException {
		String a[] = {"车牌号","所属公司","登记次数","营运记录数","差值"};//导出列明
		String b[] = {"CPHM","COMPANY_NAME","DJCS","YYJLS","CZ"};//导出map中的key
		String gzb = "登记数据分析";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = yqcxService.finddjsjfxdc(stime,etime,vehicle,company,value);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}

	/**
	 * 乘客确诊溯源
	 */
	@RequestMapping("/findckqzsy")
	@ResponseBody
	public String findckqzsy(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("phone") String phone) {
		String msg = yqcxService.findckqzsy(stime,etime,phone);
		return msg;
	}
	/**
	 * 乘客确诊溯源导出
	 */
	@RequestMapping("/findckqzsydc")
	@ResponseBody
	public String findckqzsydc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("type") String type,
							   @RequestParam("phone") String phone) throws IOException {
		if(type.equals("1")){
			String a[] = {"车牌号","所属公司","登记时间","司机姓名","司机电话","填写类型"};//导出列明
			String b[] = {"CPHM","COMPANY_NAME","DJSJ","SJXM","SJDH","CKRS"};//导出map中的key
			String gzb = "司机确诊溯源(司机明细)";//导出sheet名和导出的文件名
			List<Map<String, Object>> list = yqcxService.findckqzsydc(stime,etime,phone,type);
			downloadAct.download(request,response,a,b,gzb,list);
		}else if(type.equals("2")){
			String a[] = {"乘客手机号","乘客姓名","登记时间","填写类型"};//导出列明
			String b[] = {"CKDH","CKXM","DJSJ","CKRS"};//导出map中的key
			String gzb = "司机确诊溯源(乘客明细)";//导出sheet名和导出的文件名
			List<Map<String, Object>> list = yqcxService.findckqzsydc(stime,etime,phone,type);
			downloadAct.download(request,response,a,b,gzb,list);
		}
		return null;
	}

	/**
	 * 司机确诊溯源
	 */
	@RequestMapping("/findsjqzsy")
	@ResponseBody
	public String findsjqzsy(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("phone") String phone) {
		String msg = yqcxService.findsjqzsy(stime,etime,vehicle,phone);
		return msg;
	}
	/**
	 * 司机确诊溯源导出
	 */
	@RequestMapping("/findsjqzsydc")
	@ResponseBody
	public String findsjqzsydc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("phone") String phone,
							   @RequestParam("type") String type) throws IOException {
		if(type.equals("1")){
			String a[] = {"乘客手机号","乘客姓名","登记时间","填写类型"};//导出列明
			String b[] = {"CKDH","CKXM","DJSJ","CKRS"};//导出map中的key
			String gzb = "司机确诊溯源(乘客明细)";//导出sheet名和导出的文件名
			List<Map<String, Object>> list = yqcxService.findsjqzsydc(stime,etime,vehicle,phone,type);
			downloadAct.download(request,response,a,b,gzb,list);
		}else if(type.equals("2")){
			String a[] = {"车牌号","所属公司","登记时间","司机姓名","司机电话","填写类型"};//导出列明
			String b[] = {"CPHM","COMPANY_NAME","DJSJ","SJXM","SJDH","CKRS"};//导出map中的key
			String gzb = "司机确诊溯源(司机明细)";//导出sheet名和导出的文件名
			List<Map<String, Object>> list = yqcxService.findsjqzsydc(stime,etime,vehicle,phone,type);
			downloadAct.download(request,response,a,b,gzb,list);
		}
		return null;
	}

	/**
	 * 登记异常分析
	 */
	@RequestMapping("/finddjycfx")
	@ResponseBody
	public String finddjycfx(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("vehicle") String vehicle,
							 @RequestParam("value") String value,
                             @RequestParam("company") String company,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = yqcxService.finddjycfx(stime,etime,vehicle,value,company,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 登记异常分析导出
	 */
	@RequestMapping("/finddjycfxdc")
	@ResponseBody
	public String finddjycfxdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("vehicle") String vehicle,
							   @RequestParam("value") String value,
                               @RequestParam("company") String company) throws IOException {
		String a[] = {"车牌号","所属公司","有营运无登记","有定位无登记","有卡口记录无登记"};//导出列明
		String b[] = {"VEHICLE_NO","COMPANY_NAME","JJQ_NDJ","GPS_NYY_NDJ","NGPS_NJJQ_NDJ_ZP"};//导出map中的key
		String gzb = "登记异常分析";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = yqcxService.finddjycfxdc(stime,etime,vehicle,value,company);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}

	/**
	 * 信息总览
	 */
	@RequestMapping("/findTodayInformation")
	@ResponseBody
	public String findTodayInformation(HttpServletRequest request) {
		String msg = yqcxService.findTodayInformation();
		return msg;
	}

	/**
	 * 操作日志
	 */
	@RequestMapping("/findczrz")
	@ResponseBody
	public String findczrz(HttpServletRequest request,
							 @RequestParam("stime") String stime,
							 @RequestParam("etime") String etime,
							 @RequestParam("value") String value,
							 @RequestParam("pageIndex") Integer pageIndex,
							 @RequestParam("pageSize") Integer pageSize) {
		String msg = yqcxService.findczrz(stime,etime,value,pageIndex,pageSize);
		return msg;
	}

	/**
	 * 操作日志导出
	 */
	@RequestMapping("/findczrzdc")
	@ResponseBody
	public String findczrzdc(HttpServletRequest request,HttpServletResponse response,
							   @RequestParam("stime") String stime,
							   @RequestParam("etime") String etime,
							   @RequestParam("value") String value) throws IOException {
//		String a[] = {"操作描述","操作ip","用户","时间","方法名","方法参数","sql","结果"};//导出列明
//		String b[] = {"ACTIONDES","ACTIONIP","USERNAME","ACTIONTIME","METHODNAME","METHODPARAMS","ACTIONSQL","RESULT"};//导出map中的key
		String a[] = {"操作描述","操作ip","用户","时间","方法名","方法参数","结果"};//导出列明
		String b[] = {"ACTIONDES","ACTIONIP","USERNAME","ACTIONTIME","METHODNAME","METHODPARAMS","RESULT"};//导出map中的key
		String gzb = "操作日志";//导出sheet名和导出的文件名
		List<Map<String, Object>> list = yqcxService.findczrzdc(stime,etime,value);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
	}
}
