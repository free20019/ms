package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KfuwzDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;



/**
 * 服务违章服務層
 * 
 * @author HXX
 *
 */
@Service
public class KfuwzService {
	

	@Autowired
	private KfuwzDao fuwzDao;

	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getByFindAll() {
		List<Map<String, Object>> list = fuwzDao.getByFindAll();
		return Result.success(list);
	}
*/
	/**
	 * 违章性质下拉框
	 */
	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getwzxzKfuwzService() {
		List<Map<String, Object>> list = fuwzDao.getwzxzSelectKfuwzDao();
		return Result.success(list);

	}*/

	/**
	 * update
	 */
	@DS("datasource1")
	public Integer getUpdateKfuwzService(@Param("bid") String bid, @Param("sjxm") String sjxm,
			@Param("cphm") String cphm, @Param("wzsj") String wzsj, @Param("wzdd") String wzdd,
			@Param("wznr") String wznr, @Param("wzxz") String wzxz, @Param("kfqk") String kfqk,
			@Param("cjsj") String cjsj, @Param("fk") String fk) {
		Integer updateKfuwzDao = fuwzDao.getUpdateKfuwzDao(bid, sjxm, cphm, wzsj, wzdd, wznr, wzxz, kfqk, cjsj, fk);
		return updateKfuwzDao;

	}

	/**
	 * delete
	 * 
	 * @param bid
	 * @return
	 */
	@DS("datasource1")
	public Integer getDeleteKfuwzService(String bid) {
		Integer integer = fuwzDao.getDeleteKfuwzDao(bid);
		return integer;
	}

	/**
	 * select
	 * 
	 * @param cph
	 * @param sj
	 * @return
	 */
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSeleteNameKfuwzService(@Param("cph") String cph) {
		List<Map<String, Object>> list = fuwzDao.getSelectNameKfuwzDao(cph);
		return Result.success(list);
	}

	/**
	 * 添加
	 * 
	 * <p>
	 * Title: getInsertKfuwzService
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 */
	@DS("datasource1")
	public Integer getInsertKfuwzService(@Param("sjxm") String sjxm, @Param("cphm") String cphm,
			@Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
			@Param("wzxz") String wzxz, @Param("kfqk") String kfqk, @Param("cjsj") String cjsj,
			@Param("fk") String fk) {
		Integer integer = fuwzDao.getInsertKfuwzDao(sjxm, cphm, wzsj, wzdd, wznr, wzxz, kfqk, cjsj, fk);

		return integer;

	}
	
	
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getByFindAllService(String cph, String xm, String wzxz, Integer pageIndex,
			Integer pageSize) {
		List<Map<String, Object>> list = fuwzDao.getByFindAllDao(cph,xm,wzxz,pageIndex,pageSize);
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
	public String getByFindAllServiceExport(@Param("cph") String cph, @Param("sjxm") String sjxm,
			@Param("wzxz") String wzxz) {
		List<Map<String, Object>> list = fuwzDao.getByFindAllDaoExport(cph,sjxm,wzxz);
		return FastJsonUtil.toJSONString(list);
	}

}
