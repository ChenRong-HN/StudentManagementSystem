package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.studentDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class perfect_infoCmp extends Composite {
	private Text text_college;
	private Text text_major;
	private Text text_class;
	private Text text_sname;
	private Text text_phone;
	private Text text_sid;
	private Text text_dorm;
	private Text text_address;
	private Text text_pname;
	private Text text_pphone;
	private String picture;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public perfect_infoCmp(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(perfect_infoCmp.class, "/images/5.jpg"));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("学院：");
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(57, 69, 54, 24);

		text_college = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_college.setEnabled(false);
		text_college.setBounds(117, 66, 176, 30);

		Label label = new Label(this, SWT.NONE);
		label.setText("专业：");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(315, 69, 54, 24);

		text_major = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_major.setEnabled(false);
		text_major.setBounds(372, 66, 135, 30);

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("班级：");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(566, 69, 60, 24);

		text_class = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_class.setEnabled(false);
		text_class.setBounds(632, 66, 145, 30);

		Label label_picture = new Label(this, SWT.BORDER | SWT.CENTER);
		label_picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				FileDialog fd = new FileDialog(getShell());
				fd.setFilterExtensions(new String[] { "*.jpg", "*.png", "*.jpeg", "*.*" });// 后缀名设置
				picture = fd.open();// 返回的是图片的绝对路径
				if (null == picture || "".equals(picture)) {
					Utils.showMessage(getShell(), "消息提示", "未选中图片");
					return;
				}
				try {
					File file = new File(picture);
					InputStream in = new FileInputStream(file);
					Image image = new Image(Display.getDefault(), in);
					label_picture.setImage(image);
					StudentUi.label.setImage(SWTResourceManager.getImage(picture));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		// 图片上传

		label_picture.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_picture.setText("请点击上传头像");
		label_picture.setBounds(846, 54, 152, 200);

		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("姓名：");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(57, 214, 54, 24);

		text_sname = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_sname.setEnabled(false);
		text_sname.setBounds(117, 208, 135, 30);

		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("电话：");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_4.setBounds(315, 214, 54, 24);

		text_phone = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_phone.setEnabled(false);
		text_phone.setBounds(372, 208, 135, 33);

		Label label_9 = new Label(this, SWT.NONE);
		label_9.setText("学号：");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_9.setBounds(566, 217, 54, 24);

		text_sid = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_sid.setEnabled(false);
		text_sid.setBounds(626, 214, 145, 30);

		Label label_10 = new Label(this, SWT.NONE);
		label_10.setText("宿舍号：");
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_10.setBounds(57, 358, 60, 24);

		text_dorm = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_dorm.setEnabled(false);
		text_dorm.setBounds(120, 355, 135, 30);

		Label label_6 = new Label(this, SWT.NONE);
		label_6.setText("家庭住址：");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_6.setBounds(57, 478, 75, 24);

		text_address = new Text(this, SWT.BORDER);
		text_address.setBounds(140, 472, 229, 30);

		Label label_7 = new Label(this, SWT.NONE);
		label_7.setText("监护人姓名：");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_7.setBounds(315, 358, 90, 24);

		text_pname = new Text(this, SWT.BORDER);
		text_pname.setBounds(408, 355, 135, 30);

		Label label_8 = new Label(this, SWT.NONE);
		label_8.setText("监护人电话：");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_8.setBounds(597, 358, 92, 24);

		text_pphone = new Text(this, SWT.BORDER);
		text_pphone.setBounds(695, 355, 152, 30);

		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sdao.updateS(text_pname.getText(), text_pphone.getText(), text_address.getText(), picture,
						text_sid.getText());
			}
		});
		btnNewButton.setText("保存");
		btnNewButton.setBounds(428, 550, 183, 34);
		query();

	}

	studentDao sdao = new studentDao();

	// 将学生信息填入
	public void query() {
		List<Map<String, Object>> list = sdao.selectBySid(StudentLogin.login);
		System.out.println(list);
		text_college.setText((String) list.get(0).get("name"));
		text_major.setText((String) list.get(0).get("mname"));
		text_class.setText((String) list.get(0).get("cname"));
		text_sname.setText((String) list.get(0).get("sname"));
		text_phone.setText((String) list.get(0).get("phone"));
		text_sid.setText(Utils.objToString(list.get(0).get("sid")));
		text_dorm.setText((String) list.get(0).get("dorm"));
		text_pname.setText((String) list.get(0).get("pname"));
		text_pphone.setText((String) list.get(0).get("pphone"));
		text_address.setText((String) list.get(0).get("address"));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}