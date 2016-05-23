package es.us.isa.ideas.module.controller;

import com.google.gson.Gson;
import es.us.isa.ideas.module.common.AppResponse;
import es.us.isa.ideas.module.model.Filedata;
import es.us.isa.ideas.module.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import es.us.isa.ideas.module.model.Module;
import es.us.isa.ideas.module.model.Operation;
import es.us.isa.ideas.module.model.Syntax;
import es.us.isa.ideas.module.utils.Utils;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("")
public abstract class BaseModuleController {

    private static final Logger LOG = Logger.getLogger(BaseModuleController.class.getName());

    protected Module module;
    protected String MANIFEST = "module_manifest.json";

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class ResourceNotFoundException extends RuntimeException {
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Module initModule() {
        loadModule();
        return module;
    }

    protected void loadModule() {
        if (module == null) {
            LOG.info("[IDEAS] Module is not loaded. Loading...");
            setModule();
        }
    }

    protected void setModule() {
        String json = Utils.loadFileContents(MANIFEST);
        Gson gson = new Gson();
        module = gson.fromJson(json, Module.class);
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        loadModule();
        return "forward:" + module.getHelpURI();
    }

    @RequestMapping(value = "/models", method = RequestMethod.GET)
    @ResponseBody
    public Model[] getModels() {
        loadModule();
        return module.getModels();
    }

    @RequestMapping(value = "/models/{model}", method = RequestMethod.GET)
    @ResponseBody
    public Model getModel(@PathVariable("model") String model) {
        loadModule();
        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                return modelManifest;
            }
        }
        throw new ResourceNotFoundException();
    }

    @RequestMapping(value = "/models/{model}/syntaxes", method = RequestMethod.GET)
    @ResponseBody
    public Syntax[] getModelSyntaxes(@PathVariable("model") String model) {
        loadModule();
        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                return modelManifest.getSyntaxes();
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/syntaxes/{syntax}", method = RequestMethod.GET)
    @ResponseBody
    public Syntax getModelSyntax(@PathVariable("model") String model, @PathVariable("syntax") String syntax) {
        loadModule();
        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                for (Syntax s : modelManifest.getSyntaxes()) {
                    if (s.getId().equals(syntax)) {
                        return s;
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/syntaxes/{syntax}/mode", method = RequestMethod.GET)
    @ResponseBody
    public String getLanguageMode(@PathVariable("model") String model, @PathVariable("syntax") String syntax,
            HttpServletResponse response) {
        loadModule();

        response.setContentType("text/javascript");
        response.setCharacterEncoding("UTF-8");

        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                String fileUri = "";
                for (Syntax s : modelManifest.getSyntaxes()) {
                    if (s.getId().equals(syntax)) {
                        fileUri = s.get_editorModeURI();
                    }
                }
                try {
                    return Utils.loadFileContents("modes/" + fileUri);
                } catch (Exception e) {
                    return "no mode " + fileUri + " defined for format " + syntax;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/syntaxes/{syntax}/theme", method = RequestMethod.GET)
    @ResponseBody
    public String getLanguageTheme(@PathVariable("model") String model, @PathVariable("syntax") String syntax,
            HttpServletResponse response) {
        loadModule();

        response.setContentType("text/javascript");
        response.setCharacterEncoding("UTF-8");

        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                String fileUri = "";
                for (Syntax s : modelManifest.getSyntaxes()) {
                    if (s.getId().equals(syntax)) {
                        fileUri = s.get_editorThemeURI();
                    }
                }
                try {
                    return Utils.loadFileContents("modes/" + fileUri);
                } catch (Exception e) {
                    return "no mode " + fileUri + " defined for format " + syntax;
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/operations", method = RequestMethod.GET)
    @ResponseBody
    public Operation[] getModelOperations(@PathVariable("model") String model) {
        loadModule();
        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                return modelManifest.getOperations();
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/operations/{operation}", method = RequestMethod.GET)
    @ResponseBody
    public Operation getModelOperation(@PathVariable("model") String model, @PathVariable("operation") String operation) {
        loadModule();
        for (Model modelManifest : module.getModels()) {
            if (modelManifest.getId().equals(model)) {
                for (Operation op : modelManifest.getOperations()) {
                    if (op.getId().equals(operation)) {
                        return op;
                    }
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/models/{model}/operations/{operation}", method = RequestMethod.POST)
    @ResponseBody
    public abstract AppResponse executeOperation(
            @PathVariable("model") String model,
            @PathVariable("operation") String operation,
            @RequestBody List<Filedata> data);

    @RequestMapping(value = "/models/{model}/syntaxes/{syntax}/check", method = RequestMethod.POST)
    @ResponseBody
    public abstract AppResponse checkLanguage(
            @PathVariable("model") String model,
            @PathVariable("syntax") String syntax,
            @RequestBody Filedata data);

    @RequestMapping(value = "/models/{model}/syntaxes/{syntax}/convert", method = RequestMethod.POST)
    @ResponseBody
    public abstract AppResponse convertFormat(
            @PathVariable("model") String model,
            @PathVariable("syntax") String syntax,
            @RequestParam("destinationSyntax") String destinationSyntax,
            @RequestBody Filedata data);

    @RequestMapping(value = "/models/{model}/tests", method = RequestMethod.GET)
    @ResponseBody
    public abstract String getTests(@PathVariable("model") String model);

    @RequestMapping(value = "/models/{model}/tests/resources/{resourceId:.+}", method = RequestMethod.GET)
    @ResponseBody
    public abstract String getTestResource(
            @PathVariable("model") String model,
            @PathVariable("resourceId") String resourceId);

}
