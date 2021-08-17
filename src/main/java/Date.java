public class Date {
    private final int day;
    private final int month;
    private final int year;

    private Date(int day, int month, int year) {
        this.day = day;
        if (month >= 13) {
            this.month = 1;
            this.year = year + 1;
        } else if (month <= 0) {
            this.month = 12;
            this.year = year -1;
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

    public static Date creatNewDate(int day, int month, int year){
        if (isThisSecondMonth(month) && (day <= 0 || day > 28))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-28, you have tried set day at %s", day));
        if (isThisShorterMonth(month) && (day <= 0 || day > 30))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-30, you have tried set day at %s", day));
        if (isThisLongerMonth(month) && (day <= 0 || day > 31))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-31, you have tried set day at %s", day));

        if(month <=0 || month > 12)
            throw new IllegalArgumentException(String.format("Month can be only in range 1-12, you have tried set month at %s", month));
        if(year <=0)
            throw new IllegalArgumentException(String.format("Year can be only positive number, you have tried set year at %s", year));


        return new Date(day,month,year);
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
        if(sumOfDays == 0){
            return previousMonthLastDay(oldDate);
        }
        if (sumOfDays > oldDate.getNumberOfDaysInMonth(oldDate.getMonth(), oldDate.getYear())) {
            int sumOfDaysAfterChangingMonth = sumOfDays - oldDate.getNumberOfDaysInMonth(oldDate.getMonth(), oldDate.getYear());
            int numberOfDaysInNextMonth = oldDate.getNumberOfDaysInMonth(oldDate.getMonth() + 1, oldDate.getYear());
            if (sumOfDaysAfterChangingMonth <= numberOfDaysInNextMonth) {
                return new Date(sumOfDaysAfterChangingMonth, oldDate.getMonth() + 1, oldDate.getYear());
            } else {
                Date newDate = new Date(1, oldDate.getMonth() + 1, oldDate.getYear());
                return changeDays(newDate, sumOfDaysAfterChangingMonth - 1);
            }

        } else if(sumOfDays < 0){
                int numberOfDaysInPreviousMonth = oldDate.getNumberOfDaysInMonth(oldDate.getMonth() -1, oldDate.getYear());
           if(-sumOfDays <= numberOfDaysInPreviousMonth){
            return new Date((getNumberOfDaysInMonth(oldDate.getMonth() -1, oldDate.getYear())) + sumOfDays,
                    oldDate.getMonth() -1, oldDate.getYear());
               }
               else {
                   int sumOfDaysAfterChangingMonth = -sumOfDays - numberOfDaysInPreviousMonth;
                   Date newDate = new Date(1, oldDate.getMonth() -1, oldDate.getYear());
                   return changeDays(newDate, -sumOfDaysAfterChangingMonth-1);
               }
        } else
        return new Date(oldDate.getDay() + amountOfAddedDays,
                oldDate.getMonth(),
                oldDate.getYear());
    }

    private Date previousMonthLastDay(Date oldDate) {
        if(!(oldDate.getMonth() == 1))
        return new Date(getNumberOfDaysInMonth(oldDate.getMonth() -1, oldDate.getYear()), oldDate.getMonth() -1, oldDate.getYear());
        else
            return new Date(getNumberOfDaysInMonth(oldDate.getMonth() -1, oldDate.getYear() -1), oldDate.getMonth() -1, oldDate.getYear() );
    }

    public int getNumberOfDaysInMonth(int month, int year) {
        if (isThisSecondMonth(month) && !isLeapYear(year)) {
            return 28;
        } else if (isThisLongerMonth(month) || month ==0) {
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

    private static boolean isThisSecondMonth(int month) {
        return month == 2;
    }

    private static boolean isThisShorterMonth(int month) {
        return month == 4 || month == 6 || month == 9 || month == 11;
    }

    private static boolean isThisLongerMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12;
    }
}