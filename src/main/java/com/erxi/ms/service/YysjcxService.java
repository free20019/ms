package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YysjcxDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 营运数据查询服务
 * 
 * @author 小坏
 * 
 * @date 2018年10月31日 下午2:25:46
 */

@Service
public class YysjcxService {

	@Autowired
	private YysjcxDao yysjcxDao;

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getyysjcxFindAllService(String start, String stop, String cph,String gsm,
			Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = yysjcxDao.getFindAllYysjcxDao(start, stop, cph, gsm, pageIndex, pageSize);
		int count = 0;
		if (list != null && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("count", count);
		map.put("datas", list);
		arrayList.add(map); 
		return Result.success(arrayList);
	}


	@DS("datasource1")
	public String getyysjcxFindAllServices(String start, String stop, String cph,String gsm) {

		List<Map<String, Object>> list = yysjcxDao.getFindAllYysjcxDaoExport(start, stop, cph,gsm);
		return FastJsonUtil.toJSONString(list);
	}

	@DS("datasource1")
    public Result<List<Map<String, Object>>> getyysjcxFindAllServiceName(String cph) {
		List<Map<String, Object>> list = yysjcxDao.getFindAllYysjcxDaoName(cph);

		return Result.success(list);
	}
	@DS("datasource1")
    public Result<List<Map<String, Object>>> getFindAllgsm() {

		List<Map<String, Object>> list = yysjcxDao.getFindAllgsm();

		return Result.success(list);

	}
}
