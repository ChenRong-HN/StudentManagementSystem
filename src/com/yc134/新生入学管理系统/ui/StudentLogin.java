package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.managerDao;
import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StudentLogin {

	protected static Shell shell;
	protected static Text text_pwd;
	private Text text_sid;
	public static String login;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StudentLogin window = new StudentLogin();
			window.open();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.layout();
		Utils.centerWindows(shell);
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				login = text_sid.getText();
				List<Map<String, Object>> list = sdao.selectAllSid();
				for (int i = 0; i < list.size(); i++) {
					if (text_sid.getText().equals(Utils.objToString(list.get(i).get("sid")))
							&& text_pwd.getText().equals(Utils.objToString(list.get(i).get("pwd")))) {
						shell.dispose();
						StudentUi window = new StudentUi();
						window.open();
						break;
					}
				}
				Utils.showMessage(shell,"消息提示","密码或学号错误");
			}
		});
		button.setBounds(448, 269, 98, 30);
		button.setText("确认");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		button_1.setBounds(158, 269, 98, 30);
		button_1.setText("取消");

		Label label_forget = new Label(shell, SWT.NONE);
		label_forget.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				updatePwdDialog u = new updatePwdDialog(shell, SWT.NONE);
				u.open();
			}
		});

		label_forget.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		label_forget.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_forget.setBounds(552, 209, 76, 20);
		label_forget.setText("忘记密码");
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setBackgroundImage(SWTResourceManager.getImage(StudentLogin.class, "/images/6.png"));
		shell.setSize(700, 460);
		shell.setText("学生登录界面");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(158, 118, 56, 27);
		label_1.setText("学号:");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(158, 202, 56, 27);
		lblNewLabel.setText("密码:");

		text_pwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_pwd.setBounds(220, 201, 326, 36);
		MessageBox mb = new MessageBox(shell);

		text_sid = new Text(shell, SWT.BORDER);
		text_sid.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_sid.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text_sid.setBounds(220, 117, 326, 36);

	}

	studentDao sdao = new studentDao();
}
