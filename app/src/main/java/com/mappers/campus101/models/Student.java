package com.mappers.campus101.models;

import java.util.ArrayList;

/**
 * A class that represents a student
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Student {

    // Instance variables
    private String id;
    private String name;
    private String surname;
    private String password;
    private Department department;
    private ArrayList<Task> tasks;

    // Constructor
    public Student (String id, String name, String surname, String password, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.department = department;
        tasks = new ArrayList<>();
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Department getDepartment() {
        return department;
    }

    // Assigns a task to student
    public void addTask (Task task){
        tasks.add (task);
    }

    // Returns the task history of a student
    public ArrayList<Task> getTaskHistory() {
        ArrayList<Task> history;
        history = new ArrayList<Task>();
        for ( Task task : tasks ) {
            history.add (task);
        }
        return history;
    }
}
