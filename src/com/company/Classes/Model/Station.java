package com.company.Classes.Model;

public class Station {
    private String name;
    private Time arrivalTime;
    private Time departureTime;


    public String getName() {
        return name;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public String returnAllInformaion(){
        return "Станция: " + getName() + " Время прибытия: " + arrivalTime.getTime() + " Время отправления " + departureTime.getTime();
    }

    public Station(String name, Time arrivalTime, Time departureTime){
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
}
