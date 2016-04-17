package com.mappers.campus101.models;

import java.util.ArrayList;

/**
 * A class that represents a team
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Team {
    // Instance variable
    private ArrayList<Student> students;

    // Constructor
    public Team() {
        students = new ArrayList<Student>();
    }

    // Adds a student to team
    public void addStudent(Student student) {
        students.add (student);
    }
}
