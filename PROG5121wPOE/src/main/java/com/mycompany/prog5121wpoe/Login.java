/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121wpoe;

/**
 *
 * @author Goitsemang Mabuse ST10505287
 * 
 */
public class Login {
   
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
         public boolean checkPasswordComplexity(String password) {
            boolean hasUpperCase = false;
            boolean hasDigit = false;
            boolean hasSpecialChar = false;
            
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                }
                else if (Character.isDigit(c)){
                    hasDigit = true;
                }
                else if (!Character.isLetterOrDigit(c)) {
                    hasSpecialChar = true;
                }
            }
            
            return password.length() >= 8 && hasUpperCase && hasDigit && hasSpecialChar;
         }
         
        //Regex pattern for South African cell numbers, adapted from:
        // https://www.regular-expressions.info/tutorial.html (basic regex reference)
        public boolean checkCellPhoneNumber(String cellNumber) {
            String regex = "^\\+27\\d{9}$";
            return cellNumber.matches(regex);
        }
             
    }


