package com.yc134.新生入学管理系统.dao;

public class receiveDao {
	public static int insert() {
		String sql = "insert into mgr_receive values((select max(nid) from mgr_notify),0,(select count(*) from mgr_student))";
		return jdbcTemplate.update(sql);
	}

	public static int delete(int id) {
		String sql = "delete mgr_receive where id=?";
		return jdbcTemplate.update(sql, id);
	}

	public static int update(int id) {
		String sql = "update mgr_receive set rec=rec+1,urec=urec-1 where id=?";
		return jdbcTemplate.update(sql, id);
	}

}
