package cn.szag.oms.manager.domain;

import java.util.Date;

public class PictureAttachment {
    private String id;
    /**
     * 集装箱Id
     */
    private String containerId;
    /**
     * 图片类型
     */
    private String pictureType;
    /**
     * 进程
     */
    private String transProcess;
    /**
     * 路径
     */
    private String path;
    /**
     * 柜号
     */
    private String boxNo;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 备注
     */
    private String remark;

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

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType == null ? null : pictureType.trim();
    }

    public String getTransProcess() {
        return transProcess;
    }

    public void setTransProcess(String transProcess) {
        this.transProcess = transProcess == null ? null : transProcess.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}