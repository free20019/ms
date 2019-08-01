package com.erxi.ms.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.erxi.ms.result.FastJsonUtil;


@Mapper
public interface OneToTwoDao {

	
	/**
	 * 上线率
	 * @param i
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findyingyun24")
	public List<Map<String, Object>> findyingyun24(
			@Param("i") Integer i);
	
	@SelectProvider(type = ottd.class, method = "getSxlAverage")
	public List<Map<String, Object>> getSxlAverage(
			@Param("st") String st,
			@Param("et")  String et);
	
	
	/**
	 * 在线营运
	 * @param i
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findzxyy24")
	public List<Map<String, Object>> findzxyy24(
			@Param("i") Integer i);
	
	@SelectProvider(type = ottd.class, method = "findzzyyaverage")
	public List<Map<String, Object>> findzzyyaverage(
			@Param("st") String st,
			@Param("et")  String et);
	
	
	@SelectProvider(type = ottd.class, method = "findzxyy48")
	public List<Map<String, Object>> findzxyy48();
	
	@SelectProvider(type = ottd.class, method = "findwozaixian")
	public String findwozaixian(
			@Param("stime") String stime,
			@Param("etime")  String etime);
	
	
	/**
	 * 一小时未营运
	 * @param i
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findweiyingyun24")
	public List<Map<String, Object>> findweiyingyun24(
			@Param("i") Integer i);
	
	@SelectProvider(type = ottd.class, method = "findaveragezaixian")
	public List<Map<String, Object>> findaveragezaixian(
			@Param("st") String st,
			@Param("et")  String et);
	
	@SelectProvider(type = ottd.class, method = "findmeiyouyingyun")
	public int findmeiyouyingyun(
			@Param("stime") String stime,
			@Param("etime")  String etime);
	
	
	@SelectProvider(type = ottd.class, method = "findweiyingyun48")
	public List<Map<String, Object>> findweiyingyun48();
	
	
	/**
	 * 重点监控区域车辆数
	 * @param i
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findarea")
	public List<Map<String, Object>> findarea(
			@Param("i") Integer i);
	
	@SelectProvider(type = ottd.class, method = "findshangzhouaverg")
	public List<Map<String, Object>> findshangzhouaverg(
			@Param("st") String st,
			@Param("et")  String et);
	
	
	/**
	 * 疑似停运
	 * @param st
	 * @param et
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findystyaverage")
	public List<Map<String, Object>> findystyaverage(
			@Param("st") String st,
			@Param("et")  String et);
	
	@SelectProvider(type = ottd.class, method = "findysty48")
	public List<Map<String, Object>> findysty48();
	
	@SelectProvider(type = ottd.class, method = "findwotingyun")
	public int findwotingyun(
			@Param("stime") String stime,
			@Param("etime")  String etime);
	
	
	
	
	/**
	 * 保有量
	 * @param postData
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findbyl")
	public List<Map<String, Object>> findbyl(
			@Param("postData") String postData);
	
	
	
	/**
	 * .重点监控区域车辆基类
	 * @param id
	 * @param time
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "yyqk24")
	public List<Map<String, Object>> yyqk24(
			@Param("id") String id, 
			@Param("time") String time);
	
	/**
	 * 重点监控区域车辆平均
	 * @param id
	 * @param st
	 * @param et
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "yyqkaverage")
	public List<Map<String, Object>> yyqkaverage(
			@Param("id") String id, 
			@Param("st") String st,
			@Param("et") String et);
	
	/**
	 * 重点监控区域车辆上月最高值和最低值
	 * @param id
	 * @param zuotian
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "findmaxmin")
	public List<Map<String, Object>> findmaxmin(
			@Param("id") String id, 
			@Param("zuotian") String zuotian);
	
	/**
	 * 重点区域车辆明细
	 * @param time
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "clmxinfo1")
	public List<Map<String, Object>> clmxinfo1(
			@Param("time") String time);
	
	@SelectProvider(type = ottd.class, method = "clmxinfo2")
	public List<Map<String, Object>> clmxinfo2(
			@Param("riq") String riq, 
			@Param("stime") String stime,
			@Param("etime") String etime, 
			@Param("speed") String speed, 
			@Param("id") String id);
	
	
	/**
	 * 实载率
	 * @param st
	 * @param et
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "szlaverage")
	public List<Map<String, Object>> szlaverage(
			@Param("st") String st,
			@Param("et")  String et);
	
	
	@SelectProvider(type = ottd.class, method = "szl48")
	public List<Map<String, Object>> szl48(
			@Param("time2")  String time2,
			@Param("time")  String time);
	
	@SelectProvider(type = ottd.class, method = "find6tos")
	public List<Map<String, Object>> find6tos(
			@Param("stime")  String stime,
			@Param("etime")  String etime);
	
	@SelectProvider(type = ottd.class, method = "szl24")
	public List<Map<String, Object>> szl24(
			@Param("time")  String time);
	
	@SelectProvider(type = ottd.class, method = "findzmaxmin")
	public List<Map<String, Object>> findzmaxmin(
			@Param("zuotian")  String zuotian);
	
	
	
	/**
	 * 重点区域上线率分析
	 * @param Ntime
	 * @param baid
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "sxlaverage")
	public List<Map<String, Object>> sxlaverage(
			@Param("Ntime")  String Ntime,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "sxl48")
	public List<Map<String, Object>> sxl48(
			@Param("time2")  String time2,
			@Param("time")  String time,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "sxl24")
	public List<Map<String, Object>> sxl24(
			@Param("time")  String time,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "findsmaxmin")
	public List<Map<String, Object>> findsmaxmin(
			@Param("zuotian")  String zuotian,
			@Param("baid")  String baid);
	
	
	/**
	 * 重车率
	 * @param Ntime
	 * @param baid
	 * @return
	 */
	@SelectProvider(type = ottd.class, method = "zclaverage")
	public List<Map<String, Object>> zclaverage(
			@Param("Ntime")  String Ntime,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "zcl48")
	public List<Map<String, Object>> zcl48(
			@Param("time2")  String time2,
			@Param("time")  String time,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "zcl24")
	public List<Map<String, Object>> zcl24(
			@Param("time")  String time,
			@Param("baid")  String baid);
	
	@SelectProvider(type = ottd.class, method = "findzclmaxmin")
	public List<Map<String, Object>> findzclmaxmin(
			@Param("zuotian")  String zuotian,
			@Param("baid")  String baid);
	
	
	class ottd{
		
		/**
		 * 上线率
		 * @param i
		 * @return
		 */
		public String findyingyun24(
				@Param("i") Integer i){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
			String time = dft.format(date.getTime());
			String sql = "select ONLINE_RATE,date_format(db_time,'%H')s from TB_TAXI_LOAD_ONLINE_RATE t where "
					+ "db_time>=str_to_date('"
					+ time
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00' order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String getSxlAverage(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select rpad(round(avg(replace(ONLINE_RATE,'%',''))),length(round(avg(replace(ONLINE_RATE,'%',''))))+1,'%') ONLINE_RATE ,s "
					+ " from(select ONLINE_RATE,date_format(db_time,'%H') s from TB_TAXI_LOAD_ONLINE_RATE t "
					+ " where db_time>=str_to_date('"
					+ st
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') and "
					+ " db_time<=str_to_date('"
					+ st
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00'"
					+ " and ba_id='"+et+"') t"
					+ " group by s order by s asc";
			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 在线营运
		 * @param i
		 * @return
		 */
		public String findzxyy24(
				@Param("i") Integer i){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
			String time = dft.format(date.getTime());
			String sql = "select RUN_RATE,date_format(db_time,'%d%H')s from TB_TAXI_RUN_RATE t where "
					+ "db_time>=str_to_date('"
					+ time
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String findzzyyaverage(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select rpad(round(avg(replace(run_rate,'%',''))),length(round(avg(replace(run_rate,'%',''))))+1,'%') ONLINE_RATE ,s "
					+ " from(select run_rate,date_format(db_time,'%H') s from TB_TAXI_RUN_RATE t "
					+ " where db_time>=str_to_date('"
					+ st
					+ "','%Y-%m-%d %H:%i:%s') and "
					+ " db_time<=str_to_date('"
					+ et
					+ "','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00') t"
					+ " group by s order by s asc";
			System.out.println(sql);
			return sql;
		}
		
		public String findzxyy48(){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE));
			String time = dft.format(date.getTime());
			Calendar date2 = Calendar.getInstance();
			date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			String time2 = dft.format(date2.getTime());
			String sql = "select  RUN_RATE,date_format(db_time,'%d%H')s from TB_TAXI_RUN_RATE t where "
					+ "db_time>=str_to_date('"
					+ time2
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String findwozaixian(
				@Param("stime") String stime,
				@Param("etime")  String etime){
			String sql = "select round(count(distinct (vhic))*100/13512) c from TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ " t"
					+ " where shangche>=str_to_date('"
					+ stime
					+ "','%Y-%m-%d %H')"
					+ " and  shangche<str_to_date('"
					+ etime + "','%Y-%m-%d %H')";
			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 一小时未营运
		 * @param i
		 * @return
		 */
		public String findweiyingyun24(
				@Param("i") Integer i){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
			String time = dft.format(date.getTime());
			String sql = "select (13512-run_taxis) RUN_TAXIS,date_format(db_time,'%d%H')s from TB_TAXI_RUN_INFO_RECORD_TEST t where "
					+ "db_time>=str_to_date('"
					+ time
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00' order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String findaveragezaixian(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select (13512-ROUND(avg(replace(run_taxis,'%','')))) RUN_TAXIS ,s "
					+ " from(select run_taxis,date_format(db_time,'%H')s from TB_TAXI_RUN_INFO_RECORD_TEST t "
					+ " where db_time>=str_to_date('"
					+ st
					+ "','%Y-%m-%d %H:%i:%s') and "
					+ " db_time<=str_to_date('"
					+ et
					+ "','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00') t"
					+ " group by s order by s asc";
			System.out.println(sql);
			return sql;
		}
		
		public String findmeiyouyingyun(
				@Param("stime") String stime,
				@Param("etime")  String etime){
			String sql = "select count(distinct (vhic)) c from TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ " t"
					+ " where shangche>=str_to_date('"
					+ stime
					+ "','%Y-%m-%d %H')"
					+ " and  shangche<str_to_date('"
					+ etime + "','%Y-%m-%d %H')";
			System.out.println(sql);
			return sql;
		}
		
		public String findweiyingyun48(){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE));
			String time = dft.format(date.getTime());
			Calendar date2 = Calendar.getInstance();
			date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			String time2 = dft.format(date2.getTime());
			String sql = "select  (13512-run_taxis) RUN_TAXIS,date_format(db_time,'%d%H')s from TB_TAXI_RUN_INFO_RECORD_TEST t where "
					+ "db_time>=str_to_date('"
					+ time2
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 
		 * @param i
		 * @return
		 */
		public String findarea(
				@Param("i") Integer i){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE) - i);
			String time = dft.format(date.getTime()); 
			String sql = "select Cast(sum(taxi_quantity) as SIGNED) RATE,s from(select taxi_quantity,date_format(db_time,'%H')s from TB_TAXI_AREA_CONFIGURATION2019 t where "
					+ "db_time>=str_to_date('"
					+ time
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  and area_id in (select area_id from tb_area)) t group by s order by s";
			System.out.println(sql);
			return sql;
		}
		
		public String findshangzhouaverg(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select Cast(ROUND(sum(taxi_quantity)/7) as SIGNED) RATE ,s "
					+ " from(select taxi_quantity,date_format(db_time,'%H')s from TB_TAXI_AREA_CONFIGURATION2019 t "
					+ " where db_time>=str_to_date('" + st
					+ "','%Y-%m-%d %H:%i:%s') and " + " db_time<=str_to_date('"
					+ et
					+ "','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00' "
					+ "and area_id in (select area_id from tb_area)) c"
					+ " group by s order by s asc";

			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 疑似停运
		 * @param st
		 * @param et
		 * @return
		 */
		public String findystyaverage(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select cast((13512-ROUND(avg(replace(run_count,'%','')))) as decimal(13,0)) RUN_COUNT ,S "
					+ " from(select run_count,date_format(db_time,'%H')s from TB_TAXI_RUN_COUNT t "
					+ " where db_time>=str_to_date('"
					+ st
					+ "','%Y-%m-%d %H:%i:%s') and "
					+ " db_time<=str_to_date('"
					+ et
					+ "','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00') t"
					+ " group by s order by s asc";
			System.out.println(sql);
			return sql;
		}
		
		public String findysty48(){
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar date = Calendar.getInstance();
			date.set(Calendar.DATE, date.get(Calendar.DATE));
			String time = dft.format(date.getTime());
			Calendar date2 = Calendar.getInstance();
			date2.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			String time2 = dft.format(date2.getTime());
			String sql = "select cast((13512-run_count) as decimal(13,0)) RUN_COUNT,date_format(db_time,'%d%H')S from TB_TAXI_RUN_COUNT t where "
					+ "db_time>=str_to_date('"
					+ time2
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String findwotingyun(
				@Param("stime") String stime,
				@Param("etime")  String etime){
			String sql = "select count(distinct (vhic)) c from TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ " t"
					+ " where shangche>=str_to_date('"
					+ stime
					+ "','%Y-%m-%d %H:%i:%s')"
					+ " and  shangche<str_to_date('"
					+ etime + ":59:59','%Y-%m-%d %H:%i:%s')";
			System.out.println(sql);
			return sql;
		}
		
		public String findbyl(
				@Param("postData") String postData){
			Map<String, Object> paramMap = FastJsonUtil.stringToMap(postData);
			int page = Integer.valueOf(String.valueOf(paramMap.get("page")));
			int pageSize = Integer.valueOf(String.valueOf(paramMap.get("pageSize")));
			String comp_id = String.valueOf(paramMap.get("comp_id"));
			String sql = " select date_format(VEHI_DATE,'%Y') VEHI_DATE,"
					+ "count(vehi_no) C from TB_VEHICLE"
					+ " where vehi_no like '%浙AT%' ";
			if(comp_id!=null&&comp_id.length()>0
					&&!comp_id.equals("null")&&!comp_id.equals("0")){
				sql+=" and comp_id = '"+comp_id +"'";
			}
			sql += " group by date_format(VEHI_DATE,'%Y') order by VEHI_DATE";
			System.out.println(sql);
			return sql;
		}
		
		public String yyqk24(
				@Param("id") String id, 
				@Param("time") String time){
			String sql = "";
			if (id != null && id.length() != 0 && !id.equals("0")) {
				sql = "select sum(taxi_quantity) count,date_format(db_time,'%H%i') s from "
						+ "(select * from TB_TAXI_AREA_CONFIGURATION2019 t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ " ) c where (date_format(db_time,'%i')='00' or date_format(db_time,'%i')='30')  "
						+ " and area_id ='"
						+ id
						+ "'"
						+ "  group by  date_format(db_time,'%H%i') order by date_format(db_time,'%H%i')";
			} else {
				sql = "select sum(taxi_quantity) count,date_format(db_time,'%H%i') s from "
						+ "(select * from TB_TAXI_AREA_CONFIGURATION2019 t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ " ) c where (date_format(db_time,'%i')='00' or date_format(db_time,'%i')='30')  "
						+ " and area_id in (select area_id from tb_area )  "
						+ "  group by  date_format(db_time,'%H%i') order by date_format(db_time,'%H%i')";

			}
			System.out.println(sql);
			return sql;
		}
		
		public String yyqkaverage(
				@Param("id") String id, 
				@Param("st") String st,
				@Param("et") String et){
			String sql = "";
			if (id != null && id.length() != 0 && !id.equals("0")) {
				sql = "select ROUND(sum(taxi_quantity)/7) count,date_format(db_time,'%H%i') s from "
						+ "(select * from TB_TAXI_AREA_CONFIGURATION2019 t where "
						+ " db_time>=str_to_date('"
						+ st
						+ " ','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ et
						+ " ','%Y-%m-%d %H:%i:%s') "
						+ " ) c where (date_format(db_time,'%i')='00' or date_format(db_time,'%i')='30')  "
						+ " and area_id ='"
						+ id
						+ "'  "
						+ "  group by  date_format(db_time,'%H%i') order by date_format(db_time,'%H%i')";
			} else {
				sql = "select ROUND(sum(taxi_quantity)/7) count,date_format(db_time,'%H%i') s from "
						+ "(select * from TB_TAXI_AREA_CONFIGURATION2019 t where "
						+ " db_time>=str_to_date('"
						+ st
						+ " ','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ et
						+ " ','%Y-%m-%d %H:%i:%s') "
						+ " ) c where (date_format(db_time,'%i')='00' or date_format(db_time,'%i')='30')  "
						+ " and area_id in (select area_id from tb_area ) "
						+ "  group by  date_format(db_time,'%H%i') order by date_format(db_time,'%H%i')";

			}
			System.out.println(sql);
			return sql;
		}
		
		public String findmaxmin(
				@Param("id") String id, 
				@Param("zuotian") String zuotian){
			String sql = "";
			if (id != null && id.length() > 0 && !id.equals("null")
					&& !id.equals("0")) {
				sql = "select * from TB_AREA_HALF_MONTH t where"
						+ " enddate=str_to_date('" + zuotian + "','%Y-%m-%d') "
						+ "and area_id='" + id + "'";
			} else {
				sql = "select  * from TB_AREA_HALF_MONTH t where"
						+ " enddate=str_to_date('" + zuotian + "','%Y-%m-%d') "
						+ "and area_id ='0'";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String clmxinfo1(
				@Param("time") String time){
			String sql = "select * from TB_AREA_VEHINO t where time=str_to_date('"
					+ time + "','%Y-%m-%d')";
			System.out.println(sql);
			return sql;
		}
		
		public String clmxinfo2(
				@Param("riq") String riq, 
				@Param("stime") String stime,
				@Param("etime") String etime,
				@Param("speed") String speed,
				@Param("id") String id){
			String sql = "";
			String date = stime.replaceAll("-", "").substring(0,6);
			if (id != null && id.length() != 0 && !id.equals("0")) {
				 sql = "select * from TB_TAXI_AREA_INFO_RECORD"+date
						+ " t,VW_VEHICLE v"
						+ "  where t.vehi_no=v.vehi_no and record_time>="
						+ "str_to_date('" + stime + "','%Y-%m-%d %H:%i:%s') and"
						+ " record_time<=str_to_date('" + etime
						+ "','%Y-%m-%d %H:%i:%s') and  area_id ='" + id
						+ "' ";
				if (speed != null && speed.length() > 0 && !speed.equals("null")) {
					sql += " and speed >=" + speed;
				}
			}else {
				sql = "select * from TB_TAXI_AREA_INFO_RECORD"+date
						+ " t"
						+ "  where record_time>="
						+ "str_to_date('" + stime + "','%Y-%m-%d %H:%i:%s') and"
						+ " record_time<=str_to_date('" + etime
						+ "','%Y-%m-%d %H:%i:%s') "
						+ " and area_id in (select area_id from tb_area )";
				if (speed != null && speed.length() > 0 && !speed.equals("null")) {
					sql += " and speed >=" + speed;
				}
			}
//			if (id != null && id.length() != 0 && !id.equals("0")) {
//				 sql = "select date_format(record_time,'%H:%i') RECORD_TIME,AREA_NAME,count(*) COUNT from TB_TAXI_AREA_INFO_RECORD"+date
//						+ " t,VW_VEHICLE v"
//						+ "  where t.vehi_no=v.vehi_no and record_time>="
//						+ "str_to_date('" + stime + "','%Y-%m-%d %H:%i:%s') and"
//						+ " record_time<=str_to_date('" + etime
//						+ "','%Y-%m-%d %H:%i:%s') and  area_id ='" + id
//						+ "' ";
//				if (speed != null && speed.length() > 0 && !speed.equals("null")) {
//					sql += " and speed >=" + speed;
//				}
//				sql +=" group by date_format(record_time,'%H:%i'),area_name order by area_name";
//			}else {
//				sql = "select date_format(record_time,'%H:%i') RECORD_TIME,AREA_NAME,count(*) COUNT  from TB_TAXI_AREA_INFO_RECORD"+date
//						+ " t"
//						+ "  where record_time>="
//						+ "str_to_date('" + stime + "','%Y-%m-%d %H:%i:%s') and"
//						+ " record_time<=str_to_date('" + etime
//						+ "','%Y-%m-%d %H:%i:%s') "
//						+ " and area_id in (select area_id from tb_area )";
//				if (speed != null && speed.length() > 0 && !speed.equals("null")) {
//					sql += " and speed >=" + speed;
//				}
//				sql +=" group by date_format(record_time,'%H:%i'),area_name order by area_name";
//			}
			System.out.println(sql);
			return sql;
		}
		
		public String szlaverage(
				@Param("st") String st,
				@Param("et")  String et){
			String sql = "select rpad(round(avg(replace(actual_load_rate,'%','')),2),"
					+ "length(round(avg(replace(actual_load_rate,'%','')),2))+1,'%') actual_load_rate ,s "
					+ " from(select actual_load_rate,date_format(db_time,'%H')s from"
					+ " TB_TAXI_RUN_INFO_RECORD_test t "
					+ " where db_time>=str_to_date('"
					+ st
					+ "','%Y-%m-%d %H:%i:%s') and "
					+ " db_time<=str_to_date('"
					+ et
					+ "','%Y-%m-%d %H:%i:%s') and date_format(db_time,'%i')='00') t"
					+ " group by s order by s asc";
			System.out.println(sql);
			return sql;
		}
		
		public String szl48(
				@Param("time2")  String time2,
				@Param("time")  String time){
			String sql = "select  actual_load_rate,date_format(db_time,'%d%H')s from "
					+ "  TB_TAXI_RUN_INFO_RECORD_test t where "
					+ "db_time>=str_to_date('"
					+ time2
					+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
					+ ""
					+ "and db_time<=str_to_date('"
					+ time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ "and date_format(db_time,'%i')='00'  order by db_time ";
			System.out.println(sql);
			return sql;
		}
		
		public String find6tos(
				@Param("stime")  String stime,
				@Param("etime")  String etime){
			String sql = " select  ROUND(jicheng/(jicheng+kongshi)*100,2) s "
					+ "from (select sum(jicheng) jicheng,sum(kongshi)  kongshi from TB_CITIZEN_"
					+ stime.substring(0, 4)
					+ " t"
					+ " where shangche>=str_to_date('"
					+ stime
					+ "','%Y-%m-%d %H')"
					+ " and  shangche<str_to_date('"
					+ etime + "','%Y-%m-%d %H')) c";
			System.out.println(sql);
			return sql;
		}
		
		public String szl24(
				@Param("time")  String time){
			String sql = "select actual_load_rate,date_format(db_time,'%H') s from "
					+ "(select * from TB_TAXI_RUN_INFO_RECORD_test t where "
					+ " db_time>=str_to_date('" + time
					+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
					+ " and db_time<=str_to_date('" + time
					+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
					+ " ) c where date_format(db_time,'%i')='00' "
					+ "   order by date_format(db_time,'%H%i')";
			System.out.println(sql);
			return sql;
		}
		
		public String findzmaxmin(
				@Param("zuotian")  String zuotian){
			String sql = "select * from TB_HALF_MONTH_ONLINE_RUN_RATE t where"
					+ " enddate=str_to_date('" + zuotian + "','%Y-%m-%d') ";
			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 重点区域上线率分析
		 * @param Ntime
		 * @param baid
		 * @return
		 */
		public String sxlaverage(
				@Param("Ntime")  String Ntime,
				@Param("baid")  String baid){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
			String time = Ntime;
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sdf.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.add(Calendar.DATE, -7);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date sTime = calendar.getTime();
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			Date eTime = calendar.getTime();
			String st = sdf.format(sTime) + " 00:00:00";
			String et = sdf.format(eTime) + " 23:59:59";
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select rpad(round(avg(replace(online_rate,'%','')),2),"
						+ "length(round(avg(replace(online_rate,'%','')),2))+1,'%') online_rate ,s "
						+ " from(select online_rate,date_format(db_time,'%H%i')s from"
						+ " TB_TAXI_LOAD_ONLINE_RATE t "
						+ " where db_time>=str_to_date('"
						+ st
						+ "','%Y-%m-%d %H:%i:%s') and "
						+ " db_time<=str_to_date('"
						+ et
						+ "','%Y-%m-%d %H:%i:%s') and (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00') and ba_id='"
						+ baid + "') t" + " group by s order by s asc";
			}else {
				sql = "select rpad(round(avg(replace(online_rate,'%','')),2),"
						+ "length(round(avg(replace(online_rate,'%','')),2))+1,'%') online_rate ,s "
						+ " from(select online_rate,date_format(db_time,'%H%i')s from"
						+ " TB_TAXI_LOAD_ONLINE_RATE t "
						+ " where db_time>=str_to_date('"
						+ st
						+ "','%Y-%m-%d %H:%i:%s') and "
						+ " db_time<=str_to_date('"
						+ et
						+ "','%Y-%m-%d %H:%i:%s') and (date_format(db_time,'%i')='00') ) t" + " group by s order by s asc";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String sxl24(
				@Param("time")  String time,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select  online_rate,date_format(db_time,'%H') s from "
						+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ " and ba_id = '"
						+ baid
						+ "') c where (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00')  "
						+ "   order by date_format(db_time,'hh24mi')";
			}else {
				sql = "select rpad(round(avg(replace(ONLINE_RATE,'%',''))),length(round(avg(replace(ONLINE_RATE,'%',''))))+1,'%')"
						+ " online_rate,date_format(db_time,'%H') s from "
						+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ ") c where (date_format(db_time,'%i')='00')  "
						+ " group by s"
						+ "   order by date_format(db_time,'hh24mi')";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String sxl48(
				@Param("time2")  String time2,
				@Param("time")  String time,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select  ONLINE_RATE,date_format(db_time,'%d%H')s from "
						+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
						+ "db_time>=str_to_date('"
						+ time2
						+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
						+ "and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ "and ( date_format(db_time,'%i')='00') and ba_id = '"
						+ baid + "' order by db_time ";
			}else {
				sql = "select rpad(round(avg(replace(ONLINE_RATE,'%',''))),length(round(avg(replace(ONLINE_RATE,'%',''))))+1,'%')"
						+ " online_rate,date_format(db_time,'%d%H')s from "
						+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
						+ "db_time>=str_to_date('"
						+ time2
						+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
						+ "and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ "and ( date_format(db_time,'%i')='00')"
						+ " group by s"
						+ " order by db_time ";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String findsmaxmin(
				@Param("zuotian")  String zuotian,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
						+ " enddate=str_to_date('" + zuotian
						+ "','%Y-%m-%d') and ba_id='" + baid + "' ";
			}else {
				sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
						+ " enddate=str_to_date('" + zuotian
						+ "','%Y-%m-%d') ";
			}
			System.out.println(sql);
			return sql;
		}
		
		/**
		 * 重车率
		 * @param Ntime
		 * @param baid
		 * @return
		 */
		public String zclaverage(
				@Param("Ntime")  String Ntime,
				@Param("baid")  String baid){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
			// Date d = new Date();
			// String time = sdf.format(d);
			String time = Ntime;
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sdf.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.add(Calendar.DATE, -7);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			Date sTime = calendar.getTime();
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			Date eTime = calendar.getTime();
			String st = sdf.format(sTime) + " 00:00:00";
			String et = sdf.format(eTime) + " 23:59:59";
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				 sql = "select rpad(round(avg(replace(load_rate,'%','')),2),"
							+ "length(round(avg(replace(load_rate,'%','')),2))+1,'%') load_rate ,s "
							+ " from(select load_rate,date_format(db_time,'%H%i')s from"
							+ " TB_TAXI_LOAD_ONLINE_RATE t "
							+ " where db_time>=str_to_date('"
							+ st
							+ "','%Y-%m-%d %H:%i:%s') and "
							+ " db_time<=str_to_date('"
							+ et
							+ "','%Y-%m-%d %H:%i:%s') and (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00') and ba_id='"
							+ baid + "') t" + " group by s order by s asc";
			}else{
				 sql = "select rpad(round(avg(replace(load_rate,'%','')),2),"
							+ "length(round(avg(replace(load_rate,'%','')),2))+1,'%') load_rate ,s "
							+ " from(select load_rate,date_format(db_time,'%H%i')s from"
							+ " TB_TAXI_LOAD_ONLINE_RATE t "
							+ " where db_time>=str_to_date('"
							+ st
							+ "','%Y-%m-%d %H:%i:%s') and "
							+ " db_time<=str_to_date('"
							+ et
							+ "','%Y-%m-%d %H:%i:%s') and (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00') "
							+ ") t" + " group by s order by s asc";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String zcl24(
				@Param("time")  String time,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select load_rate,date_format(db_time,'%H') s from "
						+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ " and ba_id = '"
						+ baid
						+ "') c where (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00')  "
						+ "   order by date_format(db_time,'%H%i')";
			}else {
				sql = "select rpad(round(avg(replace(load_rate,'%',''))),length(round(avg(replace(load_rate,'%',''))))+1,'%') "
						+ "load_rate,date_format(db_time,'%H%i') s from "
						+ "(select * from TB_TAXI_LOAD_ONLINE_RATE t where "
						+ " db_time>=str_to_date('"
						+ time
						+ " 00:00:00','%Y-%m-%d %H:%i:%s')"
						+ " and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ ") c where (date_format(db_time,'%i')='30' or date_format(db_time,'%i')='00')  "
						+ "GROUP BY s "
						+ "   order by date_format(db_time,'%H%i')";
			}
			 
			System.out.println(sql);
			return sql;
		}
		
		public String zcl48(
				@Param("time2")  String time2,
				@Param("time")  String time,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select  load_rate,date_format(db_time,'%d%H')s from "
						+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
						+ "db_time>=str_to_date('"
						+ time2
						+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
						+ "and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ "and ( date_format(db_time,'%i')='30'or date_format(db_time,'%i')='00') and ba_id = '"
						+ baid + "' order by db_time ";
			}else {
				sql = "select rpad(round(avg(replace(load_rate,'%',''))),length(round(avg(replace(load_rate,'%',''))))+1,'%') load_rate,date_format(db_time,'%d%H%i')s from "
						+ "  TB_TAXI_LOAD_ONLINE_RATE t where "
						+ "db_time>=str_to_date('"
						+ time2
						+ " 00:00:00','%Y-%m-%d %H:%i:%s') "
						+ "and db_time<=str_to_date('"
						+ time
						+ " 23:59:59','%Y-%m-%d %H:%i:%s') "
						+ "and ( date_format(db_time,'%i')='30'or date_format(db_time,'%i')='00') "
						+ "GROUP BY s "
						+ " order by db_time ";
			}
			System.out.println(sql);
			return sql;
		}
		
		public String findzclmaxmin(
				@Param("zuotian")  String zuotian,
				@Param("baid")  String baid){
			String sql = "";
			if (baid != null && baid.length() != 0 && !baid.equals("0")) {
				sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
						+ " enddate=str_to_date('" + zuotian
						+ "','%Y-%m-%d') and ba_id='" + baid + "' ";
			}else {
				sql = "select * from TB_AREA_HALF_MONTH_ONLINE_RATE t where"
						+ " enddate=str_to_date('" + zuotian
						+ "','%Y-%m-%d') ";
			}
			System.out.println(sql);
			return sql;
		}
	}
}
