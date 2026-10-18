/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121wpoe; 
/**
 *
 * @author Goitsemang Mabuse ST10505287
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLength_success() {
        Message message = new Message();
        assertEquals("Message ready to send.", message.checkMessageLength("Hi Mike, can you join us for dinner tonight?"));
    }

    @Test
    public void testMessageLength_failure() {
        Message message = new Message();
        String longMessage = "a".repeat(260);
        String result = message.checkMessageLength(longMessage);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }

    @Test
    public void testCheckRecipientCell_success() {
        Message message = new Message();
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testCheckRecipientCell_failure() {
        Message message = new Message();
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", message.checkRecipientCell("08575975889"));
    }

    @Test
    public void testCreateMessageHash() {
        Message message = new Message();
        String hash = message.createMessageHash("0012345678", 0, "Hi Thanks");
        assertEquals("00:0:HITHANKS", hash);
    }

    @Test
    public void testCheckMessageID_valid() {
        Message message = new Message();
        assertTrue(message.checkMessageID("1234567890"));
    }

    @Test
    public void testCheckMessageID_invalid() {
        Message message = new Message();
        assertFalse(message.checkMessageID("12345678901"));
    }

}
