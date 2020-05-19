package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

public class CodeNationality {
    private Integer id;

    private Long version;

    private String code;

    private Date createdate;

    private String creator;

    private Integer delFlag;

    private String editor;

    private Date lastupdate;

    private String nameCn;

    private String nameEn;

    private String sort;

    private String sourceCusCode;

    private String sourceIusCode;

   
    
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getSourceCusCode() {
        return sourceCusCode;
    }

    public void setSourceCusCode(String sourceCusCode) {
        this.sourceCusCode = sourceCusCode == null ? null : sourceCusCode.trim();
    }

    public String getSourceIusCode() {
        return sourceIusCode;
    }

    public void setSourceIusCode(String sourceIusCode) {
        this.sourceIusCode = sourceIusCode == null ? null : sourceIusCode.trim();
    }
}