package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 考核服務接口
 * @author HXX
 *
 */

@Mapper
public interface KHBDao {
	
	/*@Select("SELECT * FROM KHB")
	public List<Map<String ,Object>> getByKHBName();*/

	
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

		
			String sql = "select (select count(*) COUNT from (select * from (SELECT * FROM KHB) b where 1=1 ";
			sql += tj;
			sql += ") m ) as COUNT, t.*  from (select"
					+ " b.* from (SELECT * FROM KHB ) b where 1=1 ";
			sql += tj;
			sql += " ) t limit "+((pageIndex-1)*pageSize)+","+pageSize;
			return sql;

			
		}
	}

}
