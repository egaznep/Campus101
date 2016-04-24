package com.mappers.campus101.models;

/**
 * @author Nur Ecem Dilek
 * @date 24.4.2016
 */
public interface Locatable
{
    void setLocation ( double longitude, double latitude);
    double getLongitude();
    double getLatitude();
    boolean isAtLocation( Location loc);

}