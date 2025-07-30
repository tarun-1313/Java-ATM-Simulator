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

<img width="1908" height="961" alt="Screenshot 2025-07-30 122332" src="https://github.com/user-attachments/assets/6292b84e-ff52-44c5-8ed6-9791eba5a7a3" />
<img width="1914" height="985" alt="Screenshot 2025-07-30 122343" src="https://github.com/user-attachments/assets/e7664657-fcb4-4e90-b276-bfb11a771589" />
<img width="1919" height="963" alt="Screenshot 2025-07-30 122358" src="https://github.com/user-attachments/assets/091693c4-b3f0-4d51-b8d8-bb4f52f4bef3" />
<img width="1915" height="1029" alt="Screenshot 2025-07-30 122411" src="https://github.com/user-attachments/assets/cc73abe4-e890-4e1b-b2c7-fcf9eaa2bc3a" />
<img width="1896" height="974" alt="Screenshot 2025-07-30 122442" src="https://github.com/user-attachments/assets/7c4b7674-8289-4d91-b1c5-66982550431a" />
<img width="1881" height="943" alt="Screenshot 2025-07-30 122505" src="https://github.com/user-attachments/assets/fc8769c5-84ec-4117-a16d-cc2a51ef5290" />
<img width="1844" height="964" alt="Screenshot 2025-07-30 122533" src="https://github.com/user-attachments/assets/e590632d-ebe3-4d7c-808c-8d11b927c98a" />



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
