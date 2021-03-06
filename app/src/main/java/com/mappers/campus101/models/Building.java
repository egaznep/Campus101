package com.mappers.campus101.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Nur Ecem Dilek
 * @date 24.4.2016
 */
public class Building implements Locatable
{
    // instance variables
    private int buildingId;
    private String name;
    private int range;
    private int capacity;
    private int currentNumberOfStudents;
    private Location location;

    // constructors
    public Building( int buildingId, String name, int range, Location location, int capacity)
    {
        this.buildingId = buildingId;
        this.name = name;
        this.range = range;
        this.location = location;
        this.capacity = capacity;
        this.currentNumberOfStudents = 0;
    }

    public Building ( int buildingId, String name, Location location){
        this.buildingId = buildingId;
        this.name = name;
        this.range = 0;
        this.location = location;
        this.capacity = 0;
        this.currentNumberOfStudents = 0;
    }
    /*
    // setter methods
    public void setBuildingId( int ID)
    {
        buildingId = ID;
    }
    
    public void setName( String name)
    {
        this.name = name;
    }

    public void setLocation( Location loc)
    {
        this.location = loc;
    }
    */
    public void setRange( int range)
    {
        this.range = range;
    }

    public void setCapacity( int cap)
    {
        this.capacity = cap;
    }
    
    public void setCurrentNumberOfStudents( int no) {
        currentNumberOfStudents = no;
    }

    // getter methods
    public int getBuildingId()
    {
        return buildingId;
    }

    public String getName()
    {
        return name;
    }

    public int getRange()
    {
        return range;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public int getCurrentNumberOfStudents()
    {
        return currentNumberOfStudents;
    }

    // toString Method
    public String toString()
    {
        return "ID: " + buildingId + "\nName: " + name
                + "\nLocation: " + location + "\nRange: "
                + range + "\nCapacity: " + capacity
                + "\nCurrent Number of Students: " + currentNumberOfStudents;
    }

    // methods from Locatable
    public void setLocation ( double longitude, double latitude)
    {
        location.setLocation( longitude, latitude);
    }

    public double getLongitude()
    {
        return location.getLongitude();
    }

    public double getLatitude()
    {
        return location.getLatitude();
    }

    public boolean isAtLocation( Location loc)
    {
        return location.isAtLocation(loc);
    }

    public LatLng getLocation() { return location.getLocation(); }
}