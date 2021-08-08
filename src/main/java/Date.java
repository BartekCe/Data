public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {

        if (isWithSecondMonth(month) && (day <= 0 || day > 28))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-28, you have tried set day at %s", day));
        if (!(isWithLongerMonth(month)) && (day <= 0 || day >30))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-30, you have tried set day at %s", day));
        if (isWithLongerMonth(month) && (day <= 0 || day >31))
            throw new IllegalArgumentException(String.format("Day can be only in range 1-31, you have tried set day at %s", day));

        this.day = day;
        this.month = month;
        this.year = year;

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        if(isWithLongerMonth(oldDate.getMonth())
                && (oldDate.getDay() + amountOfAddedDays) > 31){
            Date newDate = changeMonths(oldDate,1);
            newDate.setDay((amountOfAddedDays + oldDate.getDay()) - 31);
            return newDate;
        }
        return new Date(oldDate.getDay() + amountOfAddedDays,
                oldDate.getMonth(),
                oldDate.getYear());
    }

    private boolean isWithSecondMonth(int month) {
        return month == 2;
    }

    private boolean isWithLongerMonth(int month) {
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12;
    }
}