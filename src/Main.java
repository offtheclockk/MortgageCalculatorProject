import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {

            int principal = (int) readNumber("Principal ($1K - $1M): ", 1_000, 1_000_000);
            float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
            byte years = (byte) readNumber("Period (Years): ", 1, 30);

            double mortgage = calculateMortgage(principal, annualInterest, years);

                NumberFormat mortgageFormatted = NumberFormat.getCurrencyInstance();
                System.out.println("Your mortgage payment will be " + mortgageFormatted.format(mortgage));
        }

        public static double readNumber(String prompt, double min, double max) {
            Scanner scanner = new Scanner(System.in);
            double value;
            while (true) {
                System.out.print(prompt);
                value = scanner.nextFloat();
                if (value >= min && value <= max)
                    break;
                System.out.println("Enter a value between" + min +  "and " + max);
            }
            return value;
        }

        public static double calculateMortgage(
                int principal,
                float annualInterest,
                byte years) {

            final byte monthsInYear = 12;
            final byte percent = 100;

            float monthlyInterest = (annualInterest / percent) / monthsInYear;
            short numberOfPayments = (short)(years * monthsInYear);

            double mortgage = principal
                    * (monthlyInterest * (Math.pow((1 + monthlyInterest), numberOfPayments)))
                    / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1);

            return mortgage;
        }
    }