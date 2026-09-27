public class SlotMachine {
    private char reel1;
    private char reel2;
    private char reel3;
    private double moneyPot;

    public SlotMachine() {
        moneyPot = 1000000.00;
    }

    public SlotMachine(String filename) {
        try {
            java.util.Scanner fileReader = new java.util.Scanner(new java.io.File(filename));
            moneyPot = fileReader.nextDouble();
            fileReader.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found, starting with default pot.");
            moneyPot = 1000000.00;
        }
    }
    public double pullLever(double amountPutIn) {
    char[] symbols = {'S', 'H', '7'};
    java.util.Random rand = new java.util.Random();

    reel1 = symbols[rand.nextInt(3)];
    reel2 = symbols[rand.nextInt(3)];
    reel3 = symbols[rand.nextInt(3)];

    if (reel1 == reel2 && reel2 == reel3) {
        double winnings = amountPutIn * 10;
        moneyPot = moneyPot - winnings;
        return winnings;
    }

    return 0;
}
public String toString() {
    return "" + reel1 + " " + reel2 + " " + reel3;
}

public double getMoneyPot() {
    return moneyPot;
}
public void save(String filename) {
    try {
        java.io.PrintWriter writer = new java.io.PrintWriter(filename);
        writer.println(moneyPot);
        writer.close();
    } catch (java.io.FileNotFoundException e) {
        System.out.println("Could not save to file.");
    }
}
}