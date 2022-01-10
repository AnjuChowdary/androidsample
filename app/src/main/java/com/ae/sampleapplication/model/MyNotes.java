package com.ae.sampleapplication.model;

public class MyNotes {
    String date;
    String title;
    String details;

    public MyNotes() {

    }

    public MyNotes(String date, String title, String details) {
        this.date = date;
        this.title = title;
        this.details = details;
    }

    public MyNotes(String date){
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
