package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class majorDao {
	// 查询全部专业
	public List<Map<String, Object>> selectAll() {
		String sql = "select * from mgr_major";
		return jdbcTemplate.select(sql);
	}

	// 根据编号
	public List<Map<String, Object>> selectByMid(String mid) {
		String sql = "select * from mgr_major where mid=?";
		return jdbcTemplate.select(sql, mid);
	}

	// 根据学院
	public List<Map<String, Object>> selectByName(String name) {
		String sql = "select * from mgr_major a join mgr_college b on a.id=b.id join mgr_class c on a.mid=c.mid where b.name=?";
		return jdbcTemplate.select(sql, name);
	}

	// 根据学院差专业
	public List<Map<String, Object>> selectByMname(String name) {
		String sql = "select a.mid,mname,a.id,name,cid,cname from mgr_major a join mgr_college b on a.id=b.id join mgr_class c on a.mid=c.mid where b.name=?";
		return jdbcTemplate.select(sql, name);
	}

	// 根据专业查询学院
	public List<Map<String, Object>> selectCollege(String mname) {
		String sql = "select name from mgr_major a join mgr_college b on b.id=a.id where mname=?";
		return jdbcTemplate.select(sql, mname);
	}
}
