package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.receiveDao;
import com.yc134.新生入学管理系统.dao.showNoticeDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;

public class sendDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_title;
	private Text text_concent;
	// Show_noticeDao dao;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public sendDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public boolean open() {
		createContents();
		shell.open();
		Utils.centerWindows(shell);
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return true;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(921, 652);
		shell.setText("发布通知");

		Button button = new Button(shell, SWT.NONE);
		// 取消
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button.setBounds(220, 525, 98, 40);
		button.setText("取消");

		Button button_1 = new Button(shell, SWT.NONE);
		// 确认
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (text_concent.getText().equals("")) {
					Utils.showMessage(shell, "提示", "内容不能为空！");
					return;
				} else {
					showNoticeDao.insertNotice(text_title.getText(), text_concent.getText(), Utils.login);
					receiveDao.insert();
					StudentUi.state = 0;
					MainUi.state = 1;
					Utils.showMessage(shell, "提示", "发送成功！");
					List<Map<String, Object>> list1 = showNoticeDao.show_All();
					for (int i = 0; i < list1.size(); i++) {
						sendMainDialog.showNotice(list1, i);
					}
					shell.dispose();
				}
			}
		});
		button_1.setBounds(513, 525, 98, 40);
		button_1.setText("确认");

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 97, 884, 406);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		text_concent = new Text(composite, SWT.BORDER);
		text_concent.setBounds(10, 10, 860, 382);
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite_1.setBounds(10, 23, 885, 64);

		Label label = new Label(composite_1, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label.setBounds(24, 21, 45, 26);
		label.setText("标题：");

		text_title = new Text(composite_1, SWT.BORDER);
		text_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		text_title.setBounds(75, 10, 800, 37);

	}

}
