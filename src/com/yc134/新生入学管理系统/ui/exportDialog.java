package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class exportDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text_tname;
	private Combo combo_type;
	private File file;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public exportDialog(Shell parent, int style) {
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
		shell.setBackgroundImage(SWTResourceManager.getImage(exportDialog.class, "/images/3.png"));
		shell.setSize(450, 300);
		shell.setText("导出文件选择");

		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(99, 79, 90, 26);
		label.setText("文件名字：");

		text_tname = new Text(shell, SWT.BORDER);
		text_tname.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		text_tname.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		text_tname.setBounds(195, 79, 137, 26);

		combo_type = new Combo(shell, SWT.NONE);
		combo_type.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo_type.setText(combo_type.getText());
			}
		});
		combo_type.setItems(new String[] { "a", "b", "c" });
		combo_type.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		combo_type.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		combo_type.setBounds(195, 133, 136, 35);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(99, 136, 90, 26);
		label_1.setText("文件类型：");

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (combo_type.getText().equals(".txt")) {
					String tname = "D:\\workspaceYC134\\swt.YC134.项目\\src\\" + text_tname.getText()
							+ combo_type.getText();
					setFile(tname);
					establish();
				} else if (combo_type.getText().equals(".xlsx")) {
					String tname = "C:\\Users\\xinye\\Desktop\\" + text_tname.getText() + combo_type.getText();
					setFile(tname);
					FileWriter writer = null;
					try {
						// true 实现追加 即每次导出到文件不会清空原有内容
						writer = new FileWriter(file, true);
						// 设置title
						BufferedWriter bWriter = new BufferedWriter(writer);
						String title = "   学院\t 专业\t     班级\t    姓名\t性别\t年龄      \t身份证号"
								+ "           \t手机号    \t是否核对\t是否报到\t未到人数\t总人数\n";
						if (file.length() == 0) {// title 只写入一次
							bWriter.write(title);
						}
						for (int i = 0; i < Utils.info1.size(); i++) {
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("name")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("mname")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("cname")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("sname")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("sex")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("age")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("idcard")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("phone")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("checkinfo")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("regis")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("unum")) + "\t");
							bWriter.write(Utils.objToString(Utils.info1.get(i).get("cnum")) + "\t");
							bWriter.newLine();// 添加换行符
						}
						Utils.showMessage(shell, "提示", "导出成功！");
						bWriter.close();
						shell.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						if (null != writer) {
							try {
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				} else {
					String tname = "C:\\Users\\xinye\\Desktop\\" + text_tname.getText() + combo_type.getText();
					setFile(tname);
					establish();
				}

			}
		});
		button.setBounds(234, 190, 98, 30);
		button.setText("确认");

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		button_1.setBounds(99, 190, 98, 30);
		button_1.setText("取消");
		showType();
	}

	// 创建文件
	public void setFile(String tname) {
		file = new File(tname);// 创建文件
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 将学院显示在下拉框
	private void showType() {
		try {
			String[] arr = new String[] { ".txt", ".doc", ".docx", ".xlsx" };
			// 设置到下拉列表中
			combo_type.removeAll();// 清除下拉列表中的数据
			combo_type.setItems(arr);
			combo_type.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void establish() {
		FileWriter writer = null;
		try {
			// true 实现追加 即每次导出到文件不会清空原有内容
			writer = new FileWriter(file, true);
			// 设置title
			BufferedWriter bWriter = new BufferedWriter(writer);
			String title = "   学院\t 专业\t     班级\t    姓名\t性别\t年龄      \t身份证号"
					+ "           \t手机号    \t是否核对\t是否报到\t未到人数\t总人数\n";
			if (file.length() == 0) {// title 只写入一次
				bWriter.write(title);
			}
			for (int i = 0; i < Utils.info1.size(); i++) {
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("name")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("mname")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("cname")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("sname")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("sex")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("age")) + "\t\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("idcard")) + "\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("phone")) + "\t\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("checkinfo")) + "\t\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("regis")) + "\t\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("unum")) + "\t\t");
				bWriter.write(Utils.objToString(Utils.info1.get(i).get("cnum")) + "\t\t");
				bWriter.newLine();// 添加换行符
			}
			Utils.showMessage(shell, "提示", "导出成功！");
			bWriter.close();
			shell.dispose();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
