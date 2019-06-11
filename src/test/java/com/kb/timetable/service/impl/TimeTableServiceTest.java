package com.kb.timetable.service.impl;


import com.kb.timetable.service.TimeTableService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.not;


public class TimeTableServiceTest {

    static TimeTableService timeTableService;
    static Map<String,Integer> schedule;

    @BeforeClass
    public static void setUpBase(){
        timeTableService=new TimeTableServiceImpl();
        schedule= new HashMap<>();
        schedule.put("Maths",40);
        schedule.put("Science",60);
        schedule.put("English",80);
        schedule.put("Physics",120);

    }
    /*
    This Test case gives the output for the sample input given in the problem statement
     */
    @Test
    public void checkGivenInput(){
        Integer daysToExams=200;
        System.out.println("::::::::Test Case 1 ::::::::");
        System.out.println("Schedule to Fit is ::"+schedule);
        System.out.println("Days to Exams ::"+daysToExams);
        timeTableService.fillTimeTable(200,schedule);
        timeTableService.printTimeTable();
        assertThat(timeTableService.getRemainingPeriods(),aMapWithSize(0));
    }

    @Test
    public void checkScheduleWithMoreSlots(){
        schedule.put("Hindi",100);
        schedule.put("Sports",100);
        Integer daysToExams=150;
        System.out.println("::::::::Test Case 2 ::::::::");
        System.out.println("Days to Exams ::"+daysToExams);
        System.out.println("Schedule to Fit is ::"+schedule);
        timeTableService.fillTimeTable(daysToExams,schedule);
        timeTableService.printTimeTable();
        assertThat(timeTableService.getRemainingPeriods(),aMapWithSize(0));
    }
    @Test
    public void checkWhenDaysAreLessThanSchedule(){
        schedule.put("Hindi",140);
        schedule.put("Sports",100);
        schedule.put("Dance",100);
        Integer daysToExams=100;
        System.out.println("::::::::Test Case 3 ::::::::");
        System.out.println("Days to Exams ::"+daysToExams);
        System.out.println("Schedule to Fit is ::"+schedule);
        timeTableService.fillTimeTable(daysToExams,schedule);
        timeTableService.printTimeTable();
        assertThat(timeTableService.getRemainingPeriods(),not(aMapWithSize(0)));
    }
}
