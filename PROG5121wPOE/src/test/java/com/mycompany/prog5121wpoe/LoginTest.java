package com.mycompany.prog5121wpoe;

 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Goitsemang Mabuse ST10505287
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {


@Test
public void testCheckUserName_valid() {
    Login login = new Login();
    assertEquals(true, login.checkUserName("kyl_1"));
    }

@Test
public void testCheckUserName_invalid() {
    Login login = new Login();
    assertEquals(false, login.checkUserName("kyle!!!!!!"));
    }

@Test
public void testCheckPasswordComplexity_valid() {
    Login login = new Login();
    assertEquals(true, login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

@Test
public void testCheckPasswordComplexity_invalid() {
    Login login = new Login();
    assertEquals(false, login.checkPasswordComplexity("password"));
    }

@Test
public void testCheckCellPhoneNumber_valid() {
    Login login = new Login();
    assertEquals(true, login.checkCellPhoneNumber("+27838968976"));
    }

@Test
public void testCheckCellPhoneNumber_invalid() {
    Login login = new Login();
    assertEquals(false, login.checkCellPhoneNumber("08966553"));
    }

@Test
public void testLoginUser_successful(){
    Login login = new Login();
    login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
    assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

@Test
public void testLoginUser_failed(){
    Login login = new Login();
    login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
    assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
}
