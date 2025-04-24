package sa_id_validation;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateSaIdTest {

    @Test
    public void validIdShouldReturnTrue() {
        assertTrue(ValidateSaId.isIdNumberValid("2001014800086"));
    }

    @Test
    public void anotherValidIdShouldReturnTrue() {
        assertTrue(ValidateSaId.isIdNumberValid("2909035800085"));
    }

    @Test
    public void tooShortIdShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("20010148000"));
    }

    @Test
    public void tooLongIdShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("20010148000861"));
    }

    @Test
    public void nonNumericIdShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("20010A4800086"));
    }

    @Test
    public void invalidMonthShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("2001134800086")); // 13th month
    }

    @Test
    public void invalidDayShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("2001023200086")); // 32nd day
    }

    @Test
    public void invalidGenderDigitsShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("2001019999986")); // Gender > 9999
    }

    @Test
    public void invalidCitizenshipShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800096")); // Citizenship not 0 or 1
    }

    @Test
    public void invalidChecksumShouldReturnFalse() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800087")); // Bad Luhn checksum
    }
}
