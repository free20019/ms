package com.erxi.ms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.JyxxDao;
import com.erxi.ms.result.FastJsonUtil;

/**
 * 在线支付、专车数据接入、出租汽车信息共享与报送系统
 * @author xianlehuang
 * @date 2018/12/20 
 */

@Service
public class JyxxServics {
	
    @Autowired
    private JyxxDao JyxxDao;
    private Map<String, Object> map;
   
    @DS("datasource1")
   	public String findxll(String table,String field) {
   		List<Map<String, Object>> list = JyxxDao.findxll(table,field);
   		return FastJsonUtil.toJSONString(list);
   	}
    @DS("datasource1")
   	public String findxllcl(String table,String field,String type) {
   		List<Map<String, Object>> list = JyxxDao.findxllcl(table,field,type);
   		return FastJsonUtil.toJSONString(list);
   	}
    @DS("datasource1")
   	public String findnowmonthall() {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			String time = sdf.format(d);
			String stime = time.substring(0, 10)+" 00:00:00";
			String time1 = "2015-01-01 00:00:00";
			String time2 = time.substring(0, 7)+"-01 00:00:00";
//			List<Map<String, Object>> all = JyxxDao.findnowmonthall(time1,time);
   		 	List<Map<String, Object>> now = JyxxDao.findnowmonthall(stime,time);
   		 	List<Map<String, Object>> month = JyxxDao.findnowmonthall(time2,time);
   			map = new HashMap<String, Object> ();
   			map.put("NOW", now);
//   			map.put("ALL", all);
   			map.put("MONTH", month);
   			return FastJsonUtil.toJSONString(map);
   	}
	 @DS("datasource1")
	public String findwxjy(String order,String stime,String etime,Integer pageIndex, Integer pageSize) {
		 List<Map<String, Object>> list = JyxxDao.findwxjy(order,stime,etime,pageIndex,pageSize);
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
		public  List<Map<String, Object>> findwxjydc(String order,String stime,String etime) {
			List<Map<String, Object>> list = JyxxDao.findwxjydc(order,stime,etime);
			return list;
		}
	 @DS("datasource1")
	   	public String findzcyytj() {
	   		List<Map<String, Object>> list = JyxxDao.findzcyytj();
	   		return FastJsonUtil.toJSONString(list);
	   	}
	 @DS("datasource1")
		public String findzcsj(String vehicle,String type,Integer pageIndex, Integer pageSize) {
			 List<Map<String, Object>> list = JyxxDao.findzcsj(vehicle,type,pageIndex,pageSize);
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
		public  List<Map<String, Object>> findzcsjdc(String vehicle,String type) {
			List<Map<String, Object>> list = JyxxDao.findzcsjdc(vehicle,type);
			return list;
		}
	 @DS("datasource1")
		public String findxxbs() {
		 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = new Date();
			long a = d.getTime();
			long c = a-1000*60*60;
			Date b = new Date(c);
			String stime = sdf.format(b);
			String etime = sdf.format(d);
			List<Map<String, Object>> now = JyxxDao.findxxbsnow(stime,etime);
			List<Map<String, Object>> list = JyxxDao.findxxbs();
			map = new HashMap<String, Object> ();
			map.put("now", now);
			map.put("datas",list);
			return FastJsonUtil.toJSONString(map);
		}
	 @DS("datasource1")
		public String findxxbsfy(String stime,String etime,Integer pageIndex, Integer pageSize) {
			 List<Map<String, Object>> list = JyxxDao.findxxbsfy(stime,etime,pageIndex,pageSize);
				int count = 0;
				if( list!=null && list.size() >0){
					count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
					for(int i=0;i<list.size();i++){
						list.get(i).put("SUBJECT", "浙江省交通厅信息中心");
						list.get(i).put("GMT_PAYMENT", "出租车卫星定位");
						list.get(i).put("SELLER_EMAIL", "9612");
						list.get(i).put("BUYER_EMAIL", "共享");
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
		public  List<Map<String, Object>> findxxbsdc(String stime,String etime) {
			List<Map<String, Object>> list = JyxxDao.findxxbsdc(stime,etime);
			if( list!=null && list.size() >0){				
				for(int i=0;i<list.size();i++){
					list.get(i).put("SUBJECT", "浙江省交通厅信息中心");
					list.get(i).put("GMT_PAYMENT", "出租车卫星定位");
					list.get(i).put("SELLER_EMAIL", "9612");
					list.get(i).put("BUYER_EMAIL", "共享");
				}
			}
			return list;
		}
}
