package com.gmail.firozkhanirl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gmail.firozkhanirl.model.Post;
import com.gmail.firozkhanirl.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	private static Logger logger = Logger.getLogger(PostController.class);
	
	@Resource(name="postService")
	private PostService postService;
	
	// Displaying the initial users list.
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public String getPersons(Model model) {
			logger.debug("fetch post failed");
			List user_list = postService.getAll();		
			model.addAttribute("users", user_list);		
			return "welcome";
		}

		// Opening the add new user form page.
		@RequestMapping(value = "/add", method = RequestMethod.GET)
		public String addUser(Model model) {
			logger.debug("Request to open the new user form page");
			model.addAttribute("userAttr", new Post());
			return "form";
		}

		// Opening the edit user form page.
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public String editUser(@RequestParam(value="id", required=true) int id, Model model) {
			logger.debug("Request to open the edit user form page");	
			model.addAttribute("userAttr", postService.findPostById(id));		
			return "form";
		}

		// Deleting the specified user.
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String delete(@RequestParam(value="id", required=true) int id, Model model) {
			postService.delete(id);
			return "redirect:list";
		}

		// Adding a new user or updating an existing user.
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String save(@ModelAttribute("userAttr") Post post) {
			if(post.getId() != 0) {
				postService.edit(post);
			} else {
				postService.add(post);
			}
			return "redirect:list";
		}
}
