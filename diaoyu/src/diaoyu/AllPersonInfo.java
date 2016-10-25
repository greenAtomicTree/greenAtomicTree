package diaoyu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AllPersonInfo implements Serializable{
	private static final long serialVersionUID = -7380088959311194873L;
	List<Person> tab1_personListAreaA=new ArrayList<Person>();
	List<Person> tab1_personListAreaB=new ArrayList<Person>();
	List<Person> tab1_personListAreaC=new ArrayList<Person>();
	List<Person> tab1_personListAreaD=new ArrayList<Person>();
	
//	List<Person> tab2_personListAreaA=new ArrayList<Person>();
//	List<Person> tab2_personListAreaB=new ArrayList<Person>();
//	List<Person> tab2_personListAreaC=new ArrayList<Person>();
//	List<Person> tab2_personListAreaD=new ArrayList<Person>();
	
	
	//用来获取数据使用
	Map<String,Person> finalPersonMap=new TreeMap<String,Person>();


	public List<Person> getTab1_personListAreaA() {
		return tab1_personListAreaA;
	}


	public void setTab1_personListAreaA(List<Person> tab1_personListAreaA) {
		this.tab1_personListAreaA = tab1_personListAreaA;
	}


	public List<Person> getTab1_personListAreaB() {
		return tab1_personListAreaB;
	}


	public void setTab1_personListAreaB(List<Person> tab1_personListAreaB) {
		this.tab1_personListAreaB = tab1_personListAreaB;
	}


	public List<Person> getTab1_personListAreaC() {
		return tab1_personListAreaC;
	}


	public void setTab1_personListAreaC(List<Person> tab1_personListAreaC) {
		this.tab1_personListAreaC = tab1_personListAreaC;
	}


	public List<Person> getTab1_personListAreaD() {
		return tab1_personListAreaD;
	}


	public void setTab1_personListAreaD(List<Person> tab1_personListAreaD) {
		this.tab1_personListAreaD = tab1_personListAreaD;
	}


//	public List<Person> getTab2_personListAreaA() {
//		return tab2_personListAreaA;
//	}
//
//
//	public void setTab2_personListAreaA(List<Person> tab2_personListAreaA) {
//		this.tab2_personListAreaA = tab2_personListAreaA;
//	}
//
//
//	public List<Person> getTab2_personListAreaB() {
//		return tab2_personListAreaB;
//	}
//
//
//	public void setTab2_personListAreaB(List<Person> tab2_personListAreaB) {
//		this.tab2_personListAreaB = tab2_personListAreaB;
//	}
//
//
//	public List<Person> getTab2_personListAreaC() {
//		return tab2_personListAreaC;
//	}
//
//
//	public void setTab2_personListAreaC(List<Person> tab2_personListAreaC) {
//		this.tab2_personListAreaC = tab2_personListAreaC;
//	}
//
//
//	public List<Person> getTab2_personListAreaD() {
//		return tab2_personListAreaD;
//	}
//
//
//	public void setTab2_personListAreaD(List<Person> tab2_personListAreaD) {
//		this.tab2_personListAreaD = tab2_personListAreaD;
//	}


	public Map<String, Person> getFinalPersonMap() {
		return finalPersonMap;
	}


	public void setFinalPersonMap(Map<String, Person> finalPersonMap) {
		this.finalPersonMap = finalPersonMap;
	}


	 
	
	
	
	
	
}
