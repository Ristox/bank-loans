package ee.rsx.kata.bank.loans.domain.ssn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.IntSupplier;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.LocalDate.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.regex.Pattern.compile;

public record SocialSecurityNumber(String value) {

  private static final Pattern SSN_PATTERN = compile("^\\d{3}[0-1]\\d{7}$");
  private static final DateTimeFormatter SSN_DATE_FORMAT = ofPattern("yyyyMMdd");

  private static final int[] DEFAULT_CHECKSUM_MULTIPLIERS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
  private static final int[] RECALCULATED_CHECKSUM_MULTIPLIERS = new int[]{3, 4, 5, 6, 7, 8, 9, 1, 2, 3};

  /**
   * @throws IllegalArgumentException when provided SSN value is invalid (does not correspond to Estonian SSN rules)
   * @throws IllegalStateException when century prefix of provided SSN value is invalid (not between 1...6)
   * @throws DateTimeParseException when birth date part in of provided SSN does not represent a valid date
   */
  public SocialSecurityNumber(String value) {
    this.value = value;
    validate();
  }

  private void validate() {
    var isValid =
      SSN_PATTERN.matcher(value).matches() &&
      isValidBirthDate() &&
      calculateChecksum() == parseChecksumAtTheEnd();

    if (!isValid) {
      throw new IllegalArgumentException();
    }
  }

  private boolean isValidBirthDate() {
    var birthDate = parseBirthDate();
    return birthDate.isEqual(now()) || birthDate.isBefore(now());
  }

  private LocalDate parseBirthDate() {
    var shortBirthDate = value.substring(1, 7);
    var century = value.substring(0, 1);
    var fullBirthDate = switch (century) {
      case "1", "2" -> "18" + shortBirthDate;
      case "3", "4" -> "19" + shortBirthDate;
      case "5", "6" -> "20" + shortBirthDate;
      default -> throw new IllegalStateException(
        format("Illegal century value (%s) in social security number (%s)", century, value)
      );
    };
    return LocalDate.parse(fullBirthDate, SSN_DATE_FORMAT);
  }

  private int calculateChecksum() {
    IntSupplier recalculation =
      () -> calculateChecksumUsing(RECALCULATED_CHECKSUM_MULTIPLIERS, () -> 0);

    return calculateChecksumUsing(DEFAULT_CHECKSUM_MULTIPLIERS, recalculation);
  }

  private int calculateChecksumUsing(int[] multipliers, IntSupplier recalculatedChecksum) {
    var total = sumOfEachSsnNumberMultipliedWith(multipliers);

    var modulus = total % 11;
    if (isDoubleDigit(modulus)) {
      modulus = recalculatedChecksum.getAsInt();
    }

    return modulus;
  }

  private int sumOfEachSsnNumberMultipliedWith(int[] multipliers) {
    return IntStream
      .range(0, value.length() - 1)
      .map(index -> numberAt(index) * multipliers[index])
      .sum();
  }

  private int numberAt(int index) {
    return parseInt(value.substring(index, index + 1));
  }

  private boolean isDoubleDigit(int modulus) {
    return 10 == modulus;
  }

  private int parseChecksumAtTheEnd() {
    return parseInt(value.substring(value.length() - 1));
  }
}
