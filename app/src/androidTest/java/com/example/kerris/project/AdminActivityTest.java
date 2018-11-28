package com.example.kerris.project;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AdminActivityTest {
    @Rule
    public ActivityTestRule<AdminActivity> adminActivityActivityTestRule = new ActivityTestRule<>(AdminActivity.class);


    @Test
    public void addNameRateIsInvalid(){
        onView(withId(R.id.add)).perform(click());
        onView(withText("Invalid servicename and rate.")).check(matches(isDisplayed()));
    }

    @Test
    public void addServiceNameIsInvalid(){
        onView(withId(R.id.ratebox)).perform(typeText("100"),closeSoftKeyboard());
        onView(withId(R.id.add)).perform(click());
        onView(withText("Invalid servicename.")).check(matches(isDisplayed()));
    }

    @Test
    public void upgradeServiceNameIsInvalid() {
        onView(withId(R.id.servicebox)).perform(typeText("Moving"),closeSoftKeyboard());
        onView(withId(R.id.update)).perform(click());
        onView(withText("Invalid rate.")).check(matches(isDisplayed()));
    }

    @Test
    public void addRateIsInvalid(){
        onView(withId(R.id.servicebox)).perform(typeText("Moving"),closeSoftKeyboard());
        onView(withId(R.id.ratebox)).perform(typeText("100"),closeSoftKeyboard());
        onView(withId(R.id.add)).perform(click());
        onView(withText("Create service successfully.")).check(matches(isDisplayed()));
        onView(withId(R.id.view)).perform(click());
        /*onView(withId(R.id.add)).perform(click());
        onView(withText("The service already exists. Please add again or change the rate click UPDATE.")).check(matches(isDisplayed()));*/
    }

    @Test
    public void updateRateIsInvalid(){
        onView(withId(R.id.servicebox)).perform(typeText("Moving"),closeSoftKeyboard());
        onView(withId(R.id.ratebox)).perform(typeText("150"),closeSoftKeyboard());
        onView(withId(R.id.update)).perform(click());
        onView(withText("Change rate successfully.")).check(matches(isDisplayed()));
        onView(withId(R.id.view)).perform(click());
    }

    @Test
    public void clearIsInvalid(){
        onView(withId(R.id.servicebox)).perform(typeText("Moving"),closeSoftKeyboard());
        onView(withId(R.id.delete)).perform(click());
        onView(withText("Delete service successfully.")).check(matches(isDisplayed()));
        onView(withId(R.id.view)).perform(click());
    }





}
