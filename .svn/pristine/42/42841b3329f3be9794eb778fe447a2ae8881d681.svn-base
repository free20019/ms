package com.erxi.ms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 车辆营运数据统计
 */

@Mapper
public interface YclyusjtjDao {


    @SelectProvider(type = getYclyusj.class, method = "getYclyusjtjFindAllDao")
    public List<Map<String, Object>> getYclyusjtjFindAllDao(@Param("cph") String cph, @Param("gs") String gs,
                                                            @Param("kssj") String kssj, @Param("jssj") String jssj, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);



    class getYclyusj {
        public String getYclyusjtjFindAllDao(@Param("cph") String cph, @Param("gs") String gs,
                                             @Param("kssj") String kssj, @Param("jssj") String jssj, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize) {
            
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String searchDate=sdf.format(calendar.getTime());
			int c=0;
        	String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and t.cphm like'%" + cph + "%'";
            }

            if (gs != null && !gs.isEmpty() && !gs.equals("null")
                    && gs.length() > 0) {
                tj += " and t.ZGS like '%" + gs + "%'";
            }

            if (kssj != null && !kssj.isEmpty() && !kssj.equals("null")
                    && kssj.length() > 0) {
                tj += " and t.DAY >='" + kssj + "'";
                c++;
				searchDate = kssj.replaceAll("-","").substring(0,6);
            }

            if (jssj != null && !jssj.isEmpty() && !jssj.equals("null")
                    && jssj.length() > 0) {
                tj += " and t.DAY <='" + jssj + "'";
                c++;
				searchDate = jssj.replaceAll("-","").substring(0,6);
            }
            if(c==0){
				tj += "and t.DAY ='" + searchDate + "'";
			}
            String sql = "select date_format(str_to_date(day,'%Y%m%d'),'%Y-%m-%d')TIME,count(cphm) as CCPHM, sum(t.tjcs) as TTJCS, round(sum(t.jine),2) as RYSJE,ceil(sum(t.zlc)) as RZLC, ceil(sum(t.kslc)) as RKSLC, ceil(sum(t.yssc)) as RZKLC,count(distinct(t.cphm)) RYYCL, ceil(sum(t.dhsj)) as DHSJ," +
                    "round(sum(t.yssc) / 60, 2) RYYSC, round(sum(t.tjcs) / count(distinct(t.cphm)), 0) ZZCS,round(sum(t.jine) / count(distinct(t.cphm)), 2) PJYS,round(sum(t.szlc) / (sum(t.yssc) / 60), 2) PJXSSD," +
                    "round(sum(t.yssc) / 60 / count(distinct(t.cphm)), 2) PJYYSC, round(sum(t.dhsj) / count(distinct(t.cphm)), 2) PJDHSJ from jjq_tj_"+searchDate.substring(0,6)+"_day  t" +
                    " where 1 = 1 and type = 5 " +
                    tj + "group by date_format(str_to_date(day,'%Y%m%d'),'%Y-%m-%d') order by date_format(str_to_date(day,'%Y%m%d'),'%Y-%m-%d')";
            System.out.println("统计sql="+sql);
            return sql;
        }
    }


    @SelectProvider(type = getYclyusjExport.class, method = "getYclyusjtjFindAllDaoExport")
    public List<Map<String, Object>> getYclyusjtjFindAllDaoExport(@Param("cph") String cph, @Param("gs") String gs,
                                                                  @Param("kssj") String kssj, @Param("jssj") String jssj);

    class getYclyusjExport {
        public String getYclyusjtjFindAllDaoExport(@Param("cph") String cph, @Param("gs") String gs,
                                                   @Param("kssj") String kssj, @Param("jssj") String jssj) {


        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String searchDate=sdf.format(calendar.getTime());
			int c=0;
        	String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and t.cphm like'%" + cph + "%'";
            }

            if (gs != null && !gs.isEmpty() && !gs.equals("null")
                    && gs.length() > 0) {
                tj += " and t.ZGS like '%" + gs + "%'";
            }

            if (kssj != null && !kssj.isEmpty() && !kssj.equals("null")
                    && kssj.length() > 0) {
                tj += " and t.DAY >='" + kssj + "'";
                c++;
				searchDate = kssj.replaceAll("-","").substring(0,6);
            }

            if (jssj != null && !jssj.isEmpty() && !jssj.equals("null")
                    && jssj.length() > 0) {
                tj += " and t.DAY <='" + jssj + "'";
                c++;
				searchDate = jssj.replaceAll("-","").substring(0,6);
            }
            if(c==0){
				tj += "and t.DAY ='" + searchDate + "'";
			}
            String sql = "select date_format(str_to_date(day,'%Y%m%d'),'%Y-%m-%d')TIME,count(cphm) as CCPHM, sum(t.tjcs) as TTJCS, round(sum(t.jine),2) as RYSJE,ceil(sum(t.zlc)) as RZLC, ceil(sum(t.kslc)) as RKSLC, ceil(sum(t.yssc)) as RZKLC,count(distinct(t.cphm)) RYYCL, ceil(sum(t.dhsj)) as DHSJ," +
                    "round(sum(t.yssc) / 60, 2) RYYSC, round(sum(t.tjcs) / count(distinct(t.cphm)), 0) ZZCS,round(sum(t.jine) / count(distinct(t.cphm)), 2) PJYS,round(sum(t.szlc) / (sum(t.yssc) / 60), 2) PJXSSD," +
                    "round(sum(t.yssc) / 60 / count(distinct(t.cphm)), 2) pjyysc, round(sum(t.dhsj) / count(distinct(t.cphm)), 2) pjdhsj from jjq_tj_"+searchDate.substring(0,6)+"_day  t" +
                    " where 1 = 1 and type = 5 " +
                    tj + "group by date_format(str_to_date(day,'%Y%m%d'),'%Y-%m-%d') order by day";

            return sql;
        }
    }





@Select(" SELECT DISTINCT T.CPHM CP FROM JJQ_TJ_201812_DAY  T")
 public List<Map<String, Object>> getYclyusjtjFindAllDaoName(@Param("cph") String cph);




    @Select("SELECT DISTINCT T.ZGS FROM JJQ_TJ_201812_DAY T where T.ZGS <> ''")
    public List<Map<String, Object>> getYclyusjtjFindAllDaoGS(@Param("gs") String gs);




}




