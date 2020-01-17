package com.erxi.ms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;













//import cn.com.activeMQ.Sender;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.TlaqService;

/**
 * 信息服务
 * 
 * @author erxi
 * @date : 2018年9月25日 上午9:41:00
 */
@Controller
@RequestMapping(value = "/claq")
public class TlaqAction {

	@Autowired
	private TlaqService tlaqServer;

	private DownloadAct downloadAct = new DownloadAct();

	/**
	 * 特殊车辆查找
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/findswcz")
	@ResponseBody
	public String findswcz(HttpServletRequest request,
			@RequestParam("qd_stime") String qd_stime,
			@RequestParam("qd_etime") String qd_etime,
			@RequestParam("zd_stime") String zd_stime,
			@RequestParam("zd_etime") String zd_etime,
			@RequestParam("qd_jwd") String qd_jwd,
			@RequestParam("zd_jwd") String zd_jwd) {
		String msg = tlaqServer.findswcz(qd_stime, qd_etime, zd_stime,
				zd_etime, qd_jwd, zd_jwd);
		return msg;
	}

	/**
	 * 特殊车辆查找导出
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("findswczexcle")
	@ResponseBody
	public String findswczexcle(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response)
			throws IOException {
		System.out.println(data);
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String qd_stime = String.valueOf(paramMap.get("qd_stime"));
		String qd_etime = String.valueOf(paramMap.get("qd_etime"));
		String zd_stime = String.valueOf(paramMap.get("zd_stime"));
		String zd_etime = String.valueOf(paramMap.get("zd_etime"));
		String qd_jwd = String.valueOf(paramMap.get("qd_jwd"));
		String zd_jwd = String.valueOf(paramMap.get("zd_jwd"));
		String a[] = { "车牌号", "经纬度", "时间" };// 导出列明
		String b[] = { "vehi_no", "px", "stime" };// 导出map中的key
		String gzb = "车辆信息";// 导出sheet名和导出的文件名
		String msg = tlaqServer.findswcz1(qd_stime, qd_etime, zd_stime,
				zd_etime, qd_jwd, zd_jwd);
		 System.out.println(msg);
		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

	/**
	 * 轨迹
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findGj")
	@ResponseBody
	public String findGj(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("vehino") String vehino) {
		List<Map<String, Object>> list=tlaqServer.findGjww(stime, etime, vehino);
		Map<String, Object> map = new HashMap<String, Object> ();
		map.put("code", 0);
		map.put("data",list);
		map.put("msg","success");
		return FastJsonUtil.toJSONString(map);
//		return tlaqServer.findGj(stime, etime, vehino);
	}
	

	/**
	 * 轨迹导出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findGJexcle")
	@ResponseBody
	public String findGJexcle(HttpServletRequest request,
			@RequestParam("data") String data, HttpServletResponse response)
			throws IOException {
		Map<String, Object> paramMap = FastJsonUtil.stringToMap(data);
		String stime = String.valueOf(paramMap.get("stime"));
		String etime = String.valueOf(paramMap.get("etime"));
		String vehino = String.valueOf(paramMap.get("vehino"));
		String a[] = {"车牌号", "经度", "纬度", "手机号", "司机", "速度","空重车","总里程" };// 导出列明
		String b[] = {"vehicle_num", "PX", "PY","OWN_TEL", "OWN_NAME", "SPEED", "carstate", "LC"};// 导出map中的key
		String gzb = "轨迹"+etime.substring(5,19).replace("-","月").replace(":", "：").replace(" ", "日")+"至"+stime.substring(5,19).replace("-","月").replace(":", "：").replace(" ", "日");// 导出sheet名和导出的文件名
		List<Map<String, Object>> list = tlaqServer.findGjww(stime, etime, vehino);// 导出的数据
//		String a[] = { "业户", "车牌号", "经度", "纬度", "SIM", "手机号", "司机", "速度","空重车","总里程" };// 导出列明
//		String b[] = { "COMP_NAME", "vehicle_num", "PX", "PY", "VEHI_SIM","OWN_TEL", "OWN_NAME", "SPEED", "STATE", "LC"};// 导出map中的key
//		String gzb = "车辆信息";// 导出sheet名和导出的文件名
//		String msg = tlaqServer.findGj1(stime, etime, vehino);
//		List<Map<String, Object>> list = DownloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

	/**
	 * 所有车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyjk")
	@ResponseBody
	public String qyjk() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dateformat.format(System.currentTimeMillis()));
		String msg = tlaqServer.qyjk();
		System.out.println(dateformat.format(System.currentTimeMillis()));
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
		String msg = tlaqServer.qycomp();
		return msg;
	}

	/**
	 * 下拉所有车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyveh")
	@ResponseBody
	public String qyveh() {
		String msg = tlaqServer.qyveh();
		return msg;
	}
	
	/**
	 * 下拉所有车辆(异步加载)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyvehyb")
	@ResponseBody
	public String qyveh(HttpServletRequest request, @RequestParam("name") String name) {
		String msg = tlaqServer.qyveh();
		Map map =FastJsonUtil.stringToMap(msg);
		List<Map<String, Object>> list=(List<Map<String, Object>>) map.get("dataveh");
		if(!name.equals("")){			
			for(int i=0;i<list.size();i++){
				if(String.valueOf(list.get(i).get("PLATE_NUMBER")).indexOf(name)>-1){
				}else{
					list.remove(i);
					i--;
				}
			}
		}
		return FastJsonUtil.toJSONString(list);
	}
	

	/**
	 * 下拉所有区域
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qyarea")
	@ResponseBody
	public String qyarea() {
		String msg = tlaqServer.qyarea();
		return msg;
	}

	/**
	 * 重点关注车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zdgzcl")
	@ResponseBody
	public Result<List<Map<String, Object>>> zdgzcl() {
		return tlaqServer.zdgzcl();
	}

	/**
	 * 取消重点关注车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delVehi")
	@ResponseBody
	public Result<List<Map<String, Object>>> delVehi(
			HttpServletRequest request, @RequestParam("vehi") String vehi) {
		return tlaqServer.delVehi(vehi);
	}

	/**
	 * 取消所有重点关注车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delAllVehi")
	@ResponseBody
	public Result<List<Map<String, Object>>> delAllVehi() {
		return tlaqServer.delAllVehi();
	}

	/**
	 * 增加重点关注车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insVehi")
	@ResponseBody
	public Result<List<Map<String, Object>>> insVehi(
			HttpServletRequest request, @RequestParam("vehi") String vehi,
			@RequestParam("type") String type,
			@RequestParam("longi") String longi,
			@RequestParam("lati") String lati) {
		return tlaqServer.insVehi(vehi, type, longi, lati);
	}

	/**
	 * 导入信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/upload")
	@ResponseBody
	public Result<List<Map<String, Object>>> upload(
			@RequestParam("file") MultipartFile file)
			throws IllegalStateException, IOException {
		return tlaqServer.upload(file);
	}

	/**
	 * 滞留车辆查询
	 */
	@PostMapping("/cxVehi")
	@ResponseBody
	public Result<List> cxVehi(HttpServletRequest request,
			@RequestParam("time") String time,
			@RequestParam("between") String between,
			@RequestParam("type") String type,
			@RequestParam("speed") String speed,
			@RequestParam("area") String area) {
		return tlaqServer.cxVehi(time, between, type, speed, area);
	}

	/**
	 * 滞留车辆导出
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("cxVehidc")
	@ResponseBody
	public String cxVehidc(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("time") String time,
			@RequestParam("between") String between,
			@RequestParam("type") String type,
			@RequestParam("speed") String speed,
			@RequestParam("area") String area) throws IOException {
		String a[] = { "车牌", "时间", "所属公司" };// 导出列明
		String b[] = { "vehino", "dateTime", "compname" };// 导出map中的key
		String gzb = "滞留车辆表";// 导出sheet名和导出的文件名
		Result<List> lists = tlaqServer.cxVehi(time, between, type, speed, area);
		List<Map<String, Object>> list = DownloadAct.strlist(JSON
				.toJSONString(lists.getData()));// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

	/**
	 * 前三总览
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/three")
	@ResponseBody
	public String three() throws Exception {
		String msg = "ok";
		msg = tlaqServer.yyzl();
		return msg;
	}

	/**
	 * 前二总览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/two")
	@ResponseBody
	public String two() {
		String msg = "ok";
		msg = tlaqServer.yyzls();
		return msg;
	}

	/**
	 * 前二总览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/one")
	@ResponseBody
	public String one() {
		String msg = "ok";
		msg = tlaqServer.yyzlss();
		return msg;
	}

	/**
	 * 前二总览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/zero")
	@ResponseBody
	public String zero() {
		String msg = "ok";
		msg = tlaqServer.yyzlsss();
		return msg;
	}

	/**
	 * 前二总览
	 * 
	 * @return
	 */
	@RequestMapping(value = "/five")
	@ResponseBody
	public String five() {
		String msg = "ok";
		msg = tlaqServer.yyzlssss();
		return msg;
	}

	/**
	 * 报警管理
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/bjgl")
	@ResponseBody
	public Result<List<Map<String, Object>>> bjgl(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("stime") String stime,
			@RequestParam("etime") String etime, @RequestParam("cp") String cp,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) throws IOException {
		// String table = stime;//按时间改一下表名
		String table = "TB_ALARM_HISTORY_";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");   
		table = table+sdf.format(dt); 
		
		Result<List<Map<String, Object>>> msg = tlaqServer.bjgl(table, stime,
				etime, pageIndex, pageSize, cp);
		return msg;
	}
	
	/**
	 * 报警管理
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/spgl")
	@ResponseBody
	public Result<List<Map<String, Object>>> spgl(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("stime") String stime,
			@RequestParam("etime") String etime, @RequestParam("cp") String cp,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) throws IOException {
		// String table = stime;//按时间改一下表名
		String table = "TB_ALARM_HISTORY_";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");   
		table = table+sdf.format(dt); 
		Result<List<Map<String, Object>>> msg = tlaqServer.spgl(table, stime,
				etime, pageIndex, pageSize, cp);
		return msg;
	}
	

//	@RequestMapping("sendmsg")
//	@ResponseBody
//	public String sendmsg(HttpServletRequest request,
//			HttpServletResponse response, @RequestParam("zds") String zds,
//			@RequestParam("nr") String nr) throws IOException {
//		System.out.println(zds + "    " + nr);
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		map1.put("cmd", "0x8300");
//		map1.put("flag", "0");
//		map1.put("content", nr);
//		StringBuffer mdt_no = new StringBuffer();
//		String[] list = zds.split(",");
//		for (int i = 0; i < list.length; i++) {
//			mdt_no.append(list[i]);
//			mdt_no.append(",");
//			int a = (i + 1) % 100;
//			if (a == 0) {
//				mdt_no = mdt_no.deleteCharAt(mdt_no.length() - 1);
//				map1.put("isu", mdt_no);
//				String josn1 = FastJsonUtil.toJSONString(map1);
//				Sender.StartSend("192.168.0.102", "hz_taxi_905_gj", josn1);
//				mdt_no.delete(0, mdt_no.length());
//			}
//		}
//		if (mdt_no.length() > 0) {
//			mdt_no = mdt_no.deleteCharAt(mdt_no.length() - 1);
//			map1.put("isu", mdt_no);
//			String josn1 = FastJsonUtil.toJSONString(map1);
//			Sender.StartSend("192.168.0.102", "hz_taxi_905_gj", josn1);
//		}
//		return "{\"msg\":\"发送成功\"}";
//	}
	
	
	/**
	 * 权限管理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/qxgl")
	@ResponseBody
	public Result<List<Map<String, Object>>> qxgl(
			HttpServletRequest request, @RequestParam("username") String username) {
		return tlaqServer.qxgl(username);
	}
	
	/**
	 * 权限修改
	 */
	@RequestMapping(value = "/qxglEdit")
	@ResponseBody
	public Result<List<Map<String, Object>>> qxglEdit(
			HttpServletRequest request, 
			@RequestParam("id") String id,
			@RequestParam("qx") String qx,
			@RequestParam("qxname") String qxname) {
		return tlaqServer.qxglEdit(id,qx,qxname);
	}
	
	/**
	 * 用户上线
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/findyhsx")
	@ResponseBody
	public Result<List<Map<String, Object>>> findyhsx(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("stime") String stime,
			@RequestParam("etime") String etime, @RequestParam("name") String name,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) throws IOException {
		Result<List<Map<String, Object>>> msg = tlaqServer.findyhsx(stime, etime, name, pageIndex, pageSize);
		return msg;
	}
	
	/**
	 * 添加用户
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public Result<List<Map<String, Object>>> addUser(
			HttpServletRequest request, 
			@RequestParam("user") String user,
			@RequestParam("salt") String salt,
			@RequestParam("password") String password) {
		return tlaqServer.addUser(user,salt,password);
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping(value = "/editUser")
	@ResponseBody
	public Result<List<Map<String, Object>>> editUser(
			HttpServletRequest request, 
			@RequestParam("id") String id,
			@RequestParam("user") String user,
			@RequestParam("salt") String salt,
			@RequestParam("password") String password) {
		return tlaqServer.editUser(id,user,salt,password);
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/deleteUser")
	@ResponseBody
	public Result<List<Map<String, Object>>> deleteUser(
			HttpServletRequest request, 
			@RequestParam("id") String id) {
		return tlaqServer.deleteUser(id);
	}
	
	/**
	 * 重点抓拍车辆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/focusMonitor")
	@ResponseBody
	public String focusMonitor() {
		String msg = tlaqServer.focusMonitor();
		return msg;
	}
	
	/**
	 * 故障车辆抓拍
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faultMonitor")
	@ResponseBody
	public String faultMonitor() {
		String msg = tlaqServer.faultMonitor();
		return msg;
	}
}
