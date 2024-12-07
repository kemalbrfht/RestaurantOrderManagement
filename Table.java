public class Table {
    private int tableNumber;
    private boolean isOccupied;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void free() {
        isOccupied = false;
    }

    @Override
    public String toString() {
        return "Table " + tableNumber + " - " + (isOccupied ? "Occupied" : "Free");
    }
}
