package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 调度
* @ClassName: DispatchOrder 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:23:25
 */
public class DispatchOrder {
    private String id;
    /**
     * 集装箱Id
     */
    private String containerId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 类型
     */
    private String type;
    /**
     * 调度单号
     */
    private String no;
    /**
     * 开始地点
     */
    private String startPlace;
    /**
     * 结束地点
     */
    private String endPlace;
    /**
     * 车牌号
     */
    private String plateNo;
    /**
     * 司机
     */
    private String driverName;
    /**
     * 电话
     */
    private String driverTel;
    /**
     * 调度员
     */
    private String dispatcher;
    /**
     * 运输单Id
     */
    private String transferID;
    /**
     * 抵达时间
     */
    private Date arrivalTime;
    /**
     * 发车时间
     */
    private Date departureTime;
    /**
     * 报空时间
     */
    private Date emptyTime;
    /**
     * 还箱时间
     */
    private Date returnboxTime;
    /**
     * 提柜时间
     */
    private Date mentionArkTime;
    
    public Date getMentionArkTime() {
		return mentionArkTime;
	}

	public void setMentionArkTime(Date mentionArkTime) {
		this.mentionArkTime = mentionArkTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getEmptyTime() {
		return emptyTime;
	}

	public void setEmptyTime(Date emptyTime) {
		this.emptyTime = emptyTime;
	}

	public Date getReturnboxTime() {
		return returnboxTime;
	}

	public void setReturnboxTime(Date returnboxTime) {
		this.returnboxTime = returnboxTime;
	}

	public String getTransferID() {
		return transferID;
	}

	public void setTransferID(String transferID) {
		this.transferID = transferID;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId == null ? null : containerId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace == null ? null : startPlace.trim();
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace == null ? null : endPlace.trim();
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo == null ? null : plateNo.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel == null ? null : driverTel.trim();
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher == null ? null : dispatcher.trim();
    }

}