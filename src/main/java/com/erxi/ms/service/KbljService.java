package com.erxi.ms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erxi.ms.config.DS;
import com.erxi.ms.dao.KbljlDao;
import com.erxi.ms.result.FastJsonUtil;
import com.erxi.ms.result.Result;

/**
 * 1.1.3.2.8不良记录
 * @author HXX
 *
 */

@Service
public class KbljService {
	
	
	@Autowired
	private KbljlDao bljlDao;
	
	/*@DS("datasource1")
	public Result<List<Map<String , Object>>> getBybljl(){
		List<Map<String, Object>> list = bljlDao.getKblj();
		return Result.success(list);
	}*/
	
	
	@DS("datasource1")
	public Result<List<Map<String , Object>>> getBybljlmohu(@Param("cph") String cph){
		List<Map<String, Object>> list = bljlDao.getKbljmohu(cph);
		return Result.success(list);
		
	}
	
	
	
	
	/**
	 * 修改
	 * @param BID
	 * @param CPH
	 * @param XM
	 * @param WFNR
	 * @param CLJG
	 * @return
	 */
	@DS("datasource1")
	public int getUpdateBybljl(@Param("BID") String BID,@Param("CPH") String CPH,@Param("XM") String XM,
			@Param("WFNR") String WFNR,@Param("CLJG") String CLJG){
		int updateKblj = bljlDao.getUpdateKblj(BID, CPH, XM, WFNR, CLJG);
				return updateKblj;
	}
	
	
	/**
	 * 刪除
	 * @param bid
	 * @return
	 */
	@DS("datasource1")
	public Integer deleteKblj(String bid){
	
		return bljlDao.DeleteKBLJL(bid);
		
	}
	
	/**
	 * 添加
	 * @param
	 * @return
	 */
	@DS("datasource1")
	public int Insertkbj(@Param("CPH") String CPH,@Param("XM") String XM,
			@Param("WFNR") String WFNR,@Param("CLJG") String CLJG){
				return bljlDao.InsertKBLJL( CPH, XM, WFNR, CLJG);
		
	}

	@DS("datasource1")
	public Result<List<Map<String, Object>>> getBybljlFindALLService(String cph, String xm, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = bljlDao.getKbljFindAllDao(cph,xm,pageIndex,pageSize);
		int count=0;
		if(list!=null&& list.size()>0){
			count=Integer.parseInt(String.valueOf(list.get(0).get("COUNT")));
		}
		List<Map<String, Object>> arrayList = new ArrayList<Map<String ,Object>>();
		Map map=new HashMap();
		map.put("count", count);
		map.put("list", list);
		arrayList.add(map);


		return Result.success(arrayList);
	}



	@DS("datasource1")
	public String getBybljlFindALLServiceExport(String cph, String xm) {
		List<Map<String, Object>> list = bljlDao.getKbljFindAllDaoExport(cph,xm);
		return FastJsonUtil.toJSONString(list);
	}
	
}
