package com.yc134.新生入学管理系统.dao;

import java.util.List;
import java.util.Map;

public class PagQuDao {

	public List<Map<String, Object>> pq_show(int pageNo, int pageSize) {
		int pgn;
		pgn = (pageNo - 1) * pageSize + 1;
		String sql = "select cid,cname,cnum,rnum,unum,mname,name from (select rownum rn,a.*,b.*,c.* from mgr_class a join "
				+ " mgr_major b on a.mid=b.mid join mgr_college c on c.id=b.id) where rn between ? and ? ";
		return jdbcTemplate.select(sql, pgn, pageNo * pageSize);

	}
}
