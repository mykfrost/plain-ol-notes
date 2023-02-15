package com.chinatown254.notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.utils.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public List<NoteEntity> mNotes = SampleData.getNotes();
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
