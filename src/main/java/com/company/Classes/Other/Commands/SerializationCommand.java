package com.company.Classes.Other.Commands;

import com.company.Interfaces.ICommand;

public class SerializationCommand implements ICommand {
    public boolean serialize;

    public SerializationCommand(boolean serialize){
        this.serialize = serialize;
    }
}
