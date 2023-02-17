package com.chinatown254.notes;

import android.content.Intent;
import android.os.Bundle;

import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.ui.NotesAdapter;
import com.chinatown254.notes.utils.SampleData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chinatown254.notes.databinding.ActivityNewMainBinding;
import com.chinatown254.notes.viewmodel.MainViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityNewMainBinding binding;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerview;
@OnClick(R.id.fab)
void fabClickHandler(){
    Intent intent = new Intent(this , EditorActivity.class);
    startActivity(intent);
}

private List<NoteEntity> notesData = new ArrayList<>();
private NotesAdapter mAdapter;

private MainViewModel mViewModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<NoteEntity>> notesObserver = new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                notesData.clear();
                notesData.addAll(noteEntities);
                if (mAdapter == null) {
                    mAdapter = new NotesAdapter(notesData, NewMainActivity.this);
                    mRecyclerview.setAdapter(mAdapter);
                }else {
                    mAdapter.notifyDataSetChanged();
                }

            }
        };

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.mNotes.observe(this , notesObserver);
    }

    private void initRecyclerView() {
        mRecyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.add_sample_data){
            addSampleData();
            return true;
        }else if(id == R.id.action_delete_all){
            deleteAllNotes();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        mViewModel.deleteAllNotes();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}