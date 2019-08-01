package com.erxi.ms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.TjfxService;

@Controller
@RequestMapping(value = "/tjfx")
public class TjfxAction {
	@Autowired
	private TjfxService tjfxService;
	
	private DownloadAct downloadAct = new DownloadAct();
	
	/**
	 * 驾驶员查询
	 * @param request
	 * @param sfzh
	 * @param cph
	 * @param xm
	 * @param gsm
	 * @param fwzh
	 * @param jyxkz
	 * @param status
	 * @param city
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/jsycx")
	@ResponseBody
	public Result<List<Map<String, Object>>> jsycx(HttpServletRequest request,
			@RequestParam("sfzh") String sfzh,
			@RequestParam("cph") String cph,
			@RequestParam("xm") String xm,
			@RequestParam("gsm") String gsm,
			@RequestParam("age") String age,
			@RequestParam("fwzh") String fwzh,
			@RequestParam("jyxkz") String jyxkz,
//			@RequestParam("status") String status,
			@RequestParam("city") String city,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.jsycx(sfzh, cph, xm, gsm, age,fwzh,jyxkz,
//				status,
				city,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 驾驶员导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/jsycxxlsx")
	@ResponseBody
	public String jsycxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String sfzh = String.valueOf(paramMap.get("sfzh"));
		String cph = String.valueOf(paramMap.get("cph"));
		String xm = String.valueOf(paramMap.get("xm"));
		String gsm = String.valueOf(paramMap.get("gsm"));
		String age = String.valueOf(paramMap.get("age"));
		String fwzh = String.valueOf(paramMap.get("fwzh"));
		String jyxkz = String.valueOf(paramMap.get("jyxkz"));
		String city = String.valueOf(paramMap.get("city"));
		String a[] = { "身份证号", "姓名", "业户名称","经营许可证号", "服务证号", "车牌号", "资格证有效期止","分值","证照状态" };// 导出列明
		String b[] = { "id_number", "name", "company_name","company_license_number", "server_card_num", "plate_number", "valid_period_end","assess_score","status_name" };// 导出map中的key
		String gzb = "驾驶员信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.jsycxxlsx(sfzh, cph, xm, gsm ,age ,fwzh,jyxkz,city);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	
	
	/**
	 * 车辆查询
	 * @param request
	 * @param cph
	 * @param xm
	 * @param city
	 * @param yszh
	 * @param jyxkzh
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/clcx")
	@ResponseBody
	public Result<List<Map<String, Object>>> clcx(HttpServletRequest request,
			@RequestParam("cph") String cph,
			@RequestParam("xm") String xm,
			@RequestParam("city") String city,
			@RequestParam("yszh") String yszh,
			@RequestParam("age") String age,
			@RequestParam("type") String type,
			@RequestParam("jyxkzh") String jyxkzh,
//			@RequestParam("status") String status,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.clcx( cph, xm, yszh,jyxkzh,age,type,
//				status,
				city,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 获取驾驶员信息
	 * @param request
	 * @param cph
	 * @return
	 */
	@RequestMapping("/jsyxx")
	@ResponseBody
	public Result<List<Map<String, Object>>> jsyxx(HttpServletRequest request,
			@RequestParam("cph") String cph
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.jsyxx(cph);
		return msg;
	}
	
	/**
	 * 投诉人信息
	 * @param request
	 * @param cph
	 * @return
	 */
	@RequestMapping("/tsrxx")
	@ResponseBody
	public Result<List<Map<String, Object>>> tsrxx(HttpServletRequest request,
			@RequestParam("cph") String cph
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.tsrxx(cph);
		return msg;
	}
	
	/**
	 * 行政处罚信息
	 * @param request
	 * @param cph
	 * @return
	 */
	@RequestMapping("/xzcfxx")
	@ResponseBody
	public Result<List<Map<String, Object>>> xzcfxx(HttpServletRequest request,
			@RequestParam("cph") String cph
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.xzcfxx(cph);
		return msg;
	}
	
	
	
	
	
	/**
	 * 车辆查询导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/clcxxlsx")
	@ResponseBody
	public String clcxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String cph = String.valueOf(paramMap.get("cph"));
		String xm = String.valueOf(paramMap.get("xm"));
		String city = String.valueOf(paramMap.get("city"));
		String yszh = String.valueOf(paramMap.get("yszh"));
		String age = String.valueOf(paramMap.get("age"));
		String type = String.valueOf(paramMap.get("type"));
		String jyxkzh = String.valueOf(paramMap.get("jyxkzh"));
		String a[] = { "车牌号", "车辆类型","品牌", "业户名称","经营许可证号", "道路运输证号", "核发日期", "有效日期","经营范围","状态" };// 导出列明
		String b[] = { "plate_number", "brand","vehicle_type_name", "company_name","company_license_number", "license_number", "license_valid_period_from", "license_valid_period_end","business_scope_name","status_name" };// 导出map中的key
		String gzb = "车辆信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.clcxxlsx( cph, xm, yszh,jyxkzh,age,type,city);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	
	
	
	/**
	 * 违章查询
	 * @param request
	 * @param stratTime
	 * @param endTime
	 * @param xm
	 * @param cph
	 * @param yszh
	 * @param jyxkzh
	 * @param area
	 * @param part
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/wzcx")
	@ResponseBody
	public Result<List<Map<String, Object>>> wzcx(HttpServletRequest request,
			@RequestParam("stratTime") String stratTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("xm") String xm,
			@RequestParam("cph") String cph,
			@RequestParam("yszh") String yszh,
			@RequestParam("jyxkzh") String jyxkzh,
			@RequestParam("area") String area,
			@RequestParam("part") String part,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.wzcx(stratTime, endTime,cph, xm, yszh,jyxkzh,area, part,pageIndex,pageSize);
		return msg;
	}
	
	@RequestMapping("/wzcxxlsx")
	@ResponseBody
	public String wzcxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String stratTime = String.valueOf(paramMap.get("stratTime"));
		String endTime = String.valueOf(paramMap.get("endTime"));
		String cph = String.valueOf(paramMap.get("cph"));
		String xm = String.valueOf(paramMap.get("xm"));
		String yszh = String.valueOf(paramMap.get("yszh"));
		String jyxkzh = String.valueOf(paramMap.get("jyxkzh"));
		String area = String.valueOf(paramMap.get("area"));
		String part = String.valueOf(paramMap.get("part"));
		
		String a[] = { "当事人姓名", "车号", "扣分","违章时间", "执法时间", "案件原因", "执法部门","执法区域","经营许可证号" };// 导出列明
		String b[] = { "PARTY_NAME", "AUTO_NUM", "INTEGRAL","ILLEGAL_TIME", "LEGAL_TIME", "CASE_REASON", "ORGAN","AREA","LIENCE_NUM" };// 导出map中的key
		String gzb = "违章信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.wzcxxlsx( stratTime, endTime,cph, xm, yszh,jyxkzh,area, part);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	
	
	/**
	 * 企业查询
	 * @param request
	 * @param name
	 * @param area
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/qycx")
	@ResponseBody
	public Result<List<Map<String, Object>>> qycx(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("area") String area,
			@RequestParam("style") String style,
			@RequestParam("max") String max,
			@RequestParam("min") String min,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.qycx( name, area,style,min,max,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 企业查询导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/qycxxlsx")
	@ResponseBody
	public String qycxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String name = String.valueOf(paramMap.get("name"));
		String area = String.valueOf(paramMap.get("area"));
		String style = String.valueOf(paramMap.get("style"));
		String min = String.valueOf(paramMap.get("min"));
		String max = String.valueOf(paramMap.get("max"));
		String a[] = { "业户名称", "规模(车辆数)", "区域","性质", "负责人", "经营许可证号", "联系方式","许可证起","许可证止", "发放日期","经营范围","经营状态" };// 导出列明
		String b[] = { "NAME", "VEHICLE_SUM", "AREA_NAME","ECONOMIC_NAME", "LEGAL_PERSON_NAME", "LICENSE_NUMBER", "RESPONSIBLE_PERSON_PHONE","LICENSE_VALID_PERIOD_FROM","LICENSE_VALID_PERIOD_END", "LICENSE_ISSUING_DATE","BUSINESS_SCOPE_NAME","BUSINESS_STATUS_NAME" };// 导出map中的key
		String gzb = "企业信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.qycxxlsx( name, area,style,min,max);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	
	/**
	 * 车载设备查询
	 * @param request
	 * @param name
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/czsbcx")
	@ResponseBody
	public Result<List<Map<String, Object>>> czsbcx(HttpServletRequest request,
			@RequestParam("cph") String cph,
			@RequestParam("yh") String yh,
			@RequestParam("zdlx") String zdlx,
			@RequestParam("zdbh") String zdbh,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.czsbcx(cph,yh,zdlx,zdbh,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 车载设备查询导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/czsbcxxlsx")
	@ResponseBody
	public String czsbcxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String cph = String.valueOf(paramMap.get("cph"));
		String yh = String.valueOf(paramMap.get("yh"));
		String zdlx = String.valueOf(paramMap.get("zdlx"));
		String zdbh = String.valueOf(paramMap.get("zdbh"));
		String a[] = { "业户名称", "车辆编号", "车辆号码","终端编号", "终端类型", "SIM卡号", "车主姓名","车主电话","白班电话","车辆颜色","终端型号","初装时间"};// 导出列明
		String b[] = { "BA_NAME", "VEHI_NUM", "VEHI_NO","MDT_NO", "MT_NAME", "SIM_NUM", "OWN_NAME","OWN_TEL","HOME_TEL","VC_NAME","MDT_SUB_TYPE","INST_TIME" };// 导出map中的key
		String gzb = "车辆终端设备信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.czsbcxxlsx( cph, yh, zdlx,zdbh);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	
//	/**
//	 * 故障设备查询
//	 * @param request
//	 * @param sbbh
//	 * @param pageIndex
//	 * @param pageSize
//	 * @return
//	 */
//	@RequestMapping("/gzsbcx")
//	@ResponseBody
//	public Result<List<Map<String, Object>>> gzsbcx(HttpServletRequest request,
//			@RequestParam("cph") String cph,
//			@RequestParam("yh") String yh,
//			@RequestParam("zdlx") String zdlx,
//			@RequestParam("zdbh") String zdbh,
//			@RequestParam("pageIndex") Integer pageIndex,
//			@RequestParam("pageSize") Integer pageSize
//			) {
//		Result<List<Map<String, Object>>> msg = tjfxService.gzsbcx(cph,yh,zdlx,zdbh,pageIndex,pageSize);
//		return msg;
//	}
//	
//	/**
//	 * 故障设备查询导出
//	 * @param request
//	 * @param data
//	 * @param response
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping("/gzsbcxxlsx")
//	@ResponseBody
//	public String gzsbcxxlsx(HttpServletRequest request,
//			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
//		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
//		String cph = String.valueOf(paramMap.get("cph"));
//		String yh = String.valueOf(paramMap.get("yh"));
//		String zdlx = String.valueOf(paramMap.get("zdlx"));
//		String zdbh = String.valueOf(paramMap.get("zdbh"));
//		String a[] = { "终端名称", "终端编号", "终端类型","车牌号", "业户名称", "故障类型", "故障时间" };// 导出列明
//		String b[] = { "MT_NAME", "MDT_NO", "MDT_SUB_TYPE","VEHI_NO", "COMP_NAME", "RM_ID", "RR_TIME" };// 导出map中的key
//		String gzb = "故障设备信息";// 导出sheet名和导出的文件名
//		String msg = tjfxService.gzsbcxxlsx( cph, yh, zdlx,zdbh);
//		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
//		downloadAct.download(request, response, a, b, gzb, list);
//		return null;
//	}
	
	/**
	 * 疑似绕路分析
	 * @param request
	 * @param stime
	 * @param etime
	 * @param cphm
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/ysrlfx")
	@ResponseBody
	public Result<List<Map<String, Object>>> ysrlfx(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("cphm") String cphm,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.ysrlfx( stime, etime,cphm,pageIndex,pageSize);
		return msg;
	}

	
	/**
	 * 异常营运分析
	 * @param request
	 * @param stime
	 * @param etime
	 * @param cphm
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/ycyyfx")
	@ResponseBody
	public Result<List<Map<String, Object>>> ycyyfx(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("cphm") String cphm,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.ycyyfx( stime, etime,cphm,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 营运里程异常分析 - 1
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yylcycfx")
	@ResponseBody
	public Result<List<Map<String, Object>>> yylcycfx(HttpServletRequest request,
			@RequestParam("maxMileage") Integer maxMileage,
			@RequestParam("minMileage") Integer minMileage,
			@RequestParam("startTime") String startTime
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.yylcycfx( maxMileage, minMileage,startTime);
		return msg;
	}
	
	/**
	 * 营运里程异常分析 - 2
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yylcycfxTwo")
	@ResponseBody
	public Result<List<Map<String, Object>>> yylcycfxTwo(HttpServletRequest request,
			@RequestParam("maxMileage") Integer maxMileage,
			@RequestParam("minMileage") Integer minMileage,
			@RequestParam("startTime") String startTime
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.yylcycfxTwo( maxMileage, minMileage,startTime);
		return msg;
	}
	/**
	 * 营运里程异常分析 - 2 -导出
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yylcycfxTwoexcle")
	@ResponseBody
	public String yylcycfxTwoexcle(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		Integer maxMileage = Integer.parseInt(String.valueOf(paramMap.get("maxMileage")));
		Integer minMileage = Integer.parseInt(String.valueOf(paramMap.get("minMileage")));
		String startTime = String.valueOf(paramMap.get("startTime"));
		String a[] = { "车牌", "里程", "日期","总公司", "分公司"};// 导出列明
		String b[] = { "CPHM", "S", "DAY","ZGS", "FGS"};// 导出map中的key
		String gzb = "营运里程异常分析";// 导出sheet名和导出的文件名
		String msg = tjfxService.yylcycfxTwoexcle( maxMileage, minMileage, startTime);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

	/**
	 * 营运单次里程分析
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yydcycfx")
	@ResponseBody
	public Result<List<Map<String, Object>>> yydcycfx(HttpServletRequest request,
			@RequestParam("maxMileage") Integer maxMileage,
			@RequestParam("minMileage") Integer minMileage,
			@RequestParam("startTime") String startTime
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.yydcycfx( maxMileage, minMileage,startTime);
		return msg;
	}
	
	
	/**
	 * 营运单次里程分析 -- 2
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yydcycfxTwo")
	@ResponseBody
	public Result<List<Map<String, Object>>> yydcycfxTwo(HttpServletRequest request,
			@RequestParam("maxMileage") Integer maxMileage,
			@RequestParam("minMileage") Integer minMileage,
			@RequestParam("startTime") String startTime
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.yydcycfxTwo( maxMileage, minMileage,startTime);
		return msg;
	}
	
	/**
	 * 营运单次里程分析 - 2 -导出
	 * @param request
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@RequestMapping("/yydcycfxTwoexcle")
	@ResponseBody
	public String yydcycfxTwoexcle(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		Integer maxMileage = Integer.parseInt(String.valueOf(paramMap.get("maxMileage")));
		Integer minMileage = Integer.parseInt(String.valueOf(paramMap.get("minMileage")));
		String startTime = String.valueOf(paramMap.get("startTime"));
		String a[] = { "车牌", "单次", "日期","总公司", "分公司"};// 导出列明
		String b[] = { "CPHM", "C", "DAY","ZGS", "FGS"};// 导出map中的key
		String gzb = "营运单次异常分析";// 导出sheet名和导出的文件名
		String msg = tjfxService.yydcycfxTwoexcle( maxMileage, minMileage, startTime);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	/**
	 * 燃油类型 -- 圆图
	 * @param request
	 * @return
	 */
	@RequestMapping("/rylx")
	@ResponseBody
	public Result<List<Map<String, Object>>> rylx(HttpServletRequest request
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.rylx();
		return msg;
	}
	
	
	/**
	 * 燃油类型 -- 表数据
	 * @param request
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/xny")
	@ResponseBody
	public Result<List<Map<String, Object>>> xny(HttpServletRequest request,
			@RequestParam("type") String type,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.xny( type,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 围栏进出分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/wl")
	@ResponseBody
	public Result<List<Map<String, Object>>> wl(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("wlmc") String wlmc,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		Result<List<Map<String, Object>>> msg = tjfxService.wl(wlmc,stime,etime,pageIndex,pageSize);
		return msg;
	}
	
	
	/**
	 * 围栏进出分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/wlDetail")
	@ResponseBody
	public Result<List<Map<String, Object>>> wlDetail(HttpServletRequest request,
			@RequestParam("type") String type,
			@RequestParam("name") String name,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		Result<List<Map<String, Object>>> msg = tjfxService.wlDetail(type,name, pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 围栏进出分析  -- 增加围栏
	 * @param request
	 * @param type
	 * @param name
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "/insertwl")
	@ResponseBody
	public Result<List<Map<String, Object>>> insertwl(
			HttpServletRequest request, @RequestParam("type") String type,
			@RequestParam("name") String name,
			@RequestParam("area") String area) {
		return tjfxService.insertwl(type, name, area);
	}
	
	/**
	 * 取消重点关注车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/wldel")
	@ResponseBody
	public Result<List<Map<String, Object>>> wldel(
			HttpServletRequest request, @RequestParam("id") String id) {
		return tjfxService.wldel(id);
	}
	
	
	/**
	 * 车载设备故障查询
	 * @param request
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param gz
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
//	@RequestMapping("/czsbgzcx")
//	@ResponseBody
//	public Result<List<Map<String, Object>>> czsbgzcx(HttpServletRequest request,
//			@RequestParam("cph") String cph,
//			@RequestParam("xm") String xm,
//			@RequestParam("yc") String yc,
//			@RequestParam("gz") String gz,
//			@RequestParam("stime") String stime,
//			@RequestParam("etime") String etime,
//			@RequestParam("pageIndex") Integer pageIndex,
//			@RequestParam("pageSize") Integer pageSize) {
//		Result<List<Map<String, Object>>> msg = tjfxService.czsbgzcx(cph,xm,yc,gz,stime,etime,pageIndex,pageSize);
//		return msg;
//	}
	
	@RequestMapping("/czsbgzcx")
	@ResponseBody
	public Result<List<Map<String, Object>>> czsbgzcx(HttpServletRequest request,
			@RequestParam("cph") String cph,
			@RequestParam("xm") String xm,
			@RequestParam("comp") String comp,
			@RequestParam("gz") String gz,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		
		Result<List<Map<String, Object>>> msg = tjfxService.czsbgzcx(cph,xm,comp,gz,stime,etime,pageIndex,pageSize);
		return msg;
	}
	
	
	
	
	
	
	/**
	 * 车载设备故障查询导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/czsbgzcxxlsx")
	@ResponseBody
//	public String czsbgzcxxlsx(HttpServletRequest request,
//			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
//		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
//		String cph = String.valueOf(paramMap.get("cph"));
//		String xm = String.valueOf(paramMap.get("xm"));
//		String yc = String.valueOf(paramMap.get("yc"));
//		String gz = String.valueOf(paramMap.get("gz"));
//		String stime = String.valueOf(paramMap.get("stime"));
//		String etime = String.valueOf(paramMap.get("etime"));
//		String a[] = { "车牌号", "故障类型", "故障时间"};// 导出列明
//		String b[] = { "VEHICLE_NO", "TYPE", "DBTIME" };// 导出map中的key
//		String gzb = "车载设备故障信息";// 导出sheet名和导出的文件名
//		String msg = tjfxService.czsbgzcxxlsx(cph,xm,yc,gz,stime,etime);
//		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
//		downloadAct.download(request, response, a, b, gzb, list);
//		return null;
//	}
	public String czsbgzcxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String cph = String.valueOf(paramMap.get("cph"));
		String xm = String.valueOf(paramMap.get("xm"));
		String comp = String.valueOf(paramMap.get("comp"));
		String gz = String.valueOf(paramMap.get("gz"));
		String stime = String.valueOf(paramMap.get("stime"));
		String etime = String.valueOf(paramMap.get("etime"));
		String a[] = { "车牌号", "业户名称","故障类型","故障时间","有营运无定位","有定位无营运","有抓拍无定位无营运","7天无定位无营运","全天空车全天重车","视频黑屏","视频移位","视频断线"};// 导出列明
		String b[] = { "VEHICLE_NO","COMP_NAME", "TYPE", "DB_TIME","NO_GPS","NO_JJQ","NO_GPS_JJQ","SEVEN_GPS_JJQ","EMPTY_HEAVY","SCREEN_BLACK","MOVE_ON","BREAK_OFF" };// 导出map中的key
		String gzb = "车载设备故障信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.czsbgzcxxlsx(cph,xm,comp,gz,stime,etime);
		
		System.out.println(msg);
		
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	/**
	 * 车载设备故障统计
	 * @param request
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param stime
	 * @param etime
	 * @return
	 */
//	@RequestMapping("/czsbgztj")
//	@ResponseBody
//	public Result<List<Map<String, Object>>> czsbgztj(HttpServletRequest request,
//			@RequestParam("cph") String cph,
//			@RequestParam("xm") String xm,
//			@RequestParam("yc") String yc,
//			@RequestParam("stime") String stime,
//			@RequestParam("etime") String etime) {
//		Result<List<Map<String, Object>>> msg = tjfxService.czsbgztj(cph,xm,yc,stime,etime);
//		return msg;
//	}
//	
	@RequestMapping("/czsbgztj")
	@ResponseBody
	public Result<List<Map<String, Object>>> czsbgztj(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime) {
		Result<List<Map<String, Object>>> msg = tjfxService.czsbgztj(stime,etime);
		return msg;
	}
	
	
	/**
	 * 车载设备故障统计导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/czsbgztjxlsx")
	@ResponseBody
//	public String czsbgztjxlsx(HttpServletRequest request,
//			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
//		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
//		String cph = String.valueOf(paramMap.get("cph"));
//		String xm = String.valueOf(paramMap.get("xm"));
//		String yc = String.valueOf(paramMap.get("yc"));
//		String stime = String.valueOf(paramMap.get("stime"));
//		String etime = String.valueOf(paramMap.get("etime"));
//		String a[] = {"终端主电源欠压","主电源掉电", "无定位数据","无数据上传","定位模块故障", "天线短路","非精确定位", "通讯故障", "定位回传过密","回传数据丢失", "摄像头遮挡", "摄像头信号丢失","硬盘故障", "SD卡故障", "视频主机故障","视频拓展故障", "计价器连接断开", "导航屏断开", "空重车不变化", "空重车切换频繁"};// 导出列明
//		String b[] = { "LOW_VOLTAGE", "NO_POWER", "NO_GPS","NO_UPLOAD", "MOD_FAULT", "ANT_FAULT","INEXACT", "COMM_FAULT", "GPS_OVER_BACK","GPS_NO_BACK", "CAM_OCCLUSION", "CAM_NOSIGN","HD_FAULT", "SD_FAULT", "VD_FAULT","VD_EX_FAULT", "METER_DISCONN", "NAV_DISCONN","ST_NO_CHG", "ST_OVER_CHG" };// 导出map中的key
//		String gzb = "车载设备故障信息";// 导出sheet名和导出的文件名
//		String msg = tjfxService.czsbgztjxlsx(cph,xm,yc,stime,etime);
//		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
//		downloadAct.download(request, response, a, b, gzb, list);
//		return null;
//	}
	public String czsbgztjxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String stime = String.valueOf(paramMap.get("stime"));
		String etime = String.valueOf(paramMap.get("etime"));
		String a[] = { "有营运无定位", "有定位无营运", "有抓拍无定位无营运","7天无定位无营运","全天空车全天重车"};// 导出列明
		String b[] = { "NO_GPS", "NO_JJQ","NO_GPS_JJQ","SEVEN_GPS_JJQ","EMPTY_HEAVY" };// 导出map中的key
		String gzb = "车载设备故障信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.czsbgztjxlsx(stime,etime);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	/**
	 * 设备故障次数统计
	 * @param request
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param stime
	 * @param etime
	 * @return
	 */
	@RequestMapping("/sbgzcstj")
	@ResponseBody
	public Result<List<Map<String, Object>>> sbgzcstj(HttpServletRequest request,
			@RequestParam("cph") String cph,
			@RequestParam("gz") String gz,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.sbgzcstj(cph,gz,stime,etime,pageIndex,pageSize);
		return msg;
	}
	
	
	/**
	 * 设备故障次数统计导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/sbgzcstjxlsx")
	@ResponseBody
	public String sbgzcstjxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String cph = String.valueOf(paramMap.get("cph"));
		String gz = String.valueOf(paramMap.get("gz"));
		String stime = String.valueOf(paramMap.get("stime"));
		String etime = String.valueOf(paramMap.get("etime"));
		String a[] = { "车牌号", "业户名称", "故障类型","故障次数"};// 导出列明
		String b[] = { "VEHICLE_NO", "COMP_NAME","TYPE","r" };// 导出map中的key
		String gzb = "设备故障次数统计";// 导出sheet名和导出的文件名
		String msg = tjfxService.sbgzcstjxlsx(cph,gz,stime,etime);
		
		System.out.println(msg);
		
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	/**
	 * 投诉查询
	 * @param request
	 * @param name
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/tscx")
	@ResponseBody
	public Result<List<Map<String, Object>>> tscx(HttpServletRequest request,
			@RequestParam("lx") String lx,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize
			) {
		Result<List<Map<String, Object>>> msg = tjfxService.tscx(lx,stime,etime,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 投诉查询导出
	 * @param request
	 * @param data
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/tscxxlsx")
	@ResponseBody
	public String tscxxlsx(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response) throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String lx = String.valueOf(paramMap.get("lx"));
		String stime = String.valueOf(paramMap.get("stime"));
		String etime = String.valueOf(paramMap.get("etime"));
		String a[] = { "投诉人", "投诉类型", "联系方式","投诉车辆", "处理详情", "投诉时间", "处理时间"};// 导出列明
		String b[] = { "CALL_NAME", "BUSINESS_ITEMTYPE_NAME", "CALLER_ID","VEHICLE_PLATE_NUMBER", "BUSINESS_STATUS_NAME", "HAPPEN_TIME", "ACCEPT_TIME" };// 导出map中的key
		String gzb = "投诉信息";// 导出sheet名和导出的文件名
		String msg = tjfxService.tscxxlsx(lx,stime,etime);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}
	
	

	
}
