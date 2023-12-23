package com.example.labfinalsa;

public enum Gender {
    MALE,FEMALE;

    public static Gender checkType(String value){
        switch (value){
            case "MALE":
            case "male":
                return Gender.MALE;

            case "FEMALE":
            case "female":
                return Gender.FEMALE;

            default:
                return Gender.MALE;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
