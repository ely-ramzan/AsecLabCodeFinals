package com.example.labfinalsa;

import javafx.scene.Scene;

import java.io.Serializable;
import java.util.Date;

public class PersonInformation implements Serializable {
    private String name;
    private Gender gender;
    private MembershipTypes membershipTypes;
    private Date dateOfBirth;

    public PersonInformation(String name, String gender, String membershipTypes, Date dateOfBirth) {
        this.name = name;
        this.gender = Gender.checkType(gender);
        this.membershipTypes = MembershipTypes.checkType(membershipTypes);
        this.dateOfBirth = dateOfBirth;
    }
    public PersonInformation(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MembershipTypes getMembershipTypes() {
        return membershipTypes;
    }

    public void setMembershipTypes(MembershipTypes membershipTypes) {
        this.membershipTypes = membershipTypes;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PersonInformation{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", membershipTypes=" + membershipTypes +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
