package com.company.Classes.Other.Commands;

import com.company.Interfaces.ICommand;

public class NewRouteCommand implements ICommand{
    private String name;
    private int trainNum;

    public String getName() {
        return name;
    }

    public int getTrainNum() {
        return trainNum;
    }

    public NewRouteCommand(String name, int trainNum){
        this.name = name;
        this.trainNum = trainNum;
    }
}
