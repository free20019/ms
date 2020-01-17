package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KwmclDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 1.1.3.2.7文明车辆
 * @author HXX
 *
 */

@Service
public class KwmclService {
	
	
	@Autowired
	private KwmclDao wmclDao;
	
	/*@DS("datasource1")
	public Result<List<Map<String , Object>>> getKwmclService(){
		List<Map<String, Object>> kwmcl = wmclDao.getKwmcl();
		return Result.success(kwmcl);
		
	}*/
	
	/**
	 * update
	 * @param bid
	 * @return
	 */
	@DS("datasource1")
	public Integer getUpdateKwmclService(@Param("bid") String bid,@Param("cph") String cph,@Param("pdrq") String pdrq,@Param("nf") String nf,
			@Param("rymc") String rymc,@Param("xjqk") String xjqk,@Param("bz") String bz){
		Integer integer = wmclDao.getUpdatewmcl(bid, cph, pdrq, nf, rymc, xjqk, bz);
		return integer;
		
	}
	
	/**
	 * delete
	 */
	@DS("datasource1")
	public Integer getDeleteKwmclService(String bid){
		Integer integer = wmclDao.getDeleteKwmclDao(bid);
		return integer;
		
	}
	
	/**
	 * 查询
	 * @param cph
	 * @return
	 */
	
	@DS("datasource1")
	public Result<List<Map<String , Object>>> getSelectName(@Param("cph") String cph){
		 List<Map<String, Object>> list = wmclDao.getSelectNameKwmclDao(cph);
		 return Result.success(list);
		
	}
	
	
	@DS("datasource1")
	public Integer getInsertKwmclService(@Param("cph") String cph,@Param("pdrq") String pdrq,@Param("nf") String nf,@Param("rymc") String rymc,@Param("xjqk") String xjqk,
			@Param("bz") String bz) {
		Integer integer=wmclDao.getInsertwmcl(cph, pdrq, nf, rymc, xjqk, bz);
		
		return integer;
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getKwmcFindAlllService(String cph, String rymc, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = wmclDao.getKwmclFindAllDao(cph,rymc,pageIndex,pageSize);
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
	public String getFindAllsExport(String cph, String rymc) {
		List<Map<String, Object>> list = wmclDao.getFindAllExportlDao(cph, rymc);
		return FastJsonUtil.toJSONString(list);
	}
	
}
