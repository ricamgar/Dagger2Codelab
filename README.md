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

As Dagger2 injection is based on annotations, we need to include the javax.annotation dependency as well

```groovy
compile 'org.glassfish:javax.annotation:10.0-b28'
```

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

In this section we will create the scope ``@ApplicationScope`` to create instances which will live as long as the Application object. This Scope is similar to use the ``@Singleton`` annotation.

We will extend the scopes mechanism on Part 5.

To create a Scope we need to define an Interface. We will create it under ``d/scopes`` directory.

```java
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
```

Now that we created our first scope, let´s use it in our components.

### Part 3 - Create Modules and Components

### Part 4 - Inject services

### Part 5 - Extend Scopes

### Part 6 - Testing


[apt]: https://bitbucket.org/hvisser/android-apt