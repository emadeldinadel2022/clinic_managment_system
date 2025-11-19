package com.clinic_project.clinic_system.business_layer.validators;

public class PhoneNumberValidator {
    public String validator(String num) {

        if (num.length() == 11) {
            if (num.startsWith("010") || num.startsWith("011") || num.startsWith("012") || num.startsWith("015")){
                return "2"+num;
            }
        }
        else if (num.length() == 10){
            if (num.startsWith("10") || num.startsWith("11") || num.startsWith("12") || num.startsWith("15")){
                return "20"+num;
            }
        }
        return null;
    }
}
