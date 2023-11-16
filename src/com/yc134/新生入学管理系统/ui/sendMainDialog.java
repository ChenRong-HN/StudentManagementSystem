package com.yc134.新生入学管理系统.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc134.新生入学管理系统.dao.receiveDao;
import com.yc134.新生入学管理系统.dao.showNoticeDao;
import com.yc134.新生入学管理系统.utils.Utils;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class sendMainDialog extends Dialog {

	protected Object result;
	protected static Shell shell;
	static Composite composite;
	ScrolledComposite scrolledComposite;
	List<Map<String, Object>> list = showNoticeDao.show_All();

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public sendMainDialog(Shell parent, int style) {
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
		shell.setSize(1103, 731);
		shell.setText("通知");

		scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(26, 27, 973, 555);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		/////////

		////////////
		for (int i = 0; i < list.size(); i++) {
			showNotice(list, i);
		}
		//////////////
		scrolledComposite.setContent(composite);
		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			// 写通知
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendDialog send = new sendDialog(shell, SWT.NONE);
				send.open();
				shell.dispose();
			}
		});
		button.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		button.setBounds(455, 614, 98, 40);
		button.setText("写通知");
		MainUi.state = 0;
	}

	public static void showNotice(List<Map<String, Object>> list, int i) {

		Map<String, Object> map = list.get(i);
		// 双击查看通知内容
		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		composite_1.setBounds(10, 10 + 104 * i, 933, 104);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_1.setBounds(10, 38, 48, 24);
		label_1.setText("标题：");

		Label label_title = new Label(composite_1, SWT.NONE);
		label_title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_title.setBounds(64, 38, 232, 24);
		label_title.setText(map.get("title").toString());

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_2.setBounds(302, 38, 48, 24);
		label_2.setText("时间：");

		Label label_date = new Label(composite_1, SWT.NONE);
		label_date.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_date.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_date.setBounds(356, 38, 179, 24);
		label_date.setText(map.get("time").toString());

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_3.setText("发布人：");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_3.setBounds(548, 38, 66, 24);

		Label label_author = new Label(composite_1, SWT.NONE);
		label_author.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				contentDialog dialog = new contentDialog(shell, SWT.NONE);
				dialog.content = map.get("content").toString();
				dialog.title = map.get("title").toString();
						
				dialog.open() ;	
				
			}
		});
		label_author.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		label_author.setBounds(620, 38, 163, 24);
		label_author.setText(map.get("mname").toString());

		Button btn_delete = new Button(composite_1, SWT.NONE);
		btn_delete.addSelectionListener(new SelectionAdapter() {
			// 删除
			@Override
			public void widgetSelected(SelectionEvent e) {
				int s = showNoticeDao.deleteNotice(Integer.parseInt(map.get("nid").toString()));
				int r = receiveDao.delete(Integer.parseInt(map.get("nid").toString()));
				sendMainDialog.showNotice(list, i);
				Utils.showMessage(shell, "提示", "删除成功！");
				MainUi.state = 2;
				shell.dispose();

			}
		});
		btn_delete.setBounds(844, 38, 79, 30);
		btn_delete.setText("删除");
	}
}
