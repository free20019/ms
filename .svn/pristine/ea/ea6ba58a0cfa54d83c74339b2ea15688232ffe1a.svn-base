
package com.erxi.ms.controller;


import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.service.JyxxServics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 在线支付、专车数据接入、出租汽车信息共享与报送系统
 * @author xianlehuang
 * @date 2018/1/9 
 */
@RequestMapping("/jyxx")
@RestController
public class JyxxAction {

    private DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private JyxxServics Jyxxservics;

	/**
	 * 下拉栏
	 * @param request
	 * @param table
	 * @param field
	 * @return
	 */
	@RequestMapping("/xll")
	@ResponseBody
	public String findxll(HttpServletRequest request,@RequestParam("table") String table,@RequestParam("field") String field) {
		String msg = Jyxxservics.findxll(table,field);
		return msg;
	}
	/**
	 * 下拉栏
	 * @param request
	 * @param table
	 * @param field
	 * @param type
	 * @return
	 */
	@RequestMapping("/xllcl")
	@ResponseBody
	public String findxllcl(HttpServletRequest request,@RequestParam("table") String table,@RequestParam("field") String field,@RequestParam("type") String type) {
		String msg = Jyxxservics.findxllcl(table,field,type);
		return msg;
	}
	/**
	 * 交易信息总览
	 * @return
	 */
	@RequestMapping("/findnowmonthall")
	@ResponseBody
	public String findnowmonthall(HttpServletRequest request) {
		String msg = Jyxxservics.findnowmonthall();
		return msg;
	}
	
	/**
	 * 交易
	 * @param request
	 * @param order
	 * @param stime
	 * @param etime
	 * @return
	 */
	@RequestMapping("/findwxjy")
	@ResponseBody
	public String findwxjy(HttpServletRequest request,
			@RequestParam("order") String order,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = Jyxxservics.findwxjy(order,stime,etime,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 交易导出
	 * @param request
	 * @param order
	 * @param stime
	 * @param etime
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/findwxjydc")
	@ResponseBody
	public String findwxjydc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("order") String order,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime) throws IOException {
		 String a[] = {"订单标题", "交易创建时间", "交易付款时间", "卖家支付账号", "买家支付账号", "商品单价", "购买数量", "交易金额"};
         String b[] = {"SUBJECT", "GMT_CREATE", "GMT_PAYMENT", "SELLER_EMAIL", "BUYER_EMAIL", "PRICE","QUANTITY","TOTAL_FEE"};
         String gzb = "交易";
         List<Map<String, Object>> list = Jyxxservics.findwxjydc(order,stime,etime);
         downloadAct.download(request, response, a, b, gzb, list);
         return null;
	}
	/**
	 * 专车数据总览
	 * @param request
	 * @return
	 */
	@RequestMapping("/findzcyytj")
	@ResponseBody
	public String findzcyytj(HttpServletRequest request) {
		String msg = Jyxxservics.findzcyytj();
		return msg;
	}
	/**
	 * 专车数据
	 * @param request
	 * @param vehicle
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findzcsj")
	@ResponseBody
	public String findzcsj(HttpServletRequest request,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("type") String type,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = Jyxxservics.findzcsj(vehicle,type,pageIndex,pageSize);
		return msg;
	}
	/**
	 * 专车数据导出
	 * @param request
	 * @param response
	 * @param vehicle
	 * @param type
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/findzcsjdc")
	@ResponseBody
	public String findzcsjdc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("vehicle") String vehicle,
			@RequestParam("type") String type) throws IOException {
		 String a[] = {"平台名称", "车牌号码", "车辆识别码VIN", "车主姓名", "车主身份证号", "交易", "投诉量"};
         String b[] = {"COMPNAME", "PLATENUMBER", "VIN", "NAME", "IDCARDNUMBER", "TRANSACTION","COMPLAINTS"};
         String gzb = "专车数据";
         List<Map<String, Object>> list = Jyxxservics.findzcsjdc(vehicle,type);
         downloadAct.download(request, response, a, b, gzb, list);
         return null;
	}
	/**
	 * 信息报送总览
	 * @return
	 */
	@RequestMapping(value = "/findxxbs")
	@ResponseBody
	public String findxxbs() {
		String msg = Jyxxservics.findxxbs();
		return msg;
	}
	/**
	 *  信息报送（分页）
	 * @param request
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/findxxbsfy")
	@ResponseBody
	public String findxxbsfy(HttpServletRequest request,
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		String msg = Jyxxservics.findxxbsfy(stime,etime,pageIndex,pageSize);
		return msg;
	}
	
	/**
	 * 信息报送导出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findxxbsdc")
	@ResponseBody
	public String findxxbsdc(
			@RequestParam("stime") String stime,
			@RequestParam("etime") String etime,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 String a[] = {"对象", "日期", "日均(数据量)", "数据类型", "车辆数", "方式"};
         String b[] = {"SUBJECT", "DB_TIME", "COUNT", "GMT_PAYMENT", "SELLER_EMAIL", "BUYER_EMAIL"};
         String gzb = "信息共享与报送";
         List<Map<String, Object>> list = Jyxxservics.findxxbsdc(stime,etime);
         downloadAct.download(request, response, a, b, gzb, list);
         return null;
	}
}

