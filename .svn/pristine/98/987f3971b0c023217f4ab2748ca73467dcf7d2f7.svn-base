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
 * 服务投诉
 * 
 * @author HXX
 *
 */

@Mapper
public interface KfwtsDao {

	/*
	 * @Select("SELECT * FROM kFWTS") public List<Map<String, Object>>
	 * getFindAll();
	 */

	@Update({
			"UPDATE KFWTS F SET F.CPH=#{cph},F.BSSJ=#{bsjs},F.CCSJ=#{ccsj},F.SLSJ=#{slsj},F.LXFS=#{lxfs},F.TSR=#{tsr},F.DCQK=#{dcqk},F.TSSY=#{tssy},F.TSRYJ=#{tsyj},F.CLJG=#{cljg} WHERE F.BID=#{bid}" })
	public Integer getUpdateKfwtsDao(@Param("bid") String bid, @Param("cph") String cph, @Param("bsjs") String bsjs,
			@Param("ccsj") String ccsj, @Param("slsj") String slsj, @Param("lxfs") String lxfs,
			@Param("tsr") String tsr, @Param("dcqk") String dcqk, @Param("tssy") String tssy,
			@Param("tsyj") String tsyj, @Param("cljg") String cljg);

	@Delete("DELETE FROM KFWTS WHERE BID =#{bid}")
	public Integer getDeleteKfwtsDao(String bid);






	@Select("SELECT * FROM KFWTS F WHERE F.CPH LIKE #{cph}")
	public List<Map<String, Object>> getSelectNameKfwtsDao(@Param("cph") String cph);




	/**
	 * <p>
	 * Title: getUpdateKfwtsDao
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	@Insert("INSERT INTO KFWTS (CPH,BSSJ,CCSJ,SLSJ,LXFS,TSR,DCQK,TSSY,TSRYJ,CLJG) VALUES(#{cph},#{bsjs},#{ccsj},#{slsj},#{lxfs},#{tsr},#{dcqk},#{tssy},#{tsyj},#{cljg})")
	public Integer getInsertfwtsDao(@Param("cph") String cph, @Param("bsjs") String bsjs, @Param("ccsj") String ccsj,
			@Param("slsj") String slsj, @Param("lxfs") String lxfs, @Param("tsr") String tsr,
			@Param("dcqk") String dcqk, @Param("tssy") String tssy, @Param("tsyj") String tsyj,
			@Param("cljg") String cljg);

	@SelectProvider(type = getFwts.class, method = "getFindAll")
	public List<Map<String, Object>> getFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

	class getFwts {
		public String getFindAll(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
				@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " and b.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " and b.BSSJ LIKE '%" + xm + "%'";
			}

			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KFWTS) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, t.*  from (select"
					+ " b.* from (SELECT * FROM KFWTS ) b where 1=1 ";
			sql += tj;
			sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;

		}
	}

	@SelectProvider(type = getExport.class, method = "getFindAllExport")
	public List<Map<String, Object>> getFindAllExport(@Param("cph") String cph, @Param("xm") String xm);

	class getExport {
		public String getFindAllExport(@Param("cph") String cph, @Param("xm") String xm) {
			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += " AND B.CPH LIKE '%" + cph + "%'";
			}

			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += " AND B.BSSJ LIKE '%" + xm + "%'";
			}

			String sql = "SELECT B.* FROM KFWTS B WHERE 1=1";
			sql += tj;
			System.out.println("服务投诉" + sql);
			return sql;

		}
	}

}
