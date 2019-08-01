package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KhxxDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 考核信息服務
 * 
 * @author HXX
 *
 */

@Service
public class KhxxService {

	@Autowired
	private KhxxDao kHBDao;

	// 考核信息
	@DS("datasource1")
	public Result<List<Map<String, Object>>> getkhxxFindAllService(String starttime, String storptime,
			Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = kHBDao.getByKHBFindAll(starttime, storptime, pageIndex, pageSize);

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
	public String getkhxxFindAllServiceExport(@Param("starttime") String starttime,
			@Param("storptime") String storptime) {
		List<Map<String, Object>> list = kHBDao.getByKHBFindEXE(starttime, storptime);
		return FastJsonUtil.toJSONString(list);
	}

}
