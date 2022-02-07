package pl.uhu87.todo_list.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.todo_list.ToDo;
import pl.uhu87.todo_list.service.ToDoService;
import pl.uhu87.todo_list.service.ToDoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/todo")
public class todo {

    private ToDoService toDoService;
    private ToDoServiceImpl toDoServiceImpl;

    public todo(ToDoService toDoService, ToDoServiceImpl toDoServiceImpl) {
        this.toDoService = toDoService;
        this.toDoServiceImpl = toDoServiceImpl;
    }

    @GetMapping("")
    @ResponseBody
    public String ToDoList(){
        return toDoServiceImpl.ToDoListToString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getToDo(@PathVariable Long id){

        if(toDoService.getToDo(id)==null){return noIdMessage;};
        return toDoServiceImpl.SingleToDoListToString(toDoServiceImpl.getToDo(id));
    }

    @GetMapping("/add/{text}")
    public String addToDo(@PathVariable String text){
        if(text.isEmpty()){
            return "redirect:/todo/errorMsg";
        }
        ToDo toDo = new ToDo(text);
        toDoServiceImpl.addToDo(text);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDo(@PathVariable Long id){
        toDoService.delete(id);
           return "redirect:/todo";
    }


    @GetMapping("/update/complete/{id}")
    public String completeToDo(@PathVariable Long id){

        if(toDoService.getToDo(id)==null){
            return "redirect:/todo/wrongID";
        } else {
        toDoService.update(toDoService.getToDo(id));

        return "redirect:/todo";}
    }

    @GetMapping("/update/text/{id}/{text}")
    public String updateText(@PathVariable Long id, @PathVariable String text){
        if(toDoService.getToDo(id)==null){
            return "redirect:/todo/wrongID";
        } else {
        toDoServiceImpl.updateText(toDoService.getToDo(id), text);

        return "redirect:/todo";}
    }

    @GetMapping("/add")
    @ResponseBody
    public String addform(){

        return addForm;
    }

    @PostMapping("/add")
    public String addformPost(@RequestParam("text") String text){

        if(text.isEmpty()){
            return "redirect:/todo/errorMsg";
        }
        ToDo toDo = new ToDo(text);
        toDoServiceImpl.addToDo(text);
        return "redirect:/todo";
    }

    @GetMapping("/update/priority/{id}")
    public String prioToDo(@PathVariable Long id){

        if(toDoService.getToDo(id)==null){
            return "redirect:/todo/wrongID";
        } else {
        toDoServiceImpl.updatePriority(toDoService.getToDo(id));

        return "redirect:/todo";}
    }



    //___________AUX_MESSAGES___________________________________


    @GetMapping("/errorMsg")
    @ResponseBody
    public String errorMsg(){
        return "Niepoprawne dane";
    }


    @GetMapping("/wrongID")
    @ResponseBody
    public String wrongID(){
        return noIdMessage;
    }



    public String noIdMessage = "Zadanie o podanym ID nie istnieje, wybierz inne ID, kliknij -->" +
            "" +
            " <a href=\"/todo\">tutaj</a> " +
            "" +
            "<-- aby sprawdzic dostepne zadania";


    public String addForm = "<form action=\"#\" method=\"POST\">\n" +
            "  <p><input type=\"text\" required placeholder=\"Podaj treść zadania\" name=\"text\"></p>\n" +
            "  <input type=\"submit\" value=\"Wyślij\">\n" +
            "</form>";
}
