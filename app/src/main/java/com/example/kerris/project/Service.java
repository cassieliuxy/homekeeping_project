package com.example.kerris.project;

public class Service {
    private int _id;
    private String _servicename;
    private String _rate;

    public Service() {
    }
    public Service(int id, String servicename, String rate) {
        _id = id;
        _servicename = servicename;
        _rate = rate;
    }
    public Service(String servicename, String rate) {
        _servicename = servicename;
        _rate = rate;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() {
        return _id;
    }
    public void setServiceName(String servicename) {
        _servicename = servicename;
    }
    public String getServiceName() {
        return _servicename;
    }
    public void setRate(String rate) {
        _rate = rate;
    }
    public String getRate() {
        return _rate;
    }
}
