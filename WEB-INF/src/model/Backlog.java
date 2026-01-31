package model;
import java.io.Serializable;
import java.util.Date;


public class Backlog implements Serializable{
    private int    id; // ID
    private String inquiryDivision; 			// 照会区分
    private String supplierCode; 				// 仕入先コード
    private String supplierName; 				// 仕入先名
    private String productCode; 				// 品名コード
    private String productName; 				// 品名(品名仕様)
    private String orderSequence; 				// 順序
    private String orderNumber; 				// 手配番号
    private Date   deliveryDateFrom; 			// 納期From
    private Date   deliveryDateTo; 				// 納期To
    private Date   deliveryDate; 				// 納期
    private String instructionCode; 			// 指示先コード
    private String instructionName; 			// 指示先名
    private String arrangementNumber; 			// 手配番号
    private String itemCode; 					// 品名コード
    private String itemName; 					// 品名(品名仕様)
    private String sequence; 					// 順序
    private String processDetails; 				// 工程内容名
    private int    arrangementQuantity; 		// 手配数量
    private String unit; 						// 単位
    private float  arrangementUnitPrice; 			// 手配単価
    private int    remainingArrangementQuantity;// 手配残数量
    private String receivingInProgress; 		// 受入中

	
	public Backlog(){}
	public Backlog(String inquiryDivision, String supplierCode, String supplierName, String productCode,
            String productName, String orderSequence, String orderNumber, Date deliveryDateFrom,
            Date deliveryDateTo, Date deliveryDate, String instructionCode, String instructionName,
            String arrangementNumber, String itemCode, String itemName, String sequence, String processDetails,
            int arrangementQuantity, String unit, float arrangementUnitPrice,
            int remainingArrangementQuantity, String receivingInProgress) {

        this.inquiryDivision = inquiryDivision;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.productCode = productCode;
        this.productName = productName;
        this.orderSequence = orderSequence;
        this.orderNumber = orderNumber;
        this.deliveryDateFrom = deliveryDateFrom;
        this.deliveryDateTo = deliveryDateTo;
        this.deliveryDate = deliveryDate;
        this.instructionCode = instructionCode;
        this.instructionName = instructionName;
        this.arrangementNumber = arrangementNumber;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.sequence = sequence;
        this.processDetails = processDetails;
        this.arrangementQuantity = arrangementQuantity;
        this.unit = unit;
        this.arrangementUnitPrice = arrangementUnitPrice;
        this.remainingArrangementQuantity = remainingArrangementQuantity;
        this.receivingInProgress = receivingInProgress;
	}
	
	public Backlog(int id, String inquiryDivision, String supplierCode, String supplierName, String productCode,
            String productName, String orderSequence, String orderNumber, Date deliveryDateFrom,
            Date deliveryDateTo, Date deliveryDate, String instructionCode, String instructionName,
            String arrangementNumber, String itemCode, String itemName, String sequence, String processDetails,
            int arrangementQuantity, String unit, float arrangementUnitPrice,
            int remainingArrangementQuantity, String receivingInProgress) {
		
		this(inquiryDivision, supplierCode, supplierName, productCode,
			     productName, orderSequence, orderNumber, deliveryDateFrom,
			     deliveryDateTo, deliveryDate, instructionCode, instructionName,
			     arrangementNumber, itemCode, itemName, sequence, processDetails,
			     arrangementQuantity, unit, arrangementUnitPrice,
			     remainingArrangementQuantity, receivingInProgress);
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInquiryDivision() {
		return inquiryDivision;
	}
	public void setInquiryDivision(String inquiryDivision) {
		this.inquiryDivision = inquiryDivision;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrderSequence() {
		return orderSequence;
	}
	public void setOrderSequence(String orderSequence) {
		this.orderSequence = orderSequence;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getDeliveryDateFrom() {
		return deliveryDateFrom;
	}
	public void setDeliveryDateFrom(Date deliveryDateFrom) {
		this.deliveryDateFrom = deliveryDateFrom;
	}
	public Date getDeliveryDateTo() {
		return deliveryDateTo;
	}
	public void setDeliveryDateTo(Date deliveryDateTo) {
		this.deliveryDateTo = deliveryDateTo;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getInstructionCode() {
		return instructionCode;
	}
	public void setInstructionCode(String instructionCode) {
		this.instructionCode = instructionCode;
	}
	public String getInstructionName() {
		return instructionName;
	}
	public void setInstructionName(String instructionName) {
		this.instructionName = instructionName;
	}
	public String getArrangementNumber() {
		return arrangementNumber;
	}
	public void setArrangementNumber(String arrangementNumber) {
		this.arrangementNumber = arrangementNumber;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getProcessDetails() {
		return processDetails;
	}
	public void setProcessDetails(String processDetails) {
		this.processDetails = processDetails;
	}
	public int getArrangementQuantity() {
		return arrangementQuantity;
	}
	public void setArrangementQuantity(int arrangementQuantity) {
		this.arrangementQuantity = arrangementQuantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getArrangementUnitPrice() {
		return arrangementUnitPrice;
	}
	public void setArrangementUnitPrice(int arrangementUnitPrice) {
		this.arrangementUnitPrice = arrangementUnitPrice;
	}
	public int getRemainingArrangementQuantity() {
		return remainingArrangementQuantity;
	}
	public void setRemainingArrangementQuantity(int remainingArrangementQuantity) {
		this.remainingArrangementQuantity = remainingArrangementQuantity;
	}
	public String getReceivingInProgress() {
		return receivingInProgress;
	}
	public void setReceivingInProgress(String receivingInProgress) {
		this.receivingInProgress = receivingInProgress;
	}
}