package com.dorin.smartravel.Objects;

import java.util.ArrayList;
import java.util.List;

public class Trip {

        private String name;
        private int numOfDays;
        private int thumbnail;
        private String startDate;
        private String endDate;
        private List<DayTrip> dayTripList;
        private ArrayList<Question> rates;
        private boolean isRate;

        public Trip() {
        }

        public Trip(String name, int numOfDays, int thumbnail, String startDate, String endDate) {
            this.name = name;
            this.numOfDays = numOfDays;
            this.thumbnail = thumbnail;
            this.startDate = startDate;
            this.endDate = endDate;
            this.isRate = false;
            prepareQA();
        }

        private void prepareQA() {
            rates= new ArrayList<Question>();
            Question a = new Question(1,"How satisfied are you with your trip?");
            rates.add(a);

            a = new Question(2,"How satisfied are you with the amount of time given everywhere?");
            rates.add(a);

            a = new Question(3,"How satisfied are you with the length of the trip?");
            rates.add(a);

            a = new Question(4,"How much personalized was the trip for you?");
            rates.add(a);

            a = new Question(5,"Will you come back to this destination?");
            rates.add(a);
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumOfDays() {
            return numOfDays;
        }

        public void setNumOfDays(int numOfDays) {
            this.numOfDays = numOfDays;
        }

        public int getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(int thumbnail) {
            this.thumbnail = thumbnail;
        }


        public String getStartDate() {
            return startDate;
        }

        public Trip setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public String getEndDate() {
            return endDate;
        }

        public Trip setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public List<DayTrip> getDayTripList() {
            return dayTripList;
        }

        public Trip setDayTripList(List<DayTrip> dayTripList) {
            this.dayTripList = dayTripList;
            return this;
        }

        public ArrayList<Question> getRates() {
            return rates;
        }

        public Trip setRates(ArrayList<Question> rates) {
            this.rates = rates;
            return this;
        }

        public Trip setIsRate(boolean rate) {
            isRate = rate;
            return this;
        }

        public boolean getIsRate() {
           return isRate;
        }


}

