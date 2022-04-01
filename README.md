# android.eit.backbase-challenge

## Backbase Code Challenge

[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.6.10-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AndroidStudio-2021.1.1-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-7.0.2-blue?style=flat)](https://gradle.org)

The goal of this application is get Movie data from TheMovie DB API for show the "Now Playing" in a
horizontal list and "Popular" endless vertical list. Also, pressing any item, you can see the
details of the movie.

This project is being maintained to match current industry standards.

# Architecture

* This project was developed using CLEAN Architecture and MVVM (Model View ViewModel) with some
  approaches from MVI (Model View Intent).
* The architecture contains the following features:
    * data:
        * remote: Using Retrofit (REST API) and OkHttp (Security and interceptors)
            * Now Playing Movies: Just consuming REST API without caching.
            * Popular: Consuming REST API using pagination with cache. Also, using Paging library
              with synchronization with Retrofit and Room DB
            * Repository for link to the domain layer
        * local: Using Room DB
            * Logic for create the RoomDB Instance, Entities and Daos.
            * Repository for link to the domain layer

    * domain: Intermediate layer between data and presentation, for have the business logic using
      agnostic use cases for connect to the ViewModels.

    * presentation: This contains the Ui Models (Only needed for the UI) using mappers, and the
      ViewModels

    * ui:
        * list: Contains 2 view components: Now Playing and Popular. Each one have own list for
          that.
        * detail: Simple fragment showing the detail data.

    * di: Means about using Dependency Injection using Dagger Hilt, separating the scopes for any
      layer and the ViewModel.

![package_arch](art/package_arch.png?raw=true)

# Screenshots

## Movie List

![movie_list](art/fragment_list.png?raw=true)

## Movie Detail

![movie_details](art/fragment_details.png?raw=true)

# Testing

This project have 3 kind of tests:

* Unit Tests: Using JUnit 4 and Mockito for check the business logic for ViewModels, 
repositories, models.

* Instrumentation Tests: Using MockWebServer for check the response of the REST API.

* UI Tests: Using Espresso for check the navigation.

# Extra functionalities

* Swipe Refresh Layout in the list
* ic_launcher for Movies logo
* Extra security layer for sensitive data

# Known issues

* REST API is no longer showing "duration" parameter.

* Implement Nested Scroll view for have entire scrolling. Some performance issues with Paging
  library meanwhile is scrolling, freezing the Main thread. Maybe upgrading to Paging 3 should fix
  this.