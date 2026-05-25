/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Bright
 */
public class Login {
    
    String userName;
    String passWord;
    String cellPhone;
    String firstName;
    String lastName;
    
        
    public boolean checkUserName(String userName){
        if (userName.contains("_") && userName.length()  <=5){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean checkPasswordComplexity(String password) {
    boolean hasUpper = false;
    boolean hasNumber = false;
    boolean hasSpecial = false;

    for (char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) {
            hasUpper = true;
        } else if (Character.isDigit(c)) {
            hasNumber = true;
        } else if (!Character.isLetterOrDigit(c)) {
            hasSpecial = true;
        }
    }

    return password.length() >= 8 && hasUpper && hasNumber && hasSpecial;
}
        
    public boolean checkCellPhoneNumber(String cellPhone){
        if (cellPhone.startsWith("+27") && cellPhone.length() == 12){
            return true;
        }else{
           // System.out.println("");
            return false;
        }
    }
    
    
        
    public String registerUser(String userName, String passWord, String cellPhone, String firstName, String lastName){
        if (!checkUserName(userName)){
            return "Username is not correctly formatted,Please ensure that your username contains an underscore and is no more than five characters in lenght.";
            
        }
        
        if (!checkPasswordComplexity(passWord)){
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!checkCellPhoneNumber(cellPhone)){
            return "Cell phone number incorrectly formatted.";
        }
        
        this.userName = userName;
        this.passWord = passWord;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "User registered successfully.";
    }
    
        
    public boolean loginUser(String userName, String passWord ){
        if(this.userName.equals(userName) && this.passWord.equals(passWord)){
           // System.out.println();
            return true;
        }else{
            return false;
        }
    }
    
    public String returnLoginStatus(String userName, String passWord) {
    if (loginUser(userName, passWord)) {
        return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
    }}