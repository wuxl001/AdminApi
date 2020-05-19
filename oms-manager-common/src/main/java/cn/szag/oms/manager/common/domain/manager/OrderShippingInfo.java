package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderShippingInfo {
    private String id;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 境内收货人
     */
    @JsonIgnore
    private String domesticConsignee;
    /**
     * 境内收货人中文
     */
    private String domesticConsigneeName;
    /**
     * 境内收货人英文
     */
    private String domesticConsigneeEname;
    /**
     * 境外发货人
     */
    private String overseasShipper;
    /**
     * 境外发货人中文
     */
    private String overseasShipperName;
    /**
     * 境外发货人英文
     */
    private String overseasShipperEname;
    /**
     * 货币单位
     */
    @JsonIgnore
    private String currency;
    /**
     * 目的国
     */
    @JsonIgnore
    private String destCountry;
    
    /**
     * 目的国中文
     */
    private String destCountryName;
    /**
     * 目的国英文
     */
    private String destCountryEname;
    /**
     * 启运港
     */
    @JsonIgnore
    private String departurePort;
    /**
     * 启运港中文
     */
    private String departurePortName;
    /**
     * 启运港英文
     */
    private String departurePortEname;
    /**
     * 目的港
     */
    @JsonIgnore
    private String destPort;
    /**
     * 目的港中文
     */
    private String destPortName;
    /**
     * 目的港英文
     */
    private String destPortEname;
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
    @JsonIgnore
    private String shipowningCompany;
    /**
     * 船公司中文
     */
    private String shipowningCompanyName;
    /**
     * 船公司英文
     */
    private String shipowningCompanyEname;
    /**
     * 预计到港时间
     */
    private Date expectArriveTime;
    /**
     * 到港时间
     */
    private Date arriveTime;
    /**
     * 删除标志位0=yes,1=no
     */
    @JsonIgnore
    private Integer delFlag;
    /**
     * 更新人id
     */
    @JsonIgnore
    private String updaterId;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 原产国
     */
    @JsonIgnore
    private String originCountryId;
    /**
     * 原产国中文
     */
    private String originCountryName;
    /**
     * 原产国英文
     */
    private String originCountryEname;
    /**
     * 目的国
     */
    @JsonIgnore
    private String destCountryId;
    /**
     * 消费使用单位
     */
    @JsonIgnore
    private String consumerOrgId;
    /**
     * 消费使用单位中文
     */
    private String consumerOrgName;
    /**
     * 消费使用单位英文
     */
    private String consumerOrgEname;
    /**
     * 进境口岸
     */
    private String entryPortId;
    /**
     * 进境口岸中文
     */
    private String entryPortName;
    /**
     * 进境口岸英文
     */
    private String entryPortEname;
    /**
     * 商品名称
     */
    private String product;
    /**
     * 件数
     */
    private String quantity;
    /**
     * 柜号
     */
    private String boxNo;
    /**
     * 码头
     */
    private Integer dock;
    
    public Integer getDock() {
		return dock;
	}

	public void setDock(Integer dock) {
		this.dock = dock;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getOriginCountryName() {
		return originCountryName;
	}

	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}

	public String getOriginCountryEname() {
		return originCountryEname;
	}

	public void setOriginCountryEname(String originCountryEname) {
		this.originCountryEname = originCountryEname;
	}

	public String getEntryPortName() {
		return entryPortName;
	}

	public void setEntryPortName(String entryPortName) {
		this.entryPortName = entryPortName;
	}

	public String getEntryPortEname() {
		return entryPortEname;
	}

	public void setEntryPortEname(String entryPortEname) {
		this.entryPortEname = entryPortEname;
	}

	public String getOverseasShipperName() {
		return overseasShipperName;
	}

	public void setOverseasShipperName(String overseasShipperName) {
		this.overseasShipperName = overseasShipperName;
	}


	public String getOverseasShipperEname() {
		return overseasShipperEname;
	}

	public void setOverseasShipperEname(String overseasShipperEname) {
		this.overseasShipperEname = overseasShipperEname;
	}

	public String getConsumerOrgName() {
		return consumerOrgName;
	}

	public void setConsumerOrgName(String consumerOrgName) {
		this.consumerOrgName = consumerOrgName;
	}

	public String getConsumerOrgEname() {
		return consumerOrgEname;
	}

	public void setConsumerOrgEname(String consumerOrgEname) {
		this.consumerOrgEname = consumerOrgEname;
	}

	public String getShipowningCompanyName() {
		return shipowningCompanyName;
	}

	public void setShipowningCompanyName(String shipowningCompanyName) {
		this.shipowningCompanyName = shipowningCompanyName;
	}

	public String getShipowningCompanyEname() {
		return shipowningCompanyEname;
	}

	public void setShipowningCompanyEname(String shipowningCompanyEname) {
		this.shipowningCompanyEname = shipowningCompanyEname;
	}

	public String getDomesticConsigneeName() {
		return domesticConsigneeName;
	}

	public void setDomesticConsigneeName(String domesticConsigneeName) {
		this.domesticConsigneeName = domesticConsigneeName;
	}

	public String getDomesticConsigneeEname() {
		return domesticConsigneeEname;
	}

	public void setDomesticConsigneeEname(String domesticConsigneeEname) {
		this.domesticConsigneeEname = domesticConsigneeEname;
	}

	public String getDestCountryEname() {
		return destCountryEname;
	}

	public void setDestCountryEname(String destCountryEname) {
		this.destCountryEname = destCountryEname;
	}

	public String getDeparturePortEname() {
		return departurePortEname;
	}

	public void setDeparturePortEname(String departurePortEname) {
		this.departurePortEname = departurePortEname;
	}

	public String getDestCountryName() {
		return destCountryName;
	}

	public void setDestCountryName(String destCountryName) {
		this.destCountryName = destCountryName;
	}

	public String getDeparturePortName() {
		return departurePortName;
	}

	public void setDeparturePortName(String departurePortName) {
		this.departurePortName = departurePortName;
	}

	public String getDestPortName() {
		return destPortName;
	}

	public void setDestPortName(String destPortName) {
		this.destPortName = destPortName;
	}


	public String getDestPortEname() {
		return destPortEname;
	}

	public void setDestPortEname(String destPortEname) {
		this.destPortEname = destPortEname;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? null : updaterId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(String originCountryId) {
        this.originCountryId = originCountryId == null ? null : originCountryId.trim();
    }

    public String getDestCountryId() {
        return destCountryId;
    }

    public void setDestCountryId(String destCountryId) {
        this.destCountryId = destCountryId == null ? null : destCountryId.trim();
    }

    public String getConsumerOrgId() {
        return consumerOrgId;
    }

    public void setConsumerOrgId(String consumerOrgId) {
        this.consumerOrgId = consumerOrgId == null ? null : consumerOrgId.trim();
    }

    public String getEntryPortId() {
        return entryPortId;
    }

    public void setEntryPortId(String entryPortId) {
        this.entryPortId = entryPortId == null ? null : entryPortId.trim();
    }
}