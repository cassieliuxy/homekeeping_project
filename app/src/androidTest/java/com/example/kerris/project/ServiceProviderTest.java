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

public class ServiceProviderTest {

    @Rule
    public ActivityTestRule<ProviderActivity> serviceProviderActivityActivityTestRule = new ActivityTestRule<>(ProviderActivity.class);

    @Test
    public void addressIsInvalid() {
        onView(withId(R.id.phonebox)).perform(typeText("9876543210"), closeSoftKeyboard());
        onView(withId(R.id.companybox)).perform(typeText("QwQ"), closeSoftKeyboard());
        onView(withId(R.id.descripbox)).perform(typeText(" I have 10 years of experience fixing washers"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withText("Invalid address.")).check(matches(isDisplayed()));
    }

    @Test
    public void phoneIsInvalid() {
        onView(withId(R.id.addbox)).perform(typeText("150 Laurier Ave"), closeSoftKeyboard());
        onView(withId(R.id.companybox)).perform(typeText("QoQ"), closeSoftKeyboard());
        onView(withId(R.id.descripbox)).perform(typeText(" I have no experience"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withText("Invalid phone number.")).check(matches(isDisplayed()));
    }

    @Test
    public void submitIsInvalid() {
        onView(withId(R.id.addbox)).perform(typeText("200 Lees Ave"), closeSoftKeyboard());
        onView(withId(R.id.phonebox)).perform(typeText("1234567890"), closeSoftKeyboard());
        onView(withId(R.id.companybox)).perform(typeText("QAQ"), closeSoftKeyboard());
        onView(withId(R.id.descripbox)).perform(typeText(" I have 5 years of experience fixing dryers"), closeSoftKeyboard());
        onView(withId(R.id.submit)).perform(click());
        onView(withText("Submit successfully.")).check(matches(isDisplayed()));
    }
}
