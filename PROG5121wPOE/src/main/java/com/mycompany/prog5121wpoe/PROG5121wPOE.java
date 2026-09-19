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
       
       //Check whether the system accepts a valid cell number and rejects an invalid one
       System.out.println(login.checkCellPhoneNumber("+27838968976"));
       System.out.println(login.checkCellPhoneNumber("0838968976"));
       
       //Test registerUser with valid registration details
       System.out.println(login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
        
     
       //Test loginUser and returnLoginStatus with correct or incorrect credentials
       System.out.println(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
       System.out.println(login.loginUser("kyl_1", "wrongpassword")); 
       System.out.println(login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
       System.out.println(login.returnLoginStatus("kyl_1", "wrongpassword"));
       
     }
}

