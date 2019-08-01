package com.erxi.ms.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.InitDao;
import com.erxi.ms.domain.Vehicle1;
import com.erxi.ms.entity.Area;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.redis.VeKey;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.GeometryHandler;
import com.vividsolutions.jts.geom.Geometry;

@Service
public class GisService {
	
	@Autowired
	RedisService redisService;

	@Autowired
	InitDao initDao;
	
	@Transactional
	public void task(){
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        //延时1秒执行,每2分钟执行一次
        service.scheduleAtFixedRate(new Runnable() {
        	private List<Vehicle1> vehilist = new ArrayList<Vehicle1>();
        	private List<Area> arealist = new ArrayList<Area>();
            @Override
            public void run() {
            		System.out.println("-----vehi redis init-----");
					vehilist = findAllVhic();
					redisService.set(VeKey.realtimeVehicle,"",FastJsonUtil.toJSONString(vehilist));
					
					arealist = findAllArea();
					for(int j=0;j<vehilist.size();j++){
						for(int k=0;k<arealist.size();k++){
							if(rectContains(vehilist.get(j), arealist.get(k))){
								arealist.get(k).getAll().add(vehilist.get(j).getVehino()+";"+vehilist.get(j).getCompname());
								break;
							}
						}
					}
					redisService.set(VeKey.realtimeArea,"",FastJsonUtil.toJSONString(arealist));
					
					System.out.println("-----vehi redis over-----");
				}
        }, 0, 20, TimeUnit.SECONDS);
	}

	// 查询所有车辆信息
	@DS("datasource1")
//	@DS("datasource3")
	public List<Vehicle1> findAllVhic() {
		List<Vehicle1> vhic = new ArrayList<Vehicle1>();
		List<Map<String, Object>> list = initDao.getVehicleList();
		for (int i = 0; i < list.size(); i++) {
			Vehicle1 vehi = new Vehicle1();
			vehi.setCompname(list.get(i).get("COMP_NAME") == null ? "" : list
					.get(i).get("COMP_NAME").toString());
			vehi.setVehino(list.get(i).get("VEHI_NO") == null ? "" : list
					.get(i).get("VEHI_NO").toString());
			vehi.setSimka(list.get(i).get("SIM_NUM") == null ? "" : list.get(i)
					.get("SIM_NUM").toString());
			vehi.setMdt_no(list.get(i).get("MDT_NO") == null ? "" : list.get(i)
					.get("MDT_NO").toString());
			vehi.setCartype(list.get(i).get("VT_NAME") == null ? "" : list
					.get(i).get("VT_NAME").toString());
			vehi.setVehisim(list.get(i).get("VEHI_SIM") == null ? "" : list
					.get(i).get("VEHI_SIM").toString());
//			vehi.setMdtno(list.get(i).get("MT_NAME") == null ? "" : list.get(i)
//					.get("MT_NAME").toString());
			vehi.setOwnname(list.get(i).get("OWN_NAME") == null ? "" : list
					.get(i).get("OWN_NAME").toString());
			vehi.setOwntel(list.get(i).get("OWN_TEL") == null ? "" : list
					.get(i).get("OWN_TEL").toString());
			vehi.setColor(list.get(i).get("VC_NAME") == null ? "" : list.get(i)
					.get("VC_NAME").toString());

			vehi.setDateTime(list.get(i).get("STIME") == null ? "" : list
					.get(i).get("STIME").toString());
			vehi.setCarStatus(list.get(i).get("STATE") == null ? "" : list
					.get(i).get("STATE").toString());
			
			vehi.setCarState(list.get(i).get("CARSTATE") == null ? "" : list
					.get(i).get("CARSTATE").toString());
			
			vehi.setHeading(list.get(i).get("ANGLE") == null ? "" : list
					.get(i).get("ANGLE").toString());
//			vehi.setGpsStatus(list.get(i).get("GPS_STATUS") == null ? "" : list
//					.get(i).get("GPS_STATUS").toString());
			vehi.setLati(list.get(i).get("PY") == null ? 0 : Double
					.parseDouble(list.get(i).get("PY").toString()));
			vehi.setLongi(list.get(i).get("PX") == null ? 0 : Double
					.parseDouble(list.get(i).get("PX").toString()));
			vehi.setSpeed(list.get(i).get("SPEED") == null ? "" : list.get(i)
					.get("SPEED").toString());
			if (vehi.getDateTime() != "" && jisuan(vehi.getDateTime())) {
				vehi.setOnoffstate("1");
			} else {
				vehi.setOnoffstate("0");
			}
			vhic.add(vehi);
		}
		return vhic;
	}
	
	
	
	//查询所有区域
	@DS("datasource1")
	public List<Area> findAllArea() {
		List<Area>area=new ArrayList<Area>();
		List<Map<String, Object>> list = initDao.getAreaList();
		for(int i=0;i<list.size();i++){
			Area a=new Area();
			a.setId(list.get(i).get("area_id")==null?"":list.get(i).get("area_id").toString());
			a.setAreaname(list.get(i).get("area_name")==null?"":list.get(i).get("area_name").toString());
			a.setAreasize(list.get(i).get("AREA_SIZE")==null?"":list.get(i).get("AREA_SIZE").toString());
			a.setAreams(list.get(i).get("AREA_DESCRIBE")==null?"":list.get(i).get("AREA_DESCRIBE").toString());
			a.setAreazbs(list.get(i).get("AREA_COORDINATES")==null?"":list.get(i).get("AREA_COORDINATES").toString());
			a.setOrderid(list.get(i).get("ORDERID")==null?"":list.get(i).get("ORDERID").toString());
			a.setAll(new ArrayList<String>());
			String nums = list.get(i).get("ALARMNUM")==null?"10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;10;":list.get(i).get("ALARMNUM").toString();
			
			for(int i1=0;i1<nums.split(";").length;i1++){
				a.setAreabjs(nums.split(";")[getybjnum()]+"");
			}
			area.add(a);
		}
		return area;
	}
	
	
	/**
	 * 车辆是否在区域内
	 * @param vehicle
	 * @param area
	 * @return
	 */
	private boolean rectContains(Vehicle1 vehicle, Area area) {
		String[] zbs = area.getAreazbs().split(";");//120.123,30.123;123.123,33.211;
		Geometry geometry=GeometryHandler.getGeometryObject(area.getAreazbs()+";"+zbs[0]);
		String xy = vehicle.getLongi() +"," + vehicle.getLati();
		Geometry g2=GeometryHandler.getGeometryObject(xy);
		return geometry.contains(g2);
	}

	
	
	//根据时间添加区域预警数
	public int getybjnum(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		//System.out.println(sdf.format(new Date()));
		String t = sdf.format(new Date());
		int a = timenum(t);
		if(a==timenum("23:58")||a==timenum("23:59")||a==timenum("00:02")||a==timenum("00:01")){
			return 0;
		}else if(a>=timenum("00:03")&&a<=timenum("00:57")){
			return 1;
		}else if(a>=timenum("00:58")&&a<=timenum("01:02")){
			return 2;
		}else if(a>=timenum("01:03")&&a<=timenum("01:57")){
			return 3;
		}else if(a>=timenum("01:58")&&a<=timenum("02:02")){
			return 4;
		}else if(a>=timenum("02:03")&&a<=timenum("02:57")){
			return 5;
		}else if(a>=timenum("02:58")&&a<=timenum("03:02")){
			return 6;
		}else if(a>=timenum("03:03")&&a<=timenum("03:57")){
			return 7;
		}else if(a>=timenum("03:58")&&a<=timenum("04:02")){
			return 8;
		}else if(a>=timenum("04:03")&&a<=timenum("04:57")){
			return 9;
		}else if(a>=timenum("04:58")&&a<=timenum("05:02")){
			return 10;
		}else if(a>=timenum("05:03")&&a<=timenum("05:57")){
			return 11;
		}else if(a>=timenum("05:58")&&a<=timenum("06:02")){
			return 12;
		}else if(a>=timenum("06:03")&&a<=timenum("06:57")){
			return 13;
		}else if(a>=timenum("06:58")&&a<=timenum("07:02")){
			return 14;
		}else if(a>=timenum("07:03")&&a<=timenum("07:57")){
			return 15;
		}else if(a>=timenum("07:58")&&a<=timenum("08:02")){
			return 16;
		}else if(a>=timenum("08:03")&&a<=timenum("08:57")){
			return 17;
		}else if(a>=timenum("08:58")&&a<=timenum("09:02")){
			return 18;
		}else if(a>=timenum("09:03")&&a<=timenum("09:57")){
			return 19;
		}else if(a>=timenum("09:58")&&a<=timenum("10:02")){
			return 20;
		}else if(a>=timenum("10:03")&&a<=timenum("10:57")){
			return 21;
		}else if(a>=timenum("10:58")&&a<=timenum("11:02")){
			return 22;
		}else if(a>=timenum("11:03")&&a<=timenum("11:57")){
			return 23;
		}else if(a>=timenum("11:58")&&a<=timenum("12:02")){
			return 24;
		}else if(a>=timenum("12:03")&&a<=timenum("12:57")){
			return 25;
		}else if(a>=timenum("12:58")&&a<=timenum("13:02")){
			return 26;
		}else if(a>=timenum("13:03")&&a<=timenum("13:57")){
			return 27;
		}else if(a>=timenum("13:58")&&a<=timenum("14:02")){
			return 28;
		}else if(a>=timenum("14:03")&&a<=timenum("14:57")){
			return 29;
		}else if(a>=timenum("14:58")&&a<=timenum("15:02")){
			return 30;
		}else if(a>=timenum("15:03")&&a<=timenum("15:57")){
			return 31;
		}else if(a>=timenum("15:58")&&a<=timenum("16:02")){
			return 32;
		}else if(a>=timenum("16:03")&&a<=timenum("16:57")){
			return 33;
		}else if(a>=timenum("16:58")&&a<=timenum("17:02")){
			return 34;
		}else if(a>=timenum("17:03")&&a<=timenum("17:57")){
			return 35;
		}else if(a>=timenum("17:58")&&a<=timenum("18:02")){
			return 36;
		}else if(a>=timenum("18:03")&&a<=timenum("18:57")){
			return 37;
		}else if(a>=timenum("18:58")&&a<=timenum("19:02")){
			return 38;
		}else if(a>=timenum("19:03")&&a<=timenum("19:57")){
			return 39;
		}else if(a>=timenum("19:58")&&a<=timenum("20:02")){
			return 40;
		}else if(a>=timenum("20:03")&&a<=timenum("20:57")){
			return 41;
		}else if(a>=timenum("20:58")&&a<=timenum("21:02")){
			return 42;
		}else if(a>=timenum("21:03")&&a<=timenum("21:57")){
			return 43;
		}else if(a>=timenum("21:58")&&a<=timenum("22:02")){
			return 44;
		}else if(a>=timenum("22:03")&&a<=timenum("22:57")){
			return 45;
		}else if(a>=timenum("22:58")&&a<=timenum("23:02")){
			return 46;
		}else if(a>=timenum("23:03")&&a<=timenum("23:57")){
			return 47;
		}else{
			return 0;
		}
	}

	public int timenum(String arg){
		return Integer.parseInt(arg.split(":")[0])*60+Integer.parseInt(arg.split(":")[1]);
	}
	
	// 计算车辆是否上线
	public static boolean jisuan(String date1) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		double result = 0;
		try {
			Date start = sdf.parse(date1);
			Date end = new Date();
			long cha = end.getTime() - start.getTime();
			result = cha * 1.0 / (1000 * 60);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (result <= 5) {
			return true;
		} else {
			return false;
		}
	}
}
