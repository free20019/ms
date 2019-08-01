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
 * 1.1.3.2.7文明车辆
 * 
 * @author HXX
 *
 */

@Mapper
public interface KwmclDao {

	/*
	 * @Select("SELECT * FROM kWMCL") public List<Map<String, Object>>
	 * getKwmcl();
	 */

	/**
	 * 修改
	 * 
	 * @param bid
	 * @param cph
	 * @param pdrq
	 * @param nf
	 * @param rymc
	 * @param xjqk
	 * @param bz
	 * @return
	 */
	// @Update({"UPDATE KBLJL SET CPH=#{CPH},XM=#{XM},WFNR=#{WFNR},CLJG=#{CLJG}
	// WHERE BID=#{BID}"})
	@Update({
			"update kwmcl w set w.cph=#{cph},w.pdrq=#{pdrq},w.nf=#{nf},w.rymc=#{rymc},w.xjqk=#{xjqk},w.bz=#{bz} where w.bid=#{bid}" })
	public Integer getUpdatewmcl(@Param("bid") String bid, @Param("cph") String cph, @Param("pdrq") String pdrq,
			@Param("nf") String nf, @Param("rymc") String rymc, @Param("xjqk") String xjqk, @Param("bz") String bz);

	/**
	 * delete
	 */

	@Delete("DELETE FROM KWMCL  WHERE BID=#{bid}")
	Integer getDeleteKwmclDao(String bid);


	//EACHER
	@Select("SELECT * FROM KWMCL W WHERE W.CPH LIKE #{cph}")
	public List<Map<String, Object>> getSelectNameKwmclDao(@Param("cph") String cph);


	/**
	 * INSET
	 * @param cph
	 * @param pdrq
	 * @param nf
	 * @param rymc
	 * @param xjqk
	 * @param bz
	 * @return
	 */



	@Insert("INSERT INTO kwmcl (CPH, PDRQ, NF, RYMC, XJQK, BZ) VALUES(#{cph}, #{pdrq}, #{nf}, #{rymc}, #{xjqk} ,#{bz})")
	public Integer getInsertwmcl(@Param("cph") String cph, @Param("pdrq") String pdrq, @Param("nf") String nf,
			@Param("rymc") String rymc, @Param("xjqk") String xjqk, @Param("bz") String bz);

	@SelectProvider(type = getwmcl.class, method = "getKwmclFindAllDao")
	public List<Map<String, Object>> getKwmclFindAllDao(@RequestParam("cph") String cph,
			@RequestParam("rymc") String rymc, @RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize);

	class getwmcl {
		public String getKwmclFindAllDao(@RequestParam("cph") String cph, @RequestParam("rymc") String rymc,
				@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.CPH like'%" + cph + "%'";
			}

			if (rymc != null && !rymc.isEmpty() && !rymc.equals("null") && rymc.length() > 0) {
				tj += " and b.RYMC like '%" + rymc + "%'";
			}

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KWMCL) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, t.* from (select"
					+ " b.* from (SELECT * FROM KWMCL ) b where 1=1 ";
			sql += tj;
			sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;

		}
	}

	@SelectProvider(type = getwmclExport.class, method = "getFindAllExportlDao")
	public List<Map<String, Object>> getFindAllExportlDao(@Param("cph") String cph, @Param("rymc") String rymc);

	class getwmclExport {
		public String getFindAllExportlDao(@Param("cph") String cph, @Param("rymc") String rymc) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.CPH like'%" + cph + "%'";
			}

			if (rymc != null && !rymc.isEmpty() && !rymc.equals("null") && rymc.length() > 0) {
				tj += " and b.RYMC like '%" + rymc + "%'";
			}

			String sql = "SELECT * FROM KWMCL B WHERE 1=1 ";
			sql += tj;
			System.out.println("来了"+sql);
			return sql;

		}
	}

}
