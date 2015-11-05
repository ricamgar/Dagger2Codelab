package de.devfest.dagger2codelab;


import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.devfest.dagger2codelab.di.components.ApplicationComponent;
import de.devfest.dagger2codelab.di.components.DaggerApplicationComponent;
import de.devfest.dagger2codelab.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

	@Rule
	public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class, true, false);

	@Test
	public void testResponse() throws Exception {
		ApplicationComponent applicationComponent = DaggerApplicationComponent
				.builder()
				.applicationModule(new TestApplicationModule(null))
				.build();

		Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
		((DaggerApp)instrumentation.getTargetContext().getApplicationContext()).setApplicationComponent(applicationComponent);

		activityTestRule.launchActivity(new Intent());
		Thread.sleep(10000);
	}
}
