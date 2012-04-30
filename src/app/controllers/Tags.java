package controllers;

import models.Tag;

/**
 * Tags is the controller class that is responsible of handling
 * HTTP requests for tags.
 * 
 * @author	Onur Yaman  <onuryaman@gmail.com>
 * @version 1.0
 * @since	1.0
 */
public class Tags extends BaseController {

	public static void search() {
		// fetch the tag name.
		String tagName = params.get("term");
		
		// if the query is empty, return null object.
		if ("".equals(tagName)) {
			renderJSON("[]");
		} else {
			// get recommended tags and return them.
			renderJSON(Tag.fetch(tagName));
		}
	}
	
}