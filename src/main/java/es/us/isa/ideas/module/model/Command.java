package es.us.isa.ideas.module.model;

import java.io.Serializable;

public class Command implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String _remoteExecution;
	private Boolean isDefault;
	private String action;
	private Parameters[] params;
	
	
	public Command() {
		super();
		
	}
	public Parameters[] getParams() {
		return params;
	}
	public void setParams(Parameters[] params) {
		this.params = params;
	}
	public String getId() {
		return id;
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
	public String get_remoteExecution() {
		return _remoteExecution;
	}
	public void set_remoteExecution(String _remoteExecution) {
		this._remoteExecution = _remoteExecution;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
