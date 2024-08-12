## Build tools & versions used
- Kotlin: 1.9.0
- Gradle: 8.7
- Jetpack Compose: 2.8.4
- Material UI: 1.6.8
- Retrofit: 2.9.0
- Okhttp: 4.11.0
- Serialization: 2.0.0
- Junit: 4.13.2
- Mockito: 5.12.0
- Mockk: 1.13.12
- Coil: 2.4.0

Target Android Nougat


## Steps to run the app
1. Load app in Android Studio
2. Gradle build
3. Run app


## What areas of the app did you focus on?
I built a full-stack application using design principles learned in this Android Developer course on Jetpack Compose
https://developer.android.com/courses/android-basics-compose/course

This was a path I am familiar with for building a new application quickly. I could look at any pieces that I needed with example code if I needed a refresher along the way.


## What was the reason for your focus? What problems were you trying to solve?
Wanted to continue to explore new languages and frameworks in the Android world. I worked with Jetpack Compose last year on a project and I really like declarative languages. Retrofit makes interacting with APIs clean and simple, you always know the data that will be returned. 


## How long did you spend on this project?
2-3 hours: Basic functionality together. Ability fetch employee list and display it on the application
2 hours: UI/UX improvements including image loading/caching, pull to refresh, better card styling
2 hours: implement unit testings
1 hour: write-up

Around 7-8 hours total.


## Did you make any trade-offs for this project? What would you have done differently with more time?
I continued to follow the best practices taught in the above Android course. The course recommends pulling state controlled elements into a UI state variable. The longer I've worked with Jetpack Compose, the harder I find to follow this best practice. Most example code uses StateFlow (which was the original implementation of state management in compose) and it can be more challenging to take tutorials and apply it to the project. 

In order to implement pull-to-refresh, I ended up bringing in another variable and placing the loading screen into the results screen to get desired UX behavior. Had I known that when I first started the project, I would have abandoned my UIState interface and gone with a classic implementation.


## What do you think is the weakest part of your project?
The testing element. I went through the Android course again and followed their approach for dependency injection with testing. I'm more familiar with mockito so tests ended up being a blend of both for time sake.

More of a weak area for me. The build scripting. This is my first time using Kotlin DSL, I'm more familiar with Groovy. I saw that it was the new preferred way to implement your application and learned a bit about it along the way.

This project is using experimental apis and jetpack compose is still a relatively new language. This risk could require major rewriting of frontend pieces into the future.


## Did you copy any code or dependencies? Please make sure to attribute them here!
https://developer.android.com/courses/android-basics-compose/course
https://medium.com/@anandgaur22/jetpack-compose-pull-to-refresh-fafb4d1a5ea6
https://developer.android.com/build/migrate-to-kotlin-dsl


## Is there any other information you’d like us to know?
Fun project to work on. Excited to chat with the team.

