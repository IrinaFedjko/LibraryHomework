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

public class LibraryController {
    public Object optionForManager() throws SQLException {
        String password = "1234";
        String pass = String.valueOf(Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter password!")));
        if (pass.equals(password)) {
            JOptionPane.showMessageDialog(null, "Correct password");
            String optionForManager = this.getUserInput(
                    "WPlease choose an option\n" +
                            "1.Check all books in library\n" +
                            "2.Check all customers\n" +
                            "3.Add new book\n" +
                            "4.Remove book from library\n" +
                            "5.Update book\n" +
                            "6.Close/Exit");
            switch (optionForManager) {
                case "1":
                    this.seeAllBooks();
                    break;
                case "2":
                    this.checkAllCustomers();
                    break;
                case "3":
                    this.addNewBook();
                    break;
                case "4":
                    this.removeBook();
                    break;
                case "5":
                    this.updateBook();
                    break;
                case "6":
                    JOptionPane.showMessageDialog(null, "Good buy!");
                    break;
            }
            System.exit(0);

            return null;
        } else {
            JOptionPane.showMessageDialog(null, "Wrong input");
        }
        optionForManager();
        return null;
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }


    public Object seeAllBooks() throws SQLException {
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
        return optionForManager();
    }


    private Object checkAllCustomers() throws SQLException {
        try {
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
            List<Customers> customers = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                int bookId = resultSet.getInt("bookId");
                Customers customer = new Customers(id, userName, email, phoneNumber, bookId);
                customers.add(customer);
                System.out.println(customer);
                JOptionPane.showMessageDialog(null, customers, "All customers!", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionForManager();
    }

    private Object addNewBook() throws SQLException {
        try {
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO books (id, title, authorName, edited, numbersOfbooks) VALUES (6, 'In Search of lost time', 'Marcel Proust', 1999, 2)");
            JOptionPane.showMessageDialog(null, "Book added successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionForManager();
    }



    private Object removeBook() throws SQLException {
        try {
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("DELETE FROM books WHERE id = ?");
            JOptionPane.showMessageDialog(null, "Book deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionForManager();
    }


    private Object updateBook() throws SQLException {
        try {
            Connection conn = SQLConnector.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE books SET ? = '?'");
            JOptionPane.showMessageDialog(null, "Book deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return optionForManager();
    }



}

