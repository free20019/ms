package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YcclcxDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 未营运车辆查询
 * @author 小坏
 * @description YcclcxServics
 * @date 2018/11/5 14:48
 */

@Service
public class YcclcxServics {
	
    @Autowired
    private YcclcxDao YcclcxDao;

    @DS("datasource1")
    public Result<List<Map<String, Object>>> getSelectFindAllJxcsgService(String start, String stop,String yhmc, Integer pageIndex,
                                                                          Integer pageSize) {

        List<Map<String, Object>> list = YcclcxDao.getFindAllYcclcxDao(start, stop, yhmc, pageIndex, pageSize);
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
	public Result<List<Map<String, Object>>> getSelectFindAllJxcsgServiceWsx(String start, String stop, String yhmc,
			Integer pageIndex, Integer pageSize) {

		  List<Map<String, Object>> listd = YcclcxDao.getFindAllYcclcxDaoWsx(start, stop, yhmc, pageIndex, pageSize);
	        int count = 0;

	        if (listd != null && listd.size() > 0) {
	            count = Integer.parseInt(String.valueOf(listd.get(0).get("COUNT")));
	        }
	        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
	        Map map = new HashMap();
	        map.put("count", count);
	        map.put("datas", listd);
	        lists.add(map);
	        return Result.success(lists);
	}

    /**
     * 无营运
     * @param start
     * @param stop
     * @param yhmc
     * @return
     */
    
    @DS("datasource1")
	public String getSelectFindAllJxcsgServiceExport(String start, String stop, String yhmc) {
		List<Map<String, Object>> list = YcclcxDao.getFindAlJysjlDaExportt(start, stop,yhmc);
		return FastJsonUtil.toJSONString(list);
	}
    
    
    /**
     * 未上线
     * @param start
     * @param stop
     * @param yhmc
     * @return
     */
    @DS("datasource1")
	public String getSelectFindAllJxcsgServiceExportWsx(String start, String stop, String yhmc) {
    	List<Map<String, Object>> list =YcclcxDao.getFindAllYcclcxDaoExportWsx(start, stop, yhmc);
		return FastJsonUtil.toJSONString(list);
	}



	@DS("datasource1")
	public Result<List<Map<String, Object>>> getSelectFindAllJxcsgServiceName(String name) {

		List<Map<String, Object>> list = YcclcxDao.getFindAllYcclcxDaoName(name);

		return Result.success(list);
	}
}
