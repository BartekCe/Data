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
        date = Date.creatNewDate(22,7,1994);
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
        Date leapYear = Date.creatNewDate(1, 2, 2000);
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
    void subtractingDaysByExactlyAmountOfDaysInDateChangeDateToDateWithLastDayInPreviousMonth(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -22);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(6);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(30);
    }

    @Test
    void subtractingDaysThatChangeMonthByOneChangeMonthInDateByOne(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -23);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(6);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(29);
    }

    @Test
    void subtractingDaysThatChangeMonthByTwoChangeMonthInDateByTwo(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -54);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(5);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(29);
    }

    @Test
    void subtractingDaysThatChangeMonthByThreeChangeMonthInDateByThree(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -85);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(4);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(28);
    }

    @Test
    void subtractingDaysThatChangeYearByOneChangeYearInDateByOne(SoftAssertions softly) {
        Date dateWithFirstJanuary = Date.creatNewDate(1, 1, 2021);
        Date dateWithSubtractDays = dateWithFirstJanuary.changeDays(dateWithFirstJanuary, -1);
        softly.assertThat(dateWithSubtractDays.getYear()).isEqualTo(2020);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(31);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(12);
    }

    @Test
    void subtractingDaysThatChangeYearByThreeChangeYearInDateByThree(SoftAssertions softly) {
        Date dateWithSubtractDays = date.changeDays(date, -999);
        softly.assertThat(dateWithSubtractDays.getYear()).isEqualTo(1991);
        softly.assertThat(dateWithSubtractDays.getMonth()).isEqualTo(10);
        softly.assertThat(dateWithSubtractDays.getDay()).isEqualTo(27);
    }
}