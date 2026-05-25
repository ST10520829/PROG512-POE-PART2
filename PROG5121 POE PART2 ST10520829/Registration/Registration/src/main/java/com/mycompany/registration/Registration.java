package com.mycompany.registration;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Registration {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        MessageStorage storage = new MessageStorage();
         
        System.out.println("Welcome to the Authorisation System!");
        
        System.out.println("Register");
        
        System.out.println("Enter first name:");
        String firstName = input.nextLine();
        
        System.out.println("Enter last name:");
        String lastName = input.nextLine();
        
        String userName;
        while (true) {
            System.out.println("Enter userName:");
            userName = input.nextLine();
            
            if (login.checkUserName(userName)) {
                break;
            } else {
                System.out.println("Username is not correctly formatted, Please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
            
        String passWord;
        while (true) {
            System.out.println("Please enter your password");
            passWord = input.nextLine();

            if (login.checkPasswordComplexity(passWord)) {
                break;
            } else { 
                System.out.println("The password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character, Try again.");
            }
        }

        String cellPhone;
        while (true) {
            System.out.println("Enter cellphone number that contains international code(+27)");
            cellPhone = input.nextLine();

            if (login.checkCellPhoneNumber(cellPhone)) { 
                break;
            } else {
                System.out.println("cell phone number incorrectly formatted. Try again.");
            }
        }
        
        String massage = login.registerUser(userName, passWord, cellPhone, firstName, lastName);

        if (massage.equals("User registered successfully.")) {
            System.out.println("\nLogin ");

            while (true) {    
                System.out.println("Enter username:");
                String loginUserName = input.nextLine();

                System.out.println("Enter password:");
                String loginUserPass = input.nextLine();
               
                boolean isLoginSuccessful = login.loginUser(loginUserName, loginUserPass);
                
                System.out.println(login.returnLoginStatus(loginUserName, loginUserPass));
                
                if (isLoginSuccessful) {
                    break; 
                }
                
                System.out.println("Please try again.\n");
            }
        }  
        
        //  Menu (JOptionPane) 
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");
        int total = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));
        int count = 0;

        while (true) {
            String menu = JOptionPane.showInputDialog("""
                Please select an option:
                1) Send Messages
                2) Show Recently Sent Messages
                3) Quit
            """);

            if (menu == null) continue; 

            switch (menu) {
                case "1":
                    if (count >= total) {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                        break;
                    }
                    String recipient = JOptionPane.showInputDialog("Enter recipient number (include country code +27):");
                    if (!Message.isValidRecipient(recipient)) {
                        JOptionPane.showMessageDialog(null, "Invalid recipient format.");
                        break;
                    }

                    String msg = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                    if (!Message.isValidMessage(msg)) {
                        JOptionPane.showMessageDialog(null, "Message too long.");
                        break;
                    }

                    Message m = new Message(recipient, msg);
                    String action = JOptionPane.showInputDialog("""
                        Choose message option:
                        1) Send
                        2) Disregard
                        3) Store for later
                    """);

                    switch (action) {
                        case "1" -> {
                            m.send();
                            storage.addMessage(m);
                            count++;
                            JOptionPane.showMessageDialog(null, "Message sent!\n" + m.getMessageDetails());
                        }
                        case "2" -> {
                            m.disregard();
                            JOptionPane.showMessageDialog(null, "Message disregarded.");
                        }
                        case "3" -> {
                            m.store();
                            storage.addMessage(m);
                            count++;
                            JOptionPane.showMessageDialog(null, "Message stored.\n" + m.getMessageDetails());
                        }
                        default -> JOptionPane.showMessageDialog(null, "Invalid action.");
                    }
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;

                case "3":
                    JOptionPane.showMessageDialog(null, "Exiting. Total messages: " + storage.getTotalMessages());
                    storage.saveMessagesToJson("messages.json");
                    return;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid menu option.");
            }
        }
    }
}