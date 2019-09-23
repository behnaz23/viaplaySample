package com.viaplay;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.viaplay.activities.MainActivity;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true);

    @Before
    public void setup() {
        Context application = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void onlineTest() {
        onView(withId(R.id.section_list)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                RecyclerView recyclerView = (RecyclerView) view;
                int numberOfSections = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();

                assertThat(recyclerView.getAdapter().getItemCount(), not(equalTo(0)));
            }
        });
    }

}
