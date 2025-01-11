package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Account {
	int getAccountNumber();
	String getPin();
	double getBalance();
	void setBalance(double balance);
}

class AccountImpl implements Account{
	private int accountNumber;
	private String pin;
	private double balance;

	public AccountImpl(int accountNumber, String pin, double balance) {
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getPin() {
		return pin;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}


public class ATM {
	private Map<Integer, Account> accounts;
	private Scanner scanner;

	public ATM() {
		this.accounts = new HashMap<>();
		this.scanner = new Scanner(System.in);

		// Initialize some accounts for testing
		accounts.put(1234, new AccountImpl(1234, "1234", 1000.0));
		accounts.put(5678, new AccountImpl(5678, "5678", 500.0));
	}

	public void run() {
		System.out.println("Welcome to the ATM!");

		while (true) {
			System.out.println("Enter your account number:");
			int accountNumber = scanner.nextInt();

			if (accounts.containsKey(accountNumber)) {
				Account account = accounts.get(accountNumber);
				System.out.println("Enter your PIN:");
				String pin = scanner.next();

				if (pin.equals(account.getPin())) {
					System.out.println("Welcome, account holder!");
					while (true) {
						System.out.println("Choose an option:");
						System.out.println("1. Check balance");
						System.out.println("2. Withdraw cash");
						System.out.println("3. Deposit money");
						System.out.println("4. Exit");

						int option = scanner.nextInt();

						switch (option) {
						case 1:
							System.out.println("Your balance is: " + account.getBalance());
							break;
						case 2:
							System.out.println("Enter the amount to withdraw:");
							double amount = scanner.nextDouble();

							if (amount > account.getBalance()) {
								System.out.println("Insufficient balance!");
							} else {
								account.setBalance(account.getBalance() - amount);
								System.out.println("Withdrawal successful. New balance: " + account.getBalance());
							}
							break;
						case 3:
							System.out.println("Enter the amount to deposit:");
							amount = scanner.nextDouble();

							account.setBalance(account.getBalance() + amount);
							System.out.println("Deposit successful. New balance: " + account.getBalance());
							break;
						case 4:
							System.out.println("Thank you for using the ATM!");
							return;
						default:
							System.out.println("Invalid option. Please choose again.");
						}
					}
				} else {
					System.out.println("Incorrect PIN. Please try again.");
				}
			} else {
				System.out.println("Account not found. Please try again.");
			}
		}
	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.run();
	}
}
