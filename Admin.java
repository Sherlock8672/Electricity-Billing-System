
package electricitybillingsystem;

import java.util.*;

class Admin {
    private final String adminUsername = "admin";
    private final String adminPassword = "admin2003";

    public boolean login(String username, String password) {
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    public void approveUser(User user) {
        user.setApproved(true);
    }

    public void activateUser(User user) {
        if (user.isApproved()) {
            user.setActive(true);
        }
    }

    public void deactivateUser(User user) {
        user.setActive(false);
    }

    public void generateBills(User user) {
        user.generateBill(user);
    }

    public void viewAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
