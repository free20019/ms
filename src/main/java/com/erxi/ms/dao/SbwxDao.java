package com.erxi.ms.dao;


import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


@Mapper
public interface SbwxDao {
	
	/**
	 * 安全监测
	 * @return
	 */
	@Select("select a.totalcount ty ,b.totalcount hq,c.totalcount ft from (select * from tb_position_totalcount where mdttype='1' order by  statisticstime desc limit 0,1) a "
			+ " left join  (select totalcount from tb_position_totalcount where mdttype='2' order by  statisticstime desc limit 0,1) b on 1=1"
			+ " left join  (select totalcount from tb_position_totalcount where mdttype='3' order by  statisticstime desc limit 0,1) c on 1=1")
	public List<Map<String, Object>> setsafety();
	/**
	 * 下拉所有公司
	 * @return
	 */
	@Select("select distinct COMP_NAME from TB_COMPANY order by COMP_NAME")
	@ResultType(String.class)
	public List<Map<String, Object>> getAllComp();
	/**
	 * 下拉栏
	 * @param table
	 * @return
	 */
	@Select("select * from ${table} ")
	public List<Map<String, Object>> findxll(
			@Param("table")String table);
	
	/**
	 * 车辆下拉栏
	 * @param table
	 * @return
	 */
	@Select("select VEHI_NO from ${table} ")
	public List<Map<String, Object>> findclxll(
			@Param("table")String table);
	/**
	 * 车辆下拉栏
	 * @param table
	 * @return
	 */
	@Select("select VEHICLE_NO from ${table} ")
	public List<Map<String, Object>> findclxll2(
			@Param("table")String table);
	
	/**
	 * 车辆监控
	 * @return
	 */
	@Select("select vc.VC_NAME,vt.VT_NAME,co.COMP_NAME,t.* from (select  al.*, b.PX,b.PY,b.LONGI,b.LATI,b.STIME,b.SPEED,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL,v.VT_ID,v.VC_ID,v.VEHI_NO "
			+ " from tb_vehicle v,tb_mdt_status b,"
			+ " tb_taxi_gzfx al"
			+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no) t"
			+ " left join tb_company co on t.COMP_ID=co.COMP_ID"
			+ " left join tb_vehi_type vt on t.VT_ID=vt.VT_ID"
			+ " left join tb_vehi_color vc on t.VC_ID=vc.VC_ID")
	public List<Map<String, Object>> vehicle();
	/**
	 * 定位
	 * @param vehi_no
	 * @return
	 */
	@Select("select  al.*,b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_taxi_gzfx al,tb_mdt_status b"
			+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no and v.vehi_no='${vehi_no}'")
	public List<Map<String, Object>> vhicmarker(
			@Param("vehi_no")String vehi_no);
	/**
	 * 定位
	 * @param vehi_no
	 * @return
	 */
	@Select("select  al.*,b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_mdt_status b,"
			+ "  tb_taxi_gzfx_overdue al"
			+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no and v.vehi_no='${vehi_no}'")
	public List<Map<String, Object>> vhicmarker2(
			@Param("vehi_no")String vehi_no);
	
	/**
	 * 添加车辆组
	 * @param tree_name
	 * @param vehstr
	 * @return
	 */
	@Insert("insert into tree_vehicle (name,children) values"
			+ "('${tree_name}','${vehstr}')")
	public int addtree(
			@Param("tree_name")String tree_name,
			@Param("vehstr")String vehstr);
	/**
	 * 修改车辆组
	 * @param id
	 * @param tree_name
	 * @param vehstr
	 * @return
	 */
	@Update("Update tree_vehicle set name= '${tree_name}',children='${vehstr}' where id='${id}'")
	public int editTree(
			@Param("id")String id,
			@Param("tree_name")String tree_name,
			@Param("vehstr")String vehstr);
	/**
	 * 删除车辆组
	 * @param id
	 * @return
	 */
	@Delete("delete from tree_vehicle where id='${id}'")
	public int removeTree(
			@Param("id")String id);
	/**
	 * 查询所有车辆组
	 * @return
	 */
	@Select("select * from tree_vehicle")
	public List<Map<String, Object>> findtree();
	
	/**
	 * 分组监控
	 * @param cphm
	 * @param status
	 * @return
	 */
	@SelectProvider(type = cljk.class, method = "findfzjk")
	public List<Map<String, Object>> findfzjk(
			@Param("status")String status,
			@Param("vehstr")String vehstr);
	
	/**
	 * 故障车辆即将到期预警
	 * @param stime
	 * @param etime
	 * @param time
	 * @return
	 */
//	@Select("select a.*,b.*,c.* from ("
//			+ "select ad.* from (select * from tb_alarm_history where dbtime=str_to_date('${etime}','%Y-%m-%d') and vehicle_no in (select vehicle_no from tb_alarm_history where dbtime=str_to_date('${stime}','%Y-%m-%d'))) ad"
//			+ " left join (select vehicle_no from tb_alarm_history where dbtime=str_to_date('${time}','%Y-%m-%d'))x on x.vehicle_no=ad.vehicle_no"
//			+ " left join (select * from  TB_REPAIR_RECORD where (RR_TIME_END<=str_to_date('${etime}','%Y-%m-%d') and RR_TIME_END>=str_to_date('${stime}','%Y-%m-%d'))"
//			+ " or (rr_time<=str_to_date('${etime}','%Y-%m-%d') and  rr_time>=str_to_date('${stime}','%Y-%m-%d'))) r on ad.vehicle_no=r.vehi_no"
//			+ "  where r.vehi_no is null and x.vehicle_no is null) a"
//			+ " left join (select v.VEHISTATUS,v.SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.vehi_no from vw_vehicle v,tb_mdt_status m where m.MDT_NO=v.SIM_NUM)b on b.vehi_no=a.vehicle_no"
//			+ " left join (select x.vehi_no as vehicle,x.RR_TIME,x.RR_TIME_END from TB_REPAIR_RECORD x where x.rr_time = (select max(rr_time) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no))c on c.vehicle=a.vehicle_no")
	@Select("select  al.*,b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_mdt_status b,"
			+ "  tb_taxi_gzfx_overdue al"
			+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no and al.db_time<=str_to_date('${etime}','%Y-%m-%d') and al.db_time>=str_to_date('${time}','%Y-%m-%d')")
	public List<Map<String, Object>> findjjbj(
			@Param("stime")String stime,
			@Param("etime")String etime,
			@Param("time")String time);
	
	
	
	/**
	 * 故障车辆已经到期预警
	 * @param stime
	 * @param etime
	 * @return
//	 */
//	@Select("select a.*,b.*,c.* from ("
//			+ "select ad.* from (select * from tb_alarm_history where dbtime=str_to_date('${etime}','%Y-%m-%d') and vehicle_no in (select vehicle_no from tb_alarm_history where dbtime=str_to_date('${time}','%Y-%m-%d'))) ad"
//			+ " left join (select * from  TB_REPAIR_RECORD where (RR_TIME_END<=str_to_date('${etime}','%Y-%m-%d') and RR_TIME_END>=str_to_date('${time}','%Y-%m-%d'))"
//			+ " or (rr_time<=str_to_date('${etime}','%Y-%m-%d') and  rr_time>=str_to_date('${time}','%Y-%m-%d'))) r on ad.vehicle_no=r.vehi_no"
//			+ "  where r.vehi_no is null) a"
//			+ " left join (select v.VEHISTATUS,v.SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.vehi_no from vw_vehicle v,tb_mdt_status m where m.MDT_NO=v.SIM_NUM)b on b.vehi_no=a.vehicle_no"
//			+ " left join (select x.vehi_no as vehicle,x.RR_TIME,x.RR_TIME_END from TB_REPAIR_RECORD x where x.rr_time = (select max(rr_time) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no))c on c.vehicle=a.vehicle_no")
	@Select("select  al.*,b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_mdt_status b,"
			+ "  tb_taxi_gzfx_overdue al"
			+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no and al.db_time<str_to_date('${time}','%Y-%m-%d')")
	public List<Map<String, Object>> findydqbj(
			@Param("etime")String etime,
			@Param("time")String time);
	
	
	/**
	 * 维修车辆预警（抓拍监控）
	 * @return
//	 */
	@SelectProvider(type = getwxcl.class, method = "faultMonitor")
	public List<Map<String, Object>> faultMonitor();
	/**
	 * 维修车辆查询
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxcl")
	public List<Map<String, Object>> findwxcl(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company,
			@Param("terminal")String terminal,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 维修车辆导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxcldc")
	public List<Map<String, Object>> findwxcldc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company,
			@Param("terminal")String terminal);
	
	/**
	 * 维修进度查询
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxjd")
	public List<Map<String, Object>> findwxjd(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 维修进度导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxjddc")
	public List<Map<String, Object>> findwxjddc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company);
	
	/**
	 * 维修车辆统计
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxtj")
	public List<Map<String, Object>> findwxtj(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company,
			@Param("terminal")String terminal,
			@Param("status")String status,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 维修车辆导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param company
	 * @param terminal
	 * @param status
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxtjdc")
	public List<Map<String, Object>> findwxtjdc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("company")String company,
			@Param("terminal")String terminal,
			@Param("status")String status);
	/**
	 * 维修工单
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxgd")
	public List<Map<String, Object>> findwxgd(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 维修工单导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findwxgddc")
	public List<Map<String, Object>> findwxgddc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle);
	
	/**
	 * 视频移位巡检
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findspywxj")
	public List<Map<String, Object>> findspywxj(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle,
			@Param("company")String company,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 视频移位巡检导出
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @return
	 */
	@SelectProvider(type = getwxcl.class, method = "findspywxjdc")
	public List<Map<String, Object>> findspywxjdc(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle,
			@Param("company")String company);
	/**
	 * 登记信息接入
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param person
	 * @param type
	 * @param terminal
	 * @return
	 */
	@SelectProvider(type = getwxxx.class, method = "getRepairVehicle")
	public List<Map<String, Object>> getRepairVehicle(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("block")String block, 
			@Param("company")String company,
			@Param("person")String person, 
			@Param("type")String type, 
			@Param("terminal")String terminal,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 进度信息接入
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param person
	 * @param type
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxxx.class, method = "getRepairjd")
	public List<Map<String, Object>> getRepairjd(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("block")String block, 
			@Param("company")String company,
			@Param("person")String person, 
			@Param("type")String type, 
			@Param("terminal")String terminal,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	/**
	 * 维修车辆统计
	 * @param stime
	 * @param etime
	 * @param vehicle
	 * @param block
	 * @param company
	 * @param person
	 * @param type
	 * @param terminal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getwxxx.class, method = "getRepairtj")
	public List<Map<String, Object>> getRepairtj(
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("vehicle")String vehicle, 
			@Param("block")String block, 
			@Param("company")String company,
			@Param("terminal")String terminal,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	class cljk{
		public String findfzjk(
				@Param("status")String status,
				@Param("vehstr")String vehstr){
			String tj="";
			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0){
				if(status.equals("0")){
//					tj += " and (a.RR_TIME_END<=a.db_time or a.RR_TIME_END>=now())";
				}
				if(status.equals("1")){
					tj += " and a.RR_TIME_END>=now()";			
				}
				if(status.equals("2")){
					tj += " and a.RR_TIME_END<=a.db_time";
				}
			}
			String sql ="";
			if(vehstr!=null&&!vehstr.isEmpty()&&!vehstr.equals("null")&&vehstr.length()>0){
				sql = "select  al.*,b.*,v.VEHISTATUS,v.SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
						+ " from vw_vehicle v,tb_mdt_status b,tb_taxi_gzfx al"
						+ " where v.vehi_no in("+vehstr+") and v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no ";
			}else{	
				if(status.equals("0")){
					sql="select vc.VC_NAME,vt.VT_NAME,co.COMP_NAME,t.* from (select  al.*, b.PX,b.PY,b.LONGI,b.LATI,b.STIME,b.SPEED,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL,v.VT_ID,v.VC_ID,v.VEHI_NO "
							+ " from tb_vehicle v,tb_mdt_status b,"
							+ " tb_taxi_gzfx al"
							+ " where v.vehi_no=al.vehicle_no and b.VEHI_NUM = v.vehi_no) t"
							+ " left join tb_company co on t.COMP_ID=co.COMP_ID"
							+ " left join tb_vehi_type vt on t.VT_ID=vt.VT_ID"
							+ " left join tb_vehi_color vc on t.VC_ID=vc.VC_ID";
				}else{					
					sql = "select a.*,b.PX,b.PY,b.LONGI,b.LATI,b.STIME,b.SPEED,vc.VC_NAME,vt.VT_NAME,co.COMP_NAME from (select al.*,r.*,v.COMP_ID,v.OWN_NAME,v.OWN_TEL,v.VT_ID,v.VC_ID from"
							+ " (select x.VEHI_NO,x.RR_TIME,x.RR_TIME_END from TB_REPAIR_RECORD x where x.rr_time = (select max(rr_time) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no)) r,"
							+ "  tb_vehicle v,tb_taxi_gzfx al"
							+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no ) a"
							+ " left join tb_company co on a.COMP_ID=co.COMP_ID"
							+ " left join tb_vehi_type vt on a.VT_ID=vt.VT_ID"
							+ " left join tb_vehi_color vc on a.VC_ID=vc.VC_ID"
							+ " left join tb_mdt_status b on b.VEHI_NUM=a.vehi_no where 1=1";
					sql += tj;							
				}
			}
			sql += " order by vehicle_no";
			System.out.println("fzjk="+sql);
			return sql;
		}
	}
	class getwxcl{
		public String faultMonitor(){
/*			String sql = "select distinct g.*,g.VEHICLE_NO VEHI_NO,m.PX,m.PY,m.STIME,m.MDT_NO,m.SPEED,m.STATE,v.MDT_NO,v.SIM_NUM,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.VEHI_SIM,v.OWN_NAME,v.OWN_TEL,h.HANDOVER_TIME,"
					+ " case when m.STIME >= DATE_SUB(NOW(), INTERVAL 5 MINUTE) then '在线' else '不在线' end STATUS"
					+ " from (select t.time,tt.* from (SELECT max(ONE_TIME) time,VEHICLE_NO FROM tb_video_shift GROUP BY VEHICLE_NO) t "
					+ " left JOIN tb_video_shift tt on tt.VEHICLE_NO=t.VEHICLE_NO and t.time=tt.ONE_TIME) g,"
					+ " (SELECT max(rr_time) rr_time,vehi_no FROM tb_repair_record GROUP BY vehi_no) r,"
					+ " vw_vehicle v, (SELECT max(HANDOVER_TIME) HANDOVER_TIME,AUTO_NUM FROM vehicle_handover GROUP BY AUTO_NUM) h,"
					+ " (select t.*, max(STIME) FROM TB_MDT_STATUS t GROUP BY t.vehi_num) m"
//					+ " (select t.time,tt.* from (SELECT max(STIME) time,vehi_num FROM TB_MDT_STATUS GROUP BY vehi_num) t left JOIN TB_MDT_STATUS tt on tt.vehi_num=t.vehi_num and t.time=tt.STIME) m"
					+ " where r.vehi_no = g.VEHICLE_NO and g.VEHICLE_NO = m.vehi_num and g.VEHICLE_NO=v.vehi_no and g.VEHICLE_NO = replace(h.AUTO_NUM,'.','') "
					+ " and g.ONE_TIME> r.rr_time"
					+ " and (ONE_ROAD_ONE!=0 or ONE_ROAD_TWO!=0 or ONE_ROAD_THREE!=0 or ONE_ROAD_FOUR!=0 or TWO_ROAD_ONE!=0 or TWO_ROAD_TWO!=0 or TWO_ROAD_THREE!=0 or TWO_ROAD_FOUR!=0)"
					+ " order by m.STIME desc";*/
			String sql = "select t.*,h.HANDOVER_TIME from (select distinct g.*,g.VEHICLE_NO VEHI_NO,m.PX,m.PY,m.STIME,m.MDT_NO MDT_NO2,m.SPEED,m.STATE,v.SIM_NUM,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.VEHI_SIM,v.OWN_NAME,v.OWN_TEL,"
					+ " case when m.STIME >= DATE_SUB(NOW(), INTERVAL 5 MINUTE) then '在线' else '不在线' end STATUS"
					+ " from (select t.time,tt.* from (SELECT max(ONE_TIME) time,VEHICLE_NO FROM tb_video_shift GROUP BY VEHICLE_NO) t "
					+ " left JOIN tb_video_shift tt on tt.VEHICLE_NO=t.VEHICLE_NO and t.time=tt.ONE_TIME) g,"
					+ " (SELECT max(rr_time) rr_time,vehi_no FROM tb_repair_record GROUP BY vehi_no) r,"
					+ " vw_vehicle v,"
					+ " (select t.*, max(STIME) FROM TB_MDT_STATUS t GROUP BY t.vehi_num) m"
					+ " where r.vehi_no = g.VEHICLE_NO and g.VEHICLE_NO = m.vehi_num and g.VEHICLE_NO=v.vehi_no"
					+ " and g.ONE_TIME> r.rr_time"
					+ " and (ONE_ROAD_ONE!=0 or ONE_ROAD_TWO!=0 or ONE_ROAD_THREE!=0 or ONE_ROAD_FOUR!=0 or TWO_ROAD_ONE!=0 or TWO_ROAD_TWO!=0 or TWO_ROAD_THREE!=0 or TWO_ROAD_FOUR!=0)"
					+ " order by m.STIME desc) t"
					+ " left join (SELECT max(HANDOVER_TIME) HANDOVER_TIME,replace(AUTO_NUM,'.','') AUTO_NUM FROM vehicle_handover GROUP BY AUTO_NUM) h on t.VEHICLE_NO =h.AUTO_NUM";
			System.out.println("faultMonitorsql="+sql);
			return sql;
		}		
		public String findwxcl(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company,
				@Param("terminal")String terminal,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.db_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.db_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and a.comp_name = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0&&!terminal.equals("终端类型公司")){
				tj += " and a."+terminal+" != '0'";
			}
			String sql = "select (select count(*) COUNT from (select b.*,co.COMP_NAME"
					+ " from (select al.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v, "
					+ "	tb_alarm_realtime al where v.vehi_no=al.vehicle_no ) b "
					+ " left join tb_company co on b.COMP_ID=co.COMP_ID) a where 1=1";
			sql += " and (a.LOW_VOLTAGE='1' or a.NO_UPLOAD='1' or a.ANT_FAULT='1' or a.INEXACT='1' or a.GPS_NO_BACK='1' or a.METER_DISCONN='1')";
			sql += tj;				
			sql += ") as COUNT,tt.* from (select a.* from (select b.*,co.COMP_NAME"
					+ " from (select al.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v, "
					+ "	tb_alarm_realtime al where v.vehi_no=al.vehicle_no ) b "
					+ " left join tb_company co on b.COMP_ID=co.COMP_ID order by b.db_time desc) a where 1=1";
			sql += " and (a.LOW_VOLTAGE='1' or a.NO_UPLOAD='1' or a.ANT_FAULT='1' or a.INEXACT='1' or a.GPS_NO_BACK='1' or a.METER_DISCONN='1')";
			sql += tj;				
			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxcldc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company,
				@Param("terminal")String terminal){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.db_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.db_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and a.comp_name = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0&&!terminal.equals("终端类型公司")){
				tj += " and a."+terminal+" != '0'";
			}				
			String sql = "select a.* from (select b.*,co.COMP_NAME from (select al.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v,"
					+ " tb_alarm_realtime al where v.vehi_no=al.vehicle_no ) b "
					+ "	left join tb_company co on b.COMP_ID=co.COMP_ID order by b.db_time desc) a where 1=1";
			sql += " and (a.LOW_VOLTAGE='1' or a.NO_UPLOAD='1' or a.ANT_FAULT='1' or a.INEXACT='1' or a.GPS_NO_BACK='1' or a.METER_DISCONN='1')";
			sql += tj;				
			sql +=" order by a.db_time desc";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxjd(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.RR_TIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.RR_TIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehi_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and a.COMP_NAME = '"+company+"'";
			}
//			String sql = "select (select count(1) COUNT from (select a.* from (select co.COMPANY_NAME COMP_NAME,al.*,r.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v,tb_global_vehicle co,"
//					+ " (select distinct vehi_no from TB_REPAIR_RECORD) r,"
//					+ " tb_taxi_gzfx al"
//					+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no and v.vehi_no=co.plate_number) a where 1=1";
//			sql += tj;				
//			sql += ") m) as COUNT,tt.* from (select a.*"
//					+ " from (select co.COMPANY_NAME COMP_NAME,al.*,r.*,v.COMP_ID,v.SIM_NUM,v.OWN_NAME,v.OWN_TEL from tb_vehicle v,tb_global_vehicle co,"
//					+ " (select x.* from TB_REPAIR_RECORD x where x.RR_TIME_END = (select max(RR_TIME_END) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no)) r,"
//					+ " tb_taxi_gzfx al"
//					+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no and v.vehi_no=co.plate_number) a where 1=1";
//			sql += tj;				
//			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			
			String sql = "select (select count(1) COUNT from (select a.* from (select distinct co.COMPANY_NAME COMP_NAME,r.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v" +
					",(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co,"
					+ " TB_REPAIR_RECORD r"
					+ " where v.vehi_no=r.vehi_no and v.vehi_no=co.plate_number and r.rr_time is not null) a where 1=1";
			sql += tj;				
			sql += ") m) as COUNT,tt.* from (select al.*,a.*"
					+ " from (select distinct co.COMPANY_NAME COMP_NAME,r.*,v.COMP_ID,v.SIM_NUM,v.OWN_NAME,v.OWN_TEL from tb_vehicle v" +
					" ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co,"
					+ " TB_REPAIR_RECORD r"
					+ " where v.vehi_no=r.vehi_no and v.vehi_no=co.plate_number and r.rr_time is not null order by r.rr_time desc) a "
					+ " left join tb_taxi_gzfx al on a.vehi_no=al.vehicle_no where 1=1";
			sql += tj;				
			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxjddc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and a.RR_TIME >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and a.RR_TIME <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and a.vehi_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and a.COMP_NAME = '"+company+"'";
			}
			String sql = "select al.*,a.*"
					+ " from (select distinct co.COMPANY_NAME COMP_NAME,r.*,v.COMP_ID,v.SIM_NUM,v.OWN_NAME,v.OWN_TEL from tb_vehicle v" +
					" ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co,"
					+ " TB_REPAIR_RECORD r"
					+ " where v.vehi_no=r.vehi_no and v.vehi_no=co.plate_number and r.rr_time is not null order by r.rr_time desc ) a "
					+ " left join tb_taxi_gzfx al on a.vehi_no=al.vehicle_no where 1=1";
			sql += tj;				
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxtj(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company,
				@Param("terminal")String terminal,
				@Param("status") String status,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and al.db_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and al.db_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and al.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and co.COMPANY_NAME = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0){
				tj += " and al."+terminal+" = '1'";
			}
			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0){
				if(status.equals("1")){
//					tj += " and a.RR_TIME_END>=a.db_time and a.RR_TIME_END<=now()";
					tj += " and (al.NO_GPS='0' and al.NO_JJQ='0' and al.NO_GPS_JJQ='0' and al.SEVEN_GPS_JJQ='0' and al.EMPTY_HEAVY='0')";
				}
				if(status.equals("2")){
//					tj += " and a.RR_TIME_END<a.db_time";	
					tj += " and (al.NO_GPS='1' or al.NO_JJQ='1' or al.NO_GPS_JJQ='1' or al.SEVEN_GPS_JJQ='1' or al.EMPTY_HEAVY='1')";
				}
				if(status.equals("3")){
					tj2 += " where RR_TIME_END>now()";
				}
			}
			String sql = "select (select count(1) from tb_vehicle v,"
					+ " (select distinct vehi_no from TB_REPAIR_RECORD";
			sql += tj2;
			sql += " ) r, tb_taxi_gzfx al" +
					" ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co"
					+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no and v.vehi_no=co.plate_number";
			sql += tj;				
			sql += ") as COUNT, a.*,r.*"
					+ " from (select  co.COMPANY_NAME COMP_NAME,al.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v" +
					" ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co,"
					+ " (select distinct VEHI_NO from TB_REPAIR_RECORD";
					sql += tj2;
					sql += " ) r, tb_taxi_gzfx al"
					+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no and v.vehi_no=co.plate_number ";
			sql += tj;	
			sql +=" order by db_time desc limit "+((pageIndex-1)*pageSize)+","+pageSize+" ) a "
					+ "	left join (select x.*,u.REAL_NAME WXRY,rt.RT_TYPE,ra.RA_ADDR from TB_REPAIR_RECORD x,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR ra "
					+ " where x.RR_TIME_END = (select max(RR_TIME_END) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no) and x.user_id=u.user_id and x.rt_id=rt.rt_id and x.ra_id=ra.ra_id ) r on r.vehi_no=a.vehicle_no";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxtjdc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company")String company,
				@Param("terminal")String terminal,
				@Param("status") String status){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and al.db_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and al.db_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and al.vehicle_no= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and co.COMPANY_NAME = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0){
				tj += " and al."+terminal+" = '1'";
			}
			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0){
				if(status.equals("1")){
//					tj += " and a.RR_TIME_END>=a.db_time and a.RR_TIME_END<=now()";
					tj += " and (al.NO_GPS='0' and al.NO_JJQ='0' and al.NO_GPS_JJQ='0' and al.SEVEN_GPS_JJQ='0' and al.EMPTY_HEAVY='0')";
				}
				if(status.equals("2")){
//					tj += " and a.RR_TIME_END<a.db_time";	
					tj += " and (al.NO_GPS='1' or al.NO_JJQ='1' or al.NO_GPS_JJQ='1' or al.SEVEN_GPS_JJQ='1' or al.EMPTY_HEAVY='1')";
				}
				if(status.equals("3")){
					tj2 += " where RR_TIME_END>now()";
				}
			}
			String sql = "select a.*,r.* from(select co.COMPANY_NAME COMP_NAME,al.*,r.*,v.SIM_NUM,v.COMP_ID,v.OWN_NAME,v.OWN_TEL from tb_vehicle v" +
					" ,(select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') co,"
					+ " (select distinct vehi_no from TB_REPAIR_RECORD";
			sql += tj2;
			sql += " ) r, tb_taxi_gzfx al"
					+ " where v.vehi_no=r.vehi_no and r.vehi_no=al.vehicle_no and v.vehi_no=co.plate_number";
			sql += tj;	
			sql +=  " ) a left join (select x.*,u.REAL_NAME WXRY,rt.RT_TYPE,ra.RA_ADDR from TB_REPAIR_RECORD x,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR ra "
					+ " where x.RR_TIME_END = (select max(RR_TIME_END) from TB_REPAIR_RECORD y where y.vehi_no=x.vehi_no) and x.user_id=u.user_id and x.rt_id=rt.rt_id and x.ra_id=ra.ra_id ) r"
					+ " on r.vehi_no=a.vehicle_no";
			System.out.println("sql="+sql);
			return sql;
		}
		public String findwxgd(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and al.fault_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and al.fault_time <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and al.vehicle_no= '"+vehicle+"'";
			}
			String sql = "select (select count(*) from tb_taxi_gzfx_examine al where 1=1";
	        sql +=tj;
	        sql += ") as COUNT,t.* from (select distinct al.*,v.COMP_NAME,date_format(al.fault_time,'%Y-%m-%d %H:%i:%s') TIME,date_format(al.ONCE_TIME,'%Y-%m-%d %H:%i:%s') ONCE,date_format(al.TWICE_TIME,'%Y-%m-%d %H:%i:%s') TWICE," +
	                " date_format(al.THIRD_TIME,'%Y-%m-%d %H:%i:%s') THIRD, date_format(al.WD_FEEDBACK_TIME,'%Y-%m-%d %H:%i:%s') FGSJ,date_format(al.REPAIR_TIME,'%Y-%m-%d %H:%i:%s') WXSJ,g.AREA_NAME from tb_taxi_gzfx_examine al" +
	                " left join vw_vehicle v on al.vehicle_no=v.vehi_no" +
	                " left join  (select plate_number,area_name from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on al.vehicle_no=g.plate_number " +
	                " where 1=1";
	        sql +=tj;
	        sql +=" order by al.fault_time desc) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;

		}
		public String findwxgddc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and al.fault_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and al.fault_time <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and al.vehicle_no= '"+vehicle+"'";
			}
			String sql = "select distinct al.*,v.COMP_NAME,date_format(al.fault_time,'%Y-%m-%d %H:%i:%s') TIME,date_format(al.ONCE_TIME,'%Y-%m-%d %H:%i:%s') ONCE,date_format(al.TWICE_TIME,'%Y-%m-%d %H:%i:%s') TWICE," +
	                " date_format(al.THIRD_TIME,'%Y-%m-%d %H:%i:%s') THIRD, date_format(al.WD_FEEDBACK_TIME,'%Y-%m-%d %H:%i:%s') FGSJ,date_format(al.REPAIR_TIME,'%Y-%m-%d %H:%i:%s') WXSJ,g.AREA_NAME from tb_taxi_gzfx_examine al" +
	                " left join vw_vehicle v on al.vehicle_no=v.vehi_no" +
	                " left join  (select plate_number,area_name from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') g on al.vehicle_no=g.plate_number " +
	                " where 1=1";
	        sql +=tj;
	        sql +=" order by al.fault_time desc";
			System.out.println("sql="+sql);
			return sql;
		}
		
		public String findspywxj(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("company") String company,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and (s.two_time >=str_to_date('"+stime+"','%Y-%m-%d') or (s.one_time>=str_to_date('"+stime+"','%Y-%m-%d') and s.two_time is null))";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and (s.two_time <=str_to_date('"+etime+"','%Y-%m-%d') or (s.one_time<=str_to_date('"+etime+"','%Y-%m-%d') and s.two_time is null))";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and v.VEHI_NO= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and v.comp_name = '"+company+"'";
			}
			String sql = "select (select count(*) from TB_VIDEO_SHIFT s,vw_vehicle v where s.vehicle_no=v.vehi_no";
	        sql +=tj;
	        sql += ") as COUNT,t.* from (select s.*,v.comp_name,v.VEHI_NO from TB_VIDEO_SHIFT s, vw_vehicle v"
	        		+ " where s.vehicle_no=v.vehi_no";
	        sql +=tj;
	        sql +=" order by s.TWO_TIME desc,s.ONE_TIME desc,v.VEHI_NO) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println("sql="+sql);
			return sql;

		}
		public String findspywxjdc(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle,
				@Param("company") String company){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and (s.two_time >=str_to_date('"+stime+"','%Y-%m-%d') or (s.one_time>=str_to_date('"+stime+"','%Y-%m-%d') and s.two_time is null))";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and (s.two_time <=str_to_date('"+etime+"','%Y-%m-%d') or (s.one_time<=str_to_date('"+etime+"','%Y-%m-%d') and s.two_time is null))";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj += " and v.VEHI_NO= '"+vehicle+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj += " and v.comp_name = '"+company+"'";
			}
			String sql = "select s.*,v.comp_name,v.VEHI_NO from TB_VIDEO_SHIFT s, vw_vehicle v where s.vehicle_no=v.vehi_no";
	        sql +=tj;
	        sql +=" order by s.TWO_TIME desc,s.ONE_TIME desc,v.VEHI_NO";
			System.out.println("sql="+sql);
			return sql;
		}
	}
	class getwxxx{
		public String getRepairVehicle(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("block")String block, 
				@Param("company")String company,
				@Param("person")String person, 
				@Param("type")String type, 
				@Param("terminal")String terminal,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and rr_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and rr_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(person!=null&&!person.isEmpty()&&!person.equals("null")&&person.length()>0&&!person.equals("维修人员")){
				tj += " and u.user_name = '"+person+"'";
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0&&!type.equals("维修类型")){
				tj += " and rt_type = '"+type+"'";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj2 += " and al.vehi_no= '"+vehicle+"'";
			}
			if(block!=null&&!block.isEmpty()&&!block.equals("null")&&block.length()>0&&!block.equals("区块")){
				tj2 += " and owner_name = '"+block+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj2 += " and comp_name = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0&&!terminal.equals("终端类型公司")){
				tj2 += " and mt_name = '"+terminal+"'";
			}
			String sql = "select (select count(*) COUNT from (select al.*,trm.RM_MALFUNCTION,us.real_name shry,v.mdt_no,v.sim_num,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.vehi_sim,v.own_name,v.own_tel"
					+ " from (select t.*,u.real_name wxry,rt.rt_type,a.ra_addr from TB_REPAIR_RECORD t,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR a"
					+ " where t.user_id=u.user_id and t.rt_id=rt.rt_id and t.ra_id=a.ra_id";
			sql += tj;				
			sql += "  ) al left join TB_REPAIR_MALFUNCTION trm on al.rm_id = trm.rm_id"
					+ " left join tb_user_wx us on al.RR_ASSESSOR=us.user_id"
					+ " left join tb_vehicle v on al.vehi_no = v.vehi_no where 1=1 and al.vehi_no is not null";
			sql += tj2;
			sql += ") m ) as COUNT,tt.* from (select al.*,us.REAL_NAME SHRY,v.MDT_NO,v.SIM_NUM,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.VEHI_SIM,v.OWN_NAME,v.OWN_TEL"
					+ " from (select t.*,u.REAL_NAME WXRY,rt.RT_TYPE,a.RA_ADDR from (select * from TB_REPAIR_RECORD order by rr_time desc) t,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR a"
					+ " where t.user_id=u.user_id and t.rt_id=rt.rt_id and t.ra_id=a.ra_id";
			sql += tj;				
			sql += "  ) al left join TB_REPAIR_MALFUNCTION trm on al.rm_id = trm.rm_id"
					+ " left join tb_user_wx us on al.RR_ASSESSOR=us.user_id"
					+ " left join tb_vehicle v on al.vehi_no = v.vehi_no where 1=1 and al.vehi_no is not null";
			sql += tj2;
			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			sql +=" order by rr_time desc";
			System.out.println("sql="+sql);
			return sql;
		}
		
		public String getRepairjd(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("block")String block, 
				@Param("company")String company,
				@Param("person")String person, 
				@Param("type")String type, 
				@Param("terminal")String terminal,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and rr_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and rr_time <str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(person!=null&&!person.isEmpty()&&!person.equals("null")&&person.length()>0&&!person.equals("维修人员")){
				tj += " and u.user_name = '"+person+"'";
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0&&!type.equals("维修类型")){
				tj += " and rt_type = '"+type+"'";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj2 += " and al.vehi_no= '"+vehicle+"'";
			}
			if(block!=null&&!block.isEmpty()&&!block.equals("null")&&block.length()>0&&!block.equals("区块")){
				tj2 += " and owner_name = '"+block+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj2 += " and comp_name = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0&&!terminal.equals("终端类型公司")){
				tj2 += " and mt_name = '"+terminal+"'";
			}
			String sql = "select (select count(*) COUNT from (select al.*,trm.RM_MALFUNCTION,us.real_name shry,v.mdt_no,v.sim_num,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.vehi_sim,v.own_name,v.own_tel"
					+ " from (select t.*,u.real_name wxry,rt.rt_type,a.ra_addr from TB_REPAIR_RECORD t,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR a"
					+ " where t.user_id=u.user_id and t.rt_id=rt.rt_id and t.ra_id=a.ra_id";
			sql += tj;				
			sql += "  ) al left join TB_REPAIR_MALFUNCTION trm on al.rm_id = trm.rm_id"
					+ " left join tb_user_wx us on al.RR_ASSESSOR=us.user_id"
					+ " left join vw_vehicle v on al.vehi_no = v.vehi_no where 1=1 and al.vehi_no is not null";
			sql += tj2;
			sql += ") m ) as COUNT,tt.* from (select al.*,us.REAL_NAME SHRY,v.MDT_NO,v.SIM_NUM,V.MT_NAME,V.OWNER_NAME,V.COMP_NAME,v.MDT_SUB_TYPE,v.VEHI_SIM,v.OWN_NAME,v.OWN_TEL"
					+ " from (select t.*,u.REAL_NAME WXRY,rt.RT_TYPE,a.RA_ADDR from (select * from TB_REPAIR_RECORD order by rr_time desc) t,tb_user_wx u,TB_REPAIR_TYPE rt,TB_REPAIR_ADDR a"
					+ " where t.user_id=u.user_id and t.rt_id=rt.rt_id and t.ra_id=a.ra_id";
			sql += tj;				
			sql += "  ) al left join TB_REPAIR_MALFUNCTION trm on al.rm_id = trm.rm_id"
					+ " left join tb_user_wx us on al.RR_ASSESSOR=us.user_id"
					+ " left join vw_vehicle v on al.vehi_no = v.vehi_no where 1=1 and al.vehi_no is not null";
			sql += tj2;
			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			sql +=" order by rr_time desc";
			System.out.println("sql12="+sql);
			return sql;
		}
		
		public String getRepairtj(
				@Param("stime")String stime,
				@Param("etime")String etime, 
				@Param("vehicle")String vehicle, 
				@Param("block")String block, 
				@Param("company")String company,
				@Param("terminal")String terminal,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			String tj2="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0&&!stime.equals("开始时间")){
				tj += " and rr_time >=str_to_date('"+stime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0&&!etime.equals("结束时间")){
				tj += " and rr_time <=str_to_date('"+etime+"','%Y-%m-%d %H:%i:%s')";
			}
			if(vehicle!=null&&!vehicle.isEmpty()&&!vehicle.equals("null")&&vehicle.length()>0&&!vehicle.equals("车牌号码")){
				tj2 += " and a.vehi_no= '"+vehicle+"'";
			}
			if(block!=null&&!block.isEmpty()&&!block.equals("null")&&block.length()>0&&!block.equals("区块")){
				tj2 += " and owner_name = '"+block+"'";
			}
			if(company!=null&&!company.isEmpty()&&!company.equals("null")&&company.length()>0&&!company.equals("公司")){
				tj2 += " and comp_name = '"+company+"'";
			}
			if(terminal!=null&&!terminal.isEmpty()&&!terminal.equals("null")&&terminal.length()>0&&!terminal.equals("终端类型公司")){
				tj2 += " and mt_name = '"+terminal+"'";
			}
			String sql = " select (select count(*) COUNT from (select * from(select vehi_no,count(1) c from TB_REPAIR_RECORD t,tb_user_wx u,TB_REPAIR_TYPE r"
						+" where t.user_id=u.user_id and t.rt_id=r.rt_id ";
			sql += tj;				
			sql += "  group by vehi_no)a left join vw_vehicle v on a.vehi_no = v.vehi_no where 1=1 and a.vehi_no is not null";
			sql += tj2;
			sql += " ) m ) as COUNT,tt.* from (select a.*,v.COMP_NAME,v.MT_NAME,v.OWNER_NAME,v.MDT_SUB_TYPE,v.VEHI_SIM,v.OWN_NAME,v.OWN_TEL from(select vehi_no,count(1) c from TB_REPAIR_RECORD t,tb_user_wx u,TB_REPAIR_TYPE r"
						+" where t.user_id=u.user_id and t.rt_id=r.rt_id";
			sql += tj;				
			sql += "  group by vehi_no)a left join vw_vehicle v on a.vehi_no = v.vehi_no where 1=1 and a.vehi_no is not null";
			sql += tj2;
			sql +=" ) tt order by tt.vehi_no limit "+((pageIndex-1)*pageSize)+","+pageSize+"";
			System.out.println("sql3="+sql);
			return sql;
		}
	}
	@Select("select * from Tb_Order_Match t where dep_time >=str_to_date(#{stime},'%Y-%m-%d %H:%i:%s') and dep_time <=str_to_date(#{etime},'%Y-%m-%d %H:%i:%s')")
	public List<Map<String, Object>> dcrdfx(
			@Param("stime")String stime,@Param("etime")String etime);
	@Select("select date_format(dep_time,'%H') dep_time,count(1) c from Tb_Order_Match where dep_time >=str_to_date(#{stime},'%Y-%m-%d %H:%i:%s') and dep_time <=str_to_date(#{etime},'%Y-%m-%d %H:%i:%s') group by date_format(dep_time,'%H') order by dep_time")
	public List<Map<String, Object>> dcsjfb(
			@Param("stime")String stime,@Param("etime")String etime);
	@Select("SELECT	s.*, area.area_name zdarea_name,	area.area_coordinates area_coordinates1 FROM (SELECT orientid,destid,order_count,dbtime,area_id,area_name,area_coordinates FROM	${table} t,			tb_order_area a		WHERE			t.OrientID = a.area_id		AND date_format(DBTime, '%Y-%m-%d') = #{time}	) s,	tb_order_area area WHERE s.DestID = area.area_id")
	public List<Map<String, Object>> cklxfx(
			@Param("time")String time,@Param("table")String table);
}
