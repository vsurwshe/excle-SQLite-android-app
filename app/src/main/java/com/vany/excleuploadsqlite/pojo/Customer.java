package com.vany.excleuploadsqlite.pojo;

public class Customer {
    // this is properties
    private Integer cuId;
    private String cuName;
    private String cuContactNumber;
    private String cuEmail;
    private String cuAddress;
    private String cuFeedback;

    public Customer() {
    }

    public Customer(Integer cuId, String cuName, String cuContactNumber, String cuEmail, String cuAddress, String cuFeedback) {
        this.cuId = cuId;
        this.cuName = cuName;
        this.cuContactNumber = cuContactNumber;
        this.cuEmail = cuEmail;
        this.cuAddress = cuAddress;
        this.cuFeedback = cuFeedback;
    }

    public Integer getCuId() {
        return cuId;
    }

    public void setCuId(Integer cuId) {
        this.cuId = cuId;
    }

    public String getCuName() {
        return cuName;
    }

    public void setCuName(String cuName) {
        this.cuName = cuName;
    }

    public String getCuContactNumber() {
        return cuContactNumber;
    }

    public void setCuContactNumber(String cuContactNumber) {
        this.cuContactNumber = cuContactNumber;
    }

    public String getCuEmail() {
        return cuEmail;
    }

    public void setCuEmail(String cuEmail) {
        this.cuEmail = cuEmail;
    }

    public String getCuAddress() {
        return cuAddress;
    }

    public void setCuAddress(String cuAddress) {
        this.cuAddress = cuAddress;
    }

    public String getCuFeedback() {
        return cuFeedback;
    }

    public void setCuFeedback(String cuFeedback) {
        this.cuFeedback = cuFeedback;
    }
}
