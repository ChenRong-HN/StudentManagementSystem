package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Composite;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import java.util.function.Consumer;

public class update_pwdCmp extends Composite {

	private Text text_sid;
	private Text text_old;
	private Text text_new;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public update_pwdCmp(Composite parent, int style) {
		super(parent, style);

		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(-167, -75, 1289, 697);

		Label label = new Label(composite, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(533, 213, 79, 27);
		label.setText("学   号：");

		text_sid = new Text(composite, SWT.BORDER);
		text_sid.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_sid.setBounds(630, 203, 241, 37);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(536, 307, 76, 27);
		label_1.setText("原密码：");

		text_old = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_old.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_old.setBounds(630, 297, 241, 37);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setBounds(536, 408, 76, 27);
		label_2.setText("新密码：");

		text_new = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_new.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_new.setBounds(630, 398, 241, 37);

		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String, Object>> list = sdao.selectAllSid();
				int i;
				for (i = 0; i < list.size(); i++) {
					if (text_sid.getText().equals(Utils.objToString(list.get(i).get("sid")))
							&& text_old.getText().equals(Utils.objToString(list.get(i).get("pwd")))) {
						sdao.updatePwd(text_sid.getText(), text_new.getText());
						text_sid.setText("");
						text_old.setText("");
						text_new.setText("");
						Utils.showMessage(getShell(), "信息提示", "修改成功");

						break;
					}
				}
				if (i >= list.size()) {
					Utils.showMessage(getShell(), "信息提示", "学号或密码错误");
				}

			}
		});
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button.setBounds(773, 515, 98, 37);
		button.setText("确认");

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_sid.setText("");
				text_old.setText("");
				text_new.setText("");
			}
		});
		button_1.setText("取消");
		button_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		button_1.setBounds(533, 515, 98, 37);

	}

	studentDao sdao = new studentDao();

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
