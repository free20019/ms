package com.erxi.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleZf {

	private String vehicle_num;
	private double longi;
	private double lati;
	private String speed_time;
	private String speed_time_old;
	private String vehicle_type;

	//对讲机
	private String name;
	private String company;

	//执法车
	private String structure_name;

}
