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
import static org.junit.jupiter.api.Assertion.*;

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

}
