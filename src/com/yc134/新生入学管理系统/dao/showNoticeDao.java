package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class showNoticeDao {

	public static List<Map<String, Object>> show_All() {
		String sql = "select * from mgr_notify";// 这个要改
		return jdbcTemplate.select(sql);
	}

	// 新增通知
	public static int insertNotice(String title, String content, String name) {
		String sql1 = "select count(nid) from mgr_notify";
		List<Map<String, Object>> s = jdbcTemplate.select(sql1);
		if (Integer.parseInt(s.get(0).get("count(nid)").toString()) != 0) {
			String sql = "insert into mgr_notify values((select max(nid) from mgr_notify)+1,?,?,?,default)";
			return jdbcTemplate.update(sql, title, content, name);
		} else {
			String sql = "insert into mgr_notify values(1,?,?,?,default)";
			return jdbcTemplate.update(sql, title, content, name);
		}

	}

	// 删除通知
	public static int deleteNotice(int id) {
		String sql = "delete mgr_notify where nid=?";
		return jdbcTemplate.update(sql, id);
	}

}
