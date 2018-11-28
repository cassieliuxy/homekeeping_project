package com.example.kerris.project;

public class Provider {
    private int _id;
    private String _username;
    private String _address;
    private String _phonenumber;
    private String _companyname;
    private String _description;
    private String _licensed;

    public Provider() {
    }
    public Provider(int id, String username, String address, String phonenumber, String companyname, String description, String licensed) {
        _id = id;
        _username = username;
        _address = address;
        _phonenumber = phonenumber;
        _companyname = companyname;
        _description = description;
        _licensed = licensed;
    }
    public Provider(String username, String address, String phonenumber, String companyname, String description, String licensed) {
        _username = username;
        _address = address;
        _phonenumber = phonenumber;
        _companyname = companyname;
        _description = description;
        _licensed = licensed;
    }

    public void setID(int id) {
        _id = id;
    }
    public int getID() {
        return _id;
    }
    public void setUserName(String username) {
        _username = username;
    }
    public String getUserName() {
        return _username;
    }
    public void setAddress(String address) { _address = address;}
    public String getAddress() {
        return _address;
    }
    public void setPhoneNumber(String phonenumber) { _phonenumber = phonenumber;}
    public String getPhoneNumber() {
        return _phonenumber;
    }
    public void setCompanyName(String companyname) { _companyname = companyname;}
    public String getCompanyName() {
        return _companyname;
    }
    public void setDescription(String description) { _description = description;}
    public String getDescription() {
        return _description;
    }
    public void setLicensed(String licensed) {
        _licensed = licensed;
    }
    public String getLicensed() {
        return _licensed;
    }
}
