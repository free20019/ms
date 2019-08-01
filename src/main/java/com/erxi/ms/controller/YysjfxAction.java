package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.JyysjfxService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 营运数据分析
 * 
 * @author 小坏
 * 
 * @date 2018年10月29日 下午4:27:56
 */

@Controller
@RequestMapping(value = "/yysjfx")
public class YysjfxAction {
	private DownloadAct downloadAct = new DownloadAct();

	@Autowired
	private JyysjfxService jyysjfxService;



	@RequestMapping(value = "/getFindAllYysjf")
	@ResponseBody
	public String getFindAllYysjfx(
			@RequestParam("start") String start,
			@RequestParam("stop") String stop,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize)
			throws ParseException {
		String sb = start.replaceAll("-", "");// 2018-01-01
		String eb = stop.replaceAll("-", "");// 2018-01-01

		String msg = jyysjfxService.getFindAllJyysjfxService(sb, eb, pageIndex,pageSize);
		return msg;

	}


	/**
	 * Export--
	 * 
	 * @param request
	 * @param start
	 * @param stop
	 * @param response
	 * @return
	 * @throws IOException
	 */

	@RequestMapping("ExportFind")
	@ResponseBody
	public String findExport(HttpServletRequest request, @Param("start") String start, @Param("stop") String stop,
			HttpServletResponse response) throws IOException {

		String starts = start.replaceAll("-", "");
		String stops = stop.replaceAll("-", "");

		String a[] = { "日期", "单车平均营运收益（元）", "单车平均载客里程（公里）", "单车平均空驶里程（公里）", "单车平均营运次数", "单车平均载客时间（分钟）", "单车平均载客等候时间（分钟）" };
		String b[] = { "TIME", "JIN", "ZL", "KS", "TJ", "SJ", "DH" };
		String gzb = "营运数据分析";
		List<Map<String, Object>> list = jyysjfxService.getFindAllJyysjfxServicExport(starts, stops);
		/*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		downloadAct.download(request, response, a, b, gzb, list);
		return null;

	}




	@RequestMapping(value = "/getFindAllYysjfName")
	@ResponseBody
	public Result<List<Map<String, Object>>> getFindAllYysjfxName(@RequestParam("cph") String cph) {

		String cph1 = "%"+cph+"%";

		Result<List<Map<String, Object>>> result = jyysjfxService.getFindAllJyysjfxServiceName(cph1);
		return result;

	}

}
