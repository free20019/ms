package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.TjfxDao;
import com.erxi.ms.result.CodeMsg;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
@Service
public class TjfxService {
	@Autowired
	TjfxDao tjfxDao;

	@DS("datasource1")
	public Result<List<Map<String, Object>>> jsycx(String sfzh, String cph,
			String xm, String gsm,String age, String fwzh, String jyxkz, 
//			String status,
			String city, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getJsy(sfzh, cph, xm, gsm,age,fwzh,jyxkz,
//				status, 
				city,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
	public String jsycxxlsx(String sfzh, String cph, String xm, String gsm,String age,
			String fwzh, String jyxkz, String city) {
		List<Map<String, Object>> list = tjfxDao.getJsyxlsx(sfzh, cph, xm, gsm,age,fwzh,jyxkz,
				city);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("plate_number", String.valueOf(list.get(i).get("plate_number")).equals("")?"":String.valueOf(list.get(i).get("plate_number")).replace("null","").replace(".",""));
			list.get(i).put("valid_period_end",  String.valueOf(list.get(i).get("valid_period_end")));
		}
		return FastJsonUtil.toJSONString(list);
	}
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> clcx(String cph, String xm,
			String yszh, String jyxkzh, String age, String type,
//			String status, 
			String city,Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getCl(cph, xm,yszh,jyxkzh,age,type,
//				status,
				city,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
//		for(int i=0;i<list.size();i++){
//			if(list.get(i).get("BUSINESS_SCOPE_NAME")!=null){
//				String scope=list.get(i).get("BUSINESS_SCOPE_NAME").toString();
//				if(scope.equals("网络预约出租汽车客运。")||scope.equals("客运：网络预约出租汽车客运。")){
//					scope="";
//				}
//				list.get(i).put("BUSINESS_SCOPE_NAME", scope);
//			}
//		}
		for(int i=0;i<list.size();i++){
			if(list.get(i).containsKey("business_scope_name")){
				String scope=String.valueOf(list.get(i).get("business_scope_name"));
				if(scope.equals("网络预约出租汽车客运。")||scope.equals("客运：网络预约出租汽车客运。")){
					scope="";
				}
				list.get(i).put("business_scope_name", scope);
			}
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
	public String clcxxlsx(String cph, String xm,
			String yszh, String jyxkzh,  String age, String type,
//			String status, 
			String city) {
		List<Map<String, Object>> list = tjfxDao.getClxlsx(cph, xm,yszh,jyxkzh,age,type,
//				status,
				city);
		for(int i=0;i<list.size();i++){
			if(list.get(i).containsKey("business_scope_name")){
				String scope=String.valueOf(list.get(i).get("business_scope_name"));
				if(scope.equals("网络预约出租汽车客运。")||scope.equals("客运：网络预约出租汽车客运。")){
					scope="";
				}
				list.get(i).put("business_scope_name", scope);
			}
			
			list.get(i).put("license_valid_period_from", String.valueOf(list.get(i).get("license_valid_period_from")));
			list.get(i).put("license_valid_period_end", String.valueOf(list.get(i).get("license_valid_period_end")));
		}
		return FastJsonUtil.toJSONString(list);
	}
	

	@DS("datasource1")
	public Result<List<Map<String, Object>>> wzcx(String stratTime,
			String endTime, String cph, String xm, String yszh, String jyxkzh,
			String area, String part, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getWz(stratTime,endTime,cph, xm,yszh,jyxkzh,
				area, part,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
	public String wzcxxlsx(String stratTime,
			String endTime, String cph, String xm, String yszh, String jyxkzh,
			String area, String part) {
		List<Map<String, Object>> list = tjfxDao.getWzxlsx(stratTime,endTime,cph, xm,yszh,jyxkzh,
				area, part);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("AUTO_NUM", list.get(i).get("AUTO_NUM").toString().replace(".",""));
			list.get(i).put("ILLEGAL_TIME", list.get(i).get("ILLEGAL_TIME").toString().substring(0,19));
			list.get(i).put("LEGAL_TIME", list.get(i).get("LEGAL_TIME").toString().substring(0,19));
		}
		return FastJsonUtil.toJSONString(list);
	}
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> qycx(String name, String area,
			String style, String min, String max, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getQy(name,area,style,min,max,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
	public String qycxxlsx(String name, String area,
			String style, String min, String max) {
		List<Map<String, Object>> list = tjfxDao.getQyxlsx(name,area,style,min,max);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("LICENSE_VALID_PERIOD_FROM", list.get(i).get("LICENSE_VALID_PERIOD_FROM").toString().substring(0,19));
			list.get(i).put("LICENSE_VALID_PERIOD_END", list.get(i).get("LICENSE_VALID_PERIOD_END").toString().substring(0,19));
			list.get(i).put("LICENSE_ISSUING_DATE", list.get(i).get("LICENSE_ISSUING_DATE").toString().substring(0,19));
		}
		return FastJsonUtil.toJSONString(list);
	}
	
	@DS("datasource1")
//	@DS("datasource3")
	public Result<List<Map<String, Object>>> czsbcx(String cph,
			String yh, String zdlx, String zdbh, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getCzsb(cph,yh,zdlx,zdbh,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
//	@DS("datasource3")
	public String czsbcxxlsx(String cph, String yh, String zdlx, String zdbh) {
		List<Map<String, Object>> list = tjfxDao.getCzsbcxxlsx(cph,yh,zdlx,zdbh);
		if( list!=null && list.size() >0){
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("INST_TIME",String.valueOf(list.get(i).get("INST_TIME")));
			}
		}
		return FastJsonUtil.toJSONString(list);
	}
	
//	@DS("datasource1")
//	public Result<List<Map<String, Object>>> gzsbcx(String cph,
//			String yh, String zdlx, String zdbh, Integer pageIndex, Integer pageSize) {
//		List<Map<String, Object>> list = tjfxDao.getGzsb(cph,yh,zdlx,zdbh,pageIndex,pageSize);
//		int count = 0;
//		if( list!=null && list.size() >0){
//			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
//		}
//		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
//		Map  map = new HashMap ();
//		map.put("count", count);
//		map.put("datas",list);
//		lists.add(map);
//		return Result.success(lists);
//	}
//
//	@DS("datasource1")
//	public String gzsbcxxlsx(String cph, String yh, String zdlx, String zdbh) {
//		List<Map<String, Object>> list = tjfxDao.getGzsbxlsx(cph,yh,zdlx,zdbh);
//		for (int i = 0; i < list.size(); i++) {
//			list.get(i).put("RR_TIME", list.get(i).get("RR_TIME").toString().substring(0,19));
//		}
//		return FastJsonUtil.toJSONString(list);
//	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> ysrlfx(String stime, String etime,
			String cphm, Integer pageIndex, Integer pageSize) {
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00"); 
		List<Map<String, Object>> list = tjfxDao.getYsrlfx(stime,etime,cphm,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> lists1 = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			//计价器里程
//			double jjq = Double.parseDouble(list.get(i).get("J_MILE").toString());
//			//程序计算里程(GPS里程)
			double gps = list.get(i).get("S_MILE")==null?0.00:Double.parseDouble(list.get(i).get("S_MILE").toString());
			//速度最优里程(最短时间里程)
			double zy = list.get(i).get("R_MILE")==null?0.00:Double.parseDouble(list.get(i).get("R_MILE").toString());
			//距离最短里程
			double zd = list.get(i).get("R_MILE_2")==null?0.00:Double.parseDouble(list.get(i).get("R_MILE_2").toString());
			map.put("RDONE", df.format(gps/zd));
			map.put("RDTWO", df.format(gps/zy));
//			if( (jjq-zy)/zy >1.2){
//				map.put("RD", "1");
//			}else{
//				map.put("RD", "0");
//			}
			map.put("id", i+1);
			lists.add(map);
		}
		Map  maps = new HashMap ();
		maps.put("count", count);
		maps.put("datas",lists);
		lists1.add(maps);
		return Result.success(lists1);
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> ycyyfx(String stime, String etime,
			String cphm, Integer pageIndex, Integer pageSize) {
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#0.00"); 
		List<Map<String, Object>> list = tjfxDao.getYyycfx(stime,etime,cphm,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> lists1 = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			//计价器里程
			double jjq = list.get(i).get("J_MILE")==null?0.00:Double.parseDouble(list.get(i).get("J_MILE").toString());
//			//程序计算里程(GPS里程)
			double gps = list.get(i).get("S_MILE")==null?0.00:Double.parseDouble(list.get(i).get("S_MILE").toString());
			//速度最优里程(最短时间里程)
			map.put("RDONE", df.format(jjq/gps));
//			if( (jjq-zy)/zy >1.2){
//				map.put("RD", "1");
//			}else{
//				map.put("RD", "0");
//			}
			map.put("id", i+1);
			lists.add(map);
		}
		Map  maps = new HashMap ();
		maps.put("count", count);
		maps.put("datas",lists);
		lists1.add(maps);
		return Result.success(lists1);
	}

	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> yylcycfx(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYylcycfx(maxMileage,minMileage,startTime);
		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> yylcycfxTwo(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYylcycfxTwo(maxMileage,minMileage,startTime);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("CPHM", "浙"+list.get(i).get("CPHM").toString());
		}
		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public String yylcycfxTwoexcle(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYylcycfxTwo(maxMileage,minMileage,startTime);
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i).get("DAY").toString();
			list.get(i).put("DAY", s.substring(0,4)+"-"+s.substring(4,6)+"-"+s.substring(6,8));
			list.get(i).put("CPHM", "浙"+list.get(i).get("CPHM").toString());
		}
		return FastJsonUtil.toJSONString(list);
	}

	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> yydcycfx(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYydcycfx(maxMileage,minMileage,startTime);
		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}

	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> yydcycfxTwo(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYydcycfxTwo(maxMileage,minMileage,startTime);
		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public String yydcycfxTwoexcle(Integer maxMileage,
			Integer minMileage, String startTime) {
		List<Map<String, Object>> list = tjfxDao.getYydcycfxTwo(maxMileage,minMileage,startTime);
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i).get("DAY").toString();
			list.get(i).put("DAY", s.substring(0,4)+"-"+s.substring(4,6)+"-"+s.substring(6,8));
			list.get(i).put("CPHM", "浙"+list.get(i).get("CPHM").toString());
		}
		return FastJsonUtil.toJSONString(list);
	}
	

	@DS("datasource1")
	public Result<List<Map<String, Object>>> rylx() {
		List<Map<String, Object>> list = tjfxDao.getRylx();
		return Result.success(list);
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> xny(String type,
			Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getXny(type,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> wl(String wlmc,
			String stime, String etime, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getWl(wlmc,stime,etime,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("counts")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> wlDetail(String type,String name,
			Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getWlDetail(type,name,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> insertwl(String type, String name,
			String area) {
		int count = tjfxDao.insertwl(type,name,area);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}
	
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> wldel(String id) {
		System.out.println(id);
		int count = tjfxDao.wldel(id);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}
	
	@DS("datasource1")
//	@DS("datasource4")
//	public Result<List<Map<String, Object>>> czsbgzcx(String cph, String xm,
//			String yc, String gz, String stime, String etime, Integer pageIndex, Integer pageSize) {
//		List<Map<String, Object>> list = tjfxDao.getCzsbgzcx(cph,xm,yc,gz,stime,etime,pageIndex,pageSize);
//		int count = 0;
//		if( list!=null && list.size() >0){
//			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
//		}
//		for (int i = 0; i < list.size(); i++) {
//			StringBuffer tj = new StringBuffer("");
//			if(list.get(i).get("LOW_VOLTAGE").toString().equals("1")){
//				tj.append("终端主电源欠压;");
//			}
//			if(list.get(i).get("NO_POWER").toString().equals("1")){
//				tj.append("主电源掉电;");
//			}
//			if(list.get(i).get("NO_GPS").toString().equals("1")){
//				tj.append("无定位数据;");
//			}
//			if(list.get(i).get("NO_UPLOAD").toString().equals("1")){
//				tj.append("无数据上传;");
//			}
//			
//			if(list.get(i).get("MOD_FAULT").toString().equals("1")){
//				tj.append("定位模块故障;");
//			}
//			if(list.get(i).get("ANT_FAULT").toString().equals("1")){
//				tj.append("天线短路;");
//			}
//			if(list.get(i).get("INEXACT").toString().equals("1")){
//				tj.append("非精确定位;");
//			}
//			
//			if(list.get(i).get("COMM_FAULT").toString().equals("1")){
//				tj.append("通讯故障;");
//			}
//			
//			if(list.get(i).get("GPS_OVER_BACK").toString().equals("1")){
//				tj.append("定位回传过密;");
//			}
//			if(list.get(i).get("GPS_NO_BACK").toString().equals("1")){
//				tj.append("回传数据丢失;");
//			}
//			
//			if(list.get(i).get("CAM_OCCLUSION").toString().equals("1")){
//				tj.append("摄像头遮挡;");
//			}
//			if(list.get(i).get("CAM_NOSIGN").toString().equals("1")){
//				tj.append("摄像头信号丢失;");
//			}
//			
//			if(list.get(i).get("HD_FAULT").toString().equals("1")){
//				tj.append("硬盘故障;");
//			}
//			if(list.get(i).get("SD_FAULT").toString().equals("1")){
//				tj.append("SD卡故障;");
//			}
//			if(list.get(i).get("VD_FAULT").toString().equals("1")){
//				tj.append("视频主机故障;");
//			}
//			if(list.get(i).get("VD_EX_FAULT").toString().equals("1")){
//				tj.append("视频扩展故障;");
//			}
//			
//			if(list.get(i).get("METER_DISCONN").toString().equals("1")){
//				tj.append("计价器连接断开;");
//			}
//			
//			if(list.get(i).get("NAV_DISCONN").toString().equals("1")){
//				tj.append("导航屏断开;");
//			}
//			
//			if(list.get(i).get("ST_NO_CHG").toString().equals("1")){
//				tj.append("空重车不变化;");
//			}
//			if(list.get(i).get("ST_OVER_CHG").toString().equals("1")){
//				tj.append("空重车切换频繁;");
//			}
//			list.get(i).put("TYPE",tj);
//		}
//		
//		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
//		Map  map = new HashMap ();
//		map.put("count", count);
//		map.put("datas",list);
//		lists.add(map);
//		return Result.success(lists);
//	}
	
	public Result<List<Map<String, Object>>> czsbgzcx(String cph, String xm,String comp,
			 String gz, String stime, String etime, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getCzsbgzcx(cph,xm,comp,gz,stime,etime,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		for (int i = 0; i < list.size(); i++) {
			StringBuffer tj = new StringBuffer("");
			if(list.get(i).get("NO_GPS").toString().equals("1")){
				tj.append("有营运无定位");
			}
			if(list.get(i).get("NO_JJQ").toString().equals("1")){
				tj.append("有定位无营运");
			}
			if(list.get(i).get("NO_GPS_JJQ").toString().equals("1")){
				tj.append("有抓拍无定位无营运");
			}
			if(list.get(i).get("SEVEN_GPS_JJQ").toString().equals("1")){
				tj.append("7天无定位无营运");
			}
			if(list.get(i).get("EMPTY_HEAVY").toString().equals("1")){
				tj.append("全天空车全天重车");
			}
			list.get(i).put("TYPE",tj);
		}
		
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}
	
	
	@DS("datasource1")
//	@DS("datasource4")
//	public String czsbgzcxxlsx(String cph, String xm, String yc, String gz,
//			String stime, String etime) {
//		List<Map<String, Object>> list = tjfxDao.getCzsbgzcxxlsx(cph,xm,yc,gz,stime,etime);
//		for (int i = 0; i < list.size(); i++) {
//			list.get(i).put("DBTIME", list.get(i).get("DBTIME").toString().substring(0,19));
//			StringBuffer tj = new StringBuffer("");
//			if(list.get(i).get("LOW_VOLTAGE").toString().equals("1")){
//				tj.append("终端主电源欠压;");
//			}
//			if(list.get(i).get("NO_POWER").toString().equals("1")){
//				tj.append("主电源掉电;");
//			}
//			if(list.get(i).get("NO_GPS").toString().equals("1")){
//				tj.append("无定位数据;");
//			}
//			if(list.get(i).get("NO_UPLOAD").toString().equals("1")){
//				tj.append("无数据上传;");
//			}
//			
//			if(list.get(i).get("MOD_FAULT").toString().equals("1")){
//				tj.append("定位模块故障;");
//			}
//			if(list.get(i).get("ANT_FAULT").toString().equals("1")){
//				tj.append("天线短路;");
//			}
//			if(list.get(i).get("INEXACT").toString().equals("1")){
//				tj.append("非精确定位;");
//			}
//			
//			if(list.get(i).get("COMM_FAULT").toString().equals("1")){
//				tj.append("通讯故障;");
//			}
//			
//			if(list.get(i).get("GPS_OVER_BACK").toString().equals("1")){
//				tj.append("定位回传过密;");
//			}
//			if(list.get(i).get("GPS_NO_BACK").toString().equals("1")){
//				tj.append("回传数据丢失;");
//			}
//			
//			if(list.get(i).get("CAM_OCCLUSION").toString().equals("1")){
//				tj.append("摄像头遮挡;");
//			}
//			if(list.get(i).get("CAM_NOSIGN").toString().equals("1")){
//				tj.append("摄像头信号丢失;");
//			}
//			
//			if(list.get(i).get("HD_FAULT").toString().equals("1")){
//				tj.append("硬盘故障;");
//			}
//			if(list.get(i).get("SD_FAULT").toString().equals("1")){
//				tj.append("SD卡故障;");
//			}
//			if(list.get(i).get("VD_FAULT").toString().equals("1")){
//				tj.append("视频主机故障;");
//			}
//			if(list.get(i).get("VD_EX_FAULT").toString().equals("1")){
//				tj.append("视频扩展故障;");
//			}
//			
//			if(list.get(i).get("METER_DISCONN").toString().equals("1")){
//				tj.append("计价器连接断开;");
//			}
//			
//			if(list.get(i).get("NAV_DISCONN").toString().equals("1")){
//				tj.append("导航屏断开;");
//			}
//			
//			if(list.get(i).get("ST_NO_CHG").toString().equals("1")){
//				tj.append("空重车不变化;");
//			}
//			if(list.get(i).get("ST_OVER_CHG").toString().equals("1")){
//				tj.append("空重车切换频繁;");
//			}
//			list.get(i).put("TYPE",tj);
//		}
//		return FastJsonUtil.toJSONString(list);
//	}
	
	public String czsbgzcxxlsx(String cph, String xm,String comp, String gz,
			String stime, String etime) {
		List<Map<String, Object>> list = tjfxDao.getCzsbgzcxxlsx(cph,xm,comp,gz,stime,etime);
		if(list.size()>0){
			list=findDetail(list);
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("DB_TIME",String.valueOf( list.get(i).get("DB_TIME")));
			StringBuffer tj = new StringBuffer("");
			if(list.get(i).get("NO_GPS").toString().equals("1")){
				tj.append("有营运无定位");
			}
			if(list.get(i).get("NO_JJQ").toString().equals("1")){
				tj.append("有定位无营运");
			}
			if(list.get(i).get("NO_GPS_JJQ").toString().equals("1")){
				tj.append("有抓拍无定位无营运");
			}
			if(list.get(i).get("SEVEN_GPS_JJQ").toString().equals("1")){
				tj.append("7天无定位无营运");
			}
			if(list.get(i).get("EMPTY_HEAVY").toString().equals("1")){
				tj.append("全天空车全天重车");
			}
			
			list.get(i).put("TYPE",tj);
		}
		return FastJsonUtil.toJSONString(list);
	}

	@DS("datasource1")
//	@DS("datasource4")
//	public Result<List<Map<String, Object>>> czsbgztj(String cph, String xm,
//			String yc, String stime, String etime) {
//		List<Map<String, Object>> list = tjfxDao.getCzsbgztj(cph,xm,yc,stime,etime);
//		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
//		Map  map = new HashMap ();
//		map.put("datas",list);
//		lists.add(map);
//		return Result.success(lists);
//	}
	public Result<List<Map<String, Object>>> czsbgztj( String stime, String etime) {
		List<Map<String, Object>> list = tjfxDao.getCzsbgztj(stime,etime);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}

	@DS("datasource1")
//	@DS("datasource4")
//	public String czsbgztjxlsx(String cph, String xm, String yc, String stime,
//			String etime) {
//		List<Map<String, Object>> list = tjfxDao.getCzsbgztj(cph,xm,yc,stime,etime);
//		return FastJsonUtil.toJSONString(list);
//	}
	public String czsbgztjxlsx( String stime,
			String etime) {
		List<Map<String, Object>> list = tjfxDao.getCzsbgztj(stime,etime);
		return FastJsonUtil.toJSONString(list);
	}

	public Result<List<Map<String, Object>>> tscx(String lx, String stime,
			String etime, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getTscx(lx,stime,etime,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("BUSINESS_ITEMTYPE_NAME", list.get(i).get("BUSINESS_ITEMTYPE_NAME").toString().replaceAll("运管_客运_出租客运_", ""));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map  map = new HashMap ();
		map.put("datas",list);
		map.put("count", count);
		lists.add(map);
		return Result.success(lists);
	}

	
	public String tscxxlsx(String lx,  String stime,
			String etime) {
		List<Map<String, Object>> list = tjfxDao.getTscxxlsx(lx,stime,etime);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("BUSINESS_ITEMTYPE_NAME", list.get(i).get("BUSINESS_ITEMTYPE_NAME").toString().replace("运管_客运_出租客运_", ""));
			list.get(i).put("VEHICLE_PLATE_NUMBER", list.get(i).get("VEHICLE_PLATE_NUMBER").toString().replace(".", ""));
			list.get(i).put("HAPPEN_TIME", list.get(i).get("HAPPEN_TIME").toString().substring(0,10));
			list.get(i).put("ACCEPT_TIME", list.get(i).get("ACCEPT_TIME").toString().substring(0,10));
		}
		return FastJsonUtil.toJSONString(list);
	}

	public Result<List<Map<String, Object>>> sbgzcstj(String cph, String gz,
			String stime, String etime, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = tjfxDao.getSbgzcstj(cph,gz,stime,etime,pageIndex,pageSize);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		Map  map = new HashMap ();
		map.put("datas",list);
		map.put("count", count);
		lists.add(map);
		return Result.success(lists);
	}

	public String sbgzcstjxlsx(String cph, String gz,  String stime,
			String etime) {
		List<Map<String, Object>> list = tjfxDao.getSbgzcstjxlsx(cph,gz,stime,etime);
		return FastJsonUtil.toJSONString(list);
	}

	public Result<List<Map<String, Object>>> jsyxx(String cph) {
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = tjfxDao.getJsyxx(cph);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		Map  map = new HashMap ();
		map.put("datas",list);
		map.put("count", list.size());
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource2")
	public Result<List<Map<String, Object>>> tsrxx(String cph) {
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = tjfxDao.getTsrxx(cph);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		Map  map = new HashMap ();
		map.put("datas",list);
		map.put("count", list.size());
		lists.add(map);
		return Result.success(lists);
	}
	
	@DS("datasource2")
	public Result<List<Map<String, Object>>> xzcfxx(String cph) {
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = tjfxDao.getXzcfxx(cph);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
		}
		Map  map = new HashMap ();
		map.put("datas",list);
		map.put("count", list.size());
		lists.add(map);
		return Result.success(lists);
	}
	//将查询出的0,1字段转化为无故障，故障
		private List<Map<String, Object>> findDetail(List<Map<String, Object>> list) {
			for(int i=0;i<list.size();i++){
				if(String.valueOf(list.get(i).get("NO_GPS")).equals("1")){
					list.get(i).put("NO_GPS","故障");
				}else if(String.valueOf(list.get(i).get("NO_GPS")).equals("0")){
					list.get(i).put("NO_GPS","无故障");
				}else{
					list.get(i).put("NO_GPS","");
				}
				if(String.valueOf(list.get(i).get("NO_JJQ")).equals("1")){
					list.get(i).put("NO_JJQ","故障");
				}else if(String.valueOf(list.get(i).get("NO_JJQ")).equals("0")){
					list.get(i).put("NO_JJQ","无故障");
				}else{
					list.get(i).put("NO_JJQ","");
				}
				if(String.valueOf(list.get(i).get("NO_GPS_JJQ")).equals("1")){
					list.get(i).put("NO_GPS_JJQ","故障");
				}else if(String.valueOf(list.get(i).get("NO_GPS_JJQ")).equals("0")){
					list.get(i).put("NO_GPS_JJQ","无故障");
				}else{
					list.get(i).put("NO_GPS_JJQ","");
				}
				if(String.valueOf(list.get(i).get("SEVEN_GPS_JJQ")).equals("1")){
					list.get(i).put("SEVEN_GPS_JJQ","故障");
				}else if(String.valueOf(list.get(i).get("SEVEN_GPS_JJQ")).equals("0")){
					list.get(i).put("SEVEN_GPS_JJQ","无故障");
				}else{
					list.get(i).put("SEVEN_GPS_JJQ","");
				}
				if(String.valueOf(list.get(i).get("EMPTY_HEAVY")).equals("1")){
					list.get(i).put("EMPTY_HEAVY","故障");
				}else if(String.valueOf(list.get(i).get("EMPTY_HEAVY")).equals("0")){
					list.get(i).put("EMPTY_HEAVY","无故障");
				}else{
					list.get(i).put("EMPTY_HEAVY","");
				}
				if(String.valueOf(list.get(i).get("SCREEN_BLACK")).equals("1")){
					list.get(i).put("SCREEN_BLACK","故障");
				}else if(String.valueOf(list.get(i).get("SCREEN_BLACK")).equals("0")){
					list.get(i).put("SCREEN_BLACK","无故障");
				}else{
					list.get(i).put("SCREEN_BLACK","");
				}
				if(String.valueOf(list.get(i).get("MOVE_ON")).equals("1")){
					list.get(i).put("MOVE_ON","故障");
				}else if(String.valueOf(list.get(i).get("SCREEN_BLACK")).equals("0")){
					list.get(i).put("MOVE_ON","无故障");
				}else{
					list.get(i).put("MOVE_ON","");
				}
				if(String.valueOf(list.get(i).get("BREAK_OFF")).equals("1")){
					list.get(i).put("BREAK_OFF","故障");
				}else if(String.valueOf(list.get(i).get("SCREEN_BLACK")).equals("0")){
					list.get(i).put("BREAK_OFF","无故障");
				}else{
					list.get(i).put("BREAK_OFF","");
				}
			}
			return list;
		}
}
