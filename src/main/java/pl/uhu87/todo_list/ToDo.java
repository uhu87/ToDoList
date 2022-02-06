package pl.uhu87.todo_list;

public class ToDo {


    private Long id;
    private String text;
    private boolean completed = false;

    public ToDo(Long id, String text, boolean completed) {
        this.id = id;
        this.text = text;
        this.completed = completed;
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

    @Override
    public String toString() {
        return
                "id: " + id +
                ", text: '" + text + '\'' +
                ", completed: " + completed;
    }
}
