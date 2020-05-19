package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;
/**
 * 评价
* @ClassName: OrderEvaluationScore 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:35:41
 */
public class OrderEvaluationScore implements Serializable {
    private String id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人Id
     */
    private String creatorId;
    /**
     * 评价类型（业务/客服/运输/司机/账单/信息准确）
     */
    private String type;
    /**
     * 柜号
     */
    private String boxNo;
    /**
     * 集装箱Id
     */
    private String containerId;
    
    public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }
}