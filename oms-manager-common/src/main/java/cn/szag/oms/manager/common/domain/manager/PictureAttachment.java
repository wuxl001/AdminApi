package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;

public class PictureAttachment implements Serializable {
    private String id;
    /**
     * 集装箱Id
     */
    private String containerId;
    /**
     * 路径
     */
    private String path;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 创建人
     */
    private String creator;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
}