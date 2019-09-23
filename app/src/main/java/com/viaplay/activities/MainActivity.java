package com.viaplay.activities;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.viaplay.databinding.ActivityMainBinding;
import com.viaplay.viewmodel.ViaPlaySectionsViewModel;
import com.viaplay.R;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViaPlaySectionsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setSectionModel(viewModel);

        viewModel.getSectionList().observe(this, new Observer<List<com.viaplay.livedata.SectionPreview>>() {
            @Override
            public void onChanged(@Nullable List<com.viaplay.livedata.SectionPreview> viaPlaySectionListLiveData) {
                RecyclerView sectionList = (RecyclerView) findViewById(R.id.section_list);
                sectionList.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                sectionList.setAdapter(new com.viaplay.view.ViaplayٰRecyclerViewAdapter(viaPlaySectionListLiveData, MainActivity.this));
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.main_activity_title);

    }

    private void refreshList() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.refresh){
            viewModel.refreshList();
        }
        return super.onOptionsItemSelected(item);
    }
}
