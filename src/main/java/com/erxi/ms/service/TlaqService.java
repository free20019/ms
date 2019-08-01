package com.erxi.ms.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.erxi.ms.config.DS;
import com.erxi.ms.controller.ExcelUtil;
import com.erxi.ms.dao.TlaqDao;
import com.erxi.ms.domain.Vehicle;
import com.erxi.ms.domain.Vehicle1;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.VeKey;
import com.erxi.ms.redis.ZlKey;
import com.erxi.ms.result.CodeMsg;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.GeometryHandler;
import com.erxi.ms.result.Result;
import com.service.GpsServicesDelegate;
import com.vividsolutions.jts.geom.Geometry;

@Service
public class TlaqService {
	private static String WS_URL = "http://192.168.0.102:9086/EWS/GpsServicesPort?WSDL";
	
	@Autowired
	TlaqDao tlaqDao;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;
	
	
	/**
	 * 标记redis取数据
	 */
	public static boolean flag =true;
	
	
	/**
	 * 所有出租车车辆
	 * @return
	 */
	public String qyjk() {
		String token = CommonService.COOKIE_NAME_TOKEN;
		List<Vehicle1> list = new ArrayList();
		String msgvel = redisService.get(VeKey.realtimeVehicle, "", String.class);
		String msgarea = redisService.get(VeKey.realtimeArea, "", String.class);
		Map maps = new HashMap();
		maps.put("vehilist", FastJsonUtil.getListMap(msgvel));
		maps.put("arealist", FastJsonUtil.getListMap(msgarea));
		return FastJsonUtil.toJSONString(maps);
	}
	
	/**
	 * 运营总览
	 * @return
	 * @throws Exception 
	 */
	@DS("datasource1")
	@Transactional
	public String yyzl() throws Exception {
		if(flag){
			Map map = new HashMap();
			map.put("compzl", countComp());
			map.put("clzl", countVehi());
			map.put("clzlcount", countVehiYear());
			map.put("perzl", countPer());
			map.put("perzl1", countPercount());
			map.put("isu", countIsu());
			redisService.set(ZlKey.Comp, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.Comp, "", String.class)));
		}	
	}
	
	/**
	 * 运营总览
	 * @return
	 */
	@DS("datasource1")
	@Transactional
	public String yyzlssss() {
		if(flag){
			Map map = new HashMap();
			map.put("all",countAll());
			map.put("order",countOrder());
			map.put("sum",countSum());
			redisService.set(ZlKey.busyOnline, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.busyOnline, "", String.class)));
		}	
	}
	
	/**
	 * 运营总览
	 * @return
	 */
	@DS("datasource1")
	@Transactional
	public String focusMonitor() {
		if(flag){
			Map map = new HashMap();
			map.put("zdzp", findFocusMonitor());
			redisService.set(ZlKey.Focus, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.Focus, "", String.class)));
		}	
	}
	
	/**
	 * 运营总览
	 * @return
	 */
	@DS("datasource1")
	@Transactional
	public String faultMonitor() {
		if(flag){
			Map map = new HashMap();
			map.put("gzzp", findFaultMonitor());
			redisService.set(ZlKey.Fault, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.Fault, "", String.class)));
		}	
	}
	
	/**
	 * 运营总览
	 * @return
	 */
//	@DS("datasource3")
	@DS("datasource1")
	@Transactional
	public String yyzlss() {
		if(flag){
			Map map = new HashMap();
			map.put("mac", countMac());
			redisService.set(ZlKey.machine, "", FastJsonUtil.toJSONString(map));
			return null;
//			GisData.maps.put("data2",map);
//			return FastJsonUtil.toJSONString(map);
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.machine, "", String.class)));
//			return FastJsonUtil.toJSONString(GisData.maps.get("data2"));
		}	
	}
	
	
	/**
	 * 运营总览
	 * @return
	 */
	@DS("datasource1")
	@Transactional
	public String yyzlsss() {
		if(flag){
			Map map = new HashMap();
			map.put("yycl", countYycls());
			redisService.set(ZlKey.Vehi, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.Vehi, "", String.class)));
		}	
	}
	
	
	/**
	 * 运营总览    投诉 
	 * @return
	 */
	@DS("datasource1")
//	@DS("datasource2")
	@Transactional
	public String yyzls() {
		if(flag){
			Map map = new HashMap();
			
			map.put("ts", countTs());
			map.put("tj", countTj());
			redisService.set(ZlKey.complaint, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.complaint, "", String.class)));
		}	
	}
	
	
	/**
	 * 下拉所有公司
	 * @return
	 */
	@DS("datasource1")
	public String qycomp() {
		if(flag){
			Map map = new HashMap();
			map.put("datacomp", allComp());
			redisService.set(ZlKey.selectcomp, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.selectcomp, "", String.class)));
		}	
	}
	
	/**
	 * 下拉所有车辆
	 * @return
	 */
	@DS("datasource1")
	public String qyveh() {
		if(flag){
			Map map = new HashMap();
			map.put("dataveh", allVehi());
			redisService.set(ZlKey.selectvehi, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.selectvehi, "", String.class)));
		}	
	}
	
	/**
	 * 下拉所有区域
	 * @return
	 */
	@DS("datasource1")
	public String qyarea() {
		if(flag){
			Map map = new HashMap();
			map.put("dataarea", allArea());
			redisService.set(ZlKey.selectarea, "", FastJsonUtil.toJSONString(map));
			return null;
		}else {
			return FastJsonUtil.toJSONString(FastJsonUtil.stringToMap(redisService.get(ZlKey.selectarea, "", String.class)));
		}	
	}
	
	

	public String findswcz(String qd_stime, String qd_etime, String zd_stime,
			String zd_etime, String qd_jwd, String zd_jwd) {
		List<List<Vehicle>> zzjg = new ArrayList<List<Vehicle>>();
		List<String> list = new ArrayList<String>();
		String s1 = "", s2 = "", s3 = "";
		List<Vehicle> listqd = new ArrayList<Vehicle>();
		List<Vehicle> listzd = new ArrayList<Vehicle>();
		if (qd_jwd != null && qd_jwd.length() > 0) {
			s1 = swczff(qd_stime, qd_etime, qd_jwd, "1");
			JSONObject json = JSONObject.fromObject(s1);
			list.add(json.getString("result0"));
			JSONObject json1 = JSONObject.fromObject(json.getString("result0"));
			Set setqd = json1.keySet();
			Iterator it = setqd.iterator();
			String a = "";
			while (it.hasNext()) {
				a = (String) it.next();
				Vehicle v = new Vehicle();
				v.setVehi_no(a);
				v.setStime(json1.get(a).toString().split("\\[")[0]);
				v.setPx(json1.get(a).toString().split("\\[")[1]);
				listqd.add(v);
			}
		}

		if (zd_jwd != null && zd_jwd.length() > 0) {
			s2 = swczff(zd_stime, zd_etime, zd_jwd, "2");
			JSONObject json = JSONObject.fromObject(s2);
			list.add(json.getString("result0"));
			JSONObject json1 = JSONObject.fromObject(json.getString("result0"));
			Set setzd = json1.keySet();
			Iterator it = setzd.iterator();
			String a = "";
			while (it.hasNext()) {
				a = (String) it.next();
				Vehicle v = new Vehicle();
				v.setVehi_no(a);
				v.setStime(json1.get(a).toString().split("\\[")[0]);
				v.setPx(json1.get(a).toString().split("\\[")[1]);
				listzd.add(v);
			}
		}
		List<Vehicle> listjg = new ArrayList<Vehicle>();
		JSONObject json11 = JSONObject.fromObject(list.get(0));
		JSONObject json2;
		json2 = JSONObject.fromObject(list.get(1));
		Set arrayList1 = json11.keySet();
		Set arrayList2 = json2.keySet();
		List s11 = new ArrayList(arrayList1);
		List s22 = new ArrayList(arrayList2);
		s11.retainAll(s22);
		Iterator it = s11.iterator();
		String a = "";
		while (it.hasNext()) {
			a = (String) it.next();
			Vehicle v = new Vehicle();
			v.setVehi_no(a);
//			System.out.println(a + "  ####");
			v.setStime(json11.get(a).toString().split("\\[")[0]);
			v.setPx(json11.get(a).toString().split("\\[")[1]);
			listjg.add(v);
		}
		zzjg.add(listqd);
		zzjg.add(listzd);
		zzjg.add(listjg);
		return FastJsonUtil.toJSONString(zzjg);
	}

	public String swczff(String stime, String etime, String jwd, String obj) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long st = 0, et = 0;
		try {
			st = sdf.parse(stime).getTime();
			et = sdf.parse(etime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(WS_URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		QName qname = new QName("http://service.com/", "GpsServicesService");
		javax.xml.ws.Service service = javax.xml.ws.Service.create(url, qname);
		GpsServicesDelegate gpsService = service
				.getPort(GpsServicesDelegate.class);
		Map<String, Object> req_ctx = ((BindingProvider) gpsService)
				.getRequestContext();
		List<String> qy = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance(), calendar2 = Calendar
				.getInstance();
		calendar.set(2016, 4, 31, 18, 30, 22);
		calendar2.set(2016, 4, 31, 18, 50, 22);
		qy.add(jwd);
		String result = gpsService.swcz3Day(qy, st, et);
		return result;
	}
	
	public String findswcz1(String qd_stime, String qd_etime, String zd_stime,
			String zd_etime, String qd_jwd, String zd_jwd) {
		List<List<Vehicle>> zzjg = new ArrayList<List<Vehicle>>();
		List<String> list = new ArrayList<String>();
		String s1 = "",s2 = "",s3 = "";
		List<Vehicle> listqd = new ArrayList<Vehicle>();
		List<Vehicle> listzd = new ArrayList<Vehicle>();
		if(qd_jwd!=null&&qd_jwd.length()>0){
			s1=swczff(qd_stime,qd_etime,qd_jwd,"1");
			JSONObject json = JSONObject.fromObject(s1);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setqd = json1.keySet();
	        Iterator it = setqd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listqd.add(v);
	    	 }  
		}
		
		if(zd_jwd!=null&&zd_jwd.length()>0){
			s2=swczff(zd_stime,zd_etime,zd_jwd,"2");
			JSONObject json = JSONObject.fromObject(s2);
	        list.add(json.getString("result0"));
	        JSONObject json1 =  JSONObject.fromObject(json.getString("result0"));
	        Set setzd = json1.keySet();
	        Iterator it = setzd.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json1.get(a).toString().split("\\[")[0]);
	    		 v.setPx(json1.get(a).toString().split("\\[")[1]);
	    		 listzd.add(v);
	    	 }  
		}
		List<Vehicle> listjg = new ArrayList<Vehicle>();
        	JSONObject json11=JSONObject.fromObject(list.get(0));
        	JSONObject json2;
        	 json2=JSONObject.fromObject(list.get(1));
        	 Set arrayList1=json11.keySet();
        	 Set arrayList2=json2.keySet();
        	 List s11 = new ArrayList(arrayList1);
        	 List s22 = new ArrayList(arrayList2);
        	 s11.retainAll(s22);
        	 Iterator it = s11.iterator();
	    	 String a = "";
	    	 while(it.hasNext()) {  
	    		 a=(String) it.next();
	    		 Vehicle v = new Vehicle();
	    		 v.setVehi_no(a);
	    		 v.setStime(json11.get(a).toString().split("\\[")[0].split(",")[0]);
	    		 v.setPx(json11.get(a).toString().split("\\[")[1].split("\\]")[0]);
	    		 listjg.add(v);
	    	 }
		return FastJsonUtil.toJSONString(listjg);
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> findGj(String stime, String etime, String vehino) {
		String sTime = stime;
		String eTime = etime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		if(sTime==null&&sTime.length()==0&&sTime.equals("null")
				&&eTime==null&&eTime.length()==0&&eTime.equals("null")){
			return Result.error(CodeMsg.DATA_ERROR);
		}else if(!sTime.substring(0, 4).equals(eTime.substring(0, 4))&&!sTime.substring(0, 4).equals(eTime.substring(0, 4))){
			return Result.error(CodeMsg.DATA_ERROR);
		}else if(vehino==null&&vehino.length()==0){
			return Result.error(CodeMsg.DATA_ERROR);
		}else{
//			String table = "hzgps_taxi.tb_gps_"+ getTable(sTime)+"@taxilink";
			String table = "tb_gps_"+ getTable(sTime);
			List<Map<String, Object>> list = tlaqDao.getVehicleList(table,vehino,sTime,eTime);
			double lc=0;
			if (list.equals(null)) {
				return Result.error(CodeMsg.DATA_ERROR);
			}
			if (list.size()>0) {
				list.get(0).put("LC", 0);
				for(int i=1;i<list.size();i++){
					lc +=GetDistance(String.valueOf(list.get(i).get("PX")),String.valueOf(list.get(i).get("PY")),String.valueOf(list.get(i-1).get("PX")),String.valueOf(list.get(i-1).get("PY")));
            		BigDecimal bg = new BigDecimal(lc);
            		double lc1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            		list.get(i).put("LC", lc1);
            	}
			}
			return Result.success(list);
		}
	}
	public List<Map<String, Object>> findGjww(String stime, String etime, String vehino) {
		String httpUrl = "http://115.236.61.148:9080/area3/history.action";
		httpUrl = httpUrl+"?vehiid="+vehino+"&stime="+stime.replace(" ", "%20")+"&etime="+etime.replace(" ", "%20");
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
        List<Map<String, Object>> list = (List<Map<String, Object>>)parseJSON2Map(String.valueOf(result)).get("vehigps");	
        double lc=0;
		if (list.size()>0) {
			for(int i=0;i<list.size();i++){
				list.get(i).put("vehicle_num", list.get(i).get("vehinum"));
				list.get(i).put("SPEED", Double.valueOf((String) list.get(i).get("speed")));
//				list.get(i).put("SPEED", String.valueOf(list.get(i).get("speed")));
				list.get(i).put("DIRECTION", list.get(i).get("direction"));
				list.get(i).put("STATE", String.valueOf(list.get(i).get("carstate")).endsWith("空车")?"0":"1");
				list.get(i).put("PX", list.get(i).get("longi"));
				list.get(i).put("PY", list.get(i).get("lati"));
				list.get(i).put("SPEED_TIME", list.get(i).get("speedtime"));
			}
			list.get(0).put("LC", 0);
			for(int i=1;i<list.size();i++){
				lc +=GetDistance(String.valueOf(list.get(i).get("PX")),String.valueOf(list.get(i).get("PY")),String.valueOf(list.get(i-1).get("PX")),String.valueOf(list.get(i-1).get("PY")));
        		BigDecimal bg = new BigDecimal(lc);
        		double lc1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        		list.get(i).put("LC", lc1);
        	}
		}
		return list;
	}
	//json to map
	public static Map<String, Object> parseJSON2Map(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = JSONObject.fromObject(jsonStr);
		for(Object k : json.keySet()){
			Object v = json.get(k);
			if(v instanceof JSONArray){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Iterator<JSONObject> it = ((JSONArray)v).iterator();
				while(it.hasNext()){
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
		
	private static double rad(String lat1){
		return (Double.parseDouble(lat1))* Math.PI / 180.00;
	}
	public static double GetDistance(String latitude1, String longitude1, String latitude2, String longitude2){	
		 double Lat1 = rad(latitude1); // 纬度
		 double Lat2 = rad(latitude2); 
		 double a = Lat1 - Lat2;//两点纬度之差
		 double b = rad(longitude1) - rad(longitude2); //经度之差
		 double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式
		 s = s * 6378137.0;//弧长乘地球半径（半径为米）
		 s = Math.round(s * 10000d) / 10000d/1000;//精确距离的数值
		 return s;
	}
	
//	@DS("datasource2")
	@DS("datasource1")
	public String findGj1(String stime, String etime, String vehino) {
		String sTime = stime;
		String eTime = etime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		if(sTime==null&&sTime.length()==0&&sTime.equals("null")
				&&eTime==null&&eTime.length()==0&&eTime.equals("null")){
			return null;
		}else if(!sTime.substring(0, 4).equals(eTime.substring(0, 4))&&!sTime.substring(0, 4).equals(eTime.substring(0, 4))){
			return null;
		}else if(vehino==null&&vehino.length()==0){
			return null;
		}else{
//			String table = "hzgps_taxi.tb_gps_"+ getTable(sTime)+"@taxilink";
			String table = "tb_gps_"+ getTable(sTime);
			List<Map<String, Object>> list = tlaqDao.getVehicleList(table,vehino,sTime,eTime);
			if (list.equals(null)) {
				return null;
			}
			double lc=0;
			if( list!=null && list.size() >0){
				for (int i = 0; i < list.size(); i++) {
					list.get(i).put("STATE", String.valueOf(list.get(i).get("STATE"))=="1"?"重车":"空车");
				}
				list.get(0).put("LC", 0);
				for(int i=1;i<list.size();i++){
					lc +=GetDistance(String.valueOf(list.get(i).get("PX")),String.valueOf(list.get(i).get("PY")),String.valueOf(list.get(i-1).get("PX")),String.valueOf(list.get(i-1).get("PY")));
					BigDecimal bg = new BigDecimal(lc);
					double lc1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					list.get(i).put("LC", lc1);
				}
			}
			return FastJsonUtil.toJSONString(list);
		}
	}
	
	public String getTable(String srctime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyMM");
		Date date = null;
		try {
			date = sdf.parse(srctime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf2.format(date);
	}
	
	@DS("datasource1")	
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> zdgzcl() {
		List<Map<String, Object>> list = tlaqDao.getZdgzcl();
		if (list.equals(null)) {
			return Result.error(CodeMsg.DATA_ERROR);
		}
		return Result.success(list);
	}

	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> delVehi(String vehi) {
		System.out.println(vehi);
		int count = tlaqDao.delVehi(vehi);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}

	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> delAllVehi() {
		int count = tlaqDao.delAllVehi();
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}
	
	@DS("datasource1")
//	@DS("datasource2")
	public Result<List<Map<String, Object>>> insVehi(String vehi, String type,String longi,String lati) {
		int count = tlaqDao.insVehi(vehi,type,longi,lati);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}

	public int insertcl(List<List<Object>> list) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count += tlaqDao.insExcelVehi(list.get(i).get(0).toString(),list.get(i).get(1).toString());
		}
		return count;
	}

	private ExcelUtil excelUtil = new ExcelUtil();
//	@DS("datasource2")
	@DS("datasource1")
	public Result<List<Map<String, Object>>> upload(MultipartFile file) throws IllegalStateException, IOException {
		if (file.isEmpty()) {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
		String fileName = file.getName();
		// 原文件名即上传的文件名
		String origFileName = file.getOriginalFilename();
		String path ="d://erxi//" + origFileName;
		File ff = new File(path);
		 // 检测是否存在目录
	    if (!ff.getParentFile().exists()) {
	    	ff.getParentFile().mkdirs();// 新建文件夹
	    }
	    if(ff.exists()){
	    	ff.delete();
    	}
		file.transferTo(ff);
		List<List<Object>> list = excelUtil.readExcel(ff);
		if (list == null) {
			return Result.error(CodeMsg.SERVER_ERROR);
		} else {
			int count = insertcl(list);
			if(count>0){
				return Result.success(null);
			}
		}
		return Result.error(CodeMsg.SERVER_ERROR);
	}

	public Result<List> cxVehi(String time,
			String between, String type, String speed, String area) {
		List<Vehicle1> list = FastJsonUtil.toList(redisService.get(VeKey.realtimeVehicle, "", String.class), Vehicle1.class);
		JSONArray  json  = JSONArray.fromObject(area);
		String str = "";
		if(json.size()>0){
			for(int i=0;i<json.size();i++){
				JSONObject job = json.getJSONObject(i); 
				str += job.get("lng")+","+ job.get("lat")+";";
			}
		}
		//设置没有速度情况下200码一下
		if(speed == null || speed.equals("")){
			speed = "200";
		}
		List lists = new ArrayList();
		for(int j=0;j<list.size();j++){
			if(rectContains(list.get(j), str) &&
					inTime(list.get(j),time,between) && 
					type.equals(list.get(j).getCarStatus()) && 
					Double.parseDouble(speed)>Double.parseDouble(list.get(j).getSpeed())){
				lists.add(list.get(j));
			}
		}
		return Result.success(lists);
	}
	
    /**
	 * 车辆是否在区域内   120.123,30.123;123.123,33.211;
	 * @param vehicle
	 * @param area
	 * @return
	 */
	private boolean rectContains(Vehicle1 vehicle, String area) {
		String[] zbs = area.split(";");
		Geometry geometry=GeometryHandler.getGeometryObject(area+""+zbs[0]);
		String xy = vehicle.getLongi() +"," + vehicle.getLati();
		Geometry g2=GeometryHandler.getGeometryObject(xy);
		return geometry.contains(g2);
	}
	
	/**
	 * 车辆是否在时间段内
	 * @param vehicle
	 * @param time
	 * @return
	 */
	private boolean inTime(Vehicle1 vehicle, String time,String between) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = myFmt.parse(time);
			Date cldate = myFmt.parse(vehicle.getDateTime().substring(0,19));
			Calendar Qnow = Calendar.getInstance();  
			Calendar Hnow = Calendar.getInstance();
			Qnow.setTime(date);  
			Hnow.setTime(date);  
			Qnow.add(Calendar.MINUTE, -30);// 日期减30分钟
			Hnow.add(Calendar.MINUTE, 30); // 日期加30分钟  
			if (cldate.after(Qnow.getTime()) && cldate.before(Hnow.getTime())) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Map<String, Object>> countComp() {
		return tlaqDao.getCompany();
	}
	
	private List<Map<String, Object>> countVehi() {
		return tlaqDao.getVehi();
	}
	private List<Map<String, Object>> countVehiYear() {
		return tlaqDao.getVehiYear();
	}
	
	private List<Map<String, Object>> countPer() {
		return tlaqDao.getPerson();
	}
	
	//计算年龄  15位身份证和18位身份证计算
	private List<Map<String, Object>> countPercount() throws Exception {
		List<Map<String, Object>> list = tlaqDao.getPersoncount();
		int count50 = 0,count5065 = 0,count65=0;
		for (int i = 0; i < list.size(); i++) {
			String l  = list.get(i).get("id_number").toString();
			//18位身份证
			int cc = 0;
			if(l.length() > 15){
				cc = countAge(l.substring(6,14));
			}else if(l.length() >= 13){
				cc = countAge("19"+l.substring(6,12));
			}
			if(cc <= 50){
				count50++;
			}else if(cc >50 && cc <= 65){
				count5065++;
			}else{
				count65++;
			}
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		Map map = new HashMap();
		map.put("l1", count50);
		map.put("l2", count5065);
		map.put("l3", count65);
		System.out.println(map.toString());
		lists.add(map);
		return lists;
	}
	
	private int countAge(String l) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    Date date = formatter.parse(l);
	    Calendar c1 = Calendar.getInstance();   //当前日期
	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(date);   //设置为另一个时间

	    int year = c1.get(Calendar.YEAR);
	    int oldYear = c2.get(Calendar.YEAR);
	    
	    return year-oldYear;
	}
	
	private List<Map<String, Object>> countMac() {
		return tlaqDao.getAllMac();
	}
	
	private List<Map<String, Object>> countIsu() {
		return tlaqDao.getIssue();
	}
	
	private List<Map<String, Object>> countAll() {
		return tlaqDao.getAll();
	}
	
	private List<Map<String, Object>> countOrder() {
		return tlaqDao.getOrder();
	}
	
	private List<Map<String, Object>> countSum() {
		return tlaqDao.getSum();
	}

	private List<Map<String, Object>> countTs() {
		return tlaqDao.getTs();
	}
	private List<Map<String, Object>> countTj() {
		return tlaqDao.getTj();
	}
	private List<Map<String, Object>> findFocusMonitor() {
		return tlaqDao.findFocusMonitor();
	}
	private List<Map<String, Object>> findFaultMonitor() {
		return tlaqDao.findFaultMonitor();
	}
	
	@DS("datasource1")	
//	@DS("datasource4")
	public  Result<List<Map<String, Object>>> bjgl(String table,String stime, String etime, Integer pageIndex, Integer pageSize, String cp) {
		List<Map<String, Object>> list =tlaqDao.getbjgl(table,stime,etime,cp,pageIndex,pageSize);
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
//	@DS("datasource4")
	public Result<List<Map<String, Object>>> spgl(String table,String stime, String etime, Integer pageIndex, Integer pageSize, String cp) {
		List<Map<String, Object>> list =tlaqDao.getspgl(table,stime,etime,cp,pageIndex,pageSize);
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
	
	
	private List<Map<String, Object>> allComp() {
		return tlaqDao.getAllComp();
	}
	
	private List<Map<String, Object>> allVehi() {
		return tlaqDao.getallVehi();
	}
	
	private List<Map<String, Object>> allArea() {
		return tlaqDao.getallArea();
	}
	
	private List<Map<String, Object>> countYycls() {
		return tlaqDao.getYycls();
	}

	public Result<List<Map<String, Object>>> qxgl(String xm) {
		List<Map<String, Object>> list = tlaqDao.getQx("%"+xm+"%");
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		int count = 0;
		if( list!=null && list.size() >0){
			count = Integer.parseInt(String.valueOf(list.size()));
		}
		Map  map = new HashMap ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return Result.success(lists);
	}

	public Result<List<Map<String, Object>>> qxglEdit(String id, String qx, String qxname) {
		int count = tlaqDao.qxglEdit(id,qx,qxname);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}
	
	public Result<List<Map<String, Object>>> findyhsx(String stime, String etime, String name, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list =tlaqDao.findyhsx(stime,etime,name,pageIndex,pageSize);
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

	public Result<List<Map<String, Object>>> addUser(String user, String salt, String password) {
		List<Map<String, Object>> list= tlaqDao.findUser(user);
		if(list.size() > 0){
			return Result.error(CodeMsg.BIND_ERROR);
		}else{
			int count = tlaqDao.addUser(user,salt,password);
			if(count > 0){
				return Result.error(CodeMsg.SUCCESS);
			}else{
				return Result.error(CodeMsg.DATA_ERROR);
			}
		}
	}
	public Result<List<Map<String, Object>>> editUser(String id,String user, String salt, String password) {
		List<Map<String, Object>> list= tlaqDao.findEditUser(user,id);
		if(list.size() > 0){
			return Result.error(CodeMsg.BIND_ERROR);
		}else{
			int count = tlaqDao.editUser(id,user,salt,password);
			if(count > 0){
				return Result.error(CodeMsg.SUCCESS);
			}else{
				return Result.error(CodeMsg.DATA_ERROR);
			}
		}
	}
	
	public Result<List<Map<String, Object>>> deleteUser(String id) {
		int count = tlaqDao.deleteUser(id);
		if(count > 0){
			return Result.error(CodeMsg.SUCCESS);
		}else{
			return Result.error(CodeMsg.DATA_ERROR);
		}
	}

}
