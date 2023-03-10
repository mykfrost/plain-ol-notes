package com.chinatown254.notes.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.chinatown254.notes.database.AppRepository;
import com.chinatown254.notes.database.NoteEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class EditorViewModel extends AndroidViewModel {
    public MutableLiveData <NoteEntity> mLiveNotes = new MutableLiveData<>();

    private Executor executor = Executors.newSingleThreadExecutor();
    private AppRepository mRepository ;
    public EditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }

    public void loadData(int noteid) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                NoteEntity note = mRepository.getNoteById(noteid);
                mLiveNotes.postValue(note);
            }
        });
    }

    public void saveNote(String noteText) {
        NoteEntity note = mLiveNotes.getValue();
        if (note == null) {
            if (TextUtils.isEmpty(noteText.trim())){
                return;
            }else{
                note = new NoteEntity(new Date() ,noteText.trim());
            }

        }else{
            note.setText(noteText.trim());
        }
        mRepository.insertNote(note);
    }

    public void deleteNote() {
        mRepository.deleteNote(mLiveNotes.getValue());
    }
}
