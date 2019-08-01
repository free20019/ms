package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KhrhsDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 好人好事
 * 
 * @author HXX
 *
 */
@Service
public class KhrhsService {

	@Autowired
	private KhrhsDao hrhsDao;

	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getByFindAllhrhs() {
		List<Map<String, Object>> list = hrhsDao.getByhrhs();
		System.out.println(list);
		return Result.success(list);
	}*/

	/**
	 * 下拉框
	 * 
	 * @return
	 */

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getByFindjllb() {
		List<Map<String, Object>> list = hrhsDao.getjllb();

		return Result.success(list);

	}

	/**
	 * Update
	 * 
	 * @param bid
	 * @param cph
	 * @param xm
	 * @param sj
	 * @param nr
	 * @param jllb
	 * @param jlje
	 * @param jfqk
	 * @param jllx
	 * @return
	 */
	@DS("datasource1")
	public Integer getUpdateKhrhsService(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("sj") String sj, @Param("nr") String nr, @Param("jllb") String jllb, @Param("jlje") String jlje,
			@Param("jfqk") String jfqk, @Param("jllx") String jllx) {
		Integer updateKhrhsDao = hrhsDao.getUpdateKhrhsDao(bid, cph, xm, sj, nr, jllb, jlje, jfqk, jllx);
		return updateKhrhsDao;

	}
	/**
	 * delete
	 * 
	 * @param bid
	 * @return
	 */

	@DS("datasource1")
	public Integer getDeleteKhrhsService(String bid) {
		Integer integer = hrhsDao.getDeleteKhrhsDao(bid);
		return integer;
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNameService(@Param("cph") String cph, @Param("xm") String xm) {
		List<Map<String, Object>> list = hrhsDao.getSelectNameKhrhsDao(cph, xm);
		return Result.success(list);
	}

	/**
	 * 
	 * <p>
	 * Title: getUpdateKhrhsService
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param cph
	 * @param xm
	 * @param sj
	 * @param nr
	 * @param jllb
	 * @param jlje
	 * @param jfqk
	 * @param jllx
	 * @return
	 * 
	 */
	@DS("datasource1")
	public Integer getInserthrhsService(@Param("cph") String cph, @Param("xm") String xm, @Param("sj") String sj,
			@Param("nr") String nr, @Param("jllb") String jllb, @Param("jlje") String jlje, @Param("jfqk") String jfqk,
			@Param("jllx") String jllx) {

		Integer integer = hrhsDao.getInsertKhrhsDao(cph, xm, sj, nr, jllb, jlje, jfqk, jllx);
		return integer;
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getByFindAllhrhs(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = hrhsDao.getByhrhsFindAllDao(cph,xm,pageIndex,pageSize);
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
	public String gethrhsdc(String cph, String xm) {
		List<Map<String, Object>> list = hrhsDao.gethrhsdc(cph,xm);
		return FastJsonUtil.toJSONString(list);
	}
	//SelectNmae
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getInserthrhsServiceName(String cph) {
		List<Map<String, Object>> list = hrhsDao.getByhrhsFindAllDaoNames(cph);
		return Result.success(list);
	}
}
