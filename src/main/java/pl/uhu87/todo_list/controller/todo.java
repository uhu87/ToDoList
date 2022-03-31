package pl.uhu87.todo_list.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.uhu87.todo_list.ToDo;
import pl.uhu87.todo_list.service.ToDoService;
import pl.uhu87.todo_list.service.ToDoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class todo {

    private ToDoService toDoService;
    private ToDoServiceImpl toDoServiceImpl;

    public todo(ToDoService toDoService, ToDoServiceImpl toDoServiceImpl) {
        this.toDoService = toDoService;
        this.toDoServiceImpl = toDoServiceImpl;
    }

    //___________SHOW ALL___________________________________

    @GetMapping("/")
    public List<ToDo>toDoList (){

        return toDoServiceImpl.getToDoList();
    }

    //___________SHOW ONE___________________________________

    @GetMapping("/{id}")
    public ToDo getToDo(@PathVariable Long  id){

       /* try {
            Long idNumber = Long.parseLong(id);
            if(toDoService.getToDo(idNumber)==null){return noIdMessage;};
            return toDoServiceImpl.SingleToDoListToString(toDoServiceImpl.getToDo(idNumber));
        }catch (NumberFormatException e){return "wrong format";}

*/
        return toDoServiceImpl.getToDo(id);

    }

    //___________ADD___________________________________

    @GetMapping("/add/{text}")
    public String addToDo(@PathVariable String text){
        if(text.isEmpty()){
            return "redirect:/todo/errorMsg";
        }
        ToDo toDo = new ToDo(text);
        toDoServiceImpl.addToDo(text);
        return "redirect:/todo";
    }


    @GetMapping("/add")
    @ResponseBody
    public String addform(){

        return addForm;
    }

    @PostMapping("/add")
    public String addformPost(ToDo toDo){



        String text = toDo.getText();
        ToDo newToDo = new ToDo(toDo.getText());
        toDoServiceImpl.addToDo(newToDo);


        return "redirect:/todo/";
    }

    //___________DELETE___________________________________

    @DeleteMapping("/delete/{id}")
    public String deleteToDo(@PathVariable String id){

        try {
            Long idNumber = Long.parseLong(id);
            if(toDoService.getToDo(idNumber)==null){
                return "redirect:/todo/wrongID";}
            toDoService.delete(idNumber);
            return "redirect:/todo";
        } catch (NumberFormatException e){return "redirect:/todo/errorMsg";}

    }


    //___________UPDATES___________________________________

    @GetMapping("/update/complete/{id}")
    public String completeToDo(@PathVariable String id){
        try {
            Long idNumber = Long.parseLong(id);
        if(toDoService.getToDo(idNumber)==null){
            return "redirect:/todo/wrongID";
        } else {
            toDoService.update(toDoService.getToDo(idNumber));

            return "redirect:/todo";}
        } catch (NumberFormatException e){return "redirect:/todo/errorMsg";}
    }

    @GetMapping("/update/text/{id}/{text}")
    public String updateText(@PathVariable String id, @PathVariable String text){
        try {
            Long idNumber = Long.parseLong(id);
        if(toDoService.getToDo(idNumber)==null){
            return "redirect:/todo/wrongID";
        } else {
            toDoServiceImpl.updateText(toDoService.getToDo(idNumber), text);

            return "redirect:/todo";}
        } catch (NumberFormatException e){return "redirect:/todo/errorMsg";}
    }

    @GetMapping("/update/priority/{id}")
    public String prioToDo(@PathVariable String id){
        try {
            Long idNumber = Long.parseLong(id);
        if(toDoService.getToDo(idNumber)==null){
            return "redirect:/todo/wrongID";
        } else {
        toDoServiceImpl.updatePriority(toDoService.getToDo(idNumber));

        return "redirect:/todo";}
        } catch (NumberFormatException e){return "redirect:/todo/errorMsg";}
    }

    //___________RESET___________________________________

    @GetMapping("/reset")
    public String reset(){

        toDoServiceImpl.resetList();

            return "redirect:/todo";
    }




    //___________AUX_MESSAGES___________________________________


    @GetMapping("/errorMsg")
    @ResponseBody
    public String errorMsg(){
        return "Wrong data";
    }


    @GetMapping("/wrongID")
    @ResponseBody
    public String wrongID(){
        return noIdMessage;
    }



    public String noIdMessage = "The task with the given ID doesn't exist, please choose another ID click -->" +
            "" +
            " <a href=\"/todo\">here</a> " +
            "" +
            "<-- to see your tasks";


    public String addForm = "<form action=\"#\" method=\"POST\">\n" +
            "  <p><input type=\"text\" required placeholder=\"Give your task's description\" name=\"text\"></p>\n" +
            "  <input type=\"submit\" value=\"SAVE\">\n" +
            "</form>";
}
