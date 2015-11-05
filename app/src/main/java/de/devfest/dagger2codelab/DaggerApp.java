package de.devfest.dagger2codelab;

import android.app.Application;

import de.devfest.dagger2codelab.di.components.ApplicationComponent;
import de.devfest.dagger2codelab.di.components.ApplicationComponent.Initializer;
import de.devfest.dagger2codelab.di.modules.ApplicationModule;

public class DaggerApp extends Application {

	private ApplicationComponent applicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		applicationComponent = Initializer.init(new ApplicationModule(this));
	}

	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}

	public void setApplicationComponent(ApplicationComponent applicationComponent) {
		this.applicationComponent = applicationComponent;
	}
}
