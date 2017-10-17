package com.company.Classes.Other.Commands;

import com.company.Interfaces.ICommand;

public class DeleteRouteCommand implements ICommand {
    private int trainNum;
    private String routeName;

    public int getTrainNum() {
        return trainNum;
    }

    public String getRouteName() {
        return routeName;
    }

    public DeleteRouteCommand(int num, String name){
        trainNum = num;
        routeName = name;
    }
}
