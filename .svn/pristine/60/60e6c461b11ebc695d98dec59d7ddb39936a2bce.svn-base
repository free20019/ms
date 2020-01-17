
package com.erxi.ms.controller;


import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.YcclcxServics;

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
 * 异常车辆查询
 *
 * @author 小坏
 * @description YcclcxActio
 * @date 2018/11/1 17:24
 */

@RestController
public class YcclcxAction {

    private DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private YcclcxServics ycclcxservics;


    @RequestMapping("/getFindycclcxsName")
    public Result<List<Map<String, Object>>> getFindAllYcclcxName(@Param("name") String name) {

        String cph1 = "%" + name + "%";
        System.out.println("获取性别"+cph1);

        Result<List<Map<String, Object>>> result = ycclcxservics.getSelectFindAllJxcsgServiceName(cph1);

        return result;
    }


    /**
     * FindAll
     *
     * @return
     */
    @RequestMapping("/getFindycclcxs")
    public Result<List<Map<String, Object>>> getFindAllYcclcx(@Param("id") String id, @Param("start") String start,
                                                              @Param("stop") String stop, @Param("yhmc") String yhmc, @Param("pageIndex") Integer pageIndex,
                                                         @Param("pageSize") Integer pageSize) {



        String gs=yhmc.replaceAll("filter","");
        String one = "1";
        if (id.equals(one)) {
            Result<List<Map<String, Object>>> result = ycclcxservics.getSelectFindAllJxcsgService(start, stop, gs,
                    pageIndex, pageSize);
            return result;
        } else {

            Result<List<Map<String, Object>>> results = ycclcxservics.getSelectFindAllJxcsgServiceWsx(start, stop, gs,
                    pageIndex, pageSize);
            return results;
        }

    }

    /**
     * Export
     *
     * @param request
     * @param start
     * @param stop
     * @param yhmc
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping("/getExportFinds")
    public String Export(HttpServletRequest request, @Param("id") String id, @Param("start") String start,
                         @Param("stop") String stop, @Param("yhmc") String yhmc, HttpServletResponse response) throws IOException {

        System.out.println("接收数据" + id + start + stop + yhmc);

        String one = "1";
        if (id.equals(one)) {
            System.out.println("导出无营运数据车辆========================" + id);
            String a[] = {"公司", "车号", "SIM卡号", "联系人", "联系电话"};
            String b[] = {"COMP_NAME", "VEHI_NO", "VEHI_SIM", "OWN_NAME", "HOME_TEL"};
            String gzb = "无营运数据车辆";
            String msg = ycclcxservics.getSelectFindAllJxcsgServiceExport(start, stop, yhmc);
            List<Map<String, Object>> list = downloadAct.strlist(msg);
            downloadAct.download(request, response, a, b, gzb, list);
            return null;
        } else {

            System.out.println("导出未上线车辆数据========================" + id);
            String a[] = {"公司", "车号", "SIM卡号", "联系人", "联系电话", "最后汇报时间"};
            String b[] = {"COMP_NAME", "VEHI_NO", "VEHI_SIM", "OWN_NAME", "HOME_TEL", "TIM"};
            String gzb = "未上线车辆";
            String list = ycclcxservics.getSelectFindAllJxcsgServiceExportWsx(start, stop, yhmc);
            List<Map<String, Object>> list1 = downloadAct.strlist(list);
            downloadAct.download(request, response, a, b, gzb, list1);
            return null;
        }

    }

}

