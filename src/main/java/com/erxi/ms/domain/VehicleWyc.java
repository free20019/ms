package com.erxi.ms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleWyc {
//	private String companyid;
//	private String companyfullname;
//	private String areanumber;
	private String vehicleno;
//	private long vehicleregioncode;
	private String positiontime;
	private double longitude;
	private double latitude;
	private double speed;
//	private double direction;
//	private double elevation;
//	private double mileage;
//	private double encryptbs;
//	private long warnstatus;
//	private long vehstatus;
	private long bizstatus;
//	private String orderid;
//	private String license;
//	private String legal;
//	private String legalupdatetime;

	private String abb_name;
	private String name;
}
