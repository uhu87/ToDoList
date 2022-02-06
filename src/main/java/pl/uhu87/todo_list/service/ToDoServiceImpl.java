package pl.uhu87.todo_list.service;

import org.springframework.stereotype.Component;
import pl.uhu87.todo_list.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ToDoServiceImpl implements ToDoService {

    private List<ToDo> toDoList;

    public ToDoServiceImpl() {
        toDoList = new ArrayList<>();
        //i tutaj wrzucaj rzeczy do mapy
    }


    @Override
    public List<ToDo> getToDoList() {
        return this.toDoList;
    }


    public String ToDoListToString(){

        List<String>tasks = new ArrayList<>();
        for (ToDo td:toDoList
             ) {if (td.isCompleted()==true){tasks.add(td.getId()+" - "+td.getText()+" - "+" COMPLETED");}
        else{tasks.add(td.getId()+" - "+td.getText()+" - "+" IN PROGRESS");}
        }

        return tasks.stream()
                .map(element->"<li>"+element+"</li>")
                .collect(Collectors.joining());
    }

    @Override
    public ToDo addToDo(ToDo toDo) {
        toDo.setId(getLastId());
        toDoList.add(toDo);
        return toDo;
    }

    // DODATKOWA METODA DODAJACA, CZYTELNIEJ DLA KONTROLERA
    public ToDo addToDo(String text) {
        ToDo toDo = new ToDo();
        toDo.setText(text);
        toDo.setId(getLastId());
        toDoList.add(toDo);
        return toDo;
    }

    @Override
    public Optional<ToDo> getToDoOptional(Long id) {

        return Optional.empty();
    }

    @Override
    public ToDo getToDo(Long id) {

        for (ToDo toDo : toDoList) {
            if (id.equals(toDo.getId())) {
                return toDo;
            }
        }
        return null;
    }


    @Override
    public void delete(Long id) {
        toDoList.removeIf(toDo -> toDo.getId()==id);
    }


    @Override
    public void update(ToDo toDo) {

        toDo.setCompleted(true);

    }


    public void updateText(ToDo toDo, String text) {

        toDo.setText(text);

    }



    public void setToDoList(List<ToDo> toDoList) {
        this.toDoList = toDoList;
    }


    public Long getLastId() {

        if (toDoList.size() == 0) {
            return 1l;
        } else {

            int listSize = toDoList.size();
            Long lastID = toDoList.get(listSize - 1).getId();

            return lastID + 1;
        }
    }
}
