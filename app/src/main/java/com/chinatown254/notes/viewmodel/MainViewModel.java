package com.chinatown254.notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.chinatown254.notes.database.AppRepository;
import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.utils.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public LiveData< List<NoteEntity>> mNotes ;
    private AppRepository mRepository;
    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotes = mRepository.mNotes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }
}
