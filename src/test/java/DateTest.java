import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
class DateTest {
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date(22, 7, 1994);
    }

    @Test
    void addingYearChangeYearInDate(SoftAssertions softly) {
        Date dateWithAddedYear = date.changeYears(date, 1);
        softly.assertThat(dateWithAddedYear.getYear()).isEqualTo(1995);
    }

    @Test
    void addingMonthsChangeMonthInDate(SoftAssertions softly) {
        Date dateWithAddedMonth = date.changeMonths(date, 2);
        softly.assertThat(dateWithAddedMonth.getMonth()).isEqualTo(9);
    }

    @Test
    void addingDaysChangeDayInDate(SoftAssertions softly) {
        Date dateWithAddedDay = date.changeDays(date, 9);
        softly.assertThat(dateWithAddedDay.getDay()).isEqualTo(31);
    }

    @Test
    void addingMonthsThatChangeYearByOne(SoftAssertions softly) {
        Date dateWithAddedMonths = date.changeMonths(date, 6);
        softly.assertThat(dateWithAddedMonths.getMonth()).isEqualTo(1);
        softly.assertThat(dateWithAddedMonths.getYear()).isEqualTo(1995);
    }

    @Test
    void addingDaysThatChangeMonthNumberInDateByOne(SoftAssertions softly) {
        Date dateWithAddedDays = date.changeDays(date, 15);
        softly.assertThat(dateWithAddedDays.getDay()).isEqualTo(6);
        softly.assertThat(dateWithAddedDays.getMonth()).isEqualTo(8);
    }

    @Test
    void addingDaysThatChangeMonthNumberInDateByTwo(SoftAssertions softly) {
        Date dateWithAddedDays = date.changeDays(date, 42);
        softly.assertThat(dateWithAddedDays.getMonth()).isEqualTo(9);
        softly.assertThat(dateWithAddedDays.getDay()).isEqualTo(2);
    }

    @Test
    void addingDaysThatChangeMonthNumberInDateByThree(SoftAssertions softly) {
        Date dateWithAddedDays = date.changeDays(date, 70);
        softly.assertThat(dateWithAddedDays.getMonth()).isEqualTo(9);
        softly.assertThat(dateWithAddedDays.getDay()).isEqualTo(30);
    }

    @Test
    void addingDaysThatChangeYearInDateByOne(SoftAssertions softly) {
        Date dateWithAddedDays = date.changeDays(date, 164);
        softly.assertThat(dateWithAddedDays.getDay()).isEqualTo(2);
        softly.assertThat(dateWithAddedDays.getMonth()).isEqualTo(1);
        softly.assertThat(dateWithAddedDays.getYear()).isEqualTo(1995);
    }

    @Test
    void secondMonthInLeapYearHasTwentyNineDays(SoftAssertions softly) {
        Date leapYear = new Date(1, 2, 2000);
        int numberOfDaysInSecondMonth = leapYear.getNumberOfDaysInMonth(2, 2000);
        softly.assertThat(numberOfDaysInSecondMonth).isEqualTo(29);
    }

    @Test
    void subtractingDayByThreeChangeDateByThree(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -3);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(7);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(19);
    }

    @Test
    void subtractingDaysThatChangeMonthInDateByOne(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -22);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(6);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(30);

    }
}