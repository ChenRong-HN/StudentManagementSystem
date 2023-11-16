package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.showNoticeDao;
import com.yc134.新生入学管理系统.dao.stateDao;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.ScrolledComposite;

public class StudentNoticeUi extends Composite {
	Display display;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Composite composite;

	showNoticeDao sndao = new showNoticeDao();

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public StudentNoticeUi(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackgroundImage(SWTResourceManager.getImage(StudentNoticeUi.class, "/images/8.png"));

		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		// button.setText("确认收到");

		// Display display = new Display();
		// Color color = new Color(display, new RGB(204, 255, 229));
		// button.setBackground(color);

		Label label = new Label(this, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		label.setBackgroundImage(null);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		label.setFont(SWTResourceManager.getFont("华文新魏", 28, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(423, 10, 163, 49);
		label.setText("通   知");

		ScrolledComposite scrolledComposite = new ScrolledComposite(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(57, 115, 921, 418);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackgroundImage(null);

		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		List<Map<String, Object>> result = sndao.show_All();
		// System.out.println(result);
		// Map<String,Object>map=result.get(0);
		// System.out.println(map.get("from").toString());

		query(result);

	}

	public void query(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size(); i++) {
			query_base(list, i);
		}
	}

	public void query_base(List<Map<String, Object>> list, int i) {
		Map<String, Object> map = list.get(i);
		if (Integer.parseInt(stateDao.stuState(Integer.parseInt(StudentUi.text_ID.getText().toString())).get(0)
				.get("count(*)").toString()) != list.size()) {
			StudentUi.state = 0;
		} else {
			StudentUi.state = 1;
		}
		Composite composite_1 = new Composite(composite, SWT.NONE);
		// 双击查看通知
		composite_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				ShowNotice.nid = Integer.parseInt(map.get("nid").toString());
				ShowNotice showNotice = new ShowNotice(StudentUi.composite_1, SWT.NONE);
				StudentUi.showComposite(showNotice);
				showNotice.text_Notice.setText(map.get("content").toString());
				ShowNotice.confirm();
			}
		});
		composite_1.setBackgroundImage(null);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBounds(39, 22 + 44 * i, 841, 48);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setBounds(10, 10, 54, 24);
		label_1.setText("标题：");

		text = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(75, 10, 214, 30);
		text.setText(map.get("title").toString());
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setBounds(357, 13, 54, 24);
		label_2.setText("时间：");

		text_1 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(417, 10, 163, 30);
		text_1.setText(map.get("time").toString());
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setBounds(621, 10, 64, 24);
		label_3.setText("发布者：");

		text_2 = new Text(composite_1, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBounds(691, 7, 140, 30);
		text_2.setText(map.get("mname").toString());
		// 未查看通知的变色
		if (Integer.parseInt(stateDao
				.state(Integer.parseInt(StudentUi.text_ID.getText()), Integer.parseInt(map.get("nid").toString()))
				.get(0).get("count(*)").toString()) == 0) {
			label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			text_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
