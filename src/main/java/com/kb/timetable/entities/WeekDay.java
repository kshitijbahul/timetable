package com.kb.timetable.entities;

import java.util.ArrayList;
import java.util.List;

/*
This Class Represents a Day in a week . Each Day has at most 5 slots
Days have a Name , which becomes helpful while displaying
 */

public class WeekDay {
    private final Integer slotsInADay=5;
    private String day;
    private List<String> schedule;

    public WeekDay(Integer dayNumber){
        schedule = new ArrayList<>(slotsInADay);
        switch (dayNumber){
            case 0:
                day="Monday";
                return;
            case 1:
                day="Tuesday";
                return;
            case 2:
                day="Wednesday";
                return;
            case 3:
                day="Thursday";
                return;
            case 4:
                day="Friday";
                return;
            case 5:
                day="Saturday";
                return;
            case 6:
                day="Sunday";
                return;
        }

    }
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }
    public String getScheduleForASlot(int index){
        return schedule.get(index);
    }

    @Override
    public String toString(){
        StringBuilder sb= new StringBuilder();
        sb.append(day+":");
        int slot=0;
        for(String subject:schedule){
            sb.append(" T"+(++slot)).append("("+subject+")");
        }
        return sb.toString();
    }
}
