/**
 * <p>Title: JxcsgAction.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: www.baidudu.com</p>
 *
 * @author shenlan
 * @date 2018年10月12日
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
import com.erxi.ms.service.JxcsgService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */
@RestController
public class JxcsgAction {

    private DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private JxcsgService jxcsgService;



    @RequestMapping("/getxcsgExport")
    public String getFindAllExport(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
                                   HttpServletResponse response) throws IOException {

        String a[] = {"车牌号", "姓名", "发生日期", "报案日期", "事故地点", "事故类别", "本车损失", "对方损失", "事故总额", "事故责任"};
        String b[] = {"CPH", "XM", "FSRQ", "BARQ", "SGDD", "SGLB", "BCCS", "DFSS", "SGZE", "SGZE"};
        String gzb = "行车事故";

        String list = jxcsgService.getSelectFindAllJxcsgServiceExport(cph, xm);
        List<Map<String, Object>> msg = downloadAct.strlist(list);
        downloadAct.download(request,response,a,b,gzb,msg);
        return null;
    }


    /**
     * 页面展示
     *
     * <p>
     * Title: getSelectFindAlljxcsg
     */
	/*@RequestMapping("/getSelectFindAll")
	public Result<List<Map<String, Object>>> getSelectFindAlljxcsg() {
		return jxcsgService.getSelectFindAllJxcsgService();

	}
	*/
    @RequestMapping("/getSelectFindAll")
    public Result<List<Map<String, Object>>> getSelectFindAlljxcsg(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

       String car=cph.replaceAll("filter","");

        Result<List<Map<String, Object>>> result = jxcsgService.getSelectFindAllJxcsgService(car, xm, pageIndex, pageSize);
        return result;

    }
	
	
	
	
	
	
	
	
	

/*	@RequestMapping("/getSelectFindAllSglb")
	public Result<List<Map<String, Object>>> getSelectFindAllSglb() {
		return jxcsgService.getSelectFindAllSglbService();

	}*/


    /**
     * 修改
     *
     * <p>
     * Title: getUpdatexcsg
     */
    @RequestMapping("/getUpdatexcsg")
    public Integer getUpdatexcsg(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
                                 @Param("fsrq") String fsrq, @Param("barq") String barq, @Param("sgdd") String sgdd,
                                 @Param("sglb") String sglb, @Param("bccs") String bccs, @Param("dfss") String dfss,
                                 @Param("sgze") String sgze, @Param("sgzr") String sgzr) {

        Integer integer = jxcsgService.getUpdateSglbService(bid, cph, xm, fsrq, barq, bccs, dfss, sgze, sglb, sgzr,
                sgdd);


        return integer;

    }

    /**
     * delete
     *
     * <p>
     * Title: getDeletexcsg
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    @RequestMapping("/getDeletexcsg")
    public Integer getDeletexcsg(@Param("bid") String bid) {
        return jxcsgService.geDeleteJxcsgService(bid);

    }

    /**
     * <p>
     * Description:
     * </p>
     */
    @RequestMapping("/getSelectxcsgName")
    public Result<List<Map<String, Object>>> getSelectNamexcsg(@Param("cph") String cph) {
        String cph1 = "%" + cph + "%";
        Result<List<Map<String, Object>>> result = jxcsgService.getSelectNameFindAllcsgService(cph1);

        return result;

    }

    /**
     * <p>
     * Description:
     * </p>
     */
    @RequestMapping("/getInsertxcsg")
    public Integer getInsertxcsg(@Param("cph") String cph, @Param("xm") String xm, @Param("fsrq") String fsrq,
                                 @Param("barq") String barq, @Param("sgdd") String sgdd, @Param("sglb") String sglb,
                                 @Param("bccs") String bccs, @Param("dfss") String dfss, @Param("sgze") String sgze,
                                 @Param("sgzr") String sgzr) {

        Integer integer = jxcsgService.getInsertxcglService(cph, xm, fsrq, barq, sgdd, sglb, bccs, dfss, sgze, sgzr);
        return integer;

    }

}
