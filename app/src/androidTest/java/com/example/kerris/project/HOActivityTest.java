package com.example.kerris.project;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

public class HOActivityTest {
    @Rule
    public ActivityTestRule<HomeownerActivity> HomeownerActivityTestRule = new ActivityTestRule<>(HomeownerActivity.class);

    @Test
    public void timeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("11")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());
    }

    @Test
    public void serviceNameIsInvalid() {
        onView(withId(R.id.hoservicenamebox)).perform(typeText("Pest Control"), closeSoftKeyboard());
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("15")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("17")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());
    }

    @Test
    public void rateIsInvalid() {
        onView(withId(R.id.horatingbox)).perform(typeText("80"), closeSoftKeyboard());
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("15")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());
    }

    @Test
    public void unusableTimeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("11")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());
        onView(withText("Error. The start time cannot exceed the end time. Please choose again.")).check(matches(isDisplayed()));
    }

    @Test
    public void equalTimeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());
        onView(withText("Error. The start time cannot be equal to the end time.Please choose again.")).check(matches(isDisplayed()));
    }

}
