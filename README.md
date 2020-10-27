# Android w/o DI sample code
This is a working android app showing sample code from a presentation I held at DevFest Sweden 2020-10.16

"Android architecture has come a long way on standards and best practices. Especially near the UI. I don’t see many apps that don’t use ViewModels and LiveData to simplify the code. But one step further from the UI, where the business logic resides there are almost as many implementations as there are applications. Still there seem to be a general idea that dependency injection should be the golden way, but I don’t see it, not yet at least. The popular frameworks have been around long and haven’t convinced so far. As I am a big fan of reactive and loosely coupled systems I have an alternative to share; Actors. Let me show you my approach to Android Architecture with Actors and why I believe this is the way to move forward."

- Link here https://devfest.se/schedule/2020-10-16?sessionId=205
- Link to presentation https://speakerdeck.com/bobdahlberg/o-di
- link to the article https://bob-dahlberg.medium.com/android-w-o-di-ff8030ece993

## Concept of the Actor architecture

With the MVVM architecture as a starting point it's now the following setup:

- View -> MainFragment 
- ViewModel -> MainViewModel 
- Stream -> AppStream 
- Actor -> UserActor, LogActor 

## Flow 
- The View calls a function on the ViewModel 
- The ViewModel sends messages over AppStream 
- The AppStream broadcasts the message to everyone listening 
- The Actor listens on the AppStream for specific messages 
- The Actor performs a task to fulfill the intent of the message 
- The Actor sends its new State to the AppStream 
- The AppStream broadcasts the State to anyone listening 
- The ViewModel listens for the State and emits it on its LiveData 
- The View observes the ViewModels LiveData to update the UI 
