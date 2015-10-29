package es.us.isa.ideas.module.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import es.us.isa.ideas.module.common.AppResponse;
import es.us.isa.ideas.module.model.Format;
import es.us.isa.ideas.module.model.Language;
import es.us.isa.ideas.module.model.Operation;
import es.us.isa.ideas.module.utils.Utils;

@Controller
@RequestMapping("/language")
public abstract class BaseLanguageController {

	protected Language language;
	protected String MANIFEST = "language_manifest.json";

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Language list() {
		initLanguage();
		return language;
	}

	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	@ResponseBody
	public Operation[] operations() {
		initLanguage();
		return language.getOperations();
	}

	@RequestMapping(value = "/operation/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Operation operation(@PathVariable("id") String id) {
		initLanguage();
		Operation operation = null;
		for (Operation op : language.getOperations()) {
			if (op.getId().equals(id))
				operation = op;
		}
		return operation;
	}

	@RequestMapping(value = "/format", method = RequestMethod.GET)
	@ResponseBody
	public Format[] formats() {
		initLanguage();
		return language.getFormats();
	}

	@RequestMapping(value = "/format/{format}", method = RequestMethod.GET)
	@ResponseBody
	public Format format(@PathVariable("format") String format) {
		initLanguage();
		Format formatId = null;
		for (Format f : language.getFormats()) {
			if (f.getFormat().equals(format))
				formatId = f;
		}
		return formatId;
	}

	@RequestMapping(value = "/format/{format}/mode", method = RequestMethod.GET)
	@ResponseBody
	public String getLanguageMode(@PathVariable("format") String format,
			HttpServletResponse response) {
		initLanguage();

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		String fileUri = "";
		for (Format f : language.getFormats())
			if (f.getFormat().equals(format))
				fileUri = f.get_editorModeURI();

		String res;
		try {
			res = Utils.loadFileContents("modes/" + fileUri);
			return res;
		} catch (Exception e) {
			return "no mode " + fileUri + " defined for format " + format;
		}
	}

	// TODO: Refactor with above method
	@RequestMapping(value = "/format/{format}/theme", method = RequestMethod.GET)
	@ResponseBody
	public String getLanguageModeTheme(@PathVariable("format") String format,
			HttpServletResponse response) {
		initLanguage();

		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");

		String fileUri = "";
		for (Format f : language.getFormats())
			if (f.getFormat().equals(format))
				fileUri = f.get_editorThemeURI();

		String res;
		try {
			res = Utils.loadFileContents("modes/" + fileUri);
			return res;
		} catch (Exception e) {
			return "no mode theme" + fileUri + " defined for format " + format;
		}
	}

	@RequestMapping(value = "/operation/{id}/execute", method = RequestMethod.POST)
	@ResponseBody
	public abstract AppResponse executeOperation(@PathVariable("id") String id,
			@RequestParam("content") String content,
			@RequestParam("fileUri") String fileUri,
			@RequestParam("data") String data);

	@RequestMapping(value = "/format/{format}/checkLanguage", method = RequestMethod.POST)
	@ResponseBody
	public abstract AppResponse checkLanguage(
			@PathVariable("format") String format,
			@RequestParam("content") String content,
			@RequestParam("fileUri") String fileUri);

	@RequestMapping(value = "/convert", method = RequestMethod.POST)
	@ResponseBody
	public abstract AppResponse convertFormat(
			@RequestParam("currentFormat") String currentFormat,
			@RequestParam("desiredFormat") String desiredFormat,
			@RequestParam("fileUri") String fileUri,
			@RequestParam("content") String content);

	// -------

	// Aux

	protected void initLanguage() {
		if (language == null) {
			System.out.println("[IDEAS] Language is not loaded. Loading...");
			loadLanguage();

		}
	}

	protected void loadLanguage() {

		System.out.println("[IDEAS] Loading language...");

		String json = Utils.loadFileContents(MANIFEST);

		Gson gson = new Gson();
		language = gson.fromJson(json, Language.class);

	}

}
