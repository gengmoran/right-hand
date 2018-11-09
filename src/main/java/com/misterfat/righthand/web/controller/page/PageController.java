package com.misterfat.righthand.web.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{page}")
	public String viewPageByPath1(@PathVariable String page) {

		return page;
	}

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{dir}/{page}")
	public String viewPageByPath2(@PathVariable String dir, @PathVariable String page) {

		return dir + "/" + page;
	}

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{dir1}/{dir2}/{page}")
	public String viewPageByPath3(@PathVariable String dir1, @PathVariable String dir2, @PathVariable String page,
			Model model) {

		return dir1 + "/" + dir2 + "/" + page;
	}

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{dir1}/{dir2}/{dir3}/{page}")
	public String viewPageByPath4(@PathVariable String dir1, @PathVariable String dir2, @PathVariable String dir3,
			@PathVariable String page, Model model) {

		return dir1 + "/" + dir2 + "/" + dir3 + "/" + page;
	}

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{dir1}/{dir2}/{dir3}/{dir4}/{page}")
	public String viewPageByPath5(@PathVariable String dir1, @PathVariable String dir2, @PathVariable String dir3,
			@PathVariable String dir4, @PathVariable String page, Model model) {

		return dir1 + "/" + dir2 + "/" + dir3 + "/" + dir4 + "/" + page;
	}

	/**
	 * 通用页面访问方法
	 * 
	 * @return
	 */
	@GetMapping("/page/{dir1}/{dir2}/{dir3}/{dir4}/{dir5}/{page}")
	public String viewPageByPath6(@PathVariable String dir1, @PathVariable String dir2, @PathVariable String dir3,
			@PathVariable String dir4, @PathVariable String dir5, @PathVariable String page, Model model) {

		return dir1 + "/" + dir2 + "/" + dir3 + "/" + dir4 + "/" + dir5 + "/" + page;
	}

}
