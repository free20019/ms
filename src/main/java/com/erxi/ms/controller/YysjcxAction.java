package com.erxi.ms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.YysjcxService;

/**
 * 营运数据查询
 * 
 * @author 小坏
 * 
 * @date 2018年10月31日 下午7:13:25
 */

@RestController
public class YysjcxAction {
	private DownloadAct downloadAct = new DownloadAct();

	@Autowired
	private YysjcxService yysjcxService;



	@RequestMapping("/getFindAllyusjcxName")
	public Result<List<Map<String, Object>>> getFindAllYysjcxName(@RequestParam("cph") String cph) {

		String cph1="%"+cph+"%";

		Result<List<Map<String, Object>>> result = yysjcxService.getyysjcxFindAllServiceName(cph1);
		return result;
	}


	@RequestMapping(value = "/getFindAllgsm")
	@ResponseBody
	public Result<List<Map<String, Object>>> getFindAllgsm() {
		Result<List<Map<String, Object>>> result = yysjcxService.getFindAllgsm();
		return result;

	}







	@RequestMapping("/getFindAllyusjcx")
	public Result<List<Map<String, Object>>> getFindAllYysjcx(@RequestParam("start") String start,
			@RequestParam("stop") String stop, @RequestParam("cph") String cph,@RequestParam("gsm") String gsm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
		String car = cph.replaceAll("guolv", "");
		String cars = car.replaceAll("浙", "");
		String starts = start.replaceAll("-", "");
		String stops = stop.replaceAll("-", "");

		Result<List<Map<String, Object>>> result = yysjcxService.getyysjcxFindAllService(starts, stops, cars,gsm, pageIndex,
				pageSize);
		return result;
	}



	@RequestMapping("/getExportFindAll")
	public String getExportFindAll(HttpServletRequest request, @RequestParam("start") String start,
			@RequestParam("stop") String stop, @RequestParam("cph") String cph,@RequestParam("gsm") String gsm, HttpServletResponse response)
			throws IOException {

		String car=cph.replaceAll("guolv","");
		String cars=car.replaceAll("浙","");
		String starts = start.replaceAll("-", "");
		String stops = stop.replaceAll("-", "");
		String a[] = { "车号", "服务资格证号", "上车时间", "下车时间", "营运时间(分钟)", "载客里程(公里)", "空车里程(公里)", "等候时间(秒)", "交易类型", "营运金额(元)" };
		String b[] = { "CPH", "YINGYUN", "SCSJ", "XCSJ", "YYSJ", "ZKLC", "KCLC", "DHSJ", "JIAOYITYPE",
				"YYJE" };
		String gzb = "营运数据查询";

		String msg = yysjcxService.getyysjcxFindAllServices(starts, stops, cars,gsm);
		List<Map<String, Object>> list = downloadAct.strlist(msg);
		downloadAct.download(request, response, a, b, gzb, list);
		return null;
	}

}
