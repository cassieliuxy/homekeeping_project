package com.example.kerris.project;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

public class HOSearchTimeTest {
/*
    @Rule
    public ActivityTestRule<HoSearchActivity> HomeownerSearchActivityTestRule = new ActivityTestRule<>(HoSearchActivity.class);
*/
    @Rule
    public ActivityTestRule<HomeownerActivity> HomeownerActivityTestRule = new ActivityTestRule<>(HomeownerActivity.class);



    @Test
    public void pastTimeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Monday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("11")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());

        onData(anything()).inAdapterView((withId(R.id.hosearchlist))).atPosition(1).perform(click());

        onView(withText("Invalid time.Please reselect.")).check(matches(isDisplayed()));
    }

    @Test
    public void futureTimeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("15")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());

        onData(anything()).inAdapterView((withId(R.id.hosearchlist))).atPosition(1).perform(click());

        onView(withText("Book service successfully.")).check(matches(isDisplayed()));
    }

   @Test
    public void repeatedFutureTimeIsInvalid() {
        onView(withId(R.id.hoday)).perform(click());
        onData(is("Sunday")).perform(click());
        onView(withId(R.id.hostarttime)).perform(click());
        onData(is("13")).perform(click());
        onView(withId(R.id.hoendtime)).perform(click());
        onData(is("15")).perform(click());
        onView(withId(R.id.hosearch)).perform(click());

        onData(anything()).inAdapterView((withId(R.id.hosearchlist))).atPosition(2).perform(click());

        onView(withText("Invalid time.Please reselect.")).check(matches(isDisplayed()));
    }

    @Test
    public void emptyCommentIsInvalid() {

        onView(withId(R.id.hoview)).perform(click());
        onData(anything()).inAdapterView((withId(R.id.hoviewlist))).atPosition(0).perform(click());

        onView(withId(R.id.rateho)).perform(click());
        onData(is("5")).perform(click());

        onView(withId(R.id.button4)).perform(click());

        onView(withText("Invalid comment.")).check(matches(isDisplayed()));
    }

    @Test
    public void commentIsInvalid() throws InterruptedException {

        onView(withId(R.id.hoview)).perform(click());
        onData(anything()).inAdapterView((withId(R.id.hoviewlist))).atPosition(1).perform(click());

        onView(withId(R.id.rateho)).perform(click());
        onData(is("4")).perform(click());

        onView(withId(R.id.hocomment)).perform(typeText("Good job."), closeSoftKeyboard());

        onView(withId(R.id.button4)).perform(click());

        onView(withText("Submit successfully.")).check(matches(isDisplayed()));
    }




}
