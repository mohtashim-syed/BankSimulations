// BankSimulation.java

public class BankSimulation {
    private static final int NUM_TELLERS = 3;
    private static final int NUM_CUSTOMERS = 50;

    public static void main(String[] args) {
        // Start tellers
        for (int i = 0; i < NUM_TELLERS; i++) {
            new Teller(i).start();
        }

        // Open the bank after tellers are ready
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            BankResources.bankOpen.release();
        }

        // Start customers
        for (int i = 0; i < NUM_CUSTOMERS; i++) {
            new Customer(i).start();
        }

        // Optionally, insert poison pills if you want tellers to shut down automatically
        // for (int i = 0; i < NUM_TELLERS; i++) {
        //     BankResources.customerQueue.put(null); // causes teller to break out
        // }

        // Bank closes when all threads finish
        System.out.println("The bank closes for the day.");
    }
}
