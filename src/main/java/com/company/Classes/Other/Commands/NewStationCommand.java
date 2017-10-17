package com.company.Classes.Other.Commands;

import com.company.Classes.Exceptions.TimeFormatException;
import com.company.Classes.Model.Time;
import com.company.Interfaces.ICommand;

public class NewStationCommand implements ICommand {
    private Time arrivalTime;
    private Time departureTime;
    private String name;
    private String routeName;
    private int trainNum;

    public int getTrainNum() {
        return trainNum;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public String getName() {
        return name;
    }

    public String getRouteName() {
        return routeName;
    }

    public NewStationCommand(String name, String routeName, int trainNum, String arrivalTime, String departureTime) throws Exception{
        this.arrivalTime = new Time(arrivalTime);
        this.departureTime = new Time(departureTime);
        this.trainNum = trainNum;
        this.name = name;
        this.routeName = routeName;
        if (!checkTime(this.arrivalTime,this.departureTime)) throw new Exception("Время отправления раньше времени прибытия");
    }

    public NewStationCommand(String name, String routeName, int trainNum, Time arrivalTime, Time departureTime) throws Exception{
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.trainNum = trainNum;
        this.name = name;
        this.routeName = routeName;
        if (!checkTime(this.arrivalTime,this.departureTime)) throw new Exception("Время отправления раньше времени прибытия");
    }

    private boolean checkTime(Time arrivalTime, Time departureTime){
        if (arrivalTime.getHours() < departureTime.getHours()){
            return true;
        }
        if (arrivalTime.getHours() == departureTime.getHours()
                && arrivalTime.getMinutes() < departureTime.getMinutes()){
            return true;
        }
        else return false;
    }
}
