package com.company.Classes.Model;

import com.company.Classes.Other.Commands.DeleteRouteCommand;
import com.company.Classes.Other.Commands.NewRouteCommand;
import com.company.Classes.Other.Commands.NewStationCommand;
import com.company.Classes.Other.Commands.ShowDataBaseCommand;
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
            default:
                break;
        }
        if (command != null) {
            return command;
        } else throw new NullPointerException();
    }
    private DeleteRouteCommand newDeleteRouteCommand() throws Exception {
        DeleteRouteCommand deleteRouteCommand;
        int trainNum = Integer.parseInt(inputArray[1]);
        String routeName = inputArray[2];
        deleteRouteCommand = new DeleteRouteCommand(trainNum, routeName);
        return deleteRouteCommand;
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
        newStationCommand = new NewStationCommand(stationName, routeName,trainNum, arrivalTime, departureTime);

        return newStationCommand;
    }

    private ShowDataBaseCommand createShowDataBaseCommand() throws Exception { //глупый метод
        ShowDataBaseCommand command = new ShowDataBaseCommand();
        return command;
    }
}
