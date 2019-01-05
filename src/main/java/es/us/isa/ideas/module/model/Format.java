package es.us.isa.ideas.module.model;

import java.io.Serializable;

public class Format implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String format;
	private String editorModeId;
	private String _editorModeURI;
	private String editorThemeId;
	private String _editorThemeURI;
	private String action;
	private boolean checkLanguage;
	
	public Format() {
		super();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getEditorModeId() {
		return editorModeId;
	}

	public void setEditorModeId(String editorModeId) {
		this.editorModeId = editorModeId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isCheckLanguage() {
		return checkLanguage;
	}

	public void setCheckLanguage(boolean checkLanguage) {
		this.checkLanguage = checkLanguage;
	}
	
	public String get_editorModeURI() {
		return _editorModeURI;
	}

	public void set_editorModeURI(String _editorModeURI) {
		this._editorModeURI = _editorModeURI;
	}

	public String getEditorThemeId() {
		return editorThemeId;
	}

	public void setEditorThemeId(String editorThemeId) {
		this.editorThemeId = editorThemeId;
	}

	public String get_editorThemeURI() {
		return _editorThemeURI;
	}

	public void set_editorThemeURI(String _editorThemeURI) {
		this._editorThemeURI = _editorThemeURI;
	}
	
	
	
	
}
