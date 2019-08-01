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
import org.springframework.web.multipart.MultipartFile;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.JxxfbService;

/**
 * 出租汽车服务系统:信息发布
 */
@RestController
@RequestMapping(value = "/jxxfb")
public class JxxfbAction {

	private DownloadAct downloadAct = new DownloadAct();

	@Autowired
	private JxxfbService jxxfbService;

	/**
	 * 信息发布增加
	 * 
	 * @param bt
	 * @param nr
	 * @param fbrq
	 * @param lb
	 * @return
	 */
	@RequestMapping("/getInsertxxfb")
	public Integer getInsertxxfb(@Param("bt") String bt,
			@Param("nr") String nr, @Param("fbrq") String fbrq,
			@Param("lb") String lb,@Param("bd") String bd, @Param("fbbm") String fbbm,
			@Param("lx") String lx,@Param("fj") String fj,
			@Param("wjm") String wjm) {
		Integer insert = jxxfbService.getInsertFindAllService(bt, nr, fbrq, lb, bd, fbbm, lx, fj, wjm);
		return insert;
	}

	/**
	 * 信息发布查询
	 * 
	 * @param request
	 * @param datetimeStart
	 * @param datetimeEnd
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/find")
	public Result<List<Map<String, Object>>> findvehicle(
			HttpServletRequest request,
			@RequestParam("lx") String lx,
			@RequestParam("title") String title,
			@RequestParam("datetimeStart") String datetimeStart,
			@RequestParam("datetimeEnd") String datetimeEnd,
			@RequestParam("type") String type,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize) {
		Result<List<Map<String, Object>>> msg = jxxfbService.jxxfb(
				lx, title,datetimeStart, datetimeEnd, type, pageIndex, pageSize);
		return msg;
	}

	/**
	 * 信息发布导出
	 * 
	 * @param request
	 * @param datetimeStart
	 * @param datetimeEnd
	 * @param type
	 * @param response
	 * @return
	 */
	@RequestMapping("/getExportFindhxx")
	public String getExport(HttpServletRequest request,
			@RequestParam("lx") String lx,
			@RequestParam("title") String title,
			@RequestParam("datetimeStart") String datetimeStart,
			@RequestParam("datetimeEnd") String datetimeEnd,
			@RequestParam("type") String type, HttpServletResponse response) {
		String a[] = { "标题", "内容", "发布日期", "类别" ,"是否必读","发布部门", "附件名"};
		String b[] = { "BT", "NR", "FBRQ", "LB", "SFBD", "FBBM", "WJM"};
		String gzb = "信息发布";
		String list = jxxfbService.jxxfbExport( lx, title, datetimeStart, datetimeEnd, type);
		List<Map<String, Object>> lists = downloadAct.str2list1(list);
		try {
			downloadAct.download(request, response, a, b, gzb, lists);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param id
	 * @param bt
	 * @param nr
	 * @param fbrq
	 * @param lb
	 * @return
	 */
	@RequestMapping("/getUpdatexxfb")
	public Integer getUpdatexxfbs(@Param("id") String id,
			@Param("bt") String bt, @Param("nr") String nr,
			@Param("fbrq") String fbrq, @Param("lb") String lb,@Param("bd") String bd, @Param("fbbm") String fbbm,
			@Param("lx") String lx,@Param("fj") String fj,
			@Param("wjm") String wjm) {
		Integer insert = jxxfbService.getUpdateFindAllService(id, bt, nr, fbrq,lb, bd, fbbm, lx, fj, wjm);
		return insert;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDeletexxfb")
	public Integer getdaletexxfbs(@Param("id") String id) {
		Integer insert = jxxfbService.getdaleteFindAllService(id);
		return insert;
	}
	
	/**
	 * 信息发布公司查看详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/xxfbgs")
	public String xxfbgs(
			HttpServletRequest request,
			@RequestParam("id") String id) {
		String msg = jxxfbService.xxfbgs(id);
		return msg;
	}
	//上传
	@RequestMapping(value = "/importfile")
    @ResponseBody
    public String importfile(@RequestParam("file") MultipartFile file) throws IOException {
        String msg = jxxfbService.importfile(file);
        return msg;
    }
	
	@RequestMapping(value = "/download")
	@ResponseBody
	public synchronized String download(HttpServletRequest request,HttpServletResponse response,@RequestParam("address") String address,@RequestParam("filename") String filename) throws IOException {
		String msg = "";
		jxxfbService.download(request, response, address, filename);
	    return msg;
	}

}
