package com.kb.timetable.entities;

import java.util.HashMap;
import java.util.Map;
/*
This Class Represents a  week .
Each Week is Map of Integer and WeekDay .
The Integer represents the Day of the week
 */

public class Week {
    private final Integer workingDaysInAWeek=6;
    private Map<Integer,WeekDay> weekDays;
    public Week(){
        weekDays=new HashMap<>();
        for(int i=0;i<workingDaysInAWeek;i++){
            weekDays.put(i,new WeekDay(i));
        }
    }
    public Week(Integer days){
        weekDays=new HashMap<>();
        for(int i=0;i<days;i++){
            weekDays.put(i,new WeekDay(i));
        }
    }

    public Map<Integer, WeekDay> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(Map<Integer, WeekDay> weekDays) {
        this.weekDays = weekDays;
    }

    @Override
    public String toString() {
        return "Week{" +
                "weekDays=" + weekDays +
                '}';
    }
}

