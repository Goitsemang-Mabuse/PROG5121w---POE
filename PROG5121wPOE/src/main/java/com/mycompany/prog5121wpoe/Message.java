/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121wpoe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Goitsemang Mabuse ST10505287
 */
public class Message {

    // Arrays required by the brief
    private final List<String> sentMessages = new ArrayList<>();
    private final List<String> disregardedMessages = new ArrayList<>();
    private final List<String> storedMessages = new ArrayList<>();
    private final List<String> messageHashes = new ArrayList<>();
    private final List<String> messageIDs = new ArrayList<>();

    // Parallel arrays: the same index is the same message in every list
    private final List<String> recipients = new ArrayList<>();
    private final List<String> messageTexts = new ArrayList<>();
    private final List<String> flags = new ArrayList<>();

    // One stored message as written to and read from messages.json
    private static class StoredMessage {
        String messageID;
        String messageHash;
        String recipient;
        String message;
    }

    // Checks that the message ID is no more than 10 characters long
    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }

    // Checks the recipient cell number using the same regex as Login
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

    // Generates a random 10-digit Message ID
    public String generateMessageID() {
        long id = (long) (Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    // Builds the hash: first 2 digits of ID, message number, first and last word, all caps
    public String createMessageHash(String messageID, int messageNumber, String messageText) {
        String idPart = messageID.substring(0, 2);
        String cleanText = messageText.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleanText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Asks the user to send, disregard or store the message
    public String sentMessage(Scanner input) {
        while (true) {
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
                    System.out.println("Invalid option; please enter 1, 2 or 3.");
            }
        }
    }

    // Adds a message to the parallel arrays and to the array for its flag
    public void addMessage(String id, String hash, String recipient, String text, String flag) {
        messageIDs.add(id);
        messageHashes.add(hash);
        recipients.add(recipient);
        messageTexts.add(text);
        flags.add(flag);

        if (flag.equals("Sent")) {
            sentMessages.add(text);
        } else if (flag.equals("Stored")) {
            storedMessages.add(text);
        } else if (flag.equals("Disregard")) {
            disregardedMessages.add(text);
        }
    }

    // Returns all the messages sent while the program is running
    public String printMessages() {
        StringBuilder result = new StringBuilder();
        for (String text : sentMessages) {
            result.append(text).append("\n");
        }
        return result.toString();
    }

    // Returns the total number of messages sent
    public int returnTotalMessages() {
        return sentMessages.size();
    }

    // Longest message across every array
    public String longestMessage() {
        return longestByFlag(null);
    }

    // Longest stored message
    public String longestStoredMessage() {
        String longest = longestByFlag("Stored");
        return longest.isEmpty() ? "No stored messages." : longest;
    }

    // Searches the parallel arrays for the longest message with the given flag (null = any)
    private String longestByFlag(String flag) {
        String longest = "";
        for (int i = 0; i < messageTexts.size(); i++) {
            boolean matches = flag == null || flags.get(i).equals(flag);
            if (matches && messageTexts.get(i).length() > longest.length()) {
                longest = messageTexts.get(i);
            }
        }
        return longest;
    }

    // Returns the message text for a message ID
    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return messageTexts.get(i);
            }
        }
        return "Message ID not found.";
    }

    // Returns the recipient for a message ID
    public String recipientForMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return recipients.get(i);
            }
        }
        return "Message ID not found.";
    }

    // Returns every sent or stored message for one recipient
    public List<String> searchByRecipient(String recipient) {
        List<String> found = new ArrayList<>();
        for (int i = 0; i < recipients.size(); i++) {
            boolean sentOrStored = flags.get(i).equals("Sent") || flags.get(i).equals("Stored");
            if (recipients.get(i).equals(recipient) && sentOrStored) {
                found.add(messageTexts.get(i));
            }
        }
        return found;
    }

    // Deletes a message from every array using its hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String text = messageTexts.get(i);
                String flag = flags.get(i);

                if (flag.equals("Sent")) {
                    sentMessages.remove(text);
                } else if (flag.equals("Stored")) {
                    storedMessages.remove(text);
                } else {
                    disregardedMessages.remove(text);
                }

                messageIDs.remove(i);
                messageHashes.remove(i);
                recipients.remove(i);
                messageTexts.remove(i);
                flags.remove(i);
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Lists the sender and recipient of every stored message
    public String senderAndRecipients(String sender) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i).equals("Stored")) {
                result.append("Sender: ").append(sender)
                      .append(" | Recipient: ").append(recipients.get(i)).append("\n");
            }
        }
        return result.length() == 0 ? "No stored messages." : result.toString();
    }

    // Report with the full details of every message that has the given flag
    public String report(String flag) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i).equals(flag)) {
                result.append("Message ID: ").append(messageIDs.get(i)).append("\n");
                result.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
                result.append("Recipient: ").append(recipients.get(i)).append("\n");
                result.append("Message: ").append(messageTexts.get(i)).append("\n\n");
            }
        }
        return result.length() == 0 ? "No messages to report." : result.toString();
    }

    // Writes all stored messages to messages.json using Gson (Google, n.d.)
    public String storeMessage() {
        List<StoredMessage> toSave = new ArrayList<>();
        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i).equals("Stored")) {
                StoredMessage stored = new StoredMessage();
                stored.messageID = messageIDs.get(i);
                stored.messageHash = messageHashes.get(i);
                stored.recipient = recipients.get(i);
                stored.message = messageTexts.get(i);
                toSave.add(stored);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("messages.json")) {
            gson.toJson(toSave, writer);
            return "Messages successfully stored to messages.json.";
        } catch (IOException e) {
            return "An error occurred while storing messages: " + e.getMessage();
        }
    }

    // Reads messages.json into the stored messages array, adapted from Google (n.d.)
    // https://github.com/google/gson
    public void loadStoredMessages() {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<StoredMessage>>() { }.getType();

        try (FileReader reader = new FileReader("messages.json")) {
            List<StoredMessage> loaded = gson.fromJson(reader, listType);
            if (loaded != null) {
                for (StoredMessage m : loaded) {
                    if (!messageIDs.contains(m.messageID)) {
                        addMessage(m.messageID, m.messageHash, m.recipient, m.message, "Stored");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // No file yet, so there is nothing to load
        } catch (IOException | JsonParseException e) {
            System.out.println("Stored messages could not be read: " + e.getMessage());
        }
    }

    // Getters so the unit tests can check the arrays
    public List<String> getSentMessages() {
        return sentMessages;
    }

    public List<String> getDisregardedMessages() {
        return disregardedMessages;
    }

    public List<String> getStoredMessages() {
        return storedMessages;
    }

    public List<String> getMessageHashes() {
        return messageHashes;
    }

    public List<String> getMessageIDs() {
        return messageIDs;
    }
}