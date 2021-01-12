# RedditApp

This app demonstrates how to manage a list of items with a backend API (in this
case [Reddit API](https://www.reddit.com/dev/api/#listings))

This app load posts from Reddit until it reaches the maximum of 50 posts. The app allows to dismiss one post at a time or dismiss all post at once. It demonstrates how to utilize the `after` key in the response to discover the next page to be loaded.

The app, implemented in the [RemotePostToLocalDbRepository](app/src/main/java/com/reddit/app/data/repository/RemotePostToLocalDbRepository.kt) class, demonstrates how to set up a Repository that will use the local database to page in data for the UI and also back-fill the database from the network as the user reaches to the end of the data in the database, using [Room](https://developer.android.com/training/data-storage/room) for storage.

This usually provides the best user experience as the cached content is always available on the device and the user will still have a good experience even if the network is slow / unavailable.


### Dependency Injection
The app component dependencies are injected with [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack). Hilt is the next generation dependency injection framework from Google. It's built on top of Dagger and simplifies DI greatly.


### Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [Mockito][mockito] for mocking in tests
* [Dexter][dexter] for requesting permissions at runtime.
* [Coil][coil] for image downloading.


[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[Mockito]: http://site.mockito.org
[Dexter]: https://github.com/Karumi/Dexter
[Coil]: https://github.com/coil-kt/coil
