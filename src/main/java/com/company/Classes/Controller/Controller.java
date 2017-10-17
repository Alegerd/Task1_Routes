package com.company.Classes.Controller;

import com.company.Classes.Model.*;
import com.company.Classes.Other.Commands.*;
import com.company.Classes.Other.NewLineFromUserEvent;
import com.company.Classes.View.View;
import com.company.Interfaces.ICommand;
import com.company.Interfaces.IEvent;
import com.company.Interfaces.IListener;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class Controller implements IListener {
    private static Controller instance;
    private ArrayList<IListener> listeners = new ArrayList<>();  //это лишнее :)

    private View view;
    private Model model;
    private Parser parser;
    private DataBase db;

    private Controller() {
        parser = new Parser();
        db = new DataBase();
    }

    public void startApplication() {
        model = new Model();
        view = new View();
        view.startLoop();// отсюда поток уже не выйдет ибо у View бесконечный цикл на ввод
        // код, написанный далее, не выполнится
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    public void startEvent(IEvent event) {
        for (IListener listener : listeners) {
            listener.onEvent(event);
        }
        this.onEvent(event);
    }

    @Override
    public void onEvent(IEvent event) {                       //узнать как сделать private

        ICommand inputCommand;

        if (event instanceof NewLineFromUserEvent) {
            try {
                inputCommand = parser.parseInputLine(((NewLineFromUserEvent) event).getInputLine());
                executeCommand(inputCommand);

            } catch (Exception e) {                               //все исключения парсинга отлавливаются здесь
                view.setOutput("Ошибка: " + e.getMessage());
            }
        }
    }

    private void executeCommand(ICommand command) throws Exception {
        if (command instanceof NewRouteCommand) {
            createNewRoute((NewRouteCommand) command);
        } else if (command instanceof ShowDataBaseCommand) {
            showDataBase();
        } else if (command instanceof NewStationCommand) {
            createNewStation((NewStationCommand) command);
        } else if (command instanceof DeleteRouteCommand) {
            deleteRoute((DeleteRouteCommand) command);
        } else if (command instanceof SerializationCommand) {
            toGson((SerializationCommand) command);
        } else if (command instanceof DeleteStationCommand) {
            deleteStation((DeleteStationCommand) command);
        }
    }

    private void toGson(SerializationCommand command) throws Exception {
        Gson gson = new Gson();

        if (command.serialize) {

            String serializedDB = gson.toJson(db);
            String path = new File("").getAbsolutePath();
            File file = new File(path + "\\json.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(serializedDB);
            fw.flush();
        } else {
            String path = new File("").getAbsolutePath() + "\\json.txt";
            String data = new String();
            StringBuilder sb = new StringBuilder();
            Files.lines(Paths.get(path), StandardCharsets.UTF_8).forEach(sb::append);
            data = sb.toString();
            //System.out.println(data);
            db = gson.fromJson(data, DataBase.class);
            showDataBase();
        }
    }

    private void deleteStation(DeleteStationCommand command) throws Exception {
        if (!db.isEmpty()) {
            Iterator<Route> iter = db.iterator();

            while (iter.hasNext()) {
                Route next = iter.next();
                if (command.route.equals(next.getRouteName()) && command.train == next.getTrainNumber()) {
                    next.deleteStationFromRoute(command.name);
                }
            }
        } else throw new Exception("База данных пуста");
    }

    private void deleteRoute(DeleteRouteCommand command) throws Exception {
        if (!db.isEmpty()) {
            db.deleteRoute(command.getRouteName(), command.getTrainNum());
            view.setOutput("Маршрут " + command.getRouteName() + " удален.");
        } else throw new Exception("База данных пуста");
    }

    private void showDataBase() throws Exception {
        if (!db.isEmpty()) {
            Iterator<Route> routeIterator = db.iterator();
            while (routeIterator.hasNext()) {
                Route next = routeIterator.next();
                System.out.println(next.returnAllInformation());
            }
        } else throw new Exception("База данных пуста.");
    }

    private void createNewRoute(NewRouteCommand command) throws Exception {
        if (db.hasRoute(command.getName(), command.getTrainNum())) {
            throw new Exception("Такой маршрут уже существует.");
        } else {
            //если все нормально
            Route newRoute = new Route(command.getTrainNum(), command.getName());
            db.addRoute(newRoute);
            view.setOutput("Новый маршрут создан.");
        }
    }

    private void createNewStation(NewStationCommand command) throws Exception {
        Station station = new Station(command.getName(), command.getArrivalTime(), command.getDepartureTime());

        Iterator<Route> routeIterator = db.iterator();
        while (routeIterator.hasNext()) {
            Route route = routeIterator.next();

            if (route.getRouteName().equals(command.getRouteName())
                    && route.getTrainNumber() == command.getTrainNum()) {
                if (!route.hasStation(station)) {                          // проверка на уже существующую
                    route.addStationInRoute(station);
                    view.setOutput("Новая станция по маршруту " + "\"" + route.getRouteName() + "\"" + " добавлена.");
                    return;
                } else throw new Exception("Данная станция уже существует на указанном маршруте.");
            }
        }
        throw new Exception("Маршрут, который вы указали, не существует.");
    }

    public void someMethod() {

    }
}
