package com.yc134.新生入学管理系统.ui;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.stateDao;
import com.yc134.新生入学管理系统.utils.Utils;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.FormLayout;

public class StudentUi {

	protected Shell shell;
	public static Text text_ID;
	public static Label label;
	static Composite composite_1;
	SashForm SashForm;
	public static int state;
	private static Button btn_Check;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StudentUi window = new StudentUi();
			window.open();
		} catch (Exception e) {
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
		// 窗口居中
		Utils.centerWindows(shell);
		changeStyle();
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
		shell.setSize(1388, 677);
		shell.setText("学生信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm = new SashForm(shell, SWT.BORDER);

		Composite composite = new Composite(SashForm, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));

		Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite_2.setBounds(10, 10, 256, 251);

		label = new Label(composite_2, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(StudentUi.class, "/images/7.jpg"));
		Utils.fullImg(label);
		label.setBounds(49, 10, 153, 151);

		Label lblId = new Label(composite_2, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblId.setBounds(49, 190, 33, 20);
		lblId.setText("ID : ");

		text_ID = new Text(composite_2, SWT.READ_ONLY);
		text_ID.setEnabled(false);
		text_ID.setText(StudentLogin.login);
		text_ID.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_ID.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		text_ID.setBounds(89, 188, 129, 26);

		Button btn_Perfect = new Button(composite, SWT.NONE);
		// 完善个人信息
		btn_Perfect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				perfect_infoCmp info = new perfect_infoCmp(composite_1, SWT.NONE);
				showComposite(info);
			}
		});
		btn_Perfect.setBounds(10, 292, 256, 40);
		btn_Perfect.setText("完善个人信息");

		btn_Check = new Button(composite, SWT.NONE);
		btn_Check.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StudentNoticeUi notice = new StudentNoticeUi(composite_1, SWT.NONE);
				showComposite(notice);
			}
		});
		btn_Check.setBounds(10, 338, 256, 40);
		btn_Check.setText("查看通知");

		Button btn_About = new Button(composite, SWT.NONE);
		// 关于学校
		btn_About.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				about_schoolCmp about = new about_schoolCmp(composite_1, SWT.NONE);
				showComposite(about);
			}
		});
		btn_About.setBounds(10, 384, 256, 40);
		btn_About.setText("关于学校");

		Button button = new Button(composite, SWT.NONE);
		// 修改密码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				update_pwdCmp update = new update_pwdCmp(composite, SWT.NONE);
				showComposite(update);
			}
		});
		button.setBounds(10, 430, 256, 40);
		button.setText("修改密码");

		composite_1 = new Composite(SashForm, SWT.NONE);
		composite_1.setLayout(new StackLayout());
		SashForm.setWeights(new int[] { 277, 774 });

		changeStyle();

	}

	public static void showComposite(Composite composite) {// 传入想展示的composite
		// 一直报错的罪魁祸首
		// for(Control m:composite_1.getChildren()) {
		// m.dispose();
		// }

		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setParent(composite_1);// 将composite_1作为父窗口
		// 将需要展示的页面设置成topControl（顶层控件）
		((StackLayout) composite_1.getLayout()).topControl = composite;
		composite_1.layout();// 重新布局
	}

	public static void changeStyle() {

		if (stateDao.equal(Integer.parseInt(text_ID.getText()))) {
			btn_Check.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		} else {
			btn_Check.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}

	}

}
