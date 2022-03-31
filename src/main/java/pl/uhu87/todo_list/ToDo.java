package pl.uhu87.todo_list;

public class ToDo {


    private Long id;
    private String text;
    private boolean completed = false;
    private boolean priority = false;

    public ToDo(Long id, String text, boolean completed, boolean priority) {
        this.id = id;
        this.text = text;
        this.completed = completed;
        this.priority = priority;
    }

    public ToDo(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public ToDo(String text) {
        this.text = text;
    }

    public ToDo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return
                "id: " + id +
                ", text: '" + text + '\'' +
                ", completed: " + completed;
    }
}
