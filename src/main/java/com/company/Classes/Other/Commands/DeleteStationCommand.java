package com.company.Classes.Other.Commands;

import com.company.Interfaces.ICommand;

public class DeleteStationCommand implements ICommand {

    public String name;
    public String route;
    public int train;

    public DeleteStationCommand(String name, String routeName, int train){
        this.name = name;
        this.route = routeName;
        this.train = train;
    }
}
