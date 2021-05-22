package com.example.rcpittnp.Model;

public class HrContact {

    String hrName;
    String companyName;
    String mobileNumber;

    public HrContact() {
    }

    public HrContact(String hrName, String companyName, String mobileNumber) {
        this.hrName = hrName;
        this.companyName = companyName;
        this.mobileNumber = mobileNumber;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
