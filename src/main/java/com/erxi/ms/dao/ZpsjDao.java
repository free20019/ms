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


@Mapper
public interface ZpsjDao {
	
	/**
	 * 下拉栏
	 * @param table
	 * @return
	 */
	@Select("select distinct ${field} from ${table} where ${field} <> '0'")
	public List<Map<String, Object>> findxll(
			@Param("field")String field,@Param("table")String table);
	/**
	 * 抓拍数据查询
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findzpsjcx")
	public List<Map<String, Object>> findzpsjcx(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("address")String address,
			@Param("company")String company,
			@Param("type")String type,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 抓拍数据导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findzpsjcxdc")
	public List<Map<String, Object>> findzpsjcxdc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("address")String address,
			@Param("company")String company,
			@Param("type")String type,
			@Param("check") String check);
	/**
	 * 场站流量统计
	 * @param stime
	 * @param etime
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findczlltj")
	public List<Map<String, Object>> findczlltj(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 场站流量统计导出
	 * @param stime
	 * @param etime
	 * @param address
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findczlltjdc")
	public List<Map<String, Object>> findczlltjdc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("address")String address,
			@Param("check") String check);
	/**
	 * 日流量统计
	 * @param stime
	 * @param etime
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findrlltj")
	public List<Map<String, Object>> findrlltj(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 日流量统计导出
	 * @param stime
	 * @param etime
	 * @param address
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findrlltjdc")
	public List<Map<String, Object>> findrlltjdc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("address")String address,
			@Param("check") String check);

	/**
	 * 分段流量统计
	 * @param time
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findfdlltj")
	public List<Map<String, Object>> findfdlltj(
			@Param("time")String time,
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 分段流量统计导出
	 * @param time
	 * @param address
	 * @return
	 */
	@SelectProvider(type = zpsj.class, method = "findfdlltjdc")
	public List<Map<String, Object>> findfdlltjdc(
			@Param("time")String time,
			@Param("address")String address,
			@Param("check") String check);


	/**
	 * 卡口抓拍数据查询
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkzpsjcx")
	public List<Map<String, Object>> findkkzpsjcx(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("vehicle")String vehicle,
			@Param("address")String address,
			@Param("company")String company,
			@Param("type")String type,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 卡口抓拍数据导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param address
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkzpsjcxdc")
	public List<Map<String, Object>> findkkzpsjcxdc(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("vehicle")String vehicle,
			@Param("address")String address,
			@Param("company")String company,
			@Param("type")String type,
			@Param("check") String check);
	/**
	 * 卡口场站流量统计
	 * @param stime
	 * @param etime
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkczlltj")
	public List<Map<String, Object>> findkkczlltj(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 卡口场站流量统计导出
	 * @param stime
	 * @param etime
	 * @param address
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkczlltjdc")
	public List<Map<String, Object>> findkkczlltjdc(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("address")String address,
			@Param("check") String check);
	/**
	 * 卡口日流量统计
	 * @param stime
	 * @param etime
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkrlltj")
	public List<Map<String, Object>> findkkrlltj(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 卡口日流量统计导出
	 * @param stime
	 * @param etime
	 * @param address
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkrlltjdc")
	public List<Map<String, Object>> findkkrlltjdc(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("address")String address,
			@Param("check") String check);

	/**
	 * 卡口分段流量统计
	 * @param time
	 * @param address
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkfdlltj")
	public List<Map<String, Object>> findkkfdlltj(
			@Param("time")String time,
			@Param("address")String address,
			@Param("check") String check,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 卡口分段流量统计导出
	 * @param time
	 * @param address
	 * @return
	 */
	@SelectProvider(type = kkzpsj.class, method = "findkkfdlltjdc")
	public List<Map<String, Object>> findkkfdlltjdc(
			@Param("time")String time,
			@Param("address")String address,
			@Param("check") String check);
	class zpsj{
		public String findzpsjcx(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("address")String address,
				@Param("company")String company,
				@Param("type")String type,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			String[] time = type.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(type.equals("1")){				
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";				
				}
			}else if(type.equals("null")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";				
				}
			}else{
				
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					Calendar calendar = Calendar.getInstance();
					try {
						calendar.setTime(sdf.parse(etime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					calendar.add(Calendar.DATE, +1);
					Date date = calendar.getTime();
					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";				
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			 if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
               if(company.equals("公司为空")){
                   tj += " and b.ZGS is null";
               }else if(company.equals("公司不为空")){
                   tj += " and b.ZGS is not null";
               }else{
                   tj += " and b.ZGS = '"+company+"'";
               }
           }
           String checked="";
           if(check.equals("0")){
               checked=" GROUP BY DBTIME,VEHICLE_NO,ZGS,ADDRESS ";
           }else {
               checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ZGS,ADDRESS ";
           }
			String sql = "select (select count(1) from (select * from tb_vehicle_hk a"
					+ "  left join jjq_company b on a.VEHICLE_NO=b.CPHM ";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;				
			sql += checked;
			sql += ") m) as COUNT, a.*,b.ZGS COMPANY_NAME from  tb_vehicle_hk a"
					+ " left join jjq_company b on a.VEHICLE_NO=b.CPHM";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;				
			sql += checked;
			sql +=" order by DBTIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
			
			
//			String tj="";
//			String[] time = type.split("-");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			if(type.equals("1")){
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
//				}
//			}else if(type.equals("null")){
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
//				}
//			}else{
//
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					Calendar calendar = Calendar.getInstance();
//					try {
//						calendar.setTime(sdf.parse(etime));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					calendar.add(Calendar.DATE, +1);
//					Date date = calendar.getTime();
//					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";
//				}
//			}
//			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
//				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
//				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
//			}
//			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
//				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
//			}
//			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
//				tj += " and a.ADDRESS in ("+address+")";
//			}
//            if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
//                if(company.equals("公司为空")){
//                    tj += " and b.COMPANY_NAME is null";
//                }else if(company.equals("公司不为空")){
//                    tj += " and b.COMPANY_NAME is not null";
//                }else{
//                    tj += " and b.COMPANY_NAME = '"+company+"'";
//                }
//            }
//            String checked="";
//            if(check.equals("0")){
//                checked=" GROUP BY DBTIME,VEHICLE_NO,COMPANY_NAME,ADDRESS ";
//            }else {
//                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,COMPANY_NAME,ADDRESS ";
//            }
//			String sql = "select (select count(1) from (select * from tb_vehicle_hk a"
//					+ "  left join TB_GLOBAL_VEHICLE b on a.VEHICLE_NO=b.plate_number ";
//			sql += " where a.ADDRESS <> '0'";
//			sql += tj;
//			sql += checked;
//			sql += ") m) as COUNT, a.*,b.COMPANY_NAME COMPANY_NAME from  tb_vehicle_hk a"
//					+ " left join TB_GLOBAL_VEHICLE b on a.VEHICLE_NO=b.plate_number";
//			sql += " where a.ADDRESS <> '0'";
//			sql += tj;
//			sql += checked;
//			sql +=" order by DBTIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
//			System.out.println("sql="+sql);
//			return sql;
		}
		public String findzpsjcxdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("address")String address,
				@Param("company")String company,
				@Param("type")String type,
				@Param("check") String check){
			String tj="";
			String[] time = type.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(type.equals("1")){				
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";				
				}
			}else if(type.equals("null")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";				
				}
			}else{
				
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					Calendar calendar = Calendar.getInstance();
					try {
						calendar.setTime(sdf.parse(etime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					calendar.add(Calendar.DATE, +1);
					Date date = calendar.getTime();
					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";				
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			 if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
               if(company.equals("公司为空")){
                   tj += " and b.ZGS is null";
               }else if(company.equals("公司不为空")){
                   tj += " and b.ZGS is not null";
               }else{
                   tj += " and b.ZGS = '"+company+"'";
               }
           }
           String checked="";
           if(check.equals("0")){
               checked=" GROUP BY DBTIME,VEHICLE_NO,ZGS,ADDRESS ";
           }else {
               checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ZGS,ADDRESS ";
           }
			String sql = "select a.*,b.ZGS COMPANY_NAME from  tb_vehicle_hk a"
					+ " left join jjq_company b on a.VEHICLE_NO=b.CPHM";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;				
			sql += checked;
			sql +=" order by DBTIME desc";
			System.out.println("sql="+sql);
			return sql;
			
			
//			String tj="";
//			String[] time = type.split("-");
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			if(type.equals("1")){
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
//				}
//			}else if(type.equals("null")){
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
//				}
//			}else{
//
//				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
//					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
//				}
//				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
//					Calendar calendar = Calendar.getInstance();
//					try {
//						calendar.setTime(sdf.parse(etime));
//					} catch (ParseException e) {
//						e.printStackTrace();
//					}
//					calendar.add(Calendar.DATE, +1);
//					Date date = calendar.getTime();
//					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";
//				}
//			}
//			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
//				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
//				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
//			}
//			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
//				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
//			}
//			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
//				tj += " and a.ADDRESS in ("+address+")";
//			}
//			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
//                if(company.equals("公司为空")){
//                    tj += " and b.COMPANY_NAME is null";
//                }else if(company.equals("公司不为空")){
//                    tj += " and b.COMPANY_NAME is not null";
//                }else{
//                    tj += " and b.COMPANY_NAME = '"+company+"'";
//                }
//            }
//            String checked="";
//            if(check.equals("0")){
//                checked=" GROUP BY DBTIME,VEHICLE_NO,COMPANY_NAME,ADDRESS ";
//            }else {
//                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,COMPANY_NAME,ADDRESS ";
//            }
//			String sql = "select a.*,b.COMPANY_NAME COMPANY_NAME from  tb_vehicle_hk a"
//					+ " left join TB_GLOBAL_VEHICLE b on a.VEHICLE_NO=b.plate_number";
//			sql += " where a.ADDRESS <> '0'";
//			sql += tj;
//			sql += checked;
//			sql +=" order by DBTIME desc ";
//			System.out.println("sql="+sql);
//			return sql;
		}
		public String findczlltj(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DBTIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			String sql = "select (select count(distinct ADDRESS) from tb_vehicle_hk  a where ADDRESS <> '0' ";
			sql += tj;		
			sql += ") as COUNT, a.ADDRESS ADDRESS,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk a where ADDRESS <> '0'";
			sql += tj;		
			sql += checked;
			sql +=") a group by a.ADDRESS limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findczlltjdc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DBTIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			String sql = "select  a.ADDRESS ADDRESS,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk a where ADDRESS <> '0'";
			sql += tj;		
			sql += checked;
			sql +=") a  group by a.ADDRESS ";
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findrlltj(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') >='"+stime+"'";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') <='"+etime+"'";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			String sql = "select (select count(distinct date_format(DBTIME,'%Y-%m-%d')) from  tb_vehicle_hk  a where ADDRESS <> '0' ";
			sql += tj;	
			sql += ") as COUNT, date_format(DBTIME,'%Y-%m-%d') SJ,count(a.VEHICLE_NO) SJL from  "
					+ " (select * from  tb_vehicle_hk a where ADDRESS <> '0'";
			sql += tj;		
			sql += checked;
			sql +=" ) a group by date_format(DBTIME,'%Y-%m-%d') limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findrlltjdc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') >='"+stime+"'";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') <='"+etime+"'";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			String sql = "select  date_format(DBTIME,'%Y-%m-%d') SJ,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk a where ADDRESS <> '0'";
			sql += tj;					
			sql += checked;
			sql +=") a  group by date_format(DBTIME,'%Y-%m-%d') ";
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findfdlltj(
				@Param("time")String time,
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(time!=null&&!time.isEmpty()&&!time.equals("null")&&time.length()>0&&!time.equals("开始时间")){
				tj += " and date_format(DBTIME,'%Y-%m-%d') ='"+time+"'";
				date=time.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			String sql = "select (select count(distinct ADDRESS) from  tb_vehicle_hk  where ADDRESS <> '0' ";
			sql += tj;					
			sql += ") as COUNT,a.ADDRESS ADDRESS";
			for(int i=0;i<12;i++){				
				sql +=",m"+i+".c"+i+"";
			}	
			sql +=  " from (select distinct ADDRESS from tb_vehicle_hk where ADDRESS <> '0'  ";
			sql += tj;			
			sql +=  ") a";

			for(int i=0;i<12;i++){				
				sql += " left join (select ADDRESS,count(VEHICLE_NO) as c"+i+" from"
						+ " (select * from  tb_vehicle_hk  where ADDRESS <> '0' and floor(date_format(DBTIME,'%H')/2)='"+i+"'";
				sql += tj;				
				sql += checked;
				sql += ") a";
				sql +=" group by ADDRESS) m"+i+" on m"+i+".ADDRESS=a.ADDRESS";
			}
			sql += " limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tj123sql="+sql);
			return sql;
		}
		public String findfdlltjdc(
				@Param("time")String time,
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(time!=null&&!time.isEmpty()&&!time.equals("null")&&time.length()>0&&!time.equals("开始时间")){
				tj += " and date_format(DBTIME,'%Y-%m-%d') ='"+time+"'";
				date=time.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and ADDRESS in ("+address+")";
			}
			String sql = "select a.ADDRESS ADDRESS";
			for(int i=0;i<12;i++){				
				sql +=",m"+i+".c"+i+"";
			}	
			String checked="";
			if(check.equals("0")){
                checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
            }else {
                checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
            }
			sql +=  " from (select distinct ADDRESS from tb_vehicle_hk where ADDRESS <> '0' ";
			sql += tj;			
			sql +=  ") a";

			for(int i=0;i<12;i++){				
				sql += " left join (select ADDRESS,count(VEHICLE_NO) as c"+i+" from  "
						+ " (select * from  tb_vehicle_hk  where ADDRESS <> '0' and floor(date_format(DBTIME,'%H')/2)='"+i+"'";
				sql += tj;			
				sql += checked;
				sql += ") a";
				sql +=" group by ADDRESS) m"+i+" on m"+i+".ADDRESS=a.ADDRESS";
			}
			return sql;
		}
	}

	class kkzpsj{
		public String findkkzpsjcx(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("address")String address,
				@Param("company")String company,
				@Param("type")String type,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			String[] time = type.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(type.equals("1")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				}
			}else if(type.equals("null")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
				}
			}else{

				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					Calendar calendar = Calendar.getInstance();
					try {
						calendar.setTime(sdf.parse(etime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					calendar.add(Calendar.DATE, +1);
					Date date = calendar.getTime();
					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				if(company.equals("公司为空")){
					tj += " and b.ZGS is null";
				}else if(company.equals("公司不为空")){
					tj += " and b.ZGS is not null";
				}else{
					tj += " and b.ZGS = '"+company+"'";
				}
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ZGS,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ZGS,ADDRESS ";
			}
			String sql = "select (select count(1) from (select * from tb_vehicle_hk2 a"
					+ "  left join jjq_company b on a.VEHICLE_NO=b.CPHM ";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql += ") m) as COUNT, a.*,b.ZGS COMPANY_NAME from  tb_vehicle_hk2 a"
					+ " left join jjq_company b on a.VEHICLE_NO=b.CPHM";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=" order by DBTIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;
		}
		public String findkkzpsjcxdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("vehicle")String vehicle,
				@Param("address")String address,
				@Param("company")String company,
				@Param("type")String type,
				@Param("check") String check){
			String tj="";
			String[] time = type.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(type.equals("1")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				}
			}else if(type.equals("null")){
				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					tj += " and a.DBTIME <str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
				}
			}else{

				if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
					tj += " and a.DBTIME >=str_to_date('"+stime+" "+time[0]+"','%Y-%m-%d %H:%i:%s')";
				}
				if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
					Calendar calendar = Calendar.getInstance();
					try {
						calendar.setTime(sdf.parse(etime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					calendar.add(Calendar.DATE, +1);
					Date date = calendar.getTime();
					tj += " and a.DBTIME <str_to_date('"+sdf.format(date)+" "+time[1]+"','%Y-%m-%d %H:%i:%s')";
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&!type.equals("1")&&type.length()>0){
				tj += " and (DATE_FORMAT(a.DBTIME,'%H:%i:%s') >='"+time[0]+"'";
				tj += " or DATE_FORMAT(a.DBTIME,'%H:%i:%s') <='"+time[1]+"')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.VEHICLE_NO like '%"+vehicle+"%'";
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				if(company.equals("公司为空")){
					tj += " and b.ZGS is null";
				}else if(company.equals("公司不为空")){
					tj += " and b.ZGS is not null";
				}else{
					tj += " and b.ZGS = '"+company+"'";
				}
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ZGS,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ZGS,ADDRESS ";
			}
			String sql = "select a.*,b.ZGS COMPANY_NAME from  tb_vehicle_hk2 a"
					+ " left join jjq_company b on a.VEHICLE_NO=b.CPHM";
			sql += " where a.ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=" order by DBTIME desc";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findkkczlltj(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DBTIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			String sql = "select (select count(distinct ADDRESS) from tb_vehicle_hk2  a where ADDRESS <> '0' ";
			sql += tj;
			sql += ") as COUNT, a.ADDRESS ADDRESS,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk2 a where ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=") a group by a.ADDRESS limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findkkczlltjdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.DBTIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.DBTIME <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			String sql = "select  a.ADDRESS ADDRESS,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk2 a where ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=") a  group by a.ADDRESS ";
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findkkrlltj(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') >='"+stime+"'";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') <='"+etime+"'";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			String sql = "select (select count(distinct date_format(DBTIME,'%Y-%m-%d')) from  tb_vehicle_hk2  a where ADDRESS <> '0' ";
			sql += tj;
			sql += ") as COUNT, date_format(DBTIME,'%Y-%m-%d') SJ,count(a.VEHICLE_NO) SJL from  "
					+ " (select * from  tb_vehicle_hk2 a where ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=" ) a group by date_format(DBTIME,'%Y-%m-%d') limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findkkrlltjdc(
				@Param("stime")String stime,
				@Param("etime")String etime,
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') >='"+stime+"'";
				date=stime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and date_format(a.DBTIME,'%Y-%m-%d') <='"+etime+"'";
				date=etime.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and a.ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			String sql = "select  date_format(DBTIME,'%Y-%m-%d') SJ,count(a.VEHICLE_NO) SJL from "
					+ " (select * from  tb_vehicle_hk2 a where ADDRESS <> '0'";
			sql += tj;
			sql += checked;
			sql +=") a  group by date_format(DBTIME,'%Y-%m-%d') ";
			System.out.println("tjsql="+sql);
			return sql;
		}
		public String findkkfdlltj(
				@Param("time")String time,
				@Param("address")String address,
				@Param("check") String check,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(time!=null&&!time.isEmpty()&&!time.equals("null")&&time.length()>0&&!time.equals("开始时间")){
				tj += " and date_format(DBTIME,'%Y-%m-%d') ='"+time+"'";
				date=time.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and ADDRESS in ("+address+")";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			String sql = "select (select count(distinct ADDRESS) from  tb_vehicle_hk2  where ADDRESS <> '0' ";
			sql += tj;
			sql += ") as COUNT,a.ADDRESS ADDRESS";
			for(int i=0;i<12;i++){
				sql +=",m"+i+".c"+i+"";
			}
			sql +=  " from (select distinct ADDRESS from tb_vehicle_hk2 where ADDRESS <> '0'  ";
			sql += tj;
			sql +=  ") a";

			for(int i=0;i<12;i++){
				sql += " left join (select ADDRESS,count(VEHICLE_NO) as c"+i+" from"
						+ " (select * from  tb_vehicle_hk2  where ADDRESS <> '0' and floor(date_format(DBTIME,'%H')/2)='"+i+"'";
				sql += tj;
				sql += checked;
				sql += ") a";
				sql +=" group by ADDRESS) m"+i+" on m"+i+".ADDRESS=a.ADDRESS";
			}
			sql += " limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("tj123sql="+sql);
			return sql;
		}
		public String findkkfdlltjdc(
				@Param("time")String time,
				@Param("address")String address,
				@Param("check") String check){
			String tj="";
			int c=0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
			String date = null;
			if(time!=null&&!time.isEmpty()&&!time.equals("null")&&time.length()>0&&!time.equals("开始时间")){
				tj += " and date_format(DBTIME,'%Y-%m-%d') ='"+time+"'";
				date=time.replaceAll("-","").substring(2,6);
				c++;
			}
			if(c==0){
				date=sdf.format(new Date());
			}
			if(address!=null&&!address.isEmpty()&&!address.equals("null")&&address.length()>0&&!address.equals("场站")){
				tj += " and ADDRESS in ("+address+")";
			}
			String sql = "select a.ADDRESS ADDRESS";
			for(int i=0;i<12;i++){
				sql +=",m"+i+".c"+i+"";
			}
			String checked="";
			if(check.equals("0")){
				checked=" GROUP BY DBTIME,VEHICLE_NO,ADDRESS ";
			}else {
				checked=" GROUP BY DATE_FORMAT(DBTIME,'%Y-%m-%d %H'),floor(DATE_FORMAT(DBTIME, '%i')/"+check+"),VEHICLE_NO,ADDRESS ";
			}
			sql +=  " from (select distinct ADDRESS from tb_vehicle_hk2 where ADDRESS <> '0' ";
			sql += tj;
			sql +=  ") a";

			for(int i=0;i<12;i++){
				sql += " left join (select ADDRESS,count(VEHICLE_NO) as c"+i+" from  "
						+ " (select * from  tb_vehicle_hk2  where ADDRESS <> '0' and floor(date_format(DBTIME,'%H')/2)='"+i+"'";
				sql += tj;
				sql += checked;
				sql += ") a";
				sql +=" group by ADDRESS) m"+i+" on m"+i+".ADDRESS=a.ADDRESS";
			}
			return sql;
		}
	}
}
