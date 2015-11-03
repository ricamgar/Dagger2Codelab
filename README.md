# Dagger2 Codelab

### Introduction
This codelab is intended to be the first step to implement Dagger2 dependency injection framework to your project.

As an example we created a simple project which is using the GitHub API to get users and their repositories. Clone it and let´s start!

### Part 1 - Dagger2 Configuration
The first step is to add the Dagger-2.0.1 dependency and the Dagger-Compiler-2.0.1 for the code generation to our build.gradle file:

```groovy
compile 'com.google.dagger:dagger:2.0.1'
apt 'com.google.dagger:dagger-compiler:2.0.1'
```

As the Dagger2 compiler is based on annotations, we need to provide the javax.annotation dependency as well.
Note: they will not be available on runtime!

```groovy
provided 'org.glassfish:javax.annotation:10.0-b28'
```

Since the compiler requires to read
And to make the compiler work, we need to add the [apt plugin][apt]

```groovy
apply plugin: 'com.neenbedankt.android-apt'

dependencies {
		...
		classpath 'com.neenbedankt.gradle.plugins:android-apt:1.4'
	}
```

Sync the project and, if everything works, go to the next Part!

### Part 2 - Create Scope

Scopes in Dagger2 is the mechanism to keep single instances of classes as long as their scope exist.

In this section we will create the scope `ApplicationScope` to create instances which will live as long as the Application object (singleton).
To create a Scope we need to define an Annotation. We will create it under `di/scopes` directory.
We could use the `Singleton` Annotation as well (since it's the same implementation), but for readability and the `learning-factor` we create our own.

```java
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
```

Now that we created our first scope, let´s use it in our components.

### Part 3 - Create Modules and Components

Modules and Components are the main elements in Dagger2.

Modules are classes to provide dependencies. To create a module we need to annotate a class with `@Module`. We can create it on the `di/modules` directory. In the module, we can Provide the dependencies we need. For that we will use the `@Provides` annotation and a Scope in which this dependency is available (in our simple case this is ApplicationScope).

```java
	@ApplicationScope
	@Provides
	GitHubApi provideGitHubApi(){
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(context.getString(R.string.endpoint))
				.build();
		return restAdapter.create(GitHubApi.class);
	}
```

The Components are the injectors, the link between the `@Module` and the `@Inject`. To create a Component we need to annotate an interface with `@Component` and list the `@Modules` we want to use within the application. We can create it on `di/components`

```java
@Component(...)
public interface ApplicationComponent {

	Context getApplicationContext();

	GitHubApi getGitHubApi();
}
```

And now we need to link them together. We will add the Applcation Module to the `@Component` annotation.

```java
@Component(modules = ApplicationModule.class)
```
Ok, we have Modules, Components and they are linked together but, how to use them? Let´s inject!

### Part 4 - Inject services

We are going to inject the just created dependencies to our Activities. For that, we will use the `@Inject` annotation at the member, we want to inject.

```java
	@Inject
	GitHubApi service;
```

Note that we removed the `private` modifier. The fields must not be `private` to be accessible within the generated code Dagger.

But that´s not all. We need to tell Dagger, that our Activity is using our dependency graph. To do so, we create an `inject` method in our component, which will be used in the Activity.

```java
void inject(MainActivity activity);
```
We need now to initialize the Application Component. We are going to initialize it on the Application class using a helper class.

```java
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

	// your component stuff
	...

	final class Initializer {

		private Initializer(){}

		public static ApplicationComponent init(ApplicationModule applicationModule) {
			return DaggerApplicationComponent.builder()
					.applicationModule(applicationModule)
					.build();
		}
	}
```
Now, rebuild the Project (Build=>Rebuild Project). This triggers apt to read the annotations and the dagger compiler to generate code.

We can then use this helper to initialize the Application Component in our Application class `onCreate()`.

```java
ApplicationComponent.Initializer.init(new ApplicationModule(this)).inject(this);
```

Then, on each activity we can get the Application Component and inject the activity to the dependency graph.

```java
((DaggerApp)getApplication()).getApplicationComponent().inject(this);
```

Now build, run and enjoy!


### Part 5 - Testing


[apt]: https://bitbucket.org/hvisser/android-apt