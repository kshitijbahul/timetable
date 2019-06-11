package com.kb.timetable.service.impl;


import com.kb.timetable.entities.WeekDay;
import com.kb.timetable.service.TimeTableService;
import com.kb.timetable.service.WeekDayService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;


public class WeekDayServiceTest {
    WeekDay weekDay;
    static WeekDayService weekDayService;
    static Map<String,Integer> remainingPeriods;

    TimeTableService timeTableService;


    @BeforeClass
    public static void setupServices(){
        weekDayService = new WeekDayServiceImpl();
        remainingPeriods= new HashMap<>();
        remainingPeriods.put("Subject 1",10);
        remainingPeriods.put("Subject 2",10);
        remainingPeriods.put("Subject 3",10);
    }

    @Before
    public void setUpCase(){
        timeTableService=mock(TimeTableServiceImpl.class);
        when(timeTableService.getRemainingPeriods()).thenReturn(remainingPeriods);
        weekDay = new WeekDay(0);
    }

    @Test
    public void testWeekDay(){
        assertThat(weekDay.getSchedule(),hasSize(0));
        assertThat(weekDay.getDay(),equalToIgnoringCase("monday"));
    }
    @Test
    public void testAddPeriodToDay(){
        String subject="Subject 1";
        weekDayService.addPeriodToDay(weekDay,subject,timeTableService.getRemainingPeriods());
        assertThat(weekDay.getSchedule(),hasItem(subject));
        assertThat(weekDay.getSchedule(),hasSize(1));
    }

    @Test
    public void testFillDay(){
        weekDayService.fillDay(weekDay.getSchedule(),timeTableService.getRemainingPeriods());
        assertThat(weekDay.getSchedule(),hasSize(5));
        assertThat(weekDay.getSchedule(),not(hasItem("")));
    }
    @Test
    public void testFillDayWithMore(){
        timeTableService.getRemainingPeriods().put("Subject 4",10);
        timeTableService.getRemainingPeriods().put("Subject 5",10);
        timeTableService.getRemainingPeriods().put("Subject 6",10);
        weekDayService.fillDay(weekDay.getSchedule(),timeTableService.getRemainingPeriods());
        assertThat(weekDay.getSchedule(),hasSize(5));
        assertThat(weekDay.getSchedule(),not(hasItem("")));
    }

}
