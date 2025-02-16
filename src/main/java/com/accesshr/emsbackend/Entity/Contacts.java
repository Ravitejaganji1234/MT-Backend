package com.accesshr.emsbackend.Entity;

import jakarta.persistence.*;
import java.util.*;


@Entity 
@Table(name="contactslist")
public class Contacts {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="contactId")
    int contactId;
    @Column (name="contactCreatedEmployee")
    String contactCreatedEmployee;
    @Column (name="employeeId")
    String employeeId;
    @Column (name="personName")
    String personName;
    @Column (name="relation")
    String relation;
    @Column (name="countryCode")
    String countryCode;
    @Column (name="personMobile")
    String personMobile;
    @Column (name="personEmail")
    String personEmail;
    @Column (name="company")
    String company;
    @Column (name="country")
    String country;
    @Column (name="state")
    String state;
    @Column (name="address")
    String address;
    @Column (name="pincode")
    String pincode;
    @Column (name="bankName")
    String bankName;
    @Column (name="bankcode")
    String bankcode;
    @Column (name="accountNumber")
    String accountNumber;
    @Column (name="accountname")
    String accountname;
    @Column (name="otherAccountInformation")
    String otherAccountInformation;
    @Column (name="bankAddress")
    String bankAddress;

    public Contacts(){}

    public Contacts(int contactId, String contactCreatedEmployee, String personName, String relation, String countryCode, String personMobile, String personEmail, String company, String Country, String state, 
    String address, String pincode, String bankName, String bankCode, String accountNumber, String accountName, String otherAccountInformation, String bankAddress){
        this.contactId=contactId;
        this.contactCreatedEmployee=contactCreatedEmployee;
        this.personName=personName;
        this.relation=relation;
        this.countryCode=countryCode;
        this.personMobile=personMobile;
        this.personEmail=personEmail;
        this.company=company;
        this.country=country;
        this.state=state;
        this.address=address;
        this.pincode=pincode;
        this.bankName=bankName;
        this.bankcode=bankCode;
        this.accountNumber=accountNumber;
        this.accountname=accountName;
        this.otherAccountInformation=otherAccountInformation;
        this.bankAddress=bankAddress;
        this.employeeId=employeeId;
        
    }

    public int getContactId(){
        return contactId;
    }

    public void setContactId(int contactId){
        this.contactId=contactId;
    }

    public String getContactCreatedEmployee(){
        return contactCreatedEmployee;
    }
    public void setContactCreatedEmployee(String contactCreatedEmployee){
        this.contactCreatedEmployee=contactCreatedEmployee;
    }

    public String getEmployeeId(){return employeeId;};

    public void setEmployeeId(String employeeId){
        this.employeeId=employeeId;
    };

    public String getPersonName(){
        return personName;
    }
    public void setPersonName(String personName){
        this.personName=personName;
    }
    public String getRelation(){
        return relation;
    }
    public void setRelation(String relation){
        this.relation=relation;
    }
    public String getCountryCode(){
        return countryCode;
    }
    public void setCountryCode(String countryCode){
        this.countryCode=countryCode;
    }
    public String getPersonMobile(){
        return personMobile;
    }
    public void setPersonMobile(String personMobile){
        this.personMobile=personMobile;
    }
    public String getPersonEmail(){
        return personEmail;
    }
    public void setPersonEmail(String personEmail){
        this.personEmail=personEmail;
    }
    public String getCompany(){
        return company;
    }
    public void setCompany(String company){
        this.company=company;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country=country;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state=state;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getPincode(){
        return pincode;
    }
    public void setPincode(String pincode){
        this.pincode=pincode;
    }
    public String getBankName(){
        return bankName;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    }
    public String getBankCode(){
        return bankcode;
    }
    public void setBankCode(String bankCode){
        this.bankcode=bankCode;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber=accountNumber;
    }
    public String getAccountName(){
        return accountname;
    }
    public void setAccountName(String accountName){
        this.accountname=accountName;
    }

    public String getOtherAccountInformation(){
        return otherAccountInformation;
    }
    public void setOtherAccountInformation(String otherAccountInformation){
        this.otherAccountInformation=otherAccountInformation;
    }
    public String getBankAddress(){
        return bankAddress;
    }
    public void setBankAdress(String bankaddress){
        this.bankAddress=bankaddress;
    }
}

