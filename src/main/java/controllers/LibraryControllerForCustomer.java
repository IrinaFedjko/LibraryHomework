package controllers;

import enties.Books;
import enties.Customers;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryControllerForCustomer {


    public void makeRegistrationForCustomer() throws SQLException {
        String userName = JOptionPane.showInputDialog(null, "Please enter your username");
        String email = JOptionPane.showInputDialog(null, "Please enter your email");
        String phoneNumber = JOptionPane.showInputDialog(null, "Please enter your phoneNumber");
        Connection conn = SQLConnector.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO customers (userName, email, phoneNumber, bookId) VALUES ('" + userName + "','" + email + "','" + phoneNumber + "', '0')");
        JOptionPane.showMessageDialog(null, "Successfully registered");
    }

    public void passwordToContinueForCustomer() {
        try {
            String userName1 = JOptionPane.showInputDialog(null, "Please write your username: ");
            String email1 = JOptionPane.showInputDialog(null, "Please enter your email: ");
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers WHERE userName IS NOT NULL AND email IS NOT NULL");
            List<Customers> customers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                int bookId = resultSet.getInt("bookId");
                Customers customer = new Customers(id, userName, email, phoneNumber, bookId);
                customers.add(customer);
                System.out.println(userName);
                if (userName.equals(userName1) && email.equals(email1)) {
                    JOptionPane.showMessageDialog(null, "Correct");
                    continueAsACustomer();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect input. Try again.");
                }
                passwordToContinueForCustomer();

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    private void continueAsACustomer() throws SQLException {
        String optionMenuForCustomer = JOptionPane.showInputDialog(null, "Choose an option: \n" +
                "1. Check all books in library \n" +
                "2. Borrow book \n" +
                "3. Return book \n" +
                "4. Close/Exit\n");

        switch (optionMenuForCustomer) {
            case "1":
                this.seeAllBooks();
                break;
            case "2":
                this.borrowBook();
                break;
            case "3":
                this.returnBook();
                break;
            case "4":
                JOptionPane.showMessageDialog(null, "Good buy!");
                break;
        }
        System.exit(0);

    }


    private void seeAllBooks() throws SQLException {
        try {
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            List<Books> books = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String authorName = resultSet.getString("authorName");
                int edited = resultSet.getInt("edited");
                int numbersOfbooks = resultSet.getInt("numbersOfbooks");
                Books book = new Books(id, title, authorName, edited, numbersOfbooks);
                books.add(book);
                System.out.println(book);
                JOptionPane.showMessageDialog(null, book, "All books!", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        continueAsACustomer();

    }

    private void borrowBook() throws SQLException {
        JOptionPane.showMessageDialog(null, "We need to check availability of this book!", "Lets Borrow Book", JOptionPane.INFORMATION_MESSAGE);
        isAvailableInLibrary();
    }

    private void isAvailableInLibrary() throws SQLException {
        String titleofBorrowedBook = JOptionPane.showInputDialog(null, "Please write title of the book: ");

        Connection conn = SQLConnector.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books WHERE title = 'In Search of lost time'");
        List<Books> books = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String authorName = resultSet.getString("authorName");
            int edited = resultSet.getInt("edited");
            int numbersOfbooks = resultSet.getInt("numbersOfbooks");
            Books book = new Books(id, title, authorName, edited, numbersOfbooks);
            books.add(book);
            System.out.println(book);

            if (titleofBorrowedBook.equals(title) && numbersOfbooks > 0) {
                JOptionPane.showMessageDialog(null, "Book is available!!");
               ArrayList<Books> bookYouBorrow = new ArrayList<>();
             bookYouBorrow.add(book);
                JOptionPane.showMessageDialog(null, "You borrow book " + book + " for two weeks.");


            } else {
                JOptionPane.showMessageDialog(null, "Not available");
            }
            continueAsACustomer();
            return;
        }
    }

    private void returnBook() {
        ArrayList<Books> bookYouBorrow = new ArrayList<>();
        System.out.println(bookYouBorrow);

    }
}


