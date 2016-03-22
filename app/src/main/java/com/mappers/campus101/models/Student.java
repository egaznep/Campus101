package com.mappers.campus101.models;

import java.util.ArrayList;

/**
 * A class that represents a student
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Student {

    private String id;
    private String name;
    private String surname;
    private String password;
    private Department department;
    private ArrayList<Task> tasks;

    public Student (String id, String name, String surname, String password, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.department = department;
        tasks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public Department getDepartment() {
        return department;
    }

    public void addTask (Task task){
        tasks.add (task);
    }

    public ArrayList<Task> getTaskHistory() {
        ArrayList<Task> history;
        history = new ArrayList<Task>();
        for ( Task task : tasks ) {
            history.add (task);
        }
        return history;
    }
}
