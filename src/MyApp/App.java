package MyApp;

import MyApp.ui.AuthForm;
import MyApp.ui.ProductTableForm;

import javax.swing.*;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class App {

    public static boolean IS_ADMIN=false;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
            new AuthForm();


    }
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop","root","Nikpro2002");
    }
}