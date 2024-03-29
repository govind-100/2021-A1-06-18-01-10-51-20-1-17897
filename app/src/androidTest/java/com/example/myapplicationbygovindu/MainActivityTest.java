package com.example.myapplicationbygovindu;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class MainActivityTest extends TestCase {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public ActivityScenarioRule<MainActivity2> activityRule2 = new ActivityScenarioRule<MainActivity2>(MainActivity2.class);

    private String message = "text Message";

    private String str = "12345";
    private String str1 = "email";

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testUserInputScenario() throws InterruptedException {

            // a) Upon entering some text and clicking the Send button, the second activity class
            //should receive an Intent and the user entered text.
            Espresso.onView(withId(R.id.editText)).perform(typeText(message));
            Espresso.closeSoftKeyboard();
            Espresso.onView(withId(R.id.main)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.send)).perform(click());
            Espresso.onView(withId(R.id.secondActivity)).check(matches(isDisplayed()));

            // b) In the second activity, upon pressing the Back button, the first activity should be
            //displayed and the user provided text should be set in the text field.
            Espresso.pressBack();
            Espresso.onView(withId(R.id.main)).check(matches(isDisplayed()));

            // c) The user provided voice input should be correctly entered in the text field.
            String voiceText = Espresso.onView(withId(R.id.voice)).perform(click()).toString();
            Espresso.onView(withId(R.id.editText)).perform(typeText(voiceText));

    }

    @Test
    public void userTest2(){
        Espresso.onView(withId(R.id.editText)).perform(typeText(message));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.main)).check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.send)).perform(click());
        Espresso.onView(withId(R.id.secondActivity)).check(matches(isDisplayed()));


        Espresso.onView(withId(R.id.p_e)).perform(typeText(str1));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.secondActivity)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.confirm)).perform(click());
    }


    }




