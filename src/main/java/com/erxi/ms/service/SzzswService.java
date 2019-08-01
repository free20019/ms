/**  

* <p>Title: SzzswService.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: www.baidudu.com</p>  

* @author shenlan  

* @date 2018年10月16日  

* @version 1.0  

*/
package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.SzzswDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 失主找失物
 * 
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@Service
public class SzzswService {

	@Autowired
	private SzzswDao szzswDao;

/*	@DS("datasource1")
	public Result<List<Map<String, Object>>> getFindAllSzzswService() {
		List<Map<String, Object>> list = szzswDao.getFindAllSzzswDao();
		return Result.success(list);

	}*/

	/**
	 * 
	 * <p>
	 * Title: getDeleteSzzswService
	 * 
	 */
	@DS("datasource1")
	public Integer getDeleteSzzswService(@Param("bid") String bid) {
		Integer integer = szzswDao.getDeleteSzzswDao(bid);
		return integer;
	}

	/**
	 * 
	 * <p>
	 * Title: getUpdateSzzswService
	 * </p>
	 */
	@DS("datasource1")
	public Integer getUpdateSzzswService(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
			@Param("szxm") String szxm, @Param("yswp") String yswp, @Param("ccsj") String ccsj,
			@Param("qssj") String qssj, @Param("wz") String wz, @Param("xzjg") String xzjg) {
		Integer integer = szzswDao.getUpdateSzzswDao(bid, cph, sjxm, szxm, yswp, ccsj, qssj, wz, xzjg);
		return integer;
	}

	/**
	 * 搜索
	 * 
	 * <p>
	 * Title: getDeleteSzzswService Description:
	 * </p>
	 */
	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNameService(@Param("cph") String cph,
			@Param("sjxm") String sjxm) {
		List<Map<String, Object>> list = szzswDao.getFindAllNameSzzswDao(cph, sjxm);
		return Result.success(list);

	}*/

	/**
	 * 添加
	 */
	@DS("datasource1")
	public Integer getInSertService(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("szxm") String szxm,
			@Param("yswp") String yswp, @Param("ccsj") String ccsj, @Param("qssj") String qssj, @Param("wz") String wz,
			@Param("xzjg") String xzjg) {
		return szzswDao.getInsertzzswDao(cph, sjxm, szxm, yswp, ccsj, qssj, wz, xzjg);

	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getFindAllSzzswServices(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		
		List<Map<String, Object>> list = szzswDao.getFindAllSzzswDaos(cph,xm,pageIndex,pageSize);

		int count =0;
		if(list!=null&& list.size()>0){
			count =Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Map<String, Object>> arrayList = new ArrayList<Map<String ,Object>>();
		HashMap map = new HashMap();
		map.put("count", count);
		map.put("datas", list);
		arrayList.add(map);
		return Result.success(arrayList);
	}


	@DS("datasource1")
    public String getFindAllSzzswServicesExport(String cph, String xm) {
		List<Map<String , Object>> list=szzswDao.getFindAllSzzswDaosExport(cph,xm);
		return FastJsonUtil.toJSONString(list);
	}

	/*
	*
	* SOU
	* */
	@DS("datasource1")
    public Result<List<Map<String, Object>>> FindAllSzzswServiceName(@Param("cph") String cph) {
		List<Map<String, Object>> Name=szzswDao.SeleteSzzswDaoName(cph);
		return Result.success(Name);
	}

}