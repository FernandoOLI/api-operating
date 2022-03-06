package com.operating.api.model;

import java.util.Date;
import java.util.List;

public class VendedorReponseList {

    private String name;
    private String phone;
    private Integer age;
    private String city;
    private String state;
    private List<String> states;

    public VendedorReponseList(String name, String phone, Integer age, String city, String state, List<String> states) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.city = city;
        this.state = state;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
