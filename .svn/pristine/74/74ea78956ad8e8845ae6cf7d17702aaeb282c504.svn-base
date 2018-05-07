package com.guoll.modules.framework.page;

import org.apache.ibatis.session.RowBounds;

public class RowBounsUtil {

	public static Integer startRow=1;
	public static Integer endRow=2147483647;
	public static RowBounds getRowBounds(Integer page,Integer rows){
		startRow = (page-1)*rows;
		endRow = rows;
		return new RowBounds(startRow,endRow);
	}
}
