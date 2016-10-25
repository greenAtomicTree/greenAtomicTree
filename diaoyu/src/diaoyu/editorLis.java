package diaoyu;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

class editorLis extends MouseAdapter {
	TableEditor editor;
	Table table;
	List<Integer> canModify;
	int tableSeq;
	public editorLis(TableEditor editorx,Table tablex,List<Integer> canModifyx,int tableSeqx) {
		editor = editorx;
		table=tablex;
		canModify=canModifyx;
		tableSeq=tableSeqx;
	}

	public void mouseDown(MouseEvent event) {
		Control old = editor.getEditor();
		if (old != null)
			old.dispose();

		final Point pt = new Point(event.x, event.y);

		final TableItem item = table.getItem(pt);
		if (item == null) {
			return;
		}
		int column = -1;
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			Rectangle rect = item.getBounds(i);
			if (rect.contains(pt)) {
				column = i;
				break;
			}
		}

		if (!canModify.contains(column)) {
			return;
		}
		final Text text = new Text(table, SWT.NONE);
		text.setForeground(item.getForeground());

		text.setText(item.getText(column));
		text.setForeground(item.getForeground());
		text.selectAll();
		text.setFocus();

		editor.minimumWidth = text.getBounds().width;

		editor.setEditor(text, item, column);
		final String sourceText=text.getText();
		final String personNo=item.getText(1);//编号
		final String AreaNo=item.getText(0);//区域编号
		final int col = column;
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				item.setText(col, text.getText());
				System.out.println("Text modified to " + text.getText());
				// 设置第5列的值
				// item.setText(5, text.getText());
			}
		});
		text.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println(arg0.getSource());
				
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				System.out.println(arg0.getSource());
				String destText=text.getText();
				AllPersonInfo allPersonInfo=MainApp.allPersonInfo;
				if(!sourceText.equals(destText)){
					System.out.println("text change to --->"+destText);
					//变更名次和证件数据5是重量
					if(col==2||col==3||col==5){
						if(col==2){
							//变更用户名称或者证件信息
							System.out.println(allPersonInfo.finalPersonMap.keySet().size());
							System.out.println(allPersonInfo.finalPersonMap.get(personNo));
							allPersonInfo.finalPersonMap.get(personNo).setName(destText);
						}else if(col==3){
							//变更用户名称或者证件信息
							allPersonInfo.finalPersonMap.get(personNo).setPhoneNum(destText);
						}else if(col==5){
							//变更重量
							if(tableSeq==1){
								allPersonInfo.finalPersonMap.get(personNo).setFirstWeight(destText);
							}else{
								allPersonInfo.finalPersonMap.get(personNo).setSecWeight(destText);
							}
						}
						//获取当前区域
						List<Person> temp=new ArrayList<Person>();
						List<Person> temp2=new ArrayList<Person>();
						//重新设置区域数据
						if(tableSeq==1){
							switch(AreaNo){
								case "A":temp=allPersonInfo.getTab1_personListAreaA();break;
								case "B":temp=allPersonInfo.getTab1_personListAreaB();break;
								case "C":temp=allPersonInfo.getTab1_personListAreaC();break;
								case "D":temp=allPersonInfo.getTab1_personListAreaD();break;
							}
							
							//重新展示表格2中的姓名和证件号
							if(col==2||col==3){
								TableItem[] allitems=MainApp.tableViewer_1.getTable().getItems();
								if(allitems!=null){
									for(TableItem item:allitems){
										if(item.getText(1).equals(personNo)){
											item.setText(col, destText);
											break;
										}
									}
								}
							}
						}else{
							switch(AreaNo){
							case "C":temp=allPersonInfo.getTab1_personListAreaA();break;
							case "D":temp=allPersonInfo.getTab1_personListAreaB();break;
							case "A":temp=allPersonInfo.getTab1_personListAreaC();break;
							case "B":temp=allPersonInfo.getTab1_personListAreaD();break;
							}
						}
						//重新设置各个区域中的值
							for(Person p:temp){
								if(p.no.equals(personNo)){
									p.setName(allPersonInfo.finalPersonMap.get(personNo).getName());
									p.setPhoneNum(allPersonInfo.finalPersonMap.get(personNo).getPhoneNum());
									p.setFirstWeight(allPersonInfo.finalPersonMap.get(personNo).getFirstWeight());
									p.setSecWeight(allPersonInfo.finalPersonMap.get(personNo).getSecWeight());
									break;
								}
							} 
							//保存文件
							if(!StringCommon.isNull(MainApp.lastRecordName)){
								Business.saveToFile(MainApp.lastRecordName, allPersonInfo);
							}
					}
					
				}else{
					System.out.println("text no change to --->"+destText);
				}
			}
		});
		
		
		  //VerifyListener的位置很关键。
		text.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent event) {
				if(col==2||col==3){
					event.doit = true;
					return;
				}

				event.doit = false;
				char myChar = event.character;
				// Allow 0~9 .
				if (text.getText().indexOf(".") == -1) {// 没有小数点时，可以输入小数点。(因为此验证是确保一次只能输入一个字符。)
					if (myChar == '0' || myChar == '1' || myChar == '2'
							|| myChar == '3' || myChar == '4' || myChar == '5'
							|| myChar == '6' || myChar == '7' || myChar == '8'
							|| myChar == '9' || myChar == '\b' || myChar == '.') {
						event.doit = true;
					}
				} else {// 只要有小数点，就不能输入小数点。
					if (myChar == '0' || myChar == '1' || myChar == '2'
							|| myChar == '3' || myChar == '4' || myChar == '5'
							|| myChar == '6' || myChar == '7' || myChar == '8'
							|| myChar == '9' || myChar == '\b') {
						event.doit = true;
					}
				}
			}
		});
		   
		   
		   // 监控回车事件
		text.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.keyCode == 13) {
					e.doit = true;
					//转到下一行
					int curRow=table.getSelectionIndex(); //from 0
					int allRowNo=table.getItemCount();//from 1
					
					//触发focusLost
					table.forceFocus();  
					//释放焦点
				       text.dispose();
					if(curRow!=allRowNo-1){
						//先选中
						table.select(curRow+1);
						//然后设置focus
						TableItem nextItem=table.getItem(table.getSelectionIndex());
				       //开始编辑下一行
				       System.out.println("first point --->"+pt);
				       //先找到列
				       Rectangle rect=null;
				       for (int i = 0, n = table.getColumnCount(); i < n; i++) {
							rect = nextItem.getBounds(i);
							if(i<n-1){
								Rectangle nextRect = nextItem.getBounds(i+1);
								if(nextRect.x>=pt.x){
									break;
								}
							}
						}
				       if(rect!=null){
				    	 //得到坐标
					       Point pt2 = new Point(pt.x,rect.y);
					       Event e2 = new Event();
					       e2.x=pt.x;
					       e2.y=pt2.y;
					       e2.widget = table;
					       System.out.println("second point --->"+pt2);
					       table.notifyListeners(SWT.MouseDown, e2);
				       }
					}
				}
			}
		});

	}

}