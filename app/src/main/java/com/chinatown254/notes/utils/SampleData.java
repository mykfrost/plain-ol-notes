package com.chinatown254.notes.utils;

import com.chinatown254.notes.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_TEXT_1 = "A simple note";
    private static final String SAMPLE_TEXT_2 = "A note with a \n line feed";
    private static final String SAMPLE_TEXT_3 = "In our project, we want to highlight the job that is clicked and also display information about the\n" +
            "job in a JobDrawerComponent to the right. This component will be used in the App component so\n" +
            "sharing the job data with it might be difficult for a Job component instance using just props.\n" +
            "Sometimes it so happens that a component is deep inside hierarchy and we want to share its data\n" +
            "with a component elements much further away from it";

    private static Date getDate(int diff){
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND , diff);
        return cal.getTime();
    }

    public static List<NoteEntity>  getNotes(){
        List<NoteEntity> notes = new ArrayList<>();
        notes.add(new NoteEntity( getDate(0),SAMPLE_TEXT_1));
        notes.add(new NoteEntity( getDate(-1),SAMPLE_TEXT_2));
        notes.add(new NoteEntity( getDate(-2),SAMPLE_TEXT_3));
        return notes;
    }
}
