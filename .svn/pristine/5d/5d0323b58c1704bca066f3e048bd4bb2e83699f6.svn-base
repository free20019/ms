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
 * 榮譽管理的接口
 * 
 * @author HXX
 *
 */

@Mapper
public interface KryglDao {

	/*
	 * @Select("SELECT * FROM KRYGL") public List<Map<String,Object>>
	 * getByKrygl();
	 */

	/**
	 * 修改
	 */

	@Update({
			"UPDATE KRYGL R SET R.CPH=#{cph},R.SJXM=#{sjxm},R.PDRQ=#{pdrq},R.RYMC=#{rymc},R.NF=#{nf},R.JLJE=#{jlje},R.XJ=#{xj},R.BZ=#{bz} WHERE R.BID=#{bid}" })
	public Integer getUpdateKryglDao(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
			@Param("pdrq") String pdrq, @Param("rymc") String rymc, @Param("nf") String nf, @Param("jlje") String jlje,
			@Param("xj") String xj, @Param("bz") String bz);

	/**
	 * 刪除
	 * 
	 * @param bid
	 * @return
	 */
	@Delete("DELETE FROM KRYGL WHERE BID =#{bid}")
	public Integer getDeleteKryglDao(String bid);

	/**
	 * 查詢
	 * 
	 * @param cph
	 * @param sj
	 * @return
	 */
	@Select("SELECT * FROM KRYGL R WHERE R.CPH LIKE #{cph}")
	public List<Map<String, Object>> getNameKryglDao(@Param("cph") String cph);




	/**
	 * 添加
	 * 
	 * @param cph
	 * @param sjxm
	 * @param pdrq
	 * @param rymc
	 * @param nf
	 * @param jlje
	 * @param xj
	 * @param bz
	 * @return
	 */
	@Insert("INSERT INTO KRYGL (CPH, SJXM, PDRQ, RYMC, NF, JLJE, XJ, BZ) VALUES(#{cph}, #{sjxm}, #{pdrq}, #{rymc}, #{nf} , #{jlje}, #{xj}, #{bz})")
	public Integer getInsertKryglDao(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("pdrq") String pdrq,
			@Param("rymc") String rymc, @Param("nf") String nf, @Param("jlje") String jlje, @Param("xj") String xj,
			@Param("bz") String bz);

	@SelectProvider(type = getRygl.class, method = "getByKryglFindAll")
	public List<Map<String, Object>> getByKryglFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

	class getRygl {
		public String getByKryglFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
				@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " and b.SJXM LIKE '%" + xm + "%'";
			}

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KRYGL) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, tt.* from (select t.*  from (select"
					+ " b.* from (SELECT * FROM KRYGL ) b where 1=1 ";
			sql += tj;
			sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";

			System.out.println("荣誉管理" + sql);
			return sql;

		}
	}

	@SelectProvider(type = getFindAll.class, method = "getByKryglFindAllExport")
	public List<Map<String, Object>> getByKryglFindAllExport(@Param("cph") String cph, @Param("xm") String xm);

	class getFindAll {
		public String getByKryglFindAllExport(@Param("cph") String cph, @Param("xm") String xm) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " AND B.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " AND B.SJXM LIKE '%" + xm + "%'";
			}

			String sql = "SELECT * FROM KRYGL B WHERE 1=1 ";
			sql += tj;
			System.out.println("荣誉管理" + sql);
			return sql;

		}

	}
}
