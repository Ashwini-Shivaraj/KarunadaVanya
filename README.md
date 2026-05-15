KarunadaVanya
Wildlife Education and Alert System for Karnataka Forest Awareness

Introduction

KarunadaVanya is an Android application developed to create awareness about Karnataka wildlife, forest regions, and animal safety. The application helps users explore wildlife information, report animal movement alerts, learn safety precautions, hear forest sounds, and view Karnataka forest locations.

This project was developed using Kotlin, Jetpack Compose, Firebase Realtime Database, and Android Studio.

Problem Statement

People living near forest areas often lack:

Awareness about wildlife species
Safety guidelines during animal encounters
Information about Karnataka forest regions
A quick way to report wildlife movement alerts

There is a need for a simple mobile application that provides wildlife education and alert reporting on one platform.

Objectives

The main objectives of KarunadaVanya are:

To educate users about Karnataka wildlife
To improve wildlife safety awareness
To allow reporting of animal movement alerts
To provide forest information interactively
To create an easy-to-use educational mobile platform

Features
Splash Screen
Attractive startup screen with application logo
Smooth entry into the application
Home Dashboard
Central navigation screen
Search functionality
Feature cards for all modules

Wildlife Wiki
Displays wildlife animals
Animal images and information
Search animal feature
Detailed wildlife pages

Movement Alert System
Users can report animal movement
Stores alert data in Firebase Realtime Database
Real-time alert saving

Alert History
Displays previously reported alerts
Retrieves data from Firebase

Safety Guide
Wildlife safety tips
Safety precautions for different animals

Forest Sounds
Nature and wildlife sound effects
Interactive audio playback

Karnataka Forest Map
Displays Karnataka forest regions
Forest details with images and descriptions

| Technology                 | Purpose             |
| -------------------------- | ------------------- |
| Kotlin                     | Android Development |
| Jetpack Compose            | UI Design           |
| Firebase Realtime Database | Cloud Database      |
| Android Studio             | Development IDE     |
| Material Design            | UI Components       |
| GitHub                     | Version Control     |


System Architecture
User → Android Application → Firebase Realtime Database

User interacts with the application
Data is stored and retrieved from Firebase
Application displays information dynamically

Modules
Wildlife Education Module
Wildlife information and images

Alert Reporting Module
Animal movement reporting

Alert History Module
Displays previous alerts

Safety Awareness Module
Wildlife safety education

Forest Exploration Module
Karnataka forest information

Sound Interaction Module
Wildlife sound playback
Firebase Integration

Firebase Realtime Database is used to:
Store movement alerts
Retrieve alert history
Maintain real-time data synchronization

Example Data:

{
  "animalName": "Bear",
  "location": "Chitta Forest, Bidar"
}

Project Structure
            KarunadaVanya/
            │
            ├── app/
            │   ├── src/main/java/
            │   ├── res/drawable/
            │   ├── ui/theme/
            │
            ├── Firebase Integration
            ├── Wildlife Screens
            ├── Forest Map Screens
            ├── Sound Module
            └── Alert System

Installation Steps
Prerequisites

Android Studio
Firebase Account
Android SDK

Steps

Clone the repository
git clone https://github.com/Ashwini-Shivaraj/KarunadaVanya.git
Open the project in Android Studio
Sync Gradle
Connect Firebase
Run the application on Emulator or Android Device

Run Command
Run → app
Or click the Run button in Android Studio.

Testing
The application was tested for:

Navigation functionality
Firebase database storage
Alert history retrieval
UI responsiveness
Sound playback
Wildlife search functionality

Future Enhancements
Google Maps integration
Live wildlife tracking
User authentication
Push notifications
AI-based animal detection
Offline support
Multi-language support

Advantages
User-friendly interface
Wildlife awareness creation
Real-time alert reporting
Educational platform
Interactive experience

Limitations
Requires internet for Firebase
Static wildlife information
No live GPS tracking currently

Conclusion

KarunadaVanya successfully provides a wildlife education and alert management platform for Karnataka forest awareness. The application combines education, safety guidance, alert reporting, and forest exploration into a single mobile application using modern Android technologies.

The project demonstrates practical implementation of:

Android App Development
Firebase Realtime Database
Jetpack Compose UI
Real-time data handling
Developed By

Ashwini 
BE – Computer Science and Design Engineering
Alva’s Institute of Engineering and Technology

