package com.company.Classes.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataBase implements Iterable<Route> {

    private ArrayList<Route> routes;

    public DataBase(){
        routes = new ArrayList<>();
    }

    public boolean hasRoute(Route route){
        for (Route r: routes) {
            if (r.getRouteName() == route.getRouteName() &&
                    r.getTrainNumber() == route.getTrainNumber()) return true;
        }
        return false;
    }

    public boolean hasRoute(String name, int num){
        for (Route r: routes) {
            if (r.getRouteName().equals(name)&&
                    r.getTrainNumber() == num) return true;
        }
        return false;
    }

    public void addRoute(Route newRoute){
        routes.add(newRoute);
    }

    public void deleteRoute(int routeIndex) throws Exception{
        if (routeIndex <= routes.size() - 1 &&
                routeIndex >= 0 &&
            !this.isEmpty())
        {
            routes.remove(routeIndex);
        }
        else throw new Exception("Такого маршрута нет.");
    }

    public void deleteRoute(String routeName, int trainNum){
        for (Route route:routes) {
            if (route.getRouteName().equals(routeName) &&
                    (route.getTrainNumber() == trainNum)){
                routes.remove(route);
                return;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty(){
        return routes.isEmpty();
    }

    public Iterator<Route> iterator() {
        return new Iterator<Route>(){

            private int index = DataBase.this.routes.isEmpty() ? -2 : -1;

            @Override
            public boolean hasNext () {
                if (index >= -1) {
                    return index < DataBase.this.routes.size() - 1;
                } else return false;
            }

            @Override
            public Route next () {
                if (this.hasNext()) {
                    index++;
                    return DataBase.this.routes.get(index);
                }
                else throw new NoSuchElementException();
            }
        };
    }

    public Route getRoute(int routeIndex){
        if (routeIndex <= routes.size() - 1 &&
                routeIndex >= 0 &&
                !this.isEmpty())
        {
            return routes.get(routeIndex);
        }
        else throw new NoSuchElementException();
    }
}
