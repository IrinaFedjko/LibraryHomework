package controllers;

import javax.swing.*;
import java.sql.SQLException;

public class MenuController {

    public void start() throws SQLException {
        this.option();
    }


    private Object option() throws SQLException {
        String option = this.getUserInput("Welcome to Library\n" +
                "Write the number for activity you want to perform\n" +
                "1.Make registration if you are a new user!\n" +
                "2.Enter password to continue for a customer!\n" +
                "3.Enter password to continue for manager\n" +
                "4.Close/Exit\n");

        switch (option) {
            case "1":
                this.makeRegistration();
                break;
            case "2":
                this.enterPasswordToContinueForCustomer();
                break;
            case "3":
                this.enterPasswordToContinueForManager();
                break;
            case "4":
                JOptionPane.showMessageDialog(null, "Good buy!");
                break;
        }
        System.exit(0);

        return null;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }


    private void makeRegistration() throws SQLException {
        LibraryControllerForCustomer libraryControllerForCustomer = new LibraryControllerForCustomer();
        libraryControllerForCustomer.makeRegistrationForCustomer();
    }


    private void enterPasswordToContinueForCustomer() {
        LibraryControllerForCustomer libraryControllerForCustomer = new LibraryControllerForCustomer();
        libraryControllerForCustomer.passwordToContinueForCustomer();
    }

    private void enterPasswordToContinueForManager() throws SQLException {
        LibraryController library = new LibraryController();
        library.optionForManager();
    }



}
