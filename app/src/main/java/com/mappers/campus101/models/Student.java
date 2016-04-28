package com.mappers.campus101.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * A class that represents a student
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Student implements Locatable{

    // Instance variables
    private String id;
    private String name;
    private String surname;
    private String password;
    private String currentTaskID;
    private Department department;
    private ArrayList<Task> tasks;
    private Location location;

    // Constructor
    public Student (String id, String name, String surname, String password, Department department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.department = department;
        tasks = new ArrayList<>();
    }
    public  Student( String id)
    {
        this.id = id;
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

    @Override
    public void setLocation(double longitude, double latitude) {
        location.setLocation(longitude, latitude);
    }

    @Override
    public double getLongitude() {
        return 0;
    }

    @Override
    public double getLatitude() {
        return location.getLatitude();
    }

    @Override
    public boolean isAtLocation(Location loc) {
        return location.isAtLocation( loc);
    }

    public LatLng getLocation() { return location.getLocation(); }

    public void setTask(String taskID)
    {
        currentTaskID = taskID;
    }

    public String getCurrentTask()
    {
        return currentTaskID;
    }
}
