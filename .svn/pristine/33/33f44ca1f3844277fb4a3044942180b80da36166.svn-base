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
import com.erxi.ms.service.SzzswService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 失主找失物
 *
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@RestController
public class JszzswAction {

    private DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private SzzswService szzswService;

	/*@RequestMapping("/getSelectFindAllJszzsw")
	public Result<List<Map<String, Object>>> getgetSelectFindAllJszzsw() {
		Result<List<Map<String, Object>>> result = szzswService.getFindAllSzzswService();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}*/








@RequestMapping("/SelectFindAllJszzswName")
    public Result<List<Map<String, Object>>> getSelectFindAllJszzswName(@Param("cph") String cph) {

       String car="%"+cph+"%";
        Result<List<Map<String, Object>>> result = szzswService.FindAllSzzswServiceName(car);

        return result;

    }





    @RequestMapping("/getszzwExport")
    public String getszzwExportFindAll(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
                                       HttpServletResponse response) throws IOException {

        String a[] = {"车牌号", "司机姓名", "失主姓名", "遗失物品", "乘车时间", "起始地点", "位置", "寻找结果"};
        String b[] = {"CPH", "SJXM", "SZXM", "YSWP", "CCSJ", "CLJG", "QSDD", "WZ", "XZJG"};
        String gzb = "失主找失物";

        String list=szzswService.getFindAllSzzswServicesExport(cph,xm);
        List<Map<String, Object>> msg = downloadAct.strlist(list);
        downloadAct.download(request, response, a, b, gzb, msg);

        return null;
    }


    @RequestMapping("/getSelectFindAllHXX")
    public Result<List<Map<String, Object>>> getgetSelectFindAllJszzsw(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

       String car=cph.replaceAll("filter","");

        Result<List<Map<String, Object>>> result = szzswService.getFindAllSzzswServices(car, xm, pageIndex, pageSize);
        return result;

    }


    @RequestMapping("/getDeleteSzzsw")
    public Integer getDeletJszzsw(@Param("bid") String bid) {
        Integer integer = szzswService.getDeleteSzzswService(bid);
        return integer;

    }

    /**
     * 修改
     */

    @RequestMapping("/getUpdateSzzsw")
    public Integer getUpdateJszzsw(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
                                   @Param("szxm") String szxm, @Param("yswp") String yswp, @Param("ccsj") String ccsj,
                                   @Param("qssj") String qssj, @Param("wz") String wz, @Param("xzjg") String xzjg) {

        Integer integer = szzswService.getUpdateSzzswService(bid, cph, sjxm, szxm, yswp, ccsj, qssj, wz, xzjg);

        return integer;

    }

    /**
     * 搜索
     */

	/*@RequestMapping("getSelectNameJszzs")
	public Result<List<Map<String, Object>>> getSelectNameJszzsw(@Param("cph") String cph, @Param("sjxm") String sjxm) {
		String cph1 = "%" + cph + "%";
		String sjxm1 = "%" + sjxm + "%";

		Result<List<Map<String, Object>>> result = szzswService.getSelectNameService(cph1, sjxm1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}*/

    /**
     * 添加
     */

    @RequestMapping("getInsertJszzs")
    public Integer getInsertJszzsw(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("szxm") String szxm,
                                   @Param("yswp") String yswp, @Param("ccsj") String ccsj, @Param("qssj") String qssj, @Param("wz") String wz,
                                   @Param("xzjg") String xzjg) {
        return szzswService.getInSertService(cph, sjxm, szxm, yswp, ccsj, qssj, wz, xzjg);

    }

}
