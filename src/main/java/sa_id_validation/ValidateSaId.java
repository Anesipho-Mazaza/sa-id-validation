package sa_id_validation;

public class ValidateSaId {

    public static boolean isIdNumberValid(String idNumber) {
        // Must be exactly 13 digits
        if (idNumber == null || idNumber.length() != 13) {
            return false;
        }
        // Must contain only digits
        if (!idNumber.matches("\\d{13}")) {
            return false;
        }
        // Parse date
        String year = idNumber.substring(0, 2);
        String month = idNumber.substring(2, 4);
        String day = idNumber.substring(4, 6);
        int monthInt = Integer.parseInt(month);
        int dayInt = Integer.parseInt(day);
        if (monthInt < 1 || monthInt > 12) return false;
        if (dayInt < 1 || dayInt > 31) return false;
        // Gender (SSSS): 0000–9999 (we'll only check it's in range)
        int gender = Integer.parseInt(idNumber.substring(6, 10));
        if (gender < 0 || gender > 9999) return false;
        // Citizenship (C): 0 or 1
        char citizenship = idNumber.charAt(10);
        if (citizenship != '0' && citizenship != '1') return false;
        // Luhn checksum (Z)
        return luhnCheck(idNumber);
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
