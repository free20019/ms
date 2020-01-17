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
 * 服务违章接口
 *
 * @author HXX
 */
@Mapper
public interface KfuwzDao {

    /*
     * @Select("SELECT * FROM KFUWZ") public List<Map<String, Object>>
     * getByFindAll();
     */
    /*
     * @Select("SELECT distinct F.WZXZ FROM KFUWZ F") List<Map<String, Object>>
     * getwzxzSelectKfuwzDao();
     */

    /**
     * update
     */

    @Update({
            "UPDATE KFUWZ F SET F.SJNAME=#{sjxm},F.CPHAO=#{cphm},F.WZSJ=#{wzsj},F.WZDD=#{wzdd},F.WZNR=#{wznr},F.WZXZ=#{wzxz},F.KFQK=#{kfqk},F.CJSJ=#{cjsj},F.FK=#{fk} WHERE F.BID=#{bid}"})
    Integer getUpdateKfuwzDao(@Param("bid") String bid, @Param("sjxm") String sjxm, @Param("cphm") String cphm,
                              @Param("wzsj") String wzsj, @Param("wzdd") String wzdd, @Param("wznr") String wznr,
                              @Param("wzxz") String wzxz, @Param("kfqk") String kfqk, @Param("cjsj") String cjsj, @Param("fk") String fk);

    @Delete("DELETE FROM KFUWZ WHERE BID =#{bid}")
    public Integer getDeleteKfuwzDao(String bid);


    //@Select("SELECT * FROM KFUWZ F WHERE F.CPHAO LIKE #{cph}")
    //List<Map<String, Object>> getSelectNameKfuwzDao(@Param("cph") String cph);

    @SelectProvider(type = getName.class, method = "getSelectNameKfuwzDao")
    List<Map<String, Object>> getSelectNameKfuwzDao(@Param("cph") String cph);

    class getName {
        public String getSelectNameKfuwzDao(@Param("cph") String cph) {

            String tj="";

            if(cph!=null&& !cph.isEmpty()&& !cph.equals("null")&& cph.length()>0){
                tj+="AND F.CPHAO LIKE '"+cph+"'";
            }

            String sql ="SELECT F.CPHAO FROM KFUWZ F ";

            sql+=" WHERE 1=1 "+tj;
            return sql;
        }

    }

    @Insert("INSERT INTO KFUWZ (SJNAME,CPHAO,WZSJ,WZDD,WZNR,WZXZ,KFQK,CJSJ,FK) VALUES(#{sjxm},#{cphm},#{wzsj},#{wzdd},#{wznr},#{wzxz},#{kfqk},#{cjsj},#{fk})")
    Integer getInsertKfuwzDao(@Param("sjxm") String sjxm, @Param("cphm") String cphm, @Param("wzsj") String wzsj,
                              @Param("wzdd") String wzdd, @Param("wznr") String wznr, @Param("wzxz") String wzxz,
                              @Param("kfqk") String kfqk, @Param("cjsj") String cjsj, @Param("fk") String fk);

    @SelectProvider(type = getKhfw.class, method = "getByFindAllDao")
    public List<Map<String, Object>> getByFindAllDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
                                                     @RequestParam("wzxz") String wzxz, @RequestParam("pageIndex") Integer pageIndex,
                                                     @RequestParam("pageSize") Integer pageSize);

    class getKhfw {
        public String getByFindAllDao(@RequestParam("cph") String cph, @RequestParam("xm") String xm,
                                      @RequestParam("wzxz") String wzxz, @RequestParam("pageIndex") Integer pageIndex,
                                      @RequestParam("pageSize") Integer pageSize) {
            String tj = "";
            if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0&&!cph.equals("全部")) {
                tj += " and b.CPHAO LIKE '%" + cph + "%'";
            }

            if (xm != null && !xm.isEmpty() && !xm.equals("null") && xm.length() > 0) {
                tj += " and b.SJNAME LIKE '%" + xm + "%'";
            }

            if (wzxz != null && !wzxz.isEmpty() && !wzxz.equals("null") && wzxz.length() > 0) {
                tj += " and b.WZXZ LIKE '%" + wzxz + "%'";
            }

            String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KFUWZ) b where 1=1 ";
            sql += tj;
            sql += ") m ) as COUNT, t.* from (select"
                    + " b.* from (SELECT * FROM KFUWZ ) b where 1=1 ";
            sql += tj;
            sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;

            System.out.println("Sql啊啊啊啊"+sql);
            return sql;

        }

    }


    /**
     * 导出
     *
     * @param cph
     * @param sjxm
     * @param wzxz
     * @return
     */

    @SelectProvider(type = getFindAllExport.class, method = "getByFindAllDaoExport")
    List<Map<String, Object>> getByFindAllDaoExport(@Param("cph") String cph, @Param("sjxm") String sjxm,
                                                    @Param("wzxz") String wzxz);

    class getFindAllExport {

        public String getByFindAllDaoExport(@Param("cph") String cph, @Param("sjxm") String sjxm,
                                            @Param("wzxz") String wzxz) {
        	 String tj = "";
             if (cph != null && !cph.isEmpty() && !cph.equals("null") && cph.length() > 0&&!cph.equals("全部")) {
                 tj += " and b.CPHAO LIKE '%" + cph + "%'";
             }

             if (sjxm != null && !sjxm.isEmpty() && !sjxm.equals("null") && sjxm.length() > 0) {
                 tj += " and b.SJNAME LIKE '%" + sjxm + "%'";
             }

             if (wzxz != null && !wzxz.isEmpty() && !wzxz.equals("null") && wzxz.length() > 0) {
                 tj += " and b.WZXZ LIKE '%" + wzxz + "%'";
             }

             String sql = "select  t.* from (select"
                     + " b.* from (SELECT * FROM KFUWZ ) b where 1=1 ";
             sql += tj;
             sql += " ) t ";

             System.out.println("Sql啊啊啊啊"+sql);
             return sql;

        }

    }
}
