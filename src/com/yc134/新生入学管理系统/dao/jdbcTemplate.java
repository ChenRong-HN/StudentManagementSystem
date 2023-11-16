package com.yc134.新生入学管理系统.dao;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class jdbcTemplate {

	static String url;
	static String user;
	static String password;
	static String driver;
	static {
		try {
			ClassLoader cl = jdbcTemplate.class.getClassLoader();
			InputStream in = cl.getResourceAsStream("db.propertices");
			if (in == null) {
				System.out.println("db.propertices不存在");
			} else {
				Properties properties = new Properties();
				properties.load(in);
				url = properties.getProperty("url");
				user = properties.getProperty("username");
				password = properties.getProperty("password");
				driver = properties.getProperty("driver");
			}
			Class.forName(driver);
		} catch (Exception e) {
			throw new RuntimeException("数据库驱动加载失败", e);
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new RuntimeException("创建连接失败", e);
		}
	}

	public static interface SqlExecute {
		Object execute(Connection conn) throws Exception;
	}

	public static Object execute(SqlExecute executeor) {
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			Object ret = executeor.execute(conn);
			conn.commit();
			return ret;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new RuntimeException("回滚失败", e1);
			}
			throw new RuntimeException("SQL执行失败", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				throw new RuntimeException("关闭连接失败", e);
			}
		}
	}

	public static PreparedStatement preparedStatement(Connection conn, String sql, Object... p) throws SQLException {
		System.out.println("SQL:" + sql);
		System.out.println("参数" + Arrays.toString(p));
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < p.length; i++) {
			ps.setObject(i + 1, p[i]);
		}
		return ps;
	}

	public static int update(String sql, Object... p) {
		return (int) execute(conn -> {
			PreparedStatement ps = preparedStatement(conn, sql, p);
			return ps.executeUpdate();
		});
	}

	// 构建返回序列生成的主键值的insert语句对象
	public static PreparedStatement preparedInsertStatement(Connection conn, String sql, String keyName, Object... p)
			throws SQLException {
		System.out.println("SQL:" + sql);
		System.out.println("参数" + Arrays.toString(p));
		PreparedStatement ps = conn.prepareStatement(sql, new String[] { keyName });
		for (int i = 0; i < p.length; i++) {
			ps.setObject(i + 1, p[i]);
		}
		return ps;
	}

	// 返回数据库生成的主键值的insert的
	public static long insert(String sql, String keyName, Object... p) {
		return (long) execute(conn -> {
			PreparedStatement ps = preparedInsertStatement(conn, sql, keyName, p);
			if (ps.executeUpdate() > 0) {
				// 获取数据库生成的主键值结果集
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				return rs.getLong(1);
			} else {
				return 0;
			}
		});
	}

	// List 有序、可重复 set 无序、不重复 map 无序、键值对 LinkedHashMap 有序map
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> select(String sql, Object... s) {
		return (List<Map<String, Object>>) execute(conn -> {
			List<Map<String, Object>> list = new ArrayList<>();
			PreparedStatement ps = preparedStatement(conn, sql, s);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> row = new LinkedHashMap<>();
				for (int i = 0; i < md.getColumnCount(); i++) {
					String columnName = md.getColumnName(i + 1);
					columnName = columnName.toLowerCase();
					Object columnValue = rs.getObject(i + 1);
					row.put(columnName, columnValue);
				}
				list.add(row);
			}
			return list;
		});
	}

	// 实体类class Page{ int total;List<Map>data;}
	// map集合 Map{total:120,data:[] }
	public static Map<String, Object> selectPage1(String sql, int page, int size, Object... p) {
		Map<String, Object> ret = new HashMap<>();
		int total = 0;
		List<Map<String, Object>> data = null;
		// select a.*,rownum rn from (select * from emp where ename like ?)a where rn<?
		String sql1 = "select * from" + " (select a.*,rownum rn from(" + sql + ")a where rownum<=?) " + " where rn>=?";
		int begin = (page - 1) * size + 1;
		int end = page * size;
		Object[] newp = new Object[p.length + 2];
		System.arraycopy(p, 0, newp, 0, p.length);
		newp[newp.length - 2] = end;
		newp[newp.length - 1] = begin;
		data = jdbcTemplate.select(sql1, newp);

		String sql2 = "select count(*) cnt from(" + sql + ")";
		List<Map<String, Object>> totalList = jdbcTemplate.select(sql2, p);
		total = Integer.parseInt(totalList.get(0).get("cnt") + "");// 万能数字转换
		ret.put("total", total);
		ret.put("data", data);

		return ret;
	}

	public static Map<String, Object> selectPage(String sql, int page, int size, Object... params) {
		Map<String, Object> ret = new HashMap<>();
		int total = 0;
		List<Map<String, Object>> data = null;
		// select a.*,rownum rn from (select * from emp where ename like ?)a where rn <?
		String sql1 = "select * from" + "(select a.*,rownum rn from (" + sql + ")a where rownum <= ?)"
				+ "where rn >= ?";
		// 1,5 = 》 1~5
		// 2,5 = 》6~10 =》（page -1）*size +1 end = page * size
		int begin = (page - 1) * size + 1;
		int end = page * size;
		// 定义新的参数数组
		Object[] newParams = new Object[params.length + 2];
		// 数组复制
		System.arraycopy(params, 0, newParams, 0, params.length);
		newParams[newParams.length - 2] = end;
		newParams[newParams.length - 1] = begin;

		data = jdbcTemplate.select(sql1, newParams);
		// 查询总行数
		String sql2 = "select count(*) cnt from (" + sql + ")";
		List<Map<String, Object>> totalList = jdbcTemplate.select(sql2, params);
		// 万能数组转换方法
		total = Integer.parseInt(totalList.get(0).get("cnt") + "");

		ret.put("total", total);
		ret.put("data", data);

		return ret;
	}

}
