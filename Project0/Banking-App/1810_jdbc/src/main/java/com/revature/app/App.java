package com.revature.app;

import java.util.Scanner;

import com.revature.model.Balance;
import com.revature.model.User;
import com.revature.dao.BalanceImplDao;
import com.revature.dao.UserImplDao;
import com.revature.service.UserService;

public class App {
	private static Scanner sc = new Scanner(System.in);
	private static String firstName = "";
	private static String lastName = "";
	private static String userName = "";
	private static String passWord = "";
	
	public static int invalidOption() {
		System.out.println("\n********** INVALID OPTION **********\n\n\n");
		return 0;
	}
	public static int checkUserInput(String userStr) {
		
		if (userStr.length() > 1 | userStr.length() == 0) { 
			return App.invalidOption();
		}
		else { //Basic menu with 2 options
			if (userStr.matches("[\\D]")) {
				return App.invalidOption();
			}
			else if (Integer.parseInt(userStr + "") == 1) { 
				return 1;
			}
			else if (Integer.parseInt(userStr + "") == 2){ //
				return 2;
			}
			else
				return App.invalidOption();
		}
	}
	public static int checkMenuOptionInput(String userStr) {
		
		if (userStr.length() > 1 | userStr.length() == 0) { 
			return App.invalidOption();
		}
		else { //Menu options
			if (userStr.matches("[\\D]")) {
				return App.invalidOption();
			}
			else if (Integer.parseInt(userStr + "") == 1) { 
				return 1; 
			}
			else if (Integer.parseInt(userStr + "") == 2){ //
				return 2; 
			}
			else if (Integer.parseInt(userStr + "") == 3){ //
				return 3; 
			}
			else if (Integer.parseInt(userStr + "") == 4){ //
				return 4; 
			}
			else if (Integer.parseInt(userStr + "") == 5){ //
				return 5; 
			}
			else
				return App.invalidOption();
		}
	}
	public static boolean isAccNumValid(String string) {
		if (string.length() == 4) {
			return true;
		}
		else if (string.length() != 4) {
			App.invalidOption();
			return false;
		}
		else if (string.contains("[\\D]")) {
			App.invalidOption();
			return false;
		}
		else {
			App.invalidOption();
			return false;
		}
	}
	public static void printRegisterLogin() { // create an account or login
		System.out.println("\n\n\nPlease select an option: \n"
				+ "1. Create an account \n2. Login");
	}
	public static void askAccCreation() { 
		System.out.println("\n\n\nPlease select the account you want to create: \n"
				+ "1. Bank Administrator \n"
				+ "2. Bank User");
	}
	public static String askCredentials() {
		System.out.println("\n\n\nEnter your first name:");
		firstName = sc.nextLine();
		System.out.println("\nEnter your last name:");
		lastName = sc.nextLine();
		System.out.println("\nEnter a username:");
		userName = sc.nextLine();
		System.out.println("\nEnter a password:");
		passWord = sc.nextLine();
		return firstName + " " + lastName + " " + userName + " " + passWord;
	}
	public static String askLogin() {
		System.out.println("\nEnter a username:");
		userName = sc.nextLine();
		System.out.println("\nEnter a password:");
		passWord = sc.nextLine();
		return userName + " " + passWord;
	}
	public static void printAdminMenu() {
		System.out.println("\n\n\nWhat would you like to do next? \n"
				+ "1. View Account Balance \n"
				+ "2. Deposit into Account \n"
				+ "3. Withdraw from Account \n"
				+ "4. Approve User Account \n"
				+ "5. Logout");
	}
	public static void printUserMenu() {
		System.out.println("\n\n\nWhat would you like to do next? \n"
				+ "1. View Account Balance \n"
				+ "2. Deposit into Account \n"
				+ "3. Withdraw from Account \n"
				+ "4. Logout");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		User loggedInUser = new User();
		String credStr = "";
		String credSplit[] = null;
		String approvalStr = "";
		String accStr = "";
		boolean isDone = false;
		int casNum = 0;
		String authStr = "";
		int accType = 0;
		int accNum = 0;
		
		System.out.println(
			  "      ____   _____________________                __    __                 \n"
			+ "     |    |  \\__    ___/\\______   \\_____    ____ |  | _|__| ____    ____  \n"
			+ "     |    |    |    |    |    |  _/\\__  \\  /    \\|  |/ /  |/    \\  / ___\\ \n"
			+ " /\\__|    |    |    |    |    |   \\ / __ \\|   |  \\    <|  |   |  \\/ /_/  >\n"
			+ " \\________| /\\ |____| /\\ |______  /(____  /___|  /__|_ \\__|___|  /\\___  / \n"
			+ "            \\/        \\/        \\/      \\/     \\/     \\/       \\//_____/ \n");
		while (isDone != true) {
			int userInput = 0;
			
			switch (casNum) {
			case 0: //Welcome screen menu then go to case 1 or 2
				printRegisterLogin();
				//Check for valid option then go to case 1 or 2, create account or login
				casNum = App.checkUserInput(sc.nextLine());
				break;
			case 1: //Create an admin or user account
				askAccCreation();
				//Check for valid option then make cheking/savings account
				userInput = App.checkUserInput(sc.nextLine());
				
				if (userInput == 1) {//Ask for credentials, admin
					credStr = App.askCredentials();
					credSplit = credStr.split(" ");
					//Check for valid option then insert admin into database
					userInput = 1;
					if (userInput == 1) { //checking 
						accType = 1;
					}
					else if (userInput == 2) { //saving
						accType = 2;
					}
					authStr = "admin";
					approvalStr = "yes";
					credSplit = credStr.split(" ");
					UserImplDao.getUserDao().insertUserProcedure(
							new User(authStr, credSplit[0], credSplit[1], credSplit[2], credSplit[3], approvalStr),
							new Balance(accType, 0));
					casNum = 3;
				}
				else if (userInput == 2) { //Ask for credentials, user
					credStr = App.askCredentials();
					credSplit = credStr.split(" ");
					loggedInUser = UserImplDao.getUserDao().getUser(credSplit[2]);
					//Check for valid option then insert user into database
					accType = 1;
					authStr = "user";
					approvalStr = "no";
					credSplit = credStr.split(" ");
					UserImplDao.getUserDao().insertUserProcedure(
							new User(authStr, credSplit[0], credSplit[1], credSplit[2], credSplit[3], approvalStr),
							new Balance(accType, 0));
					casNum = 3;
				}
				else {
					casNum = 1;
				}
				loggedInUser = UserImplDao.getUserDao().getUser(credSplit[2]);
				break;
			case 2: //Ask for login credentials
				credStr = App.askLogin();
				credSplit = credStr.split(" ");
				loggedInUser = UserImplDao.getUserDao().getUser(credSplit[0]);
				//User's account does not exist, direct to account creation menu
				if (loggedInUser.equals(new User())) { 
					System.out.println("\n\n\nThe account does not exist, please create a new account.");
					casNum = 1;
					break;
				}
				//User needs approval, notify and exit the program
				if (UserImplDao.getUserDao().checkApproval(loggedInUser)) { 
					System.out.println("\n\n\nThe account needs approval.\n\n\nLogging out...");
					isDone = true;
					break;
				}
				//checks if user is an admin
				if (UserImplDao.getUserDao().checkUserAuthorization(loggedInUser)) { 
					authStr = "user";
					casNum = 5;
					break;
				}
				//If no match send them to create an account and send to case 1
				//If match proceed to options menu in case 3
				authStr = "admin";
				casNum = 5;
				break;
			case 3: //Option menu for withdraw,deposit,view balance,verify user, and logout From account creation
				String dAmount = "";
				
				if (authStr == "admin")
					App.printAdminMenu();
				else if (authStr == "user")
					App.printUserMenu();
				
				userInput = App.checkMenuOptionInput(sc.nextLine());
				
				if (loggedInUser.equals(new User())) { 
					System.out.println("\n\n\nThe account does not exist, please create a new account.");
					casNum = 1;
					break;
				}
				//User needs approval, notify and exit the program
				if (UserImplDao.getUserDao().checkApproval(loggedInUser)) { 
					System.out.println("\n\n\nThe account needs approval.\n\n\nLogging out...");
					isDone = true;
					break;
				}
				
				if (userInput == 1) { //Retrieve account balance
					//method to retrieve account balance and rint the value to the screen
					System.out.println("\n\n\nYour current account balance is: $" 
					+ BalanceImplDao.getBalanceDao().getAccountBalance(loggedInUser));
					casNum = 3;
					break;
				}
				else if (userInput == 2) {//Deposit amount into account
					System.out.println("\n\n\nEnter the amount you want to deposit.");
					dAmount = sc.nextLine(); //Amount
					
					if(Integer.parseInt(dAmount) < 0) {
						System.out.println("Invalid amount.");
						casNum = 3;
						break;
					}
					//method to insert balance into account
					if (UserService.getUserService().depositMoney(loggedInUser, Integer.parseInt(dAmount))) {
						System.out.println("\n\n\nTransaction completed succesfully.");
					}
					else
						System.out.println("\n\n\nTransaction failed.");
					casNum = 3;
					break;
				}
				else if (userInput == 3) {//Withdraw amount into account 
					System.out.println("\n\n\nEnter the amount you want to withdraw.");
					dAmount = sc.nextLine(); //Amount
					
					if(Integer.parseInt(dAmount) < 0) { //check for negatives
						System.out.println("\n\n\nInvalid amount.");
						casNum = 3;
						break;
					}
					boolean canWithdraw = UserService.getUserService().withdrawMoney(loggedInUser, Integer.parseInt(dAmount));
					//method to update changes to balance from account
					if (canWithdraw == true) {
						System.out.println("\n\n\nTransaction completed successfully.");
					}
					else if (canWithdraw == false) {
						System.out.println("\n\n\nNot enough to withdraw. \nTransaction failed.");
					}
					//Print "What would you like to do next?"
					casNum = 3;
					break;
				}
				else if (userInput == 4) { //Verify a user
					
					if (authStr == "user") {
						System.out.println("\n\n\nLogging out...\n\n\nThank you for using our services!");
						isDone = true; //Break and exit program
						break;
					}
					//Retrieve all users from the database with relevant information
					System.out.println(UserService.getUserService().listAllUsers());
					casNum = 4; //Go to case 4 and verify existing users
				}
				else if (userInput == 5) { //Logout
					//Print "Thank you for using our services."
					isDone = true;
					break; //exit the program
				}
				else
					casNum = 3;
			case 4: //Verify a specified User
				System.out.println("\n\n\nSelect the account number for the user you want to verify or enter 0 to cancel.");
				accStr = sc.nextLine();
				accNum = Integer.parseInt(accStr);
				if (accNum == 0) {	//cancel from getAllUsers list
					casNum = 3;
					break;
				}
				UserImplDao.getUserDao().verifyUser(accNum, loggedInUser);
				casNum = 3;
				break;
			case 5: //From login check if account exists, needs approval
				//User's account does not exist, direct to account creation menu
				if (loggedInUser.equals(new User())) { 
					System.out.println("\n\n\nThe account does not exist, please create a new account.");
					casNum = 1;
					break;
				}
				//User needs approval, notify and exit the program
				if (UserImplDao.getUserDao().checkApproval(loggedInUser)) { 
					System.out.println("\n\n\nThe account needs approval.\n\n\nLogging out...");
					isDone = true;
					break;
				}
				casNum = 6;
				break;
			case 6: //menu from login
				dAmount = "";
				
				if (authStr == "admin")
					App.printAdminMenu();
				else if (authStr == "user")
					App.printUserMenu();
				
				userInput = App.checkMenuOptionInput(sc.nextLine());
				
				if (userInput == 1) { //Retrieve account balance
					//method to retrieve account balance and print the value to the screen
					System.out.println("\n\n\nYour current account balance is: $" 
					+ BalanceImplDao.getBalanceDao().getAccountBalance(loggedInUser));
					casNum = 6;
					break;
				}
				else if (userInput == 2) {//Deposit amount into account
					System.out.println("\n\n\nEnter the amount you want to deposit.");
					dAmount = sc.nextLine(); //Amount
					
					if(Integer.parseInt(dAmount) < 0) {
						System.out.println("Invalid amount.");
						casNum = 6;
						break;
					}
					//method to insert balance into account
					if (UserService.getUserService().depositMoney(loggedInUser, Integer.parseInt(dAmount))) {
						System.out.println("\n\n\nTransaction completed succesfully.");
					}
					else
						System.out.println("\n\n\nTransaction failed.");
					casNum = 6;
					break;
				}
				else if (userInput == 3) {//Withdraw amount into account 
					System.out.println("\n\n\nEnter the amount you want to withdraw.");
					dAmount = sc.nextLine(); //Amount
					
					if(Integer.parseInt(dAmount) < 0) { //check for negatives
						System.out.println("\n\n\nInvalid amount.");
						casNum = 6;
						break;
					}
					boolean canWithdraw = UserService.getUserService().withdrawMoney(loggedInUser, Integer.parseInt(dAmount));
					//method to update changes to balance from account
					if (canWithdraw == true) {
						System.out.println("\n\n\nTransaction completed successfully.");
					}
					else if (canWithdraw == false) {
						System.out.println("\n\n\nNot enough to withdraw. \nTransaction failed.");
					}
					casNum = 6;
					break;
				}
				else if (userInput == 4) { //Verify a user
					
					if (authStr == "user") {
						System.out.println("\n\n\nLogging out...\n\n\nThank you for using our services!");
						isDone = true; //Break and exit program
						break;
					}
					//Retrieve all users from the database with relevant information
					System.out.println(UserService.getUserService().listAllUsers());
					casNum = 4; //Go to case 4 and verify existing users
					break;
				}
				else if (userInput == 5) { //Logout
					isDone = true;
					break; //exit the program
				}
				else
					casNum = 6;
					break;
			default:
				System.out.println("Default case triggered. Missing break statement, check code.");
				break;
			}
		}
		System.out.println("\n\n\n********** PROGRAM CLOSING **********");
		sc.close();
	}
}