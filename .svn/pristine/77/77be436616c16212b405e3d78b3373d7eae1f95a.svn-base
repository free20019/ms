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
import com.erxi.ms.service.KhxxService;

@RestController
public class KhxxAction {

	private DownloadAct downloadAct = new DownloadAct();

	@Autowired
	private KhxxService kHBService;

	/**
	 * 考核信息
	 * 
	 * @return
	 */

	@RequestMapping("/getkhxxfinds")
	public Result<List<Map<String, Object>>> findvehicle(HttpServletRequest request,
			@RequestParam("starttime") String starttime, @RequestParam("storptime") String storptime,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

		Result<List<Map<String, Object>>> msg = kHBService.getkhxxFindAllService(starttime, storptime, pageIndex,
				pageSize);

		return msg;
	}

	@RequestMapping("findswczexcles")
	public String findswczexcle(HttpServletRequest request, @Param("starttime") String starttime,
			@Param("storptime") String storptime, HttpServletResponse response) throws IOException {

		String a[] = { "司机姓名", "事件名称", "违章时间", "扣分", "车牌号", "执法单位", "服务证号", };// 导出列明
		String b[] = { "NAME", "EVENTNAME", "BREAKTIME", "DEDUCTFEN", "CARHAO", "ZHIFA", "FWZH" };// 导出map中的key
		String gzb = "考核信息";// 导出sheet名和导出的文件名
		String msg = kHBService.getkhxxFindAllServiceExport(starttime, storptime);

		List<Map<String, Object>> list = downloadAct.strlist(msg);// 导出的数据
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

}
