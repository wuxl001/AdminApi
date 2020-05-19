package cn.szag.oms.manager.common.domain.manager;

import cn.szag.oms.manager.common.page.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DictionaryManaged implements Serializable{
    private String id;

    private String parentid;

    private String name;

    private String keyword;

    private String sort;

    private String source;

    private String description;

    private Integer status;

    private String code;


    public List<DictionaryManaged> getNodes() {
        return nodes;
    }

    public void setNodes(List<DictionaryManaged> nodes) {
        this.nodes = nodes;
    }

    private List<DictionaryManaged> nodes = new ArrayList<DictionaryManaged>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}