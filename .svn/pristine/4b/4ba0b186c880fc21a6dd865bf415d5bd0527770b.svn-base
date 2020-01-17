package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.YclyusjtjDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 车辆营运数据统计
 */
@Service
public class YclyusjtjService {


    @Autowired
    private YclyusjtjDao yclyusjtjDao;


    @DS("datasource1")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAllService(@Param("cph") String cph, @Param("gs") String gs,
                                                                        @Param("kssj") String kssj, @Param("jssj") String jssj, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {
        List<Map<String, Object>> lists = yclyusjtjDao.getYclyusjtjFindAllDao(cph, gs, kssj, jssj, pageIndex, pageSize);


        int count =0;
        if (lists != null && lists.size() > 0 && lists.equals("null")) {
            count =Integer.parseInt(String.valueOf(lists.get(0).get("COUNT")));
        }

        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        Map map = new HashMap();
        map.put("count", count);
        map.put("list", lists);
        arrayList.add(map);

        return Result.success(arrayList);
    }

    @DS("datasource1")
    public String getfinaAllExportt(@Param("cph") String cph, @Param("gs") String gs,
                                    @Param("kssj") String kssj, @Param("jssj") String jssj) {
        List<Map<String, Object>> list = yclyusjtjDao.getYclyusjtjFindAllDaoExport(cph, gs, kssj, jssj);

        return FastJsonUtil.toJSONString(list);
    }




    @DS("datasource1")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAllServiceName(@Param("cph") String cph) {
        List<Map<String, Object>> list = yclyusjtjDao.getYclyusjtjFindAllDaoName(cph);


        return Result.success(list);
    }


    @DS("datasource1")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAllServiceGS(@Param("gs") String gs) {
        List<Map<String, Object>> list = yclyusjtjDao.getYclyusjtjFindAllDaoGS(gs);

        return Result.success(list);
    }
}
