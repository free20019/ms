package com.erxi.ms.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YdcsdqxjlctjDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
import com.vividsolutions.jts.operation.distance.DistanceOp;

/**
 * @ClassName 单车速度曲线及车辆统计服务层
 * @dOscription TODO
 * @Author HXX
 * @Date 2018/11/13 15:36
 * @Version 1.0
 **/


@Service
public class YdcsdqxjlctjService {

    @Autowired
    private YdcsdqxjlctjDao ydcsdqxjlctjDao;

    @DS("datasource3")
    public Result<List<Map<String, Object>>> getYdcsdqxjlctjServices(@Param("kssj") String kssj, @Param("jssj") String jssj, @Param("cph")
            String cph, @Param("csz") String csz
//            ,@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize
            ) {

        List<Map<String, Object>> list = ydcsdqxjlctjDao.getYdcsdqxjlctjDaoFindAll(kssj, jssj, cph, csz
//        		,pageIndex,pageSize
        		);

        int count=0;
        
        for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				list.get(0).put("lc","0");
			}else {
				String last_longi = list.get(i-1).get("LONGI").toString();
				String last_lati = list.get(i-1).get("LATI").toString();
				String longi = list.get(i).get("LONGI").toString();
				String lati = list.get(i).get("LATI").toString();
				String lc = distance(last_longi,last_lati,longi,lati);
				list.get(i).put("lc",lc);
			}
		}
//        if(list!=null&& list.size()>0){
//            count=Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
//        }
        List<Map<String, Object>> arrayList = new ArrayList<Map<String ,Object>>();
        Map map=new HashMap();
        map.put("count", count);
        map.put("list", list);
        arrayList.add(map);

        return Result.success(arrayList);


    }
    private static double EARTH_RADIUS = 6378.137;
    
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

    private String distance(String last_longi, String last_lati, String longi, String lati) {
    	double radLat1 = rad(Double.parseDouble(last_lati));
		double radLat2 = rad(Double.parseDouble(lati));
		double a = radLat1 - radLat2;
		double b = rad(Double.parseDouble(last_longi)) - rad(Double.parseDouble(longi));
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s+"";
	}

	/**
     * Export
     */

    @DS("datasource3")
    public String getYdcsdqxjlctjServicesExport(@Param("kssj") String kssj, @Param("jssj") String jssj, @Param("cph")
            String cph, @Param("csz") String csz) {

        List<Map<String, Object>> list = ydcsdqxjlctjDao.getYdcsdqxjlctjDaoFindAllExport(kssj, jssj, cph, csz);
        DecimalFormat df = new DecimalFormat(".00");
        for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				list.get(0).put("LC","0");
			}else {
				String last_longi = list.get(i-1).get("LONGI").toString();
				String last_lati = list.get(i-1).get("LATI").toString();
				String longi = list.get(i).get("LONGI").toString();
				String lati = list.get(i).get("LATI").toString();
				String lc = distance(last_longi,last_lati,longi,lati);
				list.get(i).put("LC",df.format(Float.valueOf(lc)/100));
			}
		}
        return FastJsonUtil.toJSONString(list);
    }

    @DS("datasource1")
    public Result<List<Map<String, Object>>> getYdcsdqxjlctjServicesName(String cph) {

        List<Map<String, Object>> list = ydcsdqxjlctjDao.getYdcsdqxjlctjDaoFindAllName(cph);

        return Result.success(list);
    }




































   /* @DS("datasource1")
    public String getYdcsdqxjlctjExport(@Param("kssj") String kssj, @Param("jssj") String jssj, @Param("gs") String gs, @Param("cph")
            String cph, @Param("csz") String csz) {

        HashMap map = new HashMap();
        map.put("datas","");


        return FastJsonUtil.toJSONString(map);
    }*/


}
