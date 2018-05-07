package com.guoll.modules.framework.page;

public abstract class Dialect {

	public static enum Type{
		ORACLE,MYSQL
	}
	public abstract String getLimitString(String sql, int skipResults, int maxResults);
}
