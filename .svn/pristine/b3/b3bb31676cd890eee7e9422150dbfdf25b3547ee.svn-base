/**
 * <p>Title: JswsjAction.java</p>
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
package com.erxi.ms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.erxi.ms.result.DownloadAct;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erxi.ms.result.Result;
import com.erxi.ms.service.JswsjService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 失物上交
 *
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@RestController
public class JswsjAction {

    private DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private JswsjService jswsjService;

	/*@RequestMapping("/getJswsjActi")
	public Result<List<Map<String, Object>>> getJswsjAction() {
		Result<List<Map<String, Object>>> result = jswsjService.getFindAllJswsService();
		return result;

	}*/


    @RequestMapping("/getswsjExport")
    public String getswsjFindAllExport(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm, HttpServletResponse response) throws IOException {
        String a[] = {"车牌号", "司机姓名", "联系方式", "车辆颜色", "上车时间", "下车时间", "物品描述", "备注"};
        String b[] = {"CPH", "SJXM", "LXFS", "CLYS", "SCSJ", "XCSJ", "WPMS", "BZ"};
        String gzb = "失物上交";

        String list = jswsjService.getSelectNamewsJswsDaoExport(cph, xm);
        List<Map<String, Object>> msg = downloadAct.strlist(list);
        downloadAct.download(request, response, a, b, gzb, msg);
        return null;
    }


    @RequestMapping("/getJswsjFindAll")
    public Result<List<Map<String, Object>>> getJswsjFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

        String car=cph.replaceAll("filter","");
        Result<List<Map<String, Object>>> result = jswsjService.getFindAllJswsService(car, xm, pageIndex, pageSize);
        return result;

    }


    @RequestMapping("getDeleteSwsj")
    public Integer getDeleteSwsjFind(@Param("bid") String bid) {
        Integer integer = jswsjService.getDeleteSwsjJswsService(bid);

        return integer;

    }

    /**
     * Title: getUpdateSwsjFind
     *
     */
    @RequestMapping("getUpdateSwsj")
    public Integer getUpdateSwsjFind(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
                                     @Param("lxfs") String lxfs, @Param("clys") String clys, @Param("scsj") String scsj,
                                     @Param("xcsj") String xcsj, @Param("wpms") String wpms, @Param("bz") String bz) {

        Integer integer = jswsjService.getUpdateSwsjFindService(bid, cph, sjxm, lxfs, clys, scsj, xcsj, wpms, bz);
        return integer;

    }

    /**
     * 搜索
     *
     * <p>
     * Title: getFindNameJswsj
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    @RequestMapping("/getJswsjName")
    public Result<List<Map<String, Object>>> getFindNameJswsj(@Param("cph") String cph) {
        String cph1 = "%" + cph + "%";

        Result<List<Map<String, Object>>> result = jswsjService.getSelectNamewsJswsDao(cph1);
        return result;

    }

    /**
     * Title: 添加
     *
     */
    @RequestMapping("getInsertSwsj")
    public Integer getInsertSwsjFind(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("lxfs") String lxfs,
                                     @Param("clys") String clys, @Param("scsj") String scsj, @Param("xcsj") String xcsj,
                                     @Param("wpms") String wpms, @Param("bz") String bz) {


        Integer integer = jswsjService.getInsertSwsjFindService(cph, sjxm, lxfs, clys, scsj, xcsj, wpms, bz);
        return integer;

    }

}
