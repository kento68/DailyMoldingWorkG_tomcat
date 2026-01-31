package model;
import java.io.Serializable;

public class Partnumbermaster implements Serializable{
	private int    id;
	private String partnumber; //品番
	private String partname; //品名
	private String sparepartnumber1; //予備品番1
	private String sparepartnumber2; //予備品番2
	private String sparepartnumber3; //予備品番3
	private String takenumber; //取り数
	
	public Partnumbermaster(){}
	public Partnumbermaster(String partnumber,String partname,String sparepartnumber1,String sparepartnumber2,String sparepartnumber3,String takenumber){
		
		this.partnumber=partnumber;
		this.partname=partname;
		this.sparepartnumber1=sparepartnumber1;
		this.sparepartnumber2=sparepartnumber2;
		this.sparepartnumber3=sparepartnumber3;
		this.takenumber=takenumber;
	}
	public Partnumbermaster(int id,String partnumber,String partname,String sparepartnumber1,String sparepartnumber2,String sparepartnumber3,String takenumber){
		
		this(partnumber,partname,sparepartnumber1,sparepartnumber2,sparepartnumber3,takenumber);
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartnumber() {
		return partnumber;
	}
	public void setPartnumber(String partnumber) {
		this.partnumber = partnumber;
	}
	public String getPartname() {
		return partname;
	}
	public void setPartname(String partname) {
		this.partname = partname;
	}
	public String getSparepartnumber1() {
		return sparepartnumber1;
	}
	public void setSparepartnumber1(String sparepartnumber1) {
		this.sparepartnumber1 = sparepartnumber1;
	}
	public String getSparepartnumber2() {
		return sparepartnumber2;
	}
	public void setSparepartnumber2(String sparepartnumber2) {
		this.sparepartnumber2 = sparepartnumber2;
	}
	public String getSparepartnumber3() {
		return sparepartnumber3;
	}
	public void setSparepartnumber3(String sparepartnumber3) {
		this.sparepartnumber3 = sparepartnumber3;
	}
	public String getTakenumber() {
		return takenumber;
	}
	public void setTakenumber(String takenumber) {
		this.takenumber = takenumber;
	}
}