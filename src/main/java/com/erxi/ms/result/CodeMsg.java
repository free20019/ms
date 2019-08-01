package com.erxi.ms.result;

public class CodeMsg {

	private int code;
	private String msg;
	
	//成功
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//通用异常
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg DATA_ERROR = new CodeMsg(500100, "数据异常");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常异常:%s");
	public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500103, "访问太频繁！");
	//登录模块异常 5002XX
	public static CodeMsg SESSION_ERROR = new CodeMsg(500200,"Session不存在或者已经失效");
	public static CodeMsg USERNAME_NOT_EXIST = new CodeMsg(500201,"用户不存在");
	public static CodeMsg PASSWORD_IS_WRONG = new CodeMsg(500202,"密码错误");
	
	//图片不存在
	public static CodeMsg IMAGE_NOT_EXIST = new CodeMsg(500301,"图片不存在");

	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}

	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}
}
