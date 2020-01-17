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
import com.erxi.ms.service.KfuwzService;

/**
 * 服务违章
 * 
 * @author HXX
 *
 */

@RestController
public class KfuwzAction {
	@Autowired
	private KfuwzService fuwzService;

	
	private DownloadAct downloadAct = new DownloadAct();

	/*
	 * @RequestMapping("/getFind") public Result<List<Map<String, Object>>>
	 * getFindAll() { return fuwzService.getByFindAll();
	 * 
	 * }
	 * 
	 *//**
		 * 违章性质的下拉框
		 * 
		 *//*
		 * @RequestMapping("/getwzxzKfuwz") public Result<List<Map<String,
		 * Object>>> getwzxzKfuwz() { Result<List<Map<String, Object>>> result =
		 * fuwzService.getwzxzKfuwzService(); return result;
		 * 
		 * }
		 */


	/**
	 * 查询
	 * @param cph
	 * @param xm
	 * @param wzxz
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getfwwzFind")
	public Result<List<Map<String, Object>>> getFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("wzxz") String wzxz, @RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {

		String car =cph.replaceAll("filter","");
		System.out.println("我的车"+car);
		Result<List<Map<String, Object>>> result = fuwzService.getByFindAllService(car, xm, wzxz, pageIndex, pageSize);
		return result;

	}

	/**
	 * 修改
	 */

	@RequestMapping("/getUpdateKfuwz")
	public Integer getUpdateKfuwz(@Param("bid") String bid, @Param("sjxm") String sjxm, @Param("cphm") String cphm,
			@Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
			@Param("wzxz") String wzxz, @Param("kfqk") String kfqk, @Param("cjsj") String cjsj,
			@Param("fk") String fk) {


		Integer integer = fuwzService.getUpdateKfuwzService(bid, sjxm, cphm, wzsj, wzdd, wznr, wzxz, kfqk, cjsj, fk);



		return integer;

	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping("/getDeleteKfuw")
	public Integer getDeleteKfuwz(String bid) {

		Integer integer = fuwzService.getDeleteKfuwzService(bid);


		return integer;

	}

	/**
	 * 查询
	 * 
	 * @param cph
	 * @return
	 */
	@RequestMapping("/getSelectKfuwName")
	public Result<List<Map<String, Object>>> getSelectNameKfuwz(@Param("cph") String cph
			) {

		String cph1 = "%" + cph + "%";
		return fuwzService.getSeleteNameKfuwzService(cph1);

	}

	/**
	 * 添加
	 * 
	 * @param cph
	 * @param sj
	 * @return
	 */
	@RequestMapping("/getInsertKfuwz")
	public Integer getInsertKfuwz(@Param("sjxm") String sjxm, @Param("cphm") String cphm, @Param("wzsj") String wzsj,
			@Param("wzdd") String wzdd, @Param("wznr") String wznr, @Param("wzxz") String wzxz,
			@Param("kfqk") String kfqk, @Param("cjsj") String cjsj, @Param("fk") String fk) {

		Integer integer = fuwzService.getInsertKfuwzService(sjxm, cphm, wzsj, wzdd, wznr, wzxz, kfqk, cjsj, fk);

		return integer;

	}

	/**
	 * 导出
	 * @throws IOException 
	 */
	@RequestMapping("/getFwwzExportAll")
	public String getFwwzExport(HttpServletRequest request, @Param("cph") String cph, @Param("sjxm") String sjxm,
			@Param("wzxz") String wzxz, HttpServletResponse response) throws IOException {
		String a[] = { "司机姓名", "车牌号", "违章时间", "违章地点", "违章内容", "违章性质", "扣分情况", "创建时间", "罚款" };
		String b[] = { "SJNAME", "CPHAO", "WZSJ", "WZDD", "WZNR", "WZXZ", "KFQK", "CJSJ", "FK" };
		String gzb = "服务违章";
		
		String msg=fuwzService.getByFindAllServiceExport(cph, sjxm, wzxz);
		List<Map<String, Object>> list = downloadAct.strlist(msg);
		downloadAct.download(request, response, a, b, gzb, list);
		return null;

	}

}
