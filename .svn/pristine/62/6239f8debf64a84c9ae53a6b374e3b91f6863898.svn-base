package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KfwtsDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

@Service
public class KfwtsService {
	
	@Autowired
	private KfwtsDao fwtsDao;

	@DS("datasource1")
	public Integer getUpdateKfwtsDao(@Param("bid") String bid, @Param("cph") String cph, @Param("bsjs") String bsjs,
			@Param("ccsj") String ccsj, @Param("slsj") String slsj, @Param("lxfs") String lxfs,
			@Param("tsr") String tsr, @Param("dcqk") String dcqk, @Param("tssy") String tssy,
			@Param("tsyj") String tsyj, @Param("cljg") String cljg) {

		Integer integer = fwtsDao.getUpdateKfwtsDao(bid, cph, bsjs, ccsj, slsj, lxfs, tsr, dcqk, tssy, tsyj, cljg);
		return integer;

	}

/*	@DS("datasource1")
	public Result<List<Map<String, Object>>> getBybljl() {
		List<Map<String, Object>> list = fwtsDao.getFindAll();
		return Result.success(list);
	}*/

	/**
	 * 删除
	 * 
	 * @param bid
	 * @return
	 */
	@DS("datasource1")
	public Integer getDeleteKfwtsKfwtsService(String bid) {
		Integer integer = fwtsDao.getDeleteKfwtsDao(bid);
		return integer;
	}

	/**
	 * 查询name and sj
	 * 
	 * @param cph
	 * @return
	 */
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNamefwtsKfwtsService(@Param("cph") String cph) {

		List<Map<String, Object>> list = fwtsDao.getSelectNameKfwtsDao(cph);
		return Result.success(list);

	}

	/**
	 * 
	 * <p>
	 * Title: getUpdateKfwtsDao
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * 
	 */
	@DS("datasource1")
	public Integer getInsertKfwtsDao(@Param("cph") String cph, @Param("bsjs") String bsjs, @Param("ccsj") String ccsj,
			@Param("slsj") String slsj, @Param("lxfs") String lxfs, @Param("tsr") String tsr,
			@Param("dcqk") String dcqk, @Param("tssy") String tssy, @Param("tsyj") String tsyj,
			@Param("cljg") String cljg) {

		Integer integer = fwtsDao.getInsertfwtsDao(cph, bsjs, ccsj, slsj, lxfs, tsr, dcqk, tssy, tsyj, cljg);

		return integer;
	}
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getBybljlFindAllService(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = fwtsDao.getFindAll(cph,xm,pageIndex,pageSize);
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
	public String getBybljlFindAllServiceExport(String cph, String xm) {
		List<Map<String, Object>> list = fwtsDao.getFindAllExport(cph,xm);
		HashMap map = new HashMap();
		map.put("datas", list);

		return FastJsonUtil.toJSONString(map);
		
		
	}

}
