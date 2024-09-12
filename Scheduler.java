import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Scheduler {
    private Map<Task, LocalDate[]> schedule = new HashMap<>();
    public void calculateSchedule(List<Task> tasks, LocalDate projectStartDate) {
        for (Task task : tasks) {
            generateTaskSchedule(task, projectStartDate);
        }
    }
    private LocalDate[] generateTaskSchedule(Task task, LocalDate projectStartDate) {
        if (schedule.containsKey(task)) {
            return schedule.get(task);
        }

        LocalDate startDate = assignStartDate(task, projectStartDate);

        LocalDate effectiveStartDate = startDate.plusWeeks(1);
        LocalDate effectiveEndDate = effectiveStartDate.plusWeeks(task.duration - 1);

        LocalDate[] taskSchedule = {effectiveStartDate, effectiveEndDate};
        schedule.put(task, taskSchedule);

        return taskSchedule;
    }
    private LocalDate assignStartDate(Task task, LocalDate projectStartDate) {
        LocalDate startDate = projectStartDate;
        for (Task dependency : task.taskDependencies) {
            LocalDate[] depSchedule = generateTaskSchedule(dependency, projectStartDate);
            startDate = startDate.isAfter(depSchedule[1]) ? startDate : depSchedule[1];
        }
        return startDate;
    }
}
