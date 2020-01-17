/**  

* <p>Title: JtwfAction.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: www.baidudu.com</p>  

* @author shenlan  

* @date 2018年10月12日  

* @version 1.0  

*/
package com.erxi.ms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.JtwfService;

/**
 * 交通违法
 * 
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */
@RestController
public class JtwfAction {
	private DownloadAct downloadAct=new DownloadAct();

	/**
	 * <p>
	 * Title: getSelectJtwfAction
	 */

	@Autowired
	private JtwfService jtwfService;

	/*
	 * @RequestMapping("/getSelectJtwf") public Result<List<Map<String,
	 * Object>>> getSelectJtwfAction() { Result<List<Map<String, Object>>>
	 * result = jtwfService.getSelectJtwfService(); return result;
	 * 
	 * }
	 */

	@RequestMapping("/getFindExport")
	public String getFindExportAll(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
			HttpServletResponse response) throws IOException {
		String a[]={"车牌号","姓名","违章时间","违章地点","违章内容","处理结果","扣分","执法机关罚款","公司罚款"};
		String b[]={"CPH","XM","WZSJ","WZDD","WZNR","CLJG","KF","ZFJGFK","GSFK"};
		String gzb="交通违法";
		String msg=jtwfService.getSelectJtwfServiceExport(cph, xm);
		List<Map<String, Object>> list = downloadAct.strlist(msg);
		downloadAct.download(request, response, a, b, gzb, list);
		return null;

	}



	/**
	 * 修改
	 */

	@RequestMapping("/getFindAlljtwf")
	public Integer getUpdateJtwf(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
			@Param("cljg") String cljg, @Param("kf") String kf, @Param("zfjgfk") String zfjgfk,
			@Param("gsfk") String gsfk) {
		Integer integer = jtwfService.getUpdateJtwfService(bid, cph, xm, wzsj, wzdd, wznr, cljg, kf, zfjgfk, gsfk);
		return integer;

	}

	/**
	 * Serach Result
	 */
	@RequestMapping("/getSelectJtwfName")
	public Result<List<Map<String, Object>>> getSelectNameJtwf(@Param("cph") String cph) {
		String cph1 = "%" + cph + "%";
		Result<List<Map<String, Object>>> result = jtwfService.getSelectNameJtwfService(cph1);
		return result;

	}

	@RequestMapping("/getDeletejtwf")
	public Integer getDeletejtwf(String bid) {
		Integer integer = jtwfService.getDeleteJtwfService(bid);
		return integer;

	}

	@RequestMapping("/getInsertjtwf")
	public Integer getInsertjtwf(@Param("cph") String cph, @Param("xm") String xm, @Param("wzsj") String wzsj,
			@Param("wzdd") String wzdd, @Param("wznr") String wznr, @Param("cljg") String cljg, @Param("kf") String kf,
			@Param("zfjgfk") String zfjgfk, @Param("gsfk") String gsfk) {
		Integer integer = jtwfService.getInsertJtwfService(cph, xm, wzsj, wzdd, wznr, cljg, kf, zfjgfk, gsfk);
		return integer;

	}

	@RequestMapping("/getSelectJtwf")
	public Result<List<Map<String, Object>>> getSelectJtwfAction(@RequestParam("cph") String cph,
																 @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex,
																 @RequestParam("pageSize") Integer pageSize) {

		String car=cph.replaceAll("filter","");
		System.out.println("交通违法"+car);
		Result<List<Map<String, Object>>> result = jtwfService.getSelectJtwfService(car, xm, pageIndex, pageSize);
		return result;

	}

}
