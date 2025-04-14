// Teller.java

import java.util.Random;

public class Teller extends Thread {
    private int id;

    public Teller(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Teller " + id + " [Teller " + id + "]: ready to serve");
        while (true) {
            try {
                Customer customer = BankResources.customerQueue.take();

                // Special case for shutting down tellers (optional, based on poison pill)
                if (customer == null) break;

                customer.setTellerId(id);
                System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: serving a customer");

                customer.askTransaction();
                String transaction = customer.giveTransaction();

                if (transaction.equalsIgnoreCase("Withdraw")) {
                    System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: handling withdrawal transaction");
                    System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: going to the manager");
                    BankResources.manager.acquire();
                    System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: getting manager's permission");
                    Thread.sleep(new Random().nextInt(26) + 5);
                    System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: got manager's permission");
                    BankResources.manager.release();
                } else {
                    System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: handling deposit transaction");
                }

                System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: going to safe");
                BankResources.safe.acquire();
                System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: enter safe");
                Thread.sleep(new Random().nextInt(41) + 10);
                System.out.println("Teller " + id + " [Customer " + customer.getCustomerId() + "]: leaving safe");
                BankResources.safe.release();

                customer.transactionDone();

                System.out.println("Teller " + id + " [Teller " + id + "]: ready to serve");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Teller " + id + " [Teller " + id + "]: leaving for the day");
    }
}
