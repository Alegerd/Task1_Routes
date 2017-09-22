package com.company.Classes.Model;

import com.company.Classes.Exceptions.TimeFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    private int hours;
    private int minutes;

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public Time(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public Time(String time) throws TimeFormatException {
        Pattern pattern = Pattern.compile("^[0-2][0-9]:[0-5][0-9]$");//доработать регулярку
        Matcher matcher = pattern.matcher(time);
        if(matcher.matches()){
            String[] s = time.split(":");
            hours = Integer.parseInt(s[0]);
            minutes = Integer.parseInt(s[1]);
        }
        else {
            throw new TimeFormatException("Неверный формат ввода времени");
        }
    }

    public String getTime(){
        return hours + " : " + minutes;
    }

}
