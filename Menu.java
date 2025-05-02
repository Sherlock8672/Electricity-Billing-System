
package electricitybillingsystem;

import java.util.*;

class Menu {
    private Scanner sc = new Scanner(System.in);
    private Admin admin = new Admin();
    
    private UserManager userManager = new UserManager();

    public void showMainMenu() {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Admin Login");
            System.out.println("3. User Login");
            System.out.println("4. Forget User Password");
            System.out.println("5. Exit");
            System.out.print("Choose Option: ");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    handleRegistration();
                    break;
                case "2":
                    handleAdminLogin();
                    break;
                case "3":
                    handleUserLogin();
                    break;
                case "4":
                    forgetPass();
                    break;
                    
                case "5":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleRegistration() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Your year of birth: ");
        String question = sc.nextLine();
        userManager.registerUser(username, password, question);
    }
    
    private void forgetPass(){
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Your Year of Birth: ");
        String question = sc.nextLine();

        User user = userManager.findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (!user.getQuestion().equals(question)) {
            System.out.println("Incorrect answer.");
            return;
        }
        System.out.println("Your Password: "+user.getPassword());
    }

    private void handleAdminLogin() {
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine();

        if (admin.login(username, password)) {
            System.out.println("Admin Logged In.");
            showAdminMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Users");
            System.out.println("2. Approve User");
            System.out.println("3. Activate User");
            System.out.println("4. Deactivate User");
            System.out.println("5. Generate Bills");
            System.out.println("6. Logout");
            System.out.print("Choose option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    admin.viewAllUsers(userManager.getAllUsers());
                    break;
                case "2":
                    System.out.print("Enter Username to Approve: ");
                    User userToApprove = userManager.findUser(sc.nextLine());
                    if (userToApprove != null) {
                        admin.approveUser(userToApprove);
                        userManager.saveToFile();
                        System.out.println("User approved.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Username to Activate: ");
                    User userToActivate = userManager.findUser(sc.nextLine());
                    if (userToActivate != null) {
                        admin.activateUser(userToActivate);
                        userManager.saveToFile();
                        System.out.println("User activated.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case "4":
                    System.out.print("Enter Username to Deactivate: ");
                    User userToDeactivate = userManager.findUser(sc.nextLine());
                    if (userToDeactivate != null) {
                        admin.deactivateUser(userToDeactivate);
                        userManager.saveToFile();
                        System.out.println("User deactivated.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case "5":
                    System.out.print("Enter username to generate bill:");
                    //String r = sc.nextLine();
                    User userfind = userManager.findUser(sc.nextLine());
                    if (userfind != null) {
                        admin.generateBills(userfind);
                        userManager.saveToFile();
                        System.out.println("User deactivated.");
                    } else {
                        System.out.println("User not found.");
                    }
                    
                    userManager.saveToFile();
                    System.out.println("Bills generated.");
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleUserLogin() {
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = userManager.findUser(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password.");
            return;
        }

        if (!user.isApproved()) {
            System.out.println("Account not approved yet.");
            return;
        }

        if (!user.isActive()) {
            System.out.println("Account is not active.");
            return;
        }

        System.out.println("\nWelcome, " + username + "!");
        while(true){
            System.out.println("1. See Bill");
            System.out.println("2. Change User Password");
            System.out.println("3. Logout");
            System.out.print("Choose Option: ");
            String c = sc.nextLine();
            switch(c){
                case "1":
                    Bill bill = user.getBill();
                    
                    if (bill != null && !bill.isPaid()) {
                        System.out.println("Your current bill is: $" + bill.getAmount());
                        
                        System.out.print("Do you want to pay? (yes/no): ");
                        if (sc.nextLine().equalsIgnoreCase("yes")) {
                            user.payBill();
                            bill.setAmount(0);
                        } else {
                            System.out.println("Payment skipped.");
                        }
                    } else {
                        System.out.println("No bill due or already paid.");
                    }
                    break;
                
                case "2":
                    /*System.out.print("Enter Username: ");
                    String name = sc.nextLine();*/
                    
                    System.out.print("Enter Old Password: ");
                    

                    if (!user.getPassword().equals(sc.nextLine())) {
                        System.out.println("Incorrect password.");
                        return;
                    }
                    else{
                        System.out.print("Enter New Password: ");
                        
                        user.setPassword(sc.nextLine());
                        System.out.println("Password Changed");
                        userManager.saveToFile();
                        break;
                    }
                    
                case "3":
                    return;
                    
                default:
                    System.out.println("Invalid Option");
                    
            }
        }
        
    }
}
    
    
    
    
    
    
    
    

