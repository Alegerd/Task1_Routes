package com.company.Classes;

import com.company.Classes.Controller.Controller;

public class Main {

    private static Controller controller;

    public static void main(String[] args) {
        controller = Controller.getInstance();
        controller.startApplication();
    }

}
