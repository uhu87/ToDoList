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
    }


    @Override
    public List<ToDo> getToDoList() {
        return this.toDoList;
    }


    public String ToDoListToString() {

        List<String> tasks = new ArrayList<>();
        for (ToDo td : toDoList
        ) {
            if (td.isCompleted() == true) {
                tasks.add(td.getId() + " - " + td.getText() + " - " + "status COMPLETED");
            } else if(td.isCompleted()==false && td.isPriority()==true)
            { tasks.add(td.getId() + " - " + td.getText() + " - " + "status: IN PROGRESS"+ " - PRIORITY");}
            else if (td.isCompleted() == false) {
                tasks.add(td.getId() + " - " + td.getText() + " - " + "status: IN PROGRESS");
            }
        }

        if(tasks.isEmpty()){return "Your list is empty, please <a href=\"/todo/add\">add</a> tasks to your list";}
        return tasks.stream()
                .map(element -> "<li>" + element + "</li>")
                .collect(Collectors.joining());
    }


    public String SingleToDoListToString(ToDo td) {

        if (td.isCompleted() == true) {
            return td.getId() + " - " + td.getText() + " - " + "status COMPLETED";
        } else {
            return td.getId() + " - " + td.getText() + " - " + "status: IN PROGRESS";
        }

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
        toDoList.removeIf(toDo -> toDo.getId() == id);
    }


    @Override
    public void update(ToDo toDo) {

        toDo.setCompleted(true);

    }


    public void updateText(ToDo toDo, String text) {

        toDo.setText(text);

    }


    public void updatePriority(ToDo toDo) {

        toDo.setPriority(true);

    }

    public void  resetList()
    {toDoList = new ArrayList<>();}


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
