import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantApp {
    private static ArrayList<MenuItem> menu = new ArrayList<>();
    private static ArrayList<Table> tables = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Masaları ve örnek menü ürünlerini başlat
        initializeTables(5);
        initializeMenu();

        while (true) {
            System.out.println("\nRestaurant Order Management System");
            System.out.println("1. View Tables");
            System.out.println("2. Manage Menu");
            System.out.println("3. Create Order");
            System.out.println("4. View Orders");
            System.out.println("5. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTables();
                    break;
                case 2:
                    manageMenu(scanner);
                    break;
                case 3:
                    createOrder(scanner);
                    break;
                case 4:
                    viewOrders();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void initializeTables(int count) {
        for (int i = 1; i <= count; i++) {
            tables.add(new Table(i));
        }
    }

    private static void initializeMenu() {
        menu.add(new MenuItem("Burger", 50.0));
        menu.add(new MenuItem("Pizza", 70.0));
        menu.add(new MenuItem("Cola", 20.0));
    }

    private static void viewTables() {
        for (Table table : tables) {
            System.out.println(table);
        }
    }

    private static void manageMenu(Scanner scanner) {
        System.out.println("\nMenu Management");
        System.out.println("1. View Menu");
        System.out.println("2. Add Menu Item");
        System.out.println("3. Back");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                System.out.println("Menu Items:");
                for (MenuItem item : menu) {
                    System.out.println(item);
                }
                break;
            case 2:
                System.out.print("Enter item name: ");
                String name = scanner.nextLine();
                System.out.print("Enter item price: ");
                double price = scanner.nextDouble();
                menu.add(new MenuItem(name, price));
                System.out.println("Item added successfully.");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice! Returning to main menu.");
        }
    }

    private static void createOrder(Scanner scanner) {
        System.out.print("Enter table number: ");
        int tableNumber = scanner.nextInt();

        Table table = tables.get(tableNumber - 1);
        if (!table.isOccupied()) {
            table.occupy();
        }

        Order order = new Order(tableNumber);
        while (true) {
            System.out.println("Menu Items:");
            for (int i = 0; i < menu.size(); i++) {
                System.out.println((i + 1) + ". " + menu.get(i));
            }
            System.out.print("Choose an item (0 to finish): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            order.addItem(menu.get(choice - 1));
        }
        orders.add(order);
        System.out.println("Order created successfully.");
    }

    private static void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
