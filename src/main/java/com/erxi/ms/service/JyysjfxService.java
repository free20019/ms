package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.JyysjfxfDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 营运数据分析服务	

* @author 小坏  

* @date 2018年10月29日  下午4:29:50
 */

@Service
public class JyysjfxService {
	
	@Autowired
	private JyysjfxfDao jyysjfxfDao;
	
	
	@DS("datasource1")
	public String getFindAllJyysjfxService(String start,String stop,Integer pageIndex,Integer pageSize){
		List<Map<String, Object>> list = jyysjfxfDao.getFindAlJysjlDao(start, stop, pageIndex, pageSize);
		int count=0;
		if(list!=null&& list.size()>0){
			count=Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Map<String, Object>> Lists = new ArrayList<Map<String ,Object>>();
		Map map=new HashMap();
		map.put("count", count);
		map.put("datas", list);
		Lists.add(map);
		return FastJsonUtil.toJSONString(Lists);
		
	}
	@DS("datasource1")
	public List<Map<String, Object>> getFindAllJyysjfxServicExport(String start,String stop){
		List<Map<String, Object>> list = jyysjfxfDao.getFindAlJysjlDaExport(start, stop);
		return list;
		
	}

	@DS("datasource1")
    public Result<List<Map<String, Object>>> getFindAllJyysjfxServiceName(String cph) {

		List<Map<String, Object>> list = jyysjfxfDao.getFindAlJysjlDaoName(cph);

		return Result.success(list);

	}
	

}
