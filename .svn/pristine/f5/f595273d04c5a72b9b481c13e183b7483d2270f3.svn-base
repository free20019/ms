/**
 * <p>Title: SzzswDao.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: www.baidudu.com</p>
 *
 * @author shenlan
 * @date 2018年10月16日
 * @version 1.0
 */
package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 失主找失物
 *
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@Mapper
public interface SzzswDao {
/*
	@Select("SELECT * FROM JSZZSW")
	public List<Map<String, Object>> getFindAllSzzswDao();
*/

    /**
     * <p>
     * Title: getDeleteSzzswDao
     */
    @Delete("DELETE FROM JSZZSW WHERE BID=#{bid}")
    public Integer getDeleteSzzswDao(@Param("bid") String bid);

    /**
     * <p>
     * Title: getUpdateSzzswDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    @Update({"UPDATE JSZZSW S SET S.CPH=#{cph},S.SJXM=#{sjxm},S.SZXM=#{szxm},"
            + "S.YSWP=#{yswp},S.CCSJ=#{ccsj},S.QSDD=#{qssj},S.WZ=#{wz},S.XZJG=#{xzjg} WHERE S.BID=#{bid}"})
    public Integer getUpdateSzzswDao(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
                                     @Param("szxm") String szxm, @Param("yswp") String yswp, @Param("ccsj") String ccsj,
                                     @Param("qssj") String qssj, @Param("wz") String wz, @Param("xzjg") String xzjg);

    /**
     * 搜索
     */
	/*@Select("SELECT * FROM JSZZSW S WHERE S.CPH LIKE #{cph} AND S.SJXM LIKE #{sjxm}")
	public List<Map<String, Object>> getFindAllNameSzzswDao(@Param("cph") String cph, @Param("sjxm") String sjxm);*/

    /**
     * 添加
     */
    @Insert("INSERT INTO JSZZSW (CPH,SJXM,SZXM,YSWP,CCSJ,QSDD,WZ,XZJG) VALUES(#{cph},#{sjxm},#{szxm},#{yswp},#{ccsj},#{qssj},#{wz},#{xzjg})")
    public Integer getInsertzzswDao(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("szxm") String szxm,
                                    @Param("yswp") String yswp, @Param("ccsj") String ccsj, @Param("qssj") String qssj, @Param("wz") String wz,
                                    @Param("xzjg") String xzjg);


    @SelectProvider(type = getszzw.class, method = "getFindAllSzzswDaos")
    public List<Map<String, Object>> getFindAllSzzswDaos(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

    class getszzw {

        public String getFindAllSzzswDaos(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and b.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += " and b.SJXM like '%" + xm + "%'";
            }


            String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM JSZZSW) b where 1=1 ";
            sql += tj;
            sql += ") m ) as COUNT, t.* from (select"
                    + " b.* from (SELECT * FROM JSZZSW ) b where 1=1 ";
            sql += tj;
            sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
            return sql;
        }
    }

    @SelectProvider(type = getszzwExport.class, method = "getFindAllSzzswDaosExport")
    List<Map<String, Object>> getFindAllSzzswDaosExport(@Param("cph") String cph, @Param("xm") String xm);

    class getszzwExport {
        public String getFindAllSzzswDaosExport(@Param("cph") String cph, @Param("xm") String xm) {

            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " AND B.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += " AND B.SJXM like '%" + xm + "%'";
            }

            String sql = "SELECT * FROM JSZZSW B WHERE 1=1 ";
            sql += tj;

            return sql;
        }
    }



   @Select("select * from jszzsw t where t.cph like #{cph}")
    public List<Map<String, Object>> SeleteSzzswDaoName(@Param("cph") String cph);

}
