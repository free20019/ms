package com.erxi.ms.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleLklh {
	private String vehi_id;
	private String vehi_num;
	private double longi;
	private double lati;
	private double speed;
	private double direction;
	private String stime;
	private String sim_num;
	private String ba_name;
	private String comp_name;
	private String vnt_name;
	private String owner_name;
	private String brand_name;
	private String db_time;

}
