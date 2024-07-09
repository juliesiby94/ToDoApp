package com.example.ToDo_App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ToDo_App.model.ToDo;
import com.example.ToDo_App.service.ToDoService;

@Controller
public class ToDoController {
	
	@Autowired
	private ToDoService service;
	@GetMapping({"/","viewToDoList"})
	public String viewAllToDoItems(Model model,@ModelAttribute("message") String message) {
		model.addAttribute("list",service.getAllToDoItems());
		model.addAttribute("message",message);
		
		return "ViewToDoList";
	}
	@GetMapping("/updateToDoStatus/{id}")
public String updateToDoStatus(@PathVariable Long id,RedirectAttributes  redirectAttributes) {
		
		 if (service.updateStatus(id)) {
			 redirectAttributes.addFlashAttribute("message","Update Success");
			 return "redirect:/viewToDoList";
		 }
		 
		 redirectAttributes.addFlashAttribute("message"," Update Failure");
		 return "redirect:/viewToDoList";
		
	}
@GetMapping("/addToDoItem")
public String addToDoItem(Model model) {
	model.addAttribute("todo",new ToDo());
	return "AddToDoItem";
	
}
@PostMapping("/saveToDoItem")
public String saveToDoItem(ToDo todo,RedirectAttributes  redirectAttributes) {
	if(service.saveOrUpdateToDoItem(todo)) {
		redirectAttributes.addFlashAttribute("message","Save sucess");
		 return "redirect:/viewToDoList";
		
	}
	redirectAttributes.addFlashAttribute("message","Save failure");
	return "redirect:/addToDoItem";
	
	
}

@GetMapping("/editToDoItem/{id}")
public String editToDoItem(@PathVariable Long id,Model model) {
	model.addAttribute("todo",service.getToDoItemById(id));
	
	return "EditToDoItem";
	
}
@PostMapping("/editSaveToDoItem")
public String editsaveToDoItem(ToDo todo,RedirectAttributes  redirectAttributes) {
	if(service.saveOrUpdateToDoItem(todo)) {
		redirectAttributes.addFlashAttribute("message","Edit success");
		 return "redirect:/viewToDoList";
		
	}
	redirectAttributes.addFlashAttribute("message","edit failure");
	return "redirect:/addToDoItem"+todo.getId();
	
}
@GetMapping("/deleteToDoItem/{id}")
public String deleteToDoItem(@PathVariable Long id,RedirectAttributes  redirectAttributes ) {
	
	if(service.deleteToDoItem(id)) {
		redirectAttributes.addFlashAttribute("message","delete success");
		return  "redirect:/viewToDoList";
	}
	
	redirectAttributes.addFlashAttribute("message","delete failure");
	return  "redirect:/viewToDoList";
}

}
