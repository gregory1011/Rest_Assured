
package com.cydeo.Homework.pojos.withIntellij;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class DriverTable {

  
    private String driverId;
    @JsonProperty("Drivers")
    private List<Driver> drivers;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

}
