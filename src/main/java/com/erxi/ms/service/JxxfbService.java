package com.erxi.ms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.JxxfaDao;
import com.erxi.ms.result.CodeMsg;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

@Service
public class JxxfbService {

	@Autowired
	private JxxfaDao jxxfaDao;

	@DS("datasource1")
	public Result<List<Map<String, Object>>> jxxfb(String lx, String title, String datetimeStart,
			String datetimeEnd, String type, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = jxxfaDao.getJxxfb(lx, title, datetimeStart,
				datetimeEnd, type, pageIndex, pageSize);
		int count = 0;
		if (list != null && list.size() > 0) {
			count = Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
//			for(int i=0;i<list.size();i++){
//				list.get(i).put("SFBD", String.valueOf(list.get(i).get("SFBD")).endsWith("1")?"必读":(String.valueOf(list.get(i).get("SFBD")).endsWith("2")?"选读":""));
//			}
		}
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map map = new HashMap();
		map.put("count", count);
		map.put("datas", list);
		lists.add(map);
		return Result.success(lists);
	}

	@DS("datasource1")
	public String jxxfbExport(String lx, String title, String datetimeStart, String datetimeEnd,
			String type) {
		List<Map<String, Object>> listWsx = jxxfaDao.getJxxfbExport(lx, title,
				datetimeStart, datetimeEnd, type);
		if (listWsx != null && listWsx.size() > 0) {
			for(int i=0;i<listWsx.size();i++){
				listWsx.get(i).put("SFBD", String.valueOf(listWsx.get(i).get("SFBD")).endsWith("1")?"必读":(String.valueOf(listWsx.get(i).get("SFBD")).endsWith("2")?"选读":""));
			}
		}
		Map map = new HashMap();
		map.put("data", listWsx);
		return FastJsonUtil.toJSONString(map);

	}

	@DS("datasource1")
	public Integer getInsertFindAllService(@Param("bt") String bt,
			@Param("nr") String nr, @Param("fbrq") String fbrq,
			@Param("lb") String lb,@Param("bd") String bd, @Param("fbbm") String fbbm,
			@Param("lx") String lx, @Param("fj") String fj,
			@Param("wjm") String wjm) {
		Integer integer = jxxfaDao.getIntegerFindAllDao(bt, nr, fbrq, lb, bd, fbbm, lx, fj, wjm);
		return integer;
	}

	@DS("datasource1")
	public Integer getUpdateFindAllService(@Param("id") String id,
			@Param("bt") String bt, @Param("nr") String nr,
			@Param("fbrq") String fbrq, @Param("lb") String lb, @Param("bd") String bd, @Param("fbbm") String fbbm,
			@Param("lx") String lx,@Param("fj") String fj,
			@Param("wjm") String wjm) {
		Integer integer = jxxfaDao.getUpdateFindAllDao(id, bt, nr, fbrq, lb, bd, fbbm, lx, fj, wjm);
		return integer;
	}

	@DS("datasource1")
	public Integer getdaleteFindAllService(@Param("id") String id) {
		Integer integer = jxxfaDao.getdaleteFindAllDao(id);
		return integer;
	}
	
	@DS("datasource1")
	public String xxfbgs(String id) {
		List<Map<String, Object>> list = jxxfaDao.xxfbgs(id);
		Map map = new HashMap();
		map.put("data", list);
		return FastJsonUtil.toJSONString(map);

	}
	/**
     *  上传附件
     * @param
     * @return
     */
	public String importfile(MultipartFile file) {
        Map<String,Object> map = new HashMap<String,Object>();
        if (file.isEmpty()) {
        	map.put("msg","1");
 	        return FastJsonUtil.toJSONString(map);
		}
//		String fileName = file.getName();
		// 原文件名即上传的文件名
		String origFileName = file.getOriginalFilename();
        String prefix=origFileName.substring(origFileName.lastIndexOf("."),origFileName.length()); //获取到文件类型后缀 比如  .jpg
		Random random = new Random();
		String ids = random.nextInt(9999)+System.currentTimeMillis()+"";
        String filenames = ids+prefix;  //组合自己的文件名字 
		String path ="d://erxi//" + filenames;
		File ff = new File(path);
		 // 检测是否存在目录
	    if (!ff.getParentFile().exists()) {
	    	ff.getParentFile().mkdirs();// 新建文件夹
	    }
	    if(ff.exists()){
	    	ff.delete();
    	}
		try {
			file.transferTo(ff);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("file_name",origFileName);
		map.put("msg","0");
		map.put("file_address",path);
        return FastJsonUtil.toJSONString(map);
    }  
	public void download(HttpServletRequest request, HttpServletResponse response,String address,String filename) throws IOException{
		System.out.println(address);
		 //3.设置content-disposition响应头控制浏览器以下载的形式打开文件

		response.setContentType("application/octet-stream;charset=UTF-8");// 设置文件输出类型
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8"));
        //4.获取要下载的文件输入流
        InputStream in=null;
        OutputStream out=null;
        try {//获取要下载的文件的绝对路径
            in=new FileInputStream(address);
            int len = 0;
             //5.创建数据缓冲区
             byte[] buffer = new byte[1024];
           //6.通过response对象获取OutputStream流
             out = response.getOutputStream();
             //7.将FileInputStream流写入到buffer缓冲区
             while ((len = in.read(buffer)) > 0) {//in.read(byte[] b)最多读入b.length个字节 在碰到流的结尾时 返回-1
             //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                 out.write(buffer,0,len);
             }
         }catch (FileNotFoundException e) {
            e.printStackTrace();
         } finally{	            
				in.close();
				out.close();
         }
	}
}
