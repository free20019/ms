package com.erxi.ms.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.RequestParam;

import com.erxi.ms.domain.Vehicle;

import net.sf.json.JSONObject;

/**
 * 考核服務接口
 * @author HXX
 *
 */

@Mapper
public interface KhxxDao {
	
	@SelectProvider(type=getKhxx.class,method="getByKHBFindAll")
	public List<Map<String ,Object>> getByKHBFindAll( @RequestParam("starttime") String starttime,
			@RequestParam("storptime") String storptime, @RequestParam("pageIndex") Integer pageIndex,
			@RequestParam("pageSize") Integer pageSize);


    class getKhxx{
		public String getByKHBFindAll( @RequestParam("starttime") String starttime,
				@RequestParam("storptime") String storptime, @RequestParam("pageIndex") Integer pageIndex,
				@RequestParam("pageSize") Integer pageSize){
			String tj = "";
			if (starttime != null && !starttime.isEmpty() && !starttime.equals("null")
					&& starttime.length() > 0) {
				tj += " and b.BREAKTIME >= '" + starttime + "'";
			}

			if (storptime != null && !storptime.isEmpty() && !storptime.equals("null")
					&& storptime.length() > 0) {
				tj += " and b.BREAKTIME <= '" + storptime + "'";
			}

		
			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KHXX) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, tt.* from (select t.*  from (select"
					+ " b.* from (SELECT * FROM KHXX ) b where 1=1 ";
			sql += tj;
			sql += " ) t  limit "+((pageIndex-1)*pageSize)+","+pageSize+") tt ";

			//System.out.println("11111111"+sql);
			return sql;

			
		}
	}
    
    
    /**
     * 导出
     * @param starttime
     * @param storptime
     * @return
     */
    @SelectProvider(type=getExe.class,method="getByKHBFindEXE")
    public List<Map<String ,Object>> getByKHBFindEXE( @RequestParam("starttime") String starttime,
			@RequestParam("storptime") String storptime
			);
    
    class getExe{
    	public String getByKHBFindEXE(@RequestParam("starttime") String starttime,
    			@RequestParam("storptime") String storptime){
    		String tj = "";
			if (starttime != null && !starttime.isEmpty() && !starttime.equals("null")
					&& starttime.length() > 0) {
				tj += "t.BREAKTIME >= '" + starttime + "'";
			}else{
				String sql1="select t.* from KHXX t ";
				//System.out.println("全部"+sql1);
				return sql1;
			}

			if (storptime != null && !storptime.isEmpty() && !storptime.equals("null")
					&& storptime.length() > 0) {
				tj += " and t.BREAKTIME <= '" + storptime + "'";
			}else{
				String sql2="select t.* from KHXX t ";
				return sql2;
			}
    		
		//String sql="select t.* from (select  b.* from (SELECT * FROM KHB ) b where 1=1 ) t " + "where ";
			String sql="select t.* from KHXX t where ";
			sql+=tj;
			System.out.println(sql);
					return sql;
    		
    	}
    }
    
    
   /* @Select("select * from KHB f where f.breaktime >=#{starttime} and f.breaktime <=#{storptime}")
    public List<Map<String ,Object>> getByKHBFindEXE(@Param("starttime") String starttime, @Param("storptime") String storptime
			);*/

}
