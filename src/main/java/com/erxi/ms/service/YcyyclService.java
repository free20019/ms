package com.erxi.ms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YcyyclDao;
import com.erxi.ms.result.FastJsonUtil;

@Service
public class YcyyclService {
	@Autowired
	YcyyclDao ycyycldao;
	private Map<String, Object> map;
	
	 @DS("datasource1")
	public String findxll(String field,String table) {
		List<Map<String, Object>> list = ycyycldao.findxll(field,table);
		return FastJsonUtil.toJSONString(list);
	}
	 @DS("datasource1")
	public String tsxll(String field,String table) {
		 Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -180);
		date = calendar.getTime();
		String time=sdf.format(date);
		List<Map<String, Object>> list = ycyycldao.tsxll(field,table,time);
		return FastJsonUtil.toJSONString(list);
	}
	 @DS("datasource1")
	public String findsbyc(String stime,String etime,String vehicle,String type,Integer pageIndex, Integer pageSize) {
		 List<Map<String, Object>> list = ycyycldao.findsbyc(stime,etime,vehicle,type,pageIndex,pageSize);
			int count = 0;
			if( list!=null && list.size() >0){
				count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			}
			if(list.size()>0){
				for(int i = 0; i< list.size();i++){
					Map<String, Object> vehicle1=list.get(i);
					vehicle1.put("GZ", findgz(vehicle1));
				}
			}
			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object> ();
			map.put("count", count);
			map.put("datas",list);
			lists.add(map);
			return FastJsonUtil.toJSONString(lists);
	}
	 @DS("datasource1")
	public List<Map<String, Object>> findsbycdc(String stime,String etime,String vehicle,String type) {
		List<Map<String, Object>> list = ycyycldao.findsbycdc(stime,etime,vehicle,type);
		if(list.size()>0){
			for(int i = 0; i< list.size();i++){
				Map<String, Object> vehicle1=list.get(i);
				vehicle1.put("GZ", findgz(vehicle1));
			}
		}
		return list;
	} 
	 @DS("datasource1")
	public String findtswz(String stime,String etime,String vehicle,String type,String address, Integer pageIndex, Integer pageSize) {
		 List<Map<String, Object>> list = ycyycldao.findtswz(stime,etime,vehicle,type,address,pageIndex,pageSize);
			int count = 0;
			if( list!=null && list.size() >0){
				count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			}
			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object> ();
			map.put("count", count);
			map.put("datas",list);
			lists.add(map);
			return FastJsonUtil.toJSONString(lists);
	}
	 @DS("datasource1")
	public List<Map<String, Object>> findtswzdc(String stime,String etime,String vehicle,String type,String address) {
		List<Map<String, Object>> list = ycyycldao.findtswzdc(stime,etime,vehicle,type,address);
		return list;
	} 
	 @DS("datasource1")
	public String findyshc(String stime,String etime,String vehicle,String type,String address, Integer pageIndex, Integer pageSize) {
		 List<Map<String, Object>> list = ycyycldao.findyshc(stime,etime,vehicle,type,address,pageIndex,pageSize);
			int count = 0;
			if( list!=null && list.size() >0){
				count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			}
			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object> ();
			map.put("count", count);
			map.put("datas",list);
			lists.add(map);
			return FastJsonUtil.toJSONString(lists);
	}
	 @DS("datasource1")
	public List<Map<String, Object>> findyshcdc(String stime,String etime,String vehicle,String type,String address) {
		List<Map<String, Object>> list = ycyycldao.findyshcdc(stime,etime,vehicle,type,address);
		return list;
	} 
	 @DS("datasource1")
	public String findystp(String stime,String etime,String vehicle,String type,String address, Integer pageIndex, Integer pageSize) {
		 List<Map<String, Object>> list = ycyycldao.findystp(stime,etime,vehicle,type,address,pageIndex,pageSize);
			int count = 0;
			if( list!=null && list.size() >0){
				count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			}
			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object> ();
			map.put("count", count);
			map.put("datas",list);
			lists.add(map);
			return FastJsonUtil.toJSONString(lists);
	}
	 @DS("datasource1")
	public List<Map<String, Object>> findystpdc(String stime,String etime,String vehicle,String type,String address) {
		List<Map<String, Object>> list = ycyycldao.findystpdc(stime,etime,vehicle,type,address);
		if( list!=null && list.size() >0){
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("ERROR_TYPE","套牌");
			}
		}
		return list;
	} 
	 
	 public String findgz(Map<String, Object> vehicle1){
			String gz="";
			//有定位无营运
			if (!String.valueOf(vehicle1.get("NO_GPS")).equals("0")){
				gz +="有定位无营运；";
			}	
			//有营运无定位
			if (!String.valueOf(vehicle1.get("NO_JJQ")).equals("0")) {
				gz +="有营运无定位；";
			}
			//有抓拍无定位无营运
			if (!String.valueOf(vehicle1.get("NO_GPS_JJQ")).equals("0")) {
				gz +="有抓拍无定位无营运；";
			}
			//7天无定位无营运
			if (!String.valueOf(vehicle1.get("SEVEN_GPS_JJQ")).equals("0")) {
				gz +="7天无定位无营运；";
			}
			//空重车无变化
			if (!String.valueOf(vehicle1.get("EMPTY_HEAVY")).equals("0")) {
				gz +="空重车无变化；";
			}
		return gz;
		
	}
}
