package com.example.kerris.project;

import android.support.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

public class SPChooseTest {
    @Rule
    public ActivityTestRule<ProviderChooseActivity> chooseActivityActivityTestRule = new ActivityTestRule<>(ProviderChooseActivity.class);

    @Test
    public void chooseIsInvalid() {
        //choose item
        onView(withId(R.id.spinnerserv)).perform(click());
        onData(is("Window Cleaning")).perform(click());
        onView(withId(R.id.addservice)).perform(click());
        onView(withText("Choose service successfully.")).check(matches(isDisplayed()));

        //choose time
        onView(withId(R.id.spinnerday)).perform(click());
        onData(is("Friday")).perform(click());
        onView(withId(R.id.spinnerstart)).perform(click());
        onData(is("14")).perform(click());
        onView(withId(R.id.spinnerend)).perform(click());
        onData(is("16")).perform(click());
        onView(withId(R.id.addtime)).perform(click());
        onView(withText("Choose time successfully.")).check(matches(isDisplayed()));
    }
}
