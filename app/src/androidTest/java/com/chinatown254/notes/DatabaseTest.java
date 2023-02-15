package com.chinatown254.notes;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import com.chinatown254.notes.database.AppDatabase;
import com.chinatown254.notes.database.NoteDao;
import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.utils.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.*;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class DatabaseTest {

    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private NoteDao mDao;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context ,AppDatabase.class).build();
        mDao = mDb.noteDao();
        Log.i(TAG, "createDb: I created the Test database");
    }

    @After
    public void closeDb(){
        mDb.close();
        Log.i(TAG, "closeDb: Closed the database");
    }

    @Test
    public void createAndRetrieveNotes(){
        mDao.insertAll(SampleData.getNotes());
        int count = mDao.getCount();
        Log.i(TAG, "createAndRetrieveNotes:COUNT = " +count);
        assertEquals(SampleData.getNotes().size(), count);
    }

    @Test
    public void compareStrings(){
        mDao.insertAll(SampleData.getNotes());
        NoteEntity original = SampleData.getNotes().get(0);
        NoteEntity fromDb = mDao.getNoteById(1);
        assertEquals(original.getText(), fromDb);
    }
}
