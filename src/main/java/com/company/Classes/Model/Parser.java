package com.company.Classes.Model;

import com.company.Classes.Other.Commands.*;
import com.company.Interfaces.ICommand;

public class Parser {

    private String[] inputArray;


    public Parser() {

    }

    public ICommand parseInputLine(String inputLine) throws Exception {
        ICommand command = null;
        inputArray = inputLine.split(" "); // проработать все случаи ввода
        switch (inputArray[0]) {                     // обработать случаи возврата null
            case "cr_r":
                command = createNewRouteCommand();
                break;
            case "show":
                command = createShowDataBaseCommand();
                break;
            case "cr_s":
                command = createNewStationCommand();
                break;
            case "del_r":
                command = newDeleteRouteCommand();
                break;
            case "del_s":
                command = deleteStationCommand();
                break;
            case "toJson":
                command = newSerializationCommand(true);
                break;
            case "fromJson":
                command = newSerializationCommand(false);
                break;
            default:
                throw new Exception("Такой команды нет.");
        }
        if (command != null) {
            return command;
        } else throw new NullPointerException();
    }

    private SerializationCommand newSerializationCommand(boolean type) {
        return new SerializationCommand(type);
    }

    private DeleteRouteCommand newDeleteRouteCommand() throws Exception {
        DeleteRouteCommand deleteRouteCommand;
        int trainNum = Integer.parseInt(inputArray[1]);
        String routeName = inputArray[2];
        deleteRouteCommand = new DeleteRouteCommand(trainNum, routeName);
        return deleteRouteCommand;
    }

    private DeleteStationCommand deleteStationCommand() throws Exception {
        DeleteStationCommand deleteStationCommand;
        String stationName = inputArray[1];
        String routeName = inputArray[2];
        int trainNum = Integer.parseInt(inputArray[3]);
        deleteStationCommand = new DeleteStationCommand(stationName, routeName, trainNum);
        return deleteStationCommand;
    }

    private NewRouteCommand createNewRouteCommand() throws Exception { //сделать исключения на все случаи жизни
        NewRouteCommand newRouteCommand;
        int trainNumber = Integer.parseInt(inputArray[1]);
        String routeName = inputArray[2];
        newRouteCommand = new NewRouteCommand(routeName, trainNumber);
        return newRouteCommand;
    }

    private NewStationCommand createNewStationCommand() throws Exception {
        NewStationCommand newStationCommand;
        String stationName = inputArray[1];
        String routeName = inputArray[2];
        int trainNum = Integer.parseInt(inputArray[3]);
        Time arrivalTime = new Time(inputArray[4]);
        Time departureTime = new Time(inputArray[5]);
        newStationCommand = new NewStationCommand(stationName, routeName, trainNum, arrivalTime, departureTime);

        return newStationCommand;
    }

    private ShowDataBaseCommand createShowDataBaseCommand() throws Exception { //глупый метод
        ShowDataBaseCommand command = new ShowDataBaseCommand();
        return command;
    }

}






