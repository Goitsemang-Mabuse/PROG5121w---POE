/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121wpoe;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Goitsemang Mabuse ST10505287
 */
public class StoredMessageTest {

    private Message message;

    private final String msg1 = "Did you get the cake?";
    private final String msg2 = "Where are you? You are late! I have asked you to be on time.";
    private final String msg3 = "Yohoooo, I am at your gate.";
    private final String msg4 = "It is dinner time!";
    private final String msg5 = "Ok, I am leaving without you.";

    // Developer entry of the brief's test data into the arrays
    @BeforeEach
    public void setUp() {
        message = new Message();
        addTestMessage("1000000001", 0, "+27834557896", msg1, "Sent");
        addTestMessage("2000000002", 1, "+27838884567", msg2, "Stored");
        addTestMessage("3000000003", 2, "+27834484567", msg3, "Disregard");
        addTestMessage("4000000004", 3, "0838884567", msg4, "Sent");
        addTestMessage("5000000005", 4, "+27838884567", msg5, "Stored");
    }

    private void addTestMessage(String id, int number, String recipient, String text, String flag) {
        String hash = message.createMessageHash(id, number, text);
        message.addMessage(id, hash, recipient, text, flag);
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertEquals(Arrays.asList(msg1, msg4), message.getSentMessages());
    }

    @Test
    public void testDisregardedMessagesArrayPopulated() {
        assertEquals(Arrays.asList(msg3), message.getDisregardedMessages());
    }

    @Test
    public void testStoredMessagesArrayPopulated() {
        assertEquals(Arrays.asList(msg2, msg5), message.getStoredMessages());
    }

    @Test
    public void testHashAndIDArraysPopulated() {
        assertEquals(5, message.getMessageHashes().size());
        assertEquals(5, message.getMessageIDs().size());
    }

    @Test
    public void testLongestMessage() {
        assertEquals(msg2, message.longestMessage());
    }

    @Test
    public void testLongestStoredMessage() {
        assertEquals(msg2, message.longestStoredMessage());
    }

    @Test
    public void testSearchByMessageID() {
        assertEquals(msg4, message.searchByMessageID("4000000004"));
    }

    @Test
    public void testSearchByRecipient() {
        assertEquals(Arrays.asList(msg2, msg5), message.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteByMessageHash() {
        String hash = message.createMessageHash("2000000002", 1, msg2);
        String expected = "Message: \"" + msg2 + "\" successfully deleted.";
        assertEquals(expected, message.deleteByHash(hash));
        assertFalse(message.getStoredMessages().contains(msg2));
    }

    @Test
    public void testDisplayReport() {
        String report = message.report("Sent");
        assertTrue(report.contains(message.createMessageHash("1000000001", 0, msg1)));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: " + msg1));
        assertTrue(report.contains(message.createMessageHash("4000000004", 3, msg4)));
        assertTrue(report.contains("Message: " + msg4));
    }
}
    
