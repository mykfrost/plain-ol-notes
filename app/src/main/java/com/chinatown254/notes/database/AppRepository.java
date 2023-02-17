package com.chinatown254.notes.database;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.chinatown254.notes.utils.SampleData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppRepository {
    private static  AppRepository ourInstance ;

    public LiveData< List<NoteEntity>> mNotes ;
    private AppDatabase mDb ;
    private Executor executor = Executors.newSingleThreadExecutor();
    public static AppRepository getInstance(Context context){
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context){
        //mNotes = getAllNotes();
        mDb = AppDatabase.getInstance(context);
        mNotes = getAllNotes();

    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertAll(SampleData.getNotes());
            }
        });
    }

    private LiveData<List<NoteEntity>> getAllNotes(){
        return mDb.noteDao().getAll();
    }

    public void deleteAllNotes() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().deleteAll();
            }
        });
    }

    public NoteEntity getNoteById(int noteid) {
        return mDb.noteDao().getNoteById(noteid);
    }

    public void insertNote(NoteEntity note) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.noteDao().insertNote(note);
            }
        });
    }
}

