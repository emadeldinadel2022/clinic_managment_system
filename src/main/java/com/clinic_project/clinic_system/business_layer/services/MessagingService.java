package com.clinic_project.clinic_system.business_layer.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessagingService {
    public static void main(String[] args) {

        Message message = Message.creator(
                        new PhoneNumber("+201015597857"), // To number
                        new PhoneNumber("+12298468449"), // From number (your Twilio number)
                        "تيست تيست تيست")  // Message body
                .create();

        System.out.println("Message SID: " + message.getSid()); // Confirmation
    }
}
