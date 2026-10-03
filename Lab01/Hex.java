package Lab01;

public class Hex {
    public static void main(String[] args) {
        int number = 45;

        String binary = "";
        int temp = number;
        while (temp > 0) {
            binary = (temp % 2) + binary;
            temp = temp / 2;
        }
        System.out.println(binary);
        System.out.println(number);

        String hex = "";
        int temp2 = number;

        while (temp2 > 0) {
            int digit = temp2 % 16;
            String digitChar = "" + digit;
            if (digit == 10) {
                digitChar = "A";
            }
            if (digit == 11) {
                digitChar = "B";
            }
            if (digit == 12) {
                digitChar = "C";
            }
            if (digit == 13) {
                digitChar = "D";
            }
            if (digit == 14) {
                digitChar = "E";
            }
            if (digit == 15) {
                digitChar = "F";
            }
            hex = digitChar + hex;
            temp2 = temp2 / 16;
        }

        System.out.println(hex);
    }
}