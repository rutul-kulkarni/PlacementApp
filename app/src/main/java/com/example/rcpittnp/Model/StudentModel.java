package com.example.rcpittnp.Model;

public class StudentModel {
    String id;
    String firstName;
    String lastName;
    String emmailId;
    String mobNumber;
    String prnNumber;
    String sscMarks;
    String hscMarks;
    boolean diploma;
    String diplomaMarks;
    boolean yearGap;
    boolean activeBacklog;
    String activeBacklogCount;
    String cgpa;

    public StudentModel() {
    }

    public StudentModel(String id ,String firstName, String lastName, String emmailId, String mobNumber, String prnNumber, String sscMarks, String hscMarks, boolean diploma, String diplomaMarks, boolean yearGap, boolean activeBacklog, String cgpa) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emmailId = emmailId;
        this.mobNumber = mobNumber;
        this.prnNumber = prnNumber;
        this.sscMarks = sscMarks;
        this.hscMarks = hscMarks;
        this.diploma = diploma;
        this.diplomaMarks = diplomaMarks;
        this.yearGap = yearGap;
        this.activeBacklog = activeBacklog;
        this.cgpa = cgpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmmailId() {
        return emmailId;
    }

    public void setEmmailId(String emmailId) {
        this.emmailId = emmailId;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getPrnNumber() {
        return prnNumber;
    }

    public void setPrnNumber(String prnNumber) {
        this.prnNumber = prnNumber;
    }

    public String getSscMarks() {
        return sscMarks;
    }

    public void setSscMarks(String sscMarks) {
        this.sscMarks = sscMarks;
    }

    public String getHscMarks() {
        return hscMarks;
    }

    public void setHscMarks(String hscMarks) {
        this.hscMarks = hscMarks;
    }

    public boolean isDiploma() {
        return diploma;
    }

    public void setDiploma(boolean diploma) {
        this.diploma = diploma;
    }

    public String getDiplomaMarks() {
        return diplomaMarks;
    }

    public void setDiplomaMarks(String diplomaMarks) {
        this.diplomaMarks = diplomaMarks;
    }

    public boolean isYearGap() {
        return yearGap;
    }

    public void setYearGap(boolean yearGap) {
        this.yearGap = yearGap;
    }

    public boolean isActiveBacklog() {
        return activeBacklog;
    }

    public void setActiveBacklog(boolean activeBacklog) {
        this.activeBacklog = activeBacklog;
    }

    public String getActiveBacklogCount() {
        return activeBacklogCount;
    }

    public void setActiveBacklogCount(String activeBacklogCount) {
        this.activeBacklogCount = activeBacklogCount;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}
