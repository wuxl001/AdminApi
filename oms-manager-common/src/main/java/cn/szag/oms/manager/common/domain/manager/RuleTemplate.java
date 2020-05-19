package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 模板
* @ClassName: RuleTemplate 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:43:51
 */
public class RuleTemplate {
    private String id;

    private String templateName;//模板名称

    private String xml;

    private Date creatorTime;//创建时间

    private String creator;//创建人

    private Integer delFlag;

    private Integer isEnabled;//是否当前启用(0=no/1=yes)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml == null ? null : xml.trim();
    }

    public Date getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Date creatorTime) {
        this.creatorTime = creatorTime;
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

    public Integer getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
}