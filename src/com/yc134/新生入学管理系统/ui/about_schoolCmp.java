package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class about_schoolCmp extends Composite {
	private Text txtHttpswwwhniteducn;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public about_schoolCmp(Composite parent, int style) {
		super(parent, SWT.NONE);
		setBackground(SWTResourceManager.getColor(169, 169, 169));
		setForeground(SWTResourceManager.getColor(255, 250, 250));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackgroundImage(SWTResourceManager.getImage(about_schoolCmp.class, "/images/6.png"));

		Label label = new Label(this, SWT.NONE);
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("华文中宋", 22, SWT.BOLD));
		label.setBounds(10, 38, 1060, 54);
		label.setText("湖南工学院");

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel.setBounds(119, 115, 865, 36);
		lblNewLabel.setText("湖南工学院（Hunan Institute of Technology）位于湖南衡阳，是2007年经教育部批准由湖南建材高等");

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(68, 165, 916, 36);
		lblNewLabel_1.setText("专科学校和湖南大学衡阳分校合并升格的省属公办普通本科院校。2010年3月湖南工业科技职工大学整体并入，");

		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_2.setBounds(68, 219, 916, 36);
		lblNewLabel_2.setText("2011年湖南工学院成为全国百所“卓越工程师培养计划”高校，CDIO工程教育联盟成员单位。2018年，成为湖南");

		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_3.setBounds(68, 273, 916, 36);
		lblNewLabel_3.setText("省省级硕士立项建设单位。截至2023年1月，学校有三个校区，占地1400余亩，校舍建筑面积56万余平方米，");

		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		lblNewLabel_4.setBounds(68, 326, 916, 24);
		lblNewLabel_4.setText("教学科研仪器设备总值2.7亿元，图书馆纸质藏书169万余册；设有17个二级教学院（部），49个本科专业；教");

		Label label_1 = new Label(this, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		label_1.setText("职工1200余人，全日制在校学生19919人。");
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		label_1.setBounds(68, 377, 977, 24);

		txtHttpswwwhniteducn = new Text(this, SWT.NONE);
		txtHttpswwwhniteducn.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		txtHttpswwwhniteducn.setText("https://www.hnit.edu.cn");
		txtHttpswwwhniteducn.setBounds(746, 407, 238, 34);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
