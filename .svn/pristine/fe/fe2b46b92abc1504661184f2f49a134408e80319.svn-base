
package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.SjzpDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 *
 * @author 作者： Mar小坏
 * @date 2018年10月15日
 */
@Service
public class SjzpService {


	@Autowired
	private SjzpDao sjzpDao;

	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllSjzpService() {
		List<Map<String, Object>> list = sjzpDao.getSelectFindAllSjzp();
		return Result.success(list);

	}
*/
	/**
	 * 
	 * <p>
	 * Title: getUpdateSjzpService
	 * </p>
	 * 
	 */
	@DS("datasource1")
	public Integer getUpdateSjzpService(@Param("bid") String bid, @Param("cph") String cph,
			@Param("clxynx") String clxynx, @Param("sjnl") String sjnl, @Param("xb") String xb, @Param("jl") String jl,
			@Param("bc") String bc, @Param("jbdd") String jbdd, @Param("lxr") String lxr, @Param("hj") String hj,
			@Param("cynx") String cynx, @Param("xj") String xj, @Param("sjxye") String sjxye,
			@Param("lxdh") String lxdh, @Param("yx") String yx, @Param("djrq") String djrq, @Param("bz") String bz) {

		Integer integer = sjzpDao.getUpdateAllSjzpDao(bid, cph, clxynx, sjnl, xb, jl, bc, jbdd, lxr, hj, cynx, xj, sjxye,
				lxdh, yx, djrq, bz);
		return integer;
	}

	/**
	 * 
	 * <p>
	 * Title: geDeleteSjzpService
	 * </p>
	 * 
	 */
	@DS("datasource1")
	public Integer geDeleteSjzpService(@Param("bid") String bid) {
		Integer integer = sjzpDao.getDeleteSjzp(bid);
		return integer;
	}

	/**
	 * 搜索
	 */
/*	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectNameSjzpService(@Param("lxr") String lxr) {
		List<Map<String, Object>> list = sjzpDao.getSelectNameSjzp(lxr);
		return Result.success(list);

	}*/

	/**
	 * 添加
	 */
	@DS("datasource1")
	public Integer getInsertInto(@Param("cph") String cph, @Param("clxynx") String clxynx, @Param("sjnl") String sjnl,
			@Param("xb") String xb, @Param("hj") String hj, @Param("cynx") String cynx, @Param("jl") String jl,
			@Param("bc") String bc, @Param("xj") String xj, @Param("jbdd") String jbdd, @Param("sjxye") String sjxye,
			@Param("lxdh") String lxdh, @Param("yx") String yx, @Param("lxr") String lxr, @Param("djrq") String djrq,
			@Param("bz") String bz) {

		Integer integer = sjzpDao.getInsertSjzp(cph, clxynx, sjnl, xb, hj, cynx, jl, bc, xj, jbdd, sjxye, lxdh, yx, lxr,
				djrq, bz);
		return integer;

	}


	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllSjzpServices(String xm,Integer pageIndex,Integer pageSize) {
		
		List<Map<String, Object>> list = sjzpDao.getSelectFindAllSjzps(xm,pageIndex,pageSize);
		int count =0;
		if(list!=null&& list.size()>0){
			count =Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("count", count);
		map.put("datas", list);
		lists.add(map);

		return Result.success(lists);
	}

	@DS("datasource1")
    public String getSelectFindAllSjzpServicesExport(String xm) {
		List<Map<String, Object>> list = sjzpDao.getSelectFindAllSjzpsExport(xm);
		return FastJsonUtil.toJSONString(list);
	}
}
