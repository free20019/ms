package com.erxi.ms.dao;


import com.erxi.ms.annotation.SystemControllerLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Mapper
public interface YqcxDao {

	/**
	 * 下拉栏
	 * @param table
	 * @return
	 */
	@Select("select distinct ${field} from ${table} ")
	public List<Map<String, Object>> findxll(
			@Param("field") String field, @Param("table") String table);

	/**
	 * 乘坐信息查询
	 */
	@SelectProvider(type = yqcx.class, method = "findczxxcx")
	public List<Map<String, Object>> findczxxcx(
            @Param("stime") String stime,
            @Param("etime") String etime,
            @Param("vehicle") String vehicle,
            @Param("company") String company,
            @Param("phone") String phone,
            @Param("pageIndex") Integer pageIndex,
            @Param("pageSize") Integer pageSize);
	/**
	 * 乘坐信息查询导出
	 */
	@SelectProvider(type = yqcx.class, method = "findczxxcxdc")
	public List<Map<String, Object>> findczxxcxdc(
            @Param("stime") String stime,
            @Param("etime") String etime,
            @Param("vehicle") String vehicle,
            @Param("company") String company,
            @Param("phone") String phone);

	/**
	 * 登记数据分析
	 */
	@SelectProvider(type = yqcx.class, method = "finddjsjfx")
	public List<Map<String, Object>> finddjsjfx(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("company") String company,
			@Param("value") String value,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);
	/**
	 * 登记数据分析导出
	 */
	@SelectProvider(type = yqcx.class, method = "finddjsjfxdc")
	public List<Map<String, Object>> finddjsjfxdc(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("company") String company,
			@Param("value") String value);

	/**
	 * 乘客确诊溯源1
	 */
	@SelectProvider(type = yqcx.class, method = "findckqzsy1")
	public List<Map<String, Object>> findckqzsy1(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("phone") String phone);
	/**
	 * 乘客确诊溯源2
	 */
	@SelectProvider(type = yqcx.class, method = "findckqzsy2")
	public List<Map<String, Object>> findckqzsy2(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("phone") String phone);


	/**
	 * 司机确诊溯源1
	 */
	@SelectProvider(type = yqcx.class, method = "findsjqzsy1")
	public List<Map<String, Object>> findsjqzsy1(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("phone") String phone);
	/**
	 * 司机确诊溯源2
	 */
	@SelectProvider(type = yqcx.class, method = "findsjqzsy2")
	public List<Map<String, Object>> findsjqzsy2(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("phone") String phone);

	/**
	 * 登记异常分析
	 */
	@SelectProvider(type = yqcx.class, method = "finddjycfx")
	public List<Map<String, Object>> finddjycfx(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("value") String value,
			@Param("company") String company,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);
	/**
	 * 登记异常分析导出
	 */
	@SelectProvider(type = yqcx.class, method = "finddjycfxdc")
	public List<Map<String, Object>> finddjycfxdc(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("vehicle") String vehicle,
			@Param("value") String value,
			@Param("company") String company);

	/**
	 * 信息总览
	 */
//	@Select("select * from (select count(*) xzckdj from TB_TAXI_SMDJ a ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g  where a.CPHM=g.plate_number and date_format(a.djsj,'%Y-%m-%d')=#{time}) a" +
//			" left join (select count(*) ljckdj from TB_TAXI_SMDJ a,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g  where a.CPHM=g.plate_number) b on 1=1")
	@Select("select * from (select count(*) xzckdj from TB_TAXI_SMDJ a where date_format(a.djsj,'%Y-%m-%d')=#{time}) a" +
			" left join (select count(*) ljckdj from TB_TAXI_SMDJ a) b on 1=1" +
			" left join (select count(*) zrxzckdj from TB_TAXI_SMDJ a where date_format(DATE_ADD(a.djsj,INTERVAL 1 DAY),'%Y-%m-%d')=#{time}) c on 1=1")
	public List<LinkedHashMap<String, Object>> findTodayInformation(@Param("time") String time);

	/**
	 * 操作日志
	 */
	@SelectProvider(type = yqcx.class, method = "findczrz")
    List<Map<String, Object>> findczrz(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("value") String value,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize
	);

	/**
	 * 操作日志导出
	 */
	@SelectProvider(type = yqcx.class, method = "findczrzdc")
	List<Map<String, Object>> findczrzdc(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("value") String value);

	class yqcx{
		public String findczxxcx(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("company")String company,
				@Param("phone")String phone,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.CKDH like '%"+phone+"%'";
			}
			String sql = "select (select count(CPHM) from  TB_TAXI_SMDJ a " +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql += ") as COUNT,t.* " +
					" from (select a.*,g.COMPANY_NAME from TB_TAXI_SMDJ a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql +=" ) t order by t.DJSJ desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
		}
		public String findczxxcxdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("company")String company,
				@Param("phone")String phone){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.CKDH like '%"+phone+"%'";
			}
			String sql = "select (select count(CPHM) from  TB_TAXI_SMDJ a " +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql += ") as COUNT,t.* " +
					" from (select a.*,g.COMPANY_NAME from TB_TAXI_SMDJ a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql +=" ) t order by t.DJSJ desc ";
			System.out.println("sqldc="+sql);
			return sql;
		}

		public String finddjsjfx(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("company")String company,
				@Param("value")String value,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			String tj2="";
			String tj3="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and b.SHANGCHE >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and b.SHANGCHE <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
				tj2 += " and CONCAT('浙',b.vhic)= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj3 += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("类型")){
				tj3 += " and ABS(a.DJCS-ifnull(b.YYJLS,0)) >= '"+value+"'";
			}
			String sql = "select (select count(*) from (select count(CPHM) DJCS,CPHM from TB_TAXI_SMDJ a where 1=1";
			sql += tj;
			sql +=	" group by a.cphm) a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
					" left join (select count(vhic) YYJLS,vhic from jjq"+etime.replaceAll("-","").substring(0,6)+"_1 b where 1=1";
			sql += tj2;
			sql +=	" group by vhic) b on CONCAT('浙',b.vhic)=a.cphm" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj3;
			sql += ") as COUNT,t.* " +
					" from (select a.*,ifnull(b.YYJLS,0) YYJLS,ABS(a.DJCS-ifnull(b.YYJLS,0)) CZ,g.COMPANY_NAME from (select count(CPHM) DJCS,CPHM from TB_TAXI_SMDJ a where 1=1";
			sql += tj;
			sql +=	" group by a.cphm) a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
					" left join (select count(vhic) YYJLS,vhic from jjq"+etime.replaceAll("-","").substring(0,6)+"_1 b where 1=1";
			sql += tj2;
			sql +=	" group by vhic) b on CONCAT('浙',b.vhic)=a.cphm" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj3;
			sql +=" ) t order by t.CZ desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
		}
		public String finddjsjfxdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("company")String company,
				@Param("value")String value){
			String tj="";
			String tj2="";
			String tj3="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and b.SHANGCHE >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and b.SHANGCHE <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
				tj2 += " and CONCAT('浙',b.vhic)= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj3 += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("类型")){
				tj3 += " and ABS(a.DJCS-ifnull(b.YYJLS,0)) >= '"+value+"'";
			}
			String sql = "select (select count(*) from (select count(CPHM) DJCS,CPHM from TB_TAXI_SMDJ a where 1=1";
			sql += tj;
			sql +=	" group by a.cphm) a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
					" left join (select count(vhic) YYJLS,vhic from jjq"+etime.replaceAll("-","").substring(0,6)+"_1 b where 1=1";
			sql += tj2;
			sql +=	" group by vhic) b on CONCAT('浙',b.vhic)=a.cphm" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj3;
			sql += ") as COUNT,t.* " +
					" from (select a.*,ifnull(b.YYJLS,0) YYJLS,ABS(a.DJCS-ifnull(b.YYJLS,0)) CZ,g.COMPANY_NAME from (select count(CPHM) DJCS,CPHM from TB_TAXI_SMDJ a where 1=1";
			sql += tj;
			sql +=	" group by a.cphm) a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
					" left join (select count(vhic) YYJLS,vhic from jjq"+etime.replaceAll("-","").substring(0,6)+"_1 b where 1=1";
			sql += tj2;
			sql +=	" group by vhic) b on CONCAT('浙',b.vhic)=a.cphm" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj3;
			sql +=" ) t order by t.CZ desc";
			System.out.println("sql="+sql);
			return sql;
		}

		public String findckqzsy1(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("phone")String phone){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.ckdh = '"+phone+"'";
			}
			String sql = "select a.*,g.COMPANY_NAME from TB_TAXI_SMDJ a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql +=" order by a.DJSJ desc ";
			System.out.println("sql="+sql);
			return sql;
		}

		public String findckqzsy2(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("phone")String phone){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.ckdh = '"+phone+"'";
				tj2 += " and a.ckdh != '"+phone+"'";
			}
			String sql = "select a.*,g.COMPANY_NAME from (select a.* from (select distinct cphm from TB_TAXI_SMDJ a where 1=1 ";
			sql += tj;
			sql +=	") b,TB_TAXI_SMDJ a where a.cphm=b.cphm";
			sql += tj2;
			sql +=") a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql +=" order by a.DJSJ desc ";
			System.out.println("sql="+sql);
			return sql;
		}

		public String findsjqzsy1(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("phone")String phone){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.SJDH = '"+phone+"'";
			}
			String sql = "select a.*,g.COMPANY_NAME from TB_TAXI_SMDJ a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number"+
//					" where  g.plate_number is not null";
					" where 1=1";
			sql += tj;
			sql +=" order by a.DJSJ desc ";
			System.out.println("sql="+sql);
			return sql;
		}

		public String findsjqzsy2(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("phone")String phone){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and a.DJSJ >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				tj2 += " and a.DJSJ <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.CPHM= '"+vehicle+"'";
			}
			if(phone!=null&&!phone.isEmpty()&&!phone.equals("null")&&phone.length()>0&&!phone.equals("类型")){
				tj += " and a.SJDH = '"+phone+"'";
			}
			String sql = "select distinct a.*,g.COMPANY_NAME from (select a.* from (select ckdh,cphm from TB_TAXI_SMDJ a where 1=1 ";
			sql += tj;
			sql +=	") b,TB_TAXI_SMDJ a where a.ckdh=b.ckdh and a.cphm!=b.cphm";
			sql += tj2;
			sql +=") a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.CPHM=g.plate_number" +
//					" where  g.plate_number is not null";
					" where 1=1";
			sql +=" order by a.DJSJ desc ";
			System.out.println("sql="+sql);
			return sql;
		}

		public String finddjycfx(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("value")String value,
				@Param("company")String company,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DB_TIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DB_TIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("类型")){
				tj += " and a."+value+"=1";
			}else{
				tj += " and (a.JJQ_NDJ=1 or a.GPS_NYY_NDJ=1 or a.NGPS_NJJQ_NDJ_ZP=1)";
			}
			String sql = "select (select count(vehicle_no) from  tb_taxi_gzfx_yq a " +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.vehicle_no=g.plate_number"+
					" where  g.plate_number is not null";
			sql += tj;
			sql += ") as COUNT,t.* " +
					" from (select a.*,g.COMPANY_NAME from tb_taxi_gzfx_yq a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.vehicle_no=g.plate_number"+
					" where  g.plate_number is not null";
			sql += tj;
			sql +=" ) t order by t.DB_TIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
		}
		public String finddjycfxdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("value")String value,
				@Param("company")String company){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DB_TIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DB_TIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and g.COMPANY_NAME= '"+company+"'";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("类型")){
				tj += " and a."+value+"=1";
			}else {
				tj += " and (a.JJQ_NDJ=1 or a.GPS_NYY_NDJ=1 or a.NGPS_NJJQ_NDJ_ZP=1)";
			}
			String sql = "select t.* from (select a.*,g.COMPANY_NAME from tb_taxi_gzfx_yq a" +
					" left join (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on a.vehicle_no=g.plate_number"+
					" where g.plate_number is not null";
			sql += tj;
			sql +=" ) t order by t.DB_TIME desc ";
			System.out.println("sql="+sql);
			return sql;
		}

		public String findczrz(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("value")String value,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("")){
				tj += " and a.ACTIONTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("")){
				tj += " and a.ACTIONTIME <=str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("")){
				tj += " and (a.ACTIONDES like '%"+value+"%' or a.USERNAME like '%"+value+"%')";
			}
			String sql = "select (select count(*) from  tb_action_record a " +
					" where  1=1";
			sql += tj;
			sql += ") as COUNT,t.* " +
					" from (select a.* from tb_action_record a" +
					" where 1=1";
			sql += tj;
			sql +=" ) t order by t.ACTIONTIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
		}
		public String findczrzdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("value")String value){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("")){
				tj += " and a.ACTIONTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("")){
				tj += " and a.ACTIONTIME <=str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
			}
			if(value!=null&&!value.isEmpty()&&!value.equals("null")&&value.length()>0&&!value.equals("")){
				tj += " and (a.ACTIONDES like '%"+value+"%' or a.USERNAME like '%"+value+"%')";
			}
			String sql = "select a.* from tb_action_record a" +
					" where 1=1";
			sql += tj;
			sql +=" order by a.ACTIONTIME desc";
			System.out.println("sql="+sql);
			return sql;
		}
	}
}
