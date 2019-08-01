package com.erxi.ms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.RequestParam;

import com.erxi.ms.dao.ZpsjDao.zpsj;

import java.util.List;
import java.util.Map;

/**
 * 在线支付、专车数据接入、出租汽车信息共享与报送系统
 * @author xianlehuang
 * @date 2018/12/20 
 */

@Mapper
public interface JyxxDao {
	/**
	 * 下拉栏
	 * @param table
	 * @param field
	 * @return
	 */
	@Select("select distinct ${field} from ${table} where ${field} <> '0'")
	public List<Map<String, Object>> findxll(
			@Param("table")String table,
			@Param("field")String field);
	/**
	 * 下拉栏
	 * @param table
	 * @param field
	 * @return
	 */
	@Select("select distinct ${field} from ${table} where ${field} <> '0' and COMPNAME ='${type}'")
	public List<Map<String, Object>> findxllcl(
			@Param("table")String table,
			@Param("field")String field,
			@Param("type")String type);
	/**
	 * 交易信息总览
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findnowmonthall")
	public List<Map<String, Object>> findnowmonthall(
			@Param("stime")String stime,
			@Param("etime")String etime);
	/**
	 * 交易
	 * @param order
	 * @param stime
	 * @param etime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findwxjy")
	public List<Map<String, Object>> findwxjy(
			@Param("order")String order, 
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 交易导出
	 * @param order
	 * @param stime
	 * @param etime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findwxjydc")
	public List<Map<String, Object>> findwxjydc(
			@Param("order")String order, 
			@Param("stime")String stime,
			@Param("etime")String etime);
	/**
	 * 专车数据总览
	 * @return
	 */
	@Select("SELECT count(DISTINCT PLATENUMBER) VEHICLE,count(distinct NAME) NAME,COMPNAME"
			+ "	FROM tb_vehicle_add where PLATENUMBER <> '' and NAME <> '' GROUP BY COMPNAME")
	public List<Map<String, Object>> findzcyytj();
	
	/**
	 * 专车数据
	 * @param vehicle
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findzcsj")
	public List<Map<String, Object>> findzcsj(
			@Param("vehicle")String vehicle, 
			@Param("type")String type,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 专车数据导出
	 * @param vehicle
	 * @param type
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findzcsjdc")
	public List<Map<String, Object>> findzcsjdc(
			@Param("vehicle")String vehicle, 
			@Param("type")String type);
	
	/**
	 * 信息报送总览
	 * @return
	 */
	@Select("select sum(average_count*60) COUNT,date_format(db_time,'%Y-%m-%d')"
			+ " DB_TIME from TB_ST_BB group by"
			+ " date_format(db_time,'%Y-%m-%d') order by db_time desc")
	public List<Map<String, Object>> findxxbs();
	
	@Select("select * from TB_ST_BB  where db_time>=str_to_date('${stime}','%Y-%m-%d %H:%i:%s')"
			+ " and db_time<str_to_date('${etime}','%Y-%m-%d %H:%i:%s')")
	public List<Map<String, Object>> findxxbsnow(
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	/**
	 * 信息报送（分页）
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = Jyxx.class, method = "findxxbsfy")
	public List<Map<String, Object>> findxxbsfy(
			@Param("stime") String stime,
			@Param("etime") String etime,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 信息报送导出
	 * @return
	 */
	@Select("select sum(average_count*60) COUNT,date_format(db_time,'%Y-%m-%d')"
			+ " DB_TIME from TB_ST_BB where db_time>=str_to_date('${stime} 00:00:00','%Y-%m-%d %H:%i:%s') and db_time<=str_to_date('${etime} 23:59:59','%Y-%m-%d %H:%i:%s') group by"
			+ " date_format(db_time,'%Y-%m-%d') order by db_time desc")
	public List<Map<String, Object>> findxxbsdc(
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	class Jyxx{
		public String findnowmonthall(
				@Param("stime")String stime,
				@Param("etime")String etime){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and gmt_payment >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and gmt_payment <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			String sql="select round(COALESCE(SUM(TOTAL_FEE),0),0) COUNT,count(*) C from TB_ORDER_RECODER where 1=1";
			sql +=tj;
			System.out.println(sql);
			return sql;
		}
		public String findwxjy(
				@Param("order")String order, 
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(order!=null&&!order.isEmpty()&&!order.equals("null")&&order.length()>0&&!order.equals("订单")){
				tj += " and subject like '%"+order+"%'";
			}
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and gmt_payment >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and gmt_payment <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			String sql="select (select count(*) from TB_ORDER_RECODER  where 1=1";
			sql +=tj;
			sql +=" ) as COUNT,t.* from (select SUBJECT,date_format(GMT_CREATE,'%Y-%m-%d %H:%i:%s') GMT_CREATE,date_format(GMT_PAYMENT,'%Y-%m-%d %H:%i:%s')  GMT_PAYMENT, SELLER_EMAIL, BUYER_EMAIL, PRICE,QUANTITY,TOTAL_FEE from TB_ORDER_RECODER where 1=1";
			sql +=tj;
			sql +=" ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		public String findwxjydc(
				@Param("order")String order, 
				@Param("stime")String stime,
				@Param("etime")String etime){
			String tj="";
			if(order!=null&&!order.isEmpty()&&!order.equals("null")&&order.length()>0&&!order.equals("订单")){
				tj += " and subject like '%"+order+"%'";
			}
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and gmt_payment >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and gmt_payment <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			String sql="select t.* from (select SUBJECT,date_format(GMT_CREATE,'%Y-%m-%d %H:%i:%s') GMT_CREATE,date_format(GMT_PAYMENT,'%Y-%m-%d %H:%i:%s')  GMT_PAYMENT, SELLER_EMAIL, BUYER_EMAIL, PRICE,QUANTITY,TOTAL_FEE from TB_ORDER_RECODER where 1=1";
			sql +=tj;
			sql +=" ) t";
			System.out.println(sql);
			return sql;
		}
		
		public String findzcsj(
				@Param("vehicle")String vehicle, 
				@Param("type")String type,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号")){
				tj += " and PLATENUMBER = '"+vehicle+"'";
			}
			tj += " and COMPNAME = '"+type+"'";
			String sql="select (select count(*) from tb_vehicle_add  where 1=1";
			sql +=tj;
			sql +=" ) as COUNT,t.* from tb_vehicle_add t where 1=1";
			sql +=tj;
			sql +="  limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		public String findzcsjdc(
				@Param("vehicle")String vehicle, 
				@Param("type")String type){
			String tj="";
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号")){
				tj += " and PLATENUMBER = '"+vehicle+"'";
			}
			tj += " and COMPNAME = '"+type+"'";
			String sql="select * from tb_vehicle_add where 1=1";
			sql +=tj;
			System.out.println(sql);
			return sql;
		}
		public String findxxbsfy(
				@Param("stime") String stime,
				@Param("etime") String etime,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and db_time >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and db_time <=str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
			}
			String sql="select (select count(DISTINCT date_format(db_time, '%Y-%m-%d')) from TB_ST_BB where 1=1";
			sql +=tj;
			sql += " ) as COUNT,sum(average_count*60) COUNT2,date_format(db_time,'%Y-%m-%d') DB_TIME from TB_ST_BB where 1=1";
			sql +=tj;
			sql += " group by date_format(db_time,'%Y-%m-%d') order by db_time desc"
				+ " limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
	}	
}