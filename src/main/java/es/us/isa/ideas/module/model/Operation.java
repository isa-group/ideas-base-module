package es.us.isa.ideas.module.model;

import java.io.Serializable;
import java.util.Map;

public class Operation implements Serializable {

	private static final long serialVersionUID = -295613429613100005L;
	
	private String id;
	private String name;
	private String _remoteExecution;
	private Map<String,String> data;
	private String action;
        private String icon;
        private Boolean iconOnly;
	
	public Operation() {
		super();
	}

	public String getId() {
		return id;
	}
        public String getIcon(){
            return icon;
        }
        public void setIcon(String icon){
            this.icon=icon;
        }
        public Boolean getIconOnly(){
            return iconOnly;
        }
        public void setIconOnly(Boolean iconOnly){
            this.iconOnly=iconOnly;
        }

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String get_remoteExecution() {
		return _remoteExecution;
	}

	public void set_remoteExecution(String _remoteExecution) {
		this._remoteExecution = _remoteExecution;
	}
	
	
	
	
	
}
