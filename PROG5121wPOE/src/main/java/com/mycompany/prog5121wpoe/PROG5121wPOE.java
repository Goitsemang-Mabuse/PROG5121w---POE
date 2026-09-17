/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog5121wpoe;

/**
 *
 * @author Goitsemang Mabuse ST10505287
 */
public class PROG5121wPOE {

    public static void main(String[] args) {
        Login login = new Login();
        
        //Check whether the system accepts a valid username and rejects an invalid username
        System.out.println(login.checkUserName("kyl_1"));
        System.out.println(login.checkUserName("kyle!!!!!!"));
        
       //Check whether the system accepts a valid password and rejects an invalid one
       System.out.println(login.checkPasswordComplexity("Ch&&sec@ke99!"));
       System.out.println(login.checkPasswordComplexity("password"));
        }
    }

