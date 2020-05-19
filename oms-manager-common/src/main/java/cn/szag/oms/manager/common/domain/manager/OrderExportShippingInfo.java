package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderExportShippingInfo {
    private String id;

    private String orderId;
    @JsonIgnore
    private String loadingport;//启运港
    @JsonIgnore
    private String deliveryPort;//目的港
    @JsonIgnore
    private String stopPort;//经停港
    @JsonIgnore
    private String loadingCountry;//原产国
    @JsonIgnore
    private String deliveryCountry;//运抵国

    private String ship;//船名

    private String blNo;//总提单号
    @JsonIgnore
    private String voyageNo;//航次
    @JsonIgnore
    private String shippingCompany;//船公司
    @JsonIgnore
    private String consignor;//发货人
    @JsonIgnore
    private String consignee;//收货人
    @JsonIgnore
    private String notifier;//通知人

    private String dock;//码头
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eta;//预计到港时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date etd;//预计离港时间
    @JsonIgnore
    private Date createdate;//创建时间
    @JsonIgnore
    private Date lastupdate;//最后更新时间
    @JsonIgnore
    private String creator;//创建人
    @JsonIgnore
    private String editor;//修改人
    @JsonIgnore
    private Integer delFlag;//删除标志位（0=正常/1=删除）

    private String transferShipInfo;//中转船名航次
    @JsonIgnore
    private String productionid;//中转船名航次

    private Date cydate;//截关日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date atd;//实际离港实际
    
    
    
    /**
     * 扩展字段
     */
    /**
     * 启运港
     */
    private String departurePortName;
    /**
     * 启运港英文
     */
    private String departurePortEName;
    /**
     * 目的港
     */
    private String destPortName;
    /**
     * 目的港英文
     */
    private String destPortEName;
    /**
     * 启运国
     */
    private String loadingCountryName;
    /**
     * 启运国英文
     */
    private String loadingCountryEName;
    /**
     * 目的国
     */
    private String deliveryCountryName;
    /**
     * 目的国英文
     */
    private String deliveryCountryEname;
    /**
     * 船公司
     */
    private String shipowningCompanyName;
    /**
     * 船公司英文
     */
    private String shipowningCompanyEName;
    /**
     * 船名航次
     */
    private String shipInfo;
    /**
     * 集装箱Id
     */
    private String containerId;
    /**
     * 件数
     */
    private String quantity;
    /**
     * 商品名称
     */
    private String product;
    /**
     * 集装箱号
     */
    private String boxNo;
    /**
     * 订舱号
     */
    private String bookingNo;
    private Date expectArriveTime;
    
    public Date getExpectArriveTime() {
		return expectArriveTime;
	}

	public void setExpectArriveTime(Date expectArriveTime) {
		this.expectArriveTime = expectArriveTime;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(String bookingNo) {
		this.bookingNo = bookingNo;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getDeparturePortName() {
		return departurePortName;
	}

	public void setDeparturePortName(String departurePortName) {
		this.departurePortName = departurePortName;
	}

	public String getDeparturePortEName() {
		return departurePortEName;
	}

	public void setDeparturePortEName(String departurePortEName) {
		this.departurePortEName = departurePortEName;
	}

	public String getDestPortName() {
		return destPortName;
	}

	public void setDestPortName(String destPortName) {
		this.destPortName = destPortName;
	}

	public String getDestPortEName() {
		return destPortEName;
	}

	public void setDestPortEName(String destPortEName) {
		this.destPortEName = destPortEName;
	}

	public String getLoadingCountryName() {
		return loadingCountryName;
	}

	public void setLoadingCountryName(String loadingCountryName) {
		this.loadingCountryName = loadingCountryName;
	}

	public String getLoadingCountryEName() {
		return loadingCountryEName;
	}

	public void setLoadingCountryEName(String loadingCountryEName) {
		this.loadingCountryEName = loadingCountryEName;
	}

	public String getDeliveryCountryName() {
		return deliveryCountryName;
	}

	public void setDeliveryCountryName(String deliveryCountryName) {
		this.deliveryCountryName = deliveryCountryName;
	}

	public String getDeliveryCountryEname() {
		return deliveryCountryEname;
	}

	public void setDeliveryCountryEname(String deliveryCountryEname) {
		this.deliveryCountryEname = deliveryCountryEname;
	}

	public String getShipowningCompanyName() {
		return shipowningCompanyName;
	}

	public void setShipowningCompanyName(String shipowningCompanyName) {
		this.shipowningCompanyName = shipowningCompanyName;
	}

	public String getShipowningCompanyEName() {
		return shipowningCompanyEName;
	}

	public void setShipowningCompanyEName(String shipowningCompanyEName) {
		this.shipowningCompanyEName = shipowningCompanyEName;
	}

	public String getShipInfo() {
		return shipInfo;
	}

	public void setShipInfo(String shipInfo) {
		this.shipInfo = shipInfo;
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

    public String getLoadingport() {
        return loadingport;
    }

    public void setLoadingport(String loadingport) {
        this.loadingport = loadingport == null ? null : loadingport.trim();
    }

    public String getDeliveryPort() {
        return deliveryPort;
    }

    public void setDeliveryPort(String deliveryPort) {
        this.deliveryPort = deliveryPort == null ? null : deliveryPort.trim();
    }

    public String getStopPort() {
        return stopPort;
    }

    public void setStopPort(String stopPort) {
        this.stopPort = stopPort == null ? null : stopPort.trim();
    }

    public String getLoadingCountry() {
        return loadingCountry;
    }

    public void setLoadingCountry(String loadingCountry) {
        this.loadingCountry = loadingCountry == null ? null : loadingCountry.trim();
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry == null ? null : deliveryCountry.trim();
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship == null ? null : ship.trim();
    }

    public String getBlNo() {
        return blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo == null ? null : blNo.trim();
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo == null ? null : voyageNo.trim();
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany == null ? null : shippingCompany.trim();
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor == null ? null : consignor.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getNotifier() {
        return notifier;
    }

    public void setNotifier(String notifier) {
        this.notifier = notifier == null ? null : notifier.trim();
    }

    public String getDock() {
        return dock;
    }

    public void setDock(String dock) {
        this.dock = dock == null ? null : dock.trim();
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Date getEtd() {
        return etd;
    }

    public void setEtd(Date etd) {
        this.etd = etd;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getTransferShipInfo() {
        return transferShipInfo;
    }

    public void setTransferShipInfo(String transferShipInfo) {
        this.transferShipInfo = transferShipInfo == null ? null : transferShipInfo.trim();
    }

    public String getProductionid() {
        return productionid;
    }

    public void setProductionid(String productionid) {
        this.productionid = productionid == null ? null : productionid.trim();
    }

    public Date getCydate() {
        return cydate;
    }

    public void setCydate(Date cydate) {
        this.cydate = cydate;
    }

    public Date getAtd() {
        return atd;
    }

    public void setAtd(Date atd) {
        this.atd = atd;
    }
}