import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o número do cartão de crédito: ");
        String cardNumber = ler.nextLine();

        if (isValidCreditCard(cardNumber)) {
            System.out.println("Valid card number");
            System.out.println("Card issuer: " + getCardIssuer(cardNumber));
        } else {
            System.out.println("Invalid card number");
        }
    }

    public static boolean isValidCreditCard(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static String getCardIssuer(String cardNumber) {
        if (Pattern.matches("^4[0-9]{12}(?:[0-9]{3})?$", cardNumber)) {
            return "Visa";
        } else if (Pattern.matches("^5[1-5][0-9]{14}$", cardNumber)) {
            return "MasterCard";
        } else if (Pattern.matches("^3[47][0-9]{13}$", cardNumber)) {
            return "American Express";
        } else if (Pattern.matches("^6(?:011|5[0-9]{2})[0-9]{12}$", cardNumber)) {
            return "Discover";
        } else {
            return "Unknown";
        }
    }
}