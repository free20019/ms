package com.erxi.ms.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 营运数据分析接口
 * 
 * @author 小坏
 * 
 * @date 2018年10月29日 下午4:26:38
 */

@Mapper
public interface JyysjfxfDao {


	@SelectProvider(type = getJysj.class, method = "getFindAlJysjlDao")
	public List<Map<String, Object>> getFindAlJysjlDao(
			@RequestParam("start") String start,
			@RequestParam("stop") String stop,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize);



    class getJysj {
		public String getFindAlJysjlDao(
				@RequestParam("start") String start,
				@RequestParam("stop") String stop,
				@RequestParam("pageIndex") Integer pageIndex,
				@RequestParam("pageSize") Integer pageSize) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
//			calendar.add(Calendar.DAY_OF_MONTH, -1);
			String searchDate=sdf.format(calendar.getTime());

			String tj = "";
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += "and b.DAY >='" + start + "'";
				c++;
				searchDate = start.replaceAll("-","").substring(0,6);
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += "and b.DAY <='" + stop + "'";
				c++;
				searchDate = stop.replaceAll("-","").substring(0,6);
			}
			if(c==0){
//				tj += "and b.DAY ='" + searchDate + "'";
			}

			String sql = "select (select count(*) COUNT from (select distinct DAY from  jjq_tj_"+searchDate.substring(0,6)+"_day b where 1=1 and type='5'";
			sql += tj;
			sql += ") m) as COUNT, tt.* from (select t.* from (select" + " "
					+ " date_format(str_to_date(b.DAY, '%Y%m%d'), '%Y-%m-%d') TIME,  round(ceil(avg(b.jine)),2)  JIN,ceil(avg(b.ZLC)) ZL,ceil(avg(b.KSLC)) KS,ceil(avg(b.TJCS)) TJ,ceil(avg(b.SZLC)) SJ,ceil(avg(b.DHSJ)) DH " +
					" from jjq_tj_"+searchDate.substring(0,6)+"_day b where 1=1 and type='5'";
			sql += tj+ " group by b.DAY";
			sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";

			System.out.println("营运数据分析"+sql);

			return sql;

		}
	}

	@SelectProvider(type = getJysExport.class, method = "getFindAlJysjlDaExport")
	public List<Map<String, Object>> getFindAlJysjlDaExport(@Param("start") String start, @Param("stop") String stop);

	class getJysExport {
		public String getFindAlJysjlDaExport(@Param("start") String start, @Param("stop") String stop) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			String searchDate=sdf.format(calendar.getTime());

			String tj = "";
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += "and b.DAY >='" + start + "'";
				c++;
				searchDate = start.replaceAll("-","").substring(0,6);
			}

			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += "and b.DAY <='" + stop + "'";
				c++;
				searchDate = stop.replaceAll("-","").substring(0,6);
			}
			if(c==0){
//				tj += "and b.DAY ='" + searchDate + "'";
			}

			String sql = "select t.* from (select" 
					+ " date_format(str_to_date(b.DAY, '%Y%m%d'), '%Y-%m-%d') TIME,  round(ceil(avg(b.jine)),2)  JIN,ceil(avg(b.ZLC)) ZL,ceil(avg(b.KSLC)) KS,ceil(avg(b.TJCS)) TJ,ceil(avg(b.SZLC)) SJ,ceil(avg(b.DHSJ)) DH " +
					" from jjq_tj_"+searchDate.substring(0,6)+"_day b where 1=1 and type='5'";
			sql += tj+ " group by b.DAY";
			sql += " ) t ";

			System.out.println("营运数据分析导出"+sql);

			return sql;


		}
	}


	@Select("SELECT DISTINCT J.CPHM FROM JJQ_TJ_201809_DAY J WHERE J.CPHM LIKE #{cph}")
	List<Map<String, Object>> getFindAlJysjlDaoName(@Param("cph") String cph);


}
