package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.WsfwDao;
import com.erxi.ms.redis.Monitor;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.ZlKey;
import com.erxi.ms.result.FastJsonUtil;

/**
 * 网上服务中的几个功能
 * @author xianlehuang
 * @date 2018/12/20 
 */

@Service
public class WsfwServics {
	
    @Autowired
    private WsfwDao WsfwDao;
    private Map<String, Object> map;
    
    @Autowired
	RedisService redisService;
    /**
	 * 标记redis取数据
	 */
	public static boolean flag =true;
    /**
	 * 下拉所有公司
	 * @return
	 */
	@DS("datasource1")
	public String qycomp() {
		if(flag){
			Map map = new HashMap();
			map.put("datacomp", allComp());
			redisService.set(Monitor.selectcomp, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(Monitor.selectcomp, "", String.class)));
		}	
	}
	private List<Map<String, Object>> allComp() {
		return WsfwDao.getAllComp();
	}
    @DS("datasource1")
   	public String findxll(String table,String field) {
   		List<Map<String, Object>> list = WsfwDao.findxll(table,field);
   		return FastJsonUtil.toJSONString(list);
   	}
    @DS("datasource1")
   	public String findclzxcx(String stime,String etime,String vehicle,String company, Integer pageIndex, Integer pageSize) {
   		 List<Map<String, Object>> list = WsfwDao.findclzxcx(stime,etime,vehicle,company,pageIndex,pageSize);
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
   	public  List<Map<String, Object>> findclzxcxdc(String stime,String etime,String vehicle,String company) {
   		List<Map<String, Object>> list = WsfwDao.findclzxcxdc(stime,etime,vehicle,company);
   		return list;
   	}
    @DS("datasource1")
   	public String findfgslctj(String stime,String etime,String company,String vehicle, Integer pageIndex, Integer pageSize) {
   		 List<Map<String, Object>> list = WsfwDao.findfgslctj(stime,etime,company, vehicle,pageIndex,pageSize);
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
   	public  List<Map<String, Object>> findfgslctjdc(String stime,String etime,String company,String vehicle) {
   		List<Map<String, Object>> list = WsfwDao.findfgslctjdc(stime,etime,company,vehicle);
   		return list;
   	}
    @DS("datasource1")
   	public String findsjyysjfx(String stime,String etime,String vehicle,String company, Integer pageIndex, Integer pageSize) {
   		 List<Map<String, Object>> list = WsfwDao.findsjyysjfx(stime,etime,vehicle,company,pageIndex,pageSize);
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
   	public  List<Map<String, Object>> findsjyysjfxdc(String stime,String etime,String vehicle,String company) {
   		List<Map<String, Object>> list = WsfwDao.findsjyysjfxdc(stime,etime,vehicle,company);
   		return list;
   	}
    
    @DS("datasource2")
   	public String findzfjc(String stime,String etime,String event,String vehicle, Integer pageIndex, Integer pageSize) {
   		 List<Map<String, Object>> list = WsfwDao.findzfjc(stime,etime,event,vehicle,pageIndex,pageSize);
   			int count = 0;
   			if( list!=null && list.size() >0){
   				count = Integer.parseInt(String.valueOf(list.get(0).get("count")));
   			}
   			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
   			map = new HashMap<String, Object> ();
   			map.put("count", count);
   			map.put("datas",list);
   			lists.add(map);
   			return FastJsonUtil.toJSONString(lists);
   	}
    @DS("datasource2")
   	public  List<Map<String, Object>> findzfjcdc(String stime,String etime,String event,String vehicle) {
   		List<Map<String, Object>> list = WsfwDao.findzfjcdc(stime,etime,event,vehicle);
   		return list;
   	}
    @DS("datasource1")
   	public String findqyyysjfx(String stime,String etime,String company, Integer pageIndex, Integer pageSize) {
   		 List<Map<String, Object>> list = WsfwDao.findqyyysjfx(stime,etime,company,pageIndex,pageSize);
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
   	public  List<Map<String, Object>> findqyyysjfxdc(String stime,String etime,String company) {
   		List<Map<String, Object>> list = WsfwDao.findqyyysjfxdc(stime,etime,company);
   		return list;
   	}
    
    @DS("datasource1")
   	public String finddcpjfx(String module,String field1,String field2,String field3,String time) {
   		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
   		//今天，昨天
   		for (int i = 0; i < 2; i++) {
   			Map<String, Object> map1=toMap(i,WsfwDao.finddcpjfx(module,i,field1,time));
   			if(i==0){
   				map1.put("message", "今天");
   			}else if(i==1){
   				map1.put("message", "昨天");
   			}
   			list.add(map1);
		}
   		//前天，上周
   		for (int i = 2; i < 4; i++){
   			Map<String, Object> map2=toMap(i,WsfwDao.findday(i,field2,time));
   			if(i==2){
   				map2.put("message", "前天");
   			}else if(i==3){
   				map2.put("message", "上周");
   			}
   			list.add(map2);
   		}
   		//上周平均
   		Map<String, Object> map3=toMap(4,WsfwDao.findaverage(field2,time));
   		map3.put("message", "上周平均");
		list.add(map3);
		//上半月最大最小
		tolist(WsfwDao.findmaxmin(field3,time),list,field3);
		//上月，上年
		for (int i = 7; i < 9; i++){
   			Map<String, Object> map4=toMap(i,WsfwDao.findday(i,field2,time));
   			if(i==7){
   				map4.put("message", "上月");
   			}else if(i==8){
   				map4.put("message", "上年");
   			}
   			list.add(map4);
   		}
		return FastJsonUtil.toJSONString(list);
   	}
	private void tolist(List<Map<String, Object>> listmaxmin,
			List<Map<String, Object>> list, String field3) {
		String[] max = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		String[] min = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" };
		if (listmaxmin.size() != 0) {
			max = String.valueOf(listmaxmin.get(0).get(field3+"_MAX")).split(
					";");
			min = String.valueOf(listmaxmin.get(0).get(field3+"_MIN")).split(
					";");
		}
		Map<String, Object> maxMap = new HashMap<String, Object>();
		for (int j = 0; j < max.length; j++) {
			maxMap.put("y" + j, max[j]);
		}
		maxMap.put("message", "前半月最大");

		Map<String, Object> minMap = new HashMap<String, Object>();
		for (int j = 0; j < min.length; j++) {
			minMap.put("y" + j, min[j]);
		}
		minMap.put("message", "前半月最小");
		list.add(maxMap);
		list.add(minMap);	
	}
	private Map<String, Object> toMap(int m,List<Map<String, Object>> list) {
		String[] str = { "00", "01", "02", "03", "04", "05",
				"06", "07", "08", "09", "10", "11", "12", "13",
				"14", "15", "16", "17", "18", "19", "20", "21",
				"22", "23", };
		Map<String, Object> map = new HashMap<String, Object>();
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			if ((a=Arrays.asList(str).indexOf(list.get(i).get("TIME")))>-1) {
				map.put("y" + a, list.get(i).get("COUNT"));
			}
		}
		for (int i = 0; i < 24; i++) {
			if (map.get("y" + i) == null) {
				map.put("y" + i, "");
			}
		}
		return map;
	}
    
	@DS("datasource1")
   	public String findfwzlxx(String postData) {
   		 List<Map<String, Object>> list = WsfwDao.findfwzlxx(postData);
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
   	public  List<Map<String, Object>> findfwzlxxdc(String postData) {
   		List<Map<String, Object>> list = WsfwDao.findfwzlxxdc(postData);
   		return list;
   	}
	
    
}
