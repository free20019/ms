/**
 * <p>Title: JswsjDao.java</p>
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

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 作者： Mar小坏
 * @date 2018年10月16日
 */
@Mapper
public interface JswsjDao {

    /**
     * 首页展示
     *
     * <p>
     * Title: getFindAllJswsjDao
     * </p>
     */
    /*
     * @Select("SELECT * FROM JSWSJ") public List<Map<String, Object>>
     * getFindAllJswsjDao();
     */

    /**
     *
     * <p>
     * Title: getDeleteSwsjJswsDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @Delete("DELETE FROM JSWSJ WHERE BID=#{bid}")
    public Integer getDeleteSwsjJswsDao(@Param("bid") String bid);

    /**
     *
     * <p>
     * Title: getUpdateSwsJswsDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @Update({"UPDATE JSWSJ J SET J.CPH=#{cph}," + "J.SJXM=#{sjxm},J.LXFS=#{lxfs},J.CLYS=#{clys},J.SCSJ=#{scsj},"
            + "J.XCSJ=#{xcsj},J.WPMS=#{wpms},J.BZ=#{bz} WHERE J.BID=#{bid} "})
    public Integer getUpdateSwsJswsDao(@Param("bid") String bid, @Param("cph") String cph, @Param("sjxm") String sjxm,
                                       @Param("lxfs") String lxfs, @Param("clys") String clys, @Param("scsj") String scsj,
                                       @Param("xcsj") String xcsj, @Param("wpms") String wpms, @Param("bz") String bz);

    /**
     * 搜索
     *
     * <p>
     * Title: getSelectName
     * </p>
     *
     * <p>
     * Description:
     * </p>
     */
    @Select("SELECT * FROM JSWSJ J WHERE J.CPH LIKE #{cph}")
    public List<Map<String, Object>> getSelectName(@Param("cph") String cph);

    /**
     *
     * <p>
     * Title: getInsertSwsJswsDao
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
   // @Insert("INSERT INTO JSWSJ VALUES(Seque_Swsj.Nextval,#{cph},#{sjxm},#{lxfs},#{clys},#{scsj},#{xcsj},#{wpms},#{bz})")
    @Insert("INSERT INTO JSWSJ(CPH,SJXM,LXFS,CLYS,SCSJ,XCSJ,WPMS,BZ) VALUES(#{cph},#{sjxm},#{lxfs},#{clys},#{scsj},#{xcsj},#{wpms},#{bz})")
    public Integer getInsertSwsJswsDao(@Param("cph") String cph, @Param("sjxm") String sjxm, @Param("lxfs") String lxfs,
                                       @Param("clys") String clys, @Param("scsj") String scsj, @Param("xcsj") String xcsj,
                                       @Param("wpms") String wpms, @Param("bz") String bz);




    @SelectProvider(type = getswsj.class, method = "getFindAllJswsjDao")
    public List<Map<String, Object>> getFindAllJswsjDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
                                                        @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);


    class getswsj {
        public String getFindAllJswsjDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
                                         @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {

            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and b.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += " and b.SJXM like '%" + xm + "%'";
            }


            String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM JSWSJ) b where 1=1 ";
            sql += tj;
            sql += ") m ) as COUNT, tt.* from (select t.*  from (select"
                    + " b.* from (SELECT * FROM JSWSJ ) b where 1=1 ";
            sql += tj;
            sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";
            return sql;

        }
    }

    @SelectProvider(type = getswsjExport.class, method = "getFindAllJswsjDaoExport")
    public List<Map<String, Object>> getFindAllJswsjDaoExport(@Param("cph") String cph, @Param("xm") String xm);

    class getswsjExport {
        public String getFindAllJswsjDaoExport(@Param("cph") String cph, @Param("xm") String xm) {


            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null")
                    && cph.length() > 0) {
                tj += " and b.CPH like'%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null")
                    && xm.length() > 0) {
                tj += " and b.SJXM like '%" + xm + "%'";
            }

            String sql = "SELECT * FROM JSWSJ B WHERE 1=1 ";
            sql += tj;

            return sql;
        }
    }
}
