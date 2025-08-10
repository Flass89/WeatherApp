# Mini Weather App

This is a simple Android application built in around 3 hours that fetches and displays weather data for a given city. It was created as am assignment to demonstrate modern Android development practices.


---

### Decisions & Trade-offs

*   **Technology Choices**:
    *   **UI**: Jetpack Compose was chosen over XML to demonstrate proficiency with modern UI toolkits, fulfilling a bonus requirement from the start. It simplifies UI logic and state management.
    *   **Asynchronicity**: Kotlin Coroutines with `StateFlow` were used. `StateFlow` is ideal for representing UI state that can be observed by Composables.
    *   **Networking**: Retrofit with the Moshi converter was used for its robustness, type-safety, and ease of use in parsing JSON.
    *   **Architecture**: A simplified Clean Architecture approach was implemented (UI -> ViewModel -> Repository -> API). A `UseCase` layer was omitted for this small project to save time, as it would have been a simple pass-through. The repository directly provides a `Flow<Resource<T>>` to the ViewModel.

*   **API Key Storage**: The API key is hardcoded in a constant file. For a production app, this is insecure. The key should be stored in `local.properties` and accessed via `build.gradle`, and ideally fetched from a secure backend. For this 3-hour assignment, hardcoding was the most time-efficient approach as instructed.

*   **Error Handling**: A custom `Resource` sealed class (`Success`, `Error`, `Loading`) is used to wrap network responses. This provides a clean and exhaustive way to represent UI states in the ViewModel and the UI layer.

*   **Persistence**: `SharedPreferences` was used to persist the last searched city. It's simple and perfect for storing a single key-value pair. For more complex data, `DataStore` would be the preferred choice.

---

### What I Would Add with More Time

*   **Testing the ViewModel **:
*   **Dependency Injection**: I would integrate Hilt or Koin for proper dependency injection. This would make the app more scalable and testing much easier by removing manual DI in the ViewModel factory.
*   **More Robust UI**:
    *   Add more weather details (humidity, wind speed, pressure).
    *   Implement a 3-day forecast view.
    *   Improve the UI/UX with animations and a more polished design.
*   **Comprehensive Testing**: I've included a basic ViewModel unit test. With more time, I would add tests for the Repository layer and UI tests for the Composables to ensure correctness and prevent regressions.
*   **Location Services**: Add a feature to get the weather for the user's current location using the device's GPS.
*   **Modularization**: For a larger app, I would split the project into separate modules (e.g., `:app`, `:core`, `:feature-weather`).

---

### The Riddle's Answer

The answer to the riddle "7/6 to 20 decimal places but change every other 6 in the result to a 4 (starting from the third 6). Make all the other characters which are not 4 into the letter Z" is:

**`Z.ZZ4Z4Z4Z4Z4Z4Z4Z4Z4Z`**
