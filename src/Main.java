import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte monthsInYear = 12;
    final static byte percent = 100;
        public static void main(String[] args) {

            int principal = (int) readNumber("Principal ($1K - $1M): ", 1_000, 1_000_000);
            float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
            byte years = (byte) readNumber("Period (Years): ", 1, 30);

            double mortgage = calculateMortgage(principal, annualInterest, years);
            NumberFormat mortgageFormatted = NumberFormat.getCurrencyInstance();
            System.out.println();
            System.out.println("MORTGAGE");
            System.out.println("________");
            System.out.println("Your mortgage payment will be " + mortgageFormatted.format(mortgage));

            System.out.println();
            System.out.println("PAYMENT SCHEDULE");
            System.out.println("_________________");
            for (short month = 1; month <= years * monthsInYear; month++) {
                double balance = calculateRemainingBalance(principal, annualInterest, years, month);
                System.out.println(NumberFormat.getCurrencyInstance().format(balance));
            }
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

            float monthlyInterest = (annualInterest / percent) / monthsInYear;
            short numberOfPayments = (short)(years * monthsInYear);

            double mortgage = principal
                    * (monthlyInterest * (Math.pow((1 + monthlyInterest), numberOfPayments)))
                    / (Math.pow((1 + monthlyInterest), numberOfPayments) - 1);

            return mortgage;
        }

    public static double calculateRemainingBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

        float monthlyInterest = (annualInterest / percent) / monthsInYear;
        short numberOfPayments = (short)(years * monthsInYear);

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments)
                - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
        }
    }