# User List App (Paging + Compose)

A simple Android application that displays a paginated list of users using modern Android development practices.

## Tech Stack
- Kotlin
- Jetpack Compose
- Paging 3
- RemoteMediator
- Room
- Retrofit
- Coroutines & Flow
- Hilt (Dependency Injection)

## Architecture
The project follows **Clean Architecture** principles with a clear separation of concerns:
- **Data**: API, DTOs, Room entities, RemoteMediator
- **Domain**: Business models and repository interfaces
- **Presentation**: ViewModels and Compose UI

## Features
- Infinite scrolling with Paging 3
- Offline-first approach using Room
- Single source of truth (database)
- Lifecycle-aware state handling

## Pagination Strategy
Pagination is handled using **Paging 3 + RemoteMediator**.
- Initial load via `LoadType.REFRESH`
- Forward-only pagination via `LoadType.APPEND`
- No backward pagination (`PREPEND` disabled)
- End of pagination is determined by empty API responses

## Notes
This project focuses on architecture, state management, and pagination rather than UI complexity.
