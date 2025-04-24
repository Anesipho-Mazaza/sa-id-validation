package sa_id_validation;

public class ValidateSaId {

    public static class ValidationResult {
        public final boolean valid;
        public final String reason;
        public final String birthdate;
        public final String gender;
        public final String citizenship;
        public final Integer age;

        public ValidationResult(boolean valid, String reason, String birthdate, String gender, String citizenship, Integer age) {
            this.valid = valid;
            this.reason = reason;
            this.birthdate = birthdate;
            this.gender = gender;
            this.citizenship = citizenship;
            this.age = age;
        }
        // For backward compatibility
        public ValidationResult(boolean valid, String reason, String birthdate, String gender, String citizenship) {
            this(valid, reason, birthdate, gender, citizenship, null);
        }
    }

    public static ValidationResult validateIdNumber(String idNumber) {
        // Must be exactly 13 digits
        if (idNumber == null || idNumber.length() != 13) {
            return new ValidationResult(false, "ID number must be exactly 13 digits", null, null, null);
        }
        // Must contain only digits
        if (!idNumber.matches("\\d{13}")) {
            return new ValidationResult(false, "ID number must contain only digits", null, null, null);
        }
        // Parse date
        String year = idNumber.substring(0, 2);
        String month = idNumber.substring(2, 4);
        String day = idNumber.substring(4, 6);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        // Determine century: if ID is for a person under 100, assume 2000s if year > current year, else 1900s
        java.time.LocalDate now = java.time.LocalDate.now();
        int currentYear = now.getYear() % 100;
        int fullYear = Integer.parseInt(year);
        int century = (fullYear > currentYear) ? 1900 : 2000;
        int resolvedYear = century + fullYear;
        String birthdateStr = String.format("%04d-%02d-%02d", resolvedYear, monthInt, dayInt);
        java.time.LocalDate birthdate;
        try {
            birthdate = java.time.LocalDate.of(resolvedYear, monthInt, dayInt);
        } catch (Exception e) {
            return new ValidationResult(false, "Invalid date of birth", null, null, null);
        }
        // Gender (SSSS): 0000–9999
        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        String gender = (genderDigits >= 5000) ? "Male" : "Female";
        // Citizenship (C): 0 or 1
        char citizenshipChar = idNumber.charAt(10);
        String citizenship;
        if (citizenshipChar == '0') citizenship = "SA Citizen";
        else if (citizenshipChar == '1') citizenship = "Permanent Resident";
        else return new ValidationResult(false, "Invalid citizenship digit", null, null, null);
        // Luhn checksum (Z)
        if (!luhnCheck(idNumber)) {
            return new ValidationResult(false, "Invalid Luhn checksum", null, null, null);
        }
        // Calculate age
        java.time.Period period = java.time.Period.between(birthdate, java.time.LocalDate.now());
        int age = period.getYears();
        return new ValidationResult(true, "Valid SA ID", birthdateStr, gender, citizenship, age);
    }

    public static boolean isIdNumberValid(String idNumber) {
        return validateIdNumber(idNumber).valid;
    }

    private static boolean luhnCheck(String idNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = idNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(idNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n = (n % 10) + 1;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
