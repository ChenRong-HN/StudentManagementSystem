package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.jdbcTemplate;
import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class InfoCheck extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_name;
	private Text text_sex;
	private Text text_age;
	private Text text_idcard;
	private Text text_phone;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public InfoCheck(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Utils.centerWindows(shell);
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(SWTResourceManager.getImage(InfoCheck.class, "/images/3.png"));
		shell.setSize(707, 545);
		shell.setText("学生信息");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(64, 39, 64, 37);
		lblNewLabel.setText("姓名：");

		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(64, 117, 74, 37);
		label.setText("性别：");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(64, 191, 64, 37);
		label_1.setText("年龄：");

		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setBounds(64, 275, 107, 37);
		label_2.setText("身份证号：");

		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setBounds(64, 358, 96, 37);
		label_3.setText("手机号：");

		text_name = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_name.setBounds(178, 39, 366, 30);

		text_sex = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_sex.setBounds(178, 120, 366, 30);

		text_age = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_age.setBounds(178, 198, 366, 30);

		text_idcard = new Text(shell, SWT.BORDER);
		text_idcard.setBounds(177, 278, 367, 30);

		text_phone = new Text(shell, SWT.BORDER);
		text_phone.setBounds(178, 365, 366, 30);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (!checkCardId(Utils.name, text_idcard.getText())) {
					Utils.showMessage(shell, "信息提示", "身份证号码错误");
				} else if (!checkPhone(Utils.name, text_phone.getText())) {
					Utils.showMessage(shell, "信息提示", "手机号码号码错误");
				} else if ("是".equals(sdao.checkInfo(text_idcard.getText()).get(0).get("checkinfo"))) {
					Utils.showMessage(shell, "信息提示", "不要重复核对");
					shell.dispose();
				} else {
					Utils.showMessage(shell, "信息提示", "核对成功！");
					sdao.updateCheckInfo(text_idcard.getText());
					sdao.updateRnum(text_idcard.getText(), Utils.Cname);
					shell.dispose();
				}

			}
		});
		btnNewButton.setBounds(113, 431, 114, 34);
		btnNewButton.setText("确认");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button.setBounds(452, 431, 114, 34);
		button.setText("取消");

		text_name.setText(Utils.name);
		text_sex.setText(Utils.sex);
		text_age.setText(Utils.age);

	}

	ClassInfo classinfo = new ClassInfo();
	studentDao sdao = new studentDao();

	// 比较身份证号
	public boolean checkCardId(String sname, String idcard) {
		String sql = "select to_char(idcard) from mgr_student where sname=?";
		List<Map<String, Object>> list = jdbcTemplate.select(sql, sname);
		System.out.println(list);
		System.out.println(idcard);
		System.out.println(Utils.objToString(list.get(0).get("to_char(idcard)")));
		if (idcard.equals(Utils.objToString(list.get(0).get("to_char(idcard)")))) {
			return true;
		}
		return false;
	}

	// 比较手机号
	public boolean checkPhone(String sname, String phone) {
		String sql = "select phone from mgr_student where sname=?";
		List<Map<String, Object>> list = jdbcTemplate.select(sql, sname);
		if (phone.equals(Utils.objToString(list.get(0).get("phone")))) {
			return true;
		}
		return false;
	}

}
