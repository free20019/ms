package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 好人好事接口
 *
 * @author 作者： Mar小坏
 * @date 2018年10月11日
 */
@Mapper
public interface KhrhsDao {
/*
	@Select("SELECT * FROM khrhs")
	public List<Map<String, Object>> getByhrhs();*/

	@Select("select distinct JLLX，JLLB from KHRHS")
	public List<Map<String, Object>> getjllb();

	@Update({
			"UPDATE KHRHS H SET H.CPH=#{cph},H.XM=#{xm},H.SJ=#{sj},H.NR=#{nr},H.JLLB=#{jllb},H.JLJE=#{jlje},H.JFQK=#{jfqk},H.JLLX=#{jllx} WHERE H.BID=#{bid}" })
	public Integer getUpdateKhrhsDao(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("sj") String sj, @Param("nr") String nr, @Param("jllb") String jllb, @Param("jlje") String jlje,
			@Param("jfqk") String jfqk, @Param("jllx") String jllx);

	@Delete("DELETE FROM KHRHS WHERE BID=#{bid}")
	public Integer getDeleteKhrhsDao(String bid);

	@Select("SELECT * FROM KHRHS H WHERE H.CPH LIKE #{cph} AND H.XM LIKE #{xm}")
	public List<Map<String, Object>> getSelectNameKhrhsDao(@Param("cph") String cph, @Param("xm") String xm);

	/**
	 * 
	 * 
	 * <p>
	 * Title: getInsertKhrhsDao
	 * </p>
	 */
	
	@Insert("INSERT INTO KHRHS (CPH,XM,SJ,NR,JLLB,JLJE,JFQK,JLLX) VALUES(#{cph},#{xm},#{sj},#{nr},#{jllb},#{jlje},#{jfqk},#{jllx})")
	public Integer getInsertKhrhsDao(@Param("cph") String cph, @Param("xm") String xm, @Param("sj") String sj,
			@Param("nr") String nr, @Param("jllb") String jllb, @Param("jlje") String jlje, @Param("jfqk") String jfqk,
			@Param("jllx") String jllx);

	@SelectProvider(type=getHrhs.class,method="getByhrhsFindAllDao")
	public List<Map<String, Object>> getByhrhsFindAllDao(@RequestParam("cph") String cph,@RequestParam("xm") String xm,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize);



	class getHrhs{
		public String getByhrhsFindAllDao(@RequestParam("cph") String cph,@RequestParam("xm") String xm,@RequestParam("pageIndex") Integer pageIndex,@RequestParam("pageSize") Integer pageSize){
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null")
					&& cph.length() > 0) {
				tj += " and b.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null")
					&& xm.length() > 0) {
				tj += " and b.XM LIKE '%" + xm + "%'";
			}

			

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KHRHS) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, t.*  from (select"
					+ " b.* from (SELECT * FROM KHRHS ) b where 1=1 ";
			sql += tj;
			sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
			
		}
	}
	@SelectProvider(type=getHrhsdc.class,method="gethrhsdc")
	public List<Map<String, Object>> gethrhsdc(@RequestParam("cph") String cph,@RequestParam("xm") String xm);



	class getHrhsdc{
		public String gethrhsdc(@RequestParam("cph") String cph,@RequestParam("xm") String xm){
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null")
					&& cph.length() > 0) {
				tj += " and b.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null")
					&& xm.length() > 0) {
				tj += " and b.XM LIKE '%" + xm + "%'";
			}

			

			String sql = "select  t.* from (select"
					+ " b.* from (SELECT * FROM KHRHS ) b where 1=1 ";
			sql += tj;
			sql += " ) t ";
			return sql;
			
		}
	}


      @Select("SELECT R.CPH FROM KHRHS R WHERE R.CPH LIKE #{cph}")
	public List<Map<String, Object>> getByhrhsFindAllDaoNames(@Param("cph") String cph);

}
