/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121wpoe;

import java.util.Scanner;

/**
 *
 * @author Goitsemang Mabuse ST10505287
 */
public class Message {

    private String recipient;
    private String messageText;

    private static java.util.List<String> sentMessages = new java.util.ArrayList<>();
    private static int totalMessagesSent = 0;
    
    // Checks that the message ID is no more than 10 characters long
    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }

    // Checks that the recipient cell number is correctly formatted,
    // reusing the same regex approach from Login's checkCellPhoneNumber
    public String checkRecipientCell(String cellNumber) {
        String regex = "^\\+27\\d{9}$";
        if (cellNumber.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Checks that the message does not exceed 250 characters
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // Creates the Message Hash: first 2 chars of message ID + ":" + message number + ":" + first and last words of message, all uppercase
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
    String idPart = messageID.substring(0, 2);

    // Remove punctuation so words like "tonight?" become "tonight"
    String cleanText = messageText.replaceAll("[^a-zA-Z0-9\\s]", "");
    String[] words = cleanText.trim().split("\\s+");
    String firstWord = words[0];
    String lastWord = words[words.length - 1];

    String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
    return hash.toUpperCase();
    }
    
    // Generates a random 10-digit Message ID as a String
    public String generateMessageID() {
    long id = (long) (Math.random() * 9_000_000_000L) + 1_000_000_000L;
    return String.valueOf(id);
    }
    
    // Asks the user to choose what to do with the message: send, disregard, or store
    public String sentMessage(Scanner input) {
    System.out.println("Would you like to:");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store Message to send later");
    System.out.print("Enter choice: ");

    String choice = input.nextLine();

    switch (choice) {
        case "1":
            return "Message successfully sent.";
        case "2":
            return "Press 0 to delete the message.";
        case "3":
            return "Message successfully stored.";
        default:
            return "Invalid option.";
        }
    }
    // Returns all messages sent while the program has been running
    public String printMessages() {
        StringBuilder result = new StringBuilder();
        for (String msg : sentMessages) {
            result.append(msg).append("\n");
        }
            return result.toString();
    }

    // Returns the total number of messages sent
    public int returnTotalMessages() {
         return totalMessagesSent;
        }
    
    // Records a sent message's full details and increments the total sent count
    public void recordSentMessage(String messageID, String messageHash, String recipientCell, String messageText) {
           String fullDetails = "Message ID: " + messageID + ", Hash: " + messageHash + ", Recipient: " + recipientCell + ", Message: " + messageText;
           sentMessages.add(fullDetails);
           totalMessagesSent++;
        }
}

   
