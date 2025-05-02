
package electricitybillingsystem;

import java.io.*;

class Bill implements Serializable{
    private static final long serialVersionUID = 1L;
    private int amount;
    private boolean isPaid;

    public Bill(int amount) {
        this.amount = amount;
        this.isPaid = false;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    

    public boolean isPaid() {
        return isPaid;
    }

    public void pay() {
        if (!isPaid) {
            System.out.println("Bill of $" + amount + " paid successfully.");
            isPaid = true;
            
        }
    }
}
