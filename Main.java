import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Main() {
        // Set up the frame
        setTitle("Hermione Services Login");
        setSize(600, 500);  // Increased size to accommodate graphics
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.ORANGE);

        // Panel to hold the welcome and help messages
        JPanel messagePanel = new JPanel(new GridLayout(2, 1));
        messagePanel.setBackground(Color.ORANGE);
        add(messagePanel, BorderLayout.NORTH);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome to Hermione Services", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        messagePanel.add(welcomeLabel);

        // "How can we help you?" message
        JLabel helpMessageLabel = new JLabel("How can we help you?", JLabel.CENTER);
        helpMessageLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        helpMessageLabel.setForeground(Color.WHITE);
        messagePanel.add(helpMessageLabel);

        // Panel for input fields
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBackground(Color.ORANGE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel, BorderLayout.CENTER);

        // Username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
        usernameField = new JTextField();
        panel.add(usernameLabel);
        panel.add(usernameField);

        // Password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Helvetica", Font.PLAIN, 12));
        passwordField = new JPasswordField();
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Main.this, "Username and Password cannot be empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    new GownRentalShop(username).setVisible(true);
                    dispose(); // Close the login window
                }
            }
        });
        buttonPanel.add(loginButton);

        
        // Customer form button
        JButton customerFormButton = new JButton("Customer Form");
        customerFormButton.setFont(new Font("Helvetica", Font.PLAIN, 12));
        buttonPanel.add(customerFormButton);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
 