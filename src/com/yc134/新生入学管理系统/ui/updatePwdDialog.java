package com.yc134.新生入学管理系统.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class updatePwdDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_name;
	private Text text_mname;
	private Text text_cname;
	private Text text_sid;
	private Text text_new;
	private Text text_confirm;
	private Button button;
	private Button button_1;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public updatePwdDialog(Shell parent, int style) {
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

		button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String, Object>> list = sdao.selectBy(text_name.getText(), text_mname.getText(),
						text_cname.getText(), text_sid.getText());
				if (list.isEmpty()) {
					Utils.showMessage(shell, "错误提示", "信息填写错误");
				} else {
					if (!text_new.getText().equals(text_confirm.getText())) {
						Utils.showMessage(shell, "错误提示", "前后密码不一致");
					} else {
						sdao.updatePwd(text_sid.getText(), text_new.getText());
						Utils.showMessage(shell, "信息提示", "密码修改成功");
						shell.close();
					}
				}

			}
		});
		button.setBounds(348, 326, 98, 30);
		button.setText("确认");

		button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_1.setBounds(228, 326, 98, 30);
		button_1.setText("取消");
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
		shell.setBackgroundImage(SWTResourceManager.getImage(updatePwdDialog.class, "/images/5.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(680, 425);
		shell.setText(getText());
		Label lable_college = new Label(shell, SWT.NONE);
		lable_college.setBounds(228, 31, 45, 20);
		lable_college.setText("学院：");

		Label lable_major = new Label(shell, SWT.NONE);
		lable_major.setBounds(228, 80, 45, 20);
		lable_major.setText("专业：");

		Label lable_class = new Label(shell, SWT.NONE);
		lable_class.setBounds(228, 128, 45, 20);
		lable_class.setText("班级：");

		Label lable_sid = new Label(shell, SWT.NONE);
		lable_sid.setBounds(228, 183, 45, 20);
		lable_sid.setText("学号：");

		Label lable = new Label(shell, SWT.NONE);
		lable.setBounds(228, 232, 60, 20);
		lable.setText("新密码：");

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setBounds(228, 280, 69, 20);
		lblNewLabel_5.setText("确认密码：");

		text_name = new Text(shell, SWT.BORDER);
		text_name.setBounds(299, 31, 147, 26);

		text_mname = new Text(shell, SWT.BORDER);
		text_mname.setBounds(299, 80, 147, 26);

		text_cname = new Text(shell, SWT.BORDER);
		text_cname.setBounds(299, 122, 147, 26);

		text_sid = new Text(shell, SWT.BORDER);
		text_sid.setBounds(299, 177, 147, 26);

		text_new = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_new.setBounds(299, 226, 147, 26);

		text_confirm = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_confirm.setBounds(299, 274, 147, 26);

	}

	studentDao sdao = new studentDao();
}
