import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    void addingMonthChangeMonthInDate() {
        Date dateWithAddedMonth = date.changeMonths(date, 2);
        assertThat(dateWithAddedMonth.getMonth()).isEqualTo(9);
    }

}