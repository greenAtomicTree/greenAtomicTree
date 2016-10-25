package diaoyu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainApp {

	private Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text txtNewText;
	private Text txtNewText_1;
	private Table table;
	private Table table_1;
	private Text txtNewText_5;
	public static TableViewer tableViewer_1 = null;
	public static TableViewer tableViewer = null;
	public static AllPersonInfo allPersonInfo=new AllPersonInfo();
	public static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
	public static  String lastRecordName = "";
	public static boolean result=false;
	private int Max_PERSON = 500;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			MainApp window = new MainApp();
//			window.open();
//			// 加载文件
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		if(login()){
			try {
				MainApp window = new MainApp();
				window.open();
//				// 加载文件
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	private static boolean login() {
		
		
		
		final Display display = Display.getDefault();
		final Shell shell = new Shell();
		shell.setSize(300, 400);
		
		int width = shell.getMonitor().getClientArea().width;
		int height = shell.getMonitor().getClientArea().height;
		int x = shell.getSize().x;
		int y = shell.getSize().y;
		if (x > width) {
		    shell.getSize().x = width;
		}
		if (y > height) {
		    shell.getSize().y = height;
		}
		shell.setLocation((width - x) / 2, (height - y) / 2);


		
		
		shell.setText("登录");
		shell.setToolTipText("登录");
		final Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(20, 25, 210, 220);
		composite.setToolTipText("Composite容器");
		final Group group = new Group(composite, SWT.NONE);
		group.setBounds(20, 20, 160, 160);
		group.setText("请输入：");
		group.setToolTipText("Group容器");
		final Label label1 = new Label(group, SWT.NONE);
		label1.setBounds(10, 30, 45, 20);
		label1.setText("用户名：");
		final Text text1 = new Text(group, SWT.BORDER);
		text1.setBounds(65, 25, 80, 25);

		final Label label2 = new Label(group, SWT.NONE);
		label2.setBounds(10, 70, 45, 20);
		label2.setText("密    码：");

		final Text text2 = new Text(group, SWT.BORDER | SWT.PASSWORD);
		text2.setBounds(65, 65, 80, 25);

		final Button button1 = new Button(group, SWT.NONE);
		button1.setBounds(40, 120, 50, 25);
		button1.setText("确定");

		final Button button2 = new Button(group, SWT.NONE);
		button2.setBounds(95, 120, 50, 25);
		button2.setText("取消");
		
		//确定
		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = StringCommon.trimNull(text1.getText());
				String pwd = StringCommon.trimNull(text2.getText());
				if (StringCommon.isNull(name)) {
					return;
				}
				if("admin".equals(name)&&"admin123".equals(pwd)){
					result=true;
					shell.dispose();
				}else{
					MessageDialog.openError(shell, "提示", "请输入正确的用户名和密码！");
				}
			}
		});
		
		//取消
		button2.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						shell.dispose();
					}
				});
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result; 
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimized(true);
		shell.setMaximized(true);
		shell.setFullScreen(true);
		shell.setMinimumSize(new Point(800, 600));
		shell.setSize(752, 428);
		shell.setText("SWT Application");

		// 窗口关闭事件
		shell.addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				if (StringCommon.isNull(lastRecordName)&& allPersonInfo.finalPersonMap.keySet().size() > 0) {
					try {
						lastRecordName=URLDecoder.decode(Class.class.getClass()
								.getResource("/").getPath(), "UTF-8")+df2.format(new Date())+".txt";
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					String fileName=lastRecordName;
					Business.saveToFile(fileName, allPersonInfo);
					MessageDialog.openInformation(shell, "数据库文件保存成功", "数据库文件成功保存到"
							+ lastRecordName);
					e.doit = true;
				} else if( !StringCommon.isNull(lastRecordName)&&allPersonInfo.finalPersonMap.keySet().size() > 0){
					String fileName=lastRecordName;
					Business.saveToFile(fileName, allPersonInfo);
					MessageDialog.openInformation(shell, "数据库文件保存成功", "数据库文件成功保存到"
							+ lastRecordName);
					e.doit = true;
				}
			}
		});

		final List<Integer> canModify = new ArrayList<Integer>();
		canModify.add(2);
		canModify.add(3);
		canModify.add(5);
//		canModify.add(6);

		final List<Integer> canModify2 = new ArrayList<Integer>();
		canModify2.add(5);
//		canModify2.add(6);

		// 区号
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		tabFolder.setBounds(10, 10, 800, 600);

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("信息录入");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite);
		formToolkit.paintBordersFor(composite);
		composite.setLayout(null);

		Label label = formToolkit.createLabel(composite, "参赛姓名：", SWT.NONE);
		label.setBounds(10, 10, 61, 17);

		txtNewText = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtNewText.setText("");
		txtNewText.setBounds(119, 7, 239, 23);

		Label label_1 = formToolkit
				.createLabel(composite, "手机号码/身份证", SWT.NONE);
		label_1.setBounds(10, 51, 101, 17);

		txtNewText_1 = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtNewText_1.setText("");
		txtNewText_1.setBounds(117, 51, 241, 23);

		final Button button = formToolkit.createButton(composite, "登记录入",
				SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = StringCommon.trimNull(txtNewText.getText());
				if (StringCommon.isNull(name)) {
					return;
				}
				if (allPersonInfo.finalPersonMap.keySet().size() >= Max_PERSON) {
					MessageDialog.openError(shell, "无法录入", "人数已达" + Max_PERSON
							+ "人上线，无法录入！");
					return;
				}
				
				//保存文件
				if(StringCommon.isNull(lastRecordName)){
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					dialog.setFilterPath("");// 设置默认的路径
					dialog.setText("保存数据到文件");// 设置对话框的标题
					dialog.setFileName("数据库_" + df.format(new Date()) + ".txt");// 设置默认的文件名
					dialog.setFilterNames(new String[] { "txt (*.txt)", "所有文件(*.*)" });// 设置扩展名
					dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });// 设置文件扩展名
					String fileName = dialog.open();//
					if(!StringCommon.isNull(fileName)){
						lastRecordName=fileName;
					}else{
						return;
					}
				}
				
				String no = "1";
				//如果已经有这个编号了，从1号开始重新编号,防止有删除的数据
				if(allPersonInfo.finalPersonMap.containsKey(no)){					
					no="1";
					while(allPersonInfo.finalPersonMap.containsKey(no)){
						no=(Integer.parseInt(no)+1)+"";
					}
				}			 
				 				
				Person p = new Person();
				p.setNo(no);
				p.setName(name);
				p.setPhoneNum(txtNewText_1.getText());
				allPersonInfo.finalPersonMap.put(no, p);
				//显示表格
				Business.setTableDate(table, p, 1);
				Business.setTableDate(table_1, p, 1);
				//设置总人数
				txtNewText_5.setText(allPersonInfo.finalPersonMap.keySet()
						.size() + "");
				MessageDialog.openInformation(shell, "操作成功",
						"参数编号：" + p.getNo() + " 号");
				txtNewText.setText("");
				txtNewText_1.setText("");
				txtNewText.forceFocus();
				
				
				//保存文件
				if(Business.saveToFile(lastRecordName,allPersonInfo)){
					System.out.println("lastRecordName==="+lastRecordName);
				}else{
					MessageDialog.openError(shell, "保存失败", "文件无法保存到"
							+ lastRecordName);
				}
				System.out.println("lastRecordName==="+lastRecordName);

			}
		});
		button.setBounds(10, 112, 80, 27);
		//登记录入人员信息
		class regAdapter extends KeyAdapter{
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) {
					String name = StringCommon.trimNull(txtNewText.getText());
					if (StringCommon.isNull(name)) {
						return;
					}
					if (allPersonInfo.finalPersonMap.keySet().size() >= Max_PERSON) {
						MessageDialog.openError(shell, "无法录入", "人数已达" + Max_PERSON
								+ "人上线，无法录入！");
						return;
					}
					
					//保存文件
					if(StringCommon.isNull(lastRecordName)){
						FileDialog dialog = new FileDialog(shell, SWT.SAVE);
						dialog.setFilterPath("");// 设置默认的路径
						dialog.setText("保存数据到文件");// 设置对话框的标题
						dialog.setFileName("数据库_" + df.format(new Date()) + ".txt");// 设置默认的文件名
						dialog.setFilterNames(new String[] { "txt (*.txt)", "所有文件(*.*)" });// 设置扩展名
						dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });// 设置文件扩展名
						String fileName = dialog.open();//
						if(!StringCommon.isNull(fileName)){
							lastRecordName=fileName;
						}else{
							return;
						}
					}
					
					String no = "1";
					//如果已经有这个编号了，从1号开始重新编号,防止有删除的数据
					if(allPersonInfo.finalPersonMap.containsKey(no)){					
						no="1";
						while(allPersonInfo.finalPersonMap.containsKey(no)){
							no=(Integer.parseInt(no)+1)+"";
						}
					}			 
					
					 
					Person p = new Person();
					p.setNo(no);
					p.setName(name);
					p.setPhoneNum(txtNewText_1.getText());
					allPersonInfo.finalPersonMap.put(no, p);
					//显示表格
					Business.setTableDate(table, p, 1);
					Business.setTableDate(table_1, p, 1);
					//设置总人数
					txtNewText_5.setText(allPersonInfo.finalPersonMap.keySet()
							.size() + "");
					MessageDialog.openInformation(shell, "操作成功",
							"参数编号：" + p.getNo() + " 号");
					txtNewText.setText("");
					txtNewText_1.setText("");
					txtNewText.forceFocus();
					
					txtNewText_5.setText(allPersonInfo.finalPersonMap.keySet()
							.size() + "");
					//保存文件
					if(Business.saveToFile(lastRecordName,allPersonInfo)){
						System.out.println("lastRecordName==="+lastRecordName);
					}else{
						MessageDialog.openError(shell, "保存失败", "文件无法保存到"
								+ lastRecordName);
					}
					System.out.println("lastRecordName==="+lastRecordName);
				}

			}
		}
		// 姓名可以直接回车
		txtNewText.addKeyListener(new regAdapter());
		//身份信息可以直接回车
		txtNewText_1.addKeyListener(new regAdapter());

		Label label_4 = formToolkit.createLabel(composite, "总人数：", SWT.NONE);
		label_4.setBounds(119, 117, 61, 17);

		txtNewText_5 = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtNewText_5.setEnabled(false);
		txtNewText_5.setEditable(false);
		txtNewText_5.setText("");
		txtNewText_5.setBounds(186, 114, 153, 23);

		// 打开文件按钮
		Button button_5 = new Button(composite, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.OPEN);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("打开比赛记录");// 设置对话框的标题
				dialog.setFileName("record.txt");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "文本文件 (*.txt)",
						"所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if (fileName == null) {
					return;
				}
				lastRecordName = fileName;
				File file = new File(fileName);
				ObjectInputStream in = null;
				try {
					in = new ObjectInputStream(new FileInputStream(file));
					allPersonInfo = (AllPersonInfo) in.readObject();
					Business.updateAllTable(table, table_1, allPersonInfo);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e3) {
							e3.printStackTrace();
						}
					}
				}
				txtNewText_5.setText(allPersonInfo.finalPersonMap.keySet().size() + "");
			}
		});
		button_5.setBounds(28, 205, 108, 27);
		formToolkit.adapt(button_5, true, true);
		button_5.setText("打开比赛记录");

		// 保存文件按钮
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			// 保存记录到文件
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("保存数据到文件");// 设置对话框的标题
				dialog.setFileName("数据库_" + df.format(new Date()) + ".txt");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "txt (*.txt)", "所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if(Business.saveToFile(fileName,allPersonInfo)){
					MessageDialog.openInformation(shell, "保存成功", "文件成功保存到"
							+ fileName);
				}else{
					MessageDialog.openError(shell, "保存失败", "文件无法保存到"
							+ fileName);
				}
			}
		});
		btnNewButton_1.setBounds(156, 205, 120, 27);
		formToolkit.adapt(btnNewButton_1, true, true);
		btnNewButton_1.setText("保存比赛记录");
		//清空记录
		Button button_6 = new Button(composite, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (lastRecordName.equals("")
						&& allPersonInfo.finalPersonMap.keySet().size() > 0) {
					try {
						lastRecordName=URLDecoder.decode(Class.class.getClass()
								.getResource("/").getPath(), "UTF-8")+df2.format(new Date())+".txt";
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					String fileName=lastRecordName;
					Business.saveToFile(fileName, allPersonInfo);
					MessageDialog.openInformation(shell, "数据库文件保存成功", "历史纪录文件成功保存到"
							+ lastRecordName);
				} else if( allPersonInfo.finalPersonMap.keySet().size() > 0){
					String fileName=lastRecordName;
					Business.saveToFile(fileName, allPersonInfo);
					MessageDialog.openInformation(shell, "数据库文件保存成功", "历史纪录文件成功保存到"
							+ lastRecordName);
				}
				//清空所有记录
				lastRecordName="";
				allPersonInfo=new AllPersonInfo();
				Business.updateAllTable(table, table_1, allPersonInfo);
				//新文件
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("选择新比赛记录保存的位置");// 设置对话框的标题
				dialog.setFileName("数据库_" + df.format(new Date()) + ".txt");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "txt (*.txt)", "所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.txt", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if(!StringCommon.isNull(fileName)){
					lastRecordName=fileName;
				}else{
					return;
				}
			}
		});
		button_6.setBounds(316, 205, 135, 27);
		formToolkit.adapt(button_6, true, true);
		button_6.setText("重新新建比赛数据");

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("第一轮");

		Composite composite_1 = formToolkit
				.createComposite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		formToolkit.paintBordersFor(composite_1);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		tableViewer = new TableViewer(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 34, 756, 400);
		
		final TableColumn firstAreaCol = new TableColumn(table, SWT.NONE);
		firstAreaCol.setWidth(47); 
		firstAreaCol.setText("区域");
		final TableColumn newColumnTableColumn = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn.setWidth(54);
		newColumnTableColumn.setText("编号");

		final TableColumn newColumnTableColumn_1 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_1.setWidth(102);
		newColumnTableColumn_1.setText("姓名");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_2.setWidth(138);
		newColumnTableColumn_2.setText("证件号码");

		final TableColumn newColumnTableColumn_3 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_3.setWidth(94);
		newColumnTableColumn_3.setText("一轮场次");

		final TableColumn newColumnTableColumn_4 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_4.setWidth(112);
		newColumnTableColumn_4.setText("一轮重量");

		final TableColumn newColumnTableColumn_5 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_5.setWidth(108);
		newColumnTableColumn_5.setText("一轮得分");
		final TableEditor editor1 = new TableEditor(table);

		table.addMouseListener(new editorLis(editor1, table, canModify,1));
		editor1.horizontalAlignment = SWT.LEFT;
		editor1.grabHorizontal = true;

		formToolkit.paintBordersFor(table);

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		btnNewButton.setLocation(10, 1);
		btnNewButton.setSize(132, 27);
		// 随机分配
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (allPersonInfo.finalPersonMap.keySet()==null||allPersonInfo.finalPersonMap.keySet().size() <= 4) {
					MessageDialog.openError(shell, "操作失败", "人数少于5人，无法随机分配！");
				}
				//随机分配
				Business.randomPerson(allPersonInfo);
				//显示表格
				Business.updateAllTable(table,table_1,allPersonInfo);
				MessageDialog
						.openInformation(shell, "操作成功", "随机分配上半场、下半场座位成功！");
				// 保存文件
				if (lastRecordName != null && !"".equals(lastRecordName)) {
						if(!Business.saveToFile(lastRecordName,allPersonInfo)){
							MessageDialog.openError(shell, "保存失败", "保存记录文件失败！");
						}
				}
			}
		});
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("随机分配所有人的座位");

		Button button_1 = formToolkit.createButton(composite_1, "导出报名信息",
				SWT.NONE);
		button_1.setLocation(285, 1);
		button_1.setSize(80, 27);
		// 计算上半场成绩
		Button button_4 = new Button(composite_1, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 按区域计算得分
				TableItem[] allItems = tableViewer.getTable().getItems();
				if (allItems.length < 1) {
					return;
				}
				for (TableItem item : allItems) {
					String area = item.getText(0);
					if(StringCommon.isNull(area)){
						MessageDialog.openError(shell, "提示", "请先分配座位，并输入重量");
						return;
					}
					String no = item.getText(1);
					String weight = item.getText(5);
					 if(StringCommon.isNull(weight)){
						 weight="0";
					 }
					if (area.equals("A")) {
						for (Person p : allPersonInfo.tab1_personListAreaA) {
							if (p.no.equals(no)) {
								p.setFirstWeight(weight);
								allPersonInfo.finalPersonMap.get(p.no).setFirstWeight(weight);
								break;
							}
						}
					} else if (area.equals("B")) {
						for (Person p : allPersonInfo.tab1_personListAreaB) {
							if (p.no.equals(no)) {
								p.setFirstWeight(weight);
								allPersonInfo.finalPersonMap.get(p.no).setFirstWeight(weight);
								break;
							}
						}
					} else if (area.equals("C")) {
						for (Person p : allPersonInfo.tab1_personListAreaC) {
							if (p.no.equals(no)) {
								p.setFirstWeight(weight);
								allPersonInfo.finalPersonMap.get(p.no).setFirstWeight(weight);
								break;
							}
						}
					} else if (area.equals("D")) {
						for (Person p : allPersonInfo.tab1_personListAreaD) {
							if (p.no.equals(no)) {
								p.setFirstWeight(weight);
								allPersonInfo.finalPersonMap.get(p.no).setFirstWeight(weight);
								break;
							}
						}
					}
				}
				// 重新计算得分，区域
				List<Person> temp = new ArrayList(allPersonInfo.tab1_personListAreaA);
				for (int i = 0; i < 4; i++) {
					switch (i) {
					case 0:
						temp = new ArrayList(allPersonInfo.tab1_personListAreaA);
						break;
					case 1:
						temp = new ArrayList(allPersonInfo.tab1_personListAreaB);
						break;
					case 2:
						temp = new ArrayList(allPersonInfo.tab1_personListAreaC);
						break;
					case 3:
						temp = new ArrayList(allPersonInfo.tab1_personListAreaD);
						break;
					}
					// 排序
					Collections.sort(temp, new Comparator<Person>() {
						@Override
						public int compare(Person o1, Person o2) {
							double t1 = 0;
							double t2 = 0;
							if (o1.getFirstWeight() != null
									&& !"".equals(o1.getFirstWeight())) {
								t1 = Double.parseDouble(o1.getFirstWeight());
							}
							if (o2.getFirstWeight() != null
									&& !"".equals(o2.getFirstWeight())) {
								t2 = Double.parseDouble(o2.getFirstWeight());
							}

							return new Double(t2).compareTo(new Double(t1));
						}
					});
					int point = 1;
					//ste1设置分数
					for (Person p : temp) {
							p.setFirstPoint(point*1.0 + "");
							//如果是0重量则按人数+1积分
							if(StringCommon.isNull(p.getFirstWeight())||0==Double.parseDouble(p.getFirstWeight())){
								p.setFirstPoint((temp.size()+1)+"");
							}
						point++;
					}
					//step2按第一次重量分组
					Map<String,List<Person>> map = new HashMap<String,List<Person>>();
					for(int k=0;k<temp.size();k++){
					      Person user = (Person)temp.get(k);
						  String weight =  user.getFirstWeight();
						  //0重量的不用取平均值了
						  if(StringCommon.isNull(weight)||0==Double.parseDouble(weight)){
							  continue;
						  }
						  if(map.containsKey(weight)){
						     map.get(weight).add(user);
						 }else{
						   List l = new ArrayList();
						   l.add(user);
						   map.put(weight, l);
						}
					}
					//step3重新设置分数，相同重量的取平均值
					Iterator it = map.keySet().iterator();    
					while (it.hasNext())     
					{    
						//取当前重量下的用户
						List<Person> ps=map.get(it.next());
						if(ps.size()>1){
							//先计算总分数
							double total=0;
							for(int m=0;m<ps.size();m++){
								total+=Double.parseDouble(ps.get(m).getFirstPoint());
							}
							double avg=total/ps.size();
							for(int m=0;m<ps.size();m++){
								ps.get(m).setFirstPoint(avg+"");
							}
						}
					 }  
					
					for(Person p:temp){
						// 重新设置对应区域列表中的对象
						List<Person> resetPerson = null;
						switch (p.getFirstArea()) {
						case "A":
							resetPerson = allPersonInfo.tab1_personListAreaA;
							break;
						case "B":
							resetPerson = allPersonInfo.tab1_personListAreaB;
							break;
						case "C":
							resetPerson = allPersonInfo.tab1_personListAreaC;
							break;
						case "D":
							resetPerson = allPersonInfo.tab1_personListAreaD;
							break;
						}
						for (Person reset : resetPerson) {
							if (p.getNo().equals(reset.getNo())) {
								reset.setFirstPoint(p.getFirstPoint());
								allPersonInfo.finalPersonMap.get(p.getNo()).setFirstPoint(p.getFirstPoint());
								break;
							}
						}
					}
					
					
					
				}
				// 重新设置集合
				List<Person> personList=new ArrayList<Person>();
				personList.addAll(allPersonInfo.tab1_personListAreaA);
				personList.addAll(allPersonInfo.tab1_personListAreaB);
				personList.addAll(allPersonInfo.tab1_personListAreaC);
				personList.addAll(allPersonInfo.tab1_personListAreaD);
				// 显示表格1
				table.removeAll();
				for (Person p : personList) {
					Business.setTableDate(table, p, 1);
				}
				//保存数据
				Business.saveToFile(lastRecordName, allPersonInfo);
			}
		});
		button_4.setBounds(388, 1, 109, 27);
		formToolkit.adapt(button_4, true, true);
		button_4.setText("计算上半场成绩");
		
		//删除人员
		Button b_del_person = new Button(composite_1, SWT.NONE);
		b_del_person.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[]  tableItems=table.getSelection();
				if(tableItems==null||tableItems.length>1){
					MessageDialog.openInformation(shell, "提示", "请选中一行记录！");
				}else{
					TableItem selItem= tableItems[0];
					String no=selItem.getText(1);
					String firstArea=selItem.getText(0);
					if(MessageDialog.openConfirm(shell, "确认删除", "确认删除编号：【"+selItem.getText(1)+"】，姓名：【"+selItem.getText(2)+"】吗？")){
						//删除表格1中的人员
						table.remove(table.getSelectionIndex());
						//删除表格2中的人员
						TableItem[] items2=table_1.getItems();
						for(int i=0;i<items2.length;i++){
							if(no.equals(items2[i].getText(1))){
								table_1.remove(i);
								break;
							}							
						}
						//删除全局变量中的人
						allPersonInfo.getFinalPersonMap().remove(no);
						//如果已经有区域了则，删除allPersonInfo中对应的表格
						if(!StringCommon.isNull(firstArea)){
							List<Person> pList1=null;
							if(firstArea.equals("A")){
								pList1=allPersonInfo.tab1_personListAreaA;
							}else if(firstArea.equals("B")){
								pList1=allPersonInfo.tab1_personListAreaB;
							}else if(firstArea.equals("C")){
								pList1=allPersonInfo.tab1_personListAreaC;
							}else if(firstArea.equals("D")){
								pList1=allPersonInfo.tab1_personListAreaD;
							}
							//删除第一个表格
							for(Person p:pList1){
								if(no.equals(p.getNo())){
									pList1.remove(p);
									break;
								}
							}
						}
						
						txtNewText_5.setText(allPersonInfo.finalPersonMap.keySet()
								.size() + "");
						//保存数据
						Business.saveToFile(lastRecordName, allPersonInfo);
					}
				}
			}
		});
		b_del_person.setBounds(529, 1, 80, 27);
		formToolkit.adapt(b_del_person, true, true);
		b_del_person.setText("删除人员");
		
		Button btn_tongji = new Button(composite_1, SWT.NONE);
		btn_tongji.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("导出赛场统计表");// 设置对话框的标题
				dialog.setFileName("赛场统计表_" + df.format(new Date()) + ".xls");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "excel (*.xls)",
						"所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.xls", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if (fileName == null) {
					return;
				}
				// 生成excel
				Map paramMap = new HashMap();
				String templateFileName = "";
				try {
					templateFileName = URLDecoder.decode(Class.class.getClass()
							.getResource("/").getPath(), "UTF-8")
							+ "3.xls";
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}

				String destFileName = fileName;
				List<Person> personListA=new ArrayList<Person>(allPersonInfo.tab1_personListAreaA);
				List<Person> personListB=new ArrayList<Person>(allPersonInfo.tab1_personListAreaB);
				List<Person> personListC=new ArrayList<Person>(allPersonInfo.tab1_personListAreaC);
				List<Person> personListD=new ArrayList<Person>(allPersonInfo.tab1_personListAreaD);
				if(personListA.size()<26){
					int sub=26-personListA.size();
					for(int i=0;i<sub;i++){
						Person p=new Person();
						p.setFirsSitNo("");
						p.setFirstArea("");
						p.setFirstPoint("");
						p.setFirstWeight("");
						p.setSecArea("");
						p.setSecPoint("");
						p.setSecSitNo("");
						p.setSecWeight("");
						p.setLastSeq("");
						p.setName("");
						p.setNo("");
						p.setTotalPoint("");
						p.setTotalWeight("");
						personListA.add(p);
					}
				}
				
				if(personListB.size()<26){
					int sub=26-personListB.size();
					for(int i=0;i<sub;i++){
						Person p=new Person();
						p.setFirsSitNo("");
						p.setFirstArea("");
						p.setFirstPoint("");
						p.setFirstWeight("");
						p.setSecArea("");
						p.setSecPoint("");
						p.setSecSitNo("");
						p.setSecWeight("");
						p.setLastSeq("");
						p.setName("");
						p.setNo("");
						p.setTotalPoint("");
						p.setTotalWeight("");
						personListB.add(p);
					}
				}
				if(personListC.size()<26){
					int sub=26-personListC.size();
					for(int i=0;i<sub;i++){
						Person p=new Person();
						p.setFirsSitNo("");
						p.setFirstArea("");
						p.setFirstPoint("");
						p.setFirstWeight("");
						p.setSecArea("");
						p.setSecPoint("");
						p.setSecSitNo("");
						p.setSecWeight("");
						p.setLastSeq("");
						p.setName("");
						p.setNo("");
						p.setTotalPoint("");
						p.setTotalWeight("");
						personListC.add(p);
					}
				}
				if(personListD.size()<26){
					int sub=26-personListD.size();
					for(int i=0;i<sub;i++){
						Person p=new Person();
						p.setFirsSitNo("");
						p.setFirstArea("");
						p.setFirstPoint("");
						p.setFirstWeight("");
						p.setSecArea("");
						p.setSecPoint("");
						p.setSecSitNo("");
						p.setSecWeight("");
						p.setLastSeq("");
						p.setName("");
						p.setNo("");
						p.setTotalPoint("");
						p.setTotalWeight("");
						personListD.add(p);
					}
				}
				
				paramMap.put("RESULTLISTA", personListA);
				paramMap.put("RESULTLISTB", personListB);
				paramMap.put("RESULTLISTC", personListC);
				paramMap.put("RESULTLISTD", personListD);
				XLSTransformer transformer = new XLSTransformer();
				try {
					transformer.transformXLS(templateFileName, paramMap,
							destFileName);
					MessageDialog.openInformation(shell, "操作成功", "文件保存在："
							+ destFileName + ",请到此目录下查找文件！");
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog.openError(shell, "操作失败",  "导出失败！，"+e1.getMessage());
				}  
			}
		});
		btn_tongji.setBounds(173, 1, 80, 27);
		formToolkit.adapt(btn_tongji, true, true);
		btn_tongji.setText("赛场统计表");
		// 导出报名表
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("导出报名表");// 设置对话框的标题
				dialog.setFileName("报名表_" + df.format(new Date()) + ".xls");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "excel (*.xls)",
						"所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.xls", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if (fileName == null) {
					return;
				}
				// 生成excel
				Map paramMap = new HashMap();
				String templateFileName = "";
				try {
					templateFileName = URLDecoder.decode(Class.class.getClass()
							.getResource("/").getPath(), "UTF-8")
							+ "1.xls";
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}
				String destFileName = fileName;
				List<Person> personList=new ArrayList<Person>();
				personList.addAll(allPersonInfo.tab1_personListAreaA);
				personList.addAll(allPersonInfo.tab1_personListAreaB);
				personList.addAll(allPersonInfo.tab1_personListAreaC);
				personList.addAll(allPersonInfo.tab1_personListAreaD);
				for(Person p:personList){
					//设置下半场属性
					p.setSecArea(allPersonInfo.finalPersonMap.get(p.no).getSecArea());
					p.setSecWeight(allPersonInfo.finalPersonMap.get(p.no).getSecWeight());
					p.setSecPoint(allPersonInfo.finalPersonMap.get(p.no).getSecPoint());
					p.setTotalPoint(allPersonInfo.finalPersonMap.get(p.no).getTotalPoint());
					p.setLastSeq(allPersonInfo.finalPersonMap.get(p.no).getLastSeq());
				}
				paramMap.put("RESULTLIST", personList);
				XLSTransformer transformer = new XLSTransformer();
				try {
					transformer.transformXLS(templateFileName, paramMap,
							destFileName);
					MessageDialog.openInformation(shell, "操作成功", "文件保存在："
							+ destFileName + ",请到此目录下查找文件！");
				} catch (ParsePropertyException e1) {
					e1.printStackTrace();
				} catch (InvalidFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("第二轮");

		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_2);
		formToolkit.paintBordersFor(composite_2);

		tableViewer_1 = new TableViewer(composite_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_1 = tableViewer_1.getTable();
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(1, 35, 753, 400);
		table_1.setLinesVisible(true);
		TableColumn secAreaCol = new TableColumn(table_1, SWT.NONE);
		secAreaCol.setWidth(39);
		secAreaCol.setText("区域");

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(56);
		tableColumn.setText("编号");

		TableColumn tableColumn_1 = new TableColumn(table_1, SWT.NONE);
		tableColumn_1.setWidth(99);
		tableColumn_1.setText("姓名");

		TableColumn tableColumn_2 = new TableColumn(table_1, SWT.NONE);
		tableColumn_2.setWidth(155);
		tableColumn_2.setText("证件号码");

		TableColumn tableColumn_3 = new TableColumn(table_1, SWT.NONE);
		tableColumn_3.setWidth(63);
		tableColumn_3.setText("二轮场次");

		TableColumn tableColumn_4 = new TableColumn(table_1, SWT.NONE);
		tableColumn_4.setWidth(63);
		tableColumn_4.setText("二轮重量");

		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(63);
		tableColumn_5.setText("二轮得分");

		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(63);
		tableColumn_6.setText("总得分");

		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(63);
		tableColumn_7.setText("名次");
		
		final TableEditor editor2 = new TableEditor(table_1);
		// 设置表格2的编辑器
		table_1.addMouseListener(new editorLis(editor2, table_1, canModify2,2));
		editor2.horizontalAlignment = SWT.LEFT;
		editor2.grabHorizontal = true;
		formToolkit.paintBordersFor(table_1);

		// 计算下半场得分
		Button button_2 = new Button(composite_2, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Person> persons=new ArrayList<Person>(allPersonInfo.finalPersonMap.values());
				for(Person p:persons){
					if(StringCommon.isNull(p.firstPoint)){
						MessageDialog.openError(shell, "无法计算", "请先计算第一轮比赛！");
						return ;
					}
				}
				Business.setSecWeight(table_1, allPersonInfo);
				// 重新显示表格2
				table_1.removeAll();
				List<Person> personList=new ArrayList<Person>();
				personList.addAll(allPersonInfo.tab1_personListAreaA);
				personList.addAll(allPersonInfo.tab1_personListAreaB);
				personList.addAll(allPersonInfo.tab1_personListAreaC);
				personList.addAll(allPersonInfo.tab1_personListAreaD);
				// 显示表格2
				for (Person p : personList) {
					// 显示表格2
					Business.setTableDate(table_1, p, 2);
				}
				//保存数据
				Business.saveToFile(lastRecordName, allPersonInfo);
			}
		});
		button_2.setBounds(236, 4, 177, 27);
		formToolkit.adapt(button_2, true, true);
		button_2.setText("计算下半场得分");
		// 导出成绩
		Button button_3 = new Button(composite_2, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterPath("");// 设置默认的路径
				dialog.setText("导出成绩");// 设置对话框的标题
				dialog.setFileName("比赛成绩_" + df.format(new Date()) + ".xls");// 设置默认的文件名
				dialog.setFilterNames(new String[] { "excel (*.xls)",
						"所有文件(*.*)" });// 设置扩展名
				dialog.setFilterExtensions(new String[] { "*.xls", "*.*" });// 设置文件扩展名
				String fileName = dialog.open();//
				if (fileName == null) {
					return;
				}
				// 生成excel
				Map paramMap = new HashMap();
				String templateFileName = "";
				try {
					templateFileName = URLDecoder.decode(Class.class.getClass()
							.getResource("/").getPath(), "UTF-8")
							+ "2.xls";
				} catch (UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}

				String destFileName = fileName;
				List<Person> personList=new ArrayList<Person>(allPersonInfo.finalPersonMap.values());
				// 排序
				Collections.sort(personList, new Comparator<Person>() {
					@Override
					public int compare(Person o1, Person o2) {
						double t1 = 0;
						double t2 = 0;
						if (o1.getLastSeq() != null
								&& !"".equals(o1.getLastSeq())) {
							t1 = Double.parseDouble(o1.getLastSeq());
						}
						if (o2.getLastSeq() != null
								&& !"".equals(o2.getLastSeq())) {
							t2 = Double.parseDouble(o2.getLastSeq());
						}
						return new Double(t1).compareTo(new Double(t2));
					}
				});
				paramMap.put("RESULTLIST", personList);
				XLSTransformer transformer = new XLSTransformer();
				try {
					transformer.transformXLS(templateFileName, paramMap,
							destFileName);
					MessageDialog.openInformation(shell, "操作成功", "文件保存在："
							+ destFileName + ",请到此目录下查找文件！");
				} catch (Exception e1) {
					e1.printStackTrace();
					MessageDialog.openError(shell, "操作失败",  "导出失败！，"+e1.getMessage());
				}  

			}
		});
		button_3.setBounds(471, 6, 128, 27);
		formToolkit.adapt(button_3, true, true);
		button_3.setText("导出成绩");

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("报表管理");

	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Table getTable_1() {
		return table_1;
	}

	public void setTable_1(Table table_1) {
		this.table_1 = table_1;
	}

	public TableViewer getTableViewer_1() {
		return tableViewer_1;
	}

	public void setTableViewer_1(TableViewer tableViewer_1) {
		this.tableViewer_1 = tableViewer_1;
	}

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public void setTableViewer(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	public AllPersonInfo getAllPersonInfo() {
		return allPersonInfo;
	}

	public void setAllPersonInfo(AllPersonInfo allPersonInfo) {
		this.allPersonInfo = allPersonInfo;
	}
}
