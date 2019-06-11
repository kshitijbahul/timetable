package com.kb.timetable.service;

import java.util.Map;

public interface TimeTableService {
    void fillTimeTable(Integer daysToExams, Map<String,Integer> periods);
    Map<String,Integer> getRemainingPeriods();
    void printTimeTable();
}
