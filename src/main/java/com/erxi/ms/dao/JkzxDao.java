package com.erxi.ms.dao;

import com.erxi.ms.domain.VehicleLklh;
import com.erxi.ms.domain.VehicleWyc;
import com.erxi.ms.domain.VehicleXyc;
import com.erxi.ms.domain.VehicleZf;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:监控中心
 * @date: 2020/8/26 - 10:08
 */
@Mapper
@Repository
public interface JkzxDao {

    @Select("<script>" +
            " select pid, center_longi, center_lati, sum(veh_count) veh_count from tb_cluster" +
            " where lev = #{lev} and veh_type in" +
            " <foreach collection='veh_type' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>" +
            " GROUP BY pid order by pid" +
            "</script>")
    List<LinkedHashMap<String, Object>> getMapCluster(Map<String, Object> map);

    @Select("select sum(veh_count) count, veh_type type from tb_cluster where lev = '0' GROUP BY veh_type order by veh_type")
    List<Map<String, Object>> getVehicleOnline();

    @Select("select * from VW_VEHICLE t left join tb_mdt_status s on t.mdt_no=s.mdt_no where t.VEHI_NO like '浙A%' and s.STIME<= NOW()")
    List<VehicleXyc> getMonitorXyc();

    @Select("select * from tb_vehicle_gps_status a left join tb_wyc_global_company b on a.companyid=b.companyid where a.vehicleno like '浙A%'")
    List<VehicleWyc> getMonitorWyc();

    @Select("select a.* from tb_non_mdt_status a where a.vehi_num like '浙A%' and a.OWNER_NAME <> '' and a.STIME <= NOW()")
//    @Select("SELECT * FROM(SELECT * FROM tb_non_mdt_status  ORDER BY stime DESC) a GROUP BY VEHI_NUM")
    List<VehicleLklh> getMonitorLklh();

    @Results(id = "userMap",value = {
            @Result(column = "updateTime",property = "speed_time"),
            @Result(column = "speed_time",property = "speed_time_old"),
    })
    @Select("<script>" +
            " select v.vehi_no VEHICLE_NUM,a.*,v.* from tb_zf_gps_loc a " +
            " left join (select v.vehi_no,v.deviceid,s.* from tb_structure s,tb_video_vehicle v where s.code = v.code) v on v.deviceid=a.VEHICLE_NUM" +
            " where (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and v.vehi_no  like '浙A%' and v.structure_name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and v.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by v.code, a.VEHICLE_NUM" +
            "</script>")
//    List<LinkedHashMap<String, Object>> getMonitorZfc();
    List<VehicleZf> getMonitorZfc(Map<String, Object> map);

    @ResultMap("userMap")
    @Select("select a.* from tb_zf_gps_loc a where a.VEHICLE_TYPE='5' and a.VEHICLE_NUM  like '浙A%'")
    List<VehicleZf> getMonitorZfzd();

    @ResultMap("userMap")
    @Select("<script>" +
            " select a.*,c.* from tb_zf_gps_loc a" +
            " left join tb_zf_intercom c on c.phone = a.VEHICLE_NUM" +
            " where a.VEHICLE_TYPE='4' and c.name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by c.company, a.VEHICLE_NUM" +
            "</script>")
    List<VehicleZf> getMonitorDjj(Map<String, Object> map);

    @Select("select a.* from tb_zh_compinfo a")
    List<Map<String, Object>> getBrigadeLocation();

    @Select("<script>" +
            " select * from VW_VEHICLE a" +
            " left join tb_mdt_status b on a.mdt_no=b.mdt_no" +
            " where a.VEHI_NO like '浙A%' and b.STIME &lt;= NOW()" +
            " <if test='minlng!=null and minlng != \"\"  '>" +
            " and b.longi &gt;= #{minlng}" +
            " </if>" +
            " <if test='maxlng!=null and maxlng != \"\"  '>" +
            " and b.longi &lt;= #{maxlng}" +
            " </if>" +
            " <if test='minlat!=null and minlat != \"\"  '>" +
            " and b.lati &gt;= #{minlat}" +
            " </if>" +
            " <if test='maxlat!=null and maxlat != \"\"  '>" +
            " and b.lati &lt;= #{maxlat}" +
            " </if>" +
            "</script>")
    List<VehicleXyc> findAreaVehicleXyc(HashMap<String, Object> map);

    @Select("<script>" +
            " select * from tb_vehicle_gps_status a" +
            " left join tb_wyc_global_company b on a.companyid=b.companyid" +
            " where 1=1 and a.vehicleno like '浙A%' and a.positiontime &lt;= NOW()" +
            " <if test='minlng!=null and minlng != \"\"  '>" +
            " and longitude &gt;= #{minlng}" +
            " </if>" +
            " <if test='maxlng!=null and maxlng != \"\"  '>" +
            " and longitude &lt;= #{maxlng}" +
            " </if>" +
            " <if test='minlat!=null and minlat != \"\"  '>" +
            " and latitude &gt;= #{minlat}" +
            " </if>" +
            " <if test='maxlat!=null and maxlat != \"\"  '>" +
            " and latitude &lt;= #{maxlat}" +
            " </if>" +
            " <if test='vehicle!=null'>" +
            " and a.vehicleno = #{vehicle}" +
            " </if>" +
            "</script>")
    List<VehicleWyc> findAreaVehicleWyc(HashMap<String, Object> map);

    @Select("<script>" +
            " select a.* from tb_non_mdt_status a" +
            " where a.vehi_num like '浙A%' and a.STIME &lt;= NOW()" +
            " <if test='minlng!=null and minlng != \"\"  '>" +
            " and a.longi &gt;= #{minlng}" +
            " </if>" +
            " <if test='maxlng!=null and maxlng != \"\"  '>" +
            " and a.longi &lt;= #{maxlng}" +
            " </if>" +
            " <if test='minlat!=null and minlat != \"\"  '>" +
            " and a.lati &gt;= #{minlat}" +
            " </if>" +
            " <if test='maxlat!=null and maxlat != \"\"  '>" +
            " and a.lati &lt;= #{maxlat}" +
            " </if>" +
            "</script>")
    List<VehicleLklh> findAreaVehicleLklh(HashMap<String, Object> map);

    @Select("<script>" +
            " select a.* from tb_monitor_vehicle a" +
            " where 1=1" +
            " <if test='vehicle_type!=null and vehicle_type != \"\"  '>" +
            " and a.vehicle_type=#{vehicle_type}" +
            " </if>" +
            " <if test='vehicle_no!=null and vehicle_no != \"\"  '>" +
            " and a.vehicle_no=#{vehicle_no}" +
            " </if>" +
            "</script>")
    List<LinkedHashMap<String, Object>> getMonitorVehicle(Map<String, Object> map);

    @Insert("insert into tb_monitor_vehicle (vehicle_no, vehicle_type) values (#{vehicle_no}, #{vehicle_type})")
    int addMonitorVehicle(Map<String, Object> map);

    @Update("<script>" +
            " delete from tb_monitor_vehicle" +
            " where 1=1" +
            " <if test='vehicle_type!=null and vehicle_type != \"\"  '>" +
            " and vehicle_type in" +
            " <foreach collection='vehicle_type' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>" +
            " </if>" +
            " <if test='vehicle_no!=null and vehicle_no != \"\"  '>" +
            " and a.vehicle_no=#{vehicle_no}" +
            " </if>" +
            "</script>")
    int deleteMonitorVehicle(Map<String, Object> map);

    @Select("select distinct t.vehi_no vehicle,t.vehi_sim id from VW_VEHICLE t left join tb_mdt_status s  on t.mdt_no=s.mdt_no  where t.VEHI_NO like '浙A%' ")
    List<Map<String, Object>> getVehicleXyc();

    @Select("select distinct a.vehicleno vehicle from tb_vehicle_gps_status a left join tb_wyc_global_company b on a.companyid=b.companyid where a.vehicleno like '浙A%'")
    List<Map<String, Object>> getVehicleWyc();

    @Select("select distinct a.vehi_num vehicle from tb_non_mdt_status a  where a.vehi_num like '浙A%'")
    List<Map<String, Object>> getVehicleLklh();

    //    @Select("select distinct a.vehicle_num vehicle from tb_zf_gps_loc a where (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and a.VEHICLE_NUM  like '浙A%' ")
    @Select("<script>" +
            " select  distinct v.vehi_no vehicle from tb_zf_gps_loc a " +
            " left join (select v.vehi_no,v.deviceid,s.* from tb_structure s,tb_video_vehicle v where s.code = v.code) v on v.deviceid=a.VEHICLE_NUM" +
            " where (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and v.vehi_no  like '浙A%' and v.structure_name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and v.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by v.code, a.VEHICLE_NUM" +
            "</script>")
    List<Map<String, Object>> getVehicleZfc(Map<String, Object> map);

    @Select("select distinct a.vehicle_num vehicle from tb_zf_gps_loc a where a.VEHICLE_TYPE='5' and a.VEHICLE_NUM  like '浙A%'")
    List<Map<String, Object>> getVehicleZfzd();

    @Select("<script>" +
            " select distinct a.vehicle_num vehicle from tb_zf_gps_loc a " +
            " left join tb_zf_intercom c on c.phone = a.VEHICLE_NUM" +
            " where a.VEHICLE_TYPE='4'" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            "</script>")
    List<Map<String, Object>> getVehicleDjj(Map<String, Object> map);

    @Select("select a.* from ${table} a where vehicle = #{vehicle} and  time >= #{stime} and  time < #{etime} order by time")
    List<Map<String, Object>> getTrajectoryXyc(Map<String, Object> map);

    @Select("select a.*,b.* from ${table} a " +
            " left join tb_wyc_global_company b on a.companyid=b.companyid" +
            " where VehicleNo = #{vehicle} and  PositionTime >= #{stime} and  PositionTime < #{etime} order by PositionTime")
    List<Map<String, Object>> getTrajectoryWyc(Map<String, Object> map);

    @Select("select a.*, b.* from ${table} a" +
            " left join tb_lklh_global_vehicle b on a.VEHI_NUM=b.PLATE_NUMBER" +
            " where a.VEHI_NUM = #{vehicle} and  a.stime >= #{stime} and  a.stime < #{etime} order by stime")
    List<Map<String, Object>> getTrajectoryLklh(Map<String, Object> map);

//    @Select("select a.* from ${table} a where VEHICLE_NUM = #{vehicle} and  SPEED_TIME >= #{stime} and  SPEED_TIME < #{etime} and (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') order by SPEED_TIME")
//    @Select("select v.vehi_no VEHICLE_NUM, a.*,v.* from ${table} a" +
//            " left join (select v.vehi_no,v.deviceid,s.* from tb_structure s,tb_video_vehicle v where s.code = v.code) v on v.deviceid=a.VEHICLE_NUM" +
//            " where v.vehi_no = #{vehicle} and  a.SPEED_TIME >= #{stime} and  a.SPEED_TIME < #{etime} and (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') order by a.SPEED_TIME")
     @Select("<script>" +
             " select * from" +
             " (select v.vehi_no,v.deviceid,s.* from tb_structure s,tb_video_vehicle v where s.code = v.code and  v.vehi_no = #{vehicle}) v " +
             " left join ${table} a on v.deviceid=a.VEHICLE_NUM and (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') " +
             " where a.SPEED_TIME &gt;= #{stime} and  a.SPEED_TIME &lt; #{etime}" +
             " <if test='power_company!=null and power_company != \"\"  '>" +
             "  and v.structure_name in" +
             " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
             " #{item}" +
             " </foreach>"+
             " </if>" +
             " order by a.SPEED_TIME" +
             "</script>")
    List<Map<String, Object>> getTrajectoryZfc(Map<String, Object> map);

    @Select("select a.* from ${table} a where VEHICLE_NUM = #{vehicle} and  SPEED_TIME >= #{stime} and  SPEED_TIME < #{etime} and a.VEHICLE_TYPE='5' order by SPEED_TIME")
    List<Map<String, Object>> getTrajectoryZfzd(Map<String, Object> map);

//    @Select("select a.* from ${table} a where VEHICLE_NUM = #{vehicle} and  SPEED_TIME >= #{stime} and  SPEED_TIME < #{etime} and a.VEHICLE_TYPE='4' order by SPEED_TIME")
    @Select("<script>" +
            " select a.*, c.* from ${table} a" +
            " left join tb_zf_intercom c on c.phone = a.VEHICLE_NUM" +
            " where (VEHICLE_NUM = #{vehicle} or c.name  = #{vehicle} )" +
            " and  SPEED_TIME &gt;= #{stime} and  SPEED_TIME &lt; #{etime}" +
            " and a.VEHICLE_TYPE='4' " +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by SPEED_TIME" +
            "</script>")
    List<Map<String, Object>> getTrajectoryDjj(Map<String, Object> map);

    @Select("<script>" +
            " select  v.vehi_no VEHICLE_NUM,v.*,a.*,s.* from tb_video_vehicle v" +
            " left join tb_structure s on s.code = v.code" +
            " left join  tb_zf_gps_loc a on (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and v.deviceid=a.VEHICLE_NUM" +
            " where v.vehi_no like '浙A%' and s.structure_name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and s.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by a.speed_time desc, v.code, v.vehi_no" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsZfc(Map<String, Object> map);

    @Select("<script>" +
            " select * from tb_zf_intercom c" +
            " left join tb_zf_gps_loc l on l.VEHICLE_TYPE='4' and c.phone = l.VEHICLE_NUM" +
            " where c.company is not null and c.name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by l.speed_time desc, c.company, c.phone" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsDjj(Map<String, Object> map);

    @Select("<script>" +
            " select distinct s.structure_name,s.code from tb_video_vehicle v" +
            " left join tb_structure s on s.code = v.code" +
            " left join  tb_zf_gps_loc a on (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and v.deviceid=a.VEHICLE_NUM" +
            " where v.vehi_no like '浙A%' and s.structure_name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and s.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by s.code, s.structure_name" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsMenuZfc(Map<String, Object> map);

    @Select("<script>" +
            " select distinct c.company from tb_zf_intercom c" +
            " left join tb_zf_gps_loc l on l.VEHICLE_TYPE='4' and c.phone = l.VEHICLE_NUM" +
            " where c.company is not null  and c.name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by c.id, c.company" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsMenuDjj(Map<String, Object> map);

    @Select("<script>" +
            " select v.vehi_no VEHICLE_NUM,v.*,a.*,s.* from tb_video_vehicle v" +
            " left join tb_structure s on s.code = v.code" +
            " left join  tb_zf_gps_loc a on (a.VEHICLE_TYPE='1' or a.VEHICLE_TYPE='2' or a.VEHICLE_TYPE='3') and v.deviceid=a.VEHICLE_NUM" +
            " where v.vehi_no like '浙A%' and s.structure_name = #{name}" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and s.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by a.speed_time desc, v.code, v.vehi_no" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsDetailsZfc(HashMap<String, Object> map);

    @Select("<script>" +
            " select * from tb_zf_intercom c" +
            " left join tb_zf_gps_loc l on l.VEHICLE_TYPE='4' and c.phone = l.VEHICLE_NUM" +
            " where c.company = #{name} and c.name is not null" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and c.company in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " order by l.speed_time desc, c.company, c.phone" +
            "</script>")
    List<Map<String, Object>> getMonitorDetailsDetailsDjj(HashMap<String, Object> map);

    @Select("select distinct station_name from tb_station")
    List<LinkedHashMap<String, Object>> getAllStation(Map<String, Object> map);

    @Select("select distinct * from tb_station where station_name = #{station_name}")
    List<LinkedHashMap<String, Object>> getStationLocation(Map<String, Object> map);

    @Select("select distinct * from tb_station_video" +
            " where station_id = #{station_id} and ((floor = #{floor} and is_indoor= 1) or (is_indoor= 0))")
    List<LinkedHashMap<String, Object>> getStationCamera(Map<String, Object> map);


    @Select("<script>" +
            " select case when FIND_IN_SET(#{user_id},a.invite_user_ids) then '1' else '0' END is_in ,a.* from tb_video_room a" +
            " where 1=1" +
            " <if test='room_name!=null and room_name != \"\"  '>" +
            " and a.room_name=#{room_name}" +
            " </if>" +
            " <if test='room_id!=null and room_id != \"\"  '>" +
            " and a.room_id=#{room_id}" +
            " </if>" +
            " order by create_time desc" +
            "</script>")
    List<LinkedHashMap<String, Object>> getVideoRoom(Map<String, Object> map);

    @Insert("insert into tb_video_room (room_name, room_url, wait_time, invite_user_ids, invite_users, create_userid, create_username)" +
            " values (#{room_name}, #{room_url}, #{wait_time}, #{invite_user_ids}, #{invite_users}, #{create_userid}, #{create_username})")
    @Options(useGeneratedKeys=true, keyProperty="room_id", keyColumn="room_id")
    int addVideoRoom(Map<String, Object> map);

    @Update("update tb_video_room set is_deleted=1, release_time = now() where room_id=#{room_id}")
    int deleteVideoRoom(Map<String, Object> map);

    @Select("select id,username from tb_taxi_user")
    List<LinkedHashMap<String, Object>> getUser();

    @Insert("insert into tb_video_invite_record (username, user_id, room_id) values (#{username}, #{user_id}, #{room_id})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int addVideoInvite(Map<String, Object> map);

    @Select("select count(1) from tb_video_room where is_deleted=0 and room_id=#{room_id}")
    int getVideoRoomByIdOne(Map<String, Object> map);

    @Select("select count(1) from tb_video_room where is_deleted=0 and room_id=#{room_id}" +
            " and (now() <= DATE_ADD(create_time,INTERVAL wait_time SECOND) or (select count(1) from tb_video_invite_record where room_id=#{room_id} and join_time is not null and leave_time is null)>1 )")
    int getVideoRoomByIdTwo(Map<String, Object> map);

    @Update("update tb_video_invite_record set join_time = now(), leave_time = null where room_id=#{room_id} and user_id = #{user_id}")
    void updateVideoRecord(Map<String, Object> map);

    @Select("<script>" +
            " select v.deviceid,v.vehi_no,v.structure_name,sum(o.online_time) online_time,sum(m.mileage) mileage from devicetbl b" +
            " left join (select s.structure_name, v.vehi_no, v.deviceid from tb_video_vehicle v,tb_structure s where s.code = v.code) v on b.deviceaccount =  v.deviceid" +
            " left join device_online_rate o on b.deviceaccount = o.deviceaccount" +
            " left join mileage_statistics m on m.date=o.stat_date and b.deviceaccount = m.device_index_code" +
            " where v.vehi_no like '浙A%'" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and v.structure_name  in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " <if test='structure_name!=null and structure_name != \"\" and structure_name.length > 0 '>" +
            " and v.structure_name  in" +
            " <foreach collection='structure_name'  index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " <if test='vehi_no!=null and vehi_no != \"\" and vehi_no.length > 0 '>" +
            " and v.vehi_no  in" +
            " <foreach collection='vehi_no' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            " <if test='stime!=null and stime != \"\"  '>" +
            " and o.stat_date &gt;= #{stime}" +
            " </if>" +
            " <if test='etime!=null and etime != \"\"  '>" +
            " and o.stat_date &lt;= #{etime}" +
            " </if>" +
            " group by v.deviceid,v.vehi_no,v.structure_name" +
            " order by v.structure_name, v.vehi_no" +
            "</script>")
    List<LinkedHashMap<String, Object>> getZfcOnlineTimeStatistics(Map<String, Object> map);

//    @Select("<script>" +
//            " select z.*,v.* from tb_zxtj z" +
//            " left join (select s.structure_name, v.vehi_no, v.deviceid from tb_video_vehicle v,tb_structure s where s.code = v.code) v on v.deviceid = z.vehicleNum" +
//            " where v.vehi_no like '浙A%' and OnlineTime1 >0" +
//            " <if test='power_company!=null and power_company != \"\"  '>" +
//            "  and v.structure_name  in" +
//            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
//            " #{item}" +
//            " </foreach>"+
//            " </if>" +
//            " <if test='structure_name!=null and structure_name != \"\"  '>" +
//            " and v.structure_name = #{structure_name}" +
//            " </if>" +
//            " <if test='vehi_no!=null and vehi_no != \"\"  '>" +
//            " and v.vehi_no = #{vehi_no}" +
//            " </if>" +
//            " <if test='stime!=null and stime != \"\"  '>" +
//            " and z.date &gt;= #{stime}" +
//            " </if>" +
//            " <if test='etime!=null and etime != \"\"  '>" +
//            " and z.date &lt;= #{etime}" +
//            " </if>" +
//            " order by z.date desc, v.vehi_no" +
//            "</script>")
@Select("<script>" +
        " select v.*,o.online_time,o.stat_date,m.mileage from devicetbl b" +
        " left join (select s.structure_name, v.vehi_no, v.deviceid from tb_video_vehicle v,tb_structure s where s.code = v.code) v on b.deviceaccount =  v.deviceid" +
        " left join device_online_rate o on b.deviceaccount = o.deviceaccount" +
        " left join mileage_statistics m on m.date=o.stat_date and b.deviceaccount = m.device_index_code" +
        " where v.vehi_no like '浙A%'" +
        " <if test='power_company!=null and power_company != \"\"  '>" +
        "  and v.structure_name  in" +
        " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
        " #{item}" +
        " </foreach>"+
        " </if>" +
        " <if test='structure_name!=null and structure_name != \"\"  and structure_name.length > 0  '>" +
        " and v.structure_name  in" +
        " <foreach collection='structure_name' index='index' item='item' open='(' separator=',' close=')'>" +
        " #{item}" +
        " </foreach>"+
        " </if>" +
        " <if test='vehi_no!=null and vehi_no != \"\"  and vehi_no.length > 0  '>" +
        " and v.vehi_no  in" +
        " <foreach collection='vehi_no' index='index' item='item' open='(' separator=',' close=')'>" +
        " #{item}" +
        " </foreach>"+
        " </if>" +
        " <if test='stime!=null and stime != \"\"  '>" +
        " and o.stat_date &gt;= #{stime}" +
        " </if>" +
        " <if test='etime!=null and etime != \"\"  '>" +
        " and o.stat_date &lt;= #{etime}" +
        " </if>" +
        " order by o.stat_date desc, v.structure_name, v.vehi_no" +
        "</script>")
    List<LinkedHashMap<String, Object>> getZfcOnlineTime(Map<String, Object> map);

    @Select("<script>" +
            " select distinct v.vehi_no, v.deviceid, v.code from tb_video_vehicle v" +
            " left join  tb_structure s on s.code = v.code" +
            " where 1=1" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and s.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            "</script>")
    List<LinkedHashMap<String, Object>> getAllZfc(Map<String, Object> map);

    @Select("<script>" +
            " select distinct code, structure_name from tb_structure s" +
            " where 1=1" +
            " <if test='power_company!=null and power_company != \"\"  '>" +
            "  and s.structure_name in" +
            " <foreach collection='power_company' index='index' item='item' open='(' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>"+
            " </if>" +
            "</script>")
    List<LinkedHashMap<String, Object>> getStructure(Map<String, Object> map);
}
