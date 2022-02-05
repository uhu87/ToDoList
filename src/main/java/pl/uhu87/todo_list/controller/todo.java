package pl.uhu87.todo_list.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.uhu87.todo_list.ToDo;
import pl.uhu87.todo_list.service.ToDoService;
import pl.uhu87.todo_list.service.ToDoServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class todo {

    private ToDoService toDoService;

    public todo(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/todo")
    public String ToDoList(){
        return toDoService.getToDoList().stream()
                .map(element->element.getId()+" - "+element.getText()+" - "+element.isCompleted())
                .collect(Collectors.joining(" // "));
    }

    @GetMapping("/todo/{id}")
    public String getToDo(@PathVariable Long id){

        if(toDoService.getToDo(id)==null){return noIdMessage;};
        return toDoService.getToDo(id).toString();
    }

    @GetMapping("/todo/add/{text}/{completed}")
    public String addToDo(@PathVariable String text, @PathVariable Boolean completed){

        ToDo toDo = new ToDo(10l, text, completed);
        toDoService.addToDo(toDo);
        return toDoService.getToDoList().stream()
                .map(element->element.getId()+","+element.getText()+", "+element.isCompleted())
                .collect(Collectors.joining("\n"));
    }













    public String noIdMessage = "Zadanie o podanym ID nie istnieje, wybierz inne ID, kliknij -->" +
            "" +
            " <a href=\"http://localhost:8080/todo\">tutaj</a> " +
            "" +
            "<-- aby sprawdzic dostepne zadania";


    String test = "<form action=\"post\" method=\"POST\">\n" +
            "    <p><input type=\"text\" required placeholder=\"isbn\" name=\"isbn\"></p>\n" +
            "    <p><input type=\"text\" required placeholder=\"title\" name=\"title\"></p>\n" +
            "    <p><input type=\"text\" required placeholder=\"author\" name=\"author\"></p>\n" +
            "    <p><input type=\"text\" required placeholder=\"publisher\" name=\"publisher\"></p>\n" +
            "    <p><input type=\"text\" required placeholder=\"type\" name=\"type\"></p>";
}
