package com.erxi.ms.redis;

public class VeKey extends BasePrefix {

	private VeKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	//实时车辆信息
	public static VeKey realtimeVehicle = new VeKey(500, "rv");
	
	//实时区域信息
	public static VeKey realtimeArea = new VeKey(500, "ra");
}
