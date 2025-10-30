# Midterm - Multiplication Table Application

Android application that generates and displays multiplication tables with history tracking.

**Author:** Vincente Sequeira  
**Course:** COMP 3074  
**Project Type:** Midterm Assignment

## Features

### Core Functionality
- Generate multiplication tables for any number (1-1000)
- Display tables from 1×n to 10×n in a ListView
- Click any row to delete it with confirmation dialog
- Toast notifications for user feedback
- Navigate between main screen and history screen

### Bonus Features
- Clear All option in menu with confirmation dialog
- History tracking of all generated tables
- Back navigation support
- Empty state handling in history screen

## Technical Implementation

### Architecture
- **Two Activities Pattern:**
    - MainActivity: Generate and display multiplication tables
    - HistoryActivity: Display generation history

### Key Components
- **DataManager:** Singleton pattern for data persistence across activities
- **ArrayAdapter:** Dynamic list updates
- **AlertDialog:** User confirmations
- **Intent:** Activity navigation
- **ListView:** Display tables and history

## UI Components

### MainActivity
- EditText for number input
- Generate button to create table
- History button for navigation
- ListView for table display
- Options menu with Clear All

### HistoryActivity
- ListView for history display
- Empty state TextView
- Back button in ActionBar

## Project Structure

```
app/src/main/java/com/example/midterm_vincente_sequeira/
├── MainActivity.java
├── HistoryActivity.java
└── DataManager.java

app/src/main/res/
├── layout/
│   ├── activity_main.xml
│   └── activity_history.xml
└── menu/
    └── main_menu.xml
```

## Requirements Met

- ✅ EditText, Button, ListView components
- ✅ ArrayAdapter with dynamic updates
- ✅ Click events with AlertDialog
- ✅ Toast feedback messages
- ✅ Two Activities with Intent navigation
- ✅ Data persistence across screens
- ✅ Bonus: Clear All with confirmation
- ✅ Proper project naming convention
- ✅ GitHub repository with 5+ commits

## How to Run

1. Clone the repository
2. Open in Android Studio
3. Wait for Gradle sync
4. Run on emulator or physical device (API 24+)

## Technologies Used

- **Language:** Java
- **Min SDK:** API 24 (Android 7.0)
- **IDE:** Android Studio
- **Build System:** Gradle (Kotlin DSL)

## Concepts Demonstrated

- Activity lifecycle management
- Singleton design pattern
- ListView with custom adapters
- Dialog handling
- Intent-based navigation
- User input validation
- Data persistence techniques
- Material Design principles

## License

Educational project for COMP 3074 - Midterm Assignment