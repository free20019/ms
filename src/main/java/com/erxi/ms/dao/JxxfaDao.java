/**  

 * <p>Title: JxxfaDao.java</p>  

 * <p>Description: </p>  

 * <p>Copyright: Copyright (c) 2017</p>  

 * <p>Company: www.baidudu.com</p>  

 * @author shenlan  

 * @date 2018年10月19日  

 * @version 1.0  

 */
package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface JxxfaDao {

	@SelectProvider(type = getJbxx.class, method = "getJxxfb")
	List<Map<String, Object>> getJxxfb(
			@RequestParam("lx") String lx,
			@RequestParam("title") String title,
			@RequestParam("datetimeStart") String datetimeStart,
			@RequestParam("datetimeEnd") String datetimeEnd,
			@RequestParam("type") String type,
			@RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize);

	class getJbxx {
		public String getJxxfb(
				@RequestParam("lx") String lx,
				@RequestParam("title") String title,
				@RequestParam("datetimeStart") String datetimeStart,
				@RequestParam("datetimeEnd") String datetimeEnd,
				@RequestParam("type") String type,
				@RequestParam("pageIndex") Integer pageIndex,
				@RequestParam("pageSize") Integer pageSize) {

			String tj = "";
			if (lx != null && !lx.isEmpty() && !lx.equals("null")
					&& lx.length() > 0) {
				tj += " and b.LX = '" + lx + "'";
			} 
			
			if (title != null && !title.isEmpty() && !title.equals("null")
					&& title.length() > 0) {
				tj += " and b.BT like '%" + title + "%'";
			}

			if (datetimeStart != null && !datetimeStart.isEmpty()
					&& !datetimeStart.equals("null")
					&& datetimeStart.length() > 0) {
				tj += " and b.FBRQ >= '" + datetimeStart + "'";
			}

			if (datetimeEnd != null && !datetimeEnd.isEmpty()
					&& !datetimeEnd.equals("null") && datetimeEnd.length() > 0) {
				tj += " and b.FBRQ <= '" + datetimeEnd + "'";
			}

			if (type != null && !type.isEmpty() && !type.equals("null")
					&& type.length() > 0) {
				tj += " and b.LB = '" + type + "'";
			}

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM JXXFB) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, tt.* from (select t.*  from (select"
					+ " b.* from (SELECT * FROM JXXFB ) b where 1=1 ";
			sql += tj;
			sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt "
					+ " order by tt.BID desc";
			return sql;

		}
	}

	@SelectProvider(type = getxxfbExs.class, method = "getJxxfbExport")
	public List<Map<String, Object>> getJxxfbExport(
			@RequestParam("lx") String lx,
			@RequestParam("title") String title,
			@RequestParam("datetimeStart") String datetimeStart,
			@RequestParam("datetimeEnd") String datetimeEnd,
			@RequestParam("type") String type);

	class getxxfbExs {
		public String getJxxfbExport(
				@RequestParam("lx") String lx,
				@RequestParam("title") String title,
				@RequestParam("datetimeStart") String datetimeStart,
				@RequestParam("datetimeEnd") String datetimeEnd,
				@RequestParam("type") String type) {

			String tj = "";
			if (lx != null && !lx.isEmpty() && !lx.equals("null")
					&& lx.length() > 0) {
				tj += " and T.LX = '" + lx + "'";
			} 
			
			if (title != null && !title.isEmpty() && !title.equals("null")
					&& title.length() > 0) {
				tj += " and T.BT like '%" + title + "%'";
			}
			
			if (datetimeStart != null && !datetimeStart.isEmpty()
					&& !datetimeStart.equals("null")
					&& datetimeStart.length() > 0) {
				tj += " and T.FBRQ >= '" + datetimeStart + "'";
			}

			if (datetimeEnd != null && !datetimeEnd.isEmpty()
					&& !datetimeEnd.equals("null") && datetimeEnd.length() > 0) {
				tj += " and T.FBRQ <= '" + datetimeEnd + "'";
			}

			if (type != null && !type.isEmpty() && !type.equals("null")
					&& type.length() > 0) {
				tj += " and T.LB = '" + type + "'";
			}

			String sql = "SELECT T.* FROM JXXFB T ";
			sql += " WHERE 1=1 " + tj +" order by T.BID desc";
			return sql;

		}
	}

	@Insert("INSERT INTO JXXFB (BT,NR,FBRQ,LB,SFBD,LX,FBBM,FJ,WJM) VALUES(#{bt},#{nr},#{fbrq},#{lb},#{bd},#{lx},#{fbbm},#{fj},#{wjm})")
	public Integer getIntegerFindAllDao(@Param("bt") String bt,
			@Param("nr") String nr, @Param("fbrq") String fbrq,
			@Param("lb") String lb, @Param("bd") String bd,@Param("fbbm") String fbbm,
			@Param("lx") String lx, @Param("fj") String fj,
			@Param("wjm") String wjm);

	@Update("UPDATE jxxfb X SET X.BT=#{bt},X.NR=#{nr},X.FBRQ=#{fbrq},X.LB=#{lb},X.SFBD=#{bd},X.LX=#{lx},X.FBBM=#{fbbm},X.FJ=#{fj},X.WJM=#{wjm} WHERE X.BID=#{id}")
	public Integer getUpdateFindAllDao(@Param("id") String id,
			@Param("bt") String bt, @Param("nr") String nr,
			@Param("fbrq") String fbrq, @Param("lb") String lb, @Param("bd") String bd, @Param("fbbm") String fbbm,
			@Param("lx") String lx,@Param("fj") String fj,
			@Param("wjm") String wjm);

	@Delete("DELETE FROM JXXFB  WHERE BID=#{id} ")
	public Integer getdaleteFindAllDao(@Param("id") String id);
	
	@Select("select  * from JXXFB b,jxxfb_cx c  WHERE b.BID=#{id} and b.BID=c.NEWS_ID order by c.id desc")
	public List<Map<String, Object>> xxfbgs(@Param("id") String id);

}
