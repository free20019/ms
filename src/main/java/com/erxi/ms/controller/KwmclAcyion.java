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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.KwmclService;

/**
 * 文明车辆
 *
 * @author 作者： Mar小坏
 * @date 2018年10月15日
 */

@RestController
public class KwmclAcyion {
	private DownloadAct downloadAct=new DownloadAct();

	@Autowired
	private KwmclService wmclService;

	/*
	 * @RequestMapping("/getwmcl") public Result<List<Map<String, Object>>>
	 * getwmcl() {
	 * 
	 * Result<List<Map<String, Object>>> result = wmclService.getKwmclService();
	 * return result;
	 * 
	 * }
	 */

	@RequestMapping("/getwmclExport")
	public String getwmclExportFindAll(HttpServletRequest request, @Param("cph") String cph, @Param("rymc") String rymc,
			HttpServletResponse response) throws IOException {
		
		String a[]={"车牌号","判定日期","年份","荣誉名称","星级情况","备注"};
		String b[]={"CPH","PDRQ","NF","RYMC","XJQK","BZ"};
		String gzb="文明车辆";
		String list=wmclService.getFindAllsExport(cph, rymc);
		List<Map<String, Object>> msg = downloadAct.strlist(list);
		downloadAct.download(request, response, a, b, gzb, msg);

		return null;

	}

	@RequestMapping("/getwmclFindAll")
	public Result<List<Map<String, Object>>> getwmcl(@RequestParam("cph") String cph, @RequestParam("rymc") String rymc,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

			String car=cph.replaceAll("filter","");
		Result<List<Map<String, Object>>> result = wmclService.getKwmcFindAlllService(car, rymc, pageIndex, pageSize);
		return result;

	}

	/**
	 * 修改
	 *
	 */
	@RequestMapping("/getUpdatewmcl")
	@ResponseBody
	public Integer getUpdateKwmcl(@Param("bid") String bid, @Param("cph") String cph, @Param("pdrq") String pdrq,
			@Param("nf") String nf, @Param("rymc") String rymc, @Param("xjqk") String xjqk, @Param("bz") String bz) {

		Integer integer = wmclService.getUpdateKwmclService(bid, cph, pdrq, nf, rymc, xjqk, bz);
		if (integer > 0) {
			System.out.println("SUCCESS");
		} else {
			System.out.println("ERROR");
		}
		return integer;

	}

	/**
	 * 删除
	 * 
	 */

	@RequestMapping("/getDeletewmc")
	public Integer getDeletewmcl(String bid) {
		// System.out.println("我来了" + bid);
		Integer result = wmclService.getDeleteKwmclService(bid);
		if (result > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		return result;

	}

	/**
	 * 搜索
	 * 
	 * <p>
	 * Title: getSelectNamewmcl
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 */
	@RequestMapping("/getSelectName")
	public Result<List<Map<String, Object>>> getSelectNamewmcl(@Param("cph") String cph) {

		String cph1 = "%" + cph + "%";
		Result<List<Map<String, Object>>> result = wmclService.getSelectName(cph1);
		return result;

	}

	/**
	 * Insert
	 */
	@RequestMapping("/getInsertwmcl")
	@ResponseBody
	public Integer getInsertwmcl(@Param("cph") String cph, @Param("pdrq") String pdrq, @Param("nf") String nf,
			@Param("rymc") String rymc, @Param("xjqk") String xjqk, @Param("bz") String bz) {

		Integer result = wmclService.getInsertKwmclService(cph, pdrq, nf, rymc, xjqk, bz);

		return result;

	}

}
