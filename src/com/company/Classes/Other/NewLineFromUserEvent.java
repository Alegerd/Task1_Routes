package com.company.Classes.Other;

import com.company.Interfaces.IEvent;

public class NewLineFromUserEvent implements IEvent {
    private String inputLine;

    public NewLineFromUserEvent(String inputLine){
        this.inputLine = inputLine;
    }
    public String getInputLine() {
        return inputLine;
    }
}
