package com.erxi.ms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 营运数据查询接口
 * 
 * @author 小坏
 * 
 * @date 2018年10月31日 下午2:14:41
 */

@Mapper
public interface YysjcxDao {

	@SelectProvider(type = getYysjcx.class, method = "getFindAllYysjcxDao")
	public List<Map<String, Object>> getFindAllYysjcxDao(@RequestParam("start") String start,
			@RequestParam("stop") String stop,@RequestParam("cph") String cph,@RequestParam("gsm") String gsm, @RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize);



    class getYysjcx {
    	private static List<String> getMonthBetween(String minDate, String maxDate) {
    	    ArrayList<String> result = new ArrayList<String>();
    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//格式化为年月
    	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");//格式化为年月
    	    if(minDate==""){
    	    	minDate=sdf.format((new Date()));
    	    }
    	    if(minDate==""){
    	    	minDate=sdf.format((new Date()));
    	    }
    	    Calendar min = Calendar.getInstance();
    	    Calendar max = Calendar.getInstance();
    	    try {
    	    	min.setTime(sdf.parse(minDate));
				max.setTime(sdf.parse(maxDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
    	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
    	    Calendar curr = min;
    	    while (curr.before(max)) {
    	     result.add(sdf2.format(curr.getTime()));
    	     curr.add(Calendar.MONTH, 1);
    	    }
    	    if(result.size()==0){
    	    	result.add(sdf2.format(curr.getTime()));
    	    }
    	 
    	    return result;
    	  }

		public String getFindAllYysjcxDao(@RequestParam("start") String start, @RequestParam("stop") String stop,
				@RequestParam("cph") String cph,@RequestParam("gsm") String gsm, @RequestParam("pageIndex") Integer pageIndex,
				@RequestParam("pageSize") Integer pageSize) {
			String tj = "";	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and b.SHANGCHE >=str_to_date('"+start+"','%Y%m%d %H:%i:%s')";
				c++;
			}
			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and b.SHANGCHE <=str_to_date('"+stop+"','%Y%m%d %H:%i:%s')";
				c++;
			}
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.vhic like '%" + cph + "%'";
			}
			if (gsm != null && !gsm.isEmpty() && !gsm.equals("null") && gsm.length() > 0) {
				tj += " and c.ZGS = '"+gsm+"'";
			}
			String sql = "";
			if(c==0){
				tj += " and b.SHANGCHEDATE ='" + sdf.format(new Date()) + "'";
			}
			List<String> listDate=getMonthBetween(start,stop);
			sql = " select (select count(1) from (select vhic from jjq"+listDate.get(0)+"_1 b,jjq_company c where CONCAT('浙',b.vhic) = c.cphm and b.flag = '1000000000'";
			sql += tj;
			sql += ") m ) as COUNT, tt.* from (select t.* from (select "   
//					cast(JICHENG as SIGNED)
					+ " CONCAT('浙',b.vhic) CPH,"
					+" c.ZGS, b.YINGYUN,date_format(b.SHANGCHE,'%Y-%m-%d %H:%i:%s') SCSJ,date_format(b.XIACHE,'%Y-%m-%d %H:%i:%s') XCSJ,timestampdiff(MINUTE, b.SHANGCHE, b.XIACHE) YYSJ,round(cast(b.JICHENG as SIGNED)/10,2) ZKLC,round(cast(b.KONGSHI as SIGNED)/10,2) KCLC,TIME_TO_SEC(str_to_date(DENGHOU,'%H%i%s')) DHSJ,b.JIAOYITYPE,round(cast(b.JINE as SIGNED)/100,2) YYJE"
					+ "  from  jjq"+listDate.get(0)+"_1 b,jjq_company c where CONCAT('浙',b.vhic) = c.cphm and b.flag = '1000000000'";
			sql += tj;
			sql += " order by b.SHANGCHE desc) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			System.out.println("chaxunsql="+sql);
			return sql;

		}

	}



	@SelectProvider(type = getYysjcxExport.class, method = "getFindAllYysjcxDaoExport")
	public List<Map<String, Object>> getFindAllYysjcxDaoExport(@RequestParam("start") String start,@RequestParam("stop") String stop,@RequestParam("cph") String cph,@RequestParam("gsm") String gsm);

	class getYysjcxExport{
		public String getFindAllYysjcxDaoExport(@RequestParam("start") String start,@RequestParam("stop") String stop,@RequestParam("cph") String cph,@RequestParam("gsm") String gsm){

			String tj = "";	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int c=0;
			if (start != null && !start.isEmpty() && !start.equals("null") && start.length() > 0) {
				tj += " and b.SHANGCHE >=str_to_date('"+start+"','%Y%m%d %H:%i:%s')";
				c++;
			}
			if (stop != null && !stop.isEmpty() && !stop.equals("null") && stop.length() > 0) {
				tj += " and b.SHANGCHE <=str_to_date('"+stop+"','%Y%m%d %H:%i:%s')";
				c++;
			}
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.vhic like '%" + cph + "%'";
			}
			if (gsm != null && !gsm.isEmpty() && !gsm.equals("null") && gsm.length() > 0) {
				tj += " and c.ZGS = '"+gsm+"'";
			}
			String sql = "";
			if(c==0){
				tj += " and b.SHANGCHEDATE ='" + sdf.format(new Date()) + "'";
			}
			List<String> listDate=getYysjcx.getMonthBetween(start,stop);
			sql = " select  t.* from (select "
					+ " CONCAT('浙',b.vhic) CPH,"
					+" c.ZGS, b.YINGYUN,date_format(b.SHANGCHE,'%Y-%m-%d %H:%i:%s') SCSJ,date_format(b.XIACHE,'%Y-%m-%d %H:%i:%s') XCSJ"
					+ ",timestampdiff(MINUTE, b.SHANGCHE, b.XIACHE) YYSJ,round(cast(b.JICHENG as SIGNED)/10,2) ZKLC,round(cast(b.KONGSHI as SIGNED)/10,2) KCLC,TIME_TO_SEC(str_to_date(DENGHOU,'%H%i%s')) DHSJ,b.JIAOYITYPE,round(cast(b.JINE as SIGNED)/100,2) YYJE"
					+ "  from  jjq"+listDate.get(0)+"_1 b,jjq_company c where CONCAT('浙',b.vhic) = c.cphm and b.flag = '1000000000'";
			sql += tj;
			sql += " order by b.SHANGCHE desc) t";
			System.out.println("chaxunsqldc="+sql);
			return sql;
		}
	}


@Select("SELECT DISTINCT b.vhic CP from jjq201812_1 b where flag = '1000000000'")
//@Select("SELECT DISTINCT b.vhic CP from jjq201812_1 b where b.vhic like #{cph} and flag = '1000000000'")
	public List<Map<String, Object>> getFindAllYysjcxDaoName(@RequestParam("cph") String cph);


@Select("SELECT DISTINCT zgs GSM FROM jjq_company J")
List<Map<String, Object>> getFindAllgsm();

}
