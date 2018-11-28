package com.example.kerris.project;

public class HomeownerSelect {

    private int _id;
    private String _username;
    private String _servicename;
    private String _providername;
    private String _rate;
    private String _day;
    private String _starttime;
    private String _endtime;
    private String _r;
    private String _comment;


    public HomeownerSelect() {
    }
    public HomeownerSelect(int id, String username, String servicename, String providername, String rate, String day, String starttime, String endtime, String r, String comment) {
        _id = id;
        _username = username;
        _servicename = servicename;
        _providername = providername;
        _rate = rate;
        _day = day;
        _starttime = starttime;
        _endtime = endtime;
        _r = r;
        _comment = comment;
    }
    public HomeownerSelect(String username, String servicename, String providername, String rate, String day, String starttime, String endtime) {
        _username = username;
        _servicename = servicename;
        _providername = providername;
        _rate = rate;
        _day = day;
        _starttime = starttime;
        _endtime = endtime;
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
    public void setProvidereName(String providername) { _providername = providername; }
    public String getProviderName() {
        return _providername;
    }
    public void setRate(String rate) {
        _rate = rate;
    }
    public String getRate() {
        return _rate;
    }
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
    public void setR(String r) {
        _r = r;
    }
    public String getR() { return _r; }
    public void setComment(String comment) {
        _comment = comment;
    }
    public String getComment() { return _comment; }
}
