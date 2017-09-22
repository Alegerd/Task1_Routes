package com.company.Classes.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Route implements Iterable<Station>{

    //fields
    private int trainNumber;

    private String routeName;

    private ArrayList<Station> stations = new ArrayList<>();

    //properties
    public int getTrainNumber(){
        return trainNumber;
    }

    public String getRouteName(){
        return routeName;
    }

    //constructor
    public Route(int trainNumber, String routeName){

        this.trainNumber = trainNumber;
        this.routeName = routeName;
    }

    //methods

    public Iterator<Station> iterator() {
        return new Iterator<Station>(){

            private int index = Route.this.stations.isEmpty() ? -2 : -1;

            @Override
            public boolean hasNext () {
                if (index >= -1) {
                    return index < Route.this.stations.size() -1;
                } else return false;
            }

            @Override
            public Station next () {
                if (this.hasNext()) {
                    index++;
                    return Route.this.stations.get(index);
                }
                else throw new NoSuchElementException();
            }
        };
    }
    public boolean hasStation(Station station){
        for (Station stat: stations) {
            if(stat.getName().equals(station.getName())) return true;
        }
        return false;
    }

    public String returnAllInformation() {
        String info = "";
        info += "Маршрут: " + routeName + ", Номер поезда: " + trainNumber;
        info+=informationAboutStations();
        return info;
    }

    public Station getStartingStation(){
        return stations.get(0);
    } // предусмотреть возврат нуля!!!

    public Station getDestanationStation(){
        return stations.get(stations.size() - 1);
    }

    public void addStationInRoute(Station newStation){
        stations.add(newStation);
    }

    public void deleteStationFromRoute(int stationNumber){
        if (stationNumber <= stations.size() - 1 &&
                stationNumber >= 0 &&
                !stations.isEmpty())
        {
            stations.remove(stationNumber);
        }
        else throw new NoSuchElementException();

    }

    public void deleteStationFromRoute(String stationName){
        for (Station station:stations) {
            if (station.getName() == stationName) {
                stations.remove(station);
                break;
            }
        }
    }

    private String informationAboutStations(){
        String info = "";
        for (Station station:stations) {
            info+="\n\t" + station.returnAllInformaion() + "\n";
        }
        return info;
    }

}
