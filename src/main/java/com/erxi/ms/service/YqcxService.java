package com.erxi.ms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YqcxDao;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.ZlKey;
import com.erxi.ms.result.FastJsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class YqcxService {
	@Autowired
	YqcxDao yqcxdao;
	private Map<String, Object> map;

	@Autowired
	RedisService redisService;


	 @DS("datasource1")
	public String findxll(String field,String table) {
		List<Map<String, Object>> list = yqcxdao.findxll(field,table);
		return FastJsonUtil.toJSONString(list);
	}

	 @DS("datasource1")
	public String findczxxcx(String stime,String etime,String vehicle,String company,String phone,Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yqcxdao.findczxxcx(stime,etime,vehicle,company,phone,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("CKDH",mobileEncrypt(String.valueOf(list.get(i).get("CKDH"))));
				list.get(i).put("CKXM",nameEncrypt(String.valueOf(list.get(i).get("CKXM"))));
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
	public List<Map<String, Object>> findczxxcxdc(String stime,String etime,String vehicle,String company,String phone) {
		List<Map<String, Object>> list = yqcxdao.findczxxcxdc(stime,etime,vehicle,company,phone);
		if( list!=null && list.size() >0){
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("CKDH",mobileEncrypt(String.valueOf(list.get(i).get("CKDH"))));
				list.get(i).put("CKXM",nameEncrypt(String.valueOf(list.get(i).get("CKXM"))));
			}
		}
		return list;
	}

	// 手机号码前三后二脱敏
	public static String mobileEncrypt(String mobile) {
		if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
			return mobile;
		}
		return mobile.replaceAll("(\\d{3})\\d{6}(\\d{2})", "$1******$2");
	}

	// 姓名第一位脱敏
	public static String nameEncrypt(String name) {
		if (StringUtils.isEmpty(name)||!name.matches("[\\u4e00-\\u9fa5]+")) {
			return name;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(name.substring(0,1));
		for (int i = 1; i < name.length(); i++) {
			sb.append("*");
		}
		return sb.toString();
	}

	@DS("datasource1")
	public String findczxxcxmg(String stime,String etime,String vehicle,String company,String phone,Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yqcxdao.findczxxcx(stime,etime,vehicle,company,phone,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).put("CKDH",mobileEncrypt(String.valueOf(list.get(i).get("CKDH"))));
//				list.get(i).put("CKXM",nameEncrypt(String.valueOf(list.get(i).get("CKXM"))));
//			}
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		map = new HashMap<String, Object> ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return FastJsonUtil.toJSONString(lists);
	}
	@DS("datasource1")
	public List<Map<String, Object>> findczxxcxmgdc(String stime,String etime,String vehicle,String company,String phone) {
		List<Map<String, Object>> list = yqcxdao.findczxxcxdc(stime,etime,vehicle,company,phone);
//		if( list!=null && list.size() >0){
//			for (int i = 0; i < list.size(); i++) {
//				list.get(i).put("CKDH",mobileEncrypt(String.valueOf(list.get(i).get("CKDH"))));
//				list.get(i).put("CKXM",nameEncrypt(String.valueOf(list.get(i).get("CKXM"))));
//			}
//		}
		return list;
	}

	@DS("datasource1")
	public String finddjsjfx(String stime,String etime,String vehicle,String company,String value,Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yqcxdao.finddjsjfx(stime,etime,vehicle,company,value,pageIndex,pageSize);
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
	public List<Map<String, Object>> finddjsjfxdc(String stime,String etime,String vehicle,String company,String value) {
		List<Map<String, Object>> list = yqcxdao.finddjsjfxdc(stime,etime,vehicle,company,value);
		return list;
	}

	@DS("datasource1")
	public String findckqzsy(String stime,String etime,String phone) {
		List<Map<String, Object>> list1 = yqcxdao.findckqzsy1(stime,etime,phone);
		List<Map<String, Object>> list2 = yqcxdao.findckqzsy2(stime,etime,phone);
		list1=getRealNameByCKRS(list1);
		list2=getRealNameByCKRS(list2);
		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		map = new HashMap<String, Object> ();
		map.put("list1", list1);
		map.put("list2",list2);
		lists.add(map);
		return FastJsonUtil.toJSONString(lists);
	}
	@DS("datasource1")
	public List<Map<String, Object>> findckqzsydc(String stime,String etime,String phone,String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(type.equals("1")){
			list = yqcxdao.findckqzsy1(stime,etime,phone);
		}else if(type.equals("2")){
			list = yqcxdao.findckqzsy2(stime,etime,phone);
		}
		list=getRealNameByCKRS(list);

		return list;
	}

	@DS("datasource1")
	public String findsjqzsy(String stime,String etime,String vehicle,String phone) {
		List<Map<String, Object>> list1 = yqcxdao.findsjqzsy1(stime,etime,vehicle,phone);
		List<Map<String, Object>> list2 = yqcxdao.findsjqzsy2(stime,etime,vehicle,phone);
		list1=getRealNameByCKRS(list1);
		list2=getRealNameByCKRS(list2);

		int count = 0;
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		map = new HashMap<String, Object> ();
		map.put("list1", list1);
		map.put("list2",list2);
		lists.add(map);
		return FastJsonUtil.toJSONString(lists);
	}
	@DS("datasource1")
	public List<Map<String, Object>> findsjqzsydc(String stime,String etime,String vehicle,String phone, String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(type.equals("1")){
			list = yqcxdao.findsjqzsy1(stime,etime,vehicle,phone);
		}else if(type.equals("2")){
			list = yqcxdao.findsjqzsy2(stime,etime,vehicle,phone);
		}
		list=getRealNameByCKRS(list);
		return list;
	}

	private List<Map<String, Object>> getRealNameByCKRS(List<Map<String, Object>> list){
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("CKRS","1".equals(String.valueOf(list.get(i).get("CKRS")))?"司机代填":("9".equals(String.valueOf(list.get(i).get("CKRS")))?"乘客填写":""));
			}
		}
		return list;
	}

	@DS("datasource1")
	public String finddjycfx(String stime,String etime,String vehicle,String value, String company,Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yqcxdao.finddjycfx(stime,etime,vehicle,value,company,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		list=getRealName(list);
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		map = new HashMap<String, Object> ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return FastJsonUtil.toJSONString(lists);
	}
	@DS("datasource1")
	public List<Map<String, Object>> finddjycfxdc(String stime,String etime,String vehicle,String value, String company) {
		List<Map<String, Object>> list = yqcxdao.finddjycfxdc(stime,etime,vehicle,value,company);
		list=getRealName(list);
		return list;
	}

	private List<Map<String, Object>> getRealName(List<Map<String, Object>> list){
	 	if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("JJQ_NDJ","0".equals(String.valueOf(list.get(i).get("JJQ_NDJ")))?"否":("1".equals(String.valueOf(list.get(i).get("JJQ_NDJ")))?"是":""));
				list.get(i).put("GPS_NYY_NDJ","0".equals(String.valueOf(list.get(i).get("GPS_NYY_NDJ")))?"否":("1".equals(String.valueOf(list.get(i).get("GPS_NYY_NDJ")))?"是":""));
				list.get(i).put("NGPS_NJJQ_NDJ_ZP","0".equals(String.valueOf(list.get(i).get("NGPS_NJJQ_NDJ_ZP")))?"否":("1".equals(String.valueOf(list.get(i).get("NGPS_NJJQ_NDJ_ZP")))?"是":""));
			}
		}
	 	return list;
	}

	public String findTodayInformation() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 	String time= sdf.format(new Date());
		List<LinkedHashMap<String, Object>> list = yqcxdao.findTodayInformation(time);
		if(list.size()==1){
//			if(TlaqService.flag){
//				list.get(0).put("yycl","0");
//				list.get(0).put("yyjl","0");
//			}else {
				Map map1 = FastJsonUtil.stringToMap(redisService.get(ZlKey.Vehi, "", String.class));
				Map map2 = FastJsonUtil.stringToMap(redisService.get(ZlKey.busyOnline, "", String.class));
				if(map1!=null){
					list.get(0).put("yycl",(JSON.parseObject(String.valueOf(map1.get("yycl")), new TypeReference<List<Map<String, Object>>>() {})).get(0).get("count"));
				}else{
					list.get(0).put("yycl","0");
				}
				if(map2!=null){
					list.get(0).put("yyjl",(JSON.parseObject(String.valueOf(map2.get("all")), new TypeReference<List<Map<String, Object>>>() {})).get(0).get("count"));
				}else{
					list.get(0).put("yyjl","0");
				}

//			}
			String httpUrl = "http://183.129.170.116/czczhfwpt/common/otherProject";
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			URI uri =URI.create(httpUrl.toString());
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Content-Type", "application/json;charset=utf8");
			CloseableHttpResponse response = null;
			String result = "";
			try {
				response = httpClient.execute(httpPost);
				HttpEntity responseEntity = response.getEntity();
				result = EntityUtils.toString(responseEntity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (httpClient != null) {
						httpClient.close();
					}
					if (response != null) {
						response.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(!"".equals(result)&&"[".equals(result.substring(0,1))){
				List<Map<String, Object>> listOther = JSON.parseObject(result, new TypeReference<List<Map<String, Object>>>() {});
				if (listOther.size()==1) {
					list.get(0).put("dqzcsl",listOther.get(0).get("dqzcsl"));
					list.get(0).put("dqrzsl",listOther.get(0).get("dqrzsl"));
					list.get(0).put("zcsjsl",listOther.get(0).get("zcsjsl"));
					list.get(0).put("rzsjsl",listOther.get(0).get("rzsjsl"));
				}else{
					list.get(0).put("dqzcsl","0");
					list.get(0).put("dqrzsl","0");
					list.get(0).put("zcsjsl","0");
					list.get(0).put("rzsjsl","0");
				}
			}

		}
		return FastJsonUtil.toJSONString(list);
	}

	@DS("datasource1")
    public String findczrz(String stime, String etime, String value, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yqcxdao.findczrz(stime,etime,value,pageIndex,pageSize);
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("ACTIONTIME",list.get(i).get("ACTIONTIME")==null?"":String.valueOf(list.get(i).get("ACTIONTIME")).substring(0,19));
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
	public List<Map<String, Object>> findczrzdc(String stime, String etime, String value) {
		List<Map<String, Object>> list = yqcxdao.findczrzdc(stime,etime,value);
		return list;
	}
}
