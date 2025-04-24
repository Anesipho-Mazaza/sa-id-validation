# sa-id-validation
his is a simple terminal-based Java tool that validates **South African ID numbers** based on structure and checksum logic. It uses **Test Driven Development (TDD)** principles and **JUnit 4** for unit testing.

---

## 🔍 Features

- ✅ Validates **13-digit South African ID numbers**
- ✅ Checks:
  - Proper length (13 digits)
  - All numeric characters
  - Valid **Luhn algorithm** checksum
  - Valid **date of birth**
  - Valid **gender code**
  - Valid **citizenship digit**

---

## 🧰 Requirements

- Java JDK 8 or higher
- Gradle (installed and on your system PATH)
- Terminal / Command Prompt
- (Optional) Git for cloning the repo

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Anesipho-Mazaza/sa-id-validation.git
cd sa-id-validation

2. Run Unit Tests
gradle test

3. Build the Project
gradle build

4. Run the App
./gradlew.bat :app:run
