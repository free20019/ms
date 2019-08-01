package com.erxi.ms.redis;

public class UserKey extends BasePrefix {

	public static final int TOKEN_EXPIRE = 3600 * 24 * 1;

	private UserKey(String prefix) {
		super(prefix);
	}

	private UserKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");
	public static UserKey getByUsername = new UserKey(0, "username");
}
