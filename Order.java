import java.util.ArrayList;

public class Order {
    private int tableNumber;
    private ArrayList<MenuItem> items;

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Table " + tableNumber + " Order:\n");
        for (MenuItem item : items) {
            orderDetails.append("- ").append(item).append("\n");
        }
        orderDetails.append("Total: ").append(calculateTotal()).append(" TL");
        return orderDetails.toString();
    }
}
