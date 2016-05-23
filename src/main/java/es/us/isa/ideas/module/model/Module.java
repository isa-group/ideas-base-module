package es.us.isa.ideas.module.model;

import java.io.Serializable;

public class Module implements Serializable {

    private static final long serialVersionUID = 5558188895408490691L;

    private String id;
    private Double version;
    private Model[] models;
    private String helpURI;

    private String inspectorLoader;

    public Module() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Model[] getModels() {
        return models;
    }

    public void setModels(Model[] models) {
        this.models = models;
    }

    public String getInspectorLoader() {
        return inspectorLoader;
    }

    public void setInspectorLoader(String inspectorLoader) {
        this.inspectorLoader = inspectorLoader;
    }

    public String getHelpURI() {
        String res = "#";
        if (helpURI != null) {
            res = helpURI;
        }
        return res;
    }

    public void setHelpURI(String helpURI) {
        this.helpURI = helpURI;
    }
}
