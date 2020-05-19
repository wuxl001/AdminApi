package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 任务
* @ClassName: RuleTask 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:40:00
 */
public class RuleTask {
    private String id;

    private String taskName;//任务名称

    private String flowId;//流程id

    private String movement;//动作

    private Integer status;//状态

    private String preTaskId;//前置任务id

    private Date createDate;//创建时间

    private Date updateDate;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement == null ? null : movement.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPreTaskId() {
        return preTaskId;
    }

    public void setPreTaskId(String preTaskId) {
        this.preTaskId = preTaskId == null ? null : preTaskId.trim();
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
}