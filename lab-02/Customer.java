public class Customer {
    private double wallet;

    public Customer() {
        wallet = 500.00;
    }

    public Customer(String filename) {
        try {
            java.util.Scanner fileReader = new java.util.Scanner(new java.io.File(filename));
            wallet = fileReader.nextDouble();
            fileReader.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found, starting with default wallet.");
            wallet = 500.00;
        }
    }

    public double spend(double amount) {
        if (amount > wallet) {
            double remaining = wallet;
            wallet = 0;
            return remaining;
        }
        wallet = wallet - amount;
        return amount;
    }

    public void receive(double amount) {
        wallet = wallet + amount;
    }

    public double checkWallet() {
        return wallet;
    }

    public void save(String filename) {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(filename);
            writer.println(wallet);
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Could not save to file.");
        }
    }
}