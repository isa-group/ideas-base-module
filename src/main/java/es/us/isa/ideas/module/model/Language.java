package es.us.isa.ideas.module.model;

import java.io.Serializable;

public class Language implements Serializable {

	private static final long serialVersionUID = 5558188895408490691L;

	private String id;
	private String extension;
	private String name;
	private String defaultFileName;
	private Command[] commands;
	private Format[] formats;
	private Operation[] operations;
        private String helpURI;

	private String inspectorLoader;

	public Language() {
		super();
		defaultFileName = "";
	}

	public String getId() {
		return id;
	}

	public Command[] getCommands() {
		return commands;
	}

	public void setCommands(Command[] commands) {
		this.commands = commands;
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

	public String getDefaultFileName() {
		return defaultFileName;
	}

	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName = defaultFileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public Format[] getFormats() {
		return formats;
	}

	public void setFormats(Format[] formats) {
		this.formats = formats;
	}

	public Operation[] getOperations() {
		return operations;
	}

	public void setOperations(Operation[] operations) {
		this.operations = operations;
	}

	public String getInspectorLoader() {
		return inspectorLoader;
	}

	public void setInspectorLoader(String inspectorLoader) {
		this.inspectorLoader = inspectorLoader;
	}

        public String getHelpURI() {
            String res = "#";
            if(helpURI!=null){
                res=helpURI;
            }
            return res;
        }

        public void setHelpURI(String helpURI) {
            this.helpURI = helpURI;
        }   
}
