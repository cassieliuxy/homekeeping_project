package com.example.kerris.project;

public class ProviderTime {
    private int _id;
    private String _username;
    private String _day;
    private String _starttime;
    private String _endtime;
    private String _available;

    public ProviderTime() {
    }
    public ProviderTime(int id, String username, String day, String starttime, String endtime, String available) {
        _id = id;
        _username = username;
        _day = day;
        _starttime = starttime;
        _endtime = endtime;
        _available = available;
    }
    public ProviderTime(String username, String day, String starttime, String endtime, String available) {
        _username = username;
        _day = day;
        _starttime = starttime;
        _endtime = endtime;
        _available = available;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() { return _id; }
    public void setUserName(String username) {
        _username = username;
    }
    public String getUserName() { return _username; }
    public void setDAY(String day) {
        _day = day;
    }
    public String getDAY() { return _day; }
    public void setStartTime(String starttime) {
        _starttime = starttime;
    }
    public String getStartTime() {
        return _starttime;
    }
    public void setEndTime(String endtime) {
        _endtime = endtime;
    }
    public String getEndTime() {
        return _endtime;
    }
    public void setAvailable(String available) {  _available = available; }
    public String getAvailable() {
        return _available;
    }
}
