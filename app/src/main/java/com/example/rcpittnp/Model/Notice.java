package com.example.rcpittnp.Model;

public class Notice {
    String companyName;
    Criteria criteria;
    String date;
    String pkg;

    public Notice() {
    }

    public Notice(String companyName, Criteria criteria, String date, String pkg) {
        this.companyName = companyName;
        this.criteria = criteria;
        this.date = date;
        this.pkg = pkg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }
}
