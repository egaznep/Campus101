package com.mappers.campus101.models;

/**
 * @author Nur Ecem Dilek
 * @date 24.4.2016
 */
public class Location implements Locatable
{
    // intance variables
    private double longitude;
    private double latitude;

    // constructor
    public Location ( double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // set method
    public void setLocation ( double longitude, double latitude)
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // getters
    public double getLongitude()
    {
        return longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    // implementation of method from Locatable interface
    public boolean isAtLocation( Location loc)
    {
        return (loc.getLongitude() == this.longitude) && (loc.getLatitude() == this.latitude);
    }

    // updates the longitude and tatitude
    private void updateLocation( double longitude, double latitude)
    {
        setLocation( longitude, latitude);
    }

}