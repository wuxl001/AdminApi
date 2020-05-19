package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 流程
* @ClassName: RuleFlow 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:25:20
 */
public class RuleFlow {
    private String id;

    private String flowConfigurationId;//流程配置Id

    private String flowName;//流程名称

    private String status;//状态（运行中/已结束/已注销）

    private String currentTaskNode;//当前任务节点

    private Date createDate;//创建时间

    private Date updateDate;//修改时间

    private String orderId;//订单id

    private String title;//标题

    private String flowCode;//流程编号

    private Integer delFlag;//删除标志位

    private String orderNo;//订单编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFlowConfigurationId() {
        return flowConfigurationId;
    }

    public void setFlowConfigurationId(String flowConfigurationId) {
        this.flowConfigurationId = flowConfigurationId == null ? null : flowConfigurationId.trim();
    }


    public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCurrentTaskNode() {
        return currentTaskNode;
    }

    public void setCurrentTaskNode(String currentTaskNode) {
        this.currentTaskNode = currentTaskNode == null ? null : currentTaskNode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }
}