package diaoyu;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -3877355190058028270L;
	String no;//参数编号
	String name;//姓名
	String phoneNum;//证件号码
	String firstArea;//上半场区域
	String firsSitNo;//上半场钓位
	String firstWeight;//上半场重量
	String firstPoint;//上半场得分
	
	String secArea;//下半场区域
	String secSitNo;//下半场钓位
	String secWeight;//下半场重量
	String secPoint;//下半场得分
	String totalWeight;//总重量
	String totalPoint;//总得分
	String lastSeq;//总名次
	
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getLastSeq() {
		return lastSeq;
	}
	public void setLastSeq(String lastSeq) {
		this.lastSeq = lastSeq;
	}
	public String getFirstWeight() {
		return firstWeight;
	}
	public void setFirstWeight(String firstWeight) {
		this.firstWeight = StringCommon.trimNull(firstWeight);
	}
	public String getFirstPoint() {
		return firstPoint;
	}
	public void setFirstPoint(String firstPoint) {
		this.firstPoint = StringCommon.trimNull(firstPoint);
	}
	public String getSecWeight() {
		return secWeight;
	}
	public void setSecWeight(String secWeight) {
		this.secWeight = StringCommon.trimNull(secWeight);
	}
	public String getSecPoint() {
		return secPoint;
	}
	public void setSecPoint(String secPoint) {
		this.secPoint = StringCommon.trimNull(secPoint);
	}
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
		this.totalPoint = StringCommon.trimNull(totalPoint);
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = StringCommon.trimNull(no);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = StringCommon.trimNull(phoneNum);
	}
	 
	 
	public String getFirstArea() {
		return firstArea;
	}
	public void setFirstArea(String firstArea) {
		this.firstArea = StringCommon.trimNull(firstArea);
	}
	public String getFirsSitNo() {
		return firsSitNo;
	}
	public void setFirsSitNo(String firsSitNo) {
		this.firsSitNo = StringCommon.trimNull(firsSitNo);
	}
	public String getSecArea() {
		return secArea;
	}
	public void setSecArea(String secArea) {
		this.secArea = StringCommon.trimNull(secArea);
	}
	public String getSecSitNo() {
		return secSitNo;
	}
	public void setSecSitNo(String secSitNo) {
		this.secSitNo = secSitNo;
	}
	
}
