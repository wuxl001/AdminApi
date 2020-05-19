package cn.szag.oms.manager.common.domain.manager;

import cn.szag.oms.manager.common.domain.Module;

import java.io.Serializable;
import java.util.List;

public class Role  implements Serializable {
    private String id;

    private Integer status;

    private String name;

    private String description;

    private String source;

    private List<String> modules;//一个检查组合包含多个检查项

    
	public List<String> getModules() {
		return modules;
	}

	public void setModules(List<String> modules) {
		this.modules = modules;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}