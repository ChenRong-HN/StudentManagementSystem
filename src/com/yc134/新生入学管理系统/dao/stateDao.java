package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class stateDao {
	public List<Map<String, Object>> stateQuery(int nid, int sid) {
		String sql = "select * from mgr_state where nid=? and sid=?";
		return jdbcTemplate.select(sql, nid, sid);
	}

	public static int stateInsert(int nid, int sid) {
		String sql = "insert into mgr_state values (?,?)";
		return jdbcTemplate.update(sql, nid, sid);
	}

	// 该学生已读信息的数量
	public static List<Map<String, Object>> stuState(int sid) {
		String sql = "select count(*) from mgr_notify a join mgr_state b on a.nid=b.nid where b.sid=?";
		return jdbcTemplate.select(sql, sid);
	}

	// 通知数量
	public static List<Map<String, Object>> noticeCount() {
		String sql = "select count(*) from mgr_notify";
		return jdbcTemplate.select(sql);
	}

	// 检查该学学生是否已读该条信息
	public static List<Map<String, Object>> state(int sid, int nid) {
		String sql = "select count(*) from mgr_notify a join mgr_state b on a.nid=b.nid where b.sid=? and a.nid=?";
		return jdbcTemplate.select(sql, sid, nid);
	}

	public static boolean equal(int sid) {
		List<Map<String, Object>> list;
		List<Map<String, Object>> list1;
		list = stuState(sid);
		int read = Integer.parseInt(list.get(0).get("count(*)").toString());
		list1 = noticeCount();
		int count = Integer.parseInt(list1.get(0).get("count(*)").toString());
		System.out.println(read);
		System.out.println(count);
		return read == count;
	}
}
