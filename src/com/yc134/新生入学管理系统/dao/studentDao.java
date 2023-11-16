package com.yc134.新生入学管理系统.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.yc134.新生入学管理系统.utils.Utils;

public class studentDao {

	// 查询全部
	public List<Map<String, Object>> selectAll() {
		String sql = "select * from mgr_student";
		return jdbcTemplate.select(sql);
	}

	// 根据sid 修改学生信息
	public void updateS(String pname, String pphone, String address, String picture, String sid) {
		String sql = "update mgr_student set pname=? , pphone=? ,address=?, picture=? where sid=? ";
		jdbcTemplate.update(sql, pname, pphone, address, picture, sid);
	}

	// 根据sid 查询学生信息
	public List<Map<String, Object>> selectBySid(String sid) {
		String sql = "select * from mgr_student a join mgr_class b on a.cid=b.cid join mgr_major c on b.mid=c.mid"
				+ " join mgr_college d on c.id=d.id where sid=?";
		return jdbcTemplate.select(sql, sid);
	}

	// 查询全部sid
	public List<Map<String, Object>> selectAllSid() {
		String sql = "select sid,pwd from mgr_student";
		return jdbcTemplate.select(sql);
	}

	public List<Map<String, Object>> selectBy(String name, String mname, String cname, String sid) {
		String sql = "select name,mname,cname,sid from mgr_student a join mgr_class b on a.cid=b.cid join mgr_major"
				+ " c on b.mid=c.mid join mgr_college d on c.id=d.id where name=? and mname=? and cname=? and sid=? ";
		return jdbcTemplate.select(sql, name, mname, cname, sid);

	}

	// 修改密码
	public void updatePwd(String sid, String pwd) {
		String sql = "update mgr_student set pwd=? where sid=?";
		jdbcTemplate.update(sql, pwd, sid);
	}

	// 根据学院
	public List<Map<String, Object>> selectByCollege(String id) {
		String sql = "select * from mgr_major a join mgr_college b on a.id=b.id join mgr_class c on a.mid=c.mid join mgr_student d on c.cid=d.cid where b.id=?";
		return jdbcTemplate.select(sql, id);
	}

	// 根据专业
	public List<Map<String, Object>> selectByMajor(String mid) {
		String sql = "select * from mgr_class a join mgr_student b on a.cid=b.cid join mgr_major c on a.mid=c.mid where c.mid=?";
		return jdbcTemplate.select(sql, mid);
	}

	// 根据班级名称
	public List<Map<String, Object>> selectByCname(String cname) {
		String sql = "select * from mgr_student a join mgr_class b on a.cid=b.cid where b.cname=?";
		return jdbcTemplate.select(sql, cname);
	}

	// 修改核对状态
	public void updateCheckInfo(String idcard) {

		String sql = "update mgr_student set checkinfo='是' where idcard=?";
		jdbcTemplate.update(sql, idcard);
	}

	// 修改未到人数
	public void updateRnum(String idcard, String cname) {

		String sql = "update mgr_student set regis='是' where idcard=?";
		String sql1 = "update mgr_class set rnum=rnum+1,unum=unum-1 where cname=?";
		jdbcTemplate.update(sql, idcard);
		jdbcTemplate.update(sql1, cname);
	}

	// 查询核对状态
	public List<Map<String, Object>> checkInfo(String idcard) {
		String sql = "select checkinfo from mgr_student where idcard=?";
		return jdbcTemplate.select(sql, idcard);
	}

	public long add(String cname, String sname, String sex, String age, String checkinfo, String regis, String idcard,
			String phone) {
		String sql4 = "update mgr_class set cnum=cnum+1,unum=unum+1 where cname=?";
		jdbcTemplate.update(sql4, cname);

		String sql3 = "select cid from mgr_class where cname= ? ";
		Object cid = jdbcTemplate.select(sql3, cname).get(0).get("cid");

		String sql = "select sid from(select t.*,row_number() over(order by t.sid desc) rowss1"
				+ " from mgr_student t where t.cid=(select cid from mgr_class where cname= ? ) )where rowss1='1' ";
		Object sid = jdbcTemplate.select(sql, cname).get(0).get("sid");
		BigDecimal bigDecimal = (BigDecimal) sid;
		Integer id = Integer.parseInt(bigDecimal.toString()) + 1;
		String sql1 = "insert into mgr_student values(?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql1, Utils.objToString(id), sname, sex, age, checkinfo, regis, idcard, phone,
				Utils.objToString(cid));
	}

}
