package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.receiveDao;
import com.yc134.新生入学管理系统.dao.stateDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ShowNotice extends Composite {
	public static Text text_Notice;
	Display display;
	static Button button;
	public static int nid;
	public static int sid;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ShowNotice(Composite parent, int style) {
		super(parent, style);

		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));

		text_Notice = new Text(this, SWT.READ_ONLY);
		text_Notice.setEnabled(false);
		text_Notice.setText("hello world");
		text_Notice.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_Notice.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text_Notice.setBounds(78, 101, 822, 413);

		button = new Button(this, SWT.NONE);
		// 确认收到
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (button.getText().trim().equals("已收到")) {
					return;
				}
				StudentUi.state = 1;
				StudentUi.changeStyle();

				sid = Integer.parseInt(StudentUi.text_ID.getText());
				stateDao.stateInsert(nid, sid);
				confirm();
				receiveDao.update(nid);
				StudentUi.changeStyle();
			}
		});
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		button.setBounds(423, 557, 117, 49);

		confirm();

		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		label.setFont(SWTResourceManager.getFont("华文新魏", 28, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(423, 22, 163, 49);
		label.setText("通   知");

	}

	public static void confirm() {
		if (Integer.parseInt(stateDao.state(Integer.parseInt(StudentUi.text_ID.getText()), nid).get(0).get("count(*)")
				.toString()) != 0) {
			button.setText("已收到");
		} else {
			button.setText("确认收到");
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
