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
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.managerDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Text;

public class LoginUi {

	protected static Shell shell;
	protected static Text text_pwd;
	private Text text_uername;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginUi window = new LoginUi();
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
		shell.layout();
		Utils.centerWindows(shell);
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(SWTResourceManager.getImage(LoginUi.class, "/images/3.png"));
		shell.setSize(700, 460);
		shell.setText("新生入学管理系统");

		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("华文宋体", 28, SWT.BOLD));
		label.setBounds(10, 72, 667, 49);
		label.setText("欢迎来到新生入学管理系统");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(140, 182, 56, 27);
		label_1.setText("账号:");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel.setBounds(140, 266, 56, 27);
		lblNewLabel.setText("密码:");

		text_pwd = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_pwd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text_pwd.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_pwd.setBounds(202, 265, 326, 36);
		MessageBox mb = new MessageBox(shell);

		text_uername = new Text(shell, SWT.BORDER);
		text_uername.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_uername.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text_uername.setBounds(202, 181, 326, 36);
		mb.setText("系统提示");
		text_pwd.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					// 登录验证
					List<Map<String, Object>> list = mdao.selectAll();
					for (int i = 0; i < list.size(); i++) {
						if (!text_uername.getText().equals(list.get(i).get("username"))) {
							if (i == 2) {
								Utils.showMessage(shell, "错误提示", "用户不存在");
								break;
							}
							continue;
						} else {
							if ((!text_pwd.getText().equals(Utils.objToString(list.get(i).get("password"))))) {
								Utils.showMessage(shell, "错误提示", "密码错误");
								continue;
							} else {
								Utils.login = text_uername.getText();
								shell.dispose();
								MainUi window = new MainUi();
								window.open();
								break;
							}
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	managerDao mdao = new managerDao();

}
