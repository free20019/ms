package com.erxi.ms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 小坏
 * @description 异常车辆查询 YcclcxDao
 * @date 2018/11/1 17:46
 */

@Mapper
public interface YcclcxDao {

	@SelectProvider(type = getYcclcx.class, method = "getFindAllYcclcxDao")
	public List<Map<String, Object>> getFindAllYcclcxDao(@Param("start") String start, @Param("stop") String stop,
			@Param("yhmc") String yhmc, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);



    class getYcclcx {
		public String getFindAllYcclcxDao(@Param("start") String start, @Param("stop") String stop,
				@Param("yhmc") String yhmc, @Param("pageIndex") Integer pageIndex,
				@Param("pageSize") Integer pageSize) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
			String tj = "";
			String tj2 = "";
			String tj3 = "";
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and shangche >=" + "str_to_date('" + start + "','%Y-%m-%d')";
				c++;
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and shangche <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				tj3 += " and shangche <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				c++;
			}

			if (yhmc != null && !yhmc.isEmpty() && !yhmc.equals("null") && yhmc.length() > 0) {
				tj2 += " and v.COMP_NAME like '%" + yhmc + "%'";
			}
			if(c==0){
				tj += " and shangche >=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
				tj += " and shangche <=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
			}
			String sql = " select (select count( VEHI_NO) COUNT from VW_VEHICLE  v  "
					+ " left join (select VHIC cphm from TB_JJQ_STATIC where 1=1 and `Y/N` ='N'";
			sql += tj;
			sql += " ) l on CONCAT('浙',l.cphm)=v.VEHI_NO where l.cphm is null and v.VEHI_NO like '浙A%'";
			sql += tj2;
			sql += " ) as COUNT, t.*,date_format(d.SHANGCHE,'%Y-%m-%d %H:%i:%s') TIM from (SELECT distinct VEHI_NO,VEHI_NUM,COMP_NAME,VEHI_SIM,HOME_TEL,NIGHT_TEL,OWN_NAME"
					+ " FROM VW_VEHICLE  v"
					+ " left join (select VHIC cphm from TB_JJQ_STATIC  where 1=1 and `Y/N` ='N'";
			sql += tj;
			sql += " ) l on CONCAT('浙',l.cphm)=v.VEHI_NO where  l.cphm is null and v.VEHI_NO like '浙A%' ";
			sql += tj2;
			sql += " ) t "
					+ " left join (select max(SHANGCHE) SHANGCHE,VHIC from TB_JJQ_STATIC where `Y/N` = 'Y'"+tj3+" GROUP BY VHIC) d"
					+ " on CONCAT('浙',d.VHIC)=t.VEHI_NO "
					+ " limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("异常车辆查询未营运"+sql);
			return sql;
		}
	}

	/**
	 * 未上线
	 * @param start
	 * @param stop
	 * @param yhmc
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getYcclcxWsx.class, method = "getFindAllYcclcxDaoWsx")
	public List<Map<String, Object>> getFindAllYcclcxDaoWsx(@Param("start") String start, @Param("stop") String stop,
			@Param("yhmc") String yhmc, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	class getYcclcxWsx {
		public String getFindAllYcclcxDaoWsx(@Param("start") String start, @Param("stop") String stop,
				@Param("yhmc") String yhmc, @Param("pageIndex") Integer pageIndex,
				@Param("pageSize") Integer pageSize) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
			String tj = "";
			String tj2 = "";
			int c=0;
			long ts = getDaySub(start, stop)+1;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and l.DB_TIME >=" + "str_to_date('" + start + "','%Y-%m-%d')";
				c++;
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and l.DB_TIME <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				c++;
			}

			if (yhmc != null && !yhmc.isEmpty() && !yhmc.equals("null") && yhmc.length() > 0) {
				tj2 += " and v.COMP_NAME like '%" + yhmc + "%'";
			}
			if(c==0){
				tj += " and l.DB_TIME >=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
				tj += " and l.DB_TIME <=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
			}
			String sql = " select ( select count(*) FROM VW_VEHICLE  v,"
					+ "(select vehi_no cphm,date_format(max(ONLINE_TIME),'%Y-%m-%d %H:%i:%s') TIM,count(1) c from TB_TAXI_NOT_ONLINE l where 1=1 ";
			sql += tj;
			sql += " group by vehi_no) l  where l.cphm=v.VEHI_NO and l.cphm is not null and v.VEHI_NO like '浙A%' and l.c >="+ts;
			sql += tj2;
			sql += ") as COUNT, t.* from (SELECT l.TIM,v.VEHI_NO,v.VEHI_NUM,v.COMP_NAME,v.VEHI_SIM,v.HOME_TEL,v.NIGHT_TEL,v.OWN_NAME"
					+ " FROM VW_VEHICLE  v,(select vehi_no cphm,date_format(max(ONLINE_TIME),'%Y-%m-%d %H:%i:%s') TIM,count(1) c from TB_TAXI_NOT_ONLINE l where 1=1 ";
			sql += tj;
			sql += " group by vehi_no) l  where l.cphm=v.VEHI_NO and l.cphm is not null and v.VEHI_NO like '浙A%' and l.c >="+ts;
			sql += tj2;
			sql += " ) t "
					+ " limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("异常车辆查询未上线"+sql);
			return sql;
		}

		public static long getDaySub(String beginDateStr,String endDateStr)
	    {
	        long day=0;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date beginDate;
	        Date endDate;
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE, -1); //得到前一天
	        Date date = null;
	        try {
				date = format.parse(format.format(calendar.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        try {
	            beginDate = format.parse(beginDateStr);
	            endDate= format.parse(endDateStr);
	            if(endDate.getTime()>date.getTime()){
	                day=(date.getTime()-beginDate.getTime())/(24*60*60*1000);
	            }else{
	                day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return day;
	    }
	}

	/**
	 * 无营运
	 * @param start
	 * @param stop
	 * @param yhmc
	 * @return
	 */
	@SelectProvider(type = getExport.class, method = "getFindAlJysjlDaExportt")
	public List<Map<String, Object>> getFindAlJysjlDaExportt(@Param("start") String start, @Param("stop") String stop,
			@Param("yhmc") String yhmc);

	class getExport {
		public String getFindAlJysjlDaExportt(@Param("start") String start, @Param("stop") String stop,
				@Param("yhmc") String yhmc) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
			String tj = "";
			String tj2 = "";
			String tj3 = "";
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and shangche >=" + "str_to_date('" + start + "','%Y-%m-%d')";
				c++;
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and shangche <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				tj3 += " and shangche <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				c++;
			}

			if (yhmc != null && !yhmc.isEmpty() && !yhmc.equals("null") && yhmc.length() > 0) {
				tj2 += " and v.COMP_NAME like '%" + yhmc + "%'";
			}
			if(c==0){
				tj += " and shangche >=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
				tj += " and shangche <=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
			}
			String sql = "select  t.*,date_format(d.SHANGCHE,'%Y-%m-%d %H:%i:%s') TIM from (SELECT distinct VEHI_NO,VEHI_NUM,COMP_NAME,VEHI_SIM,HOME_TEL,NIGHT_TEL,OWN_NAME"
					+ " FROM VW_VEHICLE  v"
					+ " left join (select VHIC cphm from TB_JJQ_STATIC  where 1=1 and `Y/N` ='N'";
			sql += tj;
			sql += " ) l on CONCAT('浙',l.cphm)=v.VEHI_NO where  l.cphm is null and v.VEHI_NO like '浙A%' ";
			sql += tj2;
			sql += " ) t "
					+ " left join (select max(SHANGCHE) SHANGCHE,VHIC from TB_JJQ_STATIC where `Y/N` = 'Y'"+tj3+" GROUP BY VHIC) d"
					+ " on CONCAT('浙',d.VHIC)=t.VEHI_NO ";
			System.out.println("异常车辆查询未营运导出"+sql);
			return sql;

		}

	}

	/**
	 * Export
	 * @param start
	 * @param stop
	 * @param yhmc
	 * @return
	 */

	@SelectProvider(type = getExportWsx.class, method = "getFindAlJysjlDaExporttWsx")
	public List<Map<String, Object>> getFindAllYcclcxDaoExportWsx(@Param("start") String start, @Param("stop") String stop,
			@Param("yhmc") String yhmc);

	class getExportWsx {
		public String getFindAlJysjlDaExporttWsx(@Param("start") String start, @Param("stop") String stop,
				@Param("yhmc") String yhmc) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
			String tj = "";
			String tj2 = "";
			int c=0;
			long ts = getDaySub(start, stop)+1;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and l.DB_TIME >=" + "str_to_date('" + start + "','%Y-%m-%d')";
				c++;
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and l.DB_TIME <=" + "str_to_date('" + stop + "','%Y-%m-%d')";
				c++;
			}

			if (yhmc != null && !yhmc.isEmpty() && !yhmc.equals("null") && yhmc.length() > 0) {
				tj2 += " and v.COMP_NAME like '%" + yhmc + "%'";
			}
			if(c==0){
				tj += " and l.DB_TIME >=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
				tj += " and l.DB_TIME <=str_to_date('"+sdf.format(new Date())+"','%Y-%m-%d')";
			}
			String sql = "SELECT l.TIM,v.VEHI_NO,v.VEHI_NUM,v.COMP_NAME,v.VEHI_SIM,v.HOME_TEL,v.NIGHT_TEL,v.OWN_NAME"
					+ " FROM VW_VEHICLE  v,(select vehi_no cphm,date_format(max(ONLINE_TIME),'%Y-%m-%d %H:%i:%s') TIM,count(1) c from TB_TAXI_NOT_ONLINE l where 1=1 ";
			sql += tj;
			sql += " group by vehi_no) l  where l.cphm=v.VEHI_NO and l.cphm is not null and v.VEHI_NO like '浙A%' and l.c >="+ts;
			sql += tj2;
			System.out.println("异常车辆查询导出"+sql);
			return sql;
		}
		public static long getDaySub(String beginDateStr,String endDateStr)
	    {
	        long day=0;
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date beginDate;
	        Date endDate;
	        Calendar calendar = Calendar.getInstance();
	        calendar.add(Calendar.DATE, -1); //得到前一天
	        Date date = null;
	        try {
				date = format.parse(format.format(calendar.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        try {
	            beginDate = format.parse(beginDateStr);
	            endDate= format.parse(endDateStr);
	            if(endDate.getTime()>date.getTime()){
	                day=(date.getTime()-beginDate.getTime())/(24*60*60*1000);
	            }else{
	                day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return day;
	    }

	}


	/**
	 * 车牌号
	 * @return
	 */

//@Select("SELECT DISTINCT e.COMP_NAME FROM VW_VEHICLE  e, TB_JJQ_STATIC  s  WHERE e.VEHI_NO = CONCAT('浙',s.VHIC) and e.COMP_NAME like #{name}")
@Select("SELECT DISTINCT COMP_NAME FROM VW_VEHICLE")
	List<Map<String, Object>> getFindAllYcclcxDaoName(@Param("name") String name);
}