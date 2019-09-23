package com.viaplay.view;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.viaplay.R;
import com.viaplay.activities.SectionDetailActivity;
import com.viaplay.livedata.SectionPreview;

public class SectionListItemClickListener {

    public void onSectionClickedListener(View view, SectionPreview preview){
        Context context = view.getContext();

        Intent intent = new Intent(context, SectionDetailActivity.class);
        intent.putExtra("preview", preview);

        View logo = view.findViewById(R.id.section_logo);
        ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity) context,
                        logo, "sectionLogoTransition");
        context.startActivity(intent, activityOptions.toBundle());
    }
}
