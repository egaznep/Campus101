/**
 * A class that represents tasks
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Task implements Cloneable{
    private int number;
    private TaskStatus status;
    private String description;
    // Starting and ending times will be handled also

    public Task(int number) {
        this.number = number;
        status = TaskStatus.NOT_COMPLETED;
        // Description will be requested from the server according to task number
        description = "";
    }

    public Task clone() {
        return new Task(this.number);
    }

    public void setCompleted () {
        status = TaskStatus.COMPLETED;
    }
}
