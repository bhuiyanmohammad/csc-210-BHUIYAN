import java.math.BigInteger;

public class Hex {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Error: no hex value provided.");
            System.exit(1);
        }

        String hexInput = args[0];
        for (int i = 0; i < hexInput.length(); i++) {
            char c = hexInput.charAt(i);
            boolean validDigit = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
            if (!validDigit) {
                System.err.println("Error: invalid hex character '" + c + "'.");
                System.exit(1);
            }
        }

        BigInteger total = BigInteger.ZERO;
        for (int i = 0; i < hexInput.length(); i++) {
            char c = hexInput.charAt(i);
            int value = 0;
            if (c >= '0' && c <= '9') {
                value = c - '0';
            }
            if (c >= 'a' && c <= 'f') {
                value = 10 + (c - 'a');
            }
            total = total.multiply(BigInteger.valueOf(16)).add(BigInteger.valueOf(value));
        }
        System.out.println(total);
    }
}