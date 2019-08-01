/**  

* <p>Title: JtwfDao.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: www.baidudu.com</p>  

* @author shenlan  

* @date 2018年10月12日  

* @version 1.0  

*/
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
 * @author 交通违法
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */

@Mapper
public interface JtwfDao {

	/*
	 * @Select("SELECT * FROM JTWF") public List<Map<String, Object>>
	 * getFindAllJtwfDao();
	 */

	/**
	 * 
	 * <p>
	 * Title: getUpdateJtwfDao
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * 
	 */
	@Update({
			"UPDATE JTWF T SET T.CPH=#{cph},T.XM=#{xm},T.WZSJ=#{wzsj},T.WZDD=#{wzdd},T.WZNR=#{wznr},T.CLJG=#{cljg},T.KF=#{kf},T.ZFJGFK=#{zfjgfk},T.GSFK=#{gsfk} WHERE T.BID=#{bid}" })
	public Integer getUpdateJtwfDao(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
			@Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
			@Param("cljg") String cljg, @Param("kf") String kf, @Param("zfjgfk") String zfjgfk,
			@Param("gsfk") String gsfk);

	/**
	 * 
	 * <p>
	 * Title: getUpdateJtwfDao
	 * </p>
	 * 
	 */
	@Select("SELECT J.BID,J.CPH,J.XM,J.WZSJ,J.WZDD,J.WZNR,J.CLJG,J.KF,J.ZFJGFK,J.GSFK FROM JTWF J WHERE J.CPH LIKE #{cph}")
	public List<Map<String, Object>> getSelectNameJtwfDao(@Param("cph") String cph);




	/**
	 * 删除
	 * <p>
	 * Title: getDeleteJtwfDao
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * 
	 */
	@Delete("DELETE FROM JTWF WHERE BID=#{bid}")
	public Integer getDeleteJtwfDao(String bid);

	/**
	 * 
	 * <p>
	 * Title: getInsertJtwfDao
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * 
	 */
	@Insert("INSERT INTO jtwf (CPH,XM,WZSJ,WZDD,WZNR,CLJG,KF,ZFJGFK,GSFK) values(#{cph},#{xm},#{wzsj},#{wzdd},#{wznr},#{cljg},#{kf},#{zfjgfk},#{gsfk})")
	public Integer getInsertJtwfDao(@Param("cph") String cph, @Param("xm") String xm, @Param("wzsj") String wzsj,
			@Param("wzdd") String wzdd, @Param("wznr") String wznr, @Param("cljg") String cljg, @Param("kf") String kf,
			@Param("zfjgfk") String zfjgfk, @Param("gsfk") String gsfk);

	@SelectProvider(type = getJtwf.class, method = "getFindAllJtwfDao")
	public List<Map<String, Object>> getFindAllJtwfDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

	class getJtwf {
		public String getFindAllJtwfDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
				@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " and b.XM LIKE '%" + xm + "%'";
			}

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM JTWF) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, tt.* from (select t.* from (select"
					+ " b.* from (SELECT * FROM JTWF ) b where 1=1 ";
			sql += tj;
			sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
			return sql;

		}
	}

	@SelectProvider(type = getJtwfExport.class, method = "getSelectNameJtwfDaoExport")
	public List<Map<String, Object>> getSelectNameJtwfDaoExport(@Param("cph") String cph, @Param("xm") String xm);

	class getJtwfExport {
		public String getSelectNameJtwfDaoExport(@Param("cph") String cph, @Param("xm") String xm) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " AND B.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " AND B.XM LIKE '%" + xm + "%'";
			}

			String sql = "SELECT * FROM JTWF B WHERE 1=1 ";
			sql += tj;
			System.out.println("交通违法" + sql);
			return sql;

		}
	}
}
