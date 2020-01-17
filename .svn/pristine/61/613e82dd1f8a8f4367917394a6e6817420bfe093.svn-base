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

import com.erxi.ms.result.Result;
/**
 * 1.1.3.2.8不良记录
 * 
 * @author HXX
 *
 */
@Mapper
public interface KbljlDao {
	/*
	 * @Select("SELECT * FROM KBLJL") public List<Map<String ,Object>>
	 * getKblj();
	 */

	// @Select("select b.* from kbljl b where b.cph like '%浙A1%' and b.xm
	// like'%小%'")
	// @Select("SELECT * FROM shop WHERE shop.name_text LIKE
	// CONCAT('%',#{0},'%') ")







	@Select("select b.* from kbljl b where b.cph like #{cph}")
	public List<Map<String, Object>> getKbljmohu(@Param("cph") String cph);








	// @Update("update kbljl b set
	// b.cph=#{cph},b.xm=#{xm},b.wfnr=#{wfnr},b.cljg=#{cljg} where
	// b.bid=#{bid}")
	@Update({ "UPDATE KBLJL SET CPH=#{CPH},XM=#{XM},WFNR=#{WFNR},CLJG=#{CLJG} WHERE BID=#{BID}" })
	public int getUpdateKblj(@Param("BID") String BID, @Param("CPH") String CPH, @Param("XM") String XM,
			@Param("WFNR") String WFNR, @Param("CLJG") String CLJG);

	/**
	 * 删除
	 * 
	 * @param bid
	 * @return
	 */
	@Delete("DELETE FROM KBLJL WHERE BID = #{bid}")
	public Integer DeleteKBLJL(String bid);

	/**
	 * 添加
	 */
	// @Param("BID") String BID,@Param("CPH") String CPH,@Param("XM") String
	// XM,@Param("WFNR") String WFNR,@Param("CLJG") String CLJG
	// @Insert("insert into kbljl values('浙C88527','小草','撞坏高速路','待审核');")
	// @Insert("INSERT INTO kbljl(BID, CPH, XM, WFNR, CLJG) VALUES(#{BID},
	// #{CPH}, #{XM}, #{WFNR}, #{CLJG})")

	@Insert("INSERT INTO kbljl( CPH, XM, WFNR, CLJG) VALUES(#{CPH}, #{XM}, #{WFNR}, #{CLJG})")
	public int InsertKBLJL(@Param("CPH") String CPH, @Param("XM") String XM, @Param("WFNR") String WFNR,
			@Param("CLJG") String CLJG);

	@SelectProvider(type = getBljl.class, method = "getKbljFindAllDao")
	public List<Map<String, Object>> getKbljFindAllDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
			@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

	class getBljl {
		public String getKbljFindAllDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
				@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += "and b.CPH LIKE '%" + cph + "%'";
			}
			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += "and B.XM LIKE '%" + xm + "%'";
			}

			String sql = "select (select count(*) COUNT from (select * from (select gv.* from kbljl gv) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, t.*  from (select"
					+ " b.* from (select gv.* from kbljl gv) b where 1=1 ";
			sql += tj;
			sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;
		}

	}


	@SelectProvider(type = getBljlExport.class, method = "getKbljFindAllDaoExport")
	public List<Map<String, Object>> getKbljFindAllDaoExport(@Param("cph") String cph, @Param("xm") String xm);

	class getBljlExport {
		public String getKbljFindAllDaoExport(@Param("cph") String cph, @Param("xm") String xm) {

			String tj = "";
			if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0) {
				tj += "AND B.CPH LIKE '%" + cph + "%'";
			}
			if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
				tj += "AND B.XM LIKE '%" + xm + "%'";
			}
			
			String sql="SELECT * FROM KBLJL B WHERE 1=1 ";
			sql+=tj;
			System.out.println("不良记录"+sql);
			return sql;

		}
	}
}
