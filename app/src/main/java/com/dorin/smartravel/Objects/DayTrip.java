package com.dorin.smartravel.Objects;

public class DayTrip {

    private String title;
    private int dayNumber;
    private String date;

    public DayTrip(int dayNumber, String date) {
        this.dayNumber = dayNumber;
        this.date = date;
        this.title = "DAY "+this.dayNumber+"  "+date;
    }

    public String getTitle() {
        return title;
    }

    public DayTrip setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public DayTrip setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
        return this;
    }

    public String getDate() {
        return date;
    }

    public DayTrip setDate(String date) {
        this.date = date;
        return this;
    }
}
