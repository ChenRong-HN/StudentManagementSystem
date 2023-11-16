package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class managerDao {

	// 查询全部管理员
	public List<Map<String, Object>> selectAll() {
		String sql = "select * from mgr_manager";
		return jdbcTemplate.select(sql);
	}

	public List<Map<String, Object>> select_Mgr_name(String username, String password) {
		String sql = "select username from mgr_manager where username=? and password=?";
		return jdbcTemplate.select(sql, username, password);
	}
}
