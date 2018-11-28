package com.example.kerris.project;

public class ProviderService {
    private int _id;
    private String _username;
    private String _servicename;
    private String _rate;

    public ProviderService() {
    }
    public ProviderService(int id, String username, String servicename, String rate) {
        _id = id;
        _username = username;
        _servicename = servicename;
        _rate = rate;
    }
    public ProviderService(String username, String servicename, String rate) {
        _username = username;
        _servicename = servicename;
        _rate = rate;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() { return _id; }
    public void setUserName(String username) {
        _username = username;
    }
    public String getUserName() {
        return _username;
    }
    public void setServiceName(String servicename) {  _servicename = servicename; }
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
