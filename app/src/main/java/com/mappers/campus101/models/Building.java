package com.mappers.campus101.models;
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
    private Location location;
    private int capacity;
    private int currentNumberOfStudents;

    // constructor
    public Building( int buildingId, String name, int range, Location location, int capacity)
    {
        this.buildingId = buildingId;
        this.name = name;
        this.range = range;
        this.location = location;
        this.capacity = capacity;
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
    
    public void setRange( int range)
    {
        this.range = range;
    }
    
    public void setLocation( Location loc)
    {
        this.location = loc;
    }
    
    public void setCapacity( int cap)
    {
        this.capacity = cap;
    }
    
    public void setCurrentNumberOfStudents( int no)
    {
        currentNumberOfStudents = no;
    }
    */

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

    public Location getLocation()
    {
        return location;
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
        return "ID: " + buildingId + "Name: " + name + "Location: " + location + "Range: " + range +
                "Capacity: " + capacity + "Current Number of Students: " + currentNumberOfStudents;
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
}