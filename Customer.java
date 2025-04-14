// Customer.java

import java.util.Random;

public class Customer extends Thread {
    private int id;
    private String transactionType;
    private int tellerId = -1;

    public Customer(int id) {
        this.id = id;
        this.transactionType = new Random().nextBoolean() ? "Deposit" : "Withdraw";
        System.out.println("Customer " + id + " [Customer " + id + "]: wants to perform a " + transactionType.toLowerCase() + " transaction");
    }

    public int getCustomerId() {
        return id;
    }

    public void setTellerId(int tellerId) {
        this.tellerId = tellerId;
        System.out.println("Customer " + id + " [Teller " + tellerId + "]: selects teller");
        System.out.println("Customer " + id + " [Teller " + tellerId + "]: introduces itself");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(101)); // wait 0-100ms

            BankResources.bankOpen.acquire();
            BankResources.door.acquire();
            System.out.println("Customer " + id + " [Customer " + id + "]: going to bank.");
            System.out.println("Customer " + id + " [Customer " + id + "]: entering bank.");
            System.out.println("Customer " + id + " [Customer " + id + "]: getting in line.");

            BankResources.customerQueue.put(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void askTransaction() {
        System.out.println("Teller " + tellerId + " [Customer " + id + "]: asks for transaction");
    }

    public String giveTransaction() {
        System.out.println("Customer " + id + " [Teller " + tellerId + "]: asks for " + transactionType.toLowerCase() + " transaction");
        return transactionType;
    }

    public void transactionDone() {
        System.out.println("Teller " + tellerId + " [Customer " + id + "]: finishes " + transactionType.toLowerCase() + " transaction.");
        System.out.println("Teller " + tellerId + " [Customer " + id + "]: wait for customer to leave.");
        System.out.println("Customer " + id + " [Teller " + tellerId + "]: leaves teller");
        System.out.println("Customer " + id + " [Customer " + id + "]: goes to door");
        System.out.println("Customer " + id + " [Customer " + id + "]: leaves the bank");
        BankResources.door.release();
    }
}
