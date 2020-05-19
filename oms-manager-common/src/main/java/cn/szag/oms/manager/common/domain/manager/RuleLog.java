package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 日志
* @ClassName: RuleLog 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:33:15
 */
public class RuleLog {
    private String id;

    private String flowId;//流程id

    private String taskId;//任务id

    private String remark;//备注

    private Date create;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}