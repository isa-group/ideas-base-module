package es.us.isa.ideas.module.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.us.isa.ideas.module.utils.Utils;

@Controller
@RequestMapping("/tests")
public abstract class BaseTestModuleController {

	protected String jsonFilePath = "/tests/tests.json";
	protected String resourcePath = "/tests/resources/";

	protected abstract void setResourcePath();

	protected abstract void setJsonFilePath();

	@RequestMapping(value = "/tests.json", method = RequestMethod.GET)
	@ResponseBody
	public String getJsonResource(HttpServletResponse response) {
		setJsonFilePath();
		setResourcePath();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		return Utils.loadFileContents(jsonFilePath);
	}
	
	@RequestMapping(value = "/resource/{fileext}/{filename}", method = RequestMethod.GET)
	@ResponseBody
	public String getResource(
			@PathVariable("fileext") String fileext,
			@PathVariable("filename") String filename,
			HttpServletResponse response) {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		return Utils.loadFileContents(resourcePath + filename + "." + fileext);
	}
}
