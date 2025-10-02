# RecipientForm - Android Jetpack Compose Form Validation

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-green.svg)](https://developer.android.com/jetpack/compose)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=24)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A modern Android application demonstrating best practices for form validation in Jetpack Compose. Features a reusable validated text field component with real-time error handling and a complete recipient details form implementation.

## 📱 Screenshots

[Add your app screenshots here]

## ✨ Features

- 🎯 **Reusable ValidatedTextField Component** - Drop-in component for any form
- ✅ **Real-time Validation** - Instant feedback as users type
- 🎨 **Material Design 3** - Modern, beautiful UI following Material guidelines
- 🔤 **Smart Input Handling** - Auto-capitalization, format enforcement
- ♿ **Accessibility** - Focus management and keyboard navigation
- 📍 **UK Postcode Validation** - Built-in regex validation for UK postcodes
- 🚀 **Performance Optimized** - Efficient state management with Compose
- 📱 **Responsive Design** - Adapts to different screen sizes

## 🏗️ Architecture

This project follows modern Android development best practices:

- **MVVM Architecture** (Ready for integration)
- **Jetpack Compose** for declarative UI
- **State Hoisting** for reusable components
- **Single Source of Truth** for state management
- **Material Design 3** theming system

## 🛠️ Tech Stack

- **Language**: Kotlin 1.9.0
- **UI Framework**: Jetpack Compose 1.5.0
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Build System**: Gradle with Kotlin DSL

### Dependencies

```gradle
dependencies {
    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    
    // Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.8.1")
    
    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or newer
- JDK 17 or higher
- Android SDK with API 34

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/RecipientForm.git
cd RecipientForm
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

## 💻 Usage

### Using the ValidatedTextField Component

```kotlin
@Composable
fun MyScreen() {
    var email by remember { mutableStateOf(FieldState()) }
    
    ValidatedTextField(
        value = email.value,
        onValueChange = { 
            email = FieldState(it, validateEmail(it))
        },
        label = "Email Address",
        errorMessage = email.error,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        )
    )
}

fun validateEmail(email: String): String? {
    return when {
        email.isBlank() -> "Email is required"
        !email.contains("@") -> "Invalid email format"
        else -> null
    }
}
```

### Creating a Complete Form

```kotlin
@Composable
fun MyFormScreen() {
    RecipientDetailsScreen(
        onBackClick = { /* Handle navigation */ },
        onContinueClick = { formData ->
            // Handle form submission
            val firstName = formData["firstName"]
            val lastName = formData["lastName"]
            // ... process data
        }
    )
}
```

## 📋 Form Validation Rules

| Field | Validation Rules |
|-------|-----------------|
| First Name | Required, minimum 2 characters |
| Last Name | Required, minimum 2 characters |
| House No./Name | Required |
| Postcode | Required, UK postcode format (e.g., SW1A 1AA) |

## 🎨 Customization

### Styling the ValidatedTextField

The component supports full customization through modifiers and parameters:

```kotlin
ValidatedTextField(
    value = value,
    onValueChange = { /* ... */ },
    label = "Custom Label",
    modifier = Modifier.fillMaxWidth(),
    showHelpIcon = true,
    onHelpClick = { /* Show help dialog */ },
    keyboardOptions = KeyboardOptions(/* ... */),
    // Add custom trailing content
    trailingContent = {
        IconButton(onClick = { /* ... */ }) {
            Icon(Icons.Default.Search, "Search")
        }
    }
)
```

## 📦 Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/yourpackage/recipientform/
│   │   │   ├── ui/
│   │   │   │   ├── components/
│   │   │   │   │   └── ValidatedTextField.kt
│   │   │   │   ├── screens/
│   │   │   │   │   └── RecipientDetailsScreen.kt
│   │   │   │   └── theme/
│   │   │   │       └── Theme.kt
│   │   │   ├── data/
│   │   │   │   └── FieldState.kt
│   │   │   └── MainActivity.kt
│   │   └── res/
│   │       ├── values/
│   │       └── drawable/
│   └── test/
└── build.gradle.kts
```

## 🧪 Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Coding Standards

- Follow [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Write meaningful commit messages
- Add KDoc comments for public APIs
- Ensure all tests pass before submitting PR

## 📝 Roadmap

- [ ] Add ViewModel integration example
- [ ] Implement address lookup API integration
- [ ] Add more validation patterns (email, phone, etc.)
- [ ] Create additional form field types
- [ ] Add dark mode support
- [ ] Implement automated testing
- [ ] Add localization support
- [ ] Create design system documentation

## 🐛 Known Issues

None at the moment. Please report issues [here](https://github.com/yourusername/RecipientForm/issues).

## 📄 License

```
MIT License

Copyright (c) 2025 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 👤 Author

**[Your Name]**

- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)
- Email: your.email@example.com

## 🙏 Acknowledgments

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- Android Developer Community

## 📞 Support

If you have any questions or need help, please:
- Open an [issue](https://github.com/yourusername/RecipientForm/issues)
- Join our [Discussions](https://github.com/yourusername/RecipientForm/discussions)
- Star ⭐ this repository if you find it helpful!

---

**Made with ❤️ and Jetpack Compose**
