# South African ID Validation App

This is a simple terminal-based Java tool that validates **South African ID numbers** and extracts details like date of birth, gender, citizenship, and age.

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

### 4. Run the App

**Important:** For interactive input, do NOT use `./gradlew run` or `./gradlew.bat :app:run`, as Gradle does not reliably support console input. Instead, run the app directly with Java after building:

```
java -cp app/build/classes/java/main sa_id_validation.App
```

You will see output like:
```
Testing with ID: 9202205001082
The ID is valid!
DOB 1992-02-20
Gender Male
Citizenship SA Citizen
Age 33
```

If the ID is invalid, you will see an error message explaining why.

### 📖 ID Format Overview
Format: YYMMDD SSSS C A Z

📖 ID Format Overview
Format: YYMMDD SSSS C A Z

Segment	Description
YYMMDD	Birthdate (e.g. 920220 for 20 Feb 1992)
SSSS	Gender sequence (0000–4999 = Female, 5000–9999 = Male)
C	Citizenship (0 = SA citizen, 1 = permanent resident)
A	Usually 8 (legacy/reserved)
Z	Checksum digit (Luhn algorithm)
