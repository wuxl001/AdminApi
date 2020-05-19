package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 流程配置
* @ClassName: RuleFlowConfiguration 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:31:31
 */
public class RuleFlowConfiguration {
    private String id;

    private String flowName;//流程名称

    private String atTemplateId;//当前模板id

    private String calssName;//类名

    private String subscriptionName;//订阅名

    private Date createDate;//创建人

    private String creator;//创建时间

    private Integer delFlag;

    private String templateId;//模板id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? null : flowName.trim();
    }

    public String getAtTemplateId() {
        return atTemplateId;
    }

    public void setAtTemplateId(String atTemplateId) {
        this.atTemplateId = atTemplateId == null ? null : atTemplateId.trim();
    }

    public String getCalssName() {
        return calssName;
    }

    public void setCalssName(String calssName) {
        this.calssName = calssName == null ? null : calssName.trim();
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName == null ? null : subscriptionName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }
}