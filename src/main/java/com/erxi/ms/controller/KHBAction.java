package com.erxi.ms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erxi.ms.result.Result;
import com.erxi.ms.service.KHBService;
/**
 * 服务质量信息查询
 * @author HXX
 *
 */

@RestController
public class KHBAction {
	
	@Autowired 
	private KHBService kHBService;
	
	
	/**
	 * 考核信息
	 * @return
	 */
	/*@RequestMapping("/getFindAll")
	@ResponseBody
	public List<KHB> getKHB(){
		List<KHB> list = kHBService.getByKHBName();
		System.out.println(list);
		return list;
		
	}*/
	
	
	@RequestMapping("/getkhxxfind")
	public Result<List<Map<String, Object>>> findvehicle(HttpServletRequest request,
			 @RequestParam("starttime") String starttime,
			@RequestParam("storptime") String storptime, @RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {

		Result<List<Map<String, Object>>> msg = kHBService.getkhxxFindAllService(starttime,storptime,pageIndex,pageSize);
		return msg;
	}
	
	

}
