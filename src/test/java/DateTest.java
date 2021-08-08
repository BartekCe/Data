import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SoftAssertionsExtension.class)
class DateTest {
    private Date date;

    @BeforeEach
    void setUp() {
        date = new Date(22, 7, 1994);
    }

    @Test
    void addingYearChangeYearInDate() {
        Date dateWithAddedYear = date.changeYears(date, 1);
        assertThat(dateWithAddedYear.getYear()).isEqualTo(1995);
    }

    @Test
    void addingMonthsChangeMonthInDate() {
        Date dateWithAddedMonth = date.changeMonths(date, 2);
        assertThat(dateWithAddedMonth.getMonth()).isEqualTo(9);
    }

    @Test
    void addingDaysChangeDayInDate() {
        Date dateWithAddedDay = date.changeDays(date, 9);
        assertThat(dateWithAddedDay.getDay()).isEqualTo(31);
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
    void secondMonthInLeapYearHasTwentyNineDays() {
        Date leapYear = new Date(1, 2, 2000);
        int numberOfDaysInSecondMonth = leapYear.getNumberOfDaysInMonth(2, 2000);
        assertThat(numberOfDaysInSecondMonth).isEqualTo(29);
    }
}