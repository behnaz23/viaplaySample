package com.viaplay.activities;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.viaplay.R;
import com.viaplay.databinding.SectionDetailBinding;
import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;
import com.viaplay.viewmodel.ViaplaySectionDetailViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SectionDetailActivity extends AppCompatActivity {

    @Inject
    ViaplaySectionDetailViewModel viaplaySectionDetailViewModel;

    SectionPreview sectionPreview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        sectionPreview = (SectionPreview) getIntent().getSerializableExtra("preview");

        final SectionDetailBinding detailBinding = DataBindingUtil.setContentView(this, R.layout.section_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viaplaySectionDetailViewModel.getSectionDetailLiveData(sectionPreview)
                .observe(this, new Observer<SectionDetail>() {
                    @Override
                    public void onChanged(@Nullable SectionDetail sectionDetail) {
                        detailBinding.setItem(sectionDetail);
                        detailBinding.executePendingBindings();
                    }
                });
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
            viaplaySectionDetailViewModel.refreshDetail(sectionPreview);
        }
        return super.onOptionsItemSelected(item);
    }
}
