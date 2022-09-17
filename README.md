# Marvel Android App

<p align="center">
This app demonstrates Android development with Hilt, Coroutines, Flow, Jetpack (compose, ViewModel,paging 3), and Material Design based on MVVM architecture.
</p>

<p align="center">
<img src="https://i.imgur.com/3Ae8xpW.jpg"/>
</p>

## Functionality
This app shows marvel characters and clicking on a particular character shows their following details :
 - Series
 - Events
 - Comics
 - Stories

## Download
Go to the [Google Drive Link](https://bit.ly/3BJ8xPZ) to download the debug APK.

## Architecture
App is based on the MVVM architecture and the Repository pattern.

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
  - Lifecycle - Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - Paging3  - Manages seemless data loading and caches data in viewmodel scope
  - Navigation - For screen navigation and data passing
  - Compose   - For designing the composable UI

## Unit Tests
- Paging3 Unit test
- ViewModel Unit Test with Fake Repository is also present in the app.

## Open API
App is using the [MarvelAPI](https://developer.marvel.com/) for constructing RESTful API.<br>
MarvelAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Marvel characters.

## Notes and Improvements
- Adding UI testing using Espresso.
- Adding Character Data Transfer Object and persisting the data in Room
