package diaoyu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class Business {

	/***
	 * 随机分配人员到上半场和下半场的4个区
	 * 
	 * @param allPersonInfo
	 */
	public static void randomPerson(AllPersonInfo allPersonInfo) {
		List<Person> personList = new ArrayList<Person>(
				allPersonInfo.finalPersonMap.values());
		Collections.shuffle(personList);
		Map<String, Person> tempPersonMap = allPersonInfo.finalPersonMap;
		List<Person> personListAreaA = new ArrayList<Person>();
		List<Person> personListAreaB = new ArrayList<Person>();
		List<Person> personListAreaC = new ArrayList<Person>();
		List<Person> personListAreaD = new ArrayList<Person>();
		allPersonInfo.tab1_personListAreaA.clear();
		allPersonInfo.tab1_personListAreaB.clear();
		allPersonInfo.tab1_personListAreaC.clear();
		allPersonInfo.tab1_personListAreaD.clear();

		// 拆分成4个区域
		int pageSize = 1;
		pageSize = personList.size() / 4;
		int i = 0;
		int start = 0;
		int end = 0;
		while (true) {
			if (i > 3) {
				pageSize = 1;
			}
			start = end;
			end = start + pageSize;

			if (start >= personList.size()) {
				break;
			}
			if (end > personList.size()) {
				end = personList.size();
			}
			System.out.println(i + ": " + start + " , " + end);
			if (i % 4 == 0) {
				personListAreaA.addAll(personList.subList(start, end));
			} else if (i % 4 == 1) {
				personListAreaB.addAll(personList.subList(start, end));
			} else if (i % 4 == 2) {
				personListAreaC.addAll(personList.subList(start, end));
			} else if (i % 4 == 3) {
				personListAreaD.addAll(personList.subList(start, end));
			}
			i++;
		}
		i = 1;
		for (Person p : personListAreaA) {
			p.setFirsSitNo("A" + i);
			p.setFirstArea("A");
			tempPersonMap.put(p.getNo(), p);
			allPersonInfo.tab1_personListAreaA.add(p);
			i++;
		}
		i = 1;
		for (Person p : personListAreaB) {
			p.setFirsSitNo("B" + i);
			p.setFirstArea("B");
			tempPersonMap.put(p.getNo(), p);
			allPersonInfo.tab1_personListAreaB.add(p);
			i++;
		}
		i = 1;
		for (Person p : personListAreaC) {
			p.setFirsSitNo("C" + i);
			p.setFirstArea("C");
			tempPersonMap.put(p.getNo(), p);
			allPersonInfo.tab1_personListAreaC.add(p);
			i++;
		}
		i = 1;
		for (Person p : personListAreaD) {
			p.setFirsSitNo("D" + i);
			p.setFirstArea("D");
			tempPersonMap.put(p.getNo(), p);
			allPersonInfo.tab1_personListAreaD.add(p);
			i++;
		}
		// A与C交换,B与D并随机分配
		List<Person> personListAreaA2 = new ArrayList<Person>(personListAreaC);
		List<Person> personListAreaB2 = new ArrayList<Person>(personListAreaD);
		List<Person> personListAreaC2 = new ArrayList<Person>(personListAreaA);
		List<Person> personListAreaD2 = new ArrayList<Person>(personListAreaB);
		Collections.shuffle(personListAreaA2);
		Collections.shuffle(personListAreaB2);
		Collections.shuffle(personListAreaC2);
		Collections.shuffle(personListAreaD2);

//		allPersonInfo.tab2_personListAreaA.clear();
//		allPersonInfo.tab2_personListAreaB.clear();
//		allPersonInfo.tab2_personListAreaC.clear();
//		allPersonInfo.tab2_personListAreaD.clear();

		i = 1;
		for (Person p : personListAreaA2) {
			p.setSecSitNo("A" + i);
			p.setSecArea("A");
//			allPersonInfo.tab2_personListAreaA.add(p);
			tempPersonMap.get(p.getNo()).setSecArea(p.getSecArea());
			tempPersonMap.get(p.getNo()).setSecSitNo(p.getSecSitNo());
			i++;
		}
		i = 1;
		for (Person p : personListAreaB2) {
			p.setSecSitNo("B" + i);
			p.setSecArea("B");
//			allPersonInfo.tab2_personListAreaB.add(p);
			tempPersonMap.get(p.getNo()).setSecArea(p.getSecArea());
			tempPersonMap.get(p.getNo()).setSecSitNo(p.getSecSitNo());
			i++;
		}
		i = 1;
		for (Person p : personListAreaC2) {
			p.setSecSitNo("C" + i);
			p.setSecArea("C");
//			allPersonInfo.tab2_personListAreaC.add(p);
			tempPersonMap.get(p.getNo()).setSecArea(p.getSecArea());
			tempPersonMap.get(p.getNo()).setSecSitNo(p.getSecSitNo());
			i++;
		}
		i = 1;
		for (Person p : personListAreaD2) {
			p.setSecSitNo("D" + i);
			p.setSecArea("D");
//			allPersonInfo.tab2_personListAreaD.add(p);
			tempPersonMap.get(p.getNo()).setSecArea(p.getSecArea());
			tempPersonMap.get(p.getNo()).setSecSitNo(p.getSecSitNo());
			i++;
		}
		
		//重新设置第二场位置与区域到allPersonInfo.tab1
		for (Person p : allPersonInfo.tab1_personListAreaA) {
			p.setSecSitNo(tempPersonMap.get(p.getNo()).getSecSitNo());
			p.setSecArea(tempPersonMap.get(p.getNo()).getSecArea());
		}
		for (Person p : allPersonInfo.tab1_personListAreaB) {
			p.setSecSitNo(tempPersonMap.get(p.getNo()).getSecSitNo());
			p.setSecArea(tempPersonMap.get(p.getNo()).getSecArea());
		}
		for (Person p : allPersonInfo.tab1_personListAreaC) {
			p.setSecSitNo(tempPersonMap.get(p.getNo()).getSecSitNo());
			p.setSecArea(tempPersonMap.get(p.getNo()).getSecArea());
		}
		for (Person p : allPersonInfo.tab1_personListAreaD) {
			p.setSecSitNo(tempPersonMap.get(p.getNo()).getSecSitNo());
			p.setSecArea(tempPersonMap.get(p.getNo()).getSecArea());
		}
		//截至目前表格1里面已包含表格2的区域与座位号的数据了

	}

	/*** 更新所有信息 ****/
	public static void updateAllTable(Table table, Table table_1,
			AllPersonInfo allPersonInfo) {
		table.removeAll();
		table_1.removeAll();

		// 如果还没分过区，则直接使用map中的数据
		if (allPersonInfo.tab1_personListAreaA.size() < 1
				&& allPersonInfo.tab1_personListAreaB.size() < 1
				&& allPersonInfo.tab1_personListAreaC.size() < 1
				&& allPersonInfo.tab1_personListAreaD.size() < 1) {
			for (Person p : allPersonInfo.finalPersonMap.values()) {
				setTableDate(table, p, 1);
				setTableDate(table_1, p, 2);
			}
		} else {

			// 重新显示表格1
			List<Person> temp = new ArrayList<Person>();
			temp.addAll(allPersonInfo.tab1_personListAreaA);
			temp.addAll(allPersonInfo.tab1_personListAreaB);
			temp.addAll(allPersonInfo.tab1_personListAreaC);
			temp.addAll(allPersonInfo.tab1_personListAreaD);
			// 显示表格1
			for (Person p : temp) {
				setTableDate(table, p, 1);
			}

			// 显示表格2，修改为直接用表格1的数据
//			temp.clear();
//			temp.addAll(allPersonInfo.tab2_personListAreaA);
//			temp.addAll(allPersonInfo.tab2_personListAreaB);
//			temp.addAll(allPersonInfo.tab2_personListAreaC);
//			temp.addAll(allPersonInfo.tab2_personListAreaD);
			for (Person p : temp) {
				setTableDate(table_1, p, 2);
			}

		}

	}

	/***
	 * 保存数据到文件
	 * 
	 * @param fileName
	 * @param allPersonInfo
	 * @return
	 */
	public static boolean saveToFile(String fileName,
			AllPersonInfo allPersonInfo) {
		System.out.println("save to file ==="+fileName);
		if (fileName != null) {
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new FileOutputStream(fileName));
				out.writeObject(allPersonInfo);
				out.flush();
				out.close();
				out = null;
				return true;
			} catch (IOException e1) {
				e1.printStackTrace();
				return false;
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else {
			return false;
		}
	}

	public static void setTableDate(Table table, Person p, int num) {
		// 显示表格1
		TableItem item1 = new TableItem(table, SWT.NONE);
		if (table.getItemCount() % 2 == 0) {
			item1.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		}
		if (num == 1) {
			item1.setText(new String[] { p.getFirstArea(), p.getNo(),
					p.getName(), p.getPhoneNum(), p.getFirsSitNo(),
					p.getFirstWeight(), p.getFirstPoint() });
		} else {
			item1.setText(new String[] { p.getSecArea(), p.getNo(),
					p.getName(), p.getPhoneNum(), p.getSecSitNo(),
					p.getSecWeight(), p.getSecPoint(), p.getTotalPoint(),
					p.getLastSeq() });
		}
	}
	
	/***
	 * 计算下半场设置总总量
	 * @param table
	 * @param allPersonInfo
	 */
	public static void setSecWeight(Table table, AllPersonInfo allPersonInfo) {
		// 按区域计算得分
		TableItem[] allItems = table.getItems();
		if (allItems.length < 1) {
			return;
		}

		// Step 1先从文本框里面获取重量,计算总重量
		for (TableItem item : allItems) {
			String area = item.getText(0);
			String no = item.getText(1);
			String weight = item.getText(5);
			if(StringCommon.isNull(weight)){
				weight="0";
			}
			if (area.equals("A")) {
				for (Person p : allPersonInfo.tab1_personListAreaC) {
					if (p.no.equals(no)) {
						p.setSecWeight(weight);
						allPersonInfo.finalPersonMap.get(p.no).setSecWeight(
								weight);

						// 总重量
						double t1W = 0;
						double t1W_f = 0;
						double t1W_s = 0;
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getFirstWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getFirstWeight())) {
							t1W_f = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getFirstWeight());
						}
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getSecWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getSecWeight())) {
							t1W_s = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getSecWeight());
						}

						t1W = t1W_f + t1W_s;
						allPersonInfo.finalPersonMap.get(p.no).setTotalWeight(
								t1W + "");
						p.setTotalWeight(t1W+"");
						break;
					}
				}
			} else if (area.equals("B")) {
				for (Person p : allPersonInfo.tab1_personListAreaD) {
					if (p.no.equals(no)) {
						p.setSecWeight(weight);
						allPersonInfo.finalPersonMap.get(p.no).setSecWeight(
								weight);
						// 总重量
						double t1W = 0;
						double t1W_f = 0;
						double t1W_s = 0;
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getFirstWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getFirstWeight())) {
							t1W_f = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getFirstWeight());
						}
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getSecWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getSecWeight())) {
							t1W_s = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getSecWeight());
						}

						t1W = t1W_f + t1W_s;
						allPersonInfo.finalPersonMap.get(p.no).setTotalWeight(
								t1W + "");
						p.setTotalWeight(t1W+"");
						break;
					}
				}
			} else if (area.equals("C")) {
				for (Person p : allPersonInfo.tab1_personListAreaA) {
					if (p.no.equals(no)) {
						p.setSecWeight(weight);
						allPersonInfo.finalPersonMap.get(p.no).setSecWeight(
								weight);
						// 总重量
						double t1W = 0;
						double t1W_f = 0;
						double t1W_s = 0;
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getFirstWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getFirstWeight())) {
							t1W_f = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getFirstWeight());
						}
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getSecWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getSecWeight())) {
							t1W_s = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getSecWeight());
						}

						t1W = t1W_f + t1W_s;
						allPersonInfo.finalPersonMap.get(p.no).setTotalWeight(
								t1W + "");
						p.setTotalWeight(t1W+"");
						break;
					}
				}
			} else if (area.equals("D")) {
				for (Person p : allPersonInfo.tab1_personListAreaB) {
					if (p.no.equals(no)) {
						p.setSecWeight(weight);
						allPersonInfo.finalPersonMap.get(p.no).setSecWeight(
								weight);
						// 总重量
						double t1W = 0;
						double t1W_f = 0;
						double t1W_s = 0;
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getFirstWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getFirstWeight())) {
							t1W_f = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getFirstWeight());
						}
						if (allPersonInfo.finalPersonMap.get(p.no)
								.getSecWeight() != null
								&& !"".equals(allPersonInfo.finalPersonMap.get(
										p.no).getSecWeight())) {
							t1W_s = Double
									.parseDouble(allPersonInfo.finalPersonMap
											.get(p.no).getSecWeight());
						}

						t1W = t1W_f + t1W_s;
						allPersonInfo.finalPersonMap.get(p.no).setTotalWeight(
								t1W + "");
						p.setTotalWeight(t1W+"");
						break;
					}
				}
			}
		}

		// Step 2根据重量分区域计算得分
		calcSecPoint(allPersonInfo);
		// Step 3根据总得分和总重量计算总名次
		calcLastSeq(allPersonInfo);
	}
	/****
	 * 计算下半场得分和总得分
	 * @param allPersonInfo
	 */
	public static void calcSecPoint(AllPersonInfo allPersonInfo) {
		// Step 2根据重量分区域计算得分
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
					if (o1.getSecWeight() != null
							&& !"".equals(o1.getSecWeight())) {
						t1 = Double.parseDouble(o1.getSecWeight());
					}
					if (o2.getSecWeight() != null
							&& !"".equals(o2.getSecWeight())) {
						t2 = Double.parseDouble(o2.getSecWeight());
					}
					
					if(t2-t1>0){
						return 1;
					}else if(t2-t1==0){
						return 0;
					}else{
						return -1;
					}				
										
				}
			});
			//设置第二场分数
			int point = 1;
			//ste1设置分数
			for (Person p : temp) {
					p.setSecPoint(point*1.0 + "");
					//如果是0重量则按人数+1积分
					if(StringCommon.isNull(p.getSecWeight())||0==Double.parseDouble(p.getSecWeight())){
						p.setSecPoint((temp.size()+1)+"");
					}
				point++;
			}
			//step2按第二次重量分组
			Map<String,List<Person>> map = new HashMap<String,List<Person>>();
			for(int k=0;k<temp.size();k++){
			      Person user = (Person)temp.get(k);
				  String weight =  user.getSecWeight();
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
						total+=Double.parseDouble(ps.get(m).getSecPoint());
					}
					double avg=total/ps.size();
					for(int m=0;m<ps.size();m++){
						ps.get(m).setSecPoint(avg+"");
					}
				}
			 }
			// 设置第二场分数和总得分,设置到MAP中
			for (Person p : temp) {
				// 如果一样重则得分一样
				p.setFirstPoint(allPersonInfo.finalPersonMap.get(p.no).getFirstPoint());// 上半场分数
				p.setTotalPoint((Double.parseDouble(p.getFirstPoint()) + Double.parseDouble(p.getSecPoint())) + "");// 下半场分数
				allPersonInfo.finalPersonMap.get(p.no).setSecPoint(
						p.getSecPoint());
				allPersonInfo.finalPersonMap.get(p.no).setTotalPoint(
						p.getTotalPoint());
			}
		}
		
		// 从MAP中渠道总得分，重新设置对应区域列表中的对象
		for (Person p : allPersonInfo.tab1_personListAreaA) {
			p.setSecPoint(allPersonInfo.finalPersonMap.get(p.no).getSecPoint());// 下半场得分
			p.setTotalPoint(allPersonInfo.finalPersonMap.get(p.no)
					.getTotalPoint());// 下半场总得分
		}
		for (Person p : allPersonInfo.tab1_personListAreaB) {
			p.setSecPoint(allPersonInfo.finalPersonMap.get(p.no).getSecPoint());// 下半场得分
			p.setTotalPoint(allPersonInfo.finalPersonMap.get(p.no)
					.getTotalPoint());// 下半场总得分
		}
		for (Person p : allPersonInfo.tab1_personListAreaC) {
			p.setSecPoint(allPersonInfo.finalPersonMap.get(p.no).getSecPoint());// 下半场得分
			p.setTotalPoint(allPersonInfo.finalPersonMap.get(p.no)
					.getTotalPoint());// 下半场总得分
		}
		for (Person p : allPersonInfo.tab1_personListAreaD) {
			p.setSecPoint(allPersonInfo.finalPersonMap.get(p.no).getSecPoint());// 下半场得分
			p.setTotalPoint(allPersonInfo.finalPersonMap.get(p.no)
					.getTotalPoint());// 下半场总得分
		}
	}

	/***
	 * 计算总名次
	 * 
	 * @param allPersonInfo
	 */
	public static void calcLastSeq(AllPersonInfo allPersonInfo) {
		// step4 根据最终的分数和重量计算名次
		List<Person> lastSeqtemp = new ArrayList<Person>(
				allPersonInfo.finalPersonMap.values());
		// 排序
		Collections.sort(lastSeqtemp, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {

				// 总分数
				double t1Point = 0;
				double t2Point = 0;
				if (o1.getTotalPoint() != null
						&& !"".equals(o1.getTotalPoint())) {
					t1Point = Double.parseDouble(o1.getTotalPoint());
				}
				if (o2.getTotalPoint() != null
						&& !"".equals(o2.getTotalPoint())) {
					t2Point = Double.parseDouble(o2.getTotalPoint());
				}
				//按分数计算
				if (t1Point != t2Point) {
					if(t1Point - t2Point>0){
						return 1;
					}else if(t1Point - t2Point<0){
						return -1;
					}
					return 0;
				} else {
					//如果分数相同则按按单次最小得分计算min
					double t1W=Math.min(Double.parseDouble(o1.getFirstPoint()), Double.parseDouble(o1.getSecPoint()));
					double t2W=Math.min(Double.parseDouble(o2.getFirstPoint()), Double.parseDouble(o2.getSecPoint()));
					if(t1W==t2W){
						//如果最小分数也相同，则按重量
						double t1W2=Double.parseDouble(o1.getFirstWeight())+ Double.parseDouble(o1.getSecWeight());
						double t2W2=Double.parseDouble(o2.getFirstWeight())+Double.parseDouble(o2.getSecWeight());
						return new Double(t2W2).compareTo(new Double(t1W2));
					}else{
						return new Double(t1W).compareTo(new Double(t2W));	
					}
					
				}
			}
		});
		/****************************************'
		 * 
		 * 根据总得分和总重量计算名次，优先按总得分计算，得分越高，名次越靠前
		 *****************************************/
		
		// 设置名次
		int index = 1;//名次
		// STEP4设置最后的名次
		for (Person p : lastSeqtemp) {
				p.setLastSeq(index + "");
			allPersonInfo.finalPersonMap.get(p.no).setLastSeq(p.getLastSeq());
			index++;
		}

	}
 
}
