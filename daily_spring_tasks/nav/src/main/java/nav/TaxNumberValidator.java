package nav;

public class TaxNumberValidator {

    private static final int LENGTH = 10;

    public boolean check(String s) {
        if (s.length() != LENGTH || notNumber(s)) {
            throw new IllegalArgumentException("Not a tax number");
        }
        int sum = 0;
        for (int i = 0; i < LENGTH; i++) {
            try {
                int digit = Integer.parseInt(Character.toString(s.charAt(i)));
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Not ...");
            }
            int digit = s.charAt(i) - '0';
            sum += digit * (i + 1);
        }
        int lastDigit = s.charAt(LENGTH - 1) - '0';
        return sum % 11 == lastDigit;
    }

    private boolean notNumber(String s) {
        try {
            Long.parseLong(s);
            return false;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }
}
