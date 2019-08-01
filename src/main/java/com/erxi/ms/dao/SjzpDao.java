/**
 * <p>Title: SjzpDao.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2017</p>
 *
 * <p>Company: www.baidudu.com</p>
 *
 * @author shenlan
 * @date 2018年10月15日
 * @version 1.0
 */
package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 作者： Mar小坏
 * @date 2018年10月15日
 */
@Mapper
public interface SjzpDao {

/*	@Select("SELECT * FROM JSJZP")
	public List<Map<String, Object>> getSelectFindAllSjzp();*/

    /**
     * 搜索
     */

	/*@Select("SELECT * FROM JSJZP S WHERE S.LXR LIKE #{lxr}")
	public List<Map<String, Object>> getSelectNameSjzp(@Param("lxr") String lxr);*/

    /**
     *
     * <p>
     * Title: getUpdateAllSjzpDao 修改
     * </p>
     */
    @Update({"UPDATE JSJZP J SET J.CPH=#{cph},J.CLXYNX=#{clxynx},J.SJNL=#{sjnl},J.XB=#{xb},J.BC=#{bc},"
            + "J.JBDD=#{jbdd},J.LXR=#{lxr},J.HJ=#{hj},J.CYNX=#{cynx},J.XJ=#{xj},J.SJXYE=#{sjxye},"
            + "J.LXDH=#{lxdh},J.YX=#{yx},J.DJRQ=#{djrq},J.BZ=#{bz} WHERE J.BID=#{bid}"})
    public Integer getUpdateAllSjzpDao(@Param("bid") String bid, @Param("cph") String cph,
                                       @Param("clxynx") String clxynx, @Param("sjnl") String sjnl, @Param("xb") String xb, @Param("jl") String jl,
                                       @Param("bc") String bc, @Param("jbdd") String jbdd, @Param("lxr") String lxr, @Param("hj") String hj,
                                       @Param("cynx") String cynx, @Param("xj") String xj, @Param("sjxye") String sjxye,
                                       @Param("lxdh") String lxdh, @Param("yx") String yx, @Param("djrq") String djrq, @Param("bz") String bz);

    /**
     *
     * <p>
     * Title: getDeleteSjzp
     * </p>
     *
     * <p>
     * Description:
     * </p>
     *
     *
     */
    @Delete("DELETE FROM JSJZP  WHERE BID=#{bid}")
    public Integer getDeleteSjzp(@Param("bid") String bid);

    // @Insert("INSERT INTO JSJZP
    // VALUES(SEQUE_SJZP.NEXTVAL,#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{},#{})")
    @Insert("INSERT INTO JSJZP(CPH,CLXYNX,SJNL,XB,HJ,CYNX,JL,BC,XJ,JBDD,SJXYE,LXDH,YX,LXR,DJRQ,BZ) VALUES(#{cph},#{clxynx},#{sjnl},#{xb},#{hj},#{cynx},#{jl},#{bc},#{xj},#{jbdd},#{sjxye},#{lxdh},#{yx},#{lxr},#{djrq},#{bz})")
    public Integer getInsertSjzp(@Param("cph") String cph, @Param("clxynx") String clxynx, @Param("sjnl") String sjnl,
                                 @Param("xb") String xb, @Param("hj") String hj, @Param("cynx") String cynx, @Param("jl") String jl,
                                 @Param("bc") String bc, @Param("xj") String xj, @Param("jbdd") String jbdd, @Param("sjxye") String sjxye,
                                 @Param("lxdh") String lxdh, @Param("yx") String yx, @Param("lxr") String lxr, @Param("djrq") String djrq,
                                 @Param("bz") String bz);


    @SelectProvider(type = getSjzp.class, method = "getSelectFindAllSjzps")
    public List<Map<String, Object>> getSelectFindAllSjzps(@RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);


    class getSjzp {
        public String getSelectFindAllSjzps(@RequestParam("xm") String xm, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
            String tj = "";

            if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
                tj += "and B.LXR LIKE '%" + xm + "%'";
            }

            String sql = "select (select count(*) COUNT from (select * from (select gv.* from JSJZP gv) b where 1=1 ";
            sql += tj;
            sql += ") m ) as COUNT, t.*  from (select"
                    + " b.* from (select gv.* from JSJZP gv) b where 1=1 ";
            sql += tj;
            sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
            return sql;
        }
    }


    @SelectProvider(type = getSjzpExport.class, method = "getSelectFindAllSjzpsExport")
    public List<Map<String, Object>> getSelectFindAllSjzpsExport(@RequestParam("xm") String xm);

    class getSjzpExport {
        public String getSelectFindAllSjzpsExport(@RequestParam("xm") String xm) {

            String tj = "";

            if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
                tj += "AND B.LXR LIKE '%" + xm + "%'";
            }

            String sql = "SELECT * FROM JSJZP B WHERE 1=1 ";
            sql += tj;
            return sql;
        }
    }
}
