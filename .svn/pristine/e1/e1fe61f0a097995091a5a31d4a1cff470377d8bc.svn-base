package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.KfwtsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 服务投诉
 * 
 * @author HXX
 *
 */

@RestController
public class KfwtsAction {

	private DownloadAct downloadact = new DownloadAct();

	@Autowired
	private KfwtsService fwtsService;

	@RequestMapping("/getAllExport")
	public String getFindAllExport(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
			HttpServletResponse response) throws IOException {

		String a[] = { "车牌号", "被诉司机", "乘车时间", "受理时间", "联系方式", "投诉人", "调查情况", "投诉事由", "投诉人意见", "处理结果" };
		String b[] = { "CPH", "BSSJ", "CCSJ", "SLSJ", "LXFS", "TSR", "DCQK", "TSSY", "TSRYJ", "CLJG" };
		String gzb="服务投诉";
		
		String list = fwtsService.getBybljlFindAllServiceExport(cph, xm);
		List<Map<String, Object>> msg = downloadact.str2list(list);
		downloadact.download(request, response, a, b, gzb, msg);
		return null;

	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/getfwts") public Result<List<Map<String, Object>>>
	 * getFindAll() { Result<List<Map<String, Object>>> result =
	 * fwtsService.getBybljl(); return result;
	 * 
	 * }
	 */

	@RequestMapping("/getfwtsFindAll")
	public Result<List<Map<String, Object>>> getFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

		String car=cph.replaceAll("filter","");


		Result<List<Map<String, Object>>> result = fwtsService.getBybljlFindAllService(car, xm, pageIndex, pageSize);
		return result;

	}

	/**
	 * 修改"
	 * 
	 * @return
	 */
	@RequestMapping("/getUpdatefwts")
	public Integer getUpdatefwts(@Param("bid") String bid, @Param("cph") String cph, @Param("bsjs") String bsjs,
			@Param("ccsj") String ccsj, @Param("slsj") String slsj, @Param("lxfs") String lxfs,
			@Param("tsr") String tsr, @Param("dcqk") String dcqk, @Param("tssy") String tssy,
			@Param("tsyj") String tsyj, @Param("cljg") String cljg) {

		Integer updateKfwtsDao = fwtsService.getUpdateKfwtsDao(bid, cph, bsjs, ccsj, slsj, lxfs, tsr, dcqk, tssy, tsyj,
				cljg);


		return updateKfwtsDao;

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/getDeletefwt")
	public Integer getDeletefwts(String bid) {

		Integer integer = fwtsService.getDeleteKfwtsKfwtsService(bid);

		return integer;

	}

	@RequestMapping("/getSelectNames")
	public Result<List<Map<String, Object>>> getSelectNamefwts(@Param("cph") String cph) {
		String cph1 = "%" + cph + "%";
		Result<List<Map<String, Object>>> result = fwtsService.getSelectNamefwtsKfwtsService(cph1);
		return result;

	}

	/**
	 * 
	 * 
	 * <p>
	 * Title: getInsertfwts
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 */
	@RequestMapping("/getInsertfwts")
	public Integer getInsertfwts(@Param("cph") String cph, @Param("bsjs") String bsjs, @Param("ccsj") String ccsj,
			@Param("slsj") String slsj, @Param("lxfs") String lxfs, @Param("tsr") String tsr,
			@Param("dcqk") String dcqk, @Param("tssy") String tssy, @Param("tsyj") String tsyj,
			@Param("cljg") String cljg) {
		Integer integer = fwtsService.getInsertKfwtsDao(cph, bsjs, ccsj, slsj, lxfs, tsr, dcqk, tssy, tsyj, cljg);

		return integer;

	}

}
