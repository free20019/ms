package com.erxi.ms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InitDao {

	/**
	 * 查询所有车辆信息
	 */
	
//	@Select("select * from VW_VEHICLE@db69 t left join tb_mdt_status@db69 s  on t.mdt_no=s.mdt_no")
	@Select("select * from VW_VEHICLE t left join tb_mdt_status s  on t.mdt_no=s.mdt_no")
	public List<Map<String, Object>> getVehicleList();
	
	/**
	 * 查询所有区域
	 */
	@Select("select * from TB_AREA t")
	public List<Map<String, Object>> getAreaList();

}

