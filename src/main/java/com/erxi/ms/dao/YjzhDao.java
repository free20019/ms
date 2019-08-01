package com.erxi.ms.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.bind.annotation.RequestParam;

import com.erxi.ms.result.FastJsonUtil;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应急指挥处置系统
 * @author xianlehuang
 * @date 2018/12/20 
 */

@Mapper
public interface YjzhDao {
	
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
	 * 值班
	 * @return
	 */
	@SelectProvider(type = yjzh.class, method = "findzbb")
	public List<Map<String, Object>> findzbb();
	
	/**
	 * 值班(week)
	 * @return
	 */
	@SelectProvider(type = yjzh.class, method = "findzbbweek")
	public List<Map<String, Object>> findzbbweek();
	
	/**
	 * 应急接入
	 * @return
	 */
	@SelectProvider(type = yjzh.class, method = "fingyjsjjr")
	public List<Map<String, Object>> fingyjsjjr(@Param("sjzt") String sjzt);
	
	/**
	 * 应急事件接入保存
	 * @return
	 */
	@InsertProvider(type = yjzh.class, method = "jrsave")
	public Integer jrsave(@Param("postData") String postData);
	/**
	 * 应急事件接入修改
	 * @return
	 */
	@UpdateProvider(type = yjzh.class, method = "jrUpdate")
	public Integer jrUpdate(@Param("postData") String postData);
	/**
	 * 应急事件接入审核
	 * @param id
	 * @return
	 */
	@Update("update TB_YJZH_YJSJ set sh='1' where id = '${id}'")
	public Integer jrRzsh(
			@Param("id")String id);
	
	/**
	 * 应急事件接入审核
	 * @param id
	 * @return
	 */
	@Delete("delete from TB_YJZH_YJSJ where id = '${id}'")
	public Integer jrDelete(
			@Param("id")String id);
	
	/**
	 * 资源库文件名查询
	 * @param table
	 * @return
	 */
	@Select("select * from ${table}")
	public List<Map<String, Object>> getAllNames(
			@Param("table")String table);
	/**
	 * 资源库文件内容查询
	 * @param table
	 * @param id
	 * @return
	 */
	@Select("select CONVERT (CONTENT USING utf8) as CONTENT from ${table} where id=' ${id}'")
	public List<Map<String, Object>> getContent(
			@Param("table")String table,
			@Param("id")String id);
	/**
	 * 资源库文件添加
	 * @param id
	 * @return
	 */
	@Insert("insert into ${table} (NAME,CONTENT) values (' ${name}',' ${content}')")
	public Integer saveContent(
			@Param("table") String table,
    		@Param("name") String name,
    		@Param("content")String content);
	
	/**
	 * 应急事件接入修改
	 * @return
	 */
	@Update("update ${table} set NAME='${name}',CONTENT='${content}' where ID='${id}'")
	public Integer editContent(@Param("table") String table,
			@Param("id") String id,
    		@Param("name") String name,
    		@Param("content")String content);
	
	/**
	 * 查车辆
	 * @param request
	 * @param info
	 * @return
	 */
	@Select("select b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_mdt_status b where b.VEHI_NUM = v.vehi_no and v.vehi_no like '%${info}%'")
	public List<Map<String, Object>> findclsj2(
			@Param("info")String info);
	
	/**
	 * 查车辆
	 * @param request
	 * @return
	 */
	@Select("select b.*,v.VEHISTATUS,v.VEHI_SIM SIM_NUM,v.VT_NAME,v.COMP_NAME,v.VC_NAME,v.OWN_NAME,v.OWN_TEL,v.VEHI_NO"
			+ " from vw_vehicle v,tb_mdt_status b where b.VEHI_NUM = v.vehi_no")
	public List<Map<String, Object>> findclsj1();
	
	/**
	 * 查事件
	 * @param request
	 * @return
	 */
	@Select("SELECT * FROM	TB_YJZH_YJSJ t "
			+ "LEFT JOIN (SELECT sjbh,GROUP_CONCAT(y.vehi_no) VEHI_NO,GROUP_CONCAT(y.comp_name) COMP_NAME,"
			+ "GROUP_CONCAT(y.own_name) OWN_NAME,GROUP_CONCAT(y.own_tel) OWN_TEL FROM TB_YJZH_SJCL y,VW_VEHICLE v "
			+ "WHERE y.vehi_no = v.vehi_no	GROUP BY sjbh) b ON t.sjbh = b.sjbh")
	public List<Map<String, Object>> findjtsj();
	
	class yjzh{
		public String findzbb(){
			SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
			Date d = new Date();
			String now = sdf.format(d);
			String sql = "select * from TB_YJZH_ZBB  where zbsj = '"+now+"'";
			return sql;
		}
		public String findzbbweek(){
			SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
			//得到当前一周的日期
			Calendar cal =Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY);
		    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
		    String monday = sdf.format(cal.getTime());
			String tj="'"+sdf.format(cal.getTime())+"',";
			for(int i=1;i<7;i++){
				cal.add(Calendar.DATE, 1);
				if(i<6){					
					tj +="'"+sdf.format(cal.getTime())+"',";
				}else{
					tj +="'"+sdf.format(cal.getTime())+"'";
				}
			}
		    String sql = "select * from TB_YJZH_ZBB where zbsj in ("+tj+") ORDER BY STR_TO_DATE(zbsj,'%m月%d日')";
//		    		+ " ORDER BY(CASE WHEN bbname = '周一' THEN 1 WHEN bbname = '周二' THEN 2	WHEN bbname = '周三' THEN	 3 WHEN bbname = '周四' THEN	4	WHEN bbname = '周五' THEN 5	WHEN bbname = '周六' THEN	 6	WHEN bbname = '周日' THEN	 7 END)";
		    System.out.println(sql);
			return sql;
		}
		public String fingyjsjjr(
				@Param("sjzt") String sjzt){
			String tj="";
			if(sjzt!=null&&!sjzt.isEmpty()&&!sjzt.equals("null")&&sjzt.length()>0&&!sjzt.equals("主题")){
				tj +=" and sjzt like '%"+sjzt+"%'";
			}
			String sql = "select * from TB_YJZH_YJSJ where 1=1 ";
			sql += tj;
			System.out.println("fingyjsjjr="+sql);
			return sql;
			
		}
		public String jrsave(
				@Param("postData") String postData){
			Map<String, Object> paramMap = FastJsonUtil.stringToMap(postData);
			String sjbh = String.valueOf(paramMap.get("sjbh"));
			String sjzt = String.valueOf(paramMap.get("sjzt"));
			String fsdz  = String.valueOf(paramMap.get("fsdz"));
			String jwdxx = String.valueOf(paramMap.get("jwdxx"));
			String bjr = String.valueOf(paramMap.get("bjr"));
			String bjdh  = String.valueOf(paramMap.get("bjdh"));
			String jjr = String.valueOf(paramMap.get("jjr"));
			String sjjl = String.valueOf(paramMap.get("sjjl"));
			String bjnr  = String.valueOf(paramMap.get("bjnr"));
			String bjfs = String.valueOf(paramMap.get("bjfs"));
			String sjjb =String.valueOf(paramMap.get("sjjb"));
			String[] xx=null;
			if(jwdxx!=null&&jwdxx.length()>0&&!jwdxx.equals("null")){
				xx=jwdxx.split(",");
			}
			String sql = "insert into TB_YJZH_YJSJ (SJBH,SJZT,TIME,ADDRESS,SJNR,"
					+ "BJR,DJDH,JJR,BJFS,SJJL,PX,PY,SJJB) values ("
					+ "'"+sjbh+"','"+sjzt+"',NOW(),'"+fsdz+"','"+bjnr.trim()+"','"+bjr+
					"','"+bjdh+"','"+jjr+"','"+bjfs+"','"+sjjl.trim()+"','"+xx[0]+"','"+xx[1]+"','"+sjjb+"')";
			System.out.println("jrsave="+sql);
			return sql;
			
		}
		public String jrUpdate(
				@Param("postData") String postData){
			Map<String, Object> paramMap = FastJsonUtil.stringToMap(postData);
			String sjbh = String.valueOf(paramMap.get("sjbh"));
			String sjzt = String.valueOf(paramMap.get("sjzt"));
			String fsdz  = String.valueOf(paramMap.get("fsdz"));
			String jwdxx = String.valueOf(paramMap.get("jwdxx"));
			String bjr = String.valueOf(paramMap.get("bjr"));
			String bjdh  = String.valueOf(paramMap.get("bjdh"));
			String jjr = String.valueOf(paramMap.get("jjr"));
			String sjjl = String.valueOf(paramMap.get("sjjl"));
			String bjnr  = String.valueOf(paramMap.get("bjnr"));
			String bjfs = String.valueOf(paramMap.get("bjfs"));
			String sjjb =String.valueOf(paramMap.get("sjjb"));
			String id =String.valueOf(paramMap.get("id"));
			String[] xx=null;
			if(jwdxx!=null&&jwdxx.length()>0&&!jwdxx.equals("null")){
				xx=jwdxx.split(",");
			}
			String sql = "update TB_YJZH_YJSJ set SJBH = '"+sjbh+"',"
					+ "SJZT='"+sjzt+"',TIME=NOW(),ADDRESS='"+fsdz+"'"
					+ ",SJNR='"+bjnr+"',"
					+ "BJR='"+bjr+"',DJDH='"+bjdh+"',JJR='"+jjr+"',"
					+ "BJFS='"+bjfs+"',SJJL='"+sjjl+"',PX='"+xx[0]+"',PY='"+xx[1]+"',SJJB='"+sjjb+"' "
					+ " where id = '"+id+"'";
			System.out.println("jrsave="+sql);
			return sql;
			
		}
	}	
}