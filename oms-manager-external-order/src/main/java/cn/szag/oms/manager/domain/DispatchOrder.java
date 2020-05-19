package cn.szag.oms.manager.domain;
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
     * 运输单Id
     */
    private String orderId;
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
     * 调度员电话
     */
    private String dispatcherTel;
    /**
     * 调度员邮箱
     */
    private String dispatcherEmail;
    /**
     * 调度员账号
     */
    private String dispatcherAccount;
    /**
     * 柜号
     */
    private String boxNo;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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

    public String getDispatcherTel() {
        return dispatcherTel;
    }

    public void setDispatcherTel(String dispatcherTel) {
        this.dispatcherTel = dispatcherTel == null ? null : dispatcherTel.trim();
    }

    public String getDispatcherEmail() {
        return dispatcherEmail;
    }

    public void setDispatcherEmail(String dispatcherEmail) {
        this.dispatcherEmail = dispatcherEmail == null ? null : dispatcherEmail.trim();
    }

    public String getDispatcherAccount() {
        return dispatcherAccount;
    }

    public void setDispatcherAccount(String dispatcherAccount) {
        this.dispatcherAccount = dispatcherAccount == null ? null : dispatcherAccount.trim();
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }
}