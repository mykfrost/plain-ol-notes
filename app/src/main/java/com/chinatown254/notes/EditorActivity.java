package com.chinatown254.notes;

import static com.chinatown254.notes.utils.Constants.NOTE_ID_KEY;

import android.os.Bundle;

import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.viewmodel.EditorViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chinatown254.notes.databinding.ActivityEditorBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {
 @BindView(R.id.note_text)
    TextView mTextview;
    private EditorViewModel mViewModel;
    private ActivityEditorBinding binding;
    private boolean mNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_check);
        ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initViewModel();

    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(EditorViewModel.class);
        mViewModel.mLiveNotes.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                if (noteEntity != null) {
                    mTextview.setText(noteEntity.getText());
                }

            }
        });

         Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Note");
            mNewNote = true;
        }else {
            setTitle("Edit Note");
            int noteid = extras.getInt(NOTE_ID_KEY);
            mViewModel.loadData(noteid);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveAndReturn();
    }

    private void saveAndReturn() {
        mViewModel.saveNote(mTextview.getText().toString());
        finish();
    }
}