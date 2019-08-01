package com.erxi.ms.controller;

import com.erxi.ms.result.DownloadAct;
import com.erxi.ms.result.Result;
import com.erxi.ms.service.YclyusjtjService;
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
 * 车辆营运数据统计
 */

@RestController
public class YclyusjtjAction {

    DownloadAct downloadAct = new DownloadAct();

    @Autowired
    private YclyusjtjService yclyusjtjService;






    @RequestMapping("/getYclyusjtjFindName")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAllName(@Param("cph") String cph) {

        //String ca=cph.replaceAll("filter","");
        String car="%"+cph+"%";

        Result<List<Map<String, Object>>> result = yclyusjtjService.getYclyusjtjFindAllServiceName(car);
        System.out.println("controller"+result);
        return result;
    }




    @RequestMapping("/getYclyusjtjFindGS")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAlGS(@Param("gs") String gs) {


        String car="%"+gs+"%";

        Result<List<Map<String, Object>>> result = yclyusjtjService.getYclyusjtjFindAllServiceGS(car);
        System.out.println("controller"+result);
        return result;
    }











    @RequestMapping("/getYclyusjtjFind")
    public Result<List<Map<String, Object>>> getYclyusjtjFindAll(@Param("cph") String cph, @Param("gs") String gs,

                                                                 @Param("kssj") String kssj, @Param("jssj") String jssj, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {

        String car=cph.replaceAll("filter","");
        String cars= car.replaceAll("浙","");
        String k = kssj.replaceAll("-", "");
        String j = jssj.replaceAll("-", "");
        Result<List<Map<String, Object>>> result = yclyusjtjService.getYclyusjtjFindAllService(cars, gs, k, j, pageIndex, pageSize);


        return result;
    }


    @RequestMapping("/getYclyusjtjExport")
    public String getYclyusjtjFindAllExpor(HttpServletRequest request, @Param("cph") String cph, @Param("gs") String gs,

                                           @Param("kssj") String kssj, @Param("jssj") String jssj, HttpServletResponse response) throws IOException {

        String car=cph.replaceAll("filter","");

        String cars= car.replaceAll("浙","");
        System.out.println("aaaaaaaaaaaaaaaaaaaaa"+cars);
        String gss=gs.replaceAll("filter","");
        String k=kssj.replaceAll("-","");
        String j=jssj.replaceAll("-","");

        String a[] = {"日期","车辆数(辆)", "营运次数(次)", "营运金额(元)", "营运里程(公里)", "空驶里程(公里)", "载客时间(分钟)", "载客等候时间(分钟)"};
        String b[] = {"TIME","CCPHM", "TTJCS", "RYSJE", "RZLC", "RKSLC", "RZKLC", "DHSJ"};
        String gzb = "车辆营运数据统计";

        String export = yclyusjtjService.getfinaAllExportt(cars, gss, k, j);
        List<Map<String, Object>> list = downloadAct.strlist(export);
        downloadAct.download(request, response, a, b, gzb, list);
        return null;
    }

}
