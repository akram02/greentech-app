package tech.coderhub.greenapp.model;

import com.google.gson.annotations.SerializedName;

public class Requisition {
    @SerializedName("patientName")
    String name;

    @SerializedName("bloodGroup")
    String bloodGroup;

    @SerializedName("phone")
    String mobile;

    @SerializedName("requiredDate")
    String date;


    private String id, address, age, disease;
    public Requisition() {
    }

    public Requisition(String name, String address, String mobile, String bloodGroup, String age, String disease, String date) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.disease = disease;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
