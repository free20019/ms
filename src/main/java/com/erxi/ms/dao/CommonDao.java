package com.erxi.ms.dao;

import com.erxi.ms.domain.User;
import com.erxi.ms.entity.Action;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommonDao {
	@Select("select distinct t.AREA from (select ti.* from tb_global_vehicle gv,TB_TAXI_ILLEGAL_INFO_OUT ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%' and area is not null) t")
	List<Map<String, Object>> fingzfqy();

	@Select("select distinct t.ORGAN from (select ti.* from tb_global_vehicle gv,TB_TAXI_ILLEGAL_INFO_OUT ti where REPLACE(ti.AUTO_NUM,'.','')=gv.plate_number and gv.industry=090 and gv.business_scope_code = 1400  AND gv.STATUS_NAME='营运' AND ti.AUTO_NUM LIKE '浙A%' and ORGAN is not null) t")
	List<Map<String, Object>> fingzfbm();

	
	@Select("select * from TB_TAXI_USER where username=#{username}")
	User getByUsername(String username);

	@Insert("insert into TB_LOGIN_HISTORY (USER_NAME,LOGIN_TIME,USER_KIND) values (#{username},now(),'4')")
	public int insertLoginHistory(String username);
	
	@Select("select pic from tb_vehicle_hk_pic where pic_name=#{key}")
	InputStream getImage(String key);

	@Select("select pic from tb_vehicle_hk_pic2 where pic_name=#{key}")
	InputStream getImage2(String key);

	@Select("select * from TB_TAXI_USER where username=#{name}")
	List<Map<String, Object>> getMenu(String name);
	
	@Select("select * from TB_MENUS where name=#{name}")
	List<Map<String, Object>> getMenuOne(String name);

	@Insert("insert into tb_action_record (ACTIONDES,ACTIONTYPE,ACTIONIP,USERNAME,ACTIONTIME,METHODNAME,METHODPARAMS,ACTIONSQL,RESULT) values" +
			"(#{actionDes},#{actionType},#{actionIp},#{userName},#{actionTime},#{methodName},#{methodParams},#{sql},#{result})")
    void addDatabase(Action action);

}
