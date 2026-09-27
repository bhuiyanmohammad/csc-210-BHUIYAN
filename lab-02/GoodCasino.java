public class GoodCasino {
    public static double play(Customer customer, SlotMachine machine, double amount) {
        double actualAmount = customer.spend(amount);
        double winnings = machine.pullLever(actualAmount);
        return winnings;
    }

    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        Customer customer = new Customer("customer.txt");
        SlotMachine machine = new SlotMachine("slot-machine.txt");

        while (true) {
            System.out.println("Wallet: $" + customer.checkWallet());
            System.out.println("Casino pot: $" + machine.getMoneyPot());
            System.out.print("How much would you like to put in? (or type 'quit'): ");
            String userInput = input.nextLine();

            if (userInput.equals("quit")) {
                break;
            }

            double amount = Double.parseDouble(userInput);

            if (customer.checkWallet() <= 0 || machine.getMoneyPot() <= 0) {
                System.out.println("Game over — someone ran out of money!");
                break;
            }

            double winnings = play(customer, machine, amount);
            customer.receive(winnings);

            System.out.println("Reels: " + machine.toString());
            System.out.println("You won: $" + winnings);
        }

        customer.save("customer.txt");
        machine.save("slot-machine.txt");
    }
}