package com.example.rcpittnp.Model;

import java.util.ArrayList;

public class StudentModel {
    String id;
    String firstName;
    String lastName;
    String emailId;
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
    ArrayList<String> placedCompanies;

    public StudentModel() {
    }
    public StudentModel(StudentModel oldStudent) {
        this.id = oldStudent.getId();
        this.firstName = oldStudent.getFirstName();
        this.lastName = oldStudent.getLastName();
        this.emailId = oldStudent.getEmailId();
        this.mobNumber = oldStudent.getMobNumber();
        this.prnNumber = oldStudent.getPrnNumber();
        this.sscMarks = oldStudent.getSscMarks();
        this.hscMarks = oldStudent.getHscMarks();
        this.diploma = oldStudent.isDiploma();
        this.diplomaMarks = oldStudent.getDiplomaMarks();
        this.yearGap = oldStudent.isYearGap();
        this.activeBacklog = oldStudent.isActiveBacklog();
        this.cgpa = oldStudent.getCgpa();
        this.placedCompanies = oldStudent.getPlacedCompanies();
    }

    public StudentModel(String id ,String firstName, String lastName, String emailId, String mobNumber, String prnNumber, String sscMarks, String hscMarks, boolean diploma, String diplomaMarks, boolean yearGap, boolean activeBacklog, String cgpa , ArrayList<String> placedCompanies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.mobNumber = mobNumber;
        this.prnNumber = prnNumber;
        this.sscMarks = sscMarks;
        this.hscMarks = hscMarks;
        this.diploma = diploma;
        this.diplomaMarks = diplomaMarks;
        this.yearGap = yearGap;
        this.activeBacklog = activeBacklog;
        this.cgpa = cgpa;
        this.placedCompanies = placedCompanies;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public ArrayList<String> getPlacedCompanies() {
        return placedCompanies;
    }

    public void setPlacedCompanies(ArrayList<String> placedCompanies) {
        this.placedCompanies = placedCompanies;
    }
}
