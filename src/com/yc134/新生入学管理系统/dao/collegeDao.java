package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class collegeDao {
	// 查询全部学院
	public List<Map<String, Object>> selectAll1() {
		String sql = "select * from mgr_college ";
		return jdbcTemplate.select(sql);
	}

	// 查询全部专业
	public List<Map<String, Object>> selectAll() {
		String sql = "select * from mgr_class a join mgr_major b on a.mid=b.mid join mgr_college c on c.id=b.id ";
		return jdbcTemplate.select(sql);
	}

	// 根据编号
	public List<Map<String, Object>> selectById(String id) {
		String sql = "select * from mgr_college where id=?";
		return jdbcTemplate.select(sql, id);
	}

	// 查询全部专业通过学院
	public List<Map<String, Object>> selectAll2(String name) {
		String sql = "select * from mgr_major b join mgr_college c on c.id=b.id where name=?";
		return jdbcTemplate.select(sql, name);
	}

}
