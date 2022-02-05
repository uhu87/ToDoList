package pl.uhu87.todo_list.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.uhu87.todo_list.ToDo;

import java.util.List;
import java.util.Optional;

@Primary
public interface ToDoService {


    List<ToDo> getToDoList();
    ToDo addToDo(ToDo toDo);
    ToDo getToDo(Long id);
    Optional<ToDo> getToDoOptional (Long id);
    void delete (Long id);
    void update (ToDo toDo);

}
