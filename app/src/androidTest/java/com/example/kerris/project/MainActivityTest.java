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


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void adminCreateIsInvalid(){
        onView(withId(R.id.usernamebox)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.passwordbox)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.create)).perform(click());
        onView(withText("The user already exists. Please create again.")).check(matches(isDisplayed()));
    }

    @Test
    public void adminLoginIsInvalid(){
        onView(withId(R.id.usernamebox)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.passwordbox)).perform(typeText("admin"),closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        onView(withText("Welcome admin! You are logged as Administration")).check(matches(isDisplayed()));
    }
}
