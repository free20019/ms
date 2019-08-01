package com.erxi.ms.access;

import com.erxi.ms.domain.User;

public class UserContext {
	/**
	 * 当前线程绑定 
	 * 
	 * 多线程环境下安全
	 */
	
	private static ThreadLocal<User> LocalUser = new ThreadLocal<User>();

	public static void setUser(User user) {
		LocalUser.set(user);
	}

	public static User getUser() {
		return LocalUser.get();
	}

}
