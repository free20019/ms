package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YyybgDao;
import com.erxi.ms.result.FastJsonUtil;

/**
 * @ClassName YyybgService
 * @dOscription TODO
 * @Author HXX
 * @Date 2018/11/13 18:20
 * @Version 1.0
 **/
@Service
public class YyybgService {

    @Autowired
    private YyybgDao yybgDao;

    private Map<String, Object> map;
    @DS("datasource1")
    public String getYyybgFindAllService(@Param("n") String n, @Param("y") String y,  @Param("d") String d, 
    		@Param("stime") String stime, @Param("etime") String etime,@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize){
    	List<Map<String, Object>> list = null;
    	String year = n;
    	String date="";
    	if(y.equals("null")){
    		date=year;
    		list = yybgDao.getFindAllYynbgDao(date,stime,etime,pageIndex,pageSize);
    	}else if(d.equals("null")){
    		String month = y;
    		date=year+month;
    		list = yybgDao.getFindAllYyybgDao(date,stime,etime,pageIndex,pageSize);
    	}else {
    		String month = y;
    		String day = d;
    		date=year+month+day;
    		String str=year+month;
    		list = yybgDao.getFindAllYyrbgDao(date,str,stime,etime,pageIndex,pageSize);
    	}
    	int count = 0;
		if(y.equals("null")){
			count = 12;
    	}else{
    		if( list!=null && list.size() >0){
    			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
    		}
    	}
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		map = new HashMap<String, Object> ();
		map.put("count", count);
		map.put("datas",list);
		lists.add(map);
		return FastJsonUtil.toJSONString(lists);
    }


    /**
     * Export
     * @return
     */
    @DS("datasource1")
    public List<Map<String, Object>> getYyybgServiceExport( @Param("n") String n, @Param("y") String y,  @Param("d") String d,@Param("stime") String stime, @Param("etime") String etime) {
    	List<Map<String, Object>> list = null;
    	String year = n;
    	String date="";
    	if(y.equals("null")){
    		date=year;
    		list = yybgDao.getFindAllYynbgDaodc(date,stime,etime);
    	}else if(d.equals("null")){
    		String month = y;
    		date=year+month;
    		list = yybgDao.getFindAllYyybgDaodc(date,stime,etime);
    	}else {
    		String month = y;
    		String day = d;
    		date=year+month+day;
    		String str=year+month;
    		list = yybgDao.getFindAllYyrbgDaodc(date,str,stime,etime);
    	}
        return list;
    }
}
