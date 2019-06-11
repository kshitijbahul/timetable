package com.kb.timetable.service;

import com.kb.timetable.entities.WeekDay;

import java.util.List;
import java.util.Map;

public interface WeekDayService {
    void fillDay(List<String> daySchdule, Map<String,Integer> remainingPeriods);
    Boolean addPeriodToDay(WeekDay day, String subjectName,Map<String,Integer> remainingPeriods);
}
