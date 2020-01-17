package com.erxi.ms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.KbljService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 不良记录
 * 
 * @author 作者： Mar小坏
 * @date 2018年10月11日
 */

@RestController
public class KbljlAction {
	private DownloadAct downloadAct = new DownloadAct();

	@Autowired
	private KbljService bljService;

	/*
	 * @RequestMapping("/getblj")
	 * 
	 * @ResponseBody public Result<List<Map<String, Object>>> getbljFind() {
	 * Result<List<Map<String, Object>>> result = bljService.getBybljl(); return
	 * result;
	 * 
	 * }
	 */

	@RequestMapping("/getFindAllExport")
	public String getFindExport(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
			HttpServletResponse response) throws IOException {
		String a[] = { "车牌号", "姓名", "违反内容", "结果" };
		String b[] = { "CPH", "XM", "WFNR", "CLJG" };
		String gzb = "不良记录";


		String list = bljService.getBybljlFindALLServiceExport(cph, xm);

		List<Map<String, Object>> msg = downloadAct.strlist(list);
		downloadAct.download(request, response, a, b, gzb, msg);

		return null;

	}

	@RequestMapping("/getblj")
	public Result<List<Map<String, Object>>> getbljFind(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

		String car=cph.replaceAll("filter","");
		Result<List<Map<String, Object>>> result = bljService.getBybljlFindALLService(car, xm, pageIndex, pageSize);
		return result;

	}

	// 修改
	@RequestMapping("/getUpdate")
	public Integer getUpdateKbljl(@Param("BID") String BID, @Param("CPH") String CPH, @Param("XM") String XM,
			@Param("WFNR") String WFNR, @Param("CLJG") String CLJG) {
		int updateBybljl = bljService.getUpdateBybljl(BID, CPH, XM, WFNR, CLJG);

		return updateBybljl;

	}

	// 删除
	@RequestMapping("/getdelete")
	public int getdeleteKbljl(@Param("bid") String bid) {
		int i = bljService.deleteKblj(bid);

		return i;

	}

	// 根据车牌和名字查询

	@RequestMapping("/getcpAndxmName")
	public Result<List<Map<String, Object>>> getcpAndxmKbljl(@Param("cph") String cph) {
		String cph1 = "%" + cph + "%";
		Result<List<Map<String, Object>>> result = bljService.getBybljlmohu(cph1);
		return result;
	}

	// 添加
	@RequestMapping("/getinsert")
	public Integer getinsertKbljl(@Param("CPH") String CPH, @Param("XM") String XM, @Param("WFNR") String WFNR,
			@Param("CLJG") String CLJG) {
		int conut = bljService.Insertkbj(CPH, XM, WFNR, CLJG);

		return conut;

	}

}
