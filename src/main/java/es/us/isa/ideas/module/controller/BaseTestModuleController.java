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

	protected String jsonFilePath, resourcePath;

	protected abstract void setResourcePath();

	protected abstract void setJsonFilePath();

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String getJsonResource(HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		return getResourceContent(jsonFilePath);
	}
	
	@RequestMapping(value = "/resource/{fileext}/{filename}", method = RequestMethod.GET)
	@ResponseBody
	public String getResource(
			@PathVariable("fileext") String fileext,
			@PathVariable("filename") String filename,
			HttpServletResponse response) {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		return getResourceContent(filename + "." + fileext);
	}
	
	

//	@RequestMapping(value = "/resource/agreement/{document}", method = RequestMethod.GET)
//	@ResponseBody
//	public String getAgreementResource(
//			@PathVariable("document") String document,
//			HttpServletResponse response) {
//		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
//		return getResourceContent(document + ".ag");
//	}
//
//	@RequestMapping(value = "/resource/template/{document}", method = RequestMethod.GET)
//	@ResponseBody
//	public String getTemplateResource(
//			@PathVariable("document") String document,
//			HttpServletResponse response) {
//		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
//		return getResourceContent(document + ".at");
//	}
//
//	@RequestMapping(value = "/resource/offer/{document}", method = RequestMethod.GET)
//	@ResponseBody
//	public String getOfferResource(@PathVariable("document") String document,
//			HttpServletResponse response) {
//		response.setContentType("text/plain");
//		response.setCharacterEncoding("UTF-8");
//		return getResourceContent(document + ".ao");
//	}

	public String getResourceContent(String resourceName) {
		return Utils.loadFileContents(resourcePath + resourceName);
	}
}
