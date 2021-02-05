package com.erxi.ms.redis;

public class ZlKey extends BasePrefix {

	private ZlKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	//营运车辆数
	public static ZlKey busyVehi = new ZlKey(500, "bv");
	//行业营运状况
	public static ZlKey busyStatus = new ZlKey(500, "bs");
	//行业运行情况
	public static ZlKey busyOnline = new ZlKey(500, "bo");
	
	//业户
	public static ZlKey Comp = new ZlKey(500, "comp");
	//车辆
	public static ZlKey Vehi = new ZlKey(500, "vehi");
	//驾驶员
	public static ZlKey Per = new ZlKey(500, "per");
	
	//重点抓拍车辆
	public static ZlKey Focus = new ZlKey(500, "focus");
	//故障车辆抓拍
	public static ZlKey Fault = new ZlKey(500, "fault");
	
	
	//车载 故障
	public static ZlKey machine = new ZlKey(500, "mac");
	//投诉
	public static ZlKey complaint = new ZlKey(500, "cl");
	
	
	//下拉公司
	public static ZlKey selectcomp = new ZlKey(500, "sc");
	//下拉车辆
	public static ZlKey selectvehi = new ZlKey(500, "sv");
	//下拉区域
	public static ZlKey selectarea = new ZlKey(500, "sa");

	//监控中心车辆类型(定位)
	//出租（巡游车）
	public static ZlKey monitorXyc = new ZlKey(500, "mxyc");
	//网约
	public static ZlKey monitorWyc = new ZlKey(500, "mwyc");
	//两客两货
	public static ZlKey monitorLklh = new ZlKey(500, "mlklh");
	//执法车
	public static ZlKey monitorZfc = new ZlKey(500, "mzfc");
	//执法终端
	public static ZlKey monitorZfzd = new ZlKey(500, "mzfzd");
	//对讲机
	public static ZlKey monitorDjj = new ZlKey(500, "mdjj");

	//监控中心车辆类型(车辆)
	//出租（巡游车）
	public static ZlKey vehicleXyc = new ZlKey(500, "vxyc");
	//网约
	public static ZlKey vehicleWyc = new ZlKey(500, "vwyc");
	//两客两货
	public static ZlKey vehicleLklh = new ZlKey(500, "vlklh");
	//执法车
	public static ZlKey vehicleZfc = new ZlKey(500, "vzfc");
	//执法终端
	public static ZlKey vehicleZfzd = new ZlKey(500, "vzfzd");
	//对讲机
	public static ZlKey vehicleDjj = new ZlKey(500, "vdjj");
}
