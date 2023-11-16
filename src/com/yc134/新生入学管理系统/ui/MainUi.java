
package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

import com.yc134.新生入学管理系统.biz.Manager_biz;
import com.yc134.新生入学管理系统.dao.PagQuDao;

import com.yc134.新生入学管理系统.dao.classDao;
import com.yc134.新生入学管理系统.dao.collegeDao;
import com.yc134.新生入学管理系统.dao.majorDao;
import com.yc134.新生入学管理系统.dao.managerDao;
import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.dao.swtResource;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class MainUi {

	protected Shell shell;
	private Device display;
	private Table table;
	private TableItem tableItem;
	protected static Text text_mname;
	Combo combo_collge;
	Combo combo_major;
	Combo combo_class;
	PagQuDao pqdao = new PagQuDao();
	public Text text;
	public Button btnNewButton_1;
	public Button btnNewButton;
	private String fileName;// 选择的文件名称
	private String select = null;// 选择的文件绝对路径
	public static String concent;// 通知内容
	public static int state;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainUi window = new MainUi();
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
		Utils.centerWindows(shell);

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(1132, -28, 90, 24);
		lblNewLabel_2.setText("New Label");

		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(1025, 21, 73, 28);
		label.setText("欢迎你：");

		text_mname = new Text(shell, SWT.READ_ONLY);
		text_mname.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_mname.setBounds(1104, 21, 102, 30);
		text_mname.setText(Utils.login);
		text_mname.setBackground(SWTResourceManager.getColor(255, 228, 196));
		text = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.CENTER);
		text.setText("1");

		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.addListener(SWT.MouseDown, new Listener() {

					@Override
					public void handleEvent(Event event) {
						if (event.button == 1) {
							table.removeAll();
							query(pqdao.pq_show(1, 5));
						}
					}

				});
			}
		});
		text.setBounds(580, 713, 80, 34);

		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Integer.parseInt(text.getText()) == 1) {
					Utils.showMessage(shell, "注意", "当前为第一页");
				}
				table.removeAll();
				text.setText("text.getText()-1");
				query(pqdao.pq_show(Integer.parseInt(text.getText()), 5));

			}
		});
		btnNewButton.setBounds(462, 713, 32, 34);
		btnNewButton.setText("<");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell);
				fd.setFilterExtensions(new String[] { "*.txt", "*.*", "*.doc", "*.docx" });// 后缀名设置
				String select1 = fd.open();// 返回的是文件的绝对路径
				if (null == select1 || "".equals(select1)) {
					Utils.showMessage(shell, "消息提示", "未选中文件");
					return;
				}
				if (select1.equals(select)) {
					Utils.showMessage(shell, "消息提示", "导入信息重复");
				} else {
					select = select1;
					try {
						BufferedReader bf = new BufferedReader(new FileReader(select1));
						String info = null;
						table.removeAll();
						String[] arr = {};
						while ((info = bf.readLine()) != null) {
							arr = info.toString().split("t");
							add(arr);
						}
						List<Map<String, Object>> this_show = pqdao.pq_show(1, 5);
						// 表格填充数据
						query(this_show);

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(192, 717, 98, 30);
		button.setText("导入");

		Button button_1 = new Button(shell, SWT.NONE);
		// 导出
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Utils.info1 == null) {
					Utils.showMessage(shell, "提示", "该班级暂无数据");
				} else {
					exportDialog d = new exportDialog(shell, SWT.NONE);
					d.open();
				}
			}
		});
		button_1.setBounds(955, 717, 98, 30);
		button_1.setText("导出");

		Button button_2 = new Button(shell, SWT.NONE);
		// 发布通知
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				sendMainDialog sendMain = new sendMainDialog(shell, SWT.NONE);
				create(sendMain);

			}
		});
		button_2.setBounds(866, 19, 98, 30);
		button_2.setText("发布通知");

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
		shell.setBackgroundImage(SWTResourceManager.getImage(MainUi.class, "/images/1.png"));
		shell.setSize(1263, 807);
		shell.setText("新生入学主界面");

		ClassInfo classinfo = new ClassInfo();
		shell.setLayout(null);

		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setBounds(10, 64, 1219, 59);
		lblNewLabel.setFont(SWTResourceManager.getFont("华文中宋", 28, SWT.NORMAL));
		lblNewLabel.setText("新生入学主界面");
		lblNewLabel.setAlignment(SWT.CENTER);

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(68, 173, 53, 37);
		label.setFont(swtResource.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label.setText("学院:");

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(383, 173, 58, 37);
		label_1.setFont(swtResource.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		label_1.setText("专业:");

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(703, 173, 58, 37);
		lblNewLabel_1.setFont(swtResource.getFont("Microsoft YaHei UI", 14, SWT.NORMAL));
		lblNewLabel_1.setText("班级:");

		Button btn_select = new Button(shell, SWT.NONE);
		btn_select.setBounds(1014, 173, 140, 37);
		btn_select.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 组合查询结果填充到tbale上
				List<Map<String, Object>> list = cdao.selectByCname(combo_class.getText());
				query(list);
			}
		});
		btn_select.setText("查询");
		btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Integer.parseInt(text.getText()) == 1) {
					Utils.showMessage(shell, "注意", "当前为第一页");
				}
				if (Integer.parseInt(text.getText()) > 1) {
					text.setText(Utils.objToString(Integer.parseInt(text.getText()) - 1));
					query(pqdao.pq_show(Integer.parseInt(text.getText()), 5));
				}
			}
		});
		btnNewButton.setBounds(462, 713, 32, 34);
		btnNewButton.setText("<");

		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (pqdao.pq_show(Integer.parseInt(text.getText()) + 1, 5).isEmpty()
						|| pqdao.pq_show(Integer.parseInt(text.getText()) + 1, 5) == null) {
					Utils.showMessage(shell, "注意", "当前为最后页");
				} else {
					text.setText(Utils.objToString(Integer.parseInt(text.getText()) + 1));
					List<Map<String, Object>> this_show = pqdao.pq_show(Integer.parseInt(text.getText()), 5);
					query(this_show);
				}
			}
		});

		btnNewButton_1.setBounds(755, 713, 32, 34);
		btnNewButton_1.setText(">");
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(67, 265, 1110, 442);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 0, 1110, 438);
		table.addSelectionListener(new SelectionAdapter() {
			// 单击
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem tableItem = (TableItem) e.item;
				// 选中某行数据完成导出功能
				collegeDao cDao = new collegeDao();
				Utils.info1 = Utils.fullInfoByCname(tableItem.getText(2));
			}

			// 双击
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TableItem tableItem = (TableItem) e.item;
				// 设置临时变量存储学院 专业 班级信息
				Utils.Mname = tableItem.getText(0);
				Utils.mname = tableItem.getText(1);
				Utils.Cname = tableItem.getText(2);
				if (classinfo.open()) {
					List<Map<String, Object>> list = cdao.selectByCname(Utils.Cname);
					combo_collge.setText((String) list.get(0).get("name"));
					combo_major.setText((String) list.get(0).get("mname"));
					combo_class.setText(Utils.Cname);
					query(list);
				}
			}
		});
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(222);
		tblclmnNewColumn.setText("学院");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(222);
		tblclmnNewColumn_1.setText("专业");

		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(222);
		tblclmnNewColumn_2.setText("班级");

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(220);
		tableColumn.setText("未到人数");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(219);
		tblclmnNewColumn_3.setText("总人数");

		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] { "计算机学院", "计算机", "计科2101", "20", "40" });

		combo_collge = new Combo(shell, SWT.NONE);
		combo_collge.setBounds(127, 178, 182, 32);
		combo_collge.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到点击的学院值并查询相关专业
				List<Map<String, Object>> list = mdao.selectByMname(combo_collge.getText());
				showClass(list);
				showMajor(list);
			}
		});
		combo_collge.setItems(new String[] { "a", "b", "c" });

		combo_major = new Combo(shell, SWT.NONE);
		combo_major.setBounds(447, 178, 182, 32);
		combo_major.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// 得到点击的专业值并查询相关班级
				List<Map<String, Object>> list = cdao.selectByMname(combo_major.getText());
				List<Map<String, Object>> list1 = mdao.selectCollege(combo_major.getText());
				combo_collge.setText((String) list1.get(0).get("name"));
				showClass(list);
			}
		});
		combo_major.setItems(new String[] { "a", "b", "c" });

		combo_class = new Combo(shell, SWT.NONE);
		combo_class.setBounds(767, 178, 182, 32);
		combo_class.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 组合查询学院 专业名字填到下拉框上
				List<Map<String, Object>> list = cdao.selectByCname(combo_class.getText());
				combo_collge.setText((String) list.get(0).get("name"));
				combo_major.setText((String) list.get(0).get("mname"));
			}
		});
		combo_class.setItems(new String[] { "a", "b", "c" });

		List<Map<String, Object>> this_show = pqdao.pq_show(1, 5);
		// 表格填充数据
		query(this_show);
		// 设置下拉框数据
		showCollege();
		showMajor(mdao.selectAll());
		showClass(cdao.selectAll());
		// 检查是否登录
		if (Utils.login == null) {
			Utils.showMessage(shell, "错误提示", "请先登录");
			shell.dispose();
		}

	}

	Manager_biz mbiz = new Manager_biz();
	managerDao mname = new managerDao();
	collegeDao codao = new collegeDao();
	majorDao mdao = new majorDao();
	classDao cdao = new classDao();
	studentDao sdao = new studentDao();

	// 通知 递归
	public void create(sendMainDialog sendMain) {
		sendMain.open();
		while (state == 1 || state == 2) {
			sendMainDialog sendMain1 = new sendMainDialog(shell, SWT.NONE);
			create(sendMain1);
		}
	}

	// 将导入的数据存入数据库
	public void add(String[] arr) {
		List<Map<String, Object>> list = codao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			String collegename = list.get(i).get("name").toString();
			String mname = Utils.objToString(list.get(i).get("mname"));
			String cname = Utils.objToString(list.get(i).get("cname"));
			if (collegename.equals(arr[0]) && mname.equals(arr[1]) && cname.equals(arr[2])) {
				sdao.add(cname, arr[3], arr[4], arr[7], arr[5], arr[6], arr[9], arr[8]);
				break;
			} else {
				Utils.showMessage(shell, "错误提示", "学生信息错误");
				break;
			}
		}
	}

	// 将班级信息填充到表格中
	public void query(List<Map<String, Object>> list) {
		// 清空表格原有数据
		table.removeAll();
		for (int i = 0; i < list.size(); i++) {
			tableItem = new TableItem(table, SWT.NONE);
			Map<String, Object> map = list.get(i);
			String[] data = new String[] { map.get("name").toString(), map.get("mname").toString(),
					map.get("cname").toString(), map.get("unum").toString(), map.get("cnum").toString() };
			tableItem.setText(data);
		}
	}

	// 将班级信息填充到表格中 不清空表格数据
	public void query1(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size(); i++) {
			tableItem = new TableItem(table, SWT.NONE);
			Map<String, Object> map = list.get(i);
			String[] data = new String[] { map.get("name").toString(), map.get("mname").toString(),
					map.get("cname").toString(), map.get("unum").toString(), map.get("cnum").toString() };
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
			combo_collge.removeAll();// 清除下拉列表中的数据
			combo_collge.setItems(infos);
			combo_collge.select(0);
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
