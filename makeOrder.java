import java.util.Scanner;

public class makeOrder {

    private final Scanner sc = new Scanner(System.in);

    public void order() {

        System.out.println("=== MENU ===");
        System.out.println("1. Burger - 20qr");
        System.out.println("2. Pizza - 30qr");
        System.out.println("3. Pasta - 45qr");

        int choiceNum = askNumber("Choose your food (1-3): ", 1, 3);

        String choice = "";
        switch (choiceNum) {
            case 1:
                choice = "burger";
                break;
            case 2:
                choice = "pizza";
                break;
            case 3:
                choice = "pasta";
                break;
        }

        Food food = foodFactory.createFood(choice);

        if (food == null) {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("\nExtras:");
        System.out.println("Extra cheese + 5qr");
        System.out.println("Extra sauce + 3qr");

        if (askYesNo("Add cheese? (yes/no): ")) {
            food = new cheese(food);
        }

        if (askYesNo("Add sauce? (yes/no): ")) {
            food = new sauce(food);
        }

        String deliveryChoice = askDeliveryMethod("Delivery method (home / pickup): ");

        deliveryStrategy delivery;
        if (deliveryChoice.equalsIgnoreCase("home")) {
            delivery = new homeDelivery();
        } else {
            delivery = new pickup();
        }

        order order = new order();
        customer customer = new customer();
        order.addObserver(customer);

        if (askYesNo("Would you like AI nutrition analysis? (yes/no): ")) {
            AIFacade ai = new AIFacade();

            String orderDetails =
                    "Food: " + food.getDescription() +
                    ", Total Price: " + food.getPrice() + "qr "+
                    ", Delivery: " + deliveryChoice;

            String suggestion = ai.getSuggestion(orderDetails);

            System.out.println("\n--- AI RECOMMENDATION ---");
            System.out.println(suggestion);
        }

        System.out.println("\n--- ORDER SUMMARY ---");
        System.out.println("Food: " + food.getDescription());
        System.out.println("Total Price: " + food.getPrice()+ "qr");

        order.notifyObservers("Your order is ready!");
        delivery.deliver();
        order.notifyObservers("Hope you enjoyed your order!");
    }

    private boolean askYesNo(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter yes or no.");
            }
        }
    }

    private int askNumber(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            try {
                int input = Integer.parseInt(sc.nextLine());

                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private String askDeliveryMethod(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("home") || input.equals("pickup")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter home or pickup.");
            }
        }
    }
}