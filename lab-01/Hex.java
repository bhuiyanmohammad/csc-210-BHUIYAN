package Lab01;

public class Hex {
    public static void main(String[] args) {
        String hexInput = args[0];

        int total = 0;
        for (int i = 0; i < hexInput.length(); i++) {
            char c = hexInput.charAt(i);
            int value = 0;
            if (c >= '0' && c <= '9') {
                value = c - '0';
            }
            if (c >= 'a' && c <= 'f') {
                value = 10 + (c - 'a');
            }
            total = total * 16 + value;
        }
        System.out.println(total);
    }
}