package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class classDao {
	// 查询全部
	public List<Map<String, Object>> selectAll() {
		String sql = "select * from mgr_class a join mgr_major b on a.mid=b.mid join mgr_college c on c.id=b.id";
		return jdbcTemplate.select(sql);
	}

	// 根据编号
	public List<Map<String, Object>> selectByCMid(String cid) {
		String sql = "select * from mgr_class where cid=?";
		return jdbcTemplate.select(sql, cid);
	}

	// 根据学院
	public List<Map<String, Object>> selectById(String id) {
		String sql = "select * from mgr_class a join mgr_major b on a.mid=b.mid join mgr_college c on c.id=b.id where c.id=?";
		return jdbcTemplate.select(sql, id);
	}

	// 根据专业
	public List<Map<String, Object>> selectByMname(String mname) {
		String sql = "select * from mgr_class a join mgr_major b on a.mid=b.mid where b.mname=?";
		return jdbcTemplate.select(sql, mname);
	}

	// 组合查询根据班级名字
	public List<Map<String, Object>> selectByCname(String cname) {
		String sql = "select * from mgr_class a join mgr_major b on a.mid=b.mid join mgr_college c on c.id=b.id where a.cname=?";
		return jdbcTemplate.select(sql, cname);
	}

}
