package com.isolutions4u.onlineshopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.isolutions4u.onlineshopping.model.Category;
import com.isolutions4u.onlineshopping.service.CategoryService;

@Controller
public class IndexController {

	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;

	@GetMapping(value = { "/", "/home" })
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickHome", true);
		modelAndView.addObject("title", "Home");

		modelAndView.addObject("categories",categoryService.findAllCategories());

		return modelAndView;
	}

	@GetMapping("/contact")
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickContact", true);
		modelAndView.addObject("title", "Contact Us");

		return modelAndView;
	}

	@GetMapping("/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickAbout", true);
		modelAndView.addObject("title", "About Us");

		return modelAndView;
	}

	/*
	 * Methods to load all the products and based on category
	 * 
	 */

	@GetMapping("/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickAllProducts", true);
		modelAndView.addObject("title", "All Products");

		// passing the list of categories

		modelAndView.addObject("categories", categoryService.findAllCategories());

		return modelAndView;
	}

	@GetMapping("/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView("page");

		// categoryDAO to fetch a single category

		Category category = null;
		category = categoryService.findCategoryById(id);

		modelAndView.addObject("userClickCategoryProducts", true);
		modelAndView.addObject("title", category.getName());

		// passing the list of categories

		modelAndView.addObject("categories", categoryService.findAllCategories());

		// passing the single category object
		modelAndView.addObject("category", category);

		return modelAndView;
	}

}