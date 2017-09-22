package com.company.Classes.Model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Model {

    static DataBase db = new DataBase();

    public static void showDataBase() { // попробовать сделать private

        try {
            Iterator<Route> iter = db.iterator();
            Route route;

            while (iter.hasNext()) {
                route = iter.next();
                System.out.println(route.returnAllInformation());
            }
        } catch (NoSuchElementException e) {
            System.out.println("База данных пуста :(");
        }
    }
}
