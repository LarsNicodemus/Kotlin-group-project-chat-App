# WhatsSyntax - Chat App (Group Project)

This project is an Android chat app called **WhatsSyntax**, developed as part of the **Android App Development with Kotlin** module at the **Syntax Institute**. It is a group project where the content learned over the past weeks, such as **MVVM (Model-View-ViewModel)**, **API integration**, **Database**, **RecyclerView**, **Fragments**, and **Navigation**, was applied practically. The app simulates a simple chat application with features such as status updates, chat functionality, and settings management.

## About the App

The **WhatsSyntax** app is a simple chat application that provides the following features:

- **Chats**: Display and management of chat messages using **RecyclerView** and **API integration**.
- **Status**: Display of user status updates.
- **Settings**: Management of app settings, including API key configuration.
- **Notes**: Integration with a local database to store and retrieve notes.

The app was developed in a group, with a focus on teamwork, reinforcing the concepts learned, and creating a shared reference guide.

## Team Members

- **[Lars Nicodemus](https://github.com/LarsNicodemus)**
- **[B0bby91](https://github.com/B0bby91)**
- **[Angela770824](https://github.com/Angela770824)**

## Features

- **BottomNavigationView**: Navigate between different sections of the app (Chats, Status, Settings, Notes).
- **Fragments**: Modular structure of the user interface using fragments.
- **RecyclerView**: Display chat messages and status updates in a scrollable list.
- **API Integration**: Fetch and send data using REST APIs with **API-Key** authentication (via query parameters or headers).
- **MVVM Architecture**: Separation of concerns using the Model-View-ViewModel pattern.
- **Database**: Local storage for notes using **Room Database**.
- **Navigation Component**: Simplified navigation between fragments.

## Technologies and Concepts

- **Kotlin**: The programming language for Android development.
- **Retrofit**: For API communication and handling HTTP requests.
- **Room Database**: For local data storage.
- **RecyclerView**: Efficient display of lists.
- **Fragments**: Modular UI components for different sections of the app.
- **MVVM**: Architecture pattern for separating UI logic from business logic.
- **Android Studio**: The official IDE for Android development.

## Project Week Workflow

- **Monday**: 
  - **StatusFragment**: Implemented with **API-Key** integration.
  - **RecyclerView**: Used to display status updates.
  - **API**: Reviewed and applied API concepts.

- **Tuesday**: 
  - **SettingsFragment**: Implemented with **API-POST-Requests**.
  - **RecyclerView**: Continued use for displaying settings.
  - **API**: Further practice with API integration.

- **Wednesday**: 
  - **ChatFragment**: Implemented with **RecyclerView** and **MVVM** architecture.
  - **API**: Continued integration with the chat functionality.

- **Thursday**: 
  - **ChatFragment**: Enhanced with **Navigation** between fragments.
  - **RecyclerView**: Further refined for chat message display.
  - **API**: Finalized API integration for chat features.

- **Friday**: 
  - **NotesFragment**: Implemented with local **Database** integration.
  - **Database**: Reviewed and applied Room Database concepts for storing notes.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/LarsNicodemus/Kotlin-group-project-chat-App.git
Open the project in Android Studio.

Build the app and run it on an emulator or physical device.

### Usage
Use the BottomNavigationView to navigate between the sections: Chats, Status, Settings, and Notes.

In the Chats section, you can view and send messages.

In the Status section, you can view user status updates.

In the Settings section, you can manage app settings, including API key configuration.

In the Notes section, you can store and retrieve notes using the local database.

### Contributing
Contributions are welcome! Please create a pull request or open an issue to suggest improvements or report bugs.

### License
This project is licensed under the MIT License. See the LICENSE file for more information.

Note: This app was developed as part of the Android App Development with Kotlin module at the Syntax Institute and is primarily for educational purposes. It was created in a group to foster teamwork and the application of the concepts learned.

