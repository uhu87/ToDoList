package pl.uhu87.todo_list.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.uhu87.todo_list.ToDo;
import pl.uhu87.todo_list.service.ToDoService;
import pl.uhu87.todo_list.service.ToDoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class todo {

    private ToDoService toDoService;
    private ToDoServiceImpl toDoServiceImpl;

    public todo(ToDoService toDoService, ToDoServiceImpl toDoServiceImpl) {
        this.toDoService = toDoService;
        this.toDoServiceImpl = toDoServiceImpl;
    }

    @GetMapping("/todo")
    @ResponseBody
    public String ToDoList(){
        return toDoService.getToDoList().stream()
                .map(element->"<li>"+element.getId()+" - "+element.getText()+" - "+element.isCompleted()+"</li>")
                .collect(Collectors.joining());
    }

    @GetMapping("/todo/{id}")
    @ResponseBody
    public String getToDo(@PathVariable Long id){

        if(toDoService.getToDo(id)==null){return noIdMessage;};
        return toDoService.getToDo(id).toString();
    }

    @GetMapping("/todo/add/{text}")
    public String addToDo(@PathVariable String text){
        if(text.isEmpty()){
            return "redirect:/errorMsg";
        }
        ToDo toDo = new ToDo(text);
        toDoServiceImpl.addToDo(text);
        return "redirect:/todo";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteToDo(@PathVariable Long id){
        toDoService.delete(id);
           return "redirect:/todo";
    }




    @GetMapping("/errorMsg")
    @ResponseBody
    public String errorMsg(){

        return "Niepoprawne dane";
    }









    public String noIdMessage = "Zadanie o podanym ID nie istnieje, wybierz inne ID, kliknij -->" +
            "" +
            " <a href=\"http://localhost:8080/todo\">tutaj</a> " +
            "" +
            "<-- aby sprawdzic dostepne zadania";

}
