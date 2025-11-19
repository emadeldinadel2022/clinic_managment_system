package com.clinic_project.clinic_system.business_layer.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessagingService {
    public static final String ACCOUNT_SID = "AC3b52cb83e48d14f42926157529c11557";
    public static final String AUTH_TOKEN = "485b7879938dc7f3c28083ea49769b3c";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new PhoneNumber("+201015597857"), // To number
                        new PhoneNumber("+12298468449"), // From number (your Twilio number)
                        "تيست تيست تيست")  // Message body
                .create();

        System.out.println("Message SID: " + message.getSid()); // Confirmation
    }
}
