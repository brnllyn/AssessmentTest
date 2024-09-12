import java.util.ArrayList;
import java.util.List;

class Task {
    String name;
    int duration;
    List<Task> taskDependencies;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.taskDependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        taskDependencies.add(task);
    }
}