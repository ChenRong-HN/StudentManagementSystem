package com.yc134.新生入学管理系统.utils;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.yc134.新生入学管理系统.biz.BizException;
import com.yc134.新生入学管理系统.dao.jdbcTemplate;

public class Utils {
	public static String Mname;// 学院名称
	public static String mname;// 专业名称
	public static String Cname;// 班级名称
	public static String name;// 姓名名称
	public static String sex;// 性别名称
	public static String age;// 年龄名称
	public static String type;// 导出文件类型
	public static String tname;// 导出的文件名字
	public static List<Map<String, Object>> info1;
	// 检查是否登录
	public static String login;

	// 将对象转为字符串
	public static String objToString(Object obj) {
		if (null == obj) {
			return "";
		}
		return obj.toString();
	}

	// 提示框
	public static void showMessage(Shell shell, String title, String messge) {
		MessageBox mb = new MessageBox(shell, SWT.NONE);
		mb.setText(title);
		mb.setMessage(messge);
		mb.open();
	}

	// 窗口居中
	public static void centerWindows(Shell shell) {
		// 获取屏幕的大小
		int x = Display.getCurrent().getBounds().width - shell.getBounds().width;
		int y = Display.getCurrent().getBounds().height - shell.getBounds().height;
		// 计算居中的坐标
		x *= 0.5;
		y *= 0.4;
		// 设置窗口的坐标位置
		shell.setLocation(x, y);
	}

	/**
	 * 图片自适应控件大小
	 */
	public static void fullImg(Control c) {
		PaintListener pl = new PaintListener() {
			public void paintControl(PaintEvent e) {
				Image img;
				if (e.getSource() instanceof Label) {
					img = ((Label) e.getSource()).getImage();
				} else if (e.getSource() instanceof Button) {
					img = ((Button) e.getSource()).getImage();
				} else {
					img = c.getBackgroundImage();
				}
				if (img == null)
					return;
				double wr = (double) c.getBounds().width / (double) img.getBounds().width;
				double hr = (double) c.getBounds().height / (double) img.getBounds().height;
				double r = Math.max(wr, hr);
				int destWidth = (int) (img.getBounds().width * r);
				int destHeight = (int) (img.getBounds().height * r);
				e.gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 0, 0, destWidth, destHeight);
			}
		};
		c.addPaintListener(pl);
	}

	public static boolean isEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	public static void check(boolean bool, String msg) throws BizException {
		if (bool) {
			throw new BizException(msg);
		}
	}

	public static void showMsg(String msg, Shell shell) {
		MessageBox mb = new MessageBox(shell);
		mb.setText("系统提示");
		mb.setMessage(msg);
		mb.open();
	}

	public static List<Map<String, Object>> fullInfoByCname(String Cname) {
		String sql = "select name,mname,cname,sname,sex,age,idcard,phone,checkinfo,regis,unum,cnum"
				+ " from mgr_college a join mgr_major b on a.id=b.id"
				+ " join mgr_class c on b.mid=c.mid join mgr_student d on c.cid=d.cid where cname=? ";
		return jdbcTemplate.select(sql, Cname);
	}

}
