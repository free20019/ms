package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface TjfxDao {

	/**
	 * 驾驶员查询
	 * @param pageSize 
	 * @param pageIndex 
	 * @param city 
	 * @param status 
	 * @param jyxkz 
	 * @param fwzh 
	 * @param gsm 
	 * @param xm 
	 * @param cph 
	 * @param sfzh 
	 */
	@SelectProvider(type = getJbxx.class, method = "getJsy")
	public List<Map<String, Object>> getJsy(@Param("sfzh")String sfzh, 
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("gsm")String gsm, 
			@Param("age")String age, 
			@Param("fwzh")String fwzh, 
			@Param("jyxkz")String jyxkz, 
//			@Param("status")String status, 
			@Param("city")String city, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 驾驶员查询导出
	 * @param sfzh
	 * @param cph
	 * @param xm
	 * @param gsm
	 * @param fwzh
	 * @param jyxkz
	 * @param city
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getJsyxlsx")
	public List<Map<String, Object>> getJsyxlsx(@Param("sfzh")String sfzh, 
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("gsm")String gsm, 
			@Param("age")String age, 
			@Param("fwzh")String fwzh, 
			@Param("jyxkz")String jyxkz, 
//			@Param("status")String status, 
			@Param("city")String city);
	
	/**
	 * 车辆查询
	 * @param cph
	 * @param xm
	 * @param yszh
	 * @param jyxkzh
	 * @param status
	 * @param city
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getCl")
	public List<Map<String, Object>> getCl(
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("yszh")String yszh, 
			@Param("jyxkzh")String jyxkzh, 
			@Param("age")String age, 
			@Param("type")String type, 
//			@Param("status")String status, 
			@Param("city")String city, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 车辆查询导出
	 * @param cph
	 * @param xm
	 * @param yszh
	 * @param jyxkzh
	 * @param city
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getClxlsx")
	public List<Map<String, Object>> getClxlsx(
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("yszh")String yszh, 
			@Param("jyxkzh")String jyxkzh, 
			@Param("age")String age, 
			@Param("type")String type, 
//			@Param("status")String status, 
			@Param("city")String city);
	
	
	/**
	 * 违章查询
	 * @param stratTime
	 * @param endTime
	 * @param cph
	 * @param xm
	 * @param yszh
	 * @param jyxkzh
	 * @param area
	 * @param part
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getWz")
	public List<Map<String, Object>> getWz(
			@Param("stratTime")String stratTime, 
			@Param("endTime")String endTime,
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("yszh")String yszh, 
			@Param("jyxkzh")String jyxkzh, 
			@Param("area")String area,
			@Param("part")String part, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 违章查询导出
	 * @param stratTime
	 * @param endTime
	 * @param cph
	 * @param xm
	 * @param yszh
	 * @param jyxkzh
	 * @param area
	 * @param part
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getWzxlsx")
	public List<Map<String, Object>> getWzxlsx(
			@Param("stratTime")String stratTime, 
			@Param("endTime")String endTime,
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("yszh")String yszh, 
			@Param("jyxkzh")String jyxkzh, 
			@Param("area")String area,
			@Param("part")String part);
	
	
	
	/**
	 * 企业查询
	 * @param name
	 * @param area
	 * @param style
	 * @param min
	 * @param max
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	
	@SelectProvider(type = getJbxx.class, method = "getQy")
	public List<Map<String, Object>> getQy(
			@Param("name")String name, 
			@Param("area")String area,
			@Param("style")String style, 
			@Param("min")String min,
			@Param("max")String max, 
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 企业查询导出
	 * @param name
	 * @param area
	 * @param style
	 * @param min
	 * @param max
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getQyxlsx")
	public List<Map<String, Object>> getQyxlsx(
			@Param("name")String name, 
			@Param("area")String area,
			@Param("style")String style, 
			@Param("min")String min,
			@Param("max")String max);
	
	
	
	/**
	 * 车载设备查询
	 * @param cph
	 * @param yh
	 * @param zdlx
	 * @param zdbh
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getCzsb")
	public List<Map<String, Object>> getCzsb(
			@Param("cph")String cph, 
			@Param("yh")String yh, 
			@Param("zdlx")String zdlx, 
			@Param("zdbh")String zdbh, 
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 车载设备查询导出
	 * @param cph
	 * @param yh
	 * @param zdlx
	 * @param zdbh
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getCzsbcxxlsx")
	public List<Map<String, Object>> getCzsbcxxlsx(
			@Param("cph")String cph, 
			@Param("yh")String yh, 
			@Param("zdlx")String zdlx, 
			@Param("zdbh")String zdbh);
	

	
//	/**
//	 * 故障设备查询
//	 * @param cph
//	 * @param yh
//	 * @param zdlx
//	 * @param zdbh
//	 * @param pageIndex
//	 * @param pageSize
//	 * @return
//	 */
//	@SelectProvider(type = getJbxx.class, method = "getGzsb")
//	public List<Map<String, Object>> getGzsb(
//			@Param("cph")String cph, 
//			@Param("yh")String yh, 
//			@Param("zdlx")String zdlx, 
//			@Param("zdbh")String zdbh, 
//			@Param("pageIndex")Integer pageIndex,
//			@Param("pageSize")Integer pageSize);
//	
//	
//	/**
//	 * 故障设备查询导出\
//	 * @param cph
//	 * @param yh
//	 * @param zdlx
//	 * @param zdbh
//	 * @return
//	 */
//	@SelectProvider(type = getJbxx.class, method = "getGzsbxlsx")
//	public List<Map<String, Object>> getGzsbxlsx(
//			@Param("cph")String cph, 
//			@Param("yh")String yh, 
//			@Param("zdlx")String zdlx, 
//			@Param("zdbh")String zdbh);
	
	
	

	/**
	 * 疑似绕路分析
	 * @param stime
	 * @param etime
	 * @param cphm
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYsrlfx")
	public List<Map<String, Object>> getYsrlfx(
			@Param("stime")String stime, 
			@Param("etime")String etime,
			@Param("cphm")String cphm, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	/**
	 * 异常营运分析
	 * @param stime
	 * @param etime
	 * @param cphm
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYyycfx")
	public List<Map<String, Object>> getYyycfx(
			@Param("stime")String stime, 
			@Param("etime")String etime,
			@Param("cphm")String cphm, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 营运里程异常分析 - 1
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYylcycfx")
	public List<Map<String, Object>> getYylcycfx(
			@Param("maxMileage")Integer maxMileage,
			@Param("minMileage")Integer minMileage, 
			@Param("startTime")String startTime);
	
	/**
	 * 营运里程异常分析 - 2
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYylcycfxTwo")
	public List<Map<String, Object>> getYylcycfxTwo(
			@Param("maxMileage")Integer maxMileage,
			@Param("minMileage")Integer minMileage, 
			@Param("startTime")String startTime);
	
	/**
	 * 营运单次异常分析
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYydcycfx")
	public List<Map<String, Object>> getYydcycfx(
			@Param("maxMileage")Integer maxMileage,
			@Param("minMileage")Integer minMileage, 
			@Param("startTime")String startTime);
	
	/**
	 * 营运单次异常分析 -- 2
	 * @param maxMileage
	 * @param minMileage
	 * @param startTime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getYydcycfxTwo")
	public List<Map<String, Object>> getYydcycfxTwo(
			@Param("maxMileage")Integer maxMileage,
			@Param("minMileage")Integer minMileage, 
			@Param("startTime")String startTime);
	
	
	
	/**
	 * 燃油类型 -- 圆图
	 * @return
	 */
//	@Select("select  t.FUEL_NAME,count(t.FUEL_NAME) as count from  tb_global_vehicle@taxilink113 t where t.industry=090 and t.business_scope_code = 1400  AND t.STATUS_NAME='营运' and t.plate_number LIKE '浙A%' and t.fuel_name is not null group by t.FUEL_NAME")
	@Select("select  t.FUEL_NAME,count(t.FUEL_NAME) as count from  tb_global_vehicle t where t.industry=090 and t.business_scope_code = 1400  AND t.STATUS_NAME='营运' and t.plate_number LIKE '浙A%' and t.fuel_name is not null group by t.FUEL_NAME")
	public List<Map<String, Object>> getRylx();

	
	/**
	 * 燃油类型 -- 表数据
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getXny")
	public List<Map<String, Object>> getXny(
			@Param("type")String type, 
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 围栏进出分析
	 * @param wlmc
	 * @param stime
	 * @param etime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getWl")
	public List<Map<String, Object>> getWl(
			@Param("wlmc")String wlmc, 
			@Param("stime")String stime,
			@Param("etime")String etime, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 围栏进出分析 -- 详细
	 * @param type
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getWlDetail")
	public List<Map<String, Object>> getWlDetail(
			@Param("type")String type, 
			@Param("name")String name, 
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 围栏进出分析  -- 增加围栏
	 * @param type
	 * @param name
	 * @param area
	 * @return
	 */
//	@Insert("insert into TB_TAXI_AREA@TAXILINK113 (AREANAME,TYPE,AREAPOINT) values (#{name},#{type},#{area})")
	@Insert("insert into TB_TAXI_AREA (AREANAME,TYPE,AREAPOINT) values (#{name},#{type},#{area})")
	public int insertwl(
			@Param("type")String type,
			@Param("name")String name,
			@Param("area")String area);
	
	/**
	 * 
	 * @param vehi
	 * @return
	 */
//	@Delete("delete from TB_TAXI_AREA@TAXILINK113 where id = #{id}")
	@Delete("delete from TB_TAXI_AREA where id = #{id}")
	public int wldel(@Param("id") String id);

	
	
	/**
	 * 车载设备故障查询
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param gz
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
//	@SelectProvider(type = getJbxx.class, method = "getCzsbgzcx")
//	public List<Map<String, Object>> getCzsbgzcx(
//			@Param("cph")String cph, 
//			@Param("xm")String xm, 
//			@Param("yc")String yc, 
//			@Param("gz")String gz, 
//			@Param("stime")String stime, 
//			@Param("etime")String etime,
//			@Param("pageIndex")Integer pageIndex, 
//			@Param("pageSize")Integer pageSize);
	
	@SelectProvider(type = getJbxx.class, method = "getCzsbgzcx")
	public List<Map<String, Object>> getCzsbgzcx(
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("comp")String comp, 
			@Param("gz")String gz, 
			@Param("stime")String stime, 
			@Param("etime")String etime,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 车载设备故障查询导出
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param gz
	 * @param stime
	 * @param etime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getCzsbgzcxxlsx")
//	public List<Map<String, Object>> getCzsbgzcxxlsx(
//			@Param("cph")String cph, 
//			@Param("xm")String xm, 
//			@Param("yc")String yc, 
//			@Param("gz")String gz, 
//			@Param("stime")String stime, 
//			@Param("etime")String etime);
	
	public List<Map<String, Object>> getCzsbgzcxxlsx(
			@Param("cph")String cph, 
			@Param("xm")String xm, 
			@Param("comp")String comp, 
			@Param("gz")String gz, 
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	
	
	
	
	/**
	 * 车载设备故障统计
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param stime
	 * @param etime
	 * @return
	 */
//	@SelectProvider(type = getJbxx.class, method = "getCzsbgztj")
//	public List<Map<String, Object>> getCzsbgztj(
//			@Param("cph")String cph, 
//			@Param("xm")String xm, 
//			@Param("yc")String yc, 
//			@Param("stime")String stime, 
//			@Param("etime")String etime);
	
	@SelectProvider(type = getJbxx.class, method = "getCzsbgztj")
	public List<Map<String, Object>> getCzsbgztj(
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	
	
	/**
	 * 故障查询
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param stime
	 * @param etime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getTscx")
	public List<Map<String, Object>> getTscx(
			@Param("lx")String lx, 
			@Param("stime")String stime, 
			@Param("etime")String etime,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 车载设备故障查询导出
	 * @param cph
	 * @param xm
	 * @param yc
	 * @param gz
	 * @param stime
	 * @param etime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getTscxxlsx")
	public List<Map<String, Object>> getTscxxlsx(
			@Param("lx")String lx, 
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	
	
	/**
	 * 设备故障次数统计
	 * @param cph
	 * @param gz
	 * @param stime
	 * @param etime
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getSbgzcstj")
	public List<Map<String, Object>> getSbgzcstj(
			@Param("cph")String cph, 
			@Param("gz")String gz, 
			@Param("stime")String stime, 
			@Param("etime")String etime,
			@Param("pageIndex")Integer pageIndex, 
			@Param("pageSize")Integer pageSize);
	
	
	/**
	 * 设备故障次数统计导出
	 * @param cph
	 * @param gz
	 * @param stime
	 * @param etime
	 * @return
	 */
	@SelectProvider(type = getJbxx.class, method = "getSbgzcstjxlsx")
	public List<Map<String, Object>> getSbgzcstjxlsx(
			@Param("cph")String cph, 
			@Param("gz")String gz, 
			@Param("stime")String stime, 
			@Param("etime")String etime);
	
	
	/**
	 * 驾驶员信息
	 * @param cph
	 * @return
	 */
	@Select("SELECT * from tb_global_person_certificate where REPLACE(plate_number,'.','')= '${cph}'")
	public List<Map<String, Object>> getJsyxx(@Param("cph")String cph);
	
	/**
	 * 投诉人信息
	 * @param cph
	 * @return
	 */
	@Select("SELECT * from v_global_complaint_12328 where REPLACE(VEHICLE_PLATE_NUMBER,'.','')= '${cph}'")
	public List<Map<String, Object>> getTsrxx(@Param("cph")String cph);
	
	/**
	 * 行政处罚信息
	 * @param cph
	 * @return
	 */
	@Select("SELECT * from V_TW_TAXI_CASE where VEHICLE_PLATE_NUMBER = '${cph}'")
	public List<Map<String, Object>> getXzcfxx(@Param("cph")String cph);
	
	
	
	class getJbxx{
		public String getJsy(@Param("sfzh")String sfzh, 
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("gsm")String gsm, 
				@Param("age")String age, 
				@Param("fwzh")String fwzh, 
				@Param("jyxkz")String jyxkz, 
//				@Param("status")String status, 
				@Param("city")String city, 	
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj = "";
			if(sfzh!=null&&!sfzh.isEmpty()&&!sfzh.equals("null")&&sfzh.length()>0){
				tj += " and b.ID_NUMBER = '"+sfzh+"'";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and ( b.PLATE_NUMBER = '"+cph+"' or REPLACE(b.PLATE_NUMBER,'.','')  = '"+cph+"' ) ";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0){
				tj += " and b.NAME = '"+xm+"'";
			}
			if(gsm!=null&&!gsm.isEmpty()&&!gsm.equals("null")&&gsm.length()>0&&!gsm.equals("全部")){
				tj += " and b.COMPANY_NAME = '"+gsm+"'";
			}
			if(fwzh!=null&&!fwzh.isEmpty()&&!fwzh.equals("null")&&fwzh.length()>0){
				tj += " and b.VEHICLE_ID = '"+fwzh+"'";
			}
			if(jyxkz!=null&&!jyxkz.isEmpty()&&!jyxkz.equals("null")&&jyxkz.length()>0){
				tj += " and b.COMPANY_LICENSE_NUMBER = '"+jyxkz+"'";
			}
//			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0&&!status.equals("全部")){
//				tj += " and b.STATUS_NAME = '"+status+"'";
//			}
			if(city!=null&&!city.isEmpty()&&!city.equals("null")&&city.length()>0&&!city.equals("全部")){
				if(city.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+city+"%'";
				}
			}
			
			if(age!=null&&!age.isEmpty()&&!age.equals("null")&&age.length()>0&&!age.equals("全部")){
				if (age.equals("3(含)至5年")) {
					tj += " and DRIVER_AGE >=3 and DRIVER_AGE <5 ";
				}else if(age.equals("5(含)至10年")) {
					tj += " and DRIVER_AGE >=5 and DRIVER_AGE <10 ";
				}else if(age.equals("10(含)至15年")) {
					tj += " and DRIVER_AGE >=10 and DRIVER_AGE <15 ";
				}else if(age.equals("15(含)至20年")) {
					tj += " and DRIVER_AGE >=16 and DRIVER_AGE <20 ";
				}else if(age.equals("20年(含)以上")) {
					tj += " and DRIVER_AGE >=20 ";
				}
			}
			
			String sql = "select (select count(*) COUNT from (select t.*,ti.ASSESS_SCORE,ti.ASSESS_YEAR from (select "
					+ " b.* from (select gpc.* from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ ") b where 1=1 and id_number is not null and (id,id_number) in (select max(id),id_number from"
					+ " (select gpc.id,gpc.id_number from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ ") c group by id_number) ";
			sql += tj;
			sql += " ) t left join (select ti.ASSESS_SCORE,ti.ASSESS_YEAR,ti.id_number from TB_TAXI_INTEGRITY_INFO_OUT ti "
					+ "where (ti.id_number,ti.ASSESS_YEAR) in (select id_number,max(ti.ASSESS_YEAR) from TB_TAXI_INTEGRITY_INFO_OUT ti  group by id_number)) ti  "
					+ "on ti.id_number=t.id_number " ;
			sql+=")c ) as count, tt.* from (select t.*,ti.ASSESS_SCORE,ti.ASSESS_YEAR from (select"
					+ " b.* from (select gpc.* from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ ") b where 1=1 and id_number is not null and (id,id_number) in (select max(id),id_number from"
					+ " (select gpc.id,gpc.id_number from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ " ) c group by id_number) ";
			sql += tj;
			sql += " ) t left join (select ti.ASSESS_SCORE,ti.ASSESS_YEAR,ti.id_number from TB_TAXI_INTEGRITY_INFO_OUT ti "
					+ "where (ti.id_number,ti.ASSESS_YEAR) in (select id_number,max(ti.ASSESS_YEAR) from TB_TAXI_INTEGRITY_INFO_OUT ti group by id_number)) ti  "
					+ "on ti.id_number=t.id_number " 
					+ ") tt ";
			sql +=" order by PLATE_NUMBER limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		
		public String getJsyxlsx(@Param("sfzh")String sfzh, 
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("gsm")String gsm, 
				@Param("age")String age, 
				@Param("fwzh")String fwzh, 
				@Param("jyxkz")String jyxkz, 
//				@Param("status")String status, 
				@Param("city")String city){
			String tj = "";
			if(sfzh!=null&&!sfzh.isEmpty()&&!sfzh.equals("null")&&sfzh.length()>0){
				tj += " and b.ID_NUMBER = '"+sfzh+"'";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and ( b.PLATE_NUMBER = '"+cph+"' or REPLACE(b.PLATE_NUMBER,'.','')  = '"+cph+"' ) ";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0){
				tj += " and b.NAME = '"+xm+"'";
			}
			if(gsm!=null&&!gsm.isEmpty()&&!gsm.equals("null")&&gsm.length()>0&&!gsm.equals("全部")){
				tj += " and b.COMPANY_NAME = '"+gsm+"'";
			}
			if(fwzh!=null&&!fwzh.isEmpty()&&!fwzh.equals("null")&&fwzh.length()>0){
				tj += " and b.VEHICLE_ID = '"+fwzh+"'";
			}
			if(jyxkz!=null&&!jyxkz.isEmpty()&&!jyxkz.equals("null")&&jyxkz.length()>0){
				tj += " and b.COMPANY_LICENSE_NUMBER = '"+jyxkz+"'";
			}
//			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0&&!status.equals("全部")){
//				tj += " and b.STATUS_NAME = '"+status+"'";
//			}
			if(city!=null&&!city.isEmpty()&&!city.equals("null")&&city.length()>0&&!city.equals("全部")){
				if(city.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+city+"%'";
				}
			}
			
			if(age!=null&&!age.isEmpty()&&!age.equals("null")&&age.length()>0&&!age.equals("全部")){
				if (age.equals("3(含)至5年")) {
					tj += " and DRIVER_AGE >=3 and DRIVER_AGE <5 ";
				}else if(age.equals("5(含)至10年")) {
					tj += " and DRIVER_AGE >=5 and DRIVER_AGE <10 ";
				}else if(age.equals("10(含)至15年")) {
					tj += " and DRIVER_AGE >=10 and DRIVER_AGE <15 ";
				}else if(age.equals("15(含)至20年")) {
					tj += " and DRIVER_AGE >=16 and DRIVER_AGE <20 ";
				}else if(age.equals("20年(含)以上")) {
					tj += " and DRIVER_AGE >=20 ";
				}
			}
			
			String sql = "select t.*,ti.ASSESS_SCORE,ti.ASSESS_YEAR from (select"
					+ " b.* from (select gpc.* from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ ") b where 1=1 and id_number is not null and (id,id_number) in (select max(id),id_number from"
					+ " (select gpc.* from"
//					+ " tb_global_vehicle gv,"
					+ " TB_GLOBAL_PERSON_CERTIFICATE gpc force INDEX(plate_number)"
//					+ " where REPLACE(gpc.plate_number,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND gpc.plate_number LIKE '浙A%'"
					+ ") c group by id_number) ";
			sql += tj;
			sql += " ) t left join (select ti.ASSESS_SCORE,ti.ASSESS_YEAR,ti.id_number from TB_TAXI_INTEGRITY_INFO_OUT ti "
					+ "where (ti.id_number,ti.ASSESS_YEAR) in (select id_number,max(ti.ASSESS_YEAR) from TB_TAXI_INTEGRITY_INFO_OUT ti  group by id_number)) ti  "
					+ "on ti.id_number=t.id_number ";
			System.out.println(sql);
			return sql;
		}
		
		
		
		public String getCl(
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("yszh")String yszh, 
				@Param("jyxkzh")String jyxkzh, 
				@Param("age")String age, 
				@Param("type")String type, 
//				@Param("status")String status, 
				@Param("city")String city, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("请选择车牌号码")&&!cph.equals("全部")){
				tj += " and b.PLATE_NUMBER = '"+cph+"'";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("请选择业户名称")&&!xm.equals("全部")){
				tj += " and b.COMPANY_NAME = '"+xm+"'";
			}
			if(city!=null&&!city.isEmpty()&&!city.equals("null")&&city.length()>0&&!city.equals("全部")){
				if(city.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+city+"%'";
				}
			}
			if(yszh!=null&&!yszh.isEmpty()&&!yszh.equals("null")&&yszh.length()>0){
				tj += " and b.LICENSE_NUMBER = '"+yszh+"'";
			}
			if(jyxkzh!=null&&!jyxkzh.isEmpty()&&!jyxkzh.equals("null")&&jyxkzh.length()>0){
				tj += " and b.COMPANY_LICENSE_NUMBER = '"+jyxkzh+"'";
			}
			
			if(age!=null&&!age.isEmpty()&&!age.equals("null")&&age.length()>0&&!age.equals("请选择车牌号码")&&!age.equals("全部")){
				if(age.equals("0(含)至1年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) < 365 ";
				}else if(age.equals("1(含)至2年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 365 and "+
						 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 2 * 365 ";
				}else if(age.equals("2(含)至3年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 2 * 365 and "+
							 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 3 * 365 ";
				}else if(age.equals("3(含)至4年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 3 * 365 and "+
							 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 4 * 365 ";
				}else if(age.equals("4(含)年以上")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 4 * 365  ";
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0&&!type.equals("请选择车牌号码")&&!type.equals("全部")){
				tj += " and b.brand like '%"+type+"%'";
			}
//			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0&&!status.equals("全部")){
//				tj += " and b.STATUS_NAME = '"+status+"'";
//			}
			String sql = "select (select count(*) COUNT from (select * from (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.* from (select * from (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') b where 1=1";
			sql += tj;
			sql += " ) t ) tt order by PLATE_NUMBER";
			sql +=" limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		public String getClxlsx(
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("yszh")String yszh, 
				@Param("jyxkzh")String jyxkzh, 
				@Param("age")String age, 
				@Param("type")String type, 
//				@Param("status")String status, 
				@Param("city")String city){
			String tj="";
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("请选择车牌号码")&&!cph.equals("全部")){
				tj += " and b.PLATE_NUMBER = '"+cph+"'";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("请选择业户名称")&&!xm.equals("全部")){
				tj += " and b.COMPANY_NAME = '"+xm+"'";
			}
			if(city!=null&&!city.isEmpty()&&!city.equals("null")&&city.length()>0&&!city.equals("全部")){
				if(city.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+city+"%'";
				}
			}
			if(yszh!=null&&!yszh.isEmpty()&&!yszh.equals("null")&&yszh.length()>0){
				tj += " and b.LICENSE_NUMBER = '"+yszh+"'";
			}
			if(jyxkzh!=null&&!jyxkzh.isEmpty()&&!jyxkzh.equals("null")&&jyxkzh.length()>0){
				tj += " and b.COMPANY_LICENSE_NUMBER = '"+jyxkzh+"'";
			}
			
			if(age!=null&&!age.isEmpty()&&!age.equals("null")&&age.length()>0&&!age.equals("请选择车牌号码")&&!age.equals("全部")){
				if(age.equals("0(含)至1年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) < 365 ";
				}else if(age.equals("1(含)至2年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 365 and "+
						 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 2 * 365 ";
				}else if(age.equals("2(含)至3年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 2 * 365 and "+
							 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 3 * 365 ";
				}else if(age.equals("3(含)至4年")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 3 * 365 and "+
							 "(to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d'))) < 4 * 365 ";
				}else if(age.equals("4(含)年以上")){
					tj += " and to_days(now()) - to_days(str_to_date(date_format(PURCHASE_DATE, '%Y-%m-%d'), '%Y-%m-%d')) >= 4 * 365  ";
				}
			}
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0&&!type.equals("请选择车牌号码")&&!type.equals("全部")){
				tj += " and b.brand like '%"+type+"%'";
			}
//			if(status!=null&&!status.isEmpty()&&!status.equals("null")&&status.length()>0&&!status.equals("全部")){
//				tj += " and b.STATUS_NAME = '"+status+"'";
//			}
			String sql = "select t.* from (select * from (select * from tb_global_vehicle where industry=090 and business_scope_code = 1400  AND STATUS_NAME='营运' AND PLATE_NUMBER LIKE '浙A%') b where 1=1";
			sql += tj;
			sql += " ) t order by PLATE_NUMBER";
			System.out.println(sql);
			return sql;
		}
		
		public String getWz(
				@Param("stratTime")String stratTime, 
				@Param("endTime")String endTime,
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("yszh")String yszh, 
				@Param("jyxkzh")String jyxkzh, 
				@Param("area")String area,
				@Param("part")String part, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stratTime!=null&&!stratTime.isEmpty()&&!stratTime.equals("null")&&stratTime.length()>0){
				tj += " and b.ILLEGAL_TIME >=str_to_date('"+stratTime+"','%Y-%m-%d')";	
			}
			if(endTime!=null&&!endTime.isEmpty()&&!endTime.equals("null")&&endTime.length()>0){
				tj += " and b.ILLEGAL_TIME <=str_to_date('"+endTime+"','%Y-%m-%d')";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0){
				tj += " and replace(b.PARTY_NAME,' ','') = '"+xm+"'";
			}


			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and ( b.AUTO_NUM = '"+cph+"' or REPLACE(b.AUTO_NUM,'.','')  = '"+cph+"' ) ";
			}


			if(yszh!=null&&!yszh.isEmpty()&&!yszh.equals("null")&&yszh.length()>0){
				tj += " and b.CERTI_NUM = '"+yszh+"'";
			}
			if(jyxkzh!=null&&!jyxkzh.isEmpty()&&!jyxkzh.equals("null")&&jyxkzh.length()>0){
				tj += " and b.LIENCE_NUM = '"+jyxkzh+"'";
			}
			if(area!=null&&!area.isEmpty()&&!area.equals("")&&area.length()>0&&!area.equals("全部")){
				tj += " and b.AREA = '"+area+"'";
			}
			if(part!=null&&!part.isEmpty()&&!part.equals("")&&part.length()>0&&!part.equals("全部")){
				tj += " and b.ORGAN = '"+part+"'";
			}

			String sql = "select (select count(*) COUNT from (select * from (select ti.* from tb_global_vehicle gv,TB_TAXI_ILLEGAL_INFO_OUT ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select b.* from (select ti.* from tb_global_vehicle gv,TB_TAXI_ILLEGAL_INFO_OUT ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt order by tt.ILLEGAL_TIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		
		public String getWzxlsx(
				@Param("stratTime")String stratTime, 
				@Param("endTime")String endTime,
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("yszh")String yszh, 
				@Param("jyxkzh")String jyxkzh, 
				@Param("area")String area,
				@Param("part")String part){
			String tj="";
			if(stratTime!=null&&!stratTime.isEmpty()&&!stratTime.equals("null")&&stratTime.length()>0){
				tj += " and b.ILLEGAL_TIME >=str_to_date('"+stratTime+"','%Y-%m-%d')";	
			}
			if(endTime!=null&&!endTime.isEmpty()&&!endTime.equals("null")&&endTime.length()>0){
				tj += " and b.ILLEGAL_TIME <=str_to_date('"+endTime+"','%Y-%m-%d')";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0){
				tj += " and replace(b.PARTY_NAME,' ','') = '"+xm+"'";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and ( b.AUTO_NUM = '"+cph+"' or REPLACE(b.AUTO_NUM,'.','')  = '"+cph+"' ) ";
			}
			if(yszh!=null&&!yszh.isEmpty()&&!yszh.equals("null")&&yszh.length()>0){
				tj += " and b.CERTI_NUM = '"+yszh+"'";
			}
			if(jyxkzh!=null&&!jyxkzh.isEmpty()&&!jyxkzh.equals("null")&&jyxkzh.length()>0){
				tj += " and b.LIENCE_NUM = '"+jyxkzh+"'";
			}
			if(area!=null&&!area.isEmpty()&&!area.equals("")&&area.length()>0&&!area.equals("全部")){
				tj += " and b.AREA = '"+area+"'";
			}
			if(part!=null&&!part.isEmpty()&&!part.equals("")&&part.length()>0&&!part.equals("全部")){
				tj += " and b.ORGAN = '"+part+"'";
			}

			String sql = "select b.* from (select ti.* from tb_global_vehicle gv,TB_TAXI_ILLEGAL_INFO_OUT ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%') b where 1=1 ";
			sql += tj;
			return sql;
		}
		
		public String getQy(
				@Param("name")String name, 
				@Param("area")String area,
				@Param("style")String style, 
				@Param("min")String min,
				@Param("max")String max,
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(name!=null&&!name.isEmpty()&&!name.equals("null")&&name.length()>0&&!name.equals("全部")){
				tj += " and b.NAME like '%"+name+"%'";
			}
			if(area!=null&&!area.isEmpty()&&!area.equals("null")&&area.length()>0&&!area.equals("全部")){
				if(area.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+area+"%'";
				}
			}
			if(style!=null&&!style.isEmpty()&&!style.equals("null")&&style.length()>0&&!style.equals("全部")){
				if(style.equals("业户")){
					tj += " and b.ECONOMIC_NAME not in ('个体','私营')";
				}else if(style.equals("个体")){
					tj += " and b.ECONOMIC_NAME in ('个体','私营') ";
				}
			}
			if(min!=null&&!min.isEmpty()&&!min.equals("null")&&min.length()>0){
				tj += " and  CAST(b.VEHICLE_SUM AS SIGNED) >= "+min+"";
			}
			if(max!=null&&!max.isEmpty()&&!max.equals("null")&&max.length()>0){
				tj += " and CAST(b.VEHICLE_SUM AS SIGNED) <= "+max+"";
			}
			String sql = "select (select count(*) COUNT from (select * from (select gv.* from tb_global_company gv ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select"
					+ " b.* from (select gv.* from tb_global_company gv ) b where 1=1 ";
			sql += tj;
			sql += "  order by CAST(b.VEHICLE_SUM AS SIGNED) desc) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}
		
		public String getQyxlsx(
				@Param("name")String name, 
				@Param("area")String area,
				@Param("style")String style, 
				@Param("min")String min,
				@Param("max")String max){
			String tj="";
			if(name!=null&&!name.isEmpty()&&!name.equals("null")&&name.length()>0&&!name.equals("全部")){
				tj += " and b.NAME = '"+name+"'";
			}
			if(area!=null&&!area.isEmpty()&&!area.equals("null")&&area.length()>0&&!area.equals("全部")){
				if(area.equals("主城区")){
					tj += " and (b.AREA_NAME like '%上城%' or b.AREA_NAME like '%下城%' "
							+ "or b.AREA_NAME like '%江干%' or b.AREA_NAME like '%拱墅%' "
							+ "or b.AREA_NAME like '%下沙%' "
							+ "or b.AREA_NAME like '%西湖%' or b.AREA_NAME like '%滨江%')";
				} else {
					tj += " and b.AREA_NAME like '%"+area+"%'";
				}
			}
			if(style!=null&&!style.isEmpty()&&!style.equals("null")&&style.length()>0&&!style.equals("全部")){
				if(style.equals("业户")){
					tj += " and b.ECONOMIC_NAME not in ('个体','私营')";
				}else if(style.equals("个体")){
					tj += " and b.ECONOMIC_NAME in ('个体','私营') ";
				}
			}
			if(min!=null&&!min.isEmpty()&&!min.equals("null")&&min.length()>0){
				tj += " and CAST(b.VEHICLE_SUM AS SIGNED) >= "+min+"";
			}
			if(max!=null&&!max.isEmpty()&&!max.equals("null")&&max.length()>0){
				tj += " and CAST(b.VEHICLE_SUM AS SIGNED) <= "+max+"";
			}
			String sql = "select b.* from (select gv.* from tb_global_company gv ) b where 1=1 ";
			sql += tj;
			sql += " order by CAST(b.VEHICLE_SUM AS SIGNED) desc";
			return sql;
		}
		
		
		public String getCzsb(
				@Param("cph")String cph, 
				@Param("yh")String yh, 
				@Param("zdlx")String zdlx, 
				@Param("zdbh")String zdbh, 
				@Param("pageIndex")Integer pageIndex,
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and b.VEHI_NO like '%"+cph+"%'";
			}
			if(yh!=null&&!yh.isEmpty()&&!yh.equals("null")&&yh.length()>0&&!yh.equals("全部")){
				tj += " and b.BA_NAME like '%"+yh+"%'";
			}
			if(zdlx!=null&&!zdlx.isEmpty()&&!zdlx.equals("null")&&zdlx.length()>0){
				tj += " and b.MDT_SUB_TYPE like '"+zdlx+"%'";
			}
			if(zdbh!=null&&!zdbh.isEmpty()&&!zdbh.equals("null")&&zdbh.length()>0){
				tj += " and b.MDT_NO like '%"+zdbh+"%'";
			}
			String sql = "select (select count(*) COUNT from (select * from (select gv.* from VW_VEHICLE gv ) b where 1=1 ";
			sql += tj;
			sql+=")c) as count, tt.* from (select t.*  from (select  b.* from (select gv.* from  VW_VEHICLE gv ) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}
		
		public String getCzsbcxxlsx(
				@Param("cph")String cph, 
				@Param("yh")String yh, 
				@Param("zdlx")String zdlx, 
				@Param("zdbh")String zdbh){
			String tj="";
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and b.VEHI_NO like '%"+cph+"%'";
			}
			if(yh!=null&&!yh.isEmpty()&&!yh.equals("null")&&yh.length()>0&&!yh.equals("全部")){
				tj += " and b.BA_NAME like '%"+yh+"%'";
			}
			if(zdlx!=null&&!zdlx.isEmpty()&&!zdlx.equals("null")&&zdlx.length()>0){
				tj += " and b.MDT_SUB_TYPE like '"+zdlx+"%'";
			}
			if(zdbh!=null&&!zdbh.isEmpty()&&!zdbh.equals("null")&&zdbh.length()>0){
				tj += " and b.MDT_NO like '%"+zdbh+"%'";
			}
			String sql = "select b.* from (select gv.* from VW_VEHICLE gv ) b where 1=1 ";
			sql += tj;
			return sql;
		}
		
//		public String getGzsb(
//				@Param("cph")String cph, 
//				@Param("yh")String yh, 
//				@Param("zdlx")String zdlx, 
//				@Param("zdbh")String zdbh, 
//				@Param("pageIndex")Integer pageIndex,
//				@Param("pageSize")Integer pageSize){
//			String tj="";
//			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
//				tj += " and b.VEHI_NO = '"+cph+"'";
//			}
//			if(yh!=null&&!yh.isEmpty()&&!yh.equals("null")&&yh.length()>0&&!yh.equals("全部")){
//				tj += " and b.COMP_NAME = '"+yh+"'";
//			}
//			if(zdlx!=null&&!zdlx.isEmpty()&&!zdlx.equals("null")&&zdlx.length()>0){
//				tj += " and b.MDT_SUB_TYPE like '%"+zdlx+"%'";
//			}
//			if(zdbh!=null&&!zdbh.isEmpty()&&!zdbh.equals("null")&&zdbh.length()>0){
//				tj += " and b.MDT_NO like '%"+zdbh+"%'";
//			}
//			tj += ""  ;
//			String sql = "select (select count(*) COUNT from (select * from (select gv.* from TB_VEHICLE_FAULT gv ) b where 1=1 ";
//			sql += tj;
//			sql+=")) as count, tt.* from (select t.*  from (select"
//					+ " b.* from (select gv.* from TB_VEHICLE_FAULT gv ) b where 1=1 ";
//			sql += tj;
//			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
//			return sql;
//		}
//		
//		public String getGzsbxlsx(
//				@Param("cph")String cph, 
//				@Param("yh")String yh, 
//				@Param("zdlx")String zdlx, 
//				@Param("zdbh")String zdbh){
//			String tj="";
//			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
//				tj += " and b.VEHI_NO = '"+cph+"'";
//			}
//			if(yh!=null&&!yh.isEmpty()&&!yh.equals("null")&&yh.length()>0&&!yh.equals("全部")){
//				tj += " and b.COMP_NAME = '"+yh+"'";
//			}
//			if(zdlx!=null&&!zdlx.isEmpty()&&!zdlx.equals("null")&&zdlx.length()>0){
//				tj += " and b.MDT_SUB_TYPE like '%"+zdlx+"%'";
//			}
//			if(zdbh!=null&&!zdbh.isEmpty()&&!zdbh.equals("null")&&zdbh.length()>0){
//				tj += " and b.MDT_NO like '%"+zdbh+"%'";
//			}
//			String sql = "select b.* from (select gv.* from TB_VEHICLE_FAULT gv ) b where 1=1 ";
//			sql += tj;
//			return sql;
//		}
//		
		
		
		public String getYsrlfx(
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("cphm")String cphm, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.s_time >= str_to_date('"+stime+"', '%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.s_time <= str_to_date('"+etime+"', '%Y-%m-%d %H:%i:%s')";
			}
			if(cphm!=null&&!cphm.isEmpty()&&!cphm.equals("null")&&cphm.length()>0&&!cphm.equals("全部")){
				tj += " and b.vhic ='"+cphm+"'";
			}
			String sql = "select (select count(*) COUNT from (select * from (select gv.* from TB_TAXI_PRIVIDE gv ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select b.* from (select gv.* from TB_TAXI_PRIVIDE gv ) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		
		public String getYyycfx(
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("cphm")String cphm, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.s_time >= str_to_date('"+stime+"', '%Y-%m-%d %H:%i:%s')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.s_time <= str_to_date('"+etime+"', '%Y-%m-%d %H:%i:%s')";
			}
			if(cphm!=null&&!cphm.isEmpty()&&!cphm.equals("null")&&cphm.length()>0&&!cphm.equals("全部")){
				tj += " and b.vhic ='"+cphm+"'";
			}
			String sql = "select (select count(*) COUNT from (select * from (select gv.* from TB_TAXI_PRIVIDE gv ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select"
					+ " b.* from (select gv.* from TB_TAXI_PRIVIDE gv ) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}
		
		public String getYylcycfx(
				@Param("maxMileage")Integer maxMileage,
				@Param("minMileage")Integer minMileage, 
				@Param("startTime")String startTime){
			String date = startTime.replaceAll("-", "").substring(0,6);
			String tj= "case when v.s >= 0 and v.s < "+minMileage+" then '0-"+minMileage+"' "
						+ " when v.s >= "+minMileage+" and v.s < "+maxMileage+" then '"+minMileage+"-"+maxMileage+"' "
						+ " else '"+maxMileage+"以上' end";
			String sql = "select ";
			sql += tj;
			sql+=" as ti,count(v.cphm) as count from (select sum(t.szlc) as s, t.cphm from JJQ_TJ_"+date+"_DAY t"
					+ " where date_format(t.jsyysj, '%Y-%m-%d') =  '"+startTime+"' group by t.cphm) v group by ";
			sql += tj;
			sql += " order by ti";
			return sql;
		}
		
		public String getYylcycfxTwo(
				@Param("maxMileage")Integer maxMileage,
				@Param("minMileage")Integer minMileage, 
				@Param("startTime")String startTime){
			String date = startTime.replaceAll("-", "").substring(0,6);
			String tj= "";
			if(maxMileage!=null&&!maxMileage.equals("null")&&maxMileage>0){
				tj += " and a.s < "+maxMileage;
			}
			if(minMileage!=null&&!minMileage.equals("null")&&minMileage>=0){
				tj += " and a.s >= "+minMileage;
			}
			String sql = "select * from (select sum(t.szlc) as S,t.CPHM ,t.DAY,t.ZGS,t.FGS from JJQ_TJ_"+date+"_DAY t"
					+" where date_format(t.jsyysj ,'%Y-%m-%d') = '"+startTime+"' group by t.cphm,t.day,t.zgs,t.fgs) a where 1=1 ";
			sql+=tj;
			return sql;
		}
		
		public String getYydcycfx(
				@Param("maxMileage")Integer maxMileage,
				@Param("minMileage")Integer minMileage, 
				@Param("startTime")String startTime){
			String date = startTime.replaceAll("-", "").substring(0,6);
			String tj= "case when v.c >= 0 and v.c < "+minMileage+" then '0-"+minMileage+"' "
						+ " when v.c >= "+minMileage+" and v.c < "+maxMileage+" then '"+minMileage+"-"+maxMileage+"' "
						+ " else '"+maxMileage+"以上' end";
			String sql = "select ";
			sql += tj;
			sql+=" as ti,count(v.cphm) as count from (select count(t.cphm) c,t.cphm from JJQ_TJ_"+date+"_DAY t"
					+ " where date_format(t.jsyysj, '%Y-%m-%d') =  '"+startTime+"' group by t.cphm ) v group by ti";
//			sql += tj;
			sql += " order by ti";
			return sql;
		}
		
		
		public String getYydcycfxTwo(
				@Param("maxMileage")Integer maxMileage,
				@Param("minMileage")Integer minMileage, 
				@Param("startTime")String startTime){
			String date = startTime.replaceAll("-", "").substring(0,6);
			String tj= "";
			if(maxMileage!=null&&!maxMileage.equals("null")&&maxMileage>0){
				tj += " and v.c < "+maxMileage;
			}
			if(minMileage!=null&&!minMileage.equals("null")&&minMileage>=0){
				tj += " and v.c >= "+minMileage;
			}
			String sql = "select * from (select count(t.cphm) as C, t.CPHM,t.DAY,t.ZGS,t.FGS from JJQ_TJ_"+date+"_DAY t"
					+" where date_format(t.jsyysj ,'%Y-%m-%d') = '"+startTime+"' group by t.cphm ,t.day,t.zgs,t.fgs) v where 1=1 ";
			sql+=tj;
			return sql;
		}
		
		
		public String getXny(
				@Param("type")String type, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(type!=null&&!type.isEmpty()&&!type.equals("null")&&type.length()>0){
				if(type.equals("0")){
				}else if (type.equals("1")) {
					tj +=  " and (b.FUEL_NAME = '双燃料(汽加气)' or b.FUEL_NAME = '双燃料(柴加气)' or b.FUEL_NAME = '双燃料(电加气)' or b.FUEL_NAME = '电动' or b.FUEL_NAME = '插电式混合动力')";
				}else if (type.equals("2")) {
					tj +=  " and (b.FUEL_NAME = '汽油' or b.FUEL_NAME = '柴油' or b.FUEL_NAME = '非插电式混合动力' or b.FUEL_NAME = '液化石油气（LPG）' or b.FUEL_NAME = '液化天然气（LNG）')";
				}
			}
			String sql = "select (select count(*) COUNT from (select * from (select  t.FUEL_NAME,t.BRAND,t.COLOR,t.PLATE_NUMBER from tb_global_vehicle t where t.industry=090 and t.business_scope_code = 1400  AND t.STATUS_NAME='营运' and t.plate_number LIKE '浙A%' and t.fuel_name is not null order by fuel_name desc ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select b.* from (select  t.FUEL_NAME,t.BRAND,t.COLOR,t.PLATE_NUMBER from tb_global_vehicle t where t.industry=090 and t.business_scope_code = 1400  AND t.STATUS_NAME='营运' and t.plate_number LIKE '浙A%' and t.fuel_name is not null order by fuel_name desc) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}
		
		
		public String getWl(
				@Param("wlmc")String wlmc, 
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.time >= str_to_date('"+stime+"', '%Y-%m-%d')";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.time <= str_to_date('"+etime+"', '%Y-%m-%d')";
			}
			if(wlmc!=null&&!wlmc.isEmpty()&&!wlmc.equals("null")&&wlmc.length()>0&&!wlmc.equals("全部")){
				tj += " and b.areaname like '%"+wlmc+"%'";
			}
			String sql = "select (select count(*) counts from (select * from (select gv.* from TB_TAXI_AREA gv ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as counts, tt.* from (select t.*  from (select"
					+ " b.* from (select gv.* from TB_TAXI_AREA gv ) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		
		public String getWlDetail(
				@Param("type")String type, 
				@Param("name")String name, 
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj = " and b.JZ_TYPE = '"+type+"' and b.areaname = '"+name+"'";
			String sql = "select (select count(*) count from (select * from (select g.*,gv.AREANAME from TB_TAXI_AREA gv, TB_TAXI_AREA_ALARM g where g.area_id = gv.id ) b where 1=1 ";
			sql += tj;
			sql+=")c ) as count, tt.* from (select t.*  from (select b.* from (select g.*,gv.AREANAME from TB_TAXI_AREA gv, TB_TAXI_AREA_ALARM g where g.area_id = gv.id  ) b where 1=1 ";
			sql += tj;
			sql += " ) t ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}
		
		

//		public String getCzsbgzcx(
//				@Param("cph")String cph, 
//				@Param("xm")String xm, 
//				@Param("yc")String yc, 
//				@Param("gz")String gz, 
//				@Param("stime")String stime, 
//				@Param("etime")String etime,
//				@Param("pageIndex")Integer pageIndex, 
//				@Param("pageSize")Integer pageSize){
//			String date = stime.replaceAll("-", "").substring(2,6);
//			String tj = "";
//			if(yc!=null&&!yc.isEmpty()&&!yc.equals("null")&&yc.length()>0&&!yc.equals("全部")){
//				if (yc.equals("主机故障")) {
//					tj += " and ( b.LOW_VOLTAGE = 1 or b.NO_POWER = 1 or b.NO_GPS = 1 or b.NO_UPLOAD = 1)";
//				}else if (yc.equals("定位故障")) {
//					tj += " and (b.MOD_FAULT = 1 or b.ANT_FAULT = 1 or b.INEXACT = 1)";
//				}else if (yc.equals("通信故障")) {
//					tj += " and b.COMM_FAULT = 1";
//				}else if (yc.equals("定位回传故障")) {
//					tj += " and (b.GPS_OVER_BACK = 1 or b.GPS_NO_BACK = 1)";
//				}else if (yc.equals("摄像头故障")) {
//					tj += " and (b.CAM_OCCLUSION = 1 or b.CAM_NOSIGN = 1)";
//				}else if (yc.equals("视频主机/存储故障")) {
//					tj += " and ( b.HD_FAULT = 1 or b.SD_FAULT = 1 or b.VD_FAULT = 1 or b.VD_EX_FAULT = 1)";
//				}else if (yc.equals("计价器断开故障")) {
//					tj += " and b.METER_DISCONN = 1";
//				}else if (yc.equals("导航屏断开故障")) {
//					tj += " and b.NAV_DISCONN = 1";
//				}else if (yc.equals("空车灯故障")) {
//					tj += " and (b.ST_NO_CHG = 1 or b.ST_OVER_CHG = 1)";
//				}
//			}
//			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0&&!gz.equals("全部")){
//				if (gz.equals("终端主电源欠压")) {
//					tj += " and b.LOW_VOLTAGE = 1";
//				}else if (gz.equals("主电源掉电")) {
//					tj += " and b.NO_POWER = 1";
//				}else if (gz.equals("无定位数据")) {
//					tj += " and b.NO_GPS = 1";
//				}else if (gz.equals("无数据上传")) {
//					tj += " and b.NO_UPLOAD = 1";
//					
//					
//				}else if (gz.equals("定位模块故障")) {
//					tj += " and b.MOD_FAULT = 1";
//				}else if (gz.equals("天线短路")) {
//					tj += " and b.ANT_FAULT = 1";
//				}else if (gz.equals("非精确定位")) {
//					tj += " and b.INEXACT = 1";
//					
//					
//				}else if (gz.equals("通讯故障")) {
//					tj += " and b.COMM_FAULT = 1";
//					
//				}else if (gz.equals("定位回传过密")) {
//					tj += " and b.GPS_OVER_BACK = 1";
//				}else if (gz.equals("回传数据丢失")) {
//					tj += " and b.GPS_NO_BACK = 1";
//					
//				}else if (gz.equals("摄像头遮挡")) {
//					tj += " and b.CAM_OCCLUSION = 1";
//				}else if (gz.equals("摄像头信号丢失")) {
//					tj += " and b.CAM_NOSIGN = 1";
//					
//					
//				}else if (gz.equals("硬盘故障")) {
//					tj += " and b.HD_FAULT = 1";
//				}else if (gz.equals("SD卡故障")) {
//					tj += " and b.SD_FAULT = 1";
//				}else if (gz.equals("视频主机故障")) {
//					tj += " and b.VD_FAULT = 1";
//				}else if (gz.equals("视频拓展故障")) {
//					tj += " and b.VD_EX_FAULT = 1";
//					
//				}else if (gz.equals("计价器连接断开")) {
//					tj += " and b.METER_DISCONN = 1";
//					
//				}else if (gz.equals("导航屏断开")) {
//					tj += " and b.NAV_DISCONN = 1";
//					
//				}else if (gz.equals("空重车不变化")) {
//					tj += " and b.ST_NO_CHG = 1";
//				}else if (gz.equals("空重车切换频繁")) {
//					tj += " and b.ST_OVER_CHG = 1";
//				}
//			}
//			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
//				tj += " and b.VEHICLE_NO = '"+cph+"'";
//			}
//			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("全部")){
////				tj += " and b.VEHICLE_NO like '%"+xm+"%'";
//			}
//			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
//				tj += " and b.DBTIME >= str_to_date('"+stime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
//				tj += " and b.DBTIME <= str_to_date('"+etime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			String sql = "select (select count(*) count  from TB_ALARM_HISTORY_"+date+"  b where 1=1 ";
//			sql += tj;
//			sql+=") as count, tt.* from (select b.* from TB_ALARM_HISTORY_"+date+"  b where 1=1 ";
//			sql += tj;
//			sql += " ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
//			System.out.println(sql);
//			return sql;
//		}
		public String getCzsbgzcx(
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("comp")String comp, 
				@Param("gz")String gz, 
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj = "";
			
			System.out.println(gz);
			
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.DB_TIME >= str_to_date('"+stime+"', '%Y-%m-%d') ";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.DB_TIME <= str_to_date('"+etime+"', '%Y-%m-%d') ";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and b.VEHICLE_NO = '"+cph+"'";
			}
			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0){
				if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0){
				    if (gz.equals("有营运无定位")) {
						tj += " and b.NO_GPS = 1";
					}else if (gz.equals("有定位无营运")) {
						tj += " and b.NO_JJQ = 1";
					}else if (gz.equals("有抓拍无定位无营运")) {
						tj += " and b.NO_GPS_JJQ = 1";
					}else if (gz.equals("7天无定位无营运")) {
						tj += " and b.SEVEN_GPS_JJQ = 1";
					}else if (gz.equals("全天空车全天重车")) {
						tj += " and b.EMPTY_HEAVY = 1";
					}else if (gz.equals("视频黑屏")) {
						tj += " and b.SCREEN_BLACK = 1";
					}else if (gz.equals("视频移位")) {
						tj += " and b.MOVE_ON = 1";
					}else if (gz.equals("视频断线")) {
						tj += " and b.BREAK_OFF = 1";
					}else if (gz.equals("全部")){
						tj += " and (b.NO_GPS = 1 or b.NO_JJQ = 1 or NO_GPS_JJQ = 1 or b.SEVEN_GPS_JJQ = 1 or b.EMPTY_HEAVY = 1 or b.SCREEN_BLACK = 1 or b.MOVE_ON = 1 or b.BREAK_OFF = 1) ";
					}
				}
			}
			if(comp!=null&&!comp.isEmpty()&&!comp.equals("null")&&comp.length()>0&&!comp.equals("全部")){
				tj += " and v.COMP_NAME = '"+comp+"'";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("全部")){
//				tj += " and b.VEHICLE_NO like '%"+xm+"%'";
			}
			String sql = "select (select count(*) count  from tb_taxi_gzfx_history  b ,tb_global_vehicle v where 1=1 ";
			sql += tj;
			sql+=" and v.PLATE_NUMBER = b.VEHICLE_NO ) as count, tt.* from (select b.*,v.company_name COMP_NAME from tb_taxi_gzfx_history b ,tb_global_vehicle v where 1 = 1 ";
			sql += tj;
			sql += " and v.PLATE_NUMBER = b.VEHICLE_NO ) tt limit "+((pageIndex-1)*pageSize)+","+pageSize;
			
			System.out.println(sql);
			return sql;
		}
		
//		public String getCzsbgzcxxlsx(
//				@Param("cph")String cph, 
//				@Param("xm")String xm, 
//				@Param("yc")String yc, 
//				@Param("gz")String gz, 
//				@Param("stime")String stime, 
//				@Param("etime")String etime){
//			String date = stime.replaceAll("-", "").substring(2,6);
//			String tj = "";
//			if(yc!=null&&!yc.isEmpty()&&!yc.equals("null")&&yc.length()>0&&!yc.equals("全部")){
//				if (yc.equals("主机故障")) {
//					tj += " and ( b.LOW_VOLTAGE = 1 or b.NO_POWER = 1 or b.NO_GPS = 1 or b.NO_UPLOAD = 1)";
//				}else if (yc.equals("定位故障")) {
//					tj += " and (b.MOD_FAULT = 1 or b.ANT_FAULT = 1 or b.INEXACT = 1)";
//				}else if (yc.equals("通信故障")) {
//					tj += " and b.COMM_FAULT = 1";
//				}else if (yc.equals("定位回传故障")) {
//					tj += " and (b.GPS_OVER_BACK = 1 or b.GPS_NO_BACK = 1)";
//				}else if (yc.equals("摄像头故障")) {
//					tj += " and (b.CAM_OCCLUSION = 1 or b.CAM_NOSIGN = 1)";
//				}else if (yc.equals("视频主机/存储故障")) {
//					tj += " and ( b.HD_FAULT = 1 or b.SD_FAULT = 1 or b.VD_FAULT = 1 or b.VD_EX_FAULT = 1)";
//				}else if (yc.equals("计价器断开故障")) {
//					tj += " and b.METER_DISCONN = 1";
//				}else if (yc.equals("导航屏断开故障")) {
//					tj += " and b.NAV_DISCONN = 1";
//				}else if (yc.equals("空车灯故障")) {
//					tj += " and (b.ST_NO_CHG = 1 or b.ST_OVER_CHG = 1)";
//				}
//			}
//			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0&&!gz.equals("全部")){
//				if (gz.equals("终端主电源欠压")) {
//					tj += " and b.LOW_VOLTAGE = 1";
//				}else if (gz.equals("主电源掉电")) {
//					tj += " and b.NO_POWER = 1";
//				}else if (gz.equals("无定位数据")) {
//					tj += " and b.NO_GPS = 1";
//				}else if (gz.equals("无数据上传")) {
//					tj += " and b.NO_UPLOAD = 1";
//					
//					
//				}else if (gz.equals("定位模块故障")) {
//					tj += " and b.MOD_FAULT = 1";
//				}else if (gz.equals("天线短路")) {
//					tj += " and b.ANT_FAULT = 1";
//				}else if (gz.equals("非精确定位")) {
//					tj += " and b.INEXACT = 1";
//					
//					
//				}else if (gz.equals("通讯故障")) {
//					tj += " and b.COMM_FAULT = 1";
//					
//				}else if (gz.equals("定位回传过密")) {
//					tj += " and b.GPS_OVER_BACK = 1";
//				}else if (gz.equals("回传数据丢失")) {
//					tj += " and b.GPS_NO_BACK = 1";
//					
//				}else if (gz.equals("摄像头遮挡")) {
//					tj += " and b.CAM_OCCLUSION = 1";
//				}else if (gz.equals("摄像头信号丢失")) {
//					tj += " and b.CAM_NOSIGN = 1";
//					
//					
//				}else if (gz.equals("硬盘故障")) {
//					tj += " and b.HD_FAULT = 1";
//				}else if (gz.equals("SD卡故障")) {
//					tj += " and b.SD_FAULT = 1";
//				}else if (gz.equals("视频主机故障")) {
//					tj += " and b.VD_FAULT = 1";
//				}else if (gz.equals("视频拓展故障")) {
//					tj += " and b.VD_EX_FAULT = 1";
//					
//				}else if (gz.equals("计价器连接断开")) {
//					tj += " and b.METER_DISCONN = 1";
//					
//				}else if (gz.equals("导航屏断开")) {
//					tj += " and b.NAV_DISCONN = 1";
//					
//				}else if (gz.equals("空重车不变化")) {
//					tj += " and b.ST_NO_CHG = 1";
//				}else if (gz.equals("空重车切换频繁")) {
//					tj += " and b.ST_OVER_CHG = 1";
//				}
//			}
//			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
//				tj += " and b.VEHICLE_NO = '"+cph+"'";
//			}
//			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("全部")){
////				tj += " and b.VEHICLE_NO like '%"+xm+"%'";
//			}
//			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
//				tj += " and b.DBTIME >= str_to_date('"+stime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
//				tj += " and b.DBTIME <= str_to_date('"+etime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			String sql = "select b.* from (select g.* from TB_ALARM_HISTORY_"+date+" g) b where 1=1 ";
//			sql += tj;
//			return sql;
//		}
		
		public String getCzsbgzcxxlsx(
				@Param("cph")String cph, 
				@Param("xm")String xm, 
				@Param("comp")String comp, 
				@Param("gz")String gz, 
				@Param("stime")String stime, 
				@Param("etime")String etime){
			String tj = "";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.DB_TIME >= str_to_date('"+stime+"', '%Y-%m-%d') ";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.DB_TIME <= str_to_date('"+etime+"', '%Y-%m-%d') ";
			}
			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0&&!gz.equals("全部")){
				if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0){
				    if (gz.equals("有营运无定位")) {
						tj += " and b.NO_GPS = 1";
					}else if (gz.equals("有定位无营运")) {
						tj += " and b.NO_JJQ = 1";
					}else if (gz.equals("有抓拍无定位无营运")) {
						tj += " and b.NO_GPS_JJQ = 1";
					}else if (gz.equals("7天无定位无营运")) {
						tj += " and b.SEVEN_GPS_JJQ = 1";
					}else if (gz.equals("全天空车全天重车")) {
						tj += " and b.EMPTY_HEAVY = 1";
					}else if (gz.equals("视频黑屏")) {
						tj += " and b.SCREEN_BLACK = 1";
					}else if (gz.equals("视频移位")) {
						tj += " and b.MOVE_ON = 1";
					}else if (gz.equals("视频断线")) {
						tj += " and b.BREAK_OFF = 1";
					}else if (gz.equals("全部")){
						tj += " and (b.NO_GPS = 1 or b.NO_JJQ = 1 or NO_GPS_JJQ = 1 or b.SEVEN_GPS_JJQ = 1 or b.EMPTY_HEAVY = 1 or b.SCREEN_BLACK = 1 or b.MOVE_ON = 1 or b.BREAK_OFF = 1) ";
					}
				}
			}else{
				tj += " and (b.NO_GPS = 1 or b.NO_JJQ = 1 or NO_GPS_JJQ = 1 or b.SEVEN_GPS_JJQ = 1 or b.EMPTY_HEAVY = 1 or b.SCREEN_BLACK = 1 or b.MOVE_ON = 1 or b.BREAK_OFF = 1) ";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and b.VEHICLE_NO = '"+cph+"'";
			}
			if(comp!=null&&!comp.isEmpty()&&!comp.equals("null")&&comp.length()>0&&!comp.equals("全部")){
				tj += " and v.COMP_NAME = '"+comp+"'";
			}
			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("全部")){
//				tj += " and b.VEHICLE_NO like '%"+xm+"%'";
			}
			String sql = "select b.*,v.company_name COMP_NAME from tb_taxi_gzfx_history b , tb_global_vehicle v  where 1=1 and v.PLATE_NUMBER = b.VEHICLE_NO";
			sql += tj;
			
			System.out.println(sql);
			
			return sql;
		}
		
//		public String getCzsbgztj(
//				@Param("cph")String cph, 
//				@Param("xm")String xm, 
//				@Param("yc")String yc, 
//				@Param("stime")String stime, 
//				@Param("etime")String etime){
//			String date = stime.replaceAll("-", "").substring(2,6);
//			//条件
//			String tj = "";
//			//内容
//			String nr = "";
//			if(yc!=null&&!yc.isEmpty()&&!yc.equals("null")&&yc.length()>0&&!yc.equals("全部")){
//				if (yc.equals("主机故障")) {
//					tj += " and ( b.LOW_VOLTAGE = 1 or b.NO_POWER = 1 or b.NO_GPS = 1 or b.NO_UPLOAD = 1)";
//					nr +=" count(case when b.LOW_VOLTAGE = 1 then  1  end) LOW_VOLTAGE ,"
//							+ " count(case when b.NO_POWER = 1 then  1  end) NO_POWER,"
//							+ " count(case when b.NO_GPS = 1 then  1  end) NO_GPS,"
//							+ " count(case when b.NO_UPLOAD = 1 then  1  end) NO_UPLOAD," ;
//				}else if (yc.equals("定位故障")) {
//					tj += " and (b.MOD_FAULT = 1 or b.ANT_FAULT = 1 or b.INEXACT = 1)";
//					nr +=" count(case when b.MOD_FAULT = 1 then  1  end) MOD_FAULT ,"
//							+ " count(case when b.ANT_FAULT = 1 then  1  end) ANT_FAULT,"
//							+ " count(case when b.INEXAC1  1 then  1  end) INEXACT," ;
//					
//				}else if (yc.equals("通信故障")) {
//					tj += " and b.COMM_FAULT = 1";
//					nr +=" count(case when b.COMM_FAULT = 1 then  1  end) COMM_FAULT ," ;
//				}else if (yc.equals("定位回传故障")) {
//					tj += " and (b.GPS_OVER_BACK = 1 or b.GPS_NO_BACK = 1)";
//					nr +=" count(case when b.GPS_OVER_BACK = 1 then  1  end) GPS_OVER_BACK ,"
//							+ " count(case when b.GPS_NO_BACK = 1 then  1  end) GPS_NO_BACK," ;
//				}else if (yc.equals("摄像头故障")) {
//					tj += " and (b.CAM_OCCLUSION = 1 or b.CAM_NOSIGN = 1)";
//					nr +=" count(case when b.CAM_OCCLUSION = 1 then  1  end) CAM_OCCLUSION ,"
//							+ " count(case when b.CAM_NOSIGN = 1 then  1  end) CAM_NOSIGN," ;
//				}else if (yc.equals("视频主机/存储故障")) {
//					tj += " and ( b.HD_FAULT = 1 or b.SD_FAULT = 1 or b.VD_FAULT = 1 or b.VD_EX_FAULT = 1)";
//					nr +=" count(case when b.HD_FAULT = 1 then  1  end) HD_FAULT ,"
//							+ " count(case when b.SD_FAULT = 1 then  1  end) SD_FAULT,"
//							+ " count(case when b.VD_FAULT = 1 then  1  end) VD_FAULT,"
//							+ " count(case when b.VD_EX_FAULT = 1 then  1  end) VD_EX_FAULT," ;
//				}else if (yc.equals("计价器断开故障")) {
//					tj += " and b.METER_DISCONN = 1";
//					nr +=" count(case when b.METER_DISCONN = 1 then  1  end) METER_DISCONN ," ;
//				}else if (yc.equals("导航屏断开故障")) {
//					tj += " and b.NAV_DISCONN = 1";
//					nr +=" count(case when b.NAV_DISCONN = 1 then  1  end) NAV_DISCONN ,";
//				}else if (yc.equals("空车灯故障")) {
//					tj += " and (b.ST_NO_CHG = 1 or b.ST_OVER_CHG = 1)";
//					nr +=" count(case when b.ST_NO_CHG = 1 then  1  end) ST_NO_CHG ,"
//							+ " count(case when b.ST_OVER_CHG = 1 then  1  end) ST_OVER_CHG," ;
//				}
//				nr = nr.substring(0,nr.length() - 1);
//			}else{
//				nr +=" count(case when b.LOW_VOLTAGE = 1 then  1  end) LOW_VOLTAGE ,"
//				+" count(case when b.NO_POWER = 1 then  1  end) NO_POWER,"
//				+" count(case when b.NO_GPS = 1 then  1  end) NO_GPS,"
//				+" count(case when b.NO_UPLOAD = 1 then  1  end) NO_UPLOAD,"
//				+" count(case when b.MOD_FAULT = 1 then  1  end) MOD_FAULT ,"
//				+" count(case when b.ANT_FAULT = 1 then  1  end) ANT_FAULT,"
//				+" count(case when b.INEXACT = 1 then  1  end) INEXACT,"
//				+" count(case when b.COMM_FAULT = 1 then  1  end) COMM_FAULT ,"
//				+" count(case when b.GPS_OVER_BACK = 1 then  1  end) GPS_OVER_BACK ,"
//				+" count(case when b.GPS_NO_BACK = 1 then  1  end) GPS_NO_BACK,"
//				+" count(case when b.CAM_OCCLUSION = 1 then  1  end) CAM_OCCLUSION ,"
//				+" count(case when b.CAM_NOSIGN = 1 then  1  end) CAM_NOSIGN,"
//				+" count(case when b.HD_FAULT = 1 then  1  end) HD_FAULT ,"
//				+" count(case when b.SD_FAULT = 1 then  1  end) SD_FAULT,"
//				+" count(case when b.VD_FAULT = 1 then  1  end) VD_FAULT,"
//				+" count(case when b.VD_EX_FAULT = 1 then  1  end) VD_EX_FAULT,"
//				+" count(case when b.METER_DISCONN = 1 then  1  end) METER_DISCONN ,"
//				+" count(case when b.NAV_DISCONN = 1 then  1  end) NAV_DISCONN ,"
//				+" count(case when b.ST_NO_CHG = 1 then  1  end) ST_NO_CHG ,"
//				+" count(case when b.ST_OVER_CHG = 1 then  1  end) ST_OVER_CHG";
//			}
//			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
//				tj += " and b.VEHICLE_NO = '"+cph+"'";
//			}
//			if(xm!=null&&!xm.isEmpty()&&!xm.equals("null")&&xm.length()>0&&!xm.equals("全部")){
////				tj += " and b.VEHICLE_NO like '%"+xm+"%'";
//			}
//			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
//				tj += " and b.DBTIME >= str_to_date('"+stime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
//				tj += " and b.DBTIME <= str_to_date('"+etime+"', '%Y-%m-%d %H:%i:%s') ";
//			}
//			String sql = "select "+nr+" from TB_ALARM_HISTORY_"+date+" b where 1=1 ";
//			sql += tj;
//			System.out.println(sql);
//			return sql;
//		}
		
		
		public String getCzsbgztj(
				@Param("stime")String stime, 
				@Param("etime")String etime){
			//条件
			String tj = "";
			//内容
			String nr = " count(case when b.NO_GPS = 1 then  1  end) NO_GPS,"
				+" count(case when b.NO_JJQ = 1 then  1  end) NO_JJQ,"
				+" count(case when b.NO_GPS_JJQ = 1 then  1  end) NO_GPS_JJQ,"
				+" count(case when b.SEVEN_GPS_JJQ = 1 then  1  end) SEVEN_GPS_JJQ,"
				+" count(case when b.EMPTY_HEAVY = 1 then  1  end) EMPTY_HEAVY  ";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.DB_TIME >= str_to_date('"+stime+"', '%Y-%m-%d') ";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.DB_TIME <= str_to_date('"+etime+"', '%Y-%m-%d') ";
			}
			String sql = "select "+nr+" from tb_taxi_gzfx_history b where 1=1 ";
			sql += tj;
			System.out.println(sql);
			return sql;
		}
		
		
		public String getTscx(
				@Param("lx")String lx, 
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.HAPPEN_TIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";	
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.HAPPEN_TIME <=str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
			}
			if(lx!=null&&!lx.isEmpty()&&!lx.equals("null")&&lx.length()>0&&!lx.equals("全部")){
				tj += " and REPLACE (b.business_itemtype_name,'运管_客运_出租客运_','') = '"+lx+"'";
			}

			String sql = "select (select count(*) COUNT from  V_GLOBAL_COMPLAINT_12328 b where 1=1 and VEHICLE_PLATE_NUMBER is not null and instr(b.business_itemtype_name, '运管_客运_出租客运_') > 0";
			sql += tj;
			sql+=" ) as count, tt.* from (select * from  V_GLOBAL_COMPLAINT_12328 b where 1=1 and VEHICLE_PLATE_NUMBER is not null and instr(b.business_itemtype_name, '运管_客运_出租客运_') > 0";
			sql += tj;
			sql += " ) tt order by tt.HAPPEN_TIME desc limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		public String getTscxxlsx(
				@Param("lx")String lx, 
				@Param("stime")String stime, 
				@Param("etime")String etime){
			String tj="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.HAPPEN_TIME >=str_to_date('"+stime+" 00:00:00','%Y-%m-%d %H:%i:%s')";	
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.HAPPEN_TIME <=str_to_date('"+etime+" 23:59:59','%Y-%m-%d %H:%i:%s')";
			}
			if(lx!=null&&!lx.isEmpty()&&!lx.equals("null")&&lx.length()>0&&!lx.equals("全部")){
				tj += " and REPLACE (b.business_itemtype_name,'运管_客运_出租客运_','') = '"+lx+"'";
			}

			String sql = "select * from  V_GLOBAL_COMPLAINT_12328 b where 1=1 and VEHICLE_PLATE_NUMBER is not null  and instr(b.business_itemtype_name, '运管_客运_出租客运_') > 0";
			sql += tj;
			sql += " order by b.HAPPEN_TIME desc";
			System.out.println(sql);
			return sql;
		}
		
		
		public String getSbgzcstj(
				@Param("cph")String cph, 
				@Param("gz")String gz, 
				@Param("stime")String stime, 
				@Param("etime")String etime,
				@Param("pageIndex")Integer pageIndex, 
				@Param("pageSize")Integer pageSize){
			String tj="",nr="",st="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.DB_TIME >= str_to_date('"+stime+"', '%Y-%m-%d') ";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.DB_TIME <= str_to_date('"+etime+"', '%Y-%m-%d') ";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and VEHICLE_NO = '"+cph+"'";
			}
			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0){
				if (gz.equals("有营运无定位")) {
					tj += " and b.NO_GPS = 1";
					nr += "count(case when b.NO_GPS = 1 then 1 end) r,'有营运无定位' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("有定位无营运")){
					tj += " and b.NO_JJQ = 1";
					nr += "count(case when b.NO_JJQ = 1 then 1 end) r,'有定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("有抓拍无定位无营运")){
					tj += " and b.NO_GPS_JJQ = 1";
					nr += "count(case when b.NO_GPS_JJQ = 1 then 1 end) r,'有抓拍无定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("7天无定位无营运")){
					tj += " and b.SEVEN_GPS_JJQ = 1";
					nr += "count(case when b.SEVEN_GPS_JJQ = 1 then 1 end) r,'7天无定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("全天空车全天重车")){
					tj += " and b.EMPTY_HEAVY = 1";
					nr += "count(case when b.EMPTY_HEAVY = 1 then 1 end) r,'全天空车全天重车' as TYPE";
					st += " order by r desc ";
				}
			}
			String sql = "select (select count(*) from (select b.VEHICLE_NO,"+nr+" from tb_taxi_gzfx_history b where 1=1";
			sql += tj;
			sql += "  GROUP BY VEHICLE_NO) c) as count,t.* from (SELECT d.*,c.company_name COMP_NAME FROM (select b.VEHICLE_NO,"+nr+" from tb_taxi_gzfx_history b where 1=1";
			sql += tj;
			sql += "  GROUP BY VEHICLE_NO "+st+" ) d,tb_global_vehicle c WHERE d.VEHICLE_NO = c.plate_number )t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			System.out.println(sql);
			return sql;
		}
		
		public String getSbgzcstjxlsx(
				@Param("cph")String cph, 
				@Param("gz")String gz, 
				@Param("stime")String stime, 
				@Param("etime")String etime){
			String tj="",nr="",st="";
			if(stime!=null&&!stime.isEmpty()&&!stime.equals("null")&&stime.length()>0){
				tj += " and b.DB_TIME >= str_to_date('"+stime+"', '%Y-%m-%d') ";
			}
			if(etime!=null&&!etime.isEmpty()&&!etime.equals("null")&&etime.length()>0){
				tj += " and b.DB_TIME <= str_to_date('"+etime+"', '%Y-%m-%d') ";
			}
			if(cph!=null&&!cph.isEmpty()&&!cph.equals("null")&&cph.length()>0&&!cph.equals("全部")){
				tj += " and VEHICLE_NO = '"+cph+"'";
			}
			if(gz!=null&&!gz.isEmpty()&&!gz.equals("null")&&gz.length()>0){
				if (gz.equals("有营运无定位")) {
					tj += " and b.NO_GPS = 1";
					nr += "count(case when b.NO_GPS = 1 then 1 end) r,'有营运无定位' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("有定位无营运")){
					tj += " and b.NO_JJQ = 1";
					nr += "count(case when b.NO_JJQ = 1 then 1 end) r,'有定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("有抓拍无定位无营运")){
					tj += " and b.NO_GPS_JJQ = 1";
					nr += "count(case when b.NO_GPS_JJQ = 1 then 1 end) r,'有抓拍无定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("7天无定位无营运")){
					tj += " and b.SEVEN_GPS_JJQ = 1";
					nr += "count(case when b.SEVEN_GPS_JJQ = 1 then 1 end) r,'7天无定位无营运' as TYPE";
					st += " order by r desc ";
				}else if(gz.equals("全天空车全天重车")){
					tj += " and b.EMPTY_HEAVY = 1";
					nr += "count(case when b.EMPTY_HEAVY = 1 then 1 end) r,'全天空车全天重车' as TYPE";
					st += " order by r desc ";
				}
			}
			String sql = "select (select count(*) from (select b.VEHICLE_NO,"+nr+" from tb_taxi_gzfx_history b where 1=1";
			sql += tj;
			sql += "  GROUP BY VEHICLE_NO) c) as count,t.* from (SELECT d.*,c.company_name COMP_NAME FROM (select b.VEHICLE_NO,"+nr+" from tb_taxi_gzfx_history b where 1=1";
			sql += tj;
			sql += "  GROUP BY VEHICLE_NO "+st+" ) d,tb_global_vehicle c WHERE d.VEHICLE_NO = c.plate_number )t";
			System.out.println(sql);
			return sql;
		}
		
	}

}

