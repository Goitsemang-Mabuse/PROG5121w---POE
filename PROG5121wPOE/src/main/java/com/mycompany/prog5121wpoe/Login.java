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
        
        private String username;
        private String password;
        private String cellNumber;
        
        public String registerUser(String username, String password, String cellNumber){
            this.username = username;
            this.password = password;
            this.cellNumber = cellNumber;
            
          if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
            } 
          if (!checkPasswordComplexity(password)) {
             return "Password is not correctly formatted; please ensure that the password contains at least eight characters,a capital letter, a number, and a special character."; 
            }
          if (!checkCellPhoneNumber(cellNumber)) {
             return "Cell phone number is incorrectly formatted or does not contain an internationa code; please correct the number and try again."; 
           }
           
         return "User successfully registered.";
    
        }
        
        public boolean loginUser(String username, String password) {
            return this.username.equals(username) && this.password.equals(password);
        }
        
        public String returnLoginStatus(String username, String password) {
            if (loginUser(username, password)) {
                return "Welcome" + username + ", it is great to see you.";
            }
            else{
               return "Username or password incorrect, please try again."; 
            }
                        
                        
         }    
        
        
    }  


