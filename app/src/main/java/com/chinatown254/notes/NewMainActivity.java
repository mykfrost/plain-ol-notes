package com.chinatown254.notes;

import android.content.Intent;
import android.os.Bundle;

import com.chinatown254.notes.database.NoteEntity;
import com.chinatown254.notes.ui.NotesAdapter;
import com.chinatown254.notes.utils.SampleData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;

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
        initViewModel();
        initRecyclerView();
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//            }
//        });



        notesData.addAll(mViewModel.mNotes);
        for (NoteEntity note :notesData
                ) {
            Log.i("Plain Ol Notes", note.toString());
        }
    }

    private void initViewModel() {

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void initRecyclerView() {
        mRecyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(layoutManager);

        mAdapter = new NotesAdapter(notesData, this);
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_new_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_sample_data){
            addSampleData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}