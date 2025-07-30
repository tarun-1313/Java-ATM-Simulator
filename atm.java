import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Atm extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ImageIcon atmBackground = new ImageIcon("atm.jpg");
    private ImageIcon rupeeImage = new ImageIcon("rupee.png");

    private int balance = 5000;
    private JLabel outputLabel;
    private String currentPin = "1234"; // Default PIN
    private JPasswordField pinField;
    private JTextField amountField;
    private String currentOperation = ""; // Track current operation

    public Atm() {
        setTitle("ATM Simulator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel welcomeScreen = createWelcomeScreen();
        JPanel instructionScreen = createInstructionScreen();
        JPanel pinScreen = createPinScreen();
        JPanel depositScreen = createDepositScreen();
        JPanel withdrawScreen = createWithdrawScreen();
        JPanel balanceScreen = createBalanceScreen();
        JPanel changePinScreen = createChangePinScreen();

        mainPanel.add(welcomeScreen, "Welcome");
        mainPanel.add(instructionScreen, "Instructions");
        mainPanel.add(pinScreen, "PIN");
        mainPanel.add(depositScreen, "Deposit");
        mainPanel.add(withdrawScreen, "Withdraw");
        mainPanel.add(balanceScreen, "Balance");
        mainPanel.add(changePinScreen, "ChangePIN");

        add(mainPanel);
        cardLayout.show(mainPanel, "Welcome");
    }

    private JPanel createWelcomeScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to our ATM");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(480, 390, 400, 40);
        panel.add(welcomeLabel);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 20));
        okButton.setBounds(600, 460, 100, 40);
        okButton.addActionListener(e -> cardLayout.show(mainPanel, "Instructions"));
        panel.add(okButton);

        return panel;
    }

    private JPanel createInstructionScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        String[] instructions = {
            "Press 1 for Deposit",
            "Press 2 for Withdraw", 
            "Press 3 for Balance ",
            "Press 4 to Exit"
        };

        int yPos = 380;
        for (int i = 0; i < instructions.length; i++) {
            String text = instructions[i];
            JLabel label = new JLabel(text) {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    super.paintComponent(g2);
                }
            };
            label.setFont(new Font("Arial", Font.BOLD, 24));
            label.setForeground(Color.WHITE);
            label.setBounds(490, yPos, 500, 40);
            panel.add(label);

            // Add button for each instruction
            JButton actionButton = new JButton("Click");
            actionButton.setFont(new Font("Arial", Font.BOLD, 16));
            actionButton.setBounds(750, yPos, 80, 35);
            
            final int actionNumber = i + 1;
            actionButton.addActionListener(e -> {
                if (actionNumber == 1) {
                    // Deposit - go to PIN screen
                    currentOperation = "deposit";
                    cardLayout.show(mainPanel, "PIN");
                } else if (actionNumber == 2) {
                    // Withdraw - go to PIN screen
                    currentOperation = "withdraw";
                    cardLayout.show(mainPanel, "PIN");
                } else if (actionNumber == 3) {
                    // Balance Inquiry - go to PIN screen
                    currentOperation = "balance";
                    cardLayout.show(mainPanel, "PIN");
                } else if (actionNumber == 4) {
                    // Exit - go to welcome screen
                    cardLayout.show(mainPanel, "Welcome");
                }
            });
            panel.add(actionButton);
            
            yPos += 45;
        }

        // Add output label for results
        outputLabel = new JLabel("");
        outputLabel.setFont(new Font("Arial", Font.BOLD, 24));
        outputLabel.setForeground(Color.YELLOW);
        outputLabel.setBounds(490, 580, 600, 40);
        panel.add(outputLabel);

        // Key listener for action handling (keeping original functionality)
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyChar();
                if (key == '1') {
                    currentOperation = "deposit";
                    cardLayout.show(mainPanel, "PIN");
                } else if (key == '2') {
                    currentOperation = "withdraw";
                    cardLayout.show(mainPanel, "PIN");
                } else if (key == '3') {
                    currentOperation = "balance";
                    cardLayout.show(mainPanel, "PIN");
                } else if (key == '4') {
                    cardLayout.show(mainPanel, "Welcome");
                }
            }
        });

        return panel;
    }

    private JPanel createPinScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        JLabel enterPinLabel = new JLabel("Enter PIN:");
        enterPinLabel.setFont(new Font("Arial", Font.BOLD, 24));
        enterPinLabel.setForeground(Color.WHITE);
        enterPinLabel.setBounds(480, 380, 200, 40);
        panel.add(enterPinLabel);

        pinField = new JPasswordField(4);
        pinField.setFont(new Font("Arial", Font.BOLD, 20));
        pinField.setBounds(480, 430, 150, 40);
        panel.add(pinField);

        // Buttons in same row
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.setBounds(480, 490, 100, 40);
        enterButton.addActionListener(e -> {
            String enteredPin = new String(pinField.getPassword());
            if (enteredPin.equals(currentPin)) {
                pinField.setText("");
                if (currentOperation.equals("deposit")) {
                    cardLayout.show(mainPanel, "Deposit");
                } else if (currentOperation.equals("withdraw")) {
                    cardLayout.show(mainPanel, "Withdraw");
                } else if (currentOperation.equals("balance")) {
                    updateBalanceDisplay(); // Update balance before showing screen
                    cardLayout.show(mainPanel, "Balance");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                pinField.setText("");
            }
        });
        panel.add(enterButton);

        JButton setPinButton = new JButton("Set PIN");
        setPinButton.setFont(new Font("Arial", Font.BOLD, 16));
        setPinButton.setBounds(600, 490, 100, 40);
        setPinButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "ChangePIN");
        });
        panel.add(setPinButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBounds(720, 490, 100, 40);
        exitButton.addActionListener(e -> {
            pinField.setText("");
            cardLayout.show(mainPanel, "Instructions");
        });
        panel.add(exitButton);

        return panel;
    }

    private JPanel createChangePinScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        // Previous PIN and New PIN in same row with more distance
        JLabel previousPinLabel = new JLabel("Enter Previous PIN:");
        previousPinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        previousPinLabel.setForeground(Color.WHITE);
        previousPinLabel.setBounds(480, 400, 200, 30);
        panel.add(previousPinLabel);

        JPasswordField previousPinField = new JPasswordField(4);
        previousPinField.setFont(new Font("Arial", Font.BOLD, 18));
        previousPinField.setBounds(480, 450, 150, 35);
        panel.add(previousPinField);

        JLabel newPinLabel = new JLabel("Enter New PIN:");
        newPinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setBounds(680, 400, 200, 30);
        panel.add(newPinLabel);

        JPasswordField newPinField = new JPasswordField(4);
        newPinField.setFont(new Font("Arial", Font.BOLD, 18));
        newPinField.setBounds(680, 450, 150, 35);
        panel.add(newPinField);

        // Enter and Back buttons in same row
        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.setBounds(490, 500, 100, 40);
        enterButton.addActionListener(e -> {
            String previousPin = new String(previousPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            
            if (previousPin.equals(currentPin)) {
                if (newPin.length() == 4 && newPin.matches("\\d+")) {
                    currentPin = newPin;
                    JOptionPane.showMessageDialog(this, "PIN changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    previousPinField.setText("");
                    newPinField.setText("");
                    cardLayout.show(mainPanel, "PIN");
                } else {
                    JOptionPane.showMessageDialog(this, "New PIN must be 4 digits!", "Error", JOptionPane.ERROR_MESSAGE);
                    newPinField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect previous PIN!", "Error", JOptionPane.ERROR_MESSAGE);
                previousPinField.setText("");
                newPinField.setText("");
            }
        });
        panel.add(enterButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(690, 500, 100, 40);
        backButton.addActionListener(e -> {
            previousPinField.setText("");
            newPinField.setText("");
            cardLayout.show(mainPanel, "PIN");
        });
        panel.add(backButton);

        return panel;
    }

    private JPanel createDepositScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        JLabel depositLabel = new JLabel("Enter Amount to Deposit:");
        depositLabel.setFont(new Font("Arial", Font.BOLD, 24));
        depositLabel.setForeground(Color.WHITE);
        depositLabel.setBounds(480, 380, 400, 40);
        panel.add(depositLabel);

        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.BOLD, 20));
        amountField.setBounds(480, 430, 200, 40);
        panel.add(amountField);

        // Buttons in same row
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 16));
        depositButton.setBounds(480, 480, 120, 40);
        depositButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                if (amount > 0) {
                    balance += amount;
                    amountField.setText("");
                    
                    // Show success message with current balance
                    JLabel successLabel = new JLabel("Deposited ₹" + amount + ". Current Balance: ₹" + balance);
                    successLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    successLabel.setForeground(Color.GREEN);
                    successLabel.setBounds(480, 510, 500, 40);
                    panel.add(successLabel);
                    
                    
                    panel.revalidate();
                    panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(depositButton);

        // Exit button in same row as Deposit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBounds(620, 480, 100, 40);
        exitButton.addActionListener(e -> {
            amountField.setText("");
            cardLayout.show(mainPanel, "Instructions");
        });
        panel.add(exitButton);

        // Back button in same row as Deposit and Exit
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(740, 480, 100, 40);
        backButton.addActionListener(e -> {
            amountField.setText("");
            cardLayout.show(mainPanel, "PIN");
        });
        panel.add(backButton);

        return panel;
    }

    private JPanel createWithdrawScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        JLabel withdrawLabel = new JLabel("Enter Amount to Withdraw:");
        withdrawLabel.setFont(new Font("Arial", Font.BOLD, 24));
        withdrawLabel.setForeground(Color.WHITE);
        withdrawLabel.setBounds(480, 380, 400, 40);
        panel.add(withdrawLabel);

        JTextField withdrawAmountField = new JTextField(10);
        withdrawAmountField.setFont(new Font("Arial", Font.BOLD, 20));
        withdrawAmountField.setBounds(480, 430, 200, 40);
        panel.add(withdrawAmountField);

        // Buttons in same row
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 16));
        withdrawButton.setBounds(480, 480, 120, 40);
        withdrawButton.addActionListener(e -> {
            try {
                int amount = Integer.parseInt(withdrawAmountField.getText());
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    withdrawAmountField.setText("");
                    
                    // Show success message with current balance
                    JLabel successLabel = new JLabel("Withdrawn ₹" + amount + ". Current Balance: ₹" + balance);
                    successLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    successLabel.setForeground(Color.GREEN);
                    successLabel.setBounds(480, 510, 500, 40);
                    panel.add(successLabel);
                    
                    
                    panel.revalidate();
                    panel.repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(withdrawButton);

        // Exit button in same row as Withdraw
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBounds(620, 480, 100, 40);
        exitButton.addActionListener(e -> {
            withdrawAmountField.setText("");
            cardLayout.show(mainPanel, "Instructions");
        });
        panel.add(exitButton);

        // Back button in same row as Withdraw and Exit
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(740, 480, 100, 40);
        backButton.addActionListener(e -> {
            withdrawAmountField.setText("");
            cardLayout.show(mainPanel, "PIN");
        });
        panel.add(backButton);

        return panel;
    }

    private JPanel createBalanceScreen() {
        FallingRupeesPanel panel = new FallingRupeesPanel();
        panel.setLayout(null);

        JLabel balanceLabel = new JLabel("Your Current Balance:");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(480, 380, 400, 40);
        panel.add(balanceLabel);

        // Create a dynamic label that updates with current balance
        JLabel amountLabel = new JLabel("₹" + balance);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 36));
        amountLabel.setForeground(Color.GREEN);
        amountLabel.setBounds(480, 430, 300, 50);
        panel.add(amountLabel);

        // Buttons in same row
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBounds(480, 480, 100, 40);
        exitButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Instructions");
        });
        panel.add(exitButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBounds(600, 480, 100, 40);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "PIN");
        });
        panel.add(backButton);

        // Add a method to update the balance display
        panel.putClientProperty("amountLabel", amountLabel);

        return panel;
    }

    // Method to update balance display when navigating to balance screen
    private void updateBalanceDisplay() {
        JPanel balancePanel = (JPanel) mainPanel.getComponent(5); // Balance screen is at index 5
        JLabel amountLabel = (JLabel) balancePanel.getClientProperty("amountLabel");
        if (amountLabel != null) {
            amountLabel.setText("₹" + balance);
        }
    }

    class FallingRupeesPanel extends JPanel {
        private final java.util.List<Point> rupeePositions = new ArrayList<>();
        private final Random rand = new Random();
        private final int rupeeCount = 20;

        public FallingRupeesPanel() {
            Timer timer = new Timer(50, e -> {
                for (int i = 0; i < rupeePositions.size(); i++) {
                    Point p = rupeePositions.get(i);
                    p.y += 10;
                    if (p.y > getHeight()) {
                        p.y = -rand.nextInt(600);
                        p.x = rand.nextInt(getWidth());
                    }
                }
                repaint();
            });
            for (int i = 0; i < rupeeCount; i++) {
                rupeePositions.add(new Point(rand.nextInt(800), rand.nextInt(600)));
            }
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(atmBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            for (Point p : rupeePositions) {
                g.drawImage(rupeeImage.getImage(), p.x, p.y, 25, 35, this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Atm().setVisible(true));
    }
}
