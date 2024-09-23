package model;
import java.io.Serializable;

public class Product implements Serializable{
	private int    id;
	private int    flag; //フラグ
	private int    arrangementnumber; //手配番号
	private String workperformancedate; //作業実績日
	private String machinecode; //機械コード
	private int    workmannumber; //作業担当者
	private String workinghours; //日勤夜勤区分
	private String sparepartnumber1; //予備品番1
	private String sparepartnumber2; //予備品番2
	private String sparepartnumber3; //予備品番3
	private float  workmantime; //作業工数	
	private float  machinetime; //機械時間	
	private int    numbernodefectiveproducts; //良品数
	private int    totalnumberdefects; //合計不良数
	private String remarks; //備考
	private String defectclassificationcode1; //不良分類コード1
	private int    numberdefects1; //不良数1
	private String defectclassificationcode2; //不良分類コード2
	private int    numberdefects2; //不良数2
	private String defectclassificationcode3; //不良分類コード3
	private int    numberdefects3; //不良数3
	private String defectclassificationcode4; //不良分類コード4
	private String numberdefects4; //不良数4
	private String defectclassificationcode5; //不良分類コード5
	private String numberdefects5; //不良数5
	private String defectclassificationcode6; //不良分類コード6
	private String numberdefects6; //不良数6
	private String defectclassificationcode7; //不良分類コード7
	private String numberdefects7; //不良数7
	private String defectclassificationcode8; //不良分類コード8
	private String numberdefects8; //不良数8
	private String defectclassificationcode9; //不良分類コード9
	private String numberdefects9; //不良数9	
	private String sparenumberdefects1; //予備不良数1
	private String sparenumberdefects2; //予備不良数2
	private String sparenumberdefects3; //予備不良数3
	
	public Product(){}
	public Product(int flag,int arrangementnumber,String workperformancedate,String machinecode,int workmannumber,String workinghours,
			String sparepartnumber1,String sparepartnumber2,String sparepartnumber3,
			float workmantime,float machinetime,int numbernodefectiveproducts,int totalnumberdefects,String remarks,
			String defectclassificationcode1,int numberdefects1,String defectclassificationcode2,int numberdefects2,
			String defectclassificationcode3,int numberdefects3,String defectclassificationcode4,String numberdefects4,
			String defectclassificationcode5,String numberdefects5,String defectclassificationcode6,String numberdefects6,
			String defectclassificationcode7,String numberdefects7,String defectclassificationcode8,String numberdefects8,
			String defectclassificationcode9,String numberdefects9,
			String sparenumberdefects1,String sparenumberdefects2,String sparenumberdefects3){
		
		this.flag=flag;
		this.arrangementnumber=arrangementnumber;
		this.workperformancedate=workperformancedate;
		this.machinecode=machinecode;
		this.workmannumber=workmannumber;
		this.workinghours=workinghours;
		this.sparepartnumber1=sparepartnumber1;
		this.sparepartnumber2=sparepartnumber2;
		this.sparepartnumber3=sparepartnumber3;
		this.workmantime=workmantime;
		this.machinetime=machinetime;
		this.numbernodefectiveproducts=numbernodefectiveproducts;
		this.totalnumberdefects=totalnumberdefects;
		this.remarks=remarks;
		this.defectclassificationcode1=defectclassificationcode1;
		this.numberdefects1=numberdefects1;
		this.defectclassificationcode2=defectclassificationcode2;
		this.numberdefects2=numberdefects2;
		this.defectclassificationcode3=defectclassificationcode3;
		this.numberdefects3=numberdefects3;
		this.defectclassificationcode4=defectclassificationcode4;
		this.numberdefects4=numberdefects4;
		this.defectclassificationcode5=defectclassificationcode5;
		this.numberdefects5=numberdefects5;
		this.defectclassificationcode6=defectclassificationcode6;
		this.numberdefects6=numberdefects6;
		this.defectclassificationcode7=defectclassificationcode7;
		this.numberdefects7=numberdefects7;
		this.defectclassificationcode8=defectclassificationcode8;
		this.numberdefects8=numberdefects8;
		this.defectclassificationcode9=defectclassificationcode9;
		this.numberdefects9=numberdefects9;
		this.sparenumberdefects1=sparenumberdefects1;
		this.sparenumberdefects2=sparenumberdefects2;
		this.sparenumberdefects3=sparenumberdefects3;
	}
	public Product(int id,int flag,int arrangementnumber,String workperformancedate,String machinecode,int workmannumber,String workinghours,
			String sparepartnumber1,String sparepartnumber2,String sparepartnumber3,float workmantime,float machinetime,int numbernodefectiveproducts,
			int totalnumberdefects,String remarks,
			String defectclassificationcode1,int numberdefects1,String defectclassificationcode2,int numberdefects2,
			String defectclassificationcode3,int numberdefects3,String defectclassificationcode4,String numberdefects4,
			String defectclassificationcode5,String numberdefects5,String defectclassificationcode6,String numberdefects6,
			String defectclassificationcode7,String numberdefects7,String defectclassificationcode8,String numberdefects8,
			String defectclassificationcode9,String numberdefects9,
			String sparenumberdefects1,String sparenumberdefects2,String sparenumberdefects3){
		
		this(flag,arrangementnumber,workperformancedate,machinecode,workmannumber,workinghours,
		        sparepartnumber1,sparepartnumber2,sparepartnumber3,workmantime,
				machinetime,numbernodefectiveproducts,totalnumberdefects,remarks,
				defectclassificationcode1,numberdefects1,defectclassificationcode2,numberdefects2,
				defectclassificationcode3,numberdefects3,defectclassificationcode4,numberdefects4,
				defectclassificationcode5,numberdefects5,defectclassificationcode6,numberdefects6,
				defectclassificationcode7,numberdefects7,defectclassificationcode8,numberdefects8,
				defectclassificationcode9,numberdefects9,
				sparenumberdefects1,sparenumberdefects2,sparenumberdefects3);
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getArrangementnumber() {
		return arrangementnumber;
	}
	public void setArrangementnumber(int arrangementnumber) {
		this.arrangementnumber = arrangementnumber;
	}
	public String getWorkperformancedate() {
		return workperformancedate;
	}
	public void setWorkperformancedate(String workperformancedate) {
		this.workperformancedate = workperformancedate;
	}
	public String getMachinecode() {
		return machinecode;
	}
	public void setMachinecode(String machinecode) {
		this.machinecode = machinecode;
	}
	public int getWorkmannumber() {
		return workmannumber;
	}
	public void setWorkmannumber(int workmannumber) {
		this.workmannumber = workmannumber;
	}
	public String getWorkinghours() {
		return workinghours;
	}
	public void setWorkinghours(String workinghours) {
		this.workinghours = workinghours;
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
	public float getWorkmantime() {
		return workmantime;
	}
	public void setWorkmantime(float workmantime) {
		this.workmantime = workmantime;
	}
	public float getMachinetime() {
		return machinetime;
	}
	public void setMachinetime(float machinetime) {
		this.machinetime = machinetime;
	}
	public int getNumbernodefectiveproducts() {
		return numbernodefectiveproducts;
	}
	public void setNumbernodefectiveproducts(int numbernodefectiveproducts) {
		this.numbernodefectiveproducts = numbernodefectiveproducts;
	}
	public int getTotalnumberdefects() {
		return totalnumberdefects;
	}
	public void setTotalnumberdefects(int totalnumberdefects) {
		this.totalnumberdefects = totalnumberdefects;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDefectclassificationcode1() {
		return defectclassificationcode1;
	}
	public void setDefectclassificationcode1(String defectclassificationcode1) {
		this.defectclassificationcode1 = defectclassificationcode1;
	}
	public int getNumberdefects1() {
		return numberdefects1;
	}
	public void setNumberdefects1(int numberdefects1) {
		this.numberdefects1 = numberdefects1;
	}
	public String getDefectclassificationcode2() {
		return defectclassificationcode2;
	}
	public void setDefectclassificationcode2(String defectclassificationcode2) {
		this.defectclassificationcode2 = defectclassificationcode2;
	}
	public int getNumberdefects2() {
		return numberdefects2;
	}
	public void setNumberdefects2(int numberdefects2) {
		this.numberdefects2 = numberdefects2;
	}
	public String getDefectclassificationcode3() {
		return defectclassificationcode3;
	}
	public void setDefectclassificationcode3(String defectclassificationcode3) {
		this.defectclassificationcode3 = defectclassificationcode3;
	}
	public int getNumberdefects3() {
		return numberdefects3;
	}
	public void setNumberdefects3(int numberdefects3) {
		this.numberdefects3 = numberdefects3;
	}
	public String getDefectclassificationcode4() {
		return defectclassificationcode4;
	}
	public void setDefectclassificationcode4(String defectclassificationcode4) {
		this.defectclassificationcode4 = defectclassificationcode4;
	}
	public String getNumberdefects4() {
		return numberdefects4;
	}
	public void setNumberdefects4(String numberdefects4) {
		this.numberdefects4 = numberdefects4;
	}
	public String getDefectclassificationcode5() {
		return defectclassificationcode5;
	}
	public void setDefectclassificationcode5(String defectclassificationcode5) {
		this.defectclassificationcode5 = defectclassificationcode5;
	}
	public String getNumberdefects5() {
		return numberdefects5;
	}
	public void setNumberdefects5(String numberdefects5) {
		this.numberdefects5 = numberdefects5;
	}
	public String getDefectclassificationcode6() {
		return defectclassificationcode6;
	}
	public void setDefectclassificationcode6(String defectclassificationcode6) {
		this.defectclassificationcode6 = defectclassificationcode6;
	}
	public String getNumberdefects6() {
		return numberdefects6;
	}
	public void setNumberdefects6(String numberdefects6) {
		this.numberdefects6 = numberdefects6;
	}
	public String getDefectclassificationcode7() {
		return defectclassificationcode7;
	}
	public void setDefectclassificationcode7(String defectclassificationcode7) {
		this.defectclassificationcode7 = defectclassificationcode7;
	}
	public String getNumberdefects7() {
		return numberdefects7;
	}
	public void setNumberdefects7(String numberdefects7) {
		this.numberdefects7 = numberdefects7;
	}
	public String getDefectclassificationcode8() {
		return defectclassificationcode8;
	}
	public void setDefectclassificationcode8(String defectclassificationcode8) {
		this.defectclassificationcode8 = defectclassificationcode8;
	}
	public String getNumberdefects8() {
		return numberdefects8;
	}
	public void setNumberdefects8(String numberdefects8) {
		this.numberdefects8 = numberdefects8;
	}
	public String getDefectclassificationcode9() {
		return defectclassificationcode9;
	}
	public void setDefectclassificationcode9(String defectclassificationcode9) {
		this.defectclassificationcode9 = defectclassificationcode9;
	}
	public String getNumberdefects9() {
		return numberdefects9;
	}
	public void setNumberdefects9(String numberdefects9) {
		this.numberdefects9 = numberdefects9;
	}
	public String getSparenumberdefects1() {
		return sparenumberdefects1;
	}
	public void setSparenumberdefects1(String sparenumberdefects1) {
		this.sparenumberdefects1 = sparenumberdefects1;
	}
	public String getSparenumberdefects2() {
		return sparenumberdefects2;
	}
	public void setSparenumberdefects2(String sparenumberdefects2) {
		this.sparenumberdefects2 = sparenumberdefects2;
	}
	public String getSparenumberdefects3() {
		return sparenumberdefects3;
	}
	public void setSparenumberdefects3(String sparenumberdefects3) {
		this.sparenumberdefects3 = sparenumberdefects3;
	}
}