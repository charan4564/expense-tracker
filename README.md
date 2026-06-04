# Expense Tracker

A console-based Java application to track income and expenses
with category management and file-based data persistence.

## Features

- Add income and expenses
- Category-wise expense tracking (Food, Travel, Shopping, Bills, Other)
- View all transactions in a formatted table
- Monthly summary with total income, expenses, and balance
- Budget warning when expenses exceed income
- Data saved to file and reloaded automatically on next run

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList and Custom Classes
- File Handling (BufferedWriter / BufferedReader)
- Exception Handling

## How It Works

1. Run the program
2. Previous data loads automatically from expenses.txt
3. Add income or expenses through the menu
4. View transactions or monthly summary anytime
5. Exit — data saves automatically

## Project Structure

ExpenseTracker/
├── src/
│   └── ExpenseTracker.java
└── README.md

## Key Concepts Used

- Transaction class to represent each entry (OOP)
- ArrayList to store all records dynamically
- BufferedWriter to save data on exit
- BufferedReader to load data on startup

## Future Improvements

- Monthly budget limit with alerts
- Date-wise transaction filtering
- GUI version using Java Swing

## Author

Ediga Sai Charan Goud
charangoud298@gmail.com
