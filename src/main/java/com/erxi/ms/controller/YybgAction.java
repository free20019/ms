package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.YyybgService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName getFindAllYybgAction 营运报告
 * @dOscription TODO
 * @Author HXX
 * @Date 2018/11/13 17:17
 * @Version 1.0
 **/

@RestController
public class YybgAction {

    @Autowired
    
    private YyybgService yybgService;

    private DownloadAct downloadAct = new DownloadAct();

    @RequestMapping("/getFindAllYybgs")
    public String getFindAllYybg(@Param("n") String n, @Param("y") String y,  @Param("d") String d,
    		 @Param("stime") String stime, @Param("etime") String etime,@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        String msg = yybgService.getYyybgFindAllService(n,y,d,stime,etime, pageIndex, pageSize);
        return msg;
    }

    /**
     * Export
     * @param request
     * @param n
     * @param y
     * @param d
     * @param stime
     * @param etime
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/getFindAllYybgsExports")
    public String getFindAllYybgExport(HttpServletRequest request, @Param("n") String n, @Param("y") String y,  @Param("d") String d,
    		 @Param("stime") String stime, @Param("etime") String etime,HttpServletResponse response)
            throws IOException {

        String a[] = {"日期", "总车辆数", "总营运次数", "总营收金额", "出车总时长", "营运总里程", "实载总里程", "空驶总里程"};
        String b[] = {"TIME", "CPH", "ZYYCS", "ZYYJE", "CCSC", "ZLIC","SZZLC","KSZLC"};
        String gzb = "营运报告";
        List<Map<String, Object>> list = yybgService.getYyybgServiceExport(n,y,d,stime,etime);
		downloadAct.download(request,response,a,b,gzb,list);
		return null;
    }
}
