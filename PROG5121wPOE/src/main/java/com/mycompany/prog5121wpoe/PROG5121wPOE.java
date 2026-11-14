/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121wpoe;

import java.util.List;
import java.util.Scanner;

/**
 * @author Goitsemang Mabuse ST10505287
 */
public class PROG5121wPOE {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Message message = new Message();

        System.out.println("*** Registration ***");

        String username;
        do {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if (!login.checkUserName(username)) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!login.checkUserName(username));

        String password;
        do {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if (!login.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!login.checkPasswordComplexity(password));

        String cellNumber;
        do {
            System.out.print("Enter Cell Phone Number (+27xxxxxxxxx): ");
            cellNumber = input.nextLine();
            if (!login.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        } while (!login.checkCellPhoneNumber(cellNumber));

        System.out.println(login.registerUser(username, password, cellNumber));

        System.out.println("\n*** Login ***");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter Username: ");
            String loginUsername = input.nextLine();
            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
            loggedIn = login.loginUser(loginUsername, loginPassword);
        }

        runMenu(input, message, username);
    }

    // Main menu, runs until the user chooses Quit
    private static void runMenu(Scanner input, Message message, String sender) {
        message.loadStoredMessages();
        System.out.println("Welcome to QuickChat.");

        boolean running = true;
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.print("Enter choice: ");

            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    sendMessages(input, message);
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                case "4":
                    storedMessagesMenu(input, message, sender);
                    break;
                default:
                    System.out.println("Invalid option; please enter 1, 2, 3 or 4.");
            }
        }
    }

    // Lets the user enter the chosen number of messages
    private static void sendMessages(Scanner input, Message message) {
        int numMessages = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("How many messages would you like to send? ");
            try {
                numMessages = Integer.parseInt(input.nextLine().trim());
                valid = numMessages > 0;
            } catch (NumberFormatException e) {
                valid = false;
            }
            if (!valid) {
                System.out.println("Please enter a whole number greater than 0.");
            }
        }

        for (int i = 0; i < numMessages; i++) {
            String recipientCell;
            String recipientResult;
            do {
                System.out.print("Enter recipient cell number (+27xxxxxxxxx): ");
                recipientCell = input.nextLine();
                recipientResult = message.checkRecipientCell(recipientCell);
                System.out.println(recipientResult);
            } while (!recipientResult.equals("Cell phone number successfully captured."));

            String messageText;
            String lengthResult;
            do {
                System.out.print("Enter your message: ");
                messageText = input.nextLine();
                lengthResult = message.checkMessageLength(messageText);
                System.out.println(lengthResult);
            } while (!lengthResult.equals("Message ready to send."));

            String messageID = message.generateMessageID();
            String messageHash = message.createMessageHash(messageID, i, messageText);

            String actionResult = message.sentMessage(input);
            System.out.println(actionResult);

            if (actionResult.equals("Message successfully sent.")) {
                message.addMessage(messageID, messageHash, recipientCell, messageText, "Sent");
                System.out.println("\nMessage ID: " + messageID);
                System.out.println("Message Hash: " + messageHash);
                System.out.println("Recipient: " + recipientCell);
                System.out.println("Message: " + messageText);
            } else if (actionResult.equals("Message successfully stored.")) {
                message.addMessage(messageID, messageHash, recipientCell, messageText, "Stored");
                System.out.println(message.storeMessage());
            } else {
                message.addMessage(messageID, messageHash, recipientCell, messageText, "Disregard");
            }
        }

        System.out.println("\nTotal messages sent: " + message.returnTotalMessages());
    }

    // Menu option 4: works with the stored messages array
    private static void storedMessagesMenu(Scanner input, Message message, String sender) {
        message.loadStoredMessages();

        boolean back = false;
        while (!back) {
            System.out.println("\n*** Stored Messages ***");
            System.out.println("1) Display sender and recipient of all stored messages");
            System.out.println("2) Display the longest stored message");
            System.out.println("3) Search for a message ID");
            System.out.println("4) Search for all messages stored for a recipient");
            System.out.println("5) Delete a message using the message hash");
            System.out.println("6) Display a report of all stored messages");
            System.out.println("7) Back to main menu");
            System.out.print("Enter choice: ");

            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    System.out.print(message.senderAndRecipients(sender));
                    break;
                case "2":
                    System.out.println("Longest stored message: " + message.longestStoredMessage());
                    break;
                case "3":
                    System.out.print("Enter the message ID: ");
                    String id = input.nextLine();
                    System.out.println("Recipient: " + message.recipientForMessageID(id));
                    System.out.println("Message: " + message.searchByMessageID(id));
                    break;
                case "4":
                    System.out.print("Enter the recipient cell number: ");
                    List<String> found = message.searchByRecipient(input.nextLine());
                    if (found.isEmpty()) {
                        System.out.println("No messages found for that recipient.");
                    }
                    for (String text : found) {
                        System.out.println(text);
                    }
                    break;
                case "5":
                    System.out.print("Enter the message hash: ");
                    System.out.println(message.deleteByHash(input.nextLine()));
                    message.storeMessage();
                    break;
                case "6":
                    System.out.print(message.report("Stored"));
                    break;
                case "7":
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option; please enter 1 to 7.");
            }
        }
    }
}