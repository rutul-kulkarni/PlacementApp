package com.example.rcpittnp.Model;

public class Criteria {
    String sscMarks;
    String hscMarks;
    String diplomaMarks;
    boolean yearGap;
    String yearGapCount;
    boolean activeBacklog;
    String activeBacklogCount;
    String cgpa;

    public Criteria() {
    }

    public Criteria(String sscMarks, String hscMarks, String diplomaMarks, boolean yearGap, String yearGapCount, boolean activeBacklog, String activeBacklogCount, String cgpa) {
        this.sscMarks = sscMarks;
        this.hscMarks = hscMarks;
        this.diplomaMarks = diplomaMarks;
        this.yearGap = yearGap;
        this.yearGapCount = yearGapCount;
        this.activeBacklog = activeBacklog;
        this.activeBacklogCount = activeBacklogCount;
        this.cgpa = cgpa;
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

    public String getYearGapCount() {
        return yearGapCount;
    }

    public void setYearGapCount(String yearGapCount) {
        this.yearGapCount = yearGapCount;
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
