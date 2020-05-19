package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

public class Advertisement {
    private String id;

    private String title;

    private String no;

    private String picpath;

    private Integer sort;

    private Integer isredirect;

    private String source;

    private Integer examinestatus;

    private Integer publicstatus;

    private String creator;

    private Date createtime;

    private String creatorid;

    private String updator;

    private String updatorid;

    private String publicpersonid;

    private Date publictime;

    private String examinepersonid;

    private Date examinetime;

    private Integer delFlag;

    private String examineperson;

    private String redirecturl;

    private Date updatetime;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath == null ? null : picpath.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsredirect() {
        return isredirect;
    }

    public void setIsredirect(Integer isredirect) {
        this.isredirect = isredirect;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Integer getExaminestatus() {
        return examinestatus;
    }

    public void setExaminestatus(Integer examinestatus) {
        this.examinestatus = examinestatus;
    }

    public Integer getPublicstatus() {
        return publicstatus;
    }

    public void setPublicstatus(Integer publicstatus) {
        this.publicstatus = publicstatus;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public String getUpdatorid() {
        return updatorid;
    }

    public void setUpdatorid(String updatorid) {
        this.updatorid = updatorid == null ? null : updatorid.trim();
    }

    public String getPublicpersonid() {
        return publicpersonid;
    }

    public void setPublicpersonid(String publicpersonid) {
        this.publicpersonid = publicpersonid == null ? null : publicpersonid.trim();
    }

    public Date getPublictime() {
        return publictime;
    }

    public void setPublictime(Date publictime) {
        this.publictime = publictime;
    }

    public String getExaminepersonid() {
        return examinepersonid;
    }

    public void setExaminepersonid(String examinepersonid) {
        this.examinepersonid = examinepersonid == null ? null : examinepersonid.trim();
    }

    public Date getExaminetime() {
        return examinetime;
    }

    public void setExaminetime(Date examinetime) {
        this.examinetime = examinetime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getExamineperson() {
        return examineperson;
    }

    public void setExamineperson(String examineperson) {
        this.examineperson = examineperson == null ? null : examineperson.trim();
    }

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl == null ? null : redirecturl.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}