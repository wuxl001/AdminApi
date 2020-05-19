package cn.szag.oms.manager.common.domain.manager;
/**
 * 模板组件
* @ClassName: RuleTemplateModule 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午10:44:34
 */
public class RuleTemplateModule {
    private String id;

    private String templateId;//模板id

    private String nodeName;//节点名称

    private String movement;//动作

    private String type;//类型

    private String fatherNodeId;//父节点

    private String value;//值

    private String nodeId;//节点id

    private String theNextNode;//下一节点

    private String merger;//归并

    private String sort;//排序

    private String timer;//定时器

    private String auto;//自动

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement == null ? null : movement.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFatherNodeId() {
        return fatherNodeId;
    }

    public void setFatherNodeId(String fatherNodeId) {
        this.fatherNodeId = fatherNodeId == null ? null : fatherNodeId.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getTheNextNode() {
        return theNextNode;
    }

    public void setTheNextNode(String theNextNode) {
        this.theNextNode = theNextNode == null ? null : theNextNode.trim();
    }

    public String getMerger() {
        return merger;
    }

    public void setMerger(String merger) {
        this.merger = merger == null ? null : merger.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer == null ? null : timer.trim();
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto == null ? null : auto.trim();
    }
}