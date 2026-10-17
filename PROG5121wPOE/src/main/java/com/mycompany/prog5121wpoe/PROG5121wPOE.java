/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121wpoe;

/**
 *
 * @author Goitsemang Mabuse ST10505287
 */
import java.util.Scanner;

public class PROG5121wPOE {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        Message message = new Message();
        
        // Registration
        System.out.println("*** Registration ***");

        // Keep asking for username until it passes validation
        String username;
        do {
            System.out.print("Enter Username: ");
            username = input.nextLine();
            if (!login.checkUserName(username)) {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        } while (!login.checkUserName(username));

        // Keep asking for password until it passes validation
        String password;
        do {
            System.out.print("Enter Password: ");
            password = input.nextLine();
            if (!login.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        } while (!login.checkPasswordComplexity(password));

        // Keep asking for cell number until it passes validation
        String cellNumber;
        do {
            System.out.print("Enter Cell Phone Number (+27xxxxxxxxx): ");
            cellNumber = input.nextLine();
            if (!login.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        } while (!login.checkCellPhoneNumber(cellNumber));

        // All three fields are valid at this point, so registration will succeed
        String registrationMessage = login.registerUser(username, password, cellNumber);
        System.out.println(registrationMessage);

        // Only proceed to login if registration succeeded
        if ("User successfully registered.".equals(registrationMessage)) {
            System.out.println("\n*** Login ***");

            // Keep asking for login credentials until they match the registered details
            boolean loggedIn = false;
            while (!loggedIn) {
                System.out.print("Enter Username: ");
                String loginUsername = input.nextLine();

                System.out.print("Enter Password: ");
                String loginPassword = input.nextLine();

                String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginStatus);

                loggedIn = login.loginUser(loginUsername, loginPassword);
            }
            // Welcome message and menu loop
           System.out.println("Welcome to QuickChat.");
           boolean running = true;
           while (running) {
                System.out.println("\nPlease select an option:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Enter choice: ");

                String choice = input.nextLine();

                switch (choice) {
                case "1":
                System.out.print("How many messages would you like to send? ");
                int numMessages = Integer.parseInt(input.nextLine());

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
                    message.recordSentMessage(messageID, messageHash, recipientCell, messageText);
                    System.out.println("\nMessage ID: " + messageID);
                    System.out.println("Message Hash: " + messageHash);
                    System.out.println("Recipient: " + recipientCell);
                    System.out.println("Message: " + messageText);
                }
            }

                System.out.println("\nTotal messages sent: " + message.returnTotalMessages());
                 break;
                }
           }
        }
    }
}
        
    