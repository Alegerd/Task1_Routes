package com.company.Classes.View;

import com.company.Classes.Controller.Controller;
import com.company.Classes.Other.NewLineFromUserEvent;

import java.util.Scanner;

public class View {

    Scanner scanner;
    private String inputLine;

    public View(){
        scanner = new Scanner(System.in);
    }

    public void startLoop(){
        greetings();
        waitingForACommand();
    }

    public void setOutput(String text){
        System.out.println(text);
    }

    private void greetings(){
        System.out.println("База данных маршрутов");
    }

    private void waitingForACommand(){
        while (true){
            inputLine = scanner.nextLine();
            NewLineFromUserEvent inputEvent = new NewLineFromUserEvent(inputLine);
            Controller.getInstance().startEvent(inputEvent);
        }
    }

}
