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
import com.erxi.ms.service.KryglService;

/**
 * 1.1.3.2.6荣誉管理
 * 
 * @author HXX
 *
 */

@RestController
public class KryglAction {
	
	private DownloadAct downloadAct=new DownloadAct();

	@Autowired
	private KryglService ryglService;

	/*
	 * @RequestMapping("getrygl") public Result<List<Map<String, Object>>>
	 * getKrygl(){ Result<List<Map<String, Object>>> result =
	 * ryglService.getByryg(); System.out.println(result); return result; }
	 */

	@RequestMapping("/getryglExport")
	public String getryglExportFindAll(HttpServletRequest request, @Param("cph") String cph, @Param("xm") String xm,
			HttpServletResponse response) throws IOException {
		
		String a[] ={"车牌号","司机姓名","判定日期","荣誉名称","年份","奖励金额","星级","备注"};
		String b[] ={"CPH","SJXM","PDRQ","RYMC","NF","JLJE","XJ","BZ"};
		String gzb="荣誉管理";
		String list=ryglService.getByrygFindAllServiceExport(cph, xm);
		List<Map<String, Object>> msg = downloadAct.strlist(list);
		downloadAct.download(request, response, a, b, gzb, msg);
		return null;

	}

	@RequestMapping("getryglFindAll")
	public Result<List<Map<String, Object>>> getKrygl(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
		String car=cph.replaceAll("filter","");

		Result<List<Map<String, Object>>> result = ryglService.getByrygFindAllService(car, xm, pageIndex, pageSize);
		return result;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@RequestMapping("/getUpdaterygl")
	public Integer getUpdateKrygl(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
			@Param("pdrq") String pdrq, @Param("rymc") String rymc, @Param("nf") String nf, @Param("jlje") String jlje,
			@Param("xj") String xj, @Param("bz") String bz) {

		// System.out.println(bid+cph+sjxm+pdrq+rymc+nf+jlje+xj+bz);

		Integer updateKryglService = ryglService.getUpdateKryglService(bid, cph, sjxm, pdrq, rymc, nf, jlje, xj, bz);

		if (updateKryglService > 0) {
			System.out.println("SUCCESS");
		} else {
			System.out.println("ERROR");
		}

		return updateKryglService;
	}

	@RequestMapping("/geDeletetryg")
	public Integer geDeletetrygl(String bid) {
		System.out.println("获取id" + bid);
		Integer integer = ryglService.getDeleteKryglService(bid);
		if (integer > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}
		return integer;

	}

	/**
	 * 查询
	 * 
	 * @param cph
	 * @param sj
	 * @return
	 */
	@RequestMapping("/geSelecerygNamet")
	public Result<List<Map<String, Object>>> geSeleceNametrygl(@Param("cph") String cph) {
		String cph1 = "%" + cph + "%";


		Result<List<Map<String, Object>>> result = ryglService.getSelectNameKryglService(cph1);
		return result;

	}

	/**
	 * 
	 * @param cph
	 * @param sjxm
	 * @param pdrq
	 * @param rymc
	 * @param nf
	 * @param jlje
	 * @param xj
	 * @param bz
	 * @return "cph":cph, "sjxm":sjxm, "pdrq":pdrq, "rymc":rymc, "nf":nf,
	 *         "jlje":jlje, "xj":xj, "bz":bz
	 */

	@RequestMapping("/getInserttryg")
	public Integer getInserttrygl(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("pdrq") String pdrq,
			@Param("rymc") String rymc, @Param("nf") String nf, @Param("jlje") String jlje, @Param("xj") String xj,
			@Param("bz") String bz) {

		Integer result = ryglService.getInsertKryglService(cph, sjxm, pdrq, rymc, nf, jlje, xj, bz);
		return result;

	}

}
