package com.yc134.新生入学管理系统.biz;

import java.util.List;
import java.util.Map;

import com.yc134.新生入学管理系统.dao.managerDao;

import com.yc134.新生入学管理系统.utils.Utils;

public class Manager_biz {
	managerDao mdao = new managerDao();
	public static Map<String, Object> LoginedUser;

	public void Login(String name, String pwd) throws BizException {
		// 参数校验
		Utils.check(Utils.isEmpty(name), "请输入用户名");
		Utils.check(Utils.isEmpty(pwd), "请输入密码");
		List<Map<String, Object>> list = mdao.select_Mgr_name(name, pwd);
		Utils.check(list.size() != 1, "用户名或密码错误");
		// 保存用户信息
		LoginedUser = list.get(0);

	}

	public static Map<String, Object> getLoginedUser() {
		return LoginedUser;
	}
}
