package com.example.labfinalsa;

public enum MembershipTypes {
    STANDARD,PREMIUM,VIP;

    public static MembershipTypes checkType(String value){
        switch (value){
            case "STANDARD":
            case "standard":
                return MembershipTypes.STANDARD;

            case "PREMIUM":
            case "premium":
                return MembershipTypes.PREMIUM;

            case "VIP":
            case "vip":
                return MembershipTypes.VIP;

            default:
                return MembershipTypes.STANDARD;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
