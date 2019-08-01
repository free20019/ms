/**  

* <p>Title: JxcsgService.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: www.baidudu.com</p>  

* @author shenlan  

* @date 2018年10月12日  

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
import com.erxi.ms.dao.JxcsgDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 *
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */
@Service
public class JxcsgService {

	@Autowired
	private JxcsgDao jxcsgDao;


	/**
	 * 页面展示
	 * 
	 * <p>
	 * Title: getSelectFindAllJxcsgService
	
	 */
	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllJxcsgService() {
		List<Map<String, Object>> list = jxcsgDao.getSelectFindAllJxcsgDao();
		return Result.success(list);

	}*/

	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllSglbService() {
		List<Map<String, Object>> list = jxcsgDao.getSelectFindAllJSglbDao();
		return Result.success(list);

	}*/

	/**
	 * 
	 * <p>
	 * Title: getUpdateSglbService
	 * </p>
	 * 
	 */
	@DS("datasource1")
	public Integer getUpdateSglbService(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("fsrq") String fsrq, @Param("barq") String barq, @Param("sgdd") String sgdd,
			@Param("sglb") String sglb, @Param("bccs") String bccs, @Param("dfss") String dfss,
			@Param("sgze") String sgze, @Param("sgzr") String sgzr) {
		Integer integer = jxcsgDao.getUpdateJxcsgDao(bid, cph, xm, fsrq, barq, bccs, dfss, sgze, sglb, sgzr, sgdd);
		return integer;
	}

	/**
	 * 
	 * <p>
	 * Title: geDeleteJxcsgService
	 * </p>
	 * 
	 */
	@DS("datasource1")
	public Integer geDeleteJxcsgService(@Param("bid") String bid) {
		return jxcsgDao.getDeleteJSglbDao(bid);
	}

	/**
	 * 搜索
	 * 
	 * <p>
	 * Title: getSelectNameFindAllcsgService
	
	 */
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNameFindAllcsgService(@Param("cph") String cph) {

		List<Map<String, Object>> list = jxcsgDao.getSelectNameFindAllJxcsgDao(cph);

		return Result.success(list);

	}

	@DS("datasource1")
	public Integer getInsertxcglService(@Param("cph") String cph, @Param("xm") String xm, @Param("fsrq") String fsrq,
			@Param("barq") String barq, @Param("sgdd") String sgdd, @Param("sglb") String sglb,
			@Param("bccs") String bccs, @Param("dfss") String dfss, @Param("sgze") String sgze,
			@Param("sgzr") String sgzr) {
		Integer integer = jxcsgDao.getInsertJxcsgDao(cph, xm, fsrq, barq, sgdd, sglb, bccs, dfss, sgze, sgzr);
		return integer;
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllJxcsgService(String cph, String xm, Integer pageIndex,
			Integer pageSize) {
		
		List<Map<String, Object>> list = jxcsgDao.getSelectFindAllJSglbDao(cph,xm,pageIndex,pageSize);
		
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
    public String getSelectFindAllJxcsgServiceExport(String cph, String xm) {
		List<Map<String ,Object>> list= jxcsgDao.getSelectFindAllJSglbDaoExport(cph,xm);
		return FastJsonUtil.toJSONString(list);
    }
}
