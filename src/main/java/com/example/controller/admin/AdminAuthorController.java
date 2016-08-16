package com.example.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Author;
import com.example.service.AuthorService;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminAuthorController {
	
	private AuthorService authorService;
	
	@Autowired
	public AdminAuthorController(AuthorService authorService){
		this.authorService = authorService;
	}
	
	@RequestMapping("/admin/authors")
	public String list(Model model){
		model.addAttribute("authors", authorService.list());
		return "admin/author/list";
	}
	
	@RequestMapping("/admin/author/create")
	public String create(Model model){
		model.addAttribute("author", new Author());
		return "/admin/author/authorForm";
	}
	
	@RequestMapping(value ="/admin/author/save")
	public String save(@Valid Author author,BindingResult bindungResult, Model model, RedirectAttributes redirectAttrs){
		if(bindungResult.hasErrors()){
			return "/admin/author/authorForm";
		}else{
			authorService.save(author);
			redirectAttrs.addFlashAttribute("message", "new Author has been added");
			return "redirect:/admin/authors/";
		}
	}
	
	@RequestMapping("/admin/author/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("author", authorService.get(id));
		return "admin/author/authorForm";
	}
	
	@RequestMapping("/admin/author/delete/{id}")
	public String delete(@PathVariable Long id,RedirectAttributes redirectAttrs){
		authorService.delete(id);
		redirectAttrs.addFlashAttribute("message", "Author has been deleted");
		return "redirect:/admin/authors/";
	}

}
