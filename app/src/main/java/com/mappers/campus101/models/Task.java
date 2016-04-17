package com.mappers.campus101.models;

/**
 * A class that represents a task
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Task {
    // Instance variables
    private int number;
    private TaskStatus status;
    private String description;
    // Starting and ending times will be handled also

    // Constructor
    public Task(int number) {
        this.number = number;
        status = TaskStatus.NOT_COMPLETED;
        // Description will be requested from the server according to task number
        description = "";
    }

    // Sets the task completed
    public void setCompleted () {
        status = TaskStatus.COMPLETED;
    }
}
