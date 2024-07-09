
# ToDo Application

ToDo Application shows a list of tasks with provision of adding tasks,deadlines,status of todo task,edit and delete options.


## Framework and language 

Spring framework is used to do the ToDo Application.JSP front end technology is used for graphical user interface.Java files were created for the working of various options in this application.MySql connector is used to connect to database.
## Screenshots


Screenshot of View ToDo List Page

https://github.com/juliesiby94/ToDo/assets/175052532/5e31c575-2bff-4f8a-b7c7-e601aaa5df7d


Screenshot of Add Item to ToDo List

https://github.com/juliesiby94/ToDo/assets/175052532/c7a876b4-7b19-45c9-b2b5-22eb9deb553b

Screenshot of Edit Item of ToDo List

https://github.com/juliesiby94/ToDo/assets/175052532/2ed722f7-7bd1-43e5-9af6-9cddf59fba56


## Documentation

The Application page have seven fields with an Add ToDo Item tab.
Id,Title,Date,Status,MarkAsComplete,Edit,Delete are the different fields.Add ToDo Item tab helps to add new tasks.

#### Add ToDo Item

This tab  gives us the provisions to enter the title of the task,deadline of the task and you can see the status of the task as incomplete.

#### Application properties
 
 spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

These are the two properties to work with jsp.


Connection to database: spring.datasource.url=jdbc:mysql://localhost:3306/todo_app

#### viewAllToDoItems

This function is to fetch all to do items with the help of a model.

@GetMapping({"/","viewToDoList"})
	public String viewAllToDoItems(Model model,@ModelAttribute("message") String message) {
		model.addAttribute("list",service.getAllToDoItems());
		model.addAttribute("message",message);
		
		return "ViewToDoList";

#### updateToDoStatus

public String updateToDoStatus(@PathVariable Long id,RedirectAttributes  redirectAttributes) {
		
		 if (service.updateStatus(id)) {
			 redirectAttributes.addFlashAttribute("message","Update Success");
			 return "redirect:/viewToDoList";
		 }
		 
		 redirectAttributes.addFlashAttribute("message"," Update Failure");
		 return "redirect:/viewToDoList";

#### addToDoItem

public String addToDoItem(Model model) {
	model.addAttribute("todo",new ToDo());
	return "AddToDoItem";

#### saveToDoItem

To save the newly added todo item that is getting from addToDoItem function

public String saveToDoItem(ToDo todo,RedirectAttributes  redirectAttributes) {
	if(service.saveOrUpdateToDoItem(todo)) {
		redirectAttributes.addFlashAttribute("message","Save sucess");
		 return "redirect:/viewToDoList";
		
	}
	redirectAttributes.addFlashAttribute("message","Save failure");
	return "redirect:/addToDoItem";

#### editToDoItem

It is to edit the title and deadline of the todo item.This is doing with the help of id.

public String editToDoItem(@PathVariable Long id,Model model) {
	model.addAttribute("todo",service.getToDoItemById(id));
	
	return "EditToDoItem";
	
}

#### editSaveToDoItem

This is to save the edited todo item and redirect it to viewToDoList.

public String editsaveToDoItem(ToDo todo,RedirectAttributes  redirectAttributes) {
	if(service.saveOrUpdateToDoItem(todo)) {
		redirectAttributes.addFlashAttribute("message","Edit success");
		 return "redirect:/viewToDoList";
		
	}
	redirectAttributes.addFlashAttribute("message","edit failure");
	return "redirect:/addToDoItem"+todo.getId();
	
}

#### deleteToDoItem

Deleting item using Id.

public String deleteToDoItem(@PathVariable Long id,RedirectAttributes  redirectAttributes ) {
	
	if(service.deleteToDoItem(id)) {
		redirectAttributes.addFlashAttribute("message","delete success");
		return  "redirect:/viewToDoList";
	}
	
	redirectAttributes.addFlashAttribute("message","delete failure");
	return  "redirect:/viewToDoList";
}



Finally we are creating three jsp files for add todo item,edit todo item and to view todo item.












## References

https://www.google.com/

https://www.youtube.com/