# Java-ATM-Simulator
Swing-Based GUI with Deposit, Withdraw, Balance &amp; PIN Features
# Java ATM Simulator

A fully functional **Java Swing-based ATM application** that allows users to **Deposit**, **Withdraw**, **Check Balance**, and **Set/Change PIN**. It includes a graphical ATM screen layout, rupee animations, and a clean user experience.

---

## ✨ Features

- Java Swing UI with **realistic ATM interface**
- Deposit and Withdraw with input validation
- Check Balance with a default starting balance (₹5000)
- Set/Change PIN feature with confirmation
- Graphical **falling rupee animation**
- Multiple panels: Welcome, Instructions, and Main ATM Screen
- Saves account information in memory during session

---

## 📁 Project Structure

ATM-Project/
├── atm.java # Main Java source file
├── Atm.class # Compiled main class
├── Atm$1.class # Inner class (event handling)
├── Atm$2.class # Inner class (panels & buttons)
├── Atm$3.class
├── Atm$4.class
├── Atm$5.class
├── Atm$6.class
├── Atm$7.class
├── Atm$8.class
├── Atm$FallingRupeesPa.class # Handles falling rupee animation
├── ATMPanel.class # Main ATM panel
├── ATMPanel$Rupee.class # Rupee animation sub-class
├── WelcomePanel.class # Welcome screen UI
├── InstructionPanel.class # Instructions screen UI
├── history.txt # Transaction history (if implemented)
├── atm.jpg # ATM background image
└── rupee.png # Rupee animation image

## 🚀 Getting Started

### Run the Project
```bash
javac atm.java
java Atm

🖱️ Usage
Launch the app with java Atm.

Enter or set the PIN for authentication.

Choose any operation:

Check Balance (initially ₹5000)

Deposit

Withdraw

Set/Change PIN

Exit when finished.
🗺️ Roadmap
Add persistent file/database storage for PIN and balance

Add detailed transaction history display

Enhance GUI with additional animations

Add receipt/mini statement functionality
