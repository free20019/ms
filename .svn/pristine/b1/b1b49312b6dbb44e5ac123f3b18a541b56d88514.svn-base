package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KryglDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 荣誉管理
 * @author HXX
 *
 */

@Service
public class KryglService {
	

	
	@Autowired
	private KryglDao ryglDao;
	
	/*@DS("datasource1")
	public Result<List<Map<String ,Object>>> getByryg(){
		List<Map<String, Object>> krygl = ryglDao.getByKrygl();
		return Result.success(krygl);
	}*/
	
	
	@DS("datasource1")
	public Integer getUpdateKryglService(@Param("bid") String bid,@Param("cph") String cph,@Param("sjxm") String sjxm,@Param("pdrq") String pdrq,@Param("rymc") String rymc,
			@Param("nf") String nf,@Param("jlje") String jlje,@Param("xj") String xj,@Param("bz") String bz){
		
		Integer updateKryg = ryglDao.getUpdateKryglDao(bid, cph, sjxm, pdrq, rymc, nf, jlje, xj, bz);
		
		return updateKryg;
		
	}

	/**
	 * delete
	 * @param bid
	 * @return
	 */
	@DS("datasource1")
	public Integer getDeleteKryglService(String bid) {
		Integer result=ryglDao.getDeleteKryglDao(bid);
		return result;
	}
	
	
	/**
	 * 查询车牌和司机
	 * @return
	 */
	@DS("datasource1")
	public Result<List<Map<String , Object>>> getSelectNameKryglService(@Param("cph") String cph){
		List<Map<String, Object>> list = ryglDao.getNameKryglDao(cph);
		return Result.success(list);
		
	}

	@DS("datasource1")
	public Integer getInsertKryglService(@Param("cph") String cph,@Param("sjxm") String sjxm,@Param("pdrq") String pdrq,@Param("rymc") String rymc,
			@Param("nf") String nf,@Param("jlje") String jlje,@Param("xj") String xj,@Param("bz") String bz) {
		
		
		Integer updateKryg = ryglDao.getInsertKryglDao(cph, sjxm, pdrq, rymc, nf, jlje, xj, bz);
		return updateKryg;
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getByrygFindAllService(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = ryglDao.getByKryglFindAll(cph,xm,pageIndex,pageSize);
		int count = 0;

		if (list != null && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("count", count);
		map.put("datas", list);
		lists.add(map);

		return Result.success(lists);
	}
	@DS("datasource1")
	public String getByrygFindAllServiceExport(String cph, String xm) {
		List<Map<String, Object>> list = ryglDao.getByKryglFindAllExport(cph,xm);
		return FastJsonUtil.toJSONString(list);
	}
	
	
	
}
