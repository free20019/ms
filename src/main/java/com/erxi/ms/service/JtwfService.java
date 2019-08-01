package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.JtwfDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 *
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */
@Service
public class JtwfService {

	@Autowired
	private JtwfDao jtwfDao;

	/**
	 * 
	 * <p>
	 * Title: getSelectJtwfService
	 * </p>
	 */
	/*
	 * @DS("datasource1") public Result<List<Map<String, Object>>>
	 * getSelectJtwfService() { List<Map<String, Object>> list =
	 * jtwfDao.getFindAllJtwfDao(); return Result.success(list);
	 * 
	 * }
	 */

	/*
	 * @DS("datasource2") public Result<List<Map<String, Object>>>
	 * getSelectJtwfService() { List<Map<String, Object>> list =
	 * jtwfDao.getFindAllJtwfDao(); return Result.success(list); =======
	 * /*@DS("datasource1") public Result<List<Map<String, Object>>>
	 * getSelectJtwfService() { List<Map<String, Object>> list =
	 * jtwfDao.getFindAllJtwfDao(); return Result.success(list); >>>>>>> .r11609
	 * 
	 * /** <p> Title: getUpdateJtwfService </p>
	 * 
	 */
	@DS("datasource1")
	public Integer getUpdateJtwfService(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
			@Param("cljg") String cljg, @Param("kf") String kf, @Param("zfjgfk") String zfjgfk,
			@Param("gsfk") String gsfk) {

		Integer integer = jtwfDao.getUpdateJtwfDao(bid, cph, xm, wzsj, wzdd, wznr, cljg, kf, zfjgfk, gsfk);
		return integer;
	}

	/**
	 * 
	 * <p>
	 * Title: 搜索JtwfService
	 * </p>
	 */
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNameJtwfService(@Param("cph") String cph) {

		List<Map<String, Object>> list = jtwfDao.getSelectNameJtwfDao(cph);
		//System.out.println("交通违法"+list);
		return Result.success(list);
	}

	/**
	 * 
	 * <p>
	 * Title: getDeleteJtwfService
	 * </p>
	 * 
	 */
	@DS("datasource1")
	public Integer getDeleteJtwfService(String bid) {
		Integer integer = jtwfDao.getDeleteJtwfDao(bid);


		return integer;
	}

	/**
	 * 
	 * <p>
	 * Title: getInsertJtwfService
	 * 
	 */
	@DS("datasource1")
	public Integer getInsertJtwfService(@Param("cph") String cph, @Param("xm") String xm, @Param("wzsj") String wzsj,
			@Param("wzdd") String wzdd, @Param("wznr") String wznr, @Param("cljg") String cljg, @Param("kf") String kf,
			@Param("zfjgfk") String zfjgfk, @Param("gsfk") String gsfk) {
		Integer insert = jtwfDao.getInsertJtwfDao(cph, xm, wzsj, wzdd, wznr, cljg, kf, zfjgfk, gsfk);
		return insert;
	}
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectJtwfService(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = jtwfDao.getFindAllJtwfDao(cph, xm, pageIndex, pageSize);

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
	public String getSelectJtwfServiceExport(String cph, String xm) {
		List<Map<String, Object>> list = jtwfDao.getSelectNameJtwfDaoExport(cph, xm);
		return FastJsonUtil.toJSONString(list);
	}

}
