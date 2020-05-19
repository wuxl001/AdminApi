package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
/**
 * 知识库
* @ClassName: RuleRrepository 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:35:52
 */
public class RuleRepository {
    private String id;

    private String use;//用途

    private String repositoryName;//知识名称

    private String className;//类名

    private String dealExplain;//协议说明

    private Date createDate;//创建时间

    private String creator;//创建人

    private Date updateDate;//修改时间

    private String updator;//修改人

    private Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use == null ? null : use.trim();
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName == null ? null : repositoryName.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getDealExplain() {
        return dealExplain;
    }

    public void setDealExplain(String dealExplain) {
        this.dealExplain = dealExplain == null ? null : dealExplain.trim();
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}