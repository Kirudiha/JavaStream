package com.kgfsl;

import java.util.Collection;

/**
 * Flight
 */
public  class Flight {
    public String id;
    public String name;
    public String departure;
    public String arrival;
    public int fare;
	private String flag;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
    * @param id the id to set
    */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getFare() {
        return fare;
    }
    public void setFare(int fare) {
        this.fare = fare;
    }
    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }


    
    @Override
    public String toString() {
        return id + ", " + name + ", " + departure + ", " + arrival + "," + fare + "," + flag;
    }

	
}