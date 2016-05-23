package es.us.isa.ideas.module.model;

import java.io.Serializable;

public class Filedata implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fileUri;
    private String content;

    public Filedata() {
        super();

    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return fileUri + ": " + content;
    }
    
    

}
