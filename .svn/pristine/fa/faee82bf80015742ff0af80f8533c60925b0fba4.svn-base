/**
 * <p>Title: JswsjService.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: www.baidudu.com</p>
 *
 * @author shenlan
 * @date 2018年10月16日
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
import com.erxi.ms.dao.JswsjDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 事物上交接口
 *
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@Service
public class JswsjService {

    @Autowired
    private JswsjDao jswsjDao;

	/*@DS("datasource1")
	public Result<List<Map<String, Object>>> getFindAllJswsService() {
		List<Map<String, Object>> list = jswsjDao.getFindAllJswsjDao();
		return Result.success(list);

	}*/

    /**
     *
     * <p>
     * Title: getDeleteSwsjJswsService
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @DS("datasource1")
    public Integer getDeleteSwsjJswsService(@Param("bid") String bid) {
        Integer integer = jswsjDao.getDeleteSwsjJswsDao(bid);
        return integer;
    }

    /**
     *
     * <p>
     * Title: getUpdateSwsjFindService
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @DS("datasource1")
    public Integer getUpdateSwsjFindService(@Param("bid") String bid, @Param("cph") String cph,
                                            @Param("sjxm") String sjxm, @Param("lxfs") String lxfs, @Param("clys") String clys,
                                            @Param("scsj") String scsj, @Param("xcsj") String xcsj, @Param("wpms") String wpms,
                                            @Param("bz") String bz) {

        Integer integer = jswsjDao.getUpdateSwsJswsDao(bid, cph, sjxm, lxfs, clys, scsj, xcsj, wpms, bz);
        return integer;
    }

    /**
     * 搜索
     *
     * <p>
     * Title: getSelectNamewsJswsDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    @DS("datasource1")
    public Result<List<Map<String, Object>>> getSelectNamewsJswsDao(@Param("cph") String cph) {
        List<Map<String, Object>> list = jswsjDao.getSelectName(cph);
        return Result.success(list);

    }

    /**
     *
     * <p>
     * Title: getInsertSwsjFindService
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @DS("datasource1")
    public Integer getInsertSwsjFindService(@Param("cph") String cph, @Param("sjxm") String sjxm,
                                            @Param("lxfs") String lxfs, @Param("clys") String clys, @Param("scsj") String scsj,
                                            @Param("xcsj") String xcsj, @Param("wpms") String wpms, @Param("bz") String bz) {

        //System.out.println("税务Service"+cph+sjxm+lxfs+clys+scsj+xcsj+wpms+bz);
        Integer integer = jswsjDao.getInsertSwsJswsDao(cph, sjxm, lxfs, clys, scsj, xcsj, wpms, bz);
        return integer;
    }



    @DS("datasource1")
    public Result<List<Map<String, Object>>> getFindAllJswsService(String cph, String xm, Integer pageIndex,
                                                                   Integer pageSize) {


        List<Map<String, Object>> list = jswsjDao.getFindAllJswsjDao(cph, xm, pageIndex, pageSize);
        int count = 0;
        if (list != null && list.size() > 0) {
            count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
        }
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        HashMap map = new HashMap();
        map.put("count", count);
        map.put("datas", list);
        arrayList.add(map);
        return Result.success(arrayList);
    }

    @DS("datasource1")
    public String getSelectNamewsJswsDaoExport(String cph, String xm) {
        List<Map<String, Object>> list =jswsjDao.getFindAllJswsjDaoExport(cph,xm);
        return FastJsonUtil.toJSONString(list);
    }
}
