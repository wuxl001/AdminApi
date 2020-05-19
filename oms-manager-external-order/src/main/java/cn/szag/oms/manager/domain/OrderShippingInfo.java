package cn.szag.oms.manager.domain;

import java.util.Date;

public class OrderShippingInfo {
    private String id;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 柜号
     */
    private String boxNo;
    /**
     * 境内收货人
     */
    private String domesticConsignee;
    /**
     * 境外发货人
     */
    private String overseasShipper;
    /**
     * 货币单位
     */
    private String currency;
    /**
     * 目的国
     */
    private String destCountry;
    /**
     * 启运港
     */
    private String departurePort;
    /**
     * 目的港
     */
    private String destPort;
    /**
     * 提单号
     */
    private String extractOrderNo;
    /**
     * 船名航次
     */
    private String shipInfo;
    /**
     * 中转船名航次
     */
    private String transferShipInfo;
    /**
     * 船公司
     */
    private String shipowningCompany;
    /**
     * 预计到港时间
     */
    private Date expectArriveTime;
    /**
     * 到港时间
     */
    private Date arriveTime;
    /**
     * 卸船时间
     */
    private Date shipUnloadTime;
    
    
    private String product;//商品名称
    
    private int quantity;//件数
    
    
    public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getShipUnloadTime() {
		return shipUnloadTime;
	}

	public void setShipUnloadTime(Date shipUnloadTime) {
		this.shipUnloadTime = shipUnloadTime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public String getDomesticConsignee() {
        return domesticConsignee;
    }

    public void setDomesticConsignee(String domesticConsignee) {
        this.domesticConsignee = domesticConsignee == null ? null : domesticConsignee.trim();
    }

    public String getOverseasShipper() {
        return overseasShipper;
    }

    public void setOverseasShipper(String overseasShipper) {
        this.overseasShipper = overseasShipper == null ? null : overseasShipper.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry == null ? null : destCountry.trim();
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort == null ? null : departurePort.trim();
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort == null ? null : destPort.trim();
    }

    public String getExtractOrderNo() {
        return extractOrderNo;
    }

    public void setExtractOrderNo(String extractOrderNo) {
        this.extractOrderNo = extractOrderNo == null ? null : extractOrderNo.trim();
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo == null ? null : shipInfo.trim();
    }

    public String getTransferShipInfo() {
        return transferShipInfo;
    }

    public void setTransferShipInfo(String transferShipInfo) {
        this.transferShipInfo = transferShipInfo == null ? null : transferShipInfo.trim();
    }

    public String getShipowningCompany() {
        return shipowningCompany;
    }

    public void setShipowningCompany(String shipowningCompany) {
        this.shipowningCompany = shipowningCompany == null ? null : shipowningCompany.trim();
    }

    public Date getExpectArriveTime() {
        return expectArriveTime;
    }

    public void setExpectArriveTime(Date expectArriveTime) {
        this.expectArriveTime = expectArriveTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
}