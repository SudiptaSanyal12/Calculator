import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField inputField;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public Calculator() {
        setTitle("Colorful Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(40, 44, 52));

        inputField = new JTextField();
        inputField.setBounds(40, 30, 310, 50);
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.BOLD, 20));
        inputField.setBackground(Color.WHITE);
        inputField.setForeground(Color.BLACK);
        add(inputField);

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        int x = 40, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 70, 50);
            button.setFont(new Font("Arial", Font.BOLD, 18));

            if ("/*-+=.".contains(buttonLabels[i])) {
                button.setBackground(new Color(255, 128, 0));
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(new Color(60, 63, 65));
                button.setForeground(Color.WHITE);
            }

            button.setFocusPainted(false);
            button.addActionListener(this);
            add(button);
            x += 80;
            if ((i + 1) % 4 == 0) {
                x = 40;
                y += 60;
            }
        }

        JButton clearButton = new JButton("C");
        clearButton.setBounds(40, y, 310, 50);
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setBackground(new Color(255, 128, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> inputField.setText(""));
        add(clearButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9\\.]")) {
            inputField.setText(inputField.getText() + command);
        } else if ("+-*/".contains(command)) {
            try {
                num1 = Double.parseDouble(inputField.getText());
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
                return;
            }
            operator = command.charAt(0);
            inputField.setText("");
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 == 0) {
                            inputField.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2; 
                        break;
                }
                inputField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        }
    }

    // ✅ This is the entry point!
    public static void main(String[] args) {
        new Calculator();
    }
}