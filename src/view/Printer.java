package view;

public class Printer {
    public static void printCardNumberRequest() {
        System.out.println("Enter your card number.");
    }

    public static void printPinCodeRequest() {
        System.out.println("Enter PIN.");
    }

    public static void printOptionMenu() {
        System.out.println("Select option\n1. Print balance.\n2. Cash out.\n3. Cash in.\n4. Finish.");
    }

    public static void printBalance(int value) {
        System.out.println("Your card balance is\n" + value);
    }

    public static void printAmountRequest() {
        System.out.println("Enter amount.");
    }

    public static void printSuccessfulLogIn() {
        System.out.println("You are successfully logged in.");
    }

    public static void printBalanceActionsResponse() {
        System.out.println("Balance successfully changed.");
    }

    public static void printException(int exceptionNumber) {
        switch (exceptionNumber) {
            case 1 -> System.out.println("Invalid card number! Try again.");
            case 2 -> System.out.println("There is no card with this number. Try again.");
            case 3 -> System.out.println("Invalid PIN! Try again.");
            case 4 -> System.out.println("Your card is blocked!");
            case 5 -> System.out.println("You have entered your PIN incorrectly 3 times.");
            case 6 -> System.out.println("There are not enough funds on the balance sheet.");
            case 7 -> System.out.println("There are not enough funds in the ATM.");
            case 8 -> System.out.println("The amount exceeds 1,000,000. Try again.");
            case 9 -> System.out.println("Undefined option.");
            case 10 -> System.out.println("Unexpected value.");
        }
    }
}
