package com.dorin.smartravel.Objects;

import java.util.List;

public class Trip {

        private String name;
        private int numOfDays;
        private int thumbnail;
        private String startDate;
        private String endDate;
        private List<DayTrip> dayTripList;

        public Trip() {
        }

        public Trip(String name, int numOfDays, int thumbnail, String startDate, String endDate) {
            this.name = name;
            this.numOfDays = numOfDays;
            this.thumbnail = thumbnail;
            this.startDate = startDate;
            this.endDate = endDate;
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
}

