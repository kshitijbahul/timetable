package com.kb.timetable.service.impl;

import com.kb.timetable.entities.WeekDay;
import com.kb.timetable.service.WeekDayService;


import java.util.List;
import java.util.Map;

public class WeekDayServiceImpl implements WeekDayService {


    String getAnotherSubject(String subject,Map<String,Integer> remainingPeriods){
        return remainingPeriods.entrySet().stream().filter((eachPeriod)-> (eachPeriod.getValue()!=0) && !eachPeriod.getKey().equalsIgnoreCase(subject)).map(x->x.getKey()).findFirst().orElse("");
    }
    void updateSubjectCount(String subject,Map<String,Integer> remainingPeriods) throws NullPointerException{
        if (remainingPeriods.get(subject)==1){
            remainingPeriods.remove(subject);
        }else
            remainingPeriods.put(subject,remainingPeriods.get(subject)-1);
    }
    public void fillDay(List<String> daySchdule,Map<String,Integer> remainingPeriods){
        try{
            while(daySchdule.size()!=5 && remainingPeriods.size()!=0){
                if (daySchdule.size()!=0) {
                    String subjectToBeAdded= getAnotherSubject(daySchdule.get(daySchdule.size()-1),remainingPeriods);
                    if (subjectToBeAdded.isEmpty()){
                        break;
                    }
                    updateSubjectCount(subjectToBeAdded,remainingPeriods);
                    daySchdule.add(subjectToBeAdded);
                }
                else{
                    fillNewDay(daySchdule,remainingPeriods);
                }
            }
        }catch(NullPointerException |ArrayIndexOutOfBoundsException ex){
                System.out.println("Skipped the Day. Error is "+ex.getMessage());
                ex.printStackTrace();
        }

    }
    void fillNewDay(List<String> daySchedule,Map<String,Integer> remainingPeriods){
        String subject=remainingPeriods.entrySet().stream().map((eachSubject)->eachSubject.getKey()).findFirst().orElse("");
        if (!subject.isEmpty()){
            updateSubjectCount(subject,remainingPeriods);
            daySchedule.add(subject);
        }

    }

    public Boolean addPeriodToDay(WeekDay day, String subjectName, Map<String,Integer> remainingPeriods){
        try{
            if (day.getSchedule().size()==0){
                day.getSchedule().add(subjectName);
                updateSubjectCount(subjectName,remainingPeriods);
                return Boolean.TRUE;
            }else if (day.getSchedule().size()<5 && !day.getScheduleForASlot(day.getSchedule().size()-1).equalsIgnoreCase(subjectName)){
                day.getSchedule().add(subjectName);
                updateSubjectCount(subjectName,remainingPeriods);
                return Boolean.TRUE;
            }else
                return Boolean.FALSE;
        }catch(NullPointerException |ArrayIndexOutOfBoundsException ex){
            System.out.println("Skipped addPeriodToDay for day "+day+" Error is "+ex.getMessage());
            ex.printStackTrace();
            return Boolean.FALSE;
        }


    }
}
