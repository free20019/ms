/**
 * <p>Title: JxcsgDao.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: www.baidudu.com</p>
 *
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
 * @author 作者： Mar小坏
 * @date 2018年10月12日
 */
@Mapper
public interface JxcsgDao {

	/*@Select("SELECT X.BID, X.CPH,X.XM,X.FSRQ,X.BARQ,X.SGDD,X.BCCS,X.DFSS,X.SGZE,X.SGZR,S.SGLB FROM JXCSG X INNER JOIN SGLBFEN S ON X.SGLB=S.BID")
	public List<Map<String, Object>> getSelectFindAllJxcsgDao();*/

    /**
     * 下拉框
     *
     * <p>
     * Title: getSelectFindAllJSglbDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
	/*@Select("SELECT * FROM SGLBFEN")
	public List<Map<String, Object>> getSelectFindAllJSglbDao();*/

    /**
     * <p>
     * Title: getUpdateJxcsgDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    /*
     * @Update({
     * "UPDATE JXCSG X SET X.CPH=#{cph},X.XM=#{xm},X.FSRQ=#{fsrq},X.BARQ=#{barq}, X.BCCS=#{bccs},X.DFSS=#{dfss},X.SGZE=#{sgze},X.SGLB=#{sglb},X.SGZR=#{sgzr},X.SGDD=#{sgdd}, WHERE X.BID=#{bid}"
     * })
     */
    @Update({
            "UPDATE JXCSG X SET X.CPH=#{cph},X.XM=#{xm},X.FSRQ=#{fsrq},X.BARQ=#{barq},X.SGDD=#{sgdd},X.SGLB=#{sglb},X.BCCS=#{bccs},X.DFSS=#{dfss},X.SGZE=#{sgze},X.SGZR=#{sgzr} WHERE X.BID=#{bid}"})
    public Integer getUpdateJxcsgDao(@Param("bid") String bid, @Param("cph") String cph, @Param("xm") String xm,
                                     @Param("fsrq") String fsrq, @Param("barq") String barq, @Param("sgdd") String sgdd,
                                     @Param("sglb") String sglb, @Param("bccs") String bccs, @Param("dfss") String dfss,
                                     @Param("sgze") String sgze, @Param("sgzr") String sgzr);

    /**
     * <p>
     * Title: getDeleteJSglbDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    // @Delete("DELETE FROM KBLJL WHERE BID = #{bid}")
    @Delete("DELETE FROM JXCSG WHERE BID=#{bid}")
    public Integer getDeleteJSglbDao(@Param("bid") String bid);


    /**
     * 添加
     */
    @Insert("INSERT INTO JXCSG (CPH,XM,FSRQ,BARQ,SGDD,SGLB,BCCS,DFSS,SGZE,SGZR) VALUES(#{cph},#{xm},#{fsrq},#{barq},#{sgdd},#{sglb},#{bccs},#{dfss},#{sgze},#{sgzr})")
    public Integer getInsertJxcsgDao(@Param("cph") String cph, @Param("xm") String xm, @Param("fsrq") String fsrq,
                                     @Param("barq") String barq, @Param("sgdd") String sgdd, @Param("sglb") String sglb,
                                     @Param("bccs") String bccs, @Param("dfss") String dfss, @Param("sgze") String sgze,
                                     @Param("sgzr") String sgzr);

    @SelectProvider(type = getxcsg.class, method = "getSelectFindAllJSglbDao")
    public List<Map<String, Object>> getSelectFindAllJSglbDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);


    class getxcsg {
        public String getSelectFindAllJSglbDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and b.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += "AND b.XM LIKE '%" + xm + "%'";
            }


            String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM JXCSG) b where 1=1 ";
            sql += tj;
            sql += ") m ) as COUNT, tt.* from (select t.*  from (select"
                    + " b.* from (SELECT * FROM JXCSG ) b where 1=1 ";
            sql += tj;
            sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
            return sql;
        }
    }


    @SelectProvider(type = getxcsgExport.class, method = "getSelectFindAllJSglbDaoExport")
    List<Map<String, Object>> getSelectFindAllJSglbDaoExport(@Param("cph") String cph, @Param("xm") String xm);

    class getxcsgExport {
        public String getSelectFindAllJSglbDaoExport(@Param("cph") String cph, @Param("xm") String xm) {
            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and B.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += "AND B.XM LIKE '%" + xm + "%'";
            }

            String sql="SELECT * FROM JXCSG B WHERE 1=1 ";
            sql+= tj ;

            return sql;
        }
    }


    /**
     * 搜索
     */

    // @Select("SELECT * FROM JXCSG X WHERE X.CPH LIKE #{cph} AND X.XM LIKE
    // #{xm}")
    @Select("SELECT X.BID,X.CPH,X.XM,X.FSRQ,X.BARQ,X.SGDD,X.BCCS,X.DFSS,X.SGZE,X.SGZR FROM JXCSG X WHERE X.CPH LIKE #{cph}")
    public List<Map<String, Object>> getSelectNameFindAllJxcsgDao(@Param("cph") String cph);
}
