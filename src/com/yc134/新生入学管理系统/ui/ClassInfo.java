package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.classDao;
import com.yc134.新生入学管理系统.dao.collegeDao;
import com.yc134.新生入学管理系统.dao.majorDao;
import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ClassInfo {

	protected Shell shell;
	private Table table;

	Combo combo_college;
	Combo combo_major;
	Combo combo_class;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClassInfo window = new ClassInfo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * 
	 * @return
	 */
	public boolean open() {
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
		return true;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(SWTResourceManager.getImage(ClassInfo.class, "/images/2.png"));
		shell.setSize(1286, 748);
		shell.setText("班级报道信息");
		shell.setLayout(new FormLayout());

		Composite composite = new Composite(shell, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(100, -202);
		fd_composite.left = new FormAttachment(0, 324);
		fd_composite.right = new FormAttachment(0, 910);
		composite.setLayoutData(fd_composite);

		InfoCheck infoCheck = new InfoCheck(shell, 0);

		Label label = new Label(shell, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 94);
		label.setLayoutData(fd_label);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setText("学院");

		Label label_1 = new Label(shell, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.top = new FormAttachment(label, 0, SWT.TOP);
		label_1.setLayoutData(fd_label_1);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setText("专业");

		Label label_2 = new Label(shell, SWT.NONE);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(label, 0, SWT.TOP);
		label_2.setLayoutData(fd_label_2);
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setText("班级");

		combo_college = new Combo(shell, SWT.NONE);
		combo_college.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到点击的学院值并查询相关专业
				List<Map<String, Object>> list = mdao.selectByName(combo_college.getText());
				showClass(list);
				List<Map<String, Object>> list1 = codao.selectAll2(combo_college.getText());
				showMajor(list1);
			}
		});
		fd_label.left = new FormAttachment(combo_college, 6);
		fd_composite.top = new FormAttachment(combo_college, 32);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = (TableItem) e.item;
				// 设置临时变量存储学院 专业 班级信息
				Utils.name = tableItem.getText(2);
				Utils.sex = tableItem.getText(3);
				Utils.age = tableItem.getText(4);

				infoCheck.open();
				query(combo_class.getText());
			}
		});
		table.setBounds(10, 10, 566, 408);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(84);
		tableColumn_6.setText("班级编号");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("学号");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(74);
		tableColumn.setText("姓名");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(56);
		tableColumn_1.setText("性别");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(56);
		tableColumn_2.setText("年龄");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(73);
		tableColumn_3.setText("是否核对");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(98);
		tableColumn_4.setText("是否报道");

		combo_college.setItems(new String[] { "aa", "bb" });
		FormData fd_combo_collage = new FormData();
		fd_combo_collage.top = new FormAttachment(0, 91);
		fd_combo_collage.right = new FormAttachment(100, -770);
		fd_combo_collage.left = new FormAttachment(0, 324);
		combo_college.setLayoutData(fd_combo_collage);
		combo_college.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));

		combo_major = new Combo(shell, SWT.NONE);
		combo_major.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到点击的专业值并查询相关班级
				List<Map<String, Object>> list = cdao.selectByMname(combo_major.getText());
				List<Map<String, Object>> list1 = mdao.selectCollege(combo_major.getText());
				combo_college.setText((String) list1.get(0).get("name"));
				System.out.println(list1);
				showClass(list);
			}
		});
		fd_label_1.left = new FormAttachment(combo_major, 6);
		combo_major.setItems(new String[] { "bb", "cc" });
		combo_major.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		FormData fd_combo_major = new FormData();
		fd_combo_major.top = new FormAttachment(label, -3, SWT.TOP);
		fd_combo_major.left = new FormAttachment(label, 6);
		fd_combo_major.right = new FormAttachment(100, -544);
		combo_major.setLayoutData(fd_combo_major);

		combo_class = new Combo(shell, SWT.NONE);
		combo_class.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				query(combo_class.getText());
				Utils.Cname = combo_class.getText();
				List<Map<String, Object>> list = cdao.selectByCname(combo_class.getText());
				combo_college.setText((String) list.get(0).get("name"));
				combo_major.setText((String) list.get(0).get("mname"));
			}
		});
		fd_label_2.left = new FormAttachment(combo_class, 6);
		combo_class.setItems(new String[] { "cc", "dd" });
		combo_class.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		FormData fd_combo_class = new FormData();
		fd_combo_class.top = new FormAttachment(label, -3, SWT.TOP);
		fd_combo_class.left = new FormAttachment(label_1, 6);
		fd_combo_class.right = new FormAttachment(100, -380);
		combo_class.setLayoutData(fd_combo_class);
		// 填充表格数据
		query(Utils.Cname);
		// 下拉框数据显示
		showCollege();
		showMajor(mdao.selectAll());
		showClass(cdao.selectAll());

		combo_college.setText(Utils.Mname);
		combo_major.setText(Utils.mname);
		combo_class.setText(Utils.Cname);

	}

	studentDao dao = new studentDao();
	collegeDao codao = new collegeDao();
	majorDao mdao = new majorDao();
	classDao cdao = new classDao();

	// 将班级学生信息填充到表格中
	public void query(String cname) {
		// 清空表格原有数据
		table.removeAll();

		List<Map<String, Object>> list = dao.selectByCname(cname);

		for (int i = 0; i < list.size(); i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			Map<String, Object> map = list.get(i);
			String[] data = new String[] { map.get("cid").toString(), map.get("sid").toString(),
					map.get("sname").toString(), map.get("sex").toString(), Utils.objToString(map.get("age")),
					Utils.objToString(map.get("checkinfo")), Utils.objToString(map.get("regis"))

			};
			tableItem.setText(data);
		}
	}

	// 将学院显示在下拉框
	private void showCollege() {
		try {
			List<Map<String, Object>> list = codao.selectAll1();
			String[] infos = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> m = list.get(i);
				// 下拉列表 计算机学院
				infos[i] = Utils.objToString(Utils.objToString(m.get("name")));
			}

			// 设置到下拉列表中
			combo_college.removeAll();// 清除下拉列表中的数据
			combo_college.setItems(infos);
			combo_college.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将专业显示在下拉框
	private void showMajor(List<Map<String, Object>> list) {
		try {
			String[] infos = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> m = list.get(i);
				// 下拉列表 计算机学院
				infos[i] = Utils.objToString(Utils.objToString(m.get("mname")));
			}
			// 设置到下拉列表中
			combo_major.removeAll();// 清除下拉列表中的数据
			combo_major.setItems(infos);
			combo_major.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将班级显示在下拉框
	private void showClass(List<Map<String, Object>> list) {
		try {
			String[] infos = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> m = list.get(i);
				// 下拉列表 计算机学院
				infos[i] = Utils.objToString(Utils.objToString(m.get("cname")));
			}
			// 设置到下拉列表中
			combo_class.removeAll();// 清除下拉列表中的数据
			combo_class.setItems(infos);
			combo_class.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
