package com.kb.timetable.service.impl;

import com.kb.timetable.entities.Week;
import com.kb.timetable.service.TimeTableService;
import com.kb.timetable.service.WeekDayService;

import java.util.*;
import java.util.stream.Collectors;


public class TimeTableServiceImpl implements TimeTableService {

    List<Week> timeTable ;
    Integer daysToExams;



    WeekDayService weekDayService;

    private static Map<String,Integer> remainingPeriods ;

    public TimeTableServiceImpl(){
        weekDayService=new WeekDayServiceImpl();
    }


    public Map<String,Integer> getRemainingPeriods(){
        return remainingPeriods;
    }

    /*
    Main method that fills in a timetable
     */
    public void fillTimeTable(Integer daysToExams, Map<String,Integer> periods) {
        this.daysToExams=daysToExams;
        timeTable=new ArrayList<>();
        //TO keep a sorted Map of schedule. Sorted by number of classes
        this.remainingPeriods=periods.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(e->e.getKey(), e->e.getValue(),(e1, e2)->e2, LinkedHashMap::new));
        fillAllSubjectsAtLeastOnce(daysToExams);
        fillRemainingTimeTable();
    }
    /*
    Method to meet the condition
    "Each Subject should be taught at least once a week"
     */
    public void fillAllSubjectsAtLeastOnce(Integer daysToExams) {
        for(int i=0;i<daysToExams/7;i++){
            int startDay=0;
            Week week=new Week();
            for(String subject:remainingPeriods.keySet()){
                if (!weekDayService.addPeriodToDay(week.getWeekDays().get(startDay),subject,remainingPeriods)){
                    weekDayService.addPeriodToDay(week.getWeekDays().get(++startDay),subject,remainingPeriods);
                }
            }
            timeTable.add(i,week);
        }
        fillLastWeekAtleastOnce(daysToExams%7);
    }
    /*
    Fills in the remaining Days in the last week
     */
    void fillLastWeekAtleastOnce(Integer daysInLastWeek) {
        int startDay=0;
        Week week=new Week(daysInLastWeek);
        for(String subject:remainingPeriods.keySet()){
            if (!weekDayService.addPeriodToDay(week.getWeekDays().get(startDay),subject,remainingPeriods)){
                weekDayService.addPeriodToDay(week.getWeekDays().get(++startDay),subject,remainingPeriods);
            }
        }
        timeTable.add(week);
    }
    /*
    Now that the Mandatory slots have been filled the rest of the time table is filled in
     */
    void fillRemainingTimeTable() {
        for(Week eachWeek:timeTable){
            eachWeek.getWeekDays().forEach((key,weekyday)->{weekDayService.fillDay(weekyday.getSchedule(),remainingPeriods);});
        }
    }

    public void printTimeTable(){
        //Integer weekNo=0;
        for(Week eachWeek:timeTable){
            //System.out.println("Week ::"+(++weekNo));
            eachWeek.getWeekDays().forEach((key,weekday)->{System.out.println(weekday);});
        }
    }
}
