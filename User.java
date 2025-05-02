
package electricitybillingsystem;

import java.io.*;

class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String question;
    private boolean isApproved = false;
    private boolean isActive = false;
    private Bill bill;

    public User() {
    }
    
    

    public User(String username, String password, String question) {
        this.username = username;
        this.password = password;
        this.question = question;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQuestion() {
        return question;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Bill getBill() {
        return bill;
    }

    public void generateBill(User user) {
        if (isApproved && isActive) {
            int amount = (int)(100 + Math.random() * (1000-100+1));
            bill = new Bill(amount);
        } else {
            bill = null;
        }
    }

    public void payBill() {
        if (bill != null && !bill.isPaid()) {
            bill.pay();
        } else {
            System.out.println("No bill to pay or already paid.");
        }
    }

    
    public String toString() {
        return "Username: " + username +
               ", Approved: " + isApproved +
               ", Active: " + isActive +
               ", Bill: " + (bill != null ? "$" + bill.getAmount() + (bill.isPaid() ? " (Paid)" : " (Unpaid)") : "No bill");
    }
}
