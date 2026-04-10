
import java.util.Scanner;

public class makeOrder {
    public void order(){


        Scanner sc = new Scanner(System.in);

        System.out.println("Enter food (burger / pizza / pasta): ");
        String choice = sc.nextLine();

        Food food = foodFactory.createFood(choice);

        if (food == null) {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("Add cheese? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            food = new cheese(food);
        }

        System.out.println("Add sauce? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            food = new sauce(food);
        }


        System.out.println("Delivery method (home / pickup): ");
        String deliveryChoice = sc.nextLine();

        deliveryStrategy delivery;

        if (deliveryChoice.equalsIgnoreCase("home")) {
            delivery = new homeDelivery();
        } else {
            delivery = new pickup();
        }

        order order = new order();
        customer customer = new customer();
        order.addObserver(customer);

        System.out.println("Would you like a recommendation? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
             AIFacade ai = new AIFacade();
             String suggestion = ai.getSuggestion(food.getDescription());
             System.out.println(suggestion);


        }

        System.out.println("\n--- ORDER SUMMARY ---");
        System.out.println("Food: " + food.getDescription());
        System.out.println("Total Price: " + food.getPrice());
        order.notifyObservers("Your order is ready!");


        delivery.deliver();
        order.notifyObservers("Hope you enjoyed your order!");

        sc.close();
    }
    
    
}
