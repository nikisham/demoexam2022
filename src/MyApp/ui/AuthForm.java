package MyApp.ui;

import MyApp.App;
import MyApp.manager.UserEntityManager;
import MyApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class AuthForm extends BaseForm {
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton guestButton;
    private JButton authButton;
    private JPanel mainPanel;

    public AuthForm() {
        super(500, 500);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }
    private void initButtons(){
        authButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            try {
                String role = UserEntityManager.getUserRole(login, password);
                if (role == null) {
                    JOptionPane.showMessageDialog(this, "Неверный логин или пароль");
                    return;
                }
                if ("Администратор".equalsIgnoreCase(role)) {
                    App.IS_ADMIN=true;

                }
                dispose();
                new ProductTableForm();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
        guestButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });
    }
}
