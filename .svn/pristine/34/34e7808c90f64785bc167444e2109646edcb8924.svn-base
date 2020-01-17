/**  

* <p>Title: JsjzpAction.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: www.baidudu.com</p>  

* @author shenlan  

* @date 2018年10月15日  

* @version 1.0  

*/
package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.SjzpService;
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
 *
 * @author 作者： Mar小坏
 * @date 2018年10月15日
 */
@RestController
public class JsjzpAction {

	private DownloadAct downloadAct=new DownloadAct();

	@Autowired
	private SjzpService sjzpService;

	/*@RequestMapping("/getFinaAllSjzp")
	public Result<List<Map<String, Object>>> getSelectFinaAllSjzp() {
		Result<List<Map<String, Object>>> result = sjzpService.getSelectFindAllSjzpService();
		return result;

	}
*/



	@RequestMapping("/getsjzpExport")
	public String getsjzpExportFindAll(HttpServletRequest request, @RequestParam("xm") String xm, HttpServletResponse response) throws IOException {


		String a[]={"车牌号","车辆营运年限","司机年龄","性别","驾龄","班次","交班地点","联系人","登记日期"};
		String b[]={"CPH","CLXYNX","SJNL","XB","JL","BC","JBDD","LXR","DJRQ"};
		String gzb="司机招聘";

		String list = sjzpService.getSelectFindAllSjzpServicesExport(xm);
        List<Map<String, Object>> msg = downloadAct.strlist(list);
        downloadAct.download(request,response,a,b,gzb,msg);
        return xm;
	}
	
	
	
	
	
	
	@RequestMapping("/getFinaAllSjzp")
	public Result<List<Map<String, Object>>> getSelectFinaAllSjzp(@RequestParam("xm") String xm,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize) {
		Result<List<Map<String, Object>>> result = sjzpService.getSelectFindAllSjzpServices(xm,pageIndex,pageSize);
		return result;

	}

	

	
	
	
	/**
	 * 
	 * Update
	 * 
	 */

	@RequestMapping("/getUpSjzp")
	public Integer getUpdateAllSjzp(@Param("bid") String bid, @Param("cph") String cph, @Param("clxynx") String clxynx,
			@Param("sjnl") String sjnl, @Param("xb") String xb, @Param("jl") String jl, @Param("bc") String bc,
			@Param("jbdd") String jbdd, @Param("lxr") String lxr, @Param("hj") String hj, @Param("cynx") String cynx,
			@Param("xj") String xj, @Param("sjxye") String sjxye, @Param("lxdh") String lxdh, @Param("yx") String yx,
			@Param("djrq") String djrq, @Param("bz") String bz) {


		Integer integer = sjzpService.getUpdateSjzpService(bid, cph, clxynx, sjnl, xb, jl, bc, jbdd, lxr, hj, cynx, xj,
				sjxye, lxdh, yx, djrq, bz);

		return integer;

	}

	/**
	 * 删除
	 * 
	 * <p>
	 * Title: getDeleteSjzp
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 */

	@RequestMapping("getDeleteSjzp")
	public Integer getDeleteSjzp(@Param("bid") String bid) {
		Integer intege = sjzpService.geDeleteSjzpService(bid);
		return intege;
	}

	/**
	 * 搜索
	 */
/*	@RequestMapping("/getFinaNmaeSjzp")
	public Result<List<Map<String, Object>>> getSelecNameAllSjzp(@Param("lxr") String lxr) {
		System.out.println("+++++++++++++++" + lxr);
		String lxr1 = "%" + lxr + "%";
		Result<List<Map<String, Object>>> result = sjzpService.getSelectNameSjzpService(lxr1);
		return result;

	}*/

	/**
	 * 添加
	 */

	@RequestMapping("/getInsertSjzp")
	public Integer getInsertIntoAllSjzp(@Param("cph") String cph, @Param("clxynx") String clxynx,
			@Param("sjnl") String sjnl, @Param("xb") String xb, @Param("hj") String hj, @Param("cynx") String cynx,
			@Param("jl") String jl, @Param("bc") String bc, @Param("xj") String xj, @Param("jbdd") String jbdd,
			@Param("sjxye") String sjxye, @Param("lxdh") String lxdh, @Param("yx") String yx, @Param("lxr") String lxr,
			@Param("djrq") String djrq, @Param("bz") String bz) {


		Integer insertInto = sjzpService.getInsertInto(cph, clxynx, sjnl, xb, hj, cynx, jl, bc, xj, jbdd, sjxye, lxdh,
				yx, lxr, djrq, bz);

		return insertInto;

	}

}
