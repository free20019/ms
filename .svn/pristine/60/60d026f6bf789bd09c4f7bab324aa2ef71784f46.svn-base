package com.erxi.ms.redis;

public class Monitor extends BasePrefix {

	private Monitor(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	//实时车辆信息
	public static Monitor safetyMonitor = new Monitor(500, "mon");
	
	//下拉计价器 公司
	public static Monitor selectcomp = new Monitor(500, "sc");
	//下拉维修 公司
	public static Monitor selectwxcomp = new Monitor(500, "sw");
}
