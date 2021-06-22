package com.sber.stepanyan.compclub.entity;

public enum SystemUnitPower {
    LOW,
    MEDIUM,
    HIGH;

    public static boolean contains(String s)
    {
        for(SystemUnitPower choice:values())
            if (choice.name().equals(s))
                return true;
        return false;
    }
}
