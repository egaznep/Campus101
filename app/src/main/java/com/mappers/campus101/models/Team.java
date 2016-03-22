package com.mappers.campus101.models;

import java.util.ArrayList;

/**
 * A class that represents a team
 * @author Kadir Can Çelik
 * @date 21.03.2016
 */
public class Team {
    private ArrayList<Student> students;

    public Team() {
        students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        students.add (student);
    }

    public boolean canBeAdded (Student student) {
        boolean result;
        result = true;
        if ( students.size() != 0 ) {
        }
        return result;
    }
}
