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
import com.erxi.ms.service.KhrhsService;

/**
 * 好人好事
 *
 * @author 作者： Mar小坏
 * @date 2018年10月11日
 */

@RestController
public class KhrhsAction {

	@Autowired
	private KhrhsService hrhsService;

/*	@RequestMapping("/gethrhs")
	public Result<List<Map<String, Object>>> getHrhs() {
		return hrhsService.getByFindAllhrhs();
	}*/
	
	private DownloadAct downloadAct = new DownloadAct();
	
	
	@RequestMapping("/gethrhs")
	public Result<List<Map<String, Object>>> getHrhs(@RequestParam("cph") String cph,@RequestParam("xm") String xm,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize) {

		String car=cph.replaceAll("filter","");

		Result<List<Map<String, Object>>> result = hrhsService.getByFindAllhrhs(car,xm,pageIndex,pageSize);
		return result;
	}
	
	@RequestMapping("/gethrhsdc")
	public Result<List<Map<String, Object>>> gethrhsdc(@RequestParam("cph") String cph,@RequestParam("xm") String xm,HttpServletRequest request,HttpServletResponse response) throws IOException {

		String a[] = { "车牌号", "姓名", "时间", "内容","奖励类别","奖励金额","加分情况","奖励类型"};
		String b[] = { "CPH", "XM","SJ","NR","JLLB","JLJE","JFQK","JLLX"};
		String gzb = "好人好事";

		String msg = hrhsService.gethrhsdc(cph, xm);

		List<Map<String, Object>> list = downloadAct.strlist(msg);
		downloadAct.download(request, response, a, b, gzb, list);

		return null;
	}
	
	

	@RequestMapping("/gethrhsName")
	public Result<List<Map<String, Object>>> getjlleName() {
		return hrhsService.getByFindjllb();
	}

	/**
	 * "bid":item.BID,
	 * 
	 * @param bid
	 * @return
	 */

	@RequestMapping("/getUpdateKhrhs")
	public Integer getUpdateKhrhs(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("sj") String sj, @Param("nr") String nr, @Param("jfqk") String jfqk, @Param("jllb") String jllb,
			@Param("jlje") String jlje, @Param("jllx") String jllx) {

		//System.out.println("滚出来" + bid + cph + xm + sj + nr + jfqk + jllb + jlje + jllx);
		Integer updateKhrhsService = hrhsService.getUpdateKhrhsService(bid, cph, xm, sj, nr, jllb, jlje, jfqk, jllx);

		if (updateKhrhsService > 0) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}

		return updateKhrhsService;

	}

	/**
	 * 删除
	 */
	@RequestMapping("/getdeletehrhs")
	public Integer getdeletehrhs(String bid) {

		Integer integer = hrhsService.getDeleteKhrhsService(bid);
		if (integer > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}

		return integer;

	}

	// 根据车牌和名字查询SELECT * FROM KHRHS H WHERE H.CPH LIKE '%浙A%' AND H.XM LIKE
	// '%刘%'
	@RequestMapping("/getSelectNamehrhs")
	public Result<List<Map<String, Object>>> getcpAndxmhrhs(@Param("cph") String cph, @Param("xm") String sj) {

		String cph1 = "%" + cph + "%";
		String xm1 = "%" + sj + "%";

		Result<List<Map<String, Object>>> result = hrhsService.getSelectNameService(cph1, xm1);

		return result;

	}

	/**
	 * 
	 * 
	 * <p>
	 * Title: 添加
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 */
	@RequestMapping("/getInserthrhs")
	public Integer getInserthrhs(@Param("cph") String cph, @Param("xm") String xm, @Param("sj") String sj,
			@Param("nr") String nr, @Param("jllb") String jllb, @Param("jlje") String jlje, @Param("jfqk") String jfqk,
			@Param("jllx") String jllx) {

		Integer insert = hrhsService.getInserthrhsService(cph, xm, sj, nr, jllb, jlje, jfqk, jllx);
		return insert;

	}


	@RequestMapping("/getInserthrhsNames")
	public Result<List<Map<String, Object>>> getInserthrhsName(@Param("cph") String cph) {
		String cph1="%"+cph+"%";
		Result<List<Map<String, Object>>> result = hrhsService.getInserthrhsServiceName(cph1);
		return result;

	}


}
