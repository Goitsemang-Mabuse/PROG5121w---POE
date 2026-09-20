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
      public static void main(String[] args){
          Scanner input = new Scanner(System.in);
          Login login = new Login();
          
          //Registration
          System.out.println("*** Registration ***");
          System.out.println("Enter Username: ");
          String username = input.nextLine();
      
          System.out.print("Enter Password: ");
          String password = input.nextLine();
          
          System.out.print("Enter Cell Phone Number (+27xxxxxxxxx)");
          String cellNumber = input.nextLine();
          
          String registrationMessage = login.registerUser(username, password, cellNumber);
          System.out.println(registrationMessage);
          
          //Only proceed to login if registration succeeded
          if ("User successfully registered.".equals(registrationMessage)) {
              System.out.println("\n*** Login ***");
              System.out.print("Enter Username");
              String loginUsername = input.nextLine();
              
              System.out.print("Enter Password: ");
              String loginPassword = input.nextLine();
              
              String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
              System.out.println(loginStatus);
          }
      }   
      
}