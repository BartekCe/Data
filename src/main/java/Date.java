public class Date {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (isThisSecondMonth(month) && (day <= 0 || day > 28))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-28, you have tried set day at %s", day));
        if (isThisShorterMonth(month) && (day <= 0 || day > 30))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-30, you have tried set day at %s", day));
        if (isThisLongerMonth(month) && (day <= 0 || day > 31))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-31, you have tried set day at %s", day));

        this.day = day;
        if (month > 12) {
            this.month = 1;
            this.year = year + 1;
        } else {
            this.month = month;
            this.year = year;
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public Date changeYears(Date oldDate, int amountOfAddedYears) {
        return new Date(oldDate.getDay(),
                oldDate.getMonth(),
                (oldDate.getYear() + amountOfAddedYears));
    }

    public Date changeMonths(Date oldDate, int amountOfAddedMonths) {
        if (oldDate.getMonth() + amountOfAddedMonths <= 12) {
            return new Date(oldDate.getDay(),
                    oldDate.getMonth() + amountOfAddedMonths,
                    getYear());
        } else {
            return new Date(oldDate.getDay(),
                    (amountOfAddedMonths + oldDate.getMonth()) - 12,
                    oldDate.getYear() + 1);
        }
    }

    public Date changeDays(Date oldDate, int amountOfAddedDays) {
        int sumOfDays = amountOfAddedDays + oldDate.getDay();
        if (sumOfDays > oldDate.getNumberOfDaysInMonth(oldDate.getMonth(), oldDate.getYear())) {
            int sumOfDaysAfterChangingMonth = sumOfDays - oldDate.getNumberOfDaysInMonth(oldDate.getMonth(), oldDate.getYear());
            int numberOfDaysInNextMonth = oldDate.getNumberOfDaysInMonth(oldDate.getMonth() + 1, oldDate.getYear());

            if (sumOfDaysAfterChangingMonth <= numberOfDaysInNextMonth) {
                return new Date(sumOfDaysAfterChangingMonth, oldDate.getMonth() + 1, oldDate.getYear());
            } else {
                Date newDate = new Date(1, oldDate.getMonth() + 1, oldDate.getYear());
                return changeDays(newDate, sumOfDaysAfterChangingMonth - 1);
            }
        }
        return new Date(oldDate.getDay() + amountOfAddedDays,
                oldDate.getMonth(),
                oldDate.getYear());
    }

    public int getNumberOfDaysInMonth(int month, int year) {
        if (isThisSecondMonth(month) && !isLeapYear(year)) {
            return 28;
        } else if (isThisLongerMonth(month)) {
            return 31;
        } else if (isThisShorterMonth(month)) {
            return 30;
        } else {
            return 29;
        }
    }

    private boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private boolean isThisSecondMonth(int month) {
        return month == 2;
    }

    private boolean isThisShorterMonth(int month) {
        return month == 4 || month == 6 || month == 9 || month == 11;
    }

    private boolean isThisLongerMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12;
    }
}